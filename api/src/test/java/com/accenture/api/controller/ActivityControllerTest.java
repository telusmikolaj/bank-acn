package com.accenture.api.controller;

import com.accenture.api.dto.ActivityDTO;
import com.accenture.api.dto.ActivitySummaryDTO;
import com.accenture.api.form.ActivityForm;
import com.accenture.api.form.MeetingForm;
import com.accenture.api.service.ActivityService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import util.SampleDataFactory;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ActivityController.class)
class ActivityControllerTest {

    public static final String ACTIVITY_URL_TEMPLATE = "/activity";

    @MockBean
    private ActivityService activityService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;


    @Test
    void shouldCreateReturnDtoWhenFromValid() throws Exception {
        //given
        ActivityForm sent = SampleDataFactory.getSampleActvityForm();
        ActivityDTO expected = SampleDataFactory.getSampleActvityDTO();

        //when
        when(this.activityService.create(sent)).thenReturn(expected);

        this.mockMvc.perform(post(ACTIVITY_URL_TEMPLATE)
                        .content(asJsonString(sent))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(expected.getStatus().name()))
                .andExpect(jsonPath("$.description").value(expected.getDescription()))
                .andExpect(jsonPath("$.employeeNumber").value(expected.getEmployeeNumber()))
                .andExpect(jsonPath("$.type").value(expected.getType().name()));

    }

    @Test
    void shouldNotCreateWhenFormNotValid() throws Exception {
        //given
        ActivityForm sent = new MeetingForm();

        this.mockMvc.perform(post(ACTIVITY_URL_TEMPLATE)
                        .content(asJsonString(sent))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));

    }

    @Test
    void shouldReturnEmployeeActivitySummaryWhenGivenValidId() throws Exception {
        //given
        Long employeeId = 1L;
        ActivitySummaryDTO expected = SampleDataFactory.getSampleActivitySummaryDto();

        when(this.activityService.getEmployeeActivitySummary(employeeId)).thenReturn(expected);

        this.mockMvc.perform(get(ACTIVITY_URL_TEMPLATE + "/summary/employee/{employeeId}", employeeId))
                .andExpect(jsonPath("$.activites.CALL.CANCELED").value(1))
                .andExpect(jsonPath("$.activites.MEETING.SCHEDULED").value(5))
                .andExpect(jsonPath("$.activites.OFFER.COMPLETED").value(2));
    }

    @Test
    void shouldReturnCustomerActivitySummaryWhenCifValid() throws Exception {
        //given
        String cif = "1";
        ActivitySummaryDTO expected = SampleDataFactory.getSampleActivitySummaryDto();

        when(this.activityService.getCustomerActivitySummary(cif)).thenReturn(expected);

        this.mockMvc.perform(get(ACTIVITY_URL_TEMPLATE + "/summary/customer/{cif}", cif))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.activites.CALL.CANCELED").value(1))
                .andExpect(jsonPath("$.activites.MEETING.SCHEDULED").value(5))
                .andExpect(jsonPath("$.activites.OFFER.COMPLETED").value(2));
    }

    @Test
    void shouldDelete() throws Exception {
        Long id = 1L;
        doNothing().when(this.activityService).delete(id);
        this.mockMvc.perform(delete(ACTIVITY_URL_TEMPLATE + "/{id}", id));

        verify(this.activityService, times(1)).delete(id);
    }

    @Test
    void shouldUpdateWhenFormValid() throws Exception {
        Long id = 1L;
        MeetingForm sent = SampleDataFactory.getSampleActvityForm();
        ActivityDTO expected = SampleDataFactory.getSampleActvityDTO();
        when(this.activityService.update(id, sent)).thenReturn(expected);

        this.mockMvc.perform(put(ACTIVITY_URL_TEMPLATE + "/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(sent)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(expected.getStatus().name()))
                .andExpect(jsonPath("$.description").value(expected.getDescription()))
                .andExpect(jsonPath("$.employeeNumber").value(expected.getEmployeeNumber()))
                .andExpect(jsonPath("$.type").value(expected.getType().name()));

    }

    @Test
    void shouldNotUpdateWhenFormNotValid() throws Exception {
        Long id = 1L;
        MeetingForm sent = new MeetingForm();

        this.mockMvc.perform(put(ACTIVITY_URL_TEMPLATE + "/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(sent)))
                .andExpect(status().is(400));


    }

    private String asJsonString(Object object) throws JsonProcessingException {
        return this.mapper.writeValueAsString(object);
    }

}
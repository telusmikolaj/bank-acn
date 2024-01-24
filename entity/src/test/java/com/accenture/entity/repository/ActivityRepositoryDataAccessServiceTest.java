package com.accenture.entity.repository;

import com.accenture.api.dto.ActivityDTO;
import com.accenture.api.dto.ActivitySummaryDTO;
import com.accenture.api.form.ActivityForm;
import com.accenture.api.form.ActivityStatus;
import com.accenture.api.form.ActivityType;
import com.accenture.entity.mapper.*;
import com.accenture.entity.model.activity.Activity;
import com.accenture.entity.model.customer.Customer;
import com.accenture.entity.model.employee.Employee;
import com.accenture.entity.specification.FiltersSpecification;
import com.accenture.entity.util.SampleDataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.persistence.EntityNotFoundException;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(MockitoExtension.class)
class ActivityRepositoryDataAccessServiceTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer postgres = new PostgreSQLContainer("postgres");
    private final FiltersSpecification<Activity> filtersSpecification = new FiltersSpecification<>();

    @Autowired
    private ActivityRepository activityRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    private ActivityRepositoryDataAccessService underTest;

    @Mock
    private CustomerRepository customerRepository;

    @Spy
    private ActivityMapper activityMapper = Mappers.getMapper(ActivityMapper.class);

    @Spy
    private MeetingMapper meetingMapper = Mappers.getMapper(MeetingMapper.class);

    @Spy
    private CallMapper callMapper = Mappers.getMapper(CallMapper.class);

    @Spy
    private OfferMapper offerMapper = Mappers.getMapper(OfferMapper.class);

    @BeforeEach
    void setUp() {
        ActivityAbstractMapper activityAbstractMapper = new ActivityAbstractMapper(
                meetingMapper,
                callMapper,
                offerMapper
        );

        this.underTest = new ActivityRepositoryDataAccessService(
                activityRepository,
                employeeRepository,
                customerRepository,
                activityMapper,
                activityAbstractMapper,
                filtersSpecification
        );
    }

    @Test
    void shouldCreateActivity() {
        //given
        ActivityForm sent = SampleDataFactory.getSampleActvityForm();
        Customer sampleCustomer = SampleDataFactory.getSampleCustomer();
        Employee sampleEmployee = SampleDataFactory.getSampleEmployee();

        //when
        when(this.customerRepository.findCustomerByCustomerNumber(anyString())).thenReturn(Optional.of(sampleCustomer));
        when(this.employeeRepository.findEmployeeByEmployeeNumber(anyString())).thenReturn(Optional.of(sampleEmployee));
        ActivityDTO response = this.underTest.create(sent);

        //then
        assertThat(response.getCustomerNumber()).isEqualTo(sampleCustomer.getCustomerNumber());
        assertThat(response.getEmployeeNumber()).isEqualTo(sampleEmployee.getEmployeeNumber());
        assertThat(response.getDate()).isEqualTo(sent.getDate());
        assertThat(response.getStatus()).isEqualTo(sent.getStatus());
        assertThat(response.getType()).isEqualTo(sent.getType());
    }

    @Test
    void shouldNotCreateWhenCustomerNotExists() {
        //given
        ActivityForm sent = SampleDataFactory.getSampleActvityForm();

        //when
        when(this.customerRepository.findCustomerByCustomerNumber(anyString())).thenReturn(Optional.empty());

        //then
        assertThatThrownBy(() -> this.underTest.create(sent)).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void shouldNotCreateWhenEmployeeNotExists() {
        //given
        ActivityForm sent = SampleDataFactory.getSampleActvityForm();

        //then
        assertThatThrownBy(() -> this.underTest.create(sent)).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void shouldThrowExceptionWhenActivityIdNotExists() {

        assertThatThrownBy(() -> this.underTest.delete(999L)).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void shouldUpdateActivity() {
        //given
        Long id = 1L;
        ActivityForm sent = SampleDataFactory.getSampleActvityForm();
        Customer sampleCustomer = SampleDataFactory.getSampleCustomer();
        Employee sampleEmployee = SampleDataFactory.getSampleEmployee();

        //when
        when(this.customerRepository.findCustomerByCustomerNumber(anyString())).thenReturn(Optional.of(sampleCustomer));
        when(this.employeeRepository.findEmployeeByEmployeeNumber(anyString())).thenReturn(Optional.of(sampleEmployee));
        ActivityDTO response = this.underTest.update(id, sent);

        assertThat(response.getCustomerNumber()).isEqualTo(sampleCustomer.getCustomerNumber());
        assertThat(response.getEmployeeNumber()).isEqualTo(sampleEmployee.getEmployeeNumber());
        assertThat(response.getDate()).isEqualTo(sent.getDate());
        assertThat(response.getStatus()).isEqualTo(sent.getStatus());
        assertThat(response.getType()).isEqualTo(sent.getType());


    }

    @Test
    void shouldNotUpdateActivityWhenCustomerNotExsists() {
        //given
        ActivityForm sent = SampleDataFactory.getSampleActvityForm();

        //when
        when(this.customerRepository.findCustomerByCustomerNumber(anyString())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> this.underTest.update(1L, sent)).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void shouldReturnEmployeeActivitySummary() {
        //given
        Long id = 1L;

        //when
        when(this.employeeRepository.existsById(id)).thenReturn(true);
        ActivitySummaryDTO activitySummary = this.underTest.getActivitySummary(id);
        Map<ActivityType, Map<ActivityStatus, Long>> activites = activitySummary.getActivites();
        //then
        assertThat(activites).hasSize(2);
        assertThat(activites.get(ActivityType.MEETING)).isNotNull();
        assertThat(activites.get(ActivityType.OFFER)).isNotNull();
    }

}
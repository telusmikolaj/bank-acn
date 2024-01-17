package com.accenture.entity.mapper;

import com.accenture.api.form.MeetingForm;
import com.accenture.entity.model.activity.Meeting;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface MeetingMapper {


    Meeting toMeeting(MeetingForm meetingForm);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMeeting(MeetingForm form, @MappingTarget Meeting meeting);
}

package com.accenture.entity.mapper;

import com.accenture.api.form.MeetingForm;
import com.accenture.entity.model.activity.Meeting;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MeetingMapper {


    Meeting toMeeting(MeetingForm meetingForm);
}

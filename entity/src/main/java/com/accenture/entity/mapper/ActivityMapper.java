package com.accenture.entity.mapper;

import com.accenture.api.dto.ActivityDTO;
import com.accenture.api.dto.CallDTO;
import com.accenture.api.dto.MeetingDTO;
import com.accenture.api.dto.OfferDTO;
import com.accenture.entity.model.activity.Activity;
import com.accenture.entity.model.activity.Call;
import com.accenture.entity.model.activity.Meeting;
import com.accenture.entity.model.activity.Offer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.SubclassMapping;
@Mapper(componentModel = "spring")
public interface ActivityMapper {

    @SubclassMapping(source = Meeting.class, target = MeetingDTO.class)
    @SubclassMapping(source = Call.class, target = CallDTO.class)
    @SubclassMapping(source = Offer.class, target = OfferDTO.class)
    @Mapping(target = "customerNumber", source = "customer.customerNumber")
    @Mapping(target = "employeeNumber", source = "employee.employeeNumber")
    ActivityDTO toDto(Activity activity);
}

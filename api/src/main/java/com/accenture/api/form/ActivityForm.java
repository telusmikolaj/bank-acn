package com.accenture.api.form;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import java.time.LocalDateTime;

@JsonTypeInfo(
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "activityType",
        use = JsonTypeInfo.Id.NAME,
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = OfferForm.class, name = "OFFER"),
        @JsonSubTypes.Type(value = MeetingForm.class, name = "MEETING"),
        @JsonSubTypes.Type(value = CallForm.class, name = "CALL")
})
@Data
public abstract class ActivityForm {

    private String activityType;

    private LocalDateTime date;

    private ActivityStatus status;

    private String description;

    private String customerNumber;

    private String employeeNumber;
}

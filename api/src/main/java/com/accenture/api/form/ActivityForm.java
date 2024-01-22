package com.accenture.api.form;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@JsonTypeInfo(
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        use = JsonTypeInfo.Id.NAME,
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = OfferForm.class, name = "OFFER"),
        @JsonSubTypes.Type(value = MeetingForm.class, name = "MEETING"),
        @JsonSubTypes.Type(value = CallForm.class, name = "CALL")
})
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class ActivityForm {

    @NotNull
    private ActivityType type;

    @NotNull
    private LocalDateTime date;

    @NotNull
    private ActivityStatus status;

    private String description;

    @NotBlank(message = "Customer number cannot be empty")
    private String customerNumber;

    @NotBlank(message = "Employee number cannot be empty")
    private String employeeNumber;
}

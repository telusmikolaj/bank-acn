package com.accenture.api.form;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CallForm extends ActivityForm {

    private LocalDateTime startTime;

    private ContactDataForm contactData;
}

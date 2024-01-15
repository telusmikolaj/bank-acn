package com.accenture.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactDataDTO {

    private String phone;

    private String email;

}

package com.accenture.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactDataDTO {

    @NotBlank
    private String phone;

    @Email
    private String email;

}

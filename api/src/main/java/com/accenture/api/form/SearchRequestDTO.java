package com.accenture.api.form;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SearchRequestDTO {

    private String column;
    private String value;
}

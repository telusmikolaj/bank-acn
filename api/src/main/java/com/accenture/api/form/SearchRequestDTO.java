package com.accenture.api.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequestDTO {

    private String column;
    private String value;
    private String joinTable;
    private String joinField;
    private Operation operation;

    public enum Operation {
        EQUAL, LIKE, IN, GREATER_THAN, LESS_THAN, BETWEEN, JOIN
    }
}

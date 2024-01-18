package com.accenture.api.form;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SearchRequestDTO {

    private String column;
    private String value;
    private String joinTable;
    private String joinField;
    private String startTime;
    private String endTime;
    private Operation operation;

    public enum Operation {
        EQUAL, LIKE, IN, GREATER_THAN, LESS_THAN, BETWEEN, JOIN, DATE_RANGE
    }
}

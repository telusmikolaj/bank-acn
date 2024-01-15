package com.accenture.entity.util;

import com.accenture.api.form.SearchRequestDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchCondition {
    private String column;
    private String value;
    private SearchRequestDTO.Operation operation;
    private String joinTable;
    private String joinField;
}

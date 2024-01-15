package com.accenture.api.form;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RequestSearchForm {

    private List<SearchRequestDTO> searchRequestDTO;
    private GlobalOperator globalOperator;

    public enum GlobalOperator {
        AND, OR
    }
}

package com.accenture.api.form;

import lombok.Getter;

import java.util.List;

@Getter
public class RequestSearchForm {

    private List<SearchRequestDTO> searchRequestDTO;
    private GlobalOperator globalOperator;

    public enum GlobalOperator {
        AND, OR
    }
}

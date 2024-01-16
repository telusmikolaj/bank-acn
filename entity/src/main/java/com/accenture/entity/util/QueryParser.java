package com.accenture.entity.util;

import com.accenture.api.form.SearchRequestDTO;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class QueryParser {

    public List<List<SearchRequestDTO>> parseSearchString(String searchString) {
        if (searchString == null || searchString.isEmpty()) {
            throw new IllegalArgumentException("Search string is empty");
        }

        String[] orGroups = searchString.split("\\|\\|");
        return Arrays.stream(orGroups)
                .map(this::parseAndConditions)
                .toList();
    }

    private List<SearchRequestDTO> parseAndConditions(String andConditions) {
        String[] conditions = andConditions.split("&&");
        return Arrays.stream(conditions)
                .map(this::parseCondition)
                .toList();
    }

    private SearchRequestDTO parseCondition(String condition) {
        String[] parts = condition.split("==|!=|>|<|>=|<="); // Add more operators as needed
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid search condition format");
        }

        String field = parts[0];
        String value = parts[1];
        SearchRequestDTO.Operation operation = determineOperation(condition, field);

        String joinTable = null;
        String joinField = null;

        if (field.contains(".")) {
            String[] joinParts = field.split("\\.");
            if (joinParts.length != 2) {
                throw new IllegalArgumentException("Invalid join field format");
            }
            joinTable = joinParts[0];
            joinField = joinParts[1];
            field = joinField; // Set field to the field of the joined entity
        }

        return SearchRequestDTO.builder()
                .column(field)
                .value(value)
                .joinTable(joinTable)
                .joinField(joinField)
                .operation(operation)
                .build();
    }

    private SearchRequestDTO.Operation determineOperation(String condition, String field) {
        if (condition.contains("==")) return SearchRequestDTO.Operation.EQUAL;
        if (condition.contains(">")) return SearchRequestDTO.Operation.GREATER_THAN;
        if (condition.contains("<")) return SearchRequestDTO.Operation.LESS_THAN;
        if (condition.contains("%")) return SearchRequestDTO.Operation.LIKE;
        if (condition.contains("BETWEEN")) return SearchRequestDTO.Operation.BETWEEN;


        throw new IllegalArgumentException("Unsupported operation in search condition");
    }

}


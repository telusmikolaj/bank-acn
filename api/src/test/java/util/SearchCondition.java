package util;

import com.accenture.api.form.RequestSearchForm;
import com.accenture.api.form.SearchRequestDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SearchCondition {
    private String column;
    private String value;
    private SearchRequestDTO.Operation operation;
    private String joinTable;
    private String joinField;


    public SearchCondition createSearchCondition(String column, String value, SearchRequestDTO.Operation operation, String joinTable, String joinField) {
        SearchCondition.SearchConditionBuilder condition = SearchCondition.builder()
                .column(column)
                .value(value)
                .operation(operation);

        if (isJoinTableAndFieldProvided(joinTable, joinField)) {
            condition.joinTable(joinTable);
            condition.joinField(joinField);
        }

        return condition.build();
    }

    public RequestSearchForm getSearchRequest(List<SearchCondition> conditions, RequestSearchForm.GlobalOperator globalOperator) {
        List<SearchRequestDTO> dtos = conditions.stream()
                .map(condition -> buildSearchRequestDTO(condition.getColumn(), condition.getValue(), condition.getOperation(), condition.getJoinTable(), condition.getJoinField()))
                .toList();

        return buildRequestSearchForm(dtos, globalOperator);
    }

    private RequestSearchForm buildRequestSearchForm(List<SearchRequestDTO> searchRequestDTOS, RequestSearchForm.GlobalOperator globalOperator) {
        return RequestSearchForm.builder()
                .searchRequestDTO(searchRequestDTOS)
                .globalOperator(globalOperator)
                .build();
    }

    private SearchRequestDTO buildSearchRequestDTO(String column, String value, SearchRequestDTO.Operation operation, String joinTable, String joinField) {
        SearchRequestDTO.SearchRequestDTOBuilder builder = SearchRequestDTO.builder()
                .column(column)
                .value(value)
                .operation(operation);

        if (isJoinTableAndFieldProvided(joinTable, joinField)) {
            builder.joinTable(joinTable)
                    .joinField(joinField);
        }

        return builder.build();
    }

    private boolean isJoinTableAndFieldProvided(String joinTable, String joinField) {
        return joinTable != null && !joinTable.isEmpty() && joinField != null && !joinField.isEmpty();
    }
}

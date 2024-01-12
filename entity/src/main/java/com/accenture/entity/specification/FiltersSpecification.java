package com.accenture.entity.specification;

import com.accenture.api.form.RequestSearchForm;
import com.accenture.api.form.SearchRequestDTO;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FiltersSpecification<T> {

    public Specification<T> getSearchSpecification(SearchRequestDTO searchRequestDTO) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(searchRequestDTO.getColumn()), searchRequestDTO.getValue());
    }

    public Specification<T> getSearchSpecification(List<SearchRequestDTO> searchRequestDtos, RequestSearchForm.GlobalOperator globalOperator) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            for (SearchRequestDTO searchRequestDto : searchRequestDtos) {

                predicates.add(createPredicateFromDto(searchRequestDto, root, criteriaBuilder));
            }

            if (globalOperator.equals(RequestSearchForm.GlobalOperator.AND)) {
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            } else {
                return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
            }
        };
    }

    private Predicate createPredicateFromDto(SearchRequestDTO searchRequestDto, Root<T> root, CriteriaBuilder criteriaBuilder) {
        return switch (searchRequestDto.getOperation()) {
            case EQUAL -> criteriaBuilder.equal(root.get(searchRequestDto.getColumn()), searchRequestDto.getValue());
            case LIKE ->
                    criteriaBuilder.like(root.get(searchRequestDto.getColumn()), "%" + searchRequestDto.getValue() + "%");
            case IN -> {
                String[] splitIn = searchRequestDto.getValue().split(", ");
                yield root.get(searchRequestDto.getColumn()).in(Arrays.asList(splitIn));
            }
            case GREATER_THAN ->
                    criteriaBuilder.greaterThan(root.get(searchRequestDto.getColumn()), searchRequestDto.getValue());
            case LESS_THAN ->
                    criteriaBuilder.lessThan(root.get(searchRequestDto.getColumn()), searchRequestDto.getValue());
            case BETWEEN -> {
                String[] splitBetween = searchRequestDto.getValue().split(",");
                yield criteriaBuilder.between(root.get(searchRequestDto.getColumn()), splitBetween[0], splitBetween[1]);
            }
            case JOIN ->
                    criteriaBuilder.equal(root.join(searchRequestDto.getJoinTable()).get(searchRequestDto.getColumn()), searchRequestDto.getValue());
        };
    }

}

package com.accenture.entity.specification;

import com.accenture.api.form.RequestSearchForm;
import com.accenture.api.form.SearchRequestDTO;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class FiltersSpecification<T> {

    public static final String LIKE_OPERATOR = "%";
    private static final String COMMA_REGEX = ",";

    public Specification<T> getGroupedSearchSpecification(List<List<SearchRequestDTO>> groupedSearchConditions) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> orPredicates = new ArrayList<>();

            for (List<SearchRequestDTO> andGroup : groupedSearchConditions) {
                List<Predicate> andPredicates = andGroup.stream()
                        .map(dto -> createPredicateFromDto(dto, root, criteriaBuilder))
                        .toList();
                orPredicates.add(criteriaBuilder.and(andPredicates.toArray(new Predicate[0])));
            }

            return criteriaBuilder.or(orPredicates.toArray(new Predicate[0]));
        };
    }
    public Specification<T> getSearchSpecification(List<SearchRequestDTO> searchRequestDtos, RequestSearchForm.GlobalOperator globalOperator) {
        if (searchRequestDtos.isEmpty()) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
        }

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = searchRequestDtos.stream()
                    .map(dto -> createPredicateFromDto(dto, root, criteriaBuilder))
                    .toList();

            Predicate[] predicateArray = predicates.toArray(Predicate[]::new);
            return globalOperator.equals(RequestSearchForm.GlobalOperator.AND) ?
                    criteriaBuilder.and(predicateArray) :
                    criteriaBuilder.or(predicateArray);
        };
    }

    private Predicate createPredicateFromDto(SearchRequestDTO dto, Root<T> root, CriteriaBuilder criteriaBuilder) {
        Path<?> path = Optional.ofNullable(dto.getJoinTable())
                .filter(joinTable -> !joinTable.isEmpty())
                .map(joinTable -> createJoinPath(root, joinTable, dto.getJoinField()))
                .orElseGet(() -> root.get(dto.getColumn()));

        return createPredicate(path, dto, criteriaBuilder);
    }

    private Path<?> createJoinPath(Root<T> root, String joinTable, String joinField) {
        Join<Object, Object> join = root.join(joinTable, JoinType.LEFT);
        return join.get(joinField);
    }

    private Predicate createPredicate(Path<?> path, SearchRequestDTO searchRequestDto, CriteriaBuilder criteriaBuilder) {
        return switch (searchRequestDto.getOperation()) {
            case EQUAL -> createEqualPredicate(path, searchRequestDto, criteriaBuilder);
            case LIKE -> createLikePredicate(path, searchRequestDto, criteriaBuilder);
            case IN -> createInPredicate(path, searchRequestDto);
            case GREATER_THAN -> createGreaterThanPredicate(path, searchRequestDto, criteriaBuilder);
            case LESS_THAN -> createLessThanPredicate(path, searchRequestDto, criteriaBuilder);
            case BETWEEN -> createBetweenPredicate(path, searchRequestDto, criteriaBuilder);
            default ->
                    throw new UnsupportedOperationException("Operation " + searchRequestDto.getOperation() + " is not supported.");
        };
    }


    private Predicate createEqualPredicate(Path<?> path, SearchRequestDTO searchRequestDto, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.equal(path, searchRequestDto.getValue());
    }

    private Predicate createLikePredicate(Path<?> path, SearchRequestDTO searchRequestDto, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.like(path.as(String.class), LIKE_OPERATOR + searchRequestDto.getValue() + LIKE_OPERATOR);
    }

    private Predicate createInPredicate(Path<?> path, SearchRequestDTO searchRequestDto) {
        String[] splitIn = searchRequestDto.getValue().split(", ");
        return path.in(Arrays.asList(splitIn));
    }

    private Predicate createGreaterThanPredicate(Path<?> path, SearchRequestDTO searchRequestDto, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.greaterThan(path.as(String.class), searchRequestDto.getValue());
    }

    private Predicate createLessThanPredicate(Path<?> path, SearchRequestDTO searchRequestDto, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.lessThan(path.as(String.class), searchRequestDto.getValue());
    }

    private Predicate createBetweenPredicate(Path<?> path, SearchRequestDTO searchRequestDto, CriteriaBuilder criteriaBuilder) {
        String[] splitBetween = searchRequestDto.getValue().split(COMMA_REGEX);
        if (splitBetween.length < 2) {
            throw new IllegalArgumentException("BETWEEN operation requires two parameters.");
        }
        return criteriaBuilder.between(path.as(String.class), splitBetween[0], splitBetween[1]);
    }


}

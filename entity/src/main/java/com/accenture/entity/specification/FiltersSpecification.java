package com.accenture.entity.specification;


import com.accenture.api.form.RequestSearchForm;
import com.accenture.api.form.SearchRequestDTO;
import com.accenture.entity.util.DateValidator;
import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.sqm.PathElementException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FiltersSpecification<T> {

    public static final String LIKE_OPERATOR = "%";
    private static final String COMMA_REGEX = ",";

    public Specification<T> getSearchSpecification(RequestSearchForm requestSearchForm) {
        if (requestSearchForm.getSearchRequestDTO().isEmpty()) return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();


        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = requestSearchForm.getSearchRequestDTO().stream()
                    .map(dto -> createPredicateFromDto(dto, root, criteriaBuilder))
                    .toList();

            Predicate[] predicateArray = predicates.toArray(new Predicate[0]);
            return applyGlobalOperator(predicateArray, requestSearchForm.getGlobalOperator(), criteriaBuilder);
        };
    }

    private Predicate applyGlobalOperator(Predicate[] predicates, RequestSearchForm.GlobalOperator globalOperator, CriteriaBuilder criteriaBuilder) {
        return globalOperator.equals(RequestSearchForm.GlobalOperator.AND) ?
                criteriaBuilder.and(predicates) :
                criteriaBuilder.or(predicates);
    }

    private Predicate createPredicateFromDto(SearchRequestDTO dto, Root<T> root, CriteriaBuilder criteriaBuilder) {
        try {
            Path<?> searchPath = determineSearchPath(dto, root);

            return createPredicate(searchPath, dto, criteriaBuilder);
        } catch (PathElementException ex) {
            log.error("Error resolving attribute '{}' of searched entity: {}", dto.getColumn(), ex.getMessage());
            throw new InvalidParameterException("Could not resolve attribute '" + dto.getColumn() + "' of searched entity");
        }
    }

    private Path<?> determineSearchPath(SearchRequestDTO dto, Root<T> root) {
        return Optional.ofNullable(dto.getJoinTable())
                .filter(joinTable -> !joinTable.isEmpty())
                .map(joinTable -> createJoinPath(root, joinTable, dto.getJoinField()))
                .orElse(root.get(dto.getColumn()));
    }

    private Path<?> createJoinPath(Root<T> root, String joinTable, String joinField) {
        if (joinField == null) throw new InvalidParameterException("join field cannot be null");
        Join<Object, Object> join = root.join(joinTable, JoinType.LEFT);
        return join.get(joinField);
    }

    private Predicate createPredicate(Path<?> path, SearchRequestDTO searchRequestDto, CriteriaBuilder criteriaBuilder) {
        return switch (searchRequestDto.getOperation()) {
            case DATE_RANGE -> createDateRangePredicate(path, searchRequestDto, criteriaBuilder);
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

    private Predicate createDateRangePredicate(Path<?> path, SearchRequestDTO searchRequestDto, CriteriaBuilder criteriaBuilder) {
        if (isRangeDatesNull(searchRequestDto) || isRangeDatesEmpty(searchRequestDto)) {
            throw new IllegalArgumentException("DATE_RANGE operation requires two date parameters.");
        }
        DateValidator.validateDateTimeString(searchRequestDto.getStartTime());
        DateValidator.validateDateTimeString(searchRequestDto.getEndTime());

        return criteriaBuilder.between(path.as(String.class), searchRequestDto.getStartTime(), searchRequestDto.getEndTime());

    }

    private boolean isRangeDatesNull(SearchRequestDTO searchRequestDTO) {
        return
                searchRequestDTO.getStartTime() == null ||
                        searchRequestDTO.getEndTime() == null;
    }

    private boolean isRangeDatesEmpty(SearchRequestDTO searchRequestDTO) {
        return
                searchRequestDTO.getStartTime().isEmpty() ||
                        searchRequestDTO.getEndTime().isEmpty();
    }
}



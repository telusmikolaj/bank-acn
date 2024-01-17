package com.accenture.entity.specification;

import com.accenture.api.form.RequestSearchForm;
import com.accenture.api.form.SearchRequestDTO;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class FiltersSpecification<T> {

    public static final String LIKE_OPERATOR = "%";
    private static final String COMMA_REGEX = ",";

    private static boolean isDate(Path<?> path) {
        return path.getJavaType().equals(LocalDateTime.class) || path.getJavaType().equals(LocalDate.class);
    }

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
        Object value = searchRequestDto.getValue();
        boolean isDateType = isDate(path);

        return switch (searchRequestDto.getOperation()) {
            case EQUAL -> createEqualPredicate(path, isDateType ? parseDateTime(value.toString()) : value, criteriaBuilder);
            case LIKE -> createLikePredicate(path, value.toString(), criteriaBuilder);
            case IN -> createInPredicate(path, value.toString());
            case GREATER_THAN -> isDateType ? createGreaterThanPredicate(path, parseDateTime(value.toString()), criteriaBuilder) : createGreaterThanPredicate(path, value.toString(), criteriaBuilder);
            case LESS_THAN -> isDateType ? createLessThanPredicate(path, parseDateTime(value.toString()), criteriaBuilder) : createLessThanPredicate(path, value.toString(), criteriaBuilder);
            case BETWEEN -> createBetweenPredicate(path, value.toString(), criteriaBuilder, isDateType);
            default ->
                    throw new UnsupportedOperationException("Operation " + searchRequestDto.getOperation() + " is not supported.");
        };
    }

    private Predicate createEqualPredicate(Path<?> path, Object value, CriteriaBuilder criteriaBuilder) {

            return criteriaBuilder.equal(path, value);

    }

    private Predicate createLikePredicate(Path<?> path, Object value, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.like(path.as(String.class), LIKE_OPERATOR + value + LIKE_OPERATOR);
    }

    private Predicate createInPredicate(Path<?> path, String query) {
        String[] splitIn = query.split(", ");
        return path.in(Arrays.asList(splitIn));
    }

    private Predicate createGreaterThanPredicate(Path<?> path, String query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.greaterThan(path.as(String.class), query);
    }
    private Predicate createGreaterThanPredicate(Path<?> path, LocalDateTime date, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.greaterThan(path.as(LocalDateTime.class), date);
    }

    private Predicate createLessThanPredicate(Path<?> path, String query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.lessThan(path.as(String.class), query);
    }
    private Predicate createLessThanPredicate(Path<?> path, LocalDateTime date, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.lessThan(path.as(LocalDateTime.class), date);
    }

    private Predicate createBetweenPredicate(Path<?> path, String value, CriteriaBuilder criteriaBuilder, boolean isDateType) {
        if (isDateType) {
            String[] splitBetween = value.split(COMMA_REGEX);
            if (splitBetween.length != 2) {
                throw new IllegalArgumentException("BETWEEN operation requires two parameters.");
            }
            LocalDateTime start = parseDateTime(splitBetween[0]);
            LocalDateTime end = parseDateTime(splitBetween[1]);
            return createBetweenPredicate(path, start, end, criteriaBuilder);
        } else {
            return createBetweenPredicate(path, value, criteriaBuilder);
        }
    }

    private Predicate createBetweenPredicate(Path<?> path, LocalDateTime start, LocalDateTime end, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.between(path.as(LocalDateTime.class), start, end);
    }

    private Predicate createBetweenPredicate(Path<?> path, String value, CriteriaBuilder criteriaBuilder) {
        String[] splitBetween = value.split(COMMA_REGEX);
        if (splitBetween.length != 2) {
            throw new IllegalArgumentException("BETWEEN operation requires two parameters.");
        }
        return criteriaBuilder.between(path.as(String.class), splitBetween[0], splitBetween[1]);
    }

    LocalDateTime parseDateTime(String dateString) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {
            return LocalDateTime.parse(dateString, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            LocalDate date = LocalDate.parse(dateString, dateFormatter);
            return date.atStartOfDay();
        }
    }


}

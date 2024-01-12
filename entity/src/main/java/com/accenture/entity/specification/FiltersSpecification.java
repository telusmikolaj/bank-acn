package com.accenture.entity.specification;

import com.accenture.api.form.SearchRequestDTO;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FiltersSpecification<T> {

    public Specification<T> getSearchSpecification(SearchRequestDTO searchRequestDTO) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(searchRequestDTO.getColumn()), searchRequestDTO.getValue());
    }

    public Specification<T> getSearchSpecification(List<SearchRequestDTO> searchRequestDtos) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (SearchRequestDTO searchRequestDto : searchRequestDtos) {
                Predicate equal = criteriaBuilder.equal(root.get(searchRequestDto.getColumn()), searchRequestDto.getValue());
                predicates.add(equal);
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

    }
}

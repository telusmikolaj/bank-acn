package com.accenture.entity.repository;

import com.accenture.api.dto.BranchDTO;
import com.accenture.entity.mapper.BranchMapper;
import com.accenture.entity.model.employee.Branch;
import com.accenture.entity.specification.FiltersSpecification;
import com.accenture.entity.util.QueryParser;
import com.accenture.service.BranchDao;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BranchJPADataAccessService implements BranchDao {

    private final BranchRepository branchRepository;

    private final BranchMapper branchMapper;

    private final FiltersSpecification<Branch> filter;

    private final QueryParser queryParser;

    @Override
    public List<BranchDTO> search(String searchQuery) {
        Specification<Branch> groupedSearchSpecification
                = this.filter.getGroupedSearchSpecification(this.queryParser.parseSearchString(searchQuery));

        return this.branchRepository.findAll(groupedSearchSpecification)
                .stream()
                .map(this.branchMapper::toDto)
                .toList();
    }

    @Override
    public List<BranchDTO> getAll() {
        return this.branchRepository.findAll().stream()
                .map(branchMapper::toDto)
                .toList();
    }
}

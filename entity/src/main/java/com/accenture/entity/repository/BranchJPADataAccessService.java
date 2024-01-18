package com.accenture.entity.repository;

import com.accenture.api.dto.BranchDTO;
import com.accenture.api.form.RequestSearchForm;
import com.accenture.entity.mapper.BranchMapper;
import com.accenture.entity.model.employee.Branch;
import com.accenture.entity.specification.FiltersSpecification;
import com.accenture.service.BranchDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BranchJPADataAccessService implements BranchDao {

    private final BranchRepository branchRepository;

    private final BranchMapper branchMapper;

    private final FiltersSpecification<Branch> filter;


    @Override
    public List<BranchDTO> search(RequestSearchForm requestSearchForm) {
        return this.branchRepository.findAll(this.filter.getSearchSpecification(requestSearchForm))
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

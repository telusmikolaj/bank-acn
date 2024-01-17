package com.accenture.service;

import com.accenture.api.dto.BranchDTO;
import com.accenture.api.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchDao branchDao;
    @Override
    public List<BranchDTO> search(String searchQuery) {
        return this.branchDao.search(searchQuery);
    }

    @Override
    public List<BranchDTO> getAll() {
        return this.branchDao.getAll();
    }
}

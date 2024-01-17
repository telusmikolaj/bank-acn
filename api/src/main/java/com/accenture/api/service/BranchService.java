package com.accenture.api.service;

import com.accenture.api.dto.BranchDTO;

import java.util.List;

public interface BranchService {

    List<BranchDTO> search(String searchQuery);
    List<BranchDTO> getAll();
}

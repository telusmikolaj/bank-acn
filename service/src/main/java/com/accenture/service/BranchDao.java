package com.accenture.service;

import com.accenture.api.dto.BranchDTO;

import java.util.List;

public interface BranchDao {

    List<BranchDTO> search(String searchQuery);
    List<BranchDTO> getAll();

}

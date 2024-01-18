package com.accenture.service;

import com.accenture.api.dto.BranchDTO;
import com.accenture.api.form.RequestSearchForm;

import java.util.List;

public interface BranchDao {

    List<BranchDTO> search(RequestSearchForm requestSearchForm);
    List<BranchDTO> getAll();

}

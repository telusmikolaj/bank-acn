package com.accenture.api.controller;

import com.accenture.api.dto.BranchDTO;
import com.accenture.api.form.RequestSearchForm;
import com.accenture.api.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/branch")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService branchService;

    @GetMapping("/search")
    List<BranchDTO> search(@RequestBody RequestSearchForm requestSearchForm) {
        return this.branchService.search(requestSearchForm);
    }

    @GetMapping
    List<BranchDTO> getAll() {
        return this.branchService.getAll();
    }
}

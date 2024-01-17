package com.accenture.api.controller;

import com.accenture.api.dto.BranchDTO;
import com.accenture.api.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/branch")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService branchService;

    @GetMapping("/search")
    List<BranchDTO> getAll(@RequestParam String searchQuery) {
        return this.branchService.search(searchQuery);
    }

    @GetMapping
    List<BranchDTO> getAll() {
        return this.branchService.getAll();
    }
}

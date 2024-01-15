package com.accenture.entity.mapper;

import com.accenture.api.dto.BranchDTO;
import com.accenture.entity.model.employee.Branch;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = AddressMapper.class)
public interface BranchMapper {
    BranchDTO toDto(Branch branch);

    Branch toBranch(BranchDTO dto);

}

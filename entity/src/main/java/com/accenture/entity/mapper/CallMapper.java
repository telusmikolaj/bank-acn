package com.accenture.entity.mapper;


import com.accenture.api.form.CallForm;
import com.accenture.entity.model.activity.Call;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CallMapper {

    Call toCall(CallForm callForm);
}

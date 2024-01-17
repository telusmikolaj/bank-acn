package com.accenture.entity.mapper;


import com.accenture.api.form.CallForm;
import com.accenture.entity.model.activity.Call;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CallMapper {

    Call toCall(CallForm callForm);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCall(CallForm form, @MappingTarget Call call);

}

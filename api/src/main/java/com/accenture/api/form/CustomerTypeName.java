package com.accenture.api.form;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum CustomerTypeName {

    PREMIUM("PREMIUM"),
    REGULAR("REGULAR");

    private final String code;

    CustomerTypeName(String code) {
        this.code = code;
    }

    @JsonCreator
    public static CustomerTypeName decode(final String code) {
        return Stream.of(CustomerTypeName.values()).filter(targetEnum -> targetEnum.code.equals(code)).findFirst().orElse(null);
    }

    @JsonValue
    public String getCode() {
        return code;
    }
}

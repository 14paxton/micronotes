package com.ssi.Enums;

import com.fasterxml.jackson.annotation.*;
import io.micronaut.core.annotation.*;
import io.micronaut.serde.annotation.*;

@ReflectiveAccess
@Serdeable
public enum CountryCodeFormat {
    numeric("numeric"),
    alpha2("alpha2"),
    alpha3("alpha3");

    private final String code;

    CountryCodeFormat(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    @Override
    public String toString() {
        return getCode();
    }
}
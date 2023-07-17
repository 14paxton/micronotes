package com.ssi.request;

import io.micronaut.core.annotation.*;
import io.micronaut.serde.annotation.*;
import lombok.*;

import javax.validation.constraints.*;

@ReflectiveAccess
@Serdeable
public @Getter @AllArgsConstructor  class REST {
    @NotNull
    private String apiPassword;
    @NotNull
    private String apiUsername;
    @NotNull
    private String apiWebAddress;
}
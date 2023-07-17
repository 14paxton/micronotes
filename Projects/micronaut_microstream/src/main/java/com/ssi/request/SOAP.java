package com.ssi.request;

import io.micronaut.core.annotation.*;
import io.micronaut.serde.annotation.*;
import lombok.*;

import javax.validation.constraints.*;

@ReflectiveAccess
@Serdeable
public @Getter
@AllArgsConstructor class SOAP {
    @NotNull
    private String assessmentResultRequestXSLOut;
    @NotNull
    private String binarySecTokenValueType;
    @NotNull
    private String binarySecTokenId;
    @NotNull
    private String binarySecTokenEncodingType;
    @NotNull
    private String wsSecurityPassword;
    @NotNull
    private String wsSecurityUsername;
}
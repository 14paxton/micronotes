package com.ssi.result;

import com.ssi.Enums.*;
import io.micronaut.context.annotation.*;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.*;
import io.micronaut.serde.annotation.*;
import lombok.*;

import javax.validation.*;
import javax.validation.constraints.*;

@ReflectiveAccess
@Serdeable
public @Getter
@Setter
@AllArgsConstructor class SOAP {
    @Nullable
    private String wsSecurityUsername;
    @Nullable
    private String wsSecurityPassword;
    @Nullable
    private String soapAction;
    @Nullable
    private String binarySecTokenValueType;
    @Nullable
    private String binarySecTokenEncodingType;
    @Nullable
    private String binarySecTokenId;
}
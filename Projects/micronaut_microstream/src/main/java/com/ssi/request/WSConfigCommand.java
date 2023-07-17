package com.ssi.request;

import com.ssi.Enums.*;
import io.micronaut.context.annotation.*;
import io.micronaut.core.annotation.*;
import io.micronaut.core.convert.format.*;
import io.micronaut.serde.annotation.*;
import lombok.*;

import javax.validation.*;
import javax.validation.constraints.*;
import java.util.*;

@Requires(classes = CountryCodeFormat.class)
@Serdeable
public @Getter
@AllArgsConstructor class WSConfigCommand {
    @Nullable
    @Valid
    private final REST rest;

    @Nullable
    @Valid
    private final FTP ftp;

    @Nullable
    @Valid
    private final SOAP soap;

    @Nullable
    @MapFormat(transformation = MapFormat.MapTransformation.FLAT)
    private final HashMap<String, String> customAORMap;

    @Nullable
    @Valid
    private final CountryCodeFormat countryCodeFormat;

    @Nullable
    private final @NotBlank Boolean allowNoAuth = false;

    @Nullable
    private final @NotBlank Boolean active = true;

    @Nullable
    private final Boolean transformRequestIn;

    @Nullable
    private final Boolean transformRequestResponse;

    @Nullable
    private final Boolean transformStatusIn;

    @Nullable
    private final Boolean transformStatusResponse;

    @Nullable
    private final String assessmentOrderRequestXSLIn;

    @Nullable
    private final String assessmentOrderRequestXSLOut;

    @Nullable
    private final String assessmentStatusRequestXSLIn;

    @Nullable
    private final String assessmentStatusRequestXSLOut;

    @Nullable
    final String description;
}
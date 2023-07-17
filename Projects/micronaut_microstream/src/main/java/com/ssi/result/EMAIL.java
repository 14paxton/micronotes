package com.ssi.result;

import io.micronaut.core.annotation.*;
import io.micronaut.serde.annotation.*;
import lombok.*;

import javax.validation.constraints.*;

@ReflectiveAccess
@Serdeable
public @Getter
@Setter
@AllArgsConstructor class EMAIL {
    @NotBlank
    private String subject;
    @NotBlank
    private String toAddressList;
    @Nullable
    private String bodyHeader;
    @Nullable
    private String ccAddressList;
}
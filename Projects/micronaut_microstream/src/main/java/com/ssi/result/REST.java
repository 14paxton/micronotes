package com.ssi.result;

import com.ssi.Enums.*;
import io.micronaut.context.annotation.*;
import io.micronaut.core.annotation.*;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.serde.annotation.*;
import lombok.*;

import javax.validation.*;
import javax.validation.constraints.*;

@Requires(classes = HTTPSendType.class)
@ReflectiveAccess
@Serdeable
public @Getter
@Setter
@AllArgsConstructor class REST {
    @NonNull
    private String httpSendMethod;
    @NotBlank
    private String payloadFormat;
    @NotBlank
    private String contentType;

    public REST(@NonNull @Valid HTTPSendType httpSendMethod, @NotBlank String payloadFormat, @NotBlank String contentType) {
        this.httpSendMethod = String.valueOf(httpSendMethod);
        this.payloadFormat = payloadFormat;
        this.contentType = contentType;
    }
}
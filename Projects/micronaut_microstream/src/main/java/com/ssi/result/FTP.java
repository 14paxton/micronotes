package com.ssi.result;

import io.micronaut.core.annotation.*;
import io.micronaut.serde.annotation.*;
import lombok.*;

import javax.validation.constraints.*;

@ReflectiveAccess
@Serdeable
public @Getter
@Setter
@AllArgsConstructor class FTP {
    @Nullable
    private String ftpUsername;
    @Nullable
    private String ftpPassword;
    @Nullable
    private String ftpServer;
    @Nullable
    private String ftpRemoteBaseDir;
    @NotBlank
    private Integer ftpPort;
    @Nullable
    private String ftpPrivateKeyLocation;
    @Nullable
    private String payloadFormat;
}
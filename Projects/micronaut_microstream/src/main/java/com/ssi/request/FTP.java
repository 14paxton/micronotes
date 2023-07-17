package com.ssi.request;

import io.micronaut.core.annotation.*;
import io.micronaut.serde.annotation.*;
import lombok.*;

import javax.validation.constraints.*;

@ReflectiveAccess
@Serdeable
public @Getter
@AllArgsConstructor class FTP {
    @Nullable
    private Long ftpArchiveLimitSecs;
    @Nullable
    private String ftpPassword;
    @Nullable
    private String ftpPrivateKeyLocation;
    @Nullable
    private String ftpRemoteBaseDir;
    @Nullable
    private String ftpUsername;
    @Nullable
    private String fetchFileStringPattern;
    @NotNull
    private Integer ftpPort;
    @NotNull
    private String ftpServer;
}
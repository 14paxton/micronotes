package com.ssi.result;

import io.micronaut.context.annotation.Value;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.*;
import io.micronaut.serde.annotation.*;
import lombok.*;

import javax.validation.*;

@ReflectiveAccess
@Serdeable
public @Getter
@Setter class ClientResult {
    @Nullable
    @Valid
    private REST rest;
    @Nullable
    @Valid
    private EMAIL email;
    @Nullable
    @Valid
    private SOAP soap;
    @Nullable
    @Valid
    private FTP ftp;
    @NonNull
    private String assessmentReportUrlType;

    @Nullable
    private String endpointURL;

    @NonNull
    private Integer retryCount;

    @NonNull
    private Boolean transformResult;

    @Nullable
    private String assessmentResultXSLOut;

    @NonNull
    private Boolean includeScoringGroupData;

    @Nullable
    private String basicAuthPassword;

    @Nullable
    private String basicAuthUsername;

    @Nullable
    private String description;

    @Creator
    public ClientResult(@Nullable REST rest, @Nullable EMAIL email, @Nullable SOAP soap, @Nullable FTP ftp, @NonNull String assessmentReportUrlType, @Nullable String endpointURL, @NonNull Integer retryCount, @NonNull Boolean transformResult, @Nullable String assessmentResultXSLOut, @NonNull Boolean includeScoringGroupData, @Nullable String basicAuthPassword, @Nullable String basicAuthUsername, @Nullable String description) {
        this.rest = rest;
        this.email = email;
        this.soap = soap;
        this.ftp = ftp;
        this.assessmentReportUrlType = assessmentReportUrlType;
        this.endpointURL = endpointURL;
        this.retryCount = retryCount;
        this.transformResult = transformResult;
        this.assessmentResultXSLOut = assessmentResultXSLOut;
        this.includeScoringGroupData = includeScoringGroupData;
        this.basicAuthPassword = basicAuthPassword;
        this.basicAuthUsername = basicAuthUsername;
        this.description = description;
    }

    public ClientResult(ClientResultCommand crcCMD) {
        this(crcCMD.getRest(),
                crcCMD.getEmail(),
                crcCMD.getSoap(),
                crcCMD.getFtp(),
                String.valueOf(crcCMD.getAssessmentReportUrlType()),
                crcCMD.getEndpointURL(),
                crcCMD.getRetryCount(),
                crcCMD.getTransformResult(),
                crcCMD.getAssessmentResultXSLOut(),
                crcCMD.getIncludeScoringGroupData(),
                crcCMD.getBasicAuthPassword(),
                crcCMD.getBasicAuthUsername(),
                crcCMD.getDescription());
    }


}
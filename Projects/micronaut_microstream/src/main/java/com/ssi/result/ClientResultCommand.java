package com.ssi.result;

import com.ssi.Enums.*;
import io.micronaut.context.annotation.*;
import io.micronaut.core.annotation.*;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.serde.annotation.*;
import lombok.*;

import javax.validation.*;
import javax.validation.constraints.*;

@Requires(classes = AssessmentReportUrlType.class)
@Serdeable
@ConfigurationProperties("app.clientResult")
public
@Getter class ClientResultCommand {
    @Nullable
    @Valid
    private final REST rest;
    @Nullable
    @Valid
    private final EMAIL email;
    @Nullable
    @Valid
    private final SOAP soap;
    @Nullable
    @Valid
    private final FTP ftp;
    @NotBlank
    @Valid
    private AssessmentReportUrlType assessmentReportUrlType = AssessmentReportUrlType.REQ_AUTH;;

    @Nullable
    protected final String endpointURL;

    @Min(1)
    @Max(10)
    @NotBlank
    private Integer retryCount = 2;

    @NonNull
    private final Boolean transformResult;

    @Nullable
    private final String assessmentResultXSLOut;

    @NonNull
    private final Boolean includeScoringGroupData;

    @Nullable
    private final String basicAuthPassword;

    @Nullable
    private final String basicAuthUsername;

    @Nullable
    private final String description;

    public ClientResultCommand(@Nullable REST rest, @Nullable EMAIL email, @Nullable SOAP soap, @Nullable FTP ftp, @Nullable AssessmentReportUrlType assessmentReportUrlType, @Nullable String endpointURL, @Nullable Integer retryCount, @NonNull Boolean transformResult, @Nullable String assessmentResultXSLOut, @NonNull Boolean includeScoringGroupData, @Nullable String basicAuthPassword, @Nullable String basicAuthUsername, @Nullable String description) {
        this.rest = rest;
        this.email = email;
        this.soap = soap;
        this.ftp = ftp;
        if(assessmentReportUrlType != null){
            this.assessmentReportUrlType = assessmentReportUrlType;
        }
        this.endpointURL = endpointURL;
        if(retryCount != null){
            this.retryCount = retryCount;
        }
        this.transformResult = transformResult;
        this.assessmentResultXSLOut = assessmentResultXSLOut;
        this.includeScoringGroupData = includeScoringGroupData;
        this.basicAuthPassword = basicAuthPassword;
        this.basicAuthUsername = basicAuthUsername;
        this.description = description;
    }
}
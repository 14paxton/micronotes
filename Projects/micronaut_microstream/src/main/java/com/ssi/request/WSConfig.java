package com.ssi.request;

import io.micronaut.core.annotation.*;
import io.micronaut.core.convert.format.*;
import io.micronaut.serde.annotation.*;
import lombok.*;

import javax.validation.*;
import java.util.*;

@ReflectiveAccess
@Serdeable
public @Getter
@Setter
@NoArgsConstructor class WSConfig {
    @Nullable
    @Valid
    private REST rest;

    @Nullable
    @Valid
    private FTP ftp;

    @Nullable
    @Valid
    private SOAP soap;

    @Nullable
    @MapFormat(transformation = MapFormat.MapTransformation.FLAT)
    private SimpleMap customAORMap;
    @Nullable
    private String countryCodeFormat;
    @Nullable
    private Boolean allowNoAuth = false;

    @Nullable
    private Boolean active = true;

    @Nullable
    private Boolean transformRequestIn;

    @Nullable
    private Boolean transformRequestResponse;

    @Nullable
    private Boolean transformStatusIn;

    @Nullable
    private Boolean transformStatusResponse;

    @Nullable
    private String assessmentOrderRequestXSLIn;

    @Nullable
    private String assessmentOrderRequestXSLOut;

    @Nullable
    private String assessmentStatusRequestXSLIn;

    @Nullable
    private String assessmentStatusRequestXSLOut;

    @Nullable
    private String description;

    @Creator
    public WSConfig(@Nullable REST rest, @Nullable FTP ftp, @Nullable SOAP soap, @Nullable SimpleMap customAORMap, @Nullable String countryCodeFormat, @Nullable Boolean allowNoAuth, @Nullable Boolean active, @Nullable Boolean transformRequestIn, @Nullable Boolean transformRequestResponse, @Nullable Boolean transformStatusIn, @Nullable Boolean transformStatusResponse, @Nullable String assessmentOrderRequestXSLIn, @Nullable String assessmentOrderRequestXSLOut, @Nullable String assessmentStatusRequestXSLIn, @Nullable String assessmentStatusRequestXSLOut, @Nullable String description) {
        this.rest = rest;
        this.ftp = ftp;
        this.soap = soap;
        this.customAORMap = customAORMap;
        this.countryCodeFormat = countryCodeFormat;
        this.allowNoAuth = allowNoAuth;
        this.active = active;
        this.transformRequestIn = transformRequestIn;
        this.transformRequestResponse = transformRequestResponse;
        this.transformStatusIn = transformStatusIn;
        this.transformStatusResponse = transformStatusResponse;
        this.assessmentOrderRequestXSLIn = assessmentOrderRequestXSLIn;
        this.assessmentOrderRequestXSLOut = assessmentOrderRequestXSLOut;
        this.assessmentStatusRequestXSLIn = assessmentStatusRequestXSLIn;
        this.assessmentStatusRequestXSLOut = assessmentStatusRequestXSLOut;
        this.description = description;
    }

    public WSConfig(WSConfigCommand wsConfigCommand) {
        this.rest = wsConfigCommand.getRest();
        this.ftp = wsConfigCommand.getFtp();
        this.soap = wsConfigCommand.getSoap();
        if (wsConfigCommand.getCustomAORMap() != null) {
            this.customAORMap = new SimpleMap(wsConfigCommand.getCustomAORMap());
        }
        if (wsConfigCommand.getCountryCodeFormat() != null) {
            this.countryCodeFormat = wsConfigCommand.getCountryCodeFormat().toString();
        }
        this.allowNoAuth = wsConfigCommand.getAllowNoAuth();
        this.active = wsConfigCommand.getActive();
        this.transformRequestIn = wsConfigCommand.getTransformRequestIn();
        this.transformRequestResponse = wsConfigCommand.getTransformRequestResponse();
        this.transformStatusIn = wsConfigCommand.getTransformStatusIn();
        this.transformStatusResponse = wsConfigCommand.getTransformStatusResponse();
        this.assessmentOrderRequestXSLIn = wsConfigCommand.getAssessmentOrderRequestXSLIn();
        this.assessmentOrderRequestXSLOut = wsConfigCommand.getAssessmentOrderRequestXSLOut();
        this.assessmentStatusRequestXSLIn = wsConfigCommand.getAssessmentStatusRequestXSLIn();
        this.assessmentStatusRequestXSLOut = wsConfigCommand.getAssessmentStatusRequestXSLOut();
        this.description = wsConfigCommand.getDescription();
    }

    @Nullable
    public Map<String, String> getCustomAORMap() {
        if (customAORMap != null) {
            return customAORMap.getAorMap();
        }

        return null;
    }

}
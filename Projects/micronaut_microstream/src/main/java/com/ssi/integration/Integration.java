package com.ssi.integration;

import com.ssi.request.*;
import com.ssi.result.*;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.*;
import io.micronaut.data.annotation.*;
import io.micronaut.serde.annotation.*;
import lombok.*;

import javax.validation.constraints.*;
import java.util.*;

@ReflectiveAccess
@Serdeable
public @Getter
@Setter
@AllArgsConstructor class Integration {
    @NotBlank
    private final String id;

    @NotBlank
    @Version
    private Long version = 1L;
    @NotBlank
    private String companyCode;
    @Nullable
    private String externalClientId;
    @Nullable
    private Long organization;
    @Nullable
    private String tbeCompanyCode;
    @Nullable
    private String tbexPassword;
    @NonNull
    private WSConfig wsconfig;
    @NonNull
    private ClientResult clientResult;
    @Nullable
    private ClientEntityMap clientEntityMap;

    @Creator
    public Integration(String id, String companyCode, @Nullable String externalClientId, @Nullable Long organization, @Nullable String tbeCompanyCode, @Nullable String tbexPassword, @NonNull WSConfig wsconfig, @NonNull ClientResult clientResult, @Nullable Map<String, LinkedList<Map<String, String>>>clientEntityMap) {
        this.id = id;
        this.companyCode = companyCode;
        this.externalClientId = externalClientId;
        this.organization = organization;
        this.tbeCompanyCode = tbeCompanyCode;
        this.tbexPassword = tbexPassword;
        this.wsconfig = wsconfig;
        this.clientResult = clientResult;
        if (clientEntityMap != null) {
            this.clientEntityMap = new ClientEntityMap(clientEntityMap);
        }
    }

    @Nullable
    public Map<String, LinkedList<Map<String, String>>> getClientEntityMap() {
        if (this.clientEntityMap != null) {
            return this.clientEntityMap.getEntityMap();
        }
        return null;
    }
}
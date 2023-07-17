package com.ssi.integration;

import com.ssi.request.*;
import com.ssi.result.*;
import io.micronaut.core.annotation.*;
import io.micronaut.serde.annotation.*;
import lombok.*;

import javax.validation.*;
import javax.validation.constraints.*;
import java.util.*;
import java.util.stream.*;

@Serdeable
public @Getter
@AllArgsConstructor class IntegrationCommand {
    @Nullable
    private final String id;

    @NotBlank
    private final String companyCode;

    @Nullable
    private final String externalClientId;

    @Nullable
    private final Long organization;

    @Nullable
    private final String tbeCompanyCode;

    @Nullable
    private final String tbexPassword;

    @Valid
    private final WSConfigCommand wsConfig;

    @Valid
    private final ClientResultCommand clientResultConfig;

    @Nullable
    private final Map<String, LinkedList<ClientEntityDetails>> clientEntityMap;

    public IntegrationCommand(String companyCode, @Nullable String externalClientId, @Nullable Long organization, @Nullable String tbeCompanyCode, @Nullable String tbexPassword, WSConfigCommand wsConfig, ClientResultCommand clientResultConfig, @Nullable Map<String, LinkedList<ClientEntityDetails>> clientEntityMap) {
        this(null, companyCode,  externalClientId,  organization,  tbeCompanyCode,  tbexPassword, wsConfig, clientResultConfig, clientEntityMap);
    }

    @Nullable
    public Map<String, LinkedList<Map<String, String>>> getClientEntityMap() {
        if (this.clientEntityMap != null) {
            return this.clientEntityMap.entrySet()
                    .stream()
                    .map(entry -> Map.entry(entry.getKey(), entry.getValue()
                            .stream()
                            .map(ClientEntityDetails::toMap)
                            .collect(Collectors.toCollection(LinkedList::new))))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }

        return null;
    }
}
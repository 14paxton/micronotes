package com.ssi.integration;

import com.ssi.request.*;
import io.micronaut.core.annotation.*;
import io.micronaut.serde.annotation.*;
import lombok.*;

import java.util.*;
import java.util.concurrent.*;

@ReflectiveAccess
@Serdeable
public @Getter
@Setter
@AllArgsConstructor class ClientEntityDetails {

    @Nullable
    private String externalCode;
    @Nullable
    private String description;
    @Nullable
    private String value;

    protected Map<String, String> toMap() {
        Map<String, String> newMap = new ConcurrentHashMap<>();
        if (this.externalCode != null) {
            newMap.put("externalCode", this.externalCode);
        }

        if (this.description != null) {
            newMap.put("description", this.description);
        }

        if (this.value != null) {
            newMap.put("value", this.value);
        }

        return newMap;
    }
}
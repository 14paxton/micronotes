package com.ssi.integration;

import com.ssi.request.*;
import io.micronaut.core.annotation.*;
import io.micronaut.serde.annotation.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

@ReflectiveAccess
@Serdeable
public class ClientEntityMap extends ConcurrentHashMap<String, LinkedList<Map<String, String>>> {
    @NonNull
    private final Map<String, LinkedList<Map<String, String>>> entityMap = new ConcurrentHashMap<>();

    public ClientEntityMap(@NonNull Map<? extends String, ? extends LinkedList<Map<String, String>>> m) {
        super(m);
        this.entityMap.putAll(m);
    }

    @NonNull
    public Map<String, LinkedList<Map<String, String>>> getEntityMap() {
        return entityMap;
    }
}
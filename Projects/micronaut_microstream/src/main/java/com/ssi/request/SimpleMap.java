package com.ssi.request;

import io.micronaut.core.annotation.*;
import io.micronaut.serde.annotation.*;

import java.util.*;
import java.util.concurrent.*;

@ReflectiveAccess
@Serdeable
public class SimpleMap extends HashMap<String, String>{
    @NonNull
    private final Map<String, String> aorMap = new ConcurrentHashMap<>();

    public SimpleMap(Map<? extends String, ? extends String> m) {
        super(m);
        aorMap.putAll(m);
    }

    @NonNull
    public Map<String, String> getAorMap() {
        return aorMap;
    }
}
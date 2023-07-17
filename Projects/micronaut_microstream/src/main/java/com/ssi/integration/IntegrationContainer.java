package com.ssi.integration;

import io.micronaut.core.annotation.*;

import java.util.*;
import java.util.concurrent.*;

@ReflectiveAccess
public class IntegrationContainer {
    @NonNull
    private final Map<String, Integration> integrations = new ConcurrentHashMap<>();

    @NonNull
    public Map<String, Integration> getIntegrations() {
        return integrations;
    }
}
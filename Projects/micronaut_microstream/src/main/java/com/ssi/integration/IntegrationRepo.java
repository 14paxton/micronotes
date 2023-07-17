package com.ssi.integration;

import io.micronaut.core.annotation.*;

import javax.validation.*;
import javax.validation.constraints.*;
import java.util.*;

public interface IntegrationRepo {
    @NonNull
    Collection<Integration> list();

    @NonNull
    Integration create(@NonNull @NotNull @Valid IntegrationCommand integrationCommand)
            throws IntegrationDuplicateException;

    @Nullable
    Integration update(@NonNull @NotNull @Valid IntegrationCommand integrationCommand)
            throws IntegrationDuplicateException;

    @Nullable
    Integration find(@NonNull @NotBlank String companyCode);

    void delete(@NonNull @NotNull @Valid IntegrationCommand integrationCommand);

}
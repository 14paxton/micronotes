package com.ssi.integration;

import com.ssi.request.*;
import com.ssi.result.*;
import io.micronaut.core.annotation.*;
import io.micronaut.microstream.*;
import io.micronaut.microstream.annotations.*;
import jakarta.inject.*;
import one.microstream.storage.types.*;

import javax.validation.*;
import javax.validation.constraints.*;
import java.util.*;

@Singleton
public class IntegrationRepositoryImpl implements IntegrationRepo {

    private final RootProvider<IntegrationContainer> rootProvider;
    private final StorageManager storageManager;


    IntegrationRepositoryImpl(RootProvider<IntegrationContainer> rootProvider, StorageManager storageManager) {
        this.rootProvider = rootProvider;
        this.storageManager = storageManager;
    }

    @Override
    @NonNull
    public Collection<Integration> list() {
        return rootProvider.root().getIntegrations().values();
    }

    @Override
    @NonNull
    public Integration create(@NonNull @NotNull @Valid IntegrationCommand integrationCommand) {
        Map<String, Integration> integrationList = rootProvider.root().getIntegrations();
        if (integrationList.containsKey(integrationCommand.getCompanyCode())) {
            throw new IntegrationDuplicateException(integrationCommand.getCompanyCode());
        }
        return performCreate(integrationList, integrationCommand);
    }

    @StoreParams("integrationList")
    protected Integration performCreate(Map<String, Integration> integrationList, IntegrationCommand integrationCommand) {
        String id = UUID.randomUUID().toString();
        WSConfig wsConfig = new WSConfig(integrationCommand.getWsConfig());
        ClientResult clientResult = new ClientResult(integrationCommand.getClientResultConfig());
        Integration newIntegration = new Integration(id, integrationCommand.getCompanyCode(), integrationCommand.getExternalClientId(), integrationCommand.getOrganization(), integrationCommand.getTbeCompanyCode(), integrationCommand.getTbexPassword(), wsConfig, clientResult, integrationCommand.getClientEntityMap());
        return saveIntegrationToList(integrationCommand.getCompanyCode(), integrationList, newIntegration);
    }

    @StoreReturn
    protected Integration saveIntegrationToList(String key, Map<String, Integration> integrationList, Integration newIntegration) {
        integrationList.put(key, newIntegration);
        return newIntegration;
    }

    @Override
    @Nullable
    public Integration update(@NonNull @NotNull @Valid IntegrationCommand integrationCommand) {
        Integration foundIntegration = fetchIntegration(integrationCommand);
        if (foundIntegration != null) {
            return performUpdate(foundIntegration, integrationCommand);
        }

        return null;
    }

    @Nullable
    Integration fetchIntegration(@NonNull IntegrationCommand integrationCommand) {
        Map<String, Integration> integrationList = rootProvider.root().getIntegrations();
        Integration foundIntegration = integrationList.get(integrationCommand.getCompanyCode());

        if (foundIntegration != null) {
            return foundIntegration;
        } else if (integrationCommand.getId() != null) {
            Optional<Integration> result = integrationList.values()
                    .stream()
                    .filter(intVal -> Objects.equals(intVal.getId(), integrationCommand.getId()))
                    .findFirst();

            if (result.isPresent()) {
                return result.get();
            }
        }

        return null;
    }

    @StoreParams("foundIntegration")
    protected Integration performUpdate(@NonNull Integration foundIntegration, @NonNull IntegrationCommand integrationCommand) {
        foundIntegration.setExternalClientId(integrationCommand.getExternalClientId());
        foundIntegration.setOrganization(integrationCommand.getOrganization());
        foundIntegration.setTbeCompanyCode(integrationCommand.getTbeCompanyCode());
        foundIntegration.setTbexPassword(integrationCommand.getTbexPassword());
        foundIntegration.setVersion(foundIntegration.getVersion() + 1);
        if (integrationCommand.getClientEntityMap() != null) {
            foundIntegration.setClientEntityMap(new ClientEntityMap(integrationCommand.getClientEntityMap()));
        }
        updateWSConfig(foundIntegration.getWsconfig(), integrationCommand.getWsConfig());
        updateClientResultConfig(foundIntegration.getClientResult(), integrationCommand.getClientResultConfig());

        if (!foundIntegration.getCompanyCode().equals(integrationCommand.getCompanyCode())) {
            return modifyCompanyCode(foundIntegration, integrationCommand);
        }

        return foundIntegration;
    }

    @StoreParams("foundWSConfig")
    protected void updateWSConfig(@NonNull WSConfig foundWSConfig, @NonNull WSConfigCommand wsConfigCMD) {
        foundWSConfig.setRest(wsConfigCMD.getRest());
        foundWSConfig.setFtp(wsConfigCMD.getFtp());
        foundWSConfig.setSoap(wsConfigCMD.getSoap());
        if (wsConfigCMD.getCustomAORMap() != null) {
            foundWSConfig.setCustomAORMap(new SimpleMap(wsConfigCMD.getCustomAORMap()));
        }
        if (wsConfigCMD.getCountryCodeFormat() != null) {
            foundWSConfig.setCountryCodeFormat(wsConfigCMD.getCountryCodeFormat().toString());
        }
        foundWSConfig.setAllowNoAuth(wsConfigCMD.getAllowNoAuth());
        foundWSConfig.setActive(wsConfigCMD.getActive());
        foundWSConfig.setTransformRequestIn(wsConfigCMD.getTransformRequestIn());
        foundWSConfig.setTransformRequestResponse(wsConfigCMD.getTransformRequestResponse());
        foundWSConfig.setTransformStatusIn(wsConfigCMD.getTransformStatusIn());
        foundWSConfig.setTransformStatusResponse(wsConfigCMD.getTransformStatusResponse());
        foundWSConfig.setAssessmentOrderRequestXSLIn(wsConfigCMD.getAssessmentOrderRequestXSLIn());
        foundWSConfig.setAssessmentOrderRequestXSLOut(wsConfigCMD.getAssessmentOrderRequestXSLOut());
        foundWSConfig.setAssessmentStatusRequestXSLIn(wsConfigCMD.getAssessmentStatusRequestXSLIn());
        foundWSConfig.setAssessmentStatusRequestXSLOut(wsConfigCMD.getAssessmentStatusRequestXSLOut());
        foundWSConfig.setDescription(wsConfigCMD.getDescription());
    }

    @StoreParams("foundResultConfig")
    protected void updateClientResultConfig(@NonNull ClientResult foundResultConfig, @NonNull ClientResultCommand resultCMD) {
        foundResultConfig.setRest(resultCMD.getRest());
        foundResultConfig.setEmail(resultCMD.getEmail());
        foundResultConfig.setSoap(resultCMD.getSoap());
        foundResultConfig.setFtp(resultCMD.getFtp());
        foundResultConfig.setAssessmentReportUrlType(String.valueOf(resultCMD.getAssessmentReportUrlType()));
        foundResultConfig.setEndpointURL(resultCMD.getEndpointURL());
        foundResultConfig.setTransformResult(resultCMD.getTransformResult());
        foundResultConfig.setAssessmentResultXSLOut(resultCMD.getAssessmentResultXSLOut());
        foundResultConfig.setIncludeScoringGroupData(resultCMD.getIncludeScoringGroupData());
        foundResultConfig.setBasicAuthPassword(resultCMD.getBasicAuthPassword());
        foundResultConfig.setBasicAuthUsername(resultCMD.getBasicAuthUsername());
        foundResultConfig.setDescription(resultCMD.getDescription());
    }


    @StoreReturn
    protected Integration modifyCompanyCode(@NonNull Integration foundIntegration, @NonNull IntegrationCommand integrationCommand) {
        String oldCompanyCode = foundIntegration.getCompanyCode();
        String newCode = integrationCommand.getCompanyCode();

        Map<String, Integration> integrationList = rootProvider.root().getIntegrations();

        if (integrationList.containsKey(newCode)) {
            throw new IntegrationDuplicateException(newCode);
        }

        Map<String, Integration> modifiedList = performDelete(oldCompanyCode);

        if (modifiedList != null) {
            foundIntegration.setCompanyCode(newCode);
            return saveIntegrationToList(newCode, modifiedList, foundIntegration);
        }

        return foundIntegration;
    }

    @Override
    @Nullable
    public Integration find(@NonNull String companyCode) {
        return rootProvider.root().getIntegrations().get(companyCode);
    }

    @Override
    public void delete(@NonNull @NotNull @Valid IntegrationCommand integrationCommand) {
        performDelete(integrationCommand.getCompanyCode());
    }

    @StoreReturn
    protected Map<String, Integration> performDelete(String companyCode) {
        if (rootProvider.root().getIntegrations().remove(companyCode) != null) {
            return rootProvider.root().getIntegrations();
        }

        return null;
    }
}
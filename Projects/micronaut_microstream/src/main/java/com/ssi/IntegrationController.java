package com.ssi;

import com.ssi.integration.*;
import io.micronaut.core.annotation.*;
import io.micronaut.core.version.annotation.*;
import io.micronaut.http.*;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.annotation.*;
import io.micronaut.validation.*;
import javax.validation.*;
import javax.validation.constraints.*;
import java.util.*;
import static io.micronaut.scheduling.TaskExecutors.*;

@Version("1")
@Validated
@Controller("/ssi")
class IntegrationController {
    private final IntegrationRepo integrationRepo;

    IntegrationController(IntegrationRepo integrationRepo) {
        this.integrationRepo = integrationRepo;
    }

    @Get
    Collection<Integration> list() {
        return integrationRepo.list();
    }

    @ExecuteOn(IO)
    @Post
    @Status(HttpStatus.CREATED)
    Integration create(@Body @Valid IntegrationCommand integration) {

        return integrationRepo.create(integration);
    }

    @Put
    Integration update(@NonNull @NotNull @Valid @Body IntegrationCommand integration) {
        return integrationRepo.update(integration);
    }

    @Get("/{companyCode}")
    Integration find(@PathVariable String companyCode) {
        return integrationRepo.find(companyCode);
    }

    @ExecuteOn(IO)
    @Delete
    @Status(HttpStatus.NO_CONTENT)
    void delete(@NonNull @Valid @Body IntegrationCommand integration) {
        integrationRepo.delete(integration);
    }
}
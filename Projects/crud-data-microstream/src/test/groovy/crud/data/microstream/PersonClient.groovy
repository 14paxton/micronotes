package crud.data.microstream

import crud.data.Person
import io.micronaut.core.annotation.NonNull
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.*
import io.micronaut.http.client.annotation.Client

import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Client("/microstream/person")
interface PersonClient {
    @Get
    Iterable<Person> list()

    @Get("/{name}")
    Optional<Person> find(@NonNull @NotBlank @PathVariable String name)

    @Post
    HttpResponse<Person> create(@NonNull @NotNull @Valid @Body PersonCommand person)

    @Put
    Optional<Person> update(@NonNull @NotNull @Valid @Body PersonCommand person)

    @NonNull
    @Delete
    HttpStatus delete(@NonNull @Valid @Body PersonCommand person)
}
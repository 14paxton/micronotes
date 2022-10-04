package crud.data.microstream;

import crud.data.*;
import io.micronaut.core.annotation.*;
import io.micronaut.http.*;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.annotation.*;

import javax.validation.*;
import javax.validation.constraints.*;
import java.util.*;

import static io.micronaut.scheduling.TaskExecutors.*;

@Controller("/microstream/person")
class PersonMicroStreamController {
    private final PersonMicroStreamRepo personRepo;

    PersonMicroStreamController(PersonMicroStreamRepo personRepo) {
        this.personRepo = personRepo;
    }

    @Get
    Collection<Person> list() {
        return personRepo.list();
    }

    @ExecuteOn(IO)
    @Post
    @Status(HttpStatus.CREATED)
    Person create(@NonNull @NotNull @Valid @Body PersonCommand person) {
        return personRepo.create(person);
    }

    @Put
    Person update(@NonNull @NotNull @Valid @Body PersonCommand person) {
        return personRepo.update(person);
    }

    @Get("/{name}")
    Person find(@PathVariable String name) {
        return personRepo.find(name);
    }

    @ExecuteOn(IO)
    @Delete
    @Status(HttpStatus.NO_CONTENT)
    void delete(@NonNull @Valid @Body PersonCommand person) {
        personRepo.delete(person);
    }
}
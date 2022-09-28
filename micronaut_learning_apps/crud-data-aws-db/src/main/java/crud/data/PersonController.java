package crud.data;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;

import java.util.*;
import java.util.stream.*;

@Controller("/person")
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Post("/")
    public HttpResponse<Person> create(@Body PersonDTO pd) {
        return HttpResponse.created(personRepository.save(pd.getFirstName(), pd.getLastName()));
    }

    @Get("/{id}")
    public Person get(long id) {
        return personRepository.findById(id).orElse(null);
    }

    @Put("/{id}")
    public Person update(@Body PersonDTO pd, long id) {
        personRepository.update(id, pd.getFirstName(), pd.getLastName());
        return personRepository.findById(id).orElse(null);
    }

    @Get("/")
    public Iterable<Person> list() {
        return personRepository.findAll();
    }

    @Get("/record")
    public List<PersonRecord> listRecords() {

        return personRepository.find()
                .stream()
                .map(person -> new PersonRecord(person.getLastName(), person.getFirstName()))
                .collect(Collectors.toList());
    }
}

@Introspected
class PersonDTO {
     private final String firstName;
     private final String lastName;

    PersonDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
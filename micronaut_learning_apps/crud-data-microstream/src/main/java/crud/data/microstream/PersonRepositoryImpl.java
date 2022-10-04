package crud.data.microstream;

import crud.data.*;
import io.micronaut.core.annotation.*;
import io.micronaut.microstream.*;
import io.micronaut.microstream.annotations.*;
import jakarta.inject.*;

import javax.validation.*;
import javax.validation.constraints.*;
import java.util.*;

@Singleton
public class PersonRepositoryImpl implements PersonMicroStreamRepo {

    private final RootProvider<PersonContainer> rootProvider;

    PersonRepositoryImpl(RootProvider<PersonContainer> rootProvider) {
        this.rootProvider = rootProvider;
    }

    @Override
    @NonNull
    public Collection<Person> list() {
        return rootProvider.root().getPeople().values();
    }

    @Override
    @NonNull
    public Person create(@NonNull @NotNull @Valid PersonCommand person) throws PersonDuplicateException {
        Map<String, Person> people = rootProvider.root().getPeople();
        if (people.containsKey(person.getFirstName())) {
            throw new PersonDuplicateException(person.getFirstName());
        }
        return performCreate(people, person);
    }

    @StoreParams("people")
    protected Person performCreate(Map<String, Person> people, PersonCommand cmd) {
        Person newPerson = new Person(cmd.getFirstName(), cmd.getLastName());
        people.put(cmd.getFirstName(), newPerson);
        return newPerson;
    }

    @Nullable
    public Person update(@NonNull @NotNull @Valid PersonCommand cmd) {
        Map<String, Person> people = rootProvider.root().getPeople();
        Person foundPerson = people.get(cmd.getFirstName());
        if (foundPerson != null) {
            return performUpdate(foundPerson, cmd);
        }
        return null;
    }

    @StoreReturn
    protected Person performUpdate(@NonNull Person foundPerson, @NonNull PersonCommand personCommand) {
        foundPerson.setFirstName(personCommand.getFirstName());
        foundPerson.setLastName(personCommand.getLastName());
        return foundPerson;
    }

    @Override
    @Nullable
    public Person find(@NonNull @NotBlank String name) {
        return rootProvider.root().getPeople().get(name);
    }

    @Override
    public void delete(@NonNull @NotNull @Valid PersonCommand cmd) {
        performDelete(cmd);
    }

    @StoreReturn
    protected Map<String, Person> performDelete(PersonCommand cmd) {
        if (rootProvider.root().getPeople().remove(cmd.getFirstName()) != null) {
            return rootProvider.root().getPeople();
        }

        return null;
    }
}
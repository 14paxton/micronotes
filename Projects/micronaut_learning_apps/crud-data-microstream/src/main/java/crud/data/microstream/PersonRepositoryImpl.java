package crud.data.microstream;

import crud.data.*;
import io.micronaut.core.annotation.*;
import io.micronaut.microstream.*;
import io.micronaut.microstream.annotations.*;
import jakarta.inject.*;
import one.microstream.storage.types.*;

import javax.validation.*;
import javax.validation.constraints.*;
import java.util.*;

@Singleton
public class PersonRepositoryImpl implements PersonMicroStreamRepo {

    private final RootProvider<PersonContainer> rootProvider;
    private final StorageManager storageManager;


    PersonRepositoryImpl(RootProvider<PersonContainer> rootProvider, StorageManager storageManager) {

        this.rootProvider = rootProvider;
        this.storageManager = storageManager;
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
    protected Person performCreate(Map<String, Person> people, PersonCommand person) {
        String id = UUID.randomUUID().toString();
        Person newPerson = new Person(id, person.getFirstName(), person.getLastName());
        people.put(person.getFirstName(), newPerson);
        return newPerson;
    }

    @Nullable
    public Person update(@NonNull @NotNull @Valid PersonCommand person) {
        Map<String, Person> people = rootProvider.root().getPeople();
        Person foundPerson = people.get(person.getFirstName());
        if (foundPerson != null) {
            return performUpdate(foundPerson, person);
        }
        return null;
    }

    @StoreReturn
    protected Person performUpdate(@NonNull Person foundPerson, @NonNull PersonCommand personCommand) {
        foundPerson.setFirstName(personCommand.getFirstName());
        foundPerson.setLastName(personCommand.getLastName());
        foundPerson.setVersion(foundPerson.getVersion() + 1);
        return foundPerson;
    }

    @Override
    @Nullable
    public Person find(@NonNull @NotBlank String name) {
        return rootProvider.root().getPeople().get(name);
    }

    @Override
    public void delete(@NonNull @NotNull @Valid PersonCommand person) {
        performDelete(person);
    }

    @StoreReturn
    protected Map<String, Person> performDelete(PersonCommand cmd) {
        if (rootProvider.root().getPeople().remove(cmd.getFirstName()) != null) {
            return rootProvider.root().getPeople();
        }

        return null;
    }
}
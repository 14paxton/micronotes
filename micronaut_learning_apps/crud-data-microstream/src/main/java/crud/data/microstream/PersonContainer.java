package crud.data.microstream;

import crud.data.*;
import io.micronaut.core.annotation.*;

import java.util.*;
import java.util.concurrent.*;

@Introspected
public class PersonContainer {
    @NonNull
    private final Map<String, Person> people = new ConcurrentHashMap<>();

    @NonNull
    public Map<String, Person> getPeople() {
        return people;
    }
}
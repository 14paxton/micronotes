package crud.data.microstream;

import crud.data.*;
import io.micronaut.core.annotation.*;

import javax.validation.*;
import javax.validation.constraints.*;
import java.util.*;

public interface PersonMicroStreamRepo {
    @NonNull
    Collection<Person> list();

    @NonNull
    Person create(@NonNull @NotNull @Valid PersonCommand person)
            throws PersonDuplicateException;

    @Nullable
    Person update(@NonNull @NotNull @Valid PersonCommand person);

    @Nullable
    Person find(@NonNull @NotBlank String name);

    void delete(@NonNull @NotNull @Valid PersonCommand person);

}
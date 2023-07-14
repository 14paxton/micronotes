package crud.data.microstream;

import io.micronaut.core.annotation.*;
import io.micronaut.serde.annotation.*;

import javax.validation.constraints.*;

@Serdeable
public class PersonCommand {

    @NonNull
    @NotBlank
    private final String firstName;

    @Nullable
    private final String lastName;

    public PersonCommand(@NonNull String firstName) {
        this(firstName, null);
    }

    @Creator
    public PersonCommand(@NonNull String firstName,
                         @Nullable String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    @Nullable
    public String getLastName() {
        return lastName;
    }

}
package crud.data.microstream;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;

import javax.validation.constraints.NotBlank;

@Introspected
public class PersonCommand {

    @NonNull
    @NotBlank
    private final String firstName;

    @Nullable // <3>
    private final String lastName;

    public PersonCommand(@NonNull String name) {
        this(name, null);
    }

    public PersonCommand(@NonNull String firstName,
                         @Nullable String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @NonNull
    public String getName() {
        return firstName + ' ' + lastName;
    }

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

}
package crud.data;

import io.micronaut.core.annotation.*;
import io.micronaut.serde.annotation.*;

import javax.validation.constraints.*;

@Serdeable
public class Person {

    @NonNull
    @NotBlank
    private final String id;

    @NonNull
    @NotBlank
    private Long version = 1L;

    @NonNull
    @NotBlank
    private String firstName;
    @Nullable
    private String lastName;

    public Person(@NonNull String id, @NonNull String firstName, @Nullable String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @NonNull
    public String getId() {
        return id;
    }


    @NonNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NonNull String firstName) {
        this.firstName = firstName;
    }

    @Nullable
    public String getLastName() {
        return lastName;
    }

    public void setLastName(@Nullable String lastName) {
        this.lastName = lastName;
    }

    @NonNull
    public Long getVersion() {
        return version;
    }

    public void setVersion(@NonNull Long version) {
        this.version = version;
    }
}
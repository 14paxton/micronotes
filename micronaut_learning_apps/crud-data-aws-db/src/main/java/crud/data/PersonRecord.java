package crud.data;

import io.micronaut.core.annotation.*;

import javax.validation.constraints.*;
import java.math.*;

@Introspected
@ReflectiveAccess
public record PersonRecord(@NonNull @NotBlank String lastName,
                          @NonNull @NotBlank String firstName) {

}
package crud.data.microstream;

import io.micronaut.http.*;
import io.micronaut.http.annotation.*;
import io.micronaut.http.server.exceptions.*;
import io.micronaut.http.server.exceptions.response.*;
import jakarta.inject.*;

@Produces
@Singleton
public class PersonDuplicateExceptionHandler implements ExceptionHandler<PersonDuplicateException, HttpResponse<?>> {

    private final ErrorResponseProcessor<?> errorResponseProcessor;

    public PersonDuplicateExceptionHandler(ErrorResponseProcessor<?> errorResponseProcessor) {
        this.errorResponseProcessor = errorResponseProcessor;
    }

    @Override
    public HttpResponse<?> handle(HttpRequest request, PersonDuplicateException exception) {
        ErrorContext errorContext = ErrorContext.builder(request)
                .cause(exception)
                .errorMessage(exception.getMessage())
                .build();
        return errorResponseProcessor.processResponse(errorContext, HttpResponse.unprocessableEntity());
    }
}
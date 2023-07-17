package crud.data.microstream;

public class PersonDuplicateException extends RuntimeException {
    public PersonDuplicateException(String name) {
        super("Person " + name + " exists");
    }
}
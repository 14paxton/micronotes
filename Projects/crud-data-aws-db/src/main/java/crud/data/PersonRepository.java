package crud.data;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

@JdbcRepository(dialect = Dialect.MYSQL)
public interface PersonRepository extends CrudRepository<Person, Long> {
    void update(@Id long id, String firstName, String lastName);
    Person save(String firstName, String lastName);

    List<Person> find();
}
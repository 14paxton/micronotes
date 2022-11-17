package crud.data.microstream

import crud.data.Person
import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.runtime.server.EmbeddedServer
import java.util.stream.Collectors
import java.util.stream.Stream
import java.util.stream.StreamSupport

class PersonMicroStreamControllerSpec extends MicroStreamBaseSpec {
    void "test interaction with the Controller"() {
        given:
        PersonCommand doug = new PersonCommand("doug", "rouzek")
        String lucyName = "lucy"
        String lucyLastName = "Williams"
        Map<String, Object> properties = new HashMap<>(getProperties())
        EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer, properties)
        PersonClient personClient = embeddedServer.getApplicationContext().getBean(PersonClient)

        when:
        HttpResponse<Person> response = personClient.create(new PersonCommand(lucyName))
        then:
        HttpStatus.CREATED == response.status
        response.body.isPresent()

        when:
        Person banana = response.body.get()
        List<Person> fruitList = peopleList(personClient)

        then:
        1 == fruitList.size()
        banana.firstName == fruitList.get(0).firstName

        when:
        Optional<Person> lucyOptional = personClient.update(doug)

        then:
        !lucyOptional.isPresent()

        when:
        response = personClient.create(doug)

        then:
        HttpStatus.CREATED == response.status
        peopleStream(personClient)
                .anyMatch(f -> "rouzek" == f.lastName)

        when:
        lucyOptional = personClient.update(new PersonCommand(lucyName, lucyLastName))
        then:
        lucyOptional.isPresent()
        Stream.of(doug.lastName , lucyLastName).collect(Collectors.toSet()) ==
        peopleStream(personClient)
                .map(Person::getLastName)
                .collect(Collectors.toSet())
        when:
        embeddedServer.close()
        embeddedServer = ApplicationContext.run(EmbeddedServer, properties)

        then:
        2 == numberOfPeople(personClient)
        when:
        personClient.delete(doug)
        personClient.delete(new PersonCommand(lucyName, lucyLastName))
        embeddedServer.close()
        embeddedServer = ApplicationContext.run(EmbeddedServer, properties)
        personClient = embeddedServer.getApplicationContext().getBean(PersonClient)

        then:
        0 == numberOfPeople(personClient)

        cleanup:
        embeddedServer.close()
    }

    private int numberOfPeople(PersonClient personClient) {
        return peopleList(personClient).size()
    }

    private List<Person> peopleList(PersonClient personClient) {
        return peopleStream(personClient)
                .collect(Collectors.toList())
    }

    private Stream<Person> peopleStream(PersonClient personClient) {
        Iterable<Person> people = personClient.list()
        return StreamSupport.stream(people.spliterator(), false)
    }
}
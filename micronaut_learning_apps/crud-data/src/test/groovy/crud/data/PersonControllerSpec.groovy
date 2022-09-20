package crud.data

import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import spock.lang.Specification
import spock.lang.Stepwise

import jakarta.inject.Inject

@MicronautTest
@Stepwise
class PersonControllerSpec extends Specification {

    @Inject
    PersonClient client

    void 'test retrieving person instances when none exist'() {
        expect:
        client.list().size() == 0
    }

    void 'test creating people'() {
        when:
        Person person = client.create 'Johnny', 'Winter'

        then:
        person.firstName == 'Johnny'
        person.lastName == 'Winter'

        when:
        person = client.create 'Randy', 'Rhoads'

        then:
        person.firstName == 'Randy'
        person.lastName == 'Rhoads'
    }

    void 'test retrieving person instances'() {
        when:
        List<Person> people = client.list()

        then:
        people.size() == 2

        and:
        people[0].firstName == 'Johnny'
        people[0].lastName == 'Winter'

        and:
        people[1].firstName == 'Randy'
        people[1].lastName == 'Rhoads'
    }
}

@Client('/person')
interface PersonClient {
    @Post('/')
    Person create(String firstName, String lastName)

    @Get('/')
    List<Person> list()
}

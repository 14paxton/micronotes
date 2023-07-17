package crud.data.microstream

import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Specification

import javax.validation.ConstraintViolationException

@MicronautTest(startApplication = false)
class PersonMicroStreamRepoSpec extends Specification {

    @Inject
    PersonMicroStreamRepo personRepo

    void "methods validate parameters"() {
        when:
        personRepo.create(new PersonCommand(""))

        then:
        thrown(ConstraintViolationException)
    }
}
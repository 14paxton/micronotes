package crud.data.microstream

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Specification

@MicronautTest
class PersonValidationSpec extends Specification {

    @Inject
    @Client("/")
    HttpClient httpClient

    void "fruit validation works as expected"() {
        when:
        httpClient.toBlocking().exchange(HttpRequest.POST("/microstream/person", new PersonCommand("", "")))

        then:
        def e = thrown(HttpClientResponseException.class)
        e.status == HttpStatus.BAD_REQUEST
    }
}
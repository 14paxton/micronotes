package crud.data.microstream


import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject

@MicronautTest
class PersonControllerSpec extends MicroStreamBaseSpec {

    @Inject
    @Client("/")
    HttpClient httpClient

    void "duplicated fruit returns 422"() {
        when:
        PersonCommand banana = new PersonCommand("Banana")
        HttpRequest<?> request = HttpRequest.POST("/microstream/person", banana)
        HttpResponse<?> response = httpClient.toBlocking().exchange(request)

        then:
        HttpStatus.CREATED == response.status()

        when:
        httpClient.toBlocking().exchange(request)

        then:
        HttpClientResponseException e = thrown()
        HttpStatus.UNPROCESSABLE_ENTITY == e.status

        when:
        HttpRequest<?> deleteRequest = HttpRequest.DELETE("/microstream/person", banana)
        HttpResponse<?> deleteResponse = httpClient.toBlocking().exchange(deleteRequest)

        then:
        HttpStatus.NO_CONTENT == deleteResponse.status()
    }

}
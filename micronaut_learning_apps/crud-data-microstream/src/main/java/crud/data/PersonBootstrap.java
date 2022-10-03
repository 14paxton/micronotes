package crud.data;

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import jakarta.inject.Singleton;

import java.util.*;

@Singleton
@Requires(notEnv = Environment.TEST)
public class PersonBootstrap implements ApplicationEventListener<ServerStartupEvent> {

    private PersonRepository personService;

    PersonBootstrap(PersonRepository personService) {
        this.personService = personService;
    }

    @Override
    public void onApplicationEvent(ServerStartupEvent event) {

        if( ((Collection<Person>) personService.findAll()).size() < 3){
            personService.save("Geddy", "Lee");
            personService.save("Neil", "Peart");
            personService.save("Alex", "Lifeson");
        }

    }
}
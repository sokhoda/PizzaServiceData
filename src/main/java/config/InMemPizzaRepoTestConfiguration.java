package config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import repository.InMemPizzaRepo;
import repository.PizzaRepository;

//@Profile("withoutAppRunnerTests")
@Configuration
public class InMemPizzaRepoTestConfiguration {

    @Bean (name = "inMemPizzaRepo")
    @Primary
    public PizzaRepository inMemPizzaRepo(){
        return Mockito.mock(InMemPizzaRepo.class);
    }
}

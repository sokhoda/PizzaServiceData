package config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import repository.InMemOrderRepo;
import repository.InMemPizzaRepo;
import repository.OrderRepository;
import repository.PizzaRepository;

//@Profile("withoutAppRunnerTests")
@Configuration
public class InMemOrderRepoTestConfiguration {

    @Bean (name = "inMemOrderRepo")
    @Primary
    public OrderRepository inMemOrderRepo(){
        return Mockito.mock(InMemOrderRepo.class);
    }
}

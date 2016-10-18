package config;

import domain.Pizza;
import domain.PizzaType;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import repository.InMemPizzaRepo;
import repository.PizzaRepository;

import javax.annotation.PostConstruct;

import java.util.LinkedList;

import static org.mockito.Mockito.spy;

//@Profile(value = "withoutAppRunnerTests")
@Configuration
public class InMemPizzaRepoTestConfiguration {
    private  PizzaRepository spyPizzaRepo;

    @Bean (name = "inMemPizzaRepo")
    @Primary
    public PizzaRepository inMemPizzaRepo(){
        return spy(new InMemPizzaRepo());
    }

    @PostConstruct
    public void init() {
//        spyPizzaRepo.add(new Pizza(1L, "Tomato", 90., PizzaType.VEGETERIAN));
//        spyPizzaRepo.add(new Pizza(2L, "Chicken", 120., PizzaType.MEAT));
//        spyPizzaRepo.add(new Pizza(3L, "Fish", 220., PizzaType.SEA));
    }
}

package repository;

import domain.Pizza;
import domain.PizzaType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pizzaservice.PizzaService;
import pizzaservice.SimplePizzaService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;

public class SimplePizzaServiceTest {
    @Mock private PizzaRepository inMemPizzaRepo;
    private PizzaService pizzaService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        pizzaService = new SimplePizzaService(inMemPizzaRepo);
    }

    @Test
    public void testFind() throws Exception {
        Pizza expectedPizza = new Pizza(1L, "MyFavourite", 90., PizzaType.VEGETERIAN);
//        GIVEN
        given(inMemPizzaRepo.find(1L)).willReturn(expectedPizza);

//        WHEN
        Pizza actualPizza = pizzaService.find(1L);
//        THEN
        assertThat(actualPizza, is(expectedPizza));

    }
}

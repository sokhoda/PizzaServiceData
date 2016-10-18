package repository;

import config.InMemPizzaRepoTestConfiguration;
import domain.Pizza;
import domain.PizzaType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pizzaservice.PizzaService;
import pizzaservice.SimplePizzaService;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//@ActiveProfiles("withoutAppRunnerTests")
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(SimpleApplication.class)
public class SimplePizzaServiceTest {

//    @Autowired
//    private PizzaRepository inMemPizzaRepo;
//    @Autowired
//    private PizzaService pizzaService;

//    @Mock
//    private InMemPizzaRepo inMemPizzaRepo;
//
//    @Before
//    public void init(){
//        MockitoAnnotations.initMocks(this);
//    }

    private PizzaRepository inMemPizzaRepo;
    private PizzaService pizzaService;

    @Before
    public void init() {
        AnnotationConfigApplicationContext ctx = new
                AnnotationConfigApplicationContext();
        ctx.register(InMemPizzaRepoTestConfiguration.class);
        ctx.register(SimplePizzaService.class);
        ctx.refresh();
        inMemPizzaRepo = ctx.getBean("inMemPizzaRepo",
                InMemPizzaRepo.class);
        System.out.println(inMemPizzaRepo);
        pizzaService = ctx.getBean("simplePizzaService",
                SimplePizzaService.class);
        System.out.println(pizzaService);
    }

    @Test
    public void testFind() throws Exception {

        Pizza expectedPizza = new Pizza(1L, "MyFavourite", 90., PizzaType
                .VEGETERIAN);
        when(inMemPizzaRepo.find(1L)).thenReturn(expectedPizza);
//
//        String expectedName = "MyFavourite";
//
        assertThat(expectedPizza, is(pizzaService.find(1L)));

    }
}

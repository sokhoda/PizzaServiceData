package repository;

import config.InMemPizzaRepoTestConfiguration;
import domain.Pizza;
import domain.PizzaType;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pizzaservice.OrderService;
import pizzaservice.PizzaService;
import pizzaservice.SimpleOrderService;
import pizzaservice.SimplePizzaService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

//@ActiveProfiles("withoutAppRunnerTests")
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(SimpleApplication.class)
public class SimpleOrderServiceTest {

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

    private OrderRepository inMemOrderRepo;
    private OrderService orderService;

    @Before
    public void init() {
        AnnotationConfigApplicationContext ctx = new
                AnnotationConfigApplicationContext();
        ctx.register(InMemPizzaRepoTestConfiguration.class);
        ctx.register(SimplePizzaService.class);
        ctx.refresh();
        inMemOrderRepo = ctx.getBean("inMemOrderRepo",
                InMemOrderRepo.class);
        System.out.println(inMemOrderRepo);
        orderService = ctx.getBean("simplePizzaService",
                SimpleOrderService.class);
        System.out.println(orderService);
    }

    @Test
    public void testFind() throws Exception {

        Pizza expectedPizza = new Pizza(1L, "MyFavourite", 90., PizzaType
                .VEGETERIAN);
//        when(inMemOrderRepo.find(1L)).thenReturn(expectedPizza);
//
//        String expectedName = "MyFavourite";
//
//        assertThat(expectedPizza, is(pizzaService.find(1L)));

    }
}

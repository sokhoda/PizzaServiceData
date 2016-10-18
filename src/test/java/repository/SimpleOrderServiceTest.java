package repository;

import config.InMemOrderRepoTestConfiguration;
import config.InMemPizzaRepoTestConfiguration;
import domain.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Spy;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pizzaservice.OrderService;
import pizzaservice.PizzaService;
import pizzaservice.SimpleOrderService;
import pizzaservice.SimplePizzaService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Spy
    private Order spyOrder;

    private OrderRepository inMemOrderRepo;
    private OrderService orderService;

    @Before
    public void init() {
        AnnotationConfigApplicationContext ctx = new
                AnnotationConfigApplicationContext();
        ctx.register(InMemOrderRepoTestConfiguration.class);
        ctx.register(InMemPizzaRepoTestConfiguration.class);
        ctx.register(SimplePizzaService.class);
        ctx.register(SimpleOrderService.class);
        ctx.refresh();
        System.out.println(Arrays.toString(ctx.getBeanDefinitionNames()));
        inMemOrderRepo = ctx.getBean("inMemOrderRepo",
                InMemOrderRepo.class);
        System.out.println(inMemOrderRepo);
        PizzaRepository inMemPizzaRepo = ctx.getBean("inMemPizzaRepo",
                InMemPizzaRepo.class);
        System.out.println(inMemPizzaRepo.getPizzaList());

        orderService = ctx.getBean("simpleOrderService",
                SimpleOrderService.class);
        System.out.println(orderService);
        PizzaService pizzaService = ctx.getBean("simplePizzaService",
                SimplePizzaService.class);
        System.out.println(pizzaService);

//        generateOrder();
    }

    private void generateOrder() {
        Address address = new Address("Customer", "Str", "18", "2");
        Customer customer = new Customer("Alex", address, new LoyaltyCard(0.));
        final List<Pizza> pizzaList = new ArrayList<>();
        pizzaList.add(new Pizza(1L, "Tomato", 90., PizzaType.VEGETERIAN));
        spyOrder = new Order(customer, pizzaList);
    }


    @Test
    public void testFind() throws Exception {

        Pizza expectedPizza = new Pizza(1L, "MyFavourite", 90., PizzaType
                .VEGETERIAN);
        orderService.addPizzas(spyOrder );

//        when(inMemOrderRepo.find(1L)).thenReturn(expectedPizza);
//
//        String expectedName = "MyFavourite";
//
//        assertThat(expectedPizza, is(pizzaService.find(1L)));

    }
}

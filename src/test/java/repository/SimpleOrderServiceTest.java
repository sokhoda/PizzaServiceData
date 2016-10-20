package repository;

import domain.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pizzaservice.OrderService;
import pizzaservice.PizzaService;
import pizzaservice.SimpleOrderService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class SimpleOrderServiceTest {
    @Mock
    private OrderRepository inMemOrderRepo;
    @Mock
    private PizzaService pizzaService;
    private OrderService orderService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        orderService = new SimpleOrderService(pizzaService, inMemOrderRepo);
    }

    @Test
    public void testAddPizzas() throws Exception {
        final Map<Pizza, Integer> pizzaMap = new HashMap<>();
        Pizza pizza = new Pizza(2L, "Chicken", 120., PizzaType.MEAT);
        pizzaMap.put(new Pizza(1L, "Tomato", 90., PizzaType.VEGETERIAN), 1);
        Order order = new Order(null, null, pizzaMap);
        pizzaMap.put(pizza, 1);

        //GIVEN
        given(pizzaService.find(2L)).willReturn(pizza);

        //WHEN
        Order actualOrder = orderService.addPizzas(order, 2L, 1L);

        //THEN
        assertThat(actualOrder, is(new Order(null, null, pizzaMap)));
        verify(pizzaService).find(2L);
    }

    @Test
    public void testPlaceNewOrder() throws Exception {

//      GIVEN

//      WHEN
        Order expectedOrder = orderService.placeNewOrder(generateCustomer(),
                1L);
//      THEN

    }

    private Customer generateCustomer() {
        Address address = new Address("Customer", "Str", "18", "2");
        return new Customer("Alex", address, new LoyaltyCard(0.));
    }
}

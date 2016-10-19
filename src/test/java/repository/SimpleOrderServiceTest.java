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
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class SimpleOrderServiceTest {
    @Mock    private OrderRepository inMemOrderRepo;
    @Mock    private PizzaService pizzaService;
    private OrderService orderService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        orderService = new SimpleOrderService(pizzaService, inMemOrderRepo);
    }

    @Test
    public void testAddPizzas() throws Exception {
        final List<Pizza> pizzaList = new ArrayList<>();
        Pizza pizza = new Pizza(2L, "Chicken", 120., PizzaType.MEAT);
        pizzaList.add(new Pizza(1L, "Tomato", 90., PizzaType.VEGETERIAN));
        Order order = new Order(null, null, pizzaList);
        pizzaList.add(pizza);

        //GIVEN
        given(pizzaService.find(2L)).willReturn(pizza);

        //WHEN
        Order actualOrder = orderService.addPizzas(order, 2L, 1L);

        //THEN
        assertThat(actualOrder, is(new Order(null, null, pizzaList)));
        verify(pizzaService).find(2L);
    }


    private void generateOrder() {
        Address address = new Address("Customer", "Str", "18", "2");
        Customer customer = new Customer("Alex", address, new LoyaltyCard(0.));
    }
}

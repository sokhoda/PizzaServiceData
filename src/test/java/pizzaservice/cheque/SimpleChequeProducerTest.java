package pizzaservice.cheque;

import domain.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pizzaservice.discount.DiscountCalculator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class SimpleChequeProducerTest {
    @Mock
    private DiscountCalculator discountCalculator;
    @Mock
    private ChequeService chequeService;
    private SimpleChequeProducer scp;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        scp = new SimpleChequeProducer(discountCalculator, chequeService);
    }

    @Test
    public void testPlaceCheque() throws Exception {
        final List<Pizza> pizzaList = new ArrayList<>();
        Pizza pizza = new Pizza(2L, "Chicken", 120., PizzaType.MEAT);
        Address address = new Address("Customer", "Str", "18", "2");
        Customer customer = new Customer("Alex", address, new LoyaltyCard(0.));

        pizzaList.add(new Pizza(1L, "Tomato", 90., PizzaType.VEGETERIAN));
        Order order = new Order(null, customer, pizzaList);

        Cheque cheque = new Cheque();
        System.out.println(cheque);

        SimpleChequeProducer scpSpy = spy(scp);

//      GIVEN
        doNothing().when(discountCalculator).handleDiscount(order, cheque);
        doReturn(cheque).when(scpSpy).createNewCheque();

//      WHEN
        Cheque actualCheque = scpSpy.placeCheque(order);
//      THEN
        assertThat(actualCheque, is(cheque));

    }
}

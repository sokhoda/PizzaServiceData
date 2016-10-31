package pizzaservice;

import domain.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pizzaservice.states.OrderStateCycle;
import repository.OrderRepository;
import infrastructure.UnitTestData;

import java.util.*;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class SimpleOrderServiceTest extends UnitTestData {
    @Mock
    private OrderRepository orderRepo;
    @Mock
    private PizzaService pizzaService;
    @Mock
    private OrderStateCycle orderStateCycle;


    @InjectMocks
    private SimpleOrderService simpleOrderService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void addPizzas() throws Exception {

        pizzaMap.put(testPizza1, 1);
        Order order = new Order(null, null, pizzaMap);
        pizzaMap.put(testPizza2, 1);
        Order expectedOrder = new Order(null, null, pizzaMap);

        //GIVEN
        given(pizzaService.find(2L)).willReturn(testPizza2);
        given(orderRepo.save(any())).will(AdditionalAnswers.returnsFirstArg());


        //WHEN
        Order actualOrder = simpleOrderService.addPizzas(order, 2L, 1L);

        //THEN
        assertThat(actualOrder, is(expectedOrder));
        verify(pizzaService).find(2L);
        verify(orderRepo).save(any());
    }


    @Test
    public void placeNewOrder() throws Exception {
        SimpleOrderService sOrderService = spy(SimpleOrderService.class);
        sOrderService.setPizzaService(pizzaService);
        sOrderService.setOrderRepo(orderRepo);

//      GIVEN
        given(orderRepo.save(any())).will(AdditionalAnswers.returnsFirstArg());

//      WHEN
        when(pizzaService.find(1L)).thenReturn(testPizza1);
        doReturn(new Order()).when(sOrderService).createNewOrder();
        doReturn(new OrderStateCycle()).when(sOrderService).createNewOrderStateCycle();
        Order actualOrder = sOrderService.placeNewOrder(testCustomer, 1L);

//      THEN
        assertThat(actualOrder, is(expectedOrder));
        verify(pizzaService).find(1L);
        verify(sOrderService).createNewOrder();
        verify(sOrderService).createNewOrderStateCycle();
        verify(orderRepo).save(any());
    }


    @Test(expected = IllegalStateException.class)
    public void testCreateNewOrder() throws Exception {
        simpleOrderService.createNewOrder();
    }

    @Test(expected = IllegalStateException.class)
    public void createNewOrderStateCycle() throws Exception {
        simpleOrderService.createNewOrderStateCycle();
    }

    @Test
    public void find() throws Exception {

//        GIVEN
        given(orderRepo.find(1L)).willReturn(expectedOrder);
//        WHEN
        Order actualOrder = simpleOrderService.find(1L);
//        THEN
        assertThat(actualOrder, is(expectedOrder));
        verify(orderRepo).find(1L);
    }

    @Test
    public void addTotalSumToCustomerLCard() throws Exception {
        LoyaltyCard expectedLoyaltyCard = new LoyaltyCard();
        expectedLoyaltyCard.setSum(expectedOrder.calcTotalSum());

//        GIVEN
//        WHEN
        simpleOrderService.addTotalSumToCustomerLCard(expectedOrder);
        LoyaltyCard actualLoyaltyCard = expectedOrder.getCustomer()
                .getLoyaltyCard();
//        THEN
        assertThat(actualLoyaltyCard, is(expectedLoyaltyCard));
    }

    @Test
    public void findByCustomer() throws Exception {
        List<Order> expectedOrderList = new ArrayList<>(Arrays.asList
                (expectedOrder));
//        GIVEN
        given(orderRepo.findByCustomer(testCustomer)).willReturn(expectedOrderList);
//        WHEN
        List<Order> actualOrderList = simpleOrderService.findByCustomer(testCustomer);
//        THEN
        assertThat(actualOrderList, is(expectedOrderList));
        verify(orderRepo).findByCustomer(testCustomer);
    }

    @Test
    public void save() throws Exception {

//      GIVEN
        given(orderRepo.save(any())).will(AdditionalAnswers.returnsFirstArg());
//        WHEN
        final Order actualOrder = simpleOrderService.save(expectedOrder);
//        THEN
        assertThat(actualOrder, is(expectedOrder));
        verify(orderRepo).save(any());
    }


}
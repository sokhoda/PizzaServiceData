package pizzaservice.cheque;

import domain.Cheque;
import domain.Order;
import infrastructure.UnitTestData;
import org.junit.Before;
import org.junit.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pizzaservice.ChequeService;
import pizzaservice.OrderService;
import pizzaservice.discount.DiscountCalculator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class SimpleChequeProducerTest extends UnitTestData {
    @Mock
    private DiscountCalculator discountCalculator;
    @Mock
    private OrderService orderService;
    @Mock
    private ChequeService chequeService;

    @InjectMocks
    private SimpleChequeProducer sChequeProducer;

    @Before
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPlaceCheque() throws Exception {

        SimpleChequeProducer sChequeProducer = spy(SimpleChequeProducer.class);
        sChequeProducer.setDiscountCalculator(discountCalculator);
        sChequeProducer.setChequeService(chequeService);
        sChequeProducer.setOrderService(orderService);

        Order order = expectedOrder;
        Cheque expectedCheque = new Cheque();
        expectedCheque.setTotalSum(order.calcTotalSum());
        expectedOrder.setCheque(expectedCheque);

        Cheque cheque = new Cheque();

//      GIVEN
        doReturn(cheque).when(sChequeProducer).createNewCheque();
        doReturn(cheque).when(discountCalculator).handleDiscount(order, cheque);
        given(chequeService.save(any())).will(AdditionalAnswers.returnsFirstArg());
        given(orderService.save(any())).will(AdditionalAnswers.returnsFirstArg());
        doNothing().when(orderService).addTotalSumToCustomerLCard(any());

//      WHEN
        Order actualOrder = sChequeProducer.placeCheque(order);

//      THEN
        assertThat(actualOrder, is(expectedOrder));
        verify(sChequeProducer).createNewCheque();
        verify(discountCalculator).handleDiscount(order, cheque);
        verify(chequeService).save(cheque);
        verify(orderService).save(actualOrder);
    }

    @Test(expected = IllegalStateException.class)
    public void createNewCheque() throws Exception {
        sChequeProducer.createNewCheque();
    }
}

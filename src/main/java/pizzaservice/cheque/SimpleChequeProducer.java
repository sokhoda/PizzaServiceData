package pizzaservice.cheque;

import domain.Cheque;
import domain.Order;
import infrastructure.DomainHandleHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pizzaservice.ChequeService;
import pizzaservice.OrderService;
import pizzaservice.discount.DiscountCalculator;

public class SimpleChequeProducer implements ChequeProducer {
    private DiscountCalculator discountCalculator;
    private OrderService orderService;
    private ChequeService chequeService;

    @Autowired
    public SimpleChequeProducer(DiscountCalculator discountCalculator,
                                OrderService orderService, ChequeService chequeService) {
        this.discountCalculator = discountCalculator;
        this.orderService = orderService;
        this.chequeService = chequeService;
    }

    @Override
    public Order placeCheque(Order order){
        Order newOrder = DomainHandleHelper.clone(order);
        Cheque cheque = createNewCheque();
        cheque.setTotalSum(newOrder.calcTotalSum());
        cheque = discountCalculator.handleDiscount(newOrder, cheque);
        cheque = chequeService.save(cheque);
        newOrder.setCheque(cheque);

        newOrder.addTotalSumToCustomerLCard();

        return orderService.save(newOrder);
    }

    Cheque createNewCheque(){
        throw new IllegalStateException("Container couldn`t create Proxy");
    }


}

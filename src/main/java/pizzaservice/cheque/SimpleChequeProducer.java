package pizzaservice.cheque;

import domain.Cheque;
import domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import pizzaservice.OrderService;
import pizzaservice.discount.DiscountCalculator;

public class SimpleChequeProducer implements ChequeProducer {
    private DiscountCalculator discountCalculator;
    private OrderService orderService;

    @Autowired
    public SimpleChequeProducer(DiscountCalculator discountCalculator, OrderService orderService) {
        this.discountCalculator = discountCalculator;
        this.orderService = orderService;
    }
    @Override
    public Order placeCheque(Order order){
        Order newOrder = order.clone();
        Cheque cheque = createNewCheque();
        newOrder.setCheque(cheque);
        cheque.setTotalSum(newOrder.calcTotalSum());
        discountCalculator.handleDiscount(newOrder, cheque);

        newOrder.addTotalSumToCustomerLCard();
        return orderService.save(newOrder);
    }

    Cheque createNewCheque(){
        throw new IllegalStateException("Container couldn`t create Proxy");
    }


}

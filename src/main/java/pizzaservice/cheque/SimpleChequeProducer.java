package pizzaservice.cheque;

import domain.Cheque;
import domain.Orders;
import infrastructure.DomainHandleHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pizzaservice.ChequeService;
import pizzaservice.OrderService;
import pizzaservice.discount.DiscountCalculator;

@Service("chequeProducer")
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

    public SimpleChequeProducer() {
    }

    @Transactional
    @Override
    public Orders placeCheque(Orders order){
        Orders newOrder = DomainHandleHelper.clone(order);
        Cheque cheque = createNewCheque();
        cheque.setTotalSum(newOrder.calcTotalSum());
        cheque = discountCalculator.handleDiscount(newOrder, cheque);
        cheque = chequeService.save(cheque);
        newOrder.setCheque(cheque);

        orderService.addTotalSumToCustomerLCard(newOrder);

        return orderService.save(newOrder);
    }

    Cheque createNewCheque(){
        throw new IllegalStateException("Container couldn`t create Proxy");
    }

    public void setDiscountCalculator(DiscountCalculator discountCalculator) {
        this.discountCalculator = discountCalculator;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void setChequeService(ChequeService chequeService) {
        this.chequeService = chequeService;
    }
}

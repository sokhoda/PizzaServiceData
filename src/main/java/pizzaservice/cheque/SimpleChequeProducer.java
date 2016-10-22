package pizzaservice.cheque;

import domain.Cheque;
import domain.Order;
import infrastructure.Benchmark;
import org.springframework.beans.factory.annotation.Autowired;
import pizzaservice.discount.DiscountCalculator;

public class SimpleChequeProducer implements ChequeProducer {
    private DiscountCalculator discountCalculator;
    private ChequeService chequeService;

    @Autowired
    public SimpleChequeProducer(DiscountCalculator discountCalculator, ChequeService chequeService) {
        this.discountCalculator = discountCalculator;
        this.chequeService = chequeService;
    }
    @Benchmark(on = true)
    @Override
    public Cheque placeCheque(Order order){
        Cheque cheque = createNewCheque();
        order.setChequeId(cheque.getId());
        cheque.setOrderId(order.getId());
        cheque.setTotalSum(order.calcTotalSum());
        discountCalculator.handleDiscount(order, cheque);
        order.addTotalSumToCustomerLCard();
        chequeService.save(cheque);
        return cheque;
    }



    Cheque createNewCheque(){
        throw new IllegalStateException("Container couldn`t create New Cheque");
    }


}

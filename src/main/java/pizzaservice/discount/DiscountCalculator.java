package pizzaservice.discount;

import domain.Cheque;
import domain.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DiscountCalculator {
    private DiscountHandler prevHandler;
    private DiscountHandler firstHandler;

    @PostConstruct
    public void init(){
        addHandler(new ByQuantityDiscountHandler());
        addHandler(new GeneralLoyaltyCardDiscountHandler());
    }

    public void addHandler(DiscountHandler handler) {
        if (prevHandler != null) {
            prevHandler.setNext(handler);
        }
        else {
            firstHandler = handler;
        }
        prevHandler = handler;
    }

    public void handleDiscount(Order order, Cheque cheque){
        firstHandler.handleDiscount(order, cheque);
    }
}

package pizzaservice.discount;

import domain.Cheque;
import domain.DiscountRecord;
import domain.Order;
import infrastructure.DomainHandleHelper;
import org.springframework.stereotype.Component;

@Component
public class ByQuantityDiscountHandler implements DiscountHandler {
    private DiscountHandler next;

    @Override
    public void setNext(DiscountHandler handler) {
        next = handler;
    }

    @Override
    public void handleDiscount(Order order, Cheque cheque) {
        if (order.getPizzaMap().size() > DISCOUNT_THRESHOLD) {
            Double discountSum = order.getTheMostExpensivePizza().getPrice() *
                    DISCOUNT_MOST_EXPENS_PIZZA_PERCENTAGE / 100.;
            String discountName = this.getClass().getSimpleName() + ", order size > " + DISCOUNT_THRESHOLD;
            cheque.getDiscountList().add(new DiscountRecord(discountName,
                    discountSum));
        }
        next.handleDiscount(order, cheque);
    }
}

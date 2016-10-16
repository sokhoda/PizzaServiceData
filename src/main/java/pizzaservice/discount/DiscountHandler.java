package pizzaservice.discount;

import domain.Cheque;
import domain.Order;

public interface DiscountHandler {
    public static final int DISCOUNT_THRESHOLD = 4;
    public static final int DISCOUNT_MOST_EXPENS_PIZZA_PERCENTAGE = 30;
    public static final int DISCOUNT_MAX_ORDER_SUM_PERCENTAGE = 30;
    public static final int DISCOUNT_LOYALTY_CARD_SUM_PERCENTAGE = 10;

    void setNext(DiscountHandler handler);

    void handleDiscount(Order order, Cheque cheque);
}

package pizzaservice;

import domain.Customer;
import domain.Order;

public interface OrderService {
    Order placeNewOrder(Customer customer, Long... pizzasID);
    Order addPizzas(Order order, Long... idNoPair);

    Double calcDiscountByPizzaQuantity(Order order);

    Double calcDiscountByLCardPercentage(Order order);
}

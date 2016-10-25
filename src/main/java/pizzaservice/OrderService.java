package pizzaservice;

import domain.Customer;
import domain.Order;
import org.springframework.context.ApplicationContext;

public interface OrderService {
    Order placeNewOrder(Customer customer, Long... pizzasID);

    Order addPizzas(Order order, Long... idNoPair);

    Long save(Order order);

}

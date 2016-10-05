package pizzaservice;

import domain.Customer;
import domain.Order;

public interface OrderService {
    Order placeNewOrder(Customer customer, Long... pizzasID);
}

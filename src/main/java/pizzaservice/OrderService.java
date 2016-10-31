package pizzaservice;

import domain.Customer;
import domain.Order;

import java.util.List;

public interface OrderService {
    void addTotalSumToCustomerLCard(Order order);

    Order placeNewOrder(Customer customer, Long... pizzasID);

    Order addPizzas(Order order, Long... idNoPair);

    Order find(Long Order);

    List<Order> findByCustomer(Customer customer);

    Order save(Order order);

}

package repository;

import domain.Customer;
import domain.Order;

import java.util.List;

public interface OrderRepository {
    Order find(Long id);

    List<Order> findByCustomer(Customer customer);

    Order save(Order newOrder);

//    List<Order> getOrderList();
}

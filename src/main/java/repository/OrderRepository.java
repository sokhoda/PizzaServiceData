package repository;

import domain.Customer;
import domain.Orders;
import pizzaservice.states.State;
import pizzaservice.states.StateEn;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository {
    Orders find(Long id);

    List<Orders> findByCustomer(Customer customer);

    Orders save(Orders newOrder);

    List<Orders> findByDateBetween(LocalDateTime fromDate, LocalDateTime toDate);

    List<Orders> findByCustomerByState(Customer customer, State state);

    List<Orders> findByDateBetweenByState(LocalDateTime fromDate, LocalDateTime toDate, State state);


//    List<Order> getOrderList();
}

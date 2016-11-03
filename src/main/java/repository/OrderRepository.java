package repository;

import domain.Customer;
import domain.Orders;
import pizzaservice.states.State;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository {
    Orders find(Long id);

    List<Orders> findByCustomer(Customer customer);

    Orders save(Orders newOrder);

    List<Orders> findByDateBetween(LocalDateTime fromDate, LocalDateTime toDate);

    List<Orders> findByCustomerByState(Customer customer, State state);

    List<Orders> findByDateBetweenByState(LocalDateTime fromDate, LocalDateTime toDate, State state);

    List<Orders> findByDateBetweenByStateByCustomer(
            LocalDateTime fromDate, LocalDateTime toDate, State state, Customer customer);


//    List<Order> getOrderList();
}

package repository;

import domain.Customer;
import domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import pizzaservice.states.State;

import java.time.LocalDateTime;
import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    List<Orders> findByCustomer(Customer customer);


//    List<Orders> findByDateBetween(LocalDateTime fromDate, LocalDateTime toDate);

//    List<Orders> findByCustomerAndState(Customer customer, State state);

//    List<Orders> findByStateAndDateBetween(State state, LocalDateTime
//            fromDate, LocalDateTime toDate );
//
//    List<Orders> findByStateAndCustomerAndDateBetween(
//            State state, Customer customer, LocalDateTime fromDate,
//            LocalDateTime toDate);

}

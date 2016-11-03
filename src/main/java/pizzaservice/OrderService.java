package pizzaservice;

import domain.Customer;
import domain.Orders;
import pizzaservice.states.State;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    void addTotalSumToCustomerLCard(Orders order);

    Orders placeNewOrder(Customer customer, Long... pizzasID);

    Orders addPizzas(Orders order, Long... idNoPair);

    Orders find(Long id);

    List<Orders> findByCustomer(Customer customer);

    List<Orders> findByDateBetween(LocalDateTime fromDate, LocalDateTime toDate);

    List<Orders> findByDateBetweenByState(LocalDateTime fromDate,
                                          LocalDateTime toDate, State
                                                  state);

    List<Orders> findByDateBetweenByStateByCustomer(LocalDateTime fromDate,
                                                    LocalDateTime toDate, State state,
                                                    Customer customer);

    Orders save(Orders order);

    List<Orders> findByCustomerByState(Customer customer, State
            stateEn);
}

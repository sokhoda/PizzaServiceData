package repository;

import domain.Customer;
import domain.Orders;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Repository
public class InMemOrderRepo implements OrderRepository {
    private final List<Orders> orderList = new ArrayList<>();

    @Override
    public Orders find(Long id) {
        return null;
    }

    @Override
    public List<Orders> findByCustomer(Customer customer) {
        return null;
    }

    @Override
    public Orders save(Orders newOrder) {
        orderList.add(newOrder);
        return newOrder;
    }

    @Override
    public List<Orders> findByDateBetween(LocalDateTime fromDate, LocalDateTime toDate) {
        return null;
    }

}

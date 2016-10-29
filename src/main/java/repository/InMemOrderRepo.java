package repository;

import domain.Customer;
import domain.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class InMemOrderRepo implements OrderRepository {
    private final List<Order> orderList = new ArrayList<>();

    @Override
    public Order find(Long id) {
        return null;
    }

    @Override
    public List<Order> findByCustomer(Customer customer) {
        return null;
    }

    @Override
    public Order save(Order newOrder) {
        orderList.add(newOrder);
        return newOrder;
    }

}

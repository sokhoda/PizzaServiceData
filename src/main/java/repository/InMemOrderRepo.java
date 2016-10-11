package repository;

import domain.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class InMemOrderRepo implements OrderRepository {
    private final List<Order> orderList = new ArrayList<>();

    @Override
    public Order saveOrder(Order newOrder) {
        orderList.add(newOrder);
        return newOrder;
    }

    @Override
    public List<Order> getOrderList() {
        return orderList;
    }
}

package repository;

import domain.Order;

import java.util.ArrayList;
import java.util.List;

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

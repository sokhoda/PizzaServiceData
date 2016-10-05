package repository;

import domain.Order;

import java.util.List;

public interface OrderRepository {
    Order saveOrder(Order newOrder);

    List<Order> getOrderList();
}

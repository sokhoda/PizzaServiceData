package repository;

import domain.Order;

import java.util.List;

public interface OrderRepository {
    Order save(Order newOrder);

    List<Order> getOrderList();
}

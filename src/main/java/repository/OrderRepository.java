package repository;

import domain.Order;

import java.util.List;

public interface OrderRepository {
    Order find(Long id);

    Order save(Order newOrder);

//    List<Order> getOrderList();
}

package dao;

import domain.Order;

public interface OrderDao {
    Order find(Long id);

    Long save(Order order);
}

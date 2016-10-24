package dao;

import domain.Order;

import java.util.List;

public interface OrderDao {
    Order find(Long id);

    Long save(Order order);
}

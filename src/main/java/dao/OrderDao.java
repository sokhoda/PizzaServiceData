package dao;

import domain.Orders;

public interface OrderDao {
    Orders find(Long id);

    Long save(Orders order);
}

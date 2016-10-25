package dao;

import domain.Customer;
import domain.Pizza;

import java.util.List;

public interface CustomerDao {
    Customer find(Long id);

    Long save(Customer customer);
}

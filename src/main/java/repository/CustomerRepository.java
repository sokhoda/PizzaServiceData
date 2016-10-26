package repository;

import domain.Customer;
import domain.Order;
import domain.Pizza;

import java.util.List;

public interface CustomerRepository {
    Customer find(Long id);

    Customer save(Customer customer);

//    List<Customer> getCustomerList();
}

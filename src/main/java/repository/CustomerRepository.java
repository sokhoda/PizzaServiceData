package repository;

import domain.Customer;
import domain.Order;
import domain.Pizza;

import java.util.List;

public interface CustomerRepository {
    Customer findById(Long id);

    Customer saveCustomer(Customer newCustomer);

    List<Customer> getCustomerList();
}

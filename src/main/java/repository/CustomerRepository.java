package repository;

import domain.Customer;
import domain.LoyaltyCard;

import java.util.List;

public interface CustomerRepository {
    Customer find(Long id);

    List<Customer> findByName(String name);

    List<Customer> findByLoyaltyCard(LoyaltyCard loyaltyCard);

    Customer save(Customer customer);

}

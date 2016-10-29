package repository;

import domain.Customer;
import domain.LoyaltyCard;
import domain.Order;
import domain.Pizza;

import java.util.List;

public interface CustomerRepository {
    Customer find(Long id);

    Customer findByName(String name);

    Customer findByLoyaltyCard(LoyaltyCard loyaltyCard);

    Customer save(Customer customer);

}

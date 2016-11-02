package pizzaservice;

import domain.Address;
import domain.Customer;
import domain.LoyaltyCard;

import java.util.List;

/**
 * Created by s_okhoda on 13.10.2016.
 */
public interface CustomerService {
    Customer save(Customer customer);

    Customer find(Long id);

    List<Customer> findByName(String name);

    List<Customer> findByLoyaltyCard(LoyaltyCard loyaltyCard);

    int delete(Customer customer);

    Customer placeNewCustomer(String name, Address address, LoyaltyCard
            loyaltyCard);
}

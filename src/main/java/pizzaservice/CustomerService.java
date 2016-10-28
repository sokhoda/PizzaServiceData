package pizzaservice;

import domain.Address;
import domain.Customer;
import domain.LoyaltyCard;

/**
 * Created by s_okhoda on 13.10.2016.
 */
public interface CustomerService {
    Customer save(Customer customer);

    Customer find(Long id);

    Customer findByName(String name);

    Customer findByLoyaltyCard(LoyaltyCard loyaltyCard);

    Customer placeNewCustomer(String name, Address address, LoyaltyCard
            loyaltyCard);
}

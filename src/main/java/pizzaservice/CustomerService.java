package pizzaservice;

import domain.Address;
import domain.Customer;
import domain.LoyaltyCard;

/**
 * Created by s_okhoda on 13.10.2016.
 */
public interface CustomerService {
    Customer save(Customer customer);

    Customer findById(Long id);

    Customer placeNewCustomer(String name, Address address, LoyaltyCard
            loyaltyCard);
}

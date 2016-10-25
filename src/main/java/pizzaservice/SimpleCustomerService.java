package pizzaservice;

import domain.Address;
import domain.Customer;
import domain.LoyaltyCard;
import org.springframework.beans.factory.annotation.Autowired;
import repository.CustomerRepository;

/**
 * Created by s_okhoda on 13.10.2016.
 */
public class SimpleCustomerService implements CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public SimpleCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(Customer customer){
        return customerRepository.save(customer);

    }

    @Override
    public Customer findById(Long id){
        return customerRepository.findById(id);
    }

    @Override
    public Customer placeNewCustomer(String name, Address address, LoyaltyCard loyaltyCard) {

       Customer customer = createNewCustomer();
        customer.setName(name);
        customer.setAddress(address);
        customer.setLoyaltyCard(loyaltyCard);
        return null;
    }

     Customer createNewCustomer() {
        throw new IllegalStateException("Customer createNewCustomer method " +
                "not overridden");
    }
}

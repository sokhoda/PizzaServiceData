package pizzaservice;

import domain.Address;
import domain.Customer;
import domain.LoyaltyCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import repository.CustomerRepository;
import repository.LoyaltyCardRepository;

/**
 * Created by s_okhoda on 13.10.2016.
 */
public class SimpleCustomerService implements CustomerService {
    @Autowired
    @Qualifier("customerRepository")
    private CustomerRepository customerRepository;
    @Autowired
    private LoyaltyCardRepository loyaltyCardRepository;

    public SimpleCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(Customer customer){
        customer.setLoyaltyCard(loyaltyCardRepository.save(customer
                .getLoyaltyCard()));
        return customerRepository.save(customer);

    }

    @Override
    public Customer findById(Long id){
        return customerRepository.find(id);
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

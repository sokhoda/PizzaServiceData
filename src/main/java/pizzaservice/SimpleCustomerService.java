package pizzaservice;

import domain.Address;
import domain.Customer;
import domain.LoyaltyCard;
import domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import repository.CustomerRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class SimpleCustomerService implements CustomerService {
    @Autowired
    @Qualifier("customerRepository")
    private CustomerRepository customerRepository;
//    @Autowired
//    private LoyaltyCardService loyaltyCardService;

    public SimpleCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    @Override
    public Customer save(Customer customer){
        return customerRepository.save(customer);
    }

    @Override
    public Customer find(Long id){
        return customerRepository.find(id);
    }

    @Override
    public Customer findByName(String name) {
        return customerRepository.findByName(name);
    }

    @Override
    public Customer findByLoyaltyCard(LoyaltyCard loyaltyCard) {
        return customerRepository.findByLoyaltyCard(loyaltyCard);
    }


    @Transactional
    @Override
    public Customer placeNewCustomer(String name, Address address, LoyaltyCard loyaltyCard) {
       Customer customer = createNewCustomer();
        customer.setName(name);
        customer.setAddress(new HashSet<>(Arrays.asList(address)));
        customer.setLoyaltyCard(loyaltyCard);
        return save(customer);
    }

     Customer createNewCustomer() {
        throw new IllegalStateException("Customer createNewCustomer method " +
                "not overridden");
    }
}

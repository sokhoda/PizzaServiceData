package pizzaservice;

import domain.Address;
import domain.Customer;
import domain.LoyaltyCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.CustomerRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service("customerService")
public class SimpleCustomerService implements CustomerService {
//    @Qualifier("customerRepository")
    private CustomerRepository customerRepository;

    @Autowired
    public SimpleCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public SimpleCustomerService() {
    }

    @Transactional
    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer find(Long id) {
        return customerRepository.findOne(id);
    }

    @Override
    public List<Customer> findByName(String name) {
//        return customerRepository.findByName(name);
        return null;
    }

    @Override
    public List<Customer> findByLoyaltyCard(LoyaltyCard loyaltyCard) {
//        return customerRepository.findByLoyaltyCard(loyaltyCard);
        return null;
    }

    @Override
    public int delete(Customer customer){
//        return customerRepository.delete(customer);
        return 0;
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

    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
}

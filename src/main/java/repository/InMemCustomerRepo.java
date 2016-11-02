package repository;

import domain.Address;
import domain.Customer;
import domain.LoyaltyCard;
import infrastructure.Benchmark;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemCustomerRepo implements CustomerRepository {
    private final List<Customer> customerList = new ArrayList<>();

    @PostConstruct
    public void init() {
        Customer customer = new Customer("Anna Guyvan", new LoyaltyCard(0.));
        Address address = new Address("01032", "Kyiv", "Iwana " +
                        "Pidkovy", "Str.", "8", "18", customer);
        customerList.add(customer);
    }


    @Benchmark(on = false)
    @Override
    public Customer find(Long id) {
        int i = 0;
        while (i < customerList.size() && !customerList.get(i).getId().equals(id)) {
            i++;
        }
        return i < customerList.size() ? customerList.get(i) : null;
    }

    @Override
    public List<Customer> findByName(String name) {
        return null;
    }

    @Override
    public List<Customer> findByLoyaltyCard(LoyaltyCard loyaltyCard) {
        return null;
    }

    @Override
    public Customer save(Customer newCustomer) {
            customerList.add(newCustomer);
            return newCustomer;
    }

    @Override
    public int delete(Customer customer) {
        return 0;
    }

//    @Override
//    public List<Customer> getCustomerList() {
//        return customerList;
//    }
}

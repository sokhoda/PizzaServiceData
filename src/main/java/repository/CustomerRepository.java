package repository;

import domain.Customer;
import domain.LoyaltyCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
//PagingAndSortingRepository
public interface CustomerRepository  extends JpaRepository<Customer, Long> {

    List<Customer> findByName(String name);

//    List<Customer> findByLoyaltyCard(LoyaltyCard loyaltyCard);

//
//    int delete(Customer customer);
}

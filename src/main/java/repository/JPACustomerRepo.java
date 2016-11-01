package repository;

import domain.Customer;
import domain.LoyaltyCard;
import infrastructure.JPQLQueries;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("customerRepository")
public class JPACustomerRepo implements CustomerRepository {

    @PersistenceContext
    private EntityManager em;

//    @PersistenceUnit()
//    private EntityManagerFactory emf;

    @Override
    public Customer find(Long id) {
        return em.find(Customer.class, id);
    }

    @Override
    public List<Customer> findByName(String name) {
        TypedQuery<Customer> query = em.createNamedQuery("Customer.findByName",
                Customer.class);
        return query.setParameter("name", name).getResultList();
    }


    @Override
    public List<Customer> findByLoyaltyCard(LoyaltyCard loyaltyCard) {
        return JPQLQueries.selectResultList(Customer.class, em, "SELECT c from " +
                "Customer c ", "WHERE c.loyaltyCard.id = :id", new
                Object[]{loyaltyCard.getId()});
    }

    @Override
    public Customer save(Customer Customer) {
        return em.merge(Customer);
    }


}

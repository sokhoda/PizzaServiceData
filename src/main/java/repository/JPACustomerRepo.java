package repository;

import domain.Customer;
import domain.LoyaltyCard;
import domain.Pizza;
import infrastructure.JPQLQueries;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    public Customer findByName(String name) {
//        TypedQuery<Customer> query = em.createQuery("SELECT c from Customer c " +
//                "WHERE c.name = :name", Customer.class);
//        return query.setParameter("name", name).getSingleResult();

        TypedQuery<Customer> query = em.createNamedQuery("Customer.findByName",
                Customer.class);
        return query.setParameter("name", name).getSingleResult();
    }

    @Override
    public Customer findByLoyaltyCard(LoyaltyCard loyaltyCard) {
        return JPQLQueries.selectSimpleResult(Customer.class, em, "SELECT c from " +
                "Customer c ", "WHERE c.name = :name", new Object[]{loyaltyCard});
    }

    @Override
    public Customer save(Customer Customer) {
        return em.merge(Customer);
    }

}

package repository;

import domain.Customer;
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

//    @Override
//    public Customer read(Long id) {
//        TypedQuery<Customer> query = em.createQuery("SELECT c from Customer c WHERE c.id = :id ", Customer.class);
//        return query.setParameter("id", id).getSingleResult();
//    }

    @Override
    @Transactional
    public Customer save(Customer Customer) {
        Customer newCustomer = em.merge(Customer);
        return newCustomer;
    }

}

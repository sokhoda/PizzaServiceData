package dao;

import domain.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class SimpleCustomerDao implements CustomerDao {
    AddressDao addressDao = new SimpleAddressDao();

    @Override
    public Customer find(Long id) {
        return null;
    }

    @Override
    public Long save(Customer customer) {
        Long id = null;
        if (customer == null) {
            return id;
        }
        addressDao.save(customer.getAddress());

        EntityManagerFactory emf = Persistence.createEntityManagerFactory
                ("jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        if (customer.getId() == null) {
            em.persist(customer);
        }
        else {
            em.merge(customer);
        }
        et.commit();

        em.close();
        emf.close();
        return customer.getId();
    }

}

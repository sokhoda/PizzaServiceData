package dao;

import domain.Address;
import domain.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class SimpleAddressDao implements AddressDao {

    @Override
    public Address find(Long id) {
        return null;
    }

    @Override
    public Long save(Address address) {
        Long id = null;
        if (address == null) {
            return id;
        }
        EntityManagerFactory emf = Persistence.createEntityManagerFactory
                ("jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        if (address.getId() == null) {
            em.persist(address);
        }
        else {
            em.merge(address);
        }
        et.commit();

        em.close();
        emf.close();
        return address.getId();
    }
}

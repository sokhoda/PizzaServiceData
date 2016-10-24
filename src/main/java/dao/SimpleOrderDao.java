package dao;

import domain.Order;
import domain.Pizza;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class SimpleOrderDao implements OrderDao {
    @Override
    public Order find(Long id) {
        return null;
    }

    @Override
    public Long save(Order order) {
        Long id = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory
                ("jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(order);
        et.commit();

        em.close();
        emf.close();
        return id;
    }
}

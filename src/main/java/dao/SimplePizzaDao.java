package dao;

import domain.Pizza;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class SimplePizzaDao implements PizzaDao {
    @Override
    public Pizza find(Long id) {
        return null;
    }

    @Override
    public Long save(List<Pizza> pizza) {
        Long id = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory
                ("jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        for (Pizza pizza1 : pizza) {
            em.persist(pizza1);
            id = pizza1.getId();
        }
        et.commit();

        em.close();
        emf.close();
        return id;
    }

    @Override
    public Long save(Pizza pizza) {
        Long id = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory
                ("jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(pizza);
        et.commit();

        em.close();
        emf.close();
        return id;
    }

}

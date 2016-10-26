package repository;

import domain.Pizza;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Repository("pizzaRepository")
public class JPAPizzaRepo implements PizzaRepository {

    @PersistenceContext
    private EntityManager em;

//    @PersistenceUnit()
//    private EntityManagerFactory emf;

    @Override
    public Pizza find(Long id) {
        return em.find(Pizza.class, id);
    }

    @Override
    public Pizza read(Long id) {
        TypedQuery<Pizza> query = em.createQuery("SELECT p from Pizza p WHERE p.id = :id ", Pizza.class);
        return query.setParameter("id", id).getSingleResult();
    }

    @Override
    @Transactional
    public Pizza save(Pizza pizza) {
        Pizza newPizza = em.merge(pizza);
        return newPizza;
    }

    @Override
    public List<Pizza> getPizzaList() {
        return null;
    }
}

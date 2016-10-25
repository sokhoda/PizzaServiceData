package repository;

import domain.Pizza;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
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
    @Transactional
    public Pizza save(Pizza pizza){
        Pizza newPizza = em.merge(pizza);
        return newPizza;
    }

    @Override
    public List<Pizza> getPizzaList() {
        return null;
    }
}

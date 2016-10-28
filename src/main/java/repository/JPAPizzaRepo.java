package repository;

import domain.Pizza;

import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
    public Pizza save(Pizza pizza) {
        return em.merge(pizza);
    }

    @Override
    public List<Pizza> findAll() {
        return em.createQuery("SELECT p FROM Pizza", Pizza.class).getResultList();
    }
}

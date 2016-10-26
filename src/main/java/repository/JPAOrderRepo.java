package repository;

import domain.Customer;
import domain.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("orderRepository")
public class JPAOrderRepo implements OrderRepository {

    @PersistenceContext
    private EntityManager em;

//    @PersistenceUnit()
//    private EntityManagerFactory emf;

    @Override
    public Order find(Long id) {
        return em.find(Order.class, id);
    }

//    @Override
//    public Order read(Long id) {
//        TypedQuery<Order> query = em.createQuery("SELECT c from Order c WHERE c.id = :id ", Order.class);
//        return query.setParameter("id", id).getSingleResult();
//    }

    @Override
    @Transactional
    public Order save(Order Order) {
        Order newOrder = em.merge(Order);
        return newOrder;
    }


}

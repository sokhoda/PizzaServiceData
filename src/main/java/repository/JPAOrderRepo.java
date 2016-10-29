package repository;

import domain.Customer;
import domain.Order;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pizzaservice.states.OrderStateCycle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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

    @Override
    public List<Order> findByCustomer(Customer customer) {
        TypedQuery<Order> query = em.createNamedQuery("Order" +
                ".findByCustomer", Order.class);
        return  query.setParameter("customer", customer).getResultList();
    }

    @Override
    @Transactional
    public Order save(Order order) {
        return em.merge(order);
    }


}

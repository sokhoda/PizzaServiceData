package repository;

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
    @Transactional
    public Order save(Order order) {
        Order newOrder = null;
        if (order != null){
            OrderStateCycle orderStateCycle = order.getOrderStateCycle();
            order.setState(orderStateCycle.getCurState().getName());
            newOrder = em.merge(order);
            newOrder.setOrderStateCycle(orderStateCycle);
        }
        return newOrder;
    }


}

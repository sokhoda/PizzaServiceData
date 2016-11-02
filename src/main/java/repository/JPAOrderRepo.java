package repository;

import domain.Cheque;
import domain.Customer;
import domain.Orders;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Repository("orderRepository")
public class JPAOrderRepo implements OrderRepository {

    @PersistenceContext
    private EntityManager em;


//    @PersistenceUnit()
//    private EntityManagerFactory emf;


    @Override
    public Orders find(Long id) {
        return em.find(Orders.class, id);
    }

    @Override
    public List<Orders> findByCustomer(Customer customer) {
        TypedQuery<Orders> query = em.createNamedQuery("Order" +
                ".findByCustomer", Orders.class);
        return  query.setParameter("customer", customer).getResultList();
    }

    @Override
    @Transactional
    public Orders save(Orders order) {
        return em.merge(order);
    }

    @Override
    public List<Orders> findByDateBetween(LocalDateTime fromDate, LocalDateTime toDate) {
        TypedQuery<Orders> query = em.createNamedQuery("Order.findByDateBetween", Orders.class);
        return  query.setParameter("fromDate", fromDate)
                .setParameter("toDate", toDate)
                .getResultList();
    }
}

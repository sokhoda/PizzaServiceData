package repository;

import domain.Cheque;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("chequeRepository")
public class JPAChequeRepo implements ChequeRepository {
    @PersistenceContext
    private EntityManager em;

//    @PersistenceUnit()
//    private EntityManagerFactory emf;

    @Override
    public Cheque find(Long id) {
        return em.find(Cheque.class, id);
    }

//    @Override
//    public Cheque read(Long id) {
//        TypedQuery<Cheque> query = em.createQuery("SELECT p from Cheque p WHERE p.id = :id ", Cheque.class);
//        return query.setParameter("id", id).getSingleResult();
//    }

    @Override
    public Cheque save(Cheque cheque) {
        return em.merge(cheque);
    }

}

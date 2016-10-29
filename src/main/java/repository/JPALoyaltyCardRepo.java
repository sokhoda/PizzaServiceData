package repository;

import domain.LoyaltyCard;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("loyaltyCardRepository")
public class JPALoyaltyCardRepo implements LoyaltyCardRepository {

    @PersistenceContext
    private EntityManager em;

//    @PersistenceUnit()
//    private EntityManagerFactory emf;

    @Override
    public LoyaltyCard find(Long id) {
        return em.find(LoyaltyCard.class, id);
    }

    @Override
    @Transactional
    public LoyaltyCard save(LoyaltyCard LoyaltyCard) {
        LoyaltyCard newLoyaltyCard = em.merge(LoyaltyCard);
        return newLoyaltyCard;
    }

}

package repository;

import domain.Address;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("addressRepository")
public class JPAAddressRepo implements AddressRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Address find(Long id) {
        return em.find(Address.class, id);
    }

    @Override
    public List<Address> findByCityName(String city) {
        TypedQuery<Address> query = em.createNamedQuery("Address" +
                ".findByCityName", Address.class);
        return query.setParameter("city", city).getResultList();
    }

    @Override
    @Transactional
    public Address save(Address address) {
        return em.merge(address);
    }

}

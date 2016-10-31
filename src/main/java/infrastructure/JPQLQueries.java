package infrastructure;

import domain.Customer;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class JPQLQueries {

    public static <T> T selectSimpleResult(Class<T> clazz, EntityManager em,
                                           String select, String where,
                                           Object[] params ){

        TypedQuery<T> query =  em.createQuery(select + where, clazz);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i, params[i]);
        }
        return query.getSingleResult();
    }
    public static <T> List<T> selectResultList(Class<T> clazz, EntityManager em,
                                               String select, String where,
                                               Object[] params ){

        TypedQuery<T> query =  em.createQuery(select + where, clazz);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i, params[i]);
        }
        return query.getResultList();
    }
}

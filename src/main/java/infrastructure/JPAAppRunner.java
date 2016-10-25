package infrastructure;

import domain.Oak;
import domain.Pizza;
import domain.PizzaType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JPAAppRunner {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory
                ("jpa");
        EntityManager em = emf.createEntityManager();
        Pizza pizza = new Pizza(null, "Tomato", 90., PizzaType.VEGETERIAN);
        Oak oak = new Oak(null, "ukrainian", 20);

        EntityTransaction et = em.getTransaction();
        et.begin();
        System.out.println("before persist " +  pizza.getId());
        em.persist(pizza);
//        em.merge(pizza);
//        pizza.setName("224324234");
        System.out.println("after persist " +  pizza.getId());
//        em.persist(oak);
        et.commit();

        em.clear();

        Pizza pizza1 = em.find(Pizza.class, 5L);
        System.out.println(pizza == pizza1);

        em.close();
        emf.close();
    }
}

package infrastructure;

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
        Pizza pizza = new Pizza(4L, "Tomato", 90., PizzaType.VEGETERIAN);
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(pizza);
        et.commit();
        em.clear();

        Pizza pizza1 = em.find(Pizza.class, 4L);
        System.out.println(pizza == pizza1);

        em.close();
        emf.close();
    }
}

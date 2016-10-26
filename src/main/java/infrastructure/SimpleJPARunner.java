package infrastructure;

import dao.OrderDao;
import dao.PizzaDao;
import dao.SimpleOrderDao;
import dao.SimplePizzaDao;
import domain.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleJPARunner {
    private static final List<Pizza> pizzaList = new ArrayList<>();

    public static void init() {
        pizzaList.add(new Pizza(null, "Tomato", 90., PizzaType.VEGETERIAN));
        pizzaList.add(new Pizza(null, "Chicken", 120., PizzaType.MEAT));
        pizzaList.add(new Pizza(null, "Fish", 220., PizzaType.SEA));
    }

    public static void main(String[] args) {
        PizzaDao pizzaDao = new SimplePizzaDao();
        OrderDao orderDao = new SimpleOrderDao();
        init();
        pizzaDao.save(pizzaList);

//        Customer customer = new Customer("cust1", new Address("BestAddress",
//                "st", "14", "34"));
//        Map<Pizza, Integer> pizzaMap = new HashMap<>();
//        Pizza pizza1 = pizzaDao.find(1L);
////        pizza1.setPrice(120.);
////        System.out.println(pizza1);
////        pizzaDao.save(new Pizza(null, "bestOfTheBest", 111., PizzaType.SEA));
//        Pizza pizza2 = pizzaDao.find(2L);
//        pizzaMap.put(pizza1, 1);
//        pizzaMap.put(pizza2, 3);
//        Order order = new Order(customer, pizzaMap);
//        order.setChequeId(110L);
//        System.out.println(order);
//        orderDao.save(order);


    }

    public static List<Pizza> getPizzaList() {
        return pizzaList;
    }
}

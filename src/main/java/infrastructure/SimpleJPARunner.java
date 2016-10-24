package infrastructure;

import dao.PizzaDao;
import dao.SimplePizzaDao;
import domain.Pizza;
import domain.PizzaType;

import java.util.ArrayList;
import java.util.List;

public class SimpleJPARunner {
    private static final List<Pizza> pizzaList = new ArrayList<>();

    public static void init() {
        pizzaList.add(new Pizza(null, "Tomato", 90., PizzaType.VEGETERIAN));
        pizzaList.add(new Pizza(null, "Chicken", 120., PizzaType.MEAT));
        pizzaList.add(new Pizza(null, "Fish", 220., PizzaType.SEA));
    }

    public static void main(String[] args) {
        PizzaDao pizzaDao = new SimplePizzaDao();
        init();
        pizzaDao.save(pizzaList);
    }
}

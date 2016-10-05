package repository;

import domain.Pizza;
import domain.PizzaType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InMemPizzaRepo implements PizzaRepository {
    private final List<Pizza> pizzaList = new ArrayList<>(Arrays.asList(
            new Pizza(1L,"Tomato", BigDecimal.valueOf(90), PizzaType.VEGETERIAN
                    ),
            new Pizza(2L,"Chicken", BigDecimal.valueOf(120), PizzaType.MEAT),
            new Pizza(3L,"Fish", BigDecimal.valueOf(220), PizzaType.SEA)));



    @Override
    public Pizza find(Long id) {
        int i = 0;
        while (i < pizzaList.size() && !pizzaList.get(i).getId().equals(id)) {
            i++;
        }
        return i < pizzaList.size() ? pizzaList.get(i) : null;
    }

    @Override
    public List<Pizza> getPizzaList() {
        return pizzaList;
    }
}

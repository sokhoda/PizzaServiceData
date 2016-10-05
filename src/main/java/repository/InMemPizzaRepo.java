package repository;

import domain.Pizza;
import domain.PizzaType;
import infrastructure.Benchmark;
import infrastructure.PostCreate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InMemPizzaRepo implements PizzaRepository {
    private final List<Pizza> pizzaList = new ArrayList<>();

    @PostCreate
    public void init() {
        pizzaList.add(new Pizza(1L, "Tomato", BigDecimal.valueOf(90), PizzaType.VEGETERIAN));
        pizzaList.add(new Pizza(2L, "Chicken", BigDecimal.valueOf(120), PizzaType.MEAT));
        pizzaList.add(new Pizza(3L, "Fish", BigDecimal.valueOf(220), PizzaType.SEA));
    }

    @Benchmark(on = false)
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

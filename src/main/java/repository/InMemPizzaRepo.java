package repository;

import domain.Pizza;
import domain.PizzaType;
import infrastructure.Benchmark;
import infrastructure.PostCreate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class InMemPizzaRepo implements PizzaRepository {
    private final List<Pizza> pizzaList = new ArrayList<>();

    //    @PostCreate
    @PostConstruct
    public void init() {
        pizzaList.add(new Pizza(1L, "Tomato", 90., PizzaType.VEGETERIAN));
        pizzaList.add(new Pizza(2L, "Chicken", 120., PizzaType.MEAT));
        pizzaList.add(new Pizza(3L, "Fish", 220., PizzaType.SEA));
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

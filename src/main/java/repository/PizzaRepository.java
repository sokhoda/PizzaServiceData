package repository;

import domain.Pizza;

import java.util.List;

public interface PizzaRepository {
    Pizza find(Long id);

    Pizza read(Long id);

    Pizza save(Pizza pizza);

    List<Pizza> getPizzaList();
}

package repository;

import domain.Pizza;

import java.util.List;

public interface PizzaRepository {
    Pizza find(Long id);

    List<Pizza> getPizzaList();
}

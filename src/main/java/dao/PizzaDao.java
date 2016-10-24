package dao;

import domain.Pizza;

import java.util.List;

public interface PizzaDao {
    Pizza find(Long id);

    Long save(List<Pizza> pizza);

    Long save(Pizza pizza);
}

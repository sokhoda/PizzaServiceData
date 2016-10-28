package pizzaservice;

import domain.Pizza;

import java.util.List;

public interface PizzaService {
    Pizza save(Pizza pizza);

    Pizza find(Long id);

    List<Pizza> findAll();
}

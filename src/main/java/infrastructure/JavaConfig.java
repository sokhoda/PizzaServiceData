package infrastructure;

import pizzaservice.SimpleOrderService;
import pizzaservice.SimplePizzaService;
import repository.InMemOrderRepo;
import repository.InMemPizzaRepo;

import java.util.HashMap;
import java.util.Map;

public class JavaConfig implements Config {
    private Map<String, Class<?>> classes = new HashMap<>();
    {
        classes.put("pizzaRepository", InMemPizzaRepo.class);
        classes.put("orderRepository", InMemOrderRepo.class);
        classes.put("orderService", SimpleOrderService.class);
        classes.put("pizzaService", SimplePizzaService.class);
    }

    @Override
    public Class<?> getImp(String name) {
        return classes.get(name);
    }
}

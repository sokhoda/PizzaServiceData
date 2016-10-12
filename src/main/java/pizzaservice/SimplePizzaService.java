package pizzaservice;

import domain.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PizzaRepository;
@Service
public class SimplePizzaService implements PizzaService {
    private PizzaRepository pizzaRepo;

    public SimplePizzaService() {
    }

    @Autowired
    public SimplePizzaService(PizzaRepository pizzaRepo) {
//        InitialContext context = new InitialContext();
//        this.pizzaRepo = (PizzaRepo)context.getInstance("pizzaRepository");
        this.pizzaRepo = pizzaRepo;
    }

    @Override
    public Pizza find(Long id) {
        return pizzaRepo.find(id);
    }

}

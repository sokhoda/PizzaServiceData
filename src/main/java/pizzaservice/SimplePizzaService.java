package pizzaservice;

import domain.Pizza;
import repository.PizzaRepository;

public class SimplePizzaService implements PizzaService {
    private PizzaRepository pizzaRepo;


    public SimplePizzaService(PizzaRepository pizzaRepo) {
//        InitialContext context = new InitialContext();
//        this.pizzaRepo = (PizzaRepo)context.getInstance("pizzaRepository");
        this.pizzaRepo = pizzaRepo;
    }

    @Override
    public Pizza find(Long id){
        return pizzaRepo.find(id);
    }

}

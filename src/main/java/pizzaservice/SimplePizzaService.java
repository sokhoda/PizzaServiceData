package pizzaservice;

import domain.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.PizzaRepository;

import java.util.List;

@Service("pizzaService")
public class SimplePizzaService implements PizzaService {

    @Autowired
    @Qualifier(value = "pizzaRepository")
    private PizzaRepository pizzaRepo;

    public SimplePizzaService() {
    }

    public SimplePizzaService(PizzaRepository pizzaRepo) {
        this.pizzaRepo = pizzaRepo;
    }

    @Transactional
    @Override
    public Pizza save(Pizza pizza) {
        return pizzaRepo.save(pizza);
    }

    @Override
    public Pizza find(Long id) {
        return pizzaRepo.findOne(id);
    }

    @Override
    public List<Pizza> findAll() {
        return pizzaRepo.findAll();
    }


}

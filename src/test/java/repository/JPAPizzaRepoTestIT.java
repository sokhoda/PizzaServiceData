package repository;

import domain.Pizza;
import domain.PizzaType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static junit.framework.TestCase.assertNotNull;


public class JPAPizzaRepoTestIT extends RepoTestConfig {
    @Autowired
    private PizzaRepository pizzaRepository;

    @Test
    public void testSavePizza() throws Exception {
        Pizza pizza = new Pizza();
        pizza.setName("Customized");
        pizza.setType(PizzaType.MEAT);
        pizza = pizzaRepository.save(pizza);
        assertNotNull(pizza.getId());

    }

    @Test
    public void test2() throws Exception {
//        jdbcTemplate

        assertNotNull(12);
    }
}

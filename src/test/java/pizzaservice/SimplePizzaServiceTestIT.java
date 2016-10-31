package pizzaservice;

import domain.Pizza;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import infrastructure.RepoTestConfig;

import static junit.framework.TestCase.assertNotNull;

public class SimplePizzaServiceTestIT extends RepoTestConfig{
    @Autowired
    private PizzaService pizzaService;

    @Test
    public void save() throws Exception {
        //GIVEN

        //WHEN
        Pizza expectedPizza = pizzaService.save(testPizza1);
        //THEN
        assertNotNull(expectedPizza.getId());

    }

    @Test
    public void find() throws Exception {

    }

    @Test
    public void findAll() throws Exception {

    }

}
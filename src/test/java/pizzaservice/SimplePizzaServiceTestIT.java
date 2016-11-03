package pizzaservice;

import domain.Pizza;
import infrastructure.RepoTestConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SimplePizzaServiceTestIT extends RepoTestConfig{
    @Autowired
    private PizzaService pizzaService;

    @Before
    public void clearTable() {
        clearAllTables();
    }

    @Test
    public void save() throws Exception {
        //GIVEN
        //WHEN
        Pizza expectedPizza = pizzaService.save(testPizza1);
        //THEN
        assertNotNull(expectedPizza.getPizzaId());
    }

    @Test
    public void find() throws Exception {
        insertPizza(testPizza1);
        //WHEN
        Pizza actualPizza = pizzaService.find(1L);
        //THEN
        assertThat(actualPizza,is(testPizza1));
    }

    @Test
    public void findAll() throws Exception {
        insertPizza(testPizza1);
        insertPizza(testPizza2);
//        GIVEN
        List<Pizza> expectedPizzaList = Arrays.asList(testPizza1, testPizza2);
        //WHEN
        List<Pizza> actualPizzaList = pizzaService.findAll();
        //THEN
        assertThat(actualPizzaList,is(expectedPizzaList));
    }
}
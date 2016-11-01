package repository;

import domain.Pizza;
import infrastructure.RepoTestConfig;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class JPAPizzaRepoTestIT extends RepoTestConfig {
    @Autowired
    private PizzaRepository pizzaRepository;

    @Test
    public void testSavePizza() throws Exception {
        //GIVEN
        //WHEN
        Pizza actualPizza = pizzaRepository.save(testPizza1);
        //THEN
        assertNotNull(actualPizza.getId());
    }

    @Test
    public void testSavePizza2() throws Exception {
        //GIVEN
        Pizza expectedPizza = testPizza1;
        Long id = pizzaRepository.save(testPizza1).getId();

        System.out.println(pizzaRepository.find(id));
        //WHEN
//        List<Pizza> actualPizza =  jdbcTemplate.query(
//                "SELECT * from PIZZA", new BeanPropertyRowMapper(Pizza.class));

        List<Pizza> actualPizza = jdbcTemplate.queryForList("SELECT * FROM " +
                "PIZZA", Pizza.class);
        System.out.println("size=" + actualPizza);
        //THEN
//        assertThat(actualPizza.get(0), is(expectedPizza));
    }

    @Test
    public void testFind() throws Exception {
        insertPizza();
        //WHEN
        Pizza actualPizza = pizzaRepository.find(1L);
        //THEN
        assertThat(actualPizza, is(testPizza1));
    }
}

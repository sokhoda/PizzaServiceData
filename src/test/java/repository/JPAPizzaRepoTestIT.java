package repository;

import domain.Pizza;
import domain.PizzaType;
import infrastructure.RepoTestConfig;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.sql.Types;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class JPAPizzaRepoTestIT extends RepoTestConfig {
    @Autowired
    private PizzaRepository pizzaRepository;
    private Pizza testPizza = new Pizza(13L, "Oleksandr's", 1230., PizzaType.SEA);

    @Test
    public void testSavePizza() throws Exception {
        //GIVEN
        Pizza pizza = new Pizza();
        pizza.setName("Customized");
        pizza.setType(PizzaType.MEAT);

        //WHEN
        pizza = pizzaRepository.save(pizza);
        //THEN
        assertNotNull(pizza.getId());
    }

    @Test
    public void testSavePizza2() throws Exception {
        //GIVEN
        Pizza expectedPizza = testPizza;
        Long id = pizzaRepository.save(testPizza).getId();

        System.out.println(pizzaRepository.find(id));
        //WHEN
//        Pizza actualPizza = (Pizza) jdbcTemplate.queryForObject(
//                "SELECT * from PIZZA", null, new PizzaRowMapper());
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
        Pizza expectedPizza = testPizza;
        Object[] params = {13, "Oleksandr's", 1230, "SEA"};
        int[] types = {Types.INTEGER, Types.VARCHAR, Types.INTEGER, Types.VARCHAR};

        jdbcTemplate.update("INSERT INTO pizza (id, name, price, type) VALUES" +
                "(?,?,?,?)", params, types);
        //WHEN
        Pizza actualPizza = pizzaRepository.find(13L);

        //THEN
        assertThat(actualPizza, is(expectedPizza));

    }

    @Test
    public void read() throws Exception {

    }

    @Test
    public void findAll() throws Exception {

    }

    @Test
    public void test2() throws Exception {
//        jdbcTemplate

        assertNotNull(12);
    }
}

package repository;

import domain.Pizza;
import infrastructure.RepoTestConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class JPAPizzaRepoTestIT extends RepoTestConfig {
    @Autowired
    private PizzaRepository pizzaRepository;

    @Before
    public void clearTable(){
        clearAllTables();
    }

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void testSave() throws Exception {
        //GIVEN
        //WHEN
        Pizza actualPizza = pizzaRepository.save(testPizza1);
        Pizza expectedPizza = getExpectedPizza(actualPizza.getPizzaId());

        //THEN
        assertThat(actualPizza, is(expectedPizza));
        assertNotNull(actualPizza);
    }

    @Test
    public void testFind() throws Exception {
        insertPizza(testPizza1);
        //WHEN
        Pizza actualPizza = pizzaRepository.find(testPizza1.getPizzaId());
        //THEN
        assertThat(actualPizza, is(testPizza1));
    }
}

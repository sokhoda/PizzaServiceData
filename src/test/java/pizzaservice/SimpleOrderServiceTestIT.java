package pizzaservice;

import domain.Order;
import domain.Pizza;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import infrastructure.RepoTestConfig;

import static org.junit.Assert.*;

public class SimpleOrderServiceTestIT extends RepoTestConfig{
    @Autowired
    private OrderService orderService;
    @Autowired
    private PizzaService pizzaService;


    @Test
    @Ignore
    public void placeNewOrder() throws Exception {

    }


    @Test
    @Ignore
    public void findByCustomer() throws Exception {

    }

    @After
    public  void clearTable(){
         jdbcTemplate.update("DELETE FROM PIZZA", new Object[]{});
    }



    @Test
    public void save() throws Exception {
        Pizza pizza = pizzaService.save(testPizza1);
        Order actualOrder = orderService.save(expectedOrder);

        assertNotNull(actualOrder.getId());
        assertNotNull(actualOrder.getCustomer());
    }

    @Test
    public void find() throws Exception {
        System.out.println("FIND:!!!!!!!!!!!!!!!!!!!!!!!\n" + orderService.find(1L));
    }

}

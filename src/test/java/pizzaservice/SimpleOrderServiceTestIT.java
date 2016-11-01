package pizzaservice;

import domain.*;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import infrastructure.RepoTestConfig;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pizzaservice.states.StateEn;

import java.sql.Types;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class SimpleOrderServiceTestIT extends RepoTestConfig{
    @Autowired
    private OrderService orderService;
    @Autowired
    private PizzaService pizzaService;
@Autowired
private CustomerService customerService;

    @Test
    @Ignore
    public void placeNewOrder() throws Exception {

    }


    @Test
    public void findByCustomer() throws Exception {

        Pizza pizza = pizzaService.save(testPizza1);
        Order actualOrder = orderService.save(expectedOrder);

        //WHEN
        List<Order> actualCustomer = orderService.findByCustomer(testCustomer);

        //THEN
        assertThat(actualCustomer.get(0), is(testCustomer));


    }

    @Before
    public  void clearTable(){
         jdbcTemplate.update("DELETE FROM PIZZA", new Object[]{});
         jdbcTemplate.update("DELETE FROM CUSTOMER", new Object[]{});
         jdbcTemplate.update("DELETE FROM ADDRESS", new Object[]{});
         jdbcTemplate.update("DELETE FROM TB_ORDER", new Object[]{});
//        System.out.println(pizzaService.findAll());
    }

    @Test
    public void save() throws Exception {
        Pizza pizza = pizzaService.save(testPizza1);
        Order actualOrder = orderService.save(expectedOrder);

        assertNotNull(actualOrder.getId());
        assertNotNull(actualOrder.getCustomer());
    }

    @Test
    @Transactional(noRollbackFor = Exception.class)
    public void find() throws Exception {
//        Pizza pizza = pizzaService.save(testPizza1);
//        Order order = orderService.save(expectedOrder);

        insertCustomer();
        insertAddress();

        insertPizza();

        insertTBOrder();

        insertPizza_Quant();
        System.out.println(customerService.find(1L));
        System.out.println(pizzaService.find(1L));
        //WHEN
//        Order actualOrder = orderService.find(order.getId());

        //THEN
//        assertTrue(actualOrder.equals(expectedOrder));
//        assertThat(actualOrder, is(expectedOrder));

    }



    private Long insertPizza_Quant() {
        Long id = 1L;  //ord_ID
        Object[] params = {id, 1, 1L};
        int[] types = {Types.INTEGER, Types.INTEGER, Types.INTEGER};

        jdbcTemplate.update("INSERT INTO PIZZA_QUANT (Ord_id, quantity, Pizza_ID) VALUES" +
                "(?,?,?)", params, types);
        return id;
    }
    private Long insertTBOrder() {
        Long id = 1L;   //order_ID
        Object[] params = {id, StateEn.NEW, null, 1L};
        int[] types = {Types.INTEGER, Types.VARCHAR, Types.INTEGER, Types.INTEGER};

        jdbcTemplate.update("INSERT INTO TB_ORDER (id, curState, Cheque_ID, Cust_ID) VALUES" +
                "(?,?,?,?)", params, types);
        return id;
    }

    private Long insertPizza() {
        Long id = 1L;
        Object[] params = {id, "Tomato", 90, PizzaType.VEGETERIAN};
        int[] types = {Types.INTEGER, Types.VARCHAR, Types.INTEGER, Types.VARCHAR};

        jdbcTemplate.update("INSERT INTO pizza (id, name, price, type) VALUES" +
                "(?,?,?,?)", params, types);
        return id;
    }


    private Long insertCustomer() {
        Long id = 1L;
        Object[] params = {id, testCustomerName,  null};
        int[] types = {Types.INTEGER, Types.VARCHAR, Types.INTEGER};

        jdbcTemplate.update("INSERT INTO Customer (id, name, loyalCard_ID) VALUES" +
                "(?,?,?)", params, types);
        return id;
    }

    private Long insertAddress() {
        Long id = 1L;
        Object[] params = {id, "03004", "Kyiv", "CustomStreetName", "Str",
                "18", "2", 1L};
        int[] types = {Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types
                .VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types
                .INTEGER};

        jdbcTemplate.update("INSERT INTO Address (id, zipCode, city, strName, type, appNo, buildingNo, Cust_ID) VALUES" +
                "(?, ?, ?, ?, ?, ?, ?, ?)", params, types);
        return id;
    }

}

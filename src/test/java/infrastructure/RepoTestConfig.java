package infrastructure;

import domain.*;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pizzaservice.states.NewState;
import pizzaservice.states.OrderStateCycle;
import pizzaservice.states.StateEn;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:/inMemoryRepoContextH2.xml"
})
@Rollback(value = false)
public class RepoTestConfig extends AbstractTransactionalJUnit4SpringContextTests{

    protected final Pizza testPizza1 = new Pizza(1L, "Tomato", 90., PizzaType
            .VEGETERIAN);
    protected final Pizza testPizza2 = new Pizza(2L, "Chicken", 120., PizzaType
            .MEAT);
    protected final Map<Pizza, Integer> pizzaMap = new HashMap<>();
    protected Customer testCustomer;
    protected Orders expectedOrder;
    protected LoyaltyCard testLoyaltyCard;
    protected Address testAddress;
    protected String testCustomerName;

    {
        initTestLoyaltyCard();
        initTestCustomer();
        initExpectedOrder();
    }

    private void initTestCustomer() {
        testCustomerName = "Alex";
        testCustomer = new Customer(testCustomerName,  testLoyaltyCard);
        testAddress = new Address("03004", "Kyiv", "CustomStreetName", "Str",
                "18", "2", testCustomer);
        testAddress.setId(1L);
        testCustomer.getAddress().add(testAddress);
        testCustomer.setId(1L);
    }

    private void initTestLoyaltyCard() {
        testLoyaltyCard = new LoyaltyCard(0.);
    }

    private void initExpectedOrder() {
        pizzaMap.put(testPizza1, 1);
        OrderStateCycle orderStateCycle = new OrderStateCycle();
        orderStateCycle.setCurState(new NewState());
        expectedOrder = new Orders(testCustomer, pizzaMap, orderStateCycle);
    }



    public void insertOrder() {
        insertLoyaltyCard();
        insertCustomer();
        insertAddress();
        insertPizza();
        insertTBOrder();
        insertPizza_Quant();
    }


    public Long insertPizza_Quant() {
        Long id = 1L;  //ord_ID
        Object[] params = {id, 1, 1L};
        int[] types = {Types.INTEGER, Types.INTEGER, Types.INTEGER};

        jdbcTemplate.update("INSERT INTO PIZZA_QUANT (Ord_id, quantity, Pizza_ID) VALUES" +
                "(?,?,?)", params, types);
        return id;
    }

    public Long insertTBOrder() {
        Long id = 1L;   //order_ID
        Object[] params = {id, StateEn.NEW, null, 1L};
        int[] types = {Types.INTEGER, Types.VARCHAR, Types.INTEGER, Types.INTEGER};

        jdbcTemplate.update("INSERT INTO TB_ORDER (id, curState, Cheque_ID, Cust_ID) VALUES" +
                "(?,?,?,?)", params, types);
        return id;
    }

    public Long insertPizza() {
        Long id = 1L;
        Object[] params = {id, "Tomato", 90, PizzaType.VEGETERIAN};
        int[] types = {Types.INTEGER, Types.VARCHAR, Types.INTEGER, Types.VARCHAR};

        jdbcTemplate.update("INSERT INTO pizza (id, name, price, type) VALUES" +
                "(?,?,?,?)", params, types);
        return id;
    }

    public Long insertPizza2() {
        Long id = 2L;
        Object[] params = {id, "Chicken", 120., PizzaType.MEAT};
        int[] types = {Types.INTEGER, Types.VARCHAR, Types.INTEGER, Types.VARCHAR};

        jdbcTemplate.update("INSERT INTO pizza (id, name, price, type) VALUES" +
                "(?,?,?,?)", params, types);
        return id;
    }

    public Long insertLoyaltyCard() {
        Long id = 1L;
        Object[] params = {id, 0.};
        int[] types = {Types.INTEGER, Types.DOUBLE};

        jdbcTemplate.update("INSERT INTO LoyaltyCard (id, sum) VALUES" +
                "(?,?)", params, types);
        return id;
    }

    public Long insertCustomer() {
        Long id = 1L;
        Object[] params = {id, testCustomerName, 1L};
        int[] types = {Types.INTEGER, Types.VARCHAR, Types.INTEGER};

        jdbcTemplate.update("INSERT INTO Customer (id, name, loyalCard_ID) VALUES" +
                "(?,?,?)", params, types);
        return id;
    }

    public Long insertAddress() {
        Long id = 1L;
        Object[] params = {id, "03004", "Kyiv", "CustomStreetName", "Str",
                "18", "2", 1L};
        int[] types = {Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types
                .VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types
                .INTEGER};

        jdbcTemplate.update("INSERT INTO Address (id, zipCode, city, strName, type, buildingNo, appNo,  Cust_ID) VALUES" +
                "(?, ?, ?, ?, ?, ?, ?, ?)", params, types);
        return id;
    }

    public void clearAllTables() {
        jdbcTemplate.update("DELETE FROM address", new Object[]{});
        jdbcTemplate.update("DELETE FROM discountrecord", new Object[]{});
        jdbcTemplate.update("DELETE FROM tb_order", new Object[]{});
        jdbcTemplate.update("DELETE FROM pizza", new Object[]{});
        jdbcTemplate.update("DELETE FROM customer", new Object[]{});
        jdbcTemplate.update("DELETE FROM cheque", new Object[]{});
        jdbcTemplate.update("DELETE FROM loyaltycard", new Object[]{});
        jdbcTemplate.update("DELETE FROM pizza_quant", new Object[]{});
    }

}

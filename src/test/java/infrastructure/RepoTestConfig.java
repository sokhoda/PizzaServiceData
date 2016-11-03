package infrastructure;

import domain.*;
import org.junit.runner.RunWith;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pizzaservice.states.NewState;
import pizzaservice.states.OrderStateCycle;
import pizzaservice.states.StateEn;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:/inMemoryRepoContextH2.xml"
})
@Rollback(value = false)
public class RepoTestConfig extends AbstractTransactionalJUnit4SpringContextTests {

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
        testCustomer = new Customer(testCustomerName, testLoyaltyCard);
        testCustomer.setId(1L);
        testAddress = new Address("03004", "Kyiv", "CustomStreetName", "Str",
                "18", "2", testCustomer);
        testAddress.setId(1L);
        testCustomer.getAddress().add(testAddress);
    }

    private void initTestLoyaltyCard() {
        testLoyaltyCard = new LoyaltyCard(0.);
        testLoyaltyCard.setId(1L);
    }

    private void initExpectedOrder() {
        pizzaMap.put(testPizza1, 1);
        OrderStateCycle orderStateCycle = new OrderStateCycle();
        orderStateCycle.setCurState(new NewState());
        expectedOrder = new Orders(testCustomer, pizzaMap, orderStateCycle);
    }


    public void insertOrder() {
        insertLoyaltyCard(testLoyaltyCard);
        insertCustomer(testCustomer);
        insertAddress(testAddress);
        insertPizza(testPizza1);
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

    public Long insertPizza(Pizza testPizza) {
        Long id = null;
        if (testPizza != null) {
            id = testPizza.getPizzaId();
            Object[] params = {id, testPizza.getName(), testPizza.getPrice(),
                    testPizza.getType()};
            int[] types = {Types.INTEGER, Types.VARCHAR, Types.INTEGER, Types.VARCHAR};

            jdbcTemplate.update("INSERT INTO pizza (id, name, price, type) VALUES" +
                    "(?,?,?,?)", params, types);
        }
        return id;
    }

    public Long insertLoyaltyCard(LoyaltyCard testLoyaltyCard) {
        Long id = null;
        if (testLoyaltyCard != null) {
            id = testLoyaltyCard.getId();
            Object[] params = {id, 0.};
            int[] types = {Types.INTEGER, Types.DOUBLE};

            jdbcTemplate.update("INSERT INTO LoyaltyCard (id, sum) VALUES" +
                    "(?,?)", params, types);
        }
        return id;
    }

    public Long insertCustomer(Customer testCustomer) {
        Long id = null;
        if (testCustomer != null) {
            id = testCustomer.getId();
            Object[] params = {id, testCustomer.getName(), insertLoyaltyCard(testCustomer.getLoyaltyCard())};
            int[] types = {Types.INTEGER, Types.VARCHAR, Types.INTEGER};

            jdbcTemplate.update("INSERT INTO Customer (id, name, loyalCard_ID) VALUES" +
                    "(?,?,?)", params, types);
        }
        return id;
    }


    public Long insertAddress(Address testAddress) {
        Long id = null;
        if (testAddress != null) {
            id = testAddress.getId();
            Object[] params = {id, testAddress.getZipCode(), testAddress.getCity(),
                    testAddress.getStrName(), testAddress.getType(),
                    testAddress.getBuildingNo(), testAddress.getAppNo(),
                    testAddress.getCustomer().getId()};
            int[] types = {Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types
                    .VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types
                    .INTEGER};

            jdbcTemplate.update("INSERT INTO Address (id, zipCode, city, strName, type, buildingNo, appNo,  Cust_ID) VALUES" +
                    "(?, ?, ?, ?, ?, ?, ?, ?)", params, types);
        }
        return id;
    }

    public void clearAllTables() {
        logger.info("DELETING TABLES");
        jdbcTemplate.update("DELETE FROM address", new Object[]{});
        jdbcTemplate.update("DELETE FROM discountrecord", new Object[]{});
        jdbcTemplate.update("DELETE FROM pizza_quant", new Object[]{});
        jdbcTemplate.update("DELETE FROM pizza", new Object[]{});
        jdbcTemplate.update("DELETE FROM tb_order", new Object[]{});
        jdbcTemplate.update("DELETE FROM customer", new Object[]{});
        jdbcTemplate.update("DELETE FROM cheque", new Object[]{});
        jdbcTemplate.update("DELETE FROM loyaltycard", new Object[]{});
    }

    public Pizza getExpectedPizza(Long id) {
        Object[] params = {id};
        int[] types = {Types.INTEGER};
        return (Pizza) jdbcTemplate.queryForObject("SELECT * " +
                "FROM PIZZA WHERE id = ?", params, types, new PizzaRowMapper());
    }

    public List<Pizza> getExpectedPizzaList() {
        return jdbcTemplate.query("SELECT * FROM PIZZA ", new PizzaRowMapper());
    }

    public Customer getExpectedCustomer(Long id) {
        Customer customer = null;
        Object[] params = {id};
        int[] types = {Types.INTEGER};
        try {
            customer = (Customer) jdbcTemplate.queryForObject(
                    "SELECT c.*, lc.sum FROM customer c " +
                            "JOIN loyaltycard lc " +
                            "ON lc.id = c.LoyalCard_ID " +
                            "WHERE c.id = ?", params, types, new
                            CustomerRowMapper());
        }catch (DataAccessException ex) {
        }
        return customer;
    }


}

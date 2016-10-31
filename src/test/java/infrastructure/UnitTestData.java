package infrastructure;

import domain.*;
import pizzaservice.states.OrderStateCycle;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class UnitTestData {
    protected final Pizza testPizza1 = new Pizza(1L, "Tomato", 90., PizzaType
            .VEGETERIAN);
    protected final Pizza testPizza2 = new Pizza(2L, "Chicken", 120., PizzaType
            .MEAT);
    protected final Map<Pizza, Integer> pizzaMap = new HashMap<>();
    protected Customer testCustomer;
    protected Order expectedOrder;
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
        testAddress = new Address("03004", "Kyiv", "CustomStreetName", "Str",
                "18", "2");
        testCustomer = new Customer(testCustomerName,  testAddress,
                testLoyaltyCard);
    }

    private void initTestLoyaltyCard() {
        testLoyaltyCard = new LoyaltyCard(0.);
    }

    private void initExpectedOrder() {
        pizzaMap.put(testPizza1, 1);
        expectedOrder = new Order(testCustomer, pizzaMap, new OrderStateCycle
                ());
    }
}

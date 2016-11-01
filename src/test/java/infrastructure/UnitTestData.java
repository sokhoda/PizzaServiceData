package infrastructure;

import domain.*;
import pizzaservice.states.NewState;
import pizzaservice.states.OrderStateCycle;

import java.util.HashMap;
import java.util.Map;

public class UnitTestData {
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
}

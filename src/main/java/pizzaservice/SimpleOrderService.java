package pizzaservice;

import domain.Customer;
import domain.Order;
import domain.Pizza;
import domain.PizzaType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleOrderService {
    private static final List<Pizza> pizzaList = new ArrayList<>(Arrays.asList(
            new Pizza(1L,"Tomato", BigDecimal.valueOf(90), PizzaType
                    .VEGETERIAN),
            new Pizza(2L,"Chicken", BigDecimal.valueOf(120), PizzaType.MEAT),
            new Pizza(3L,"Fish", BigDecimal.valueOf(220), PizzaType.SEA)));

    private static final  List<Order> orderList = new ArrayList<>();

    public Order placeNewOrder(Customer customer, Long ... pizzasID) {
        List<Pizza> pizzas = new ArrayList<>();

        for(Long id : pizzasID){
            pizzas.add(getPizzaByID(id));  // get Pizza from predifined in-memory list
        }
        Order newOrder = new Order(customer, pizzas);

        saveOrder(newOrder);  // set Order Id and save Order to in-memory list
        return newOrder;
    }

    private void saveOrder(Order newOrder) {
        orderList.add(newOrder);
    }


    public Pizza getPizzaByID(Long id){
        int i = 0;
        while (i < pizzaList.size() && !pizzaList.get(i).getId().equals(id)) {
            i++;
        }

        return i < pizzaList.size() ? pizzaList.get(i) : null;
    }

    public static List<Pizza> getPizzaList() {
        return pizzaList;
    }
}

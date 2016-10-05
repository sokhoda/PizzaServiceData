package pizzaservice;

import domain.Customer;
import domain.Order;
import domain.Pizza;
import repository.InMemOrderRepo;
import repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

public class SimpleOrderService implements OrderService{
    private PizzaService pizzaService = null;
    private OrderRepository orderRepo = null;


    public SimpleOrderService(PizzaService pizzaService,
                              OrderRepository orderRepo) {
        this.pizzaService = pizzaService;
        this.orderRepo = orderRepo;
    }

    @Override
    public Order placeNewOrder(Customer customer, Long... pizzasID) {
        List<Pizza> pizzas = new ArrayList<>();

        for(Long id : pizzasID){
            pizzas.add(getPizzaByID(id));
        }
        Order newOrder = new Order(customer, pizzas);

        saveOrder(newOrder);
        return newOrder;
    }

    private Order saveOrder(Order newOrder) {
        return orderRepo. saveOrder(newOrder);
    }

    private Pizza getPizzaByID(Long id) {
        return pizzaService.find(id);
    }


}

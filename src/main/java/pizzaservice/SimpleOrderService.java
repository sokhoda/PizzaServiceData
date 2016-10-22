package pizzaservice;

import domain.Customer;
import domain.Order;
import domain.Pizza;
import infrastructure.Benchmark;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import repository.OrderRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleOrderService implements OrderService, InitializingBean, DisposableBean {
    public static final int DISCOUNT_THRESHOLD = 4;
    public static final int DISCOUNT_MOST_EXPENS_PIZZA_PERCENTAGE = 30;
    public static final int DISCOUNT_MAX_ORDER_SUM_PERCENTAGE = 30;
    public static final int DISCOUNT_LOYALTY_CARD_SUM_PERCENTAGE = 10;
    private PizzaService pizzaService = null;
    private OrderRepository orderRepo = null;

    public SimpleOrderService() {
    }

    @Autowired
    public SimpleOrderService(PizzaService pizzaService,
                              OrderRepository orderRepo) {
        this.pizzaService = pizzaService;
        this.orderRepo = orderRepo;
    }

    @Benchmark(on = true)
    @Override
    public Order placeNewOrder(Customer customer, Long... pizzasID) {
        Map<Pizza, Integer> pizzaMap = new HashMap<>();
        Pizza pizza;
        for (Long id : pizzasID) {
            pizza = getPizzaByID(id);
            if (pizza != null) {
                pizzaMap.put(pizza, 1);
            }
        }
        Order newOrder = createNewOrder();
        newOrder.setCustomer(customer);
        newOrder.setPizzaMap(pizzaMap);
//        newOrder.setOrderStateCycle(new OrderStateCycle());
//                new Order(customer, pizzas, new OrderStateCycle());
        saveOrder(newOrder);
        return newOrder;
    }


    //    private Order createNewOrder() {
//        return (Order)applicationContext.getBean("order");
//    }
    Order createNewOrder() {
        throw new IllegalStateException("Container couldnt");
    }

    @Override
    public Order addPizzas(Order order, Long... idNoPair) {
        Long id = null;
        Integer quantity = 0;
        Pizza currentPizza = null;
        if (order != null) {
            Map<Pizza, Integer> pizzaMap = order.getPizzaMap();
            try {
                for (int i = 0; i < idNoPair.length; i = i + 2) {
                    id = idNoPair[i];
                    quantity = idNoPair[i + 1].intValue();
                    currentPizza = getPizzaByID(id);
                    if (currentPizza != null) {
                        pizzaMap.put(currentPizza, quantity);
                    }
                }
            }
            catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        return order;
    }

    private Order saveOrder(Order newOrder) {
        return orderRepo.save(newOrder);
    }

    private Pizza getPizzaByID(Long id) {
        Pizza result = null;
        if (id != null) {
            result = pizzaService.find(id);
        }
        return result;
    }

    @Override
    public String toString() {
        return "SimpleOrderService{" +
                "orderRepo=" + orderRepo +
                ", pizzaService=" + pizzaService +
                '}';
    }

    @Override
    public void afterPropertiesSet() throws Exception {
//        System.out.println(this.getClass().getCanonicalName() + " " +
//                "afterPropertiesSet\n toString()=" + toString() );
    }

    @PostConstruct
    public void doMyPostConstruct(){
//        System.out.println(this.getClass().getCanonicalName() + " " +
//                "doMyPostConstruct()");
    }

    @Override
    public void destroy() throws Exception {
//        System.out.println(this.getClass().getCanonicalName() + " destroy\n " +
//                "toString()= " + toString());
    }
}

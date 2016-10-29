package pizzaservice;

import domain.Customer;
import domain.Order;
import domain.Pizza;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import pizzaservice.states.OrderStateCycle;
import repository.OrderRepository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleOrderService implements OrderService {
    public static final int DISCOUNT_THRESHOLD = 4;
    public static final int DISCOUNT_MOST_EXPENS_PIZZA_PERCENTAGE = 30;
    public static final int DISCOUNT_MAX_ORDER_SUM_PERCENTAGE = 30;
    public static final int DISCOUNT_LOYALTY_CARD_SUM_PERCENTAGE = 10;

    @Autowired
    private PizzaService pizzaService = null;
    @Autowired
    @Qualifier(value = "orderRepository")
    private OrderRepository orderRepo = null;

    @Autowired
    private OrderStateCycle orderStateCycle;

    public SimpleOrderService() {
    }

    public SimpleOrderService(PizzaService pizzaService,
                              OrderRepository orderRepo) {
        this.pizzaService = pizzaService;
        this.orderRepo = orderRepo;
    }



    @Transactional
    @Override
    public Order placeNewOrder(Customer customer, Long... pizzasID) {
        Map<Pizza, Integer> pizzaMap = new HashMap<>();
        Pizza pizza;
        for (Long id : pizzasID) {
            pizza = pizzaService.find(id);
            if (pizza != null) {
                pizzaMap.put(pizza, 1);
            }
        }
        Order newOrder = createNewOrder();
        newOrder.setCustomer(customer);
        newOrder.setPizzaMap(pizzaMap);
        newOrder.setOrderStateCycle(createNewOrderStateCycle());
//        newOrder.setOrderStateCycle(new OrderStateCycle());
//                new Order(customer, pizzas, new OrderStateCycle());

        return save(newOrder);
    }


    Order createNewOrder() {
        throw new IllegalStateException("Container couldn't create Proxy");
    }

    OrderStateCycle createNewOrderStateCycle() {
        throw new IllegalStateException("Container couldn't create Proxy");
    }

    @Transactional
    @Override
    public Order addPizzas(final Order order, final Long... idNoPair) {
        Order resultOrder = null;
        Pizza currentPizza = null;
        if (order != null) {
            Map<Pizza, Integer> pizzaMap = order.getPizzaMap();
            try {
                for (int i = 0; i < idNoPair.length; i = i + 2) {
                    currentPizza = pizzaService.find(idNoPair[i]);
                    if (currentPizza != null) {
                        Integer curNumber = pizzaMap.get(currentPizza);
                        pizzaMap.put(currentPizza, idNoPair[i + 1].intValue()
                                + (curNumber == null ? 0 : curNumber));
                    }
                }
                resultOrder = save(order);
            }
            catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        return resultOrder;
    }

    @Override
    public Order find(Long id) {
        return orderRepo.find(id);
    }
//    @Override
//    public Order find(Long id) {
//        Order order = orderRepo.find(id);
//        OrderStateCycle orderStateCycle1 = orderStateCycle;
//        orderStateCycle1.getCurState().setName(order.getState());
//        order.setOrderStateCycle(orderStateCycle1);
//        return order;
//    }

    @Override
    public List<Order> findByCustomer(Customer customer) {
        return orderRepo.findByCustomer(customer);
    }

//    @Transactional
//    @Override
//    public Order save(Order order) {
//        if (order != null){
//            OrderStateCycle orderStateCycle = order.getOrderStateCycle();
//            order.setState(orderStateCycle.getCurState().getName());
//            order = orderRepo.save(order);
//            order.setOrderStateCycle(orderStateCycle);
//        }
//        return order;
//    }

    @Transactional
    @Override
    public Order save(Order order){
        return orderRepo.save(order);
    }

    @Override
    public String toString() {
        return "SimpleOrderService{" +
                "orderRepo=" + orderRepo +
                ", pizzaService=" + pizzaService +
                '}';
    }
}

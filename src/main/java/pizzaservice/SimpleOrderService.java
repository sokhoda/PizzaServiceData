package pizzaservice;

import domain.Customer;
import domain.LoyaltyCard;
import domain.Order;
import domain.Pizza;
import infrastructure.Benchmark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import pizzaservice.states.OrderStateCycle;
import repository.InMemOrderRepo;
import repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class SimpleOrderService implements OrderService, ApplicationContextAware {
    public static final int DISCOUNT_THRESHOLD = 4;
    public static final int DISCOUNT_MOST_EXPENS_PIZZA_PERCENTAGE = 30;
    public static final int DISCOUNT_MAX_ORDER_SUM_PERCENTAGE = 30;
    public static final int DISCOUNT_LOYALTY_CARD_SUM_PERCENTAGE = 10;
    private PizzaService pizzaService = null;
    private OrderRepository orderRepo = null;
    private ApplicationContext applicationContext;

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
        List<Pizza> pizzas = new ArrayList<>();

        for (Long id : pizzasID) {
            pizzas.add(getPizzaByID(id));
        }
        Order newOrder = createNewOrder();
        newOrder.setCustomer(customer);
        newOrder.setPizzaList(pizzas);
        newOrder.setOrderStateCycle(new OrderStateCycle());
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
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Order addPizzas(Order order, Long... idNoPair) {
        Long id = null;
        int quantity = 0;
        if (order != null) {
            List<Pizza> pizzas = order.getPizzaList();
            try {
                for (int i = 0; i < idNoPair.length; i = i + 2) {
                    quantity = idNoPair[i + 1].intValue();
                    id = idNoPair[i];
                    for (int k = 0; k < quantity; k++) {
                        pizzas.add(getPizzaByID(id));
                    }
                }
            }
            catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        return order;
    }

    @Override
    public Double calcDiscountByPizzaQuantity(Order order) {
        Double result = 0.;
        if (order.getPizzaList().size() > DISCOUNT_THRESHOLD) {
            result = order.getTheMostExpensivePizza().getPrice() *
                    DISCOUNT_MOST_EXPENS_PIZZA_PERCENTAGE / 100.;
        }
        return result;
    }

    @Override
    public Double calcDiscountByLCardPercentage(Order order) {
        Double result = 0.;
        LoyaltyCard loyaltyCard = order.getCustomer().getLoyaltyCard();
        if (loyaltyCard != null) {
            Double discount1 = order.calcTotalSum() *
                    DISCOUNT_MAX_ORDER_SUM_PERCENTAGE / 100.;
            Double discount2 = loyaltyCard.getSum() * DISCOUNT_LOYALTY_CARD_SUM_PERCENTAGE / 100.;
            result = discount2 > discount1 ? discount1 : discount2;
        }
        return result;
    }


    private Order saveOrder(Order newOrder) {
        return orderRepo.saveOrder(newOrder);
    }

    private Pizza getPizzaByID(Long id) {
        return pizzaService.find(id);
    }


}

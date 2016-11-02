package pizzaservice;

import domain.Customer;
import domain.Orders;
import domain.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pizzaservice.states.OrderStateCycle;
import repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("orderService")
public class SimpleOrderService implements OrderService {
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

    @Override
    public void addTotalSumToCustomerLCard(Orders order) {
        if (order != null) {
            order.addTotalSumToCustomerLCard();
        }
    }

    @Transactional
    @Override
    public Orders placeNewOrder(Customer customer, Long... pizzasID) {
        Map<Pizza, Integer> pizzaMap = new HashMap<>();
        Pizza pizza;
        for (Long id : pizzasID) {
            pizza = pizzaService.find(id);
            if (pizza != null) {
                pizzaMap.put(pizza, 1);
            }
        }
        Orders newOrder = createNewOrder();
        newOrder.setCustomer(customer);
        newOrder.setPizzaMap(pizzaMap);
        newOrder.setOrderStateCycle(createNewOrderStateCycle());

        return save(newOrder);
    }


    Orders createNewOrder() {
        throw new IllegalStateException("Container couldn't create Proxy");
    }

    OrderStateCycle createNewOrderStateCycle() {
        throw new IllegalStateException("Container couldn't create Proxy");
    }

    @Transactional
    @Override
    public Orders addPizzas(final Orders order, final Long... idNoPair) {
        Orders resultOrder = null;
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
    public Orders find(Long id) {
        return orderRepo.find(id);
    }

    @Override
    public List<Orders> findByCustomer(Customer customer) {
        return orderRepo.findByCustomer(customer);
    }

    @Override
    public List<Orders> findByDateBetween(LocalDateTime fromDate, LocalDateTime toDate){
        return  orderRepo.findByDateBetween(fromDate, toDate);
    }


    @Transactional
    @Override
    public Orders save(Orders order) {
        return orderRepo.save(order);
    }

    @Override
    public String toString() {
        return "SimpleOrderService{" +
                "orderRepo=" + orderRepo +
                ", pizzaService=" + pizzaService +
                '}';
    }


    public void setPizzaService(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    public void setOrderRepo(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }
}

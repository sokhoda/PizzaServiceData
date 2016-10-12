package domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pizzaservice.states.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Scope("prototype")
public class Order {
    private static Long counter = 0L;
    private Long id;
    private Customer customer;
    private List<Pizza> pizzaList;
    private OrderStateCycle orderStateCycle;

    public Order() {
    }

    public Order(Customer customer, List<Pizza> pizzaList, OrderStateCycle orderStateCycle) {
        this.id = counter + 1L;
        this.orderStateCycle = orderStateCycle;
        this.customer = customer;
        this.pizzaList = pizzaList;
    }

    public Order(Customer customer, List<Pizza> pizzaList) {
        this(customer, pizzaList, null);
    }

    public Double calcTotalSum() {
        Double sum = 0.;
        for (Pizza pizza : pizzaList) {
            sum += pizza.getPrice();
        }
        return sum;
    }

    public Pizza getTheMostExpensivePizza() {
        List<Pizza> clonePizzas = new ArrayList<>(pizzaList);
        Collections.sort(clonePizzas, (Pizza o1, Pizza o2) -> {
            return Double.compare(o2.getPrice(), o1.getPrice());
        });
        return clonePizzas.get(0);
    }


    public void addTotalSumToCustomerLCard(){
        LoyaltyCard loyaltyCard = customer.getLoyaltyCard();
        if (loyaltyCard != null) {
            loyaltyCard.setSum(loyaltyCard.getSum() + calcTotalSum());
        }
    }

    public State nextState( ){
        return orderStateCycle.nextState();
    }

    public State previousState(){
        return orderStateCycle.previousState();
    }

    public void cancel(){
        orderStateCycle.setCurState(orderStateCycle.getCancelledSt());
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", pizzaList=" + pizzaList +
                ", orderStateCycle=" + orderStateCycle +
                '}';
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Pizza> getPizzaList() {
        return pizzaList;
    }

    public void setPizzaList(List<Pizza> pizzaList) {
        this.pizzaList = pizzaList;
    }

    public OrderStateCycle getOrderStateCycle() {
        return orderStateCycle;
    }

    @Autowired
    public void setOrderStateCycle(OrderStateCycle orderStateCycle) {
        this.orderStateCycle = orderStateCycle;
    }
}

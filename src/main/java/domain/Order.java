package domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pizzaservice.states.*;

import java.util.*;

@Component
@Scope("prototype")
public class Order {
    private static Long counter = 0L;
    private Long id;
    private Long chequeId;
    private Customer customer;
    private Map<Pizza, Integer> pizzaMap;
    private OrderStateCycle orderStateCycle;

    public Order() {
        this.id = ++counter;

    }

    public Order(Long id, Customer customer, Map<Pizza, Integer> pizzaMap) {
        this.id = id;
        this.customer = customer;
        this.pizzaMap = pizzaMap;
    }

    public Order(Customer customer, Map<Pizza, Integer> pizzaMap, OrderStateCycle orderStateCycle) {
        this.id = ++counter;
        this.customer = customer;
        this.pizzaMap = pizzaMap;
        this.orderStateCycle = orderStateCycle;
    }

    public Order(Customer customer, Map<Pizza, Integer> pizzaMap) {
        this(customer, pizzaMap, null);
    }

    public Double calcTotalSum() {
        Double sum = 0.;
        for (Pizza pizza : pizzaMap.keySet()) {
            sum += pizza.getPrice();
        }
        return sum;
    }

    public Pizza getTheMostExpensivePizza() {
        SortedSet<Map.Entry<Pizza, Integer>> pizzaSortedSet = new TreeSet<>(
                (o1, o2) -> {
                    return o2.getValue().compareTo(o1.getValue());
                });
        pizzaSortedSet.addAll(pizzaMap.entrySet());

        return pizzaSortedSet.first().getKey();
    }


    public void addTotalSumToCustomerLCard() {
        LoyaltyCard loyaltyCard = customer.getLoyaltyCard();
        if (loyaltyCard != null) {
            loyaltyCard.setSum(loyaltyCard.getSum() + calcTotalSum());
        }
    }

    public State nextState() {
        return orderStateCycle.nextState();
    }

    public State previousState() {
        return orderStateCycle.previousState();
    }

    public void cancel() {
        orderStateCycle.setCurState(orderStateCycle.getCancelledSt());
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", chequeId=" + chequeId +
                ", customer=" + customer +
                ", pizzaMap=" + pizzaMap +
                ", orderStateCycle=" + orderStateCycle +
                '}';
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (pizzaMap != null ? pizzaMap.hashCode() : 0);
        result = 31 * result + (orderStateCycle != null ? orderStateCycle.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != null ? !id.equals(order.id) : order.id != null) return false;
        if (chequeId != null ? !chequeId.equals(order.chequeId) : order.chequeId != null)
            return false;
        if (customer != null ? !customer.equals(order.customer) : order.customer != null)
            return false;
        if (pizzaMap != null ? !pizzaMap.equals(order.pizzaMap) : order.pizzaMap != null)
            return false;
        return orderStateCycle != null ? orderStateCycle.equals(order.orderStateCycle) : order.orderStateCycle == null;

    }

    public Long getChequeId() {
        return chequeId;
    }

    public void setChequeId(Long chequeId) {
        this.chequeId = chequeId;
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

    public Map<Pizza, Integer> getPizzaMap() {
        return pizzaMap;
    }

    public void setPizzaMap(Map<Pizza, Integer> pizzaMap) {
        this.pizzaMap = pizzaMap;
    }

    public OrderStateCycle getOrderStateCycle() {
        return orderStateCycle;
    }

    @Autowired
    public void setOrderStateCycle(OrderStateCycle orderStateCycle) {
        this.orderStateCycle = orderStateCycle;
    }
}

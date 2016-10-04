package domain;

import java.util.List;

public class Order {
    private static Long counter = -1L;
    private Long id;
    private List<Pizza> pizzaList;
    private Customer customer;

    public Order (Customer customer, List<Pizza> pizzaList) {
        this.id = counter + 1L;
        this.customer = customer;
        this.pizzaList = pizzaList;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", pizzaList=" + pizzaList +
                ", customer=" + customer +
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
}

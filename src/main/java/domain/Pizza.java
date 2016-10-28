package domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Pizza implements Serializable{
    @Id
    @TableGenerator(
            name = "pizzaGen",
            table = "ID_GEN",
            pkColumnName = "GEN_KEY",
            pkColumnValue = "PIZZA_ID",
            valueColumnName = "GEN_VALUE",
            initialValue = 0,
    allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.TABLE, generator = "pizzaGen")
    private Long id;

    private String name;

    private Double price;

    @Enumerated(EnumType.STRING)
    private PizzaType type;

    public Pizza(Long id, String name, Double price, PizzaType type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public Pizza() {
    }

    @Override
    public String toString() {
        return "\nPizza{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pizza pizza = (Pizza) o;

        if (name != null ? !name.equals(pizza.name) : pizza.name != null)
            return false;
        if (price != null ? !price.equals(pizza.price) : pizza.price != null)
            return false;
        return type == pizza.type;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public PizzaType getType() {
        return type;
    }

    public void setType(PizzaType type) {
        this.type = type;
    }
}

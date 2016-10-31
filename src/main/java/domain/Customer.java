package domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Scope("prototype")
@Entity
@NamedQueries({
@NamedQuery(name = "Customer.findByName", query = "SELECT c from Customer c " +
        "WHERE c.name = :name")
})
public class Customer implements Serializable{
    @Id
    @TableGenerator(
            name = "customerGen",
            table = "ID_GEN",
            pkColumnName = "GEN_KEY",
            pkColumnValue = "CUSTOMER_ID",
            valueColumnName = "GEN_VALUE",
            initialValue = 0,
            allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.TABLE, generator = "customerGen")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Address> address = new HashSet<>();

    @OneToOne(orphanRemoval = true, cascade = CascadeType.MERGE)
    @JoinColumn(name = "LoyalCard_ID")
    private LoyaltyCard loyaltyCard;

    public Customer() {
    }

    public Customer(String name, Address address, LoyaltyCard loyaltyCard) {
        this.name = name;
        this.address.add(address);
        this.loyaltyCard = loyaltyCard;
    }

    public Customer(String name, Address address) {
        this(name, address, null);
    }

    public Customer(String name) {
        this(name, null, null);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", loyaltyCard=" + loyaltyCard +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (name != null ? !name.equals(customer.name) : customer.name != null)
            return false;
        if (address != null ? !address.equals(customer.address) : customer.address != null)
            return false;
        return loyaltyCard != null ? loyaltyCard.equals(customer.loyaltyCard) : customer.loyaltyCard == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (loyaltyCard != null ? loyaltyCard.hashCode() : 0);
        return result;
    }

    public Set<Address> getAddress() {
        return address;
    }

    public void setAddress(Set<Address> address) {
        this.address = address;
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

    public LoyaltyCard getLoyaltyCard() {
        return loyaltyCard;
    }

    public void setLoyaltyCard(LoyaltyCard loyaltyCard) {
        this.loyaltyCard = loyaltyCard;
    }

}

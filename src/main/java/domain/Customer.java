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

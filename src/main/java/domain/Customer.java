package domain;

public class Customer {
    private static Long counter = 0L;
    private Long id;
    private String name;
    private Address address;
    private LoyaltyCard loyaltyCard;

    public Customer(String name, Address address, LoyaltyCard loyaltyCard) {
        this.id = counter + 1L;
        this.name = name;
        this.address = address;
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
                ", address='" + address + '\'' +
                '}';
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
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

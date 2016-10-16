package pizzaservice.discount;

public class DiscountRecord {
    protected String name;
    protected Double sum;

    public DiscountRecord(String name, Double sum) {
        this.name = name;
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "\nDiscountRecord{" +
                "name='" + name + '\'' +
                ", sum=" + sum +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }
}

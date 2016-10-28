package domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Component
@Scope("prototype")
@Entity
public class DiscountRecord implements Serializable{
    @Id
    @TableGenerator(
            name = "discountRecGen",
            table = "ID_GEN",
            pkColumnName = "GEN_KEY",
            pkColumnValue = "DISCOUNTREC_ID",
            valueColumnName = "GEN_VALUE",
            initialValue = 0,
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "discountRecGen")
    private Long id;

    private String name;
    private Double sum;
    @ManyToOne
    @JoinColumn(name = "Cheque_ID")
    private Cheque cheque;

    public DiscountRecord() {
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

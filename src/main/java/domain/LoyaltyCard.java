package domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Created by s_okhoda on 10.10.2016.
 */
@Component
@Scope("prototype")
@Entity
public class LoyaltyCard {
    private static Long counter = 0L;
    @Id
    @TableGenerator(
            name = "LOYALTYCARDGen",
            table = "ID_GEN",
            pkColumnName = "GEN_KEY",
            pkColumnValue = "LCARD_ID",
            valueColumnName = "GEN_VALUE",
            initialValue = 0,
            allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.TABLE, generator =
            "LOYALTYCARDGen")
    private Long id;
    private Double sum;

    public LoyaltyCard() {
    }

    public LoyaltyCard(Double sum) {
        this.id = ++counter;
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "LoyaltyCard{" +
                "id=" + id +
                ", sum=" + sum +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }
}

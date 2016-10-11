package domain;

/**
 * Created by s_okhoda on 10.10.2016.
 */
public class LoyaltyCard {
    private static Long counter = 0L;
    private Long id;
    private Double sum;

    public LoyaltyCard(Double sum) {
        this.id = counter + 1L;
        this.sum = sum;
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

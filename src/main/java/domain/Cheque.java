package domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pizzaservice.discount.DiscountRecord;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class Cheque {
    private static final String DEFAULT_TITLE = "Simple Pizza Cheque #";
    private static Long counter = 0L;
    private Long id;
    private String title;
    private LocalDate date;
    private Long orderId;
    private Double totalSum;

    private List<DiscountRecord> discountList = new ArrayList<>();

    public Cheque() {
        this.id = ++counter;
        this.title = DEFAULT_TITLE + id;
        this.date = LocalDate.now();
    }

    public Cheque(String title, LocalDate date, Long orderId) {
        this.id = ++counter;
        this.title = title;
        this.date = date;
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "Cheque{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", discountList=" + discountList +
                ", totalSum=" + totalSum +
                ", dueSum=" + calcDueSum() +
                '}';
    }

    public Double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(Double totalSum) {
        this.totalSum = totalSum;
    }

    public Double calcDueSum(){
        Double discountSum = 0.;
        for (DiscountRecord discountRecord : discountList) {
            discountSum += discountRecord.getSum();
        }
        Double dueSum = totalSum - discountSum;
        return dueSum > 0 ? dueSum : 0.;
    }

    public Cheque(LocalDate date, Long orderId) {
        this(DEFAULT_TITLE, date, orderId);
    }

    public Cheque(Long orderId) {
        this(DEFAULT_TITLE, LocalDate.now(), orderId);
    }

    public static Long getCounter() {
        return counter;
    }

    public static void setCounter(Long counter) {
        Cheque.counter = counter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public List<DiscountRecord> getDiscountList() {
        return discountList;
    }

    public void setDiscountList(List<DiscountRecord> discountList) {
        this.discountList = discountList;
    }


}

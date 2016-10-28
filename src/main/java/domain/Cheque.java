package domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
@Entity
public class Cheque implements Serializable{
    @Transient
    private static final String DEFAULT_TITLE = "Simple Pizza Cheque #";
    @Id
    @TableGenerator(
            name = "chequeGen",
            table = "ID_GEN",
            pkColumnName = "GEN_KEY",
            pkColumnValue = "CHEQUE_ID",
            valueColumnName = "GEN_VALUE",
            initialValue = 0,
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "chequeGen")
    private Long id;

    private String title;

    private LocalDate date;

    private Double totalSum;

    @OneToMany(mappedBy = "cheque", cascade = CascadeType.MERGE, fetch =
            FetchType.EAGER, orphanRemoval = true)
    private List<DiscountRecord> discountList = new ArrayList<>();


    public Cheque() {
        this.title = DEFAULT_TITLE + id;
        this.date = LocalDate.now();
    }

    public Cheque(String title, LocalDate date) {
        this.title = title;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Cheque{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", discountList=" + discountList +
                ", totalSum=" + totalSum +
                ", dueSum=" + calcDueSum() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cheque cheque = (Cheque) o;

        if (id != null ? !id.equals(cheque.id) : cheque.id != null)
            return false;
        if (title != null ? !title.equals(cheque.title) : cheque.title != null)
            return false;
        if (date != null ? !date.equals(cheque.date) : cheque.date != null)
            return false;
        if (totalSum != null ? !totalSum.equals(cheque.totalSum) : cheque.totalSum != null)
            return false;
        return discountList != null ? discountList.equals(cheque.discountList) : cheque.discountList == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (totalSum != null ? totalSum.hashCode() : 0);
        result = 31 * result + (discountList != null ? discountList.hashCode() : 0);
        return result;
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
        if (totalSum != null) {
            Double dueSum = totalSum - discountSum;
            return dueSum > 0 ? dueSum : 0.;
        }
        return discountSum;
    }

    public Cheque(LocalDate date) {
        this(DEFAULT_TITLE, date);
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


    public List<DiscountRecord> getDiscountList() {
        return discountList;
    }

    public void setDiscountList(List<DiscountRecord> discountList) {
        this.discountList = discountList;
    }


}

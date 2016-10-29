package pizzaservice.states;

import infrastructure.Benchmark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.io.Serializable;
//
//@Entity
@Component
@Embeddable @Access(AccessType.FIELD)
public class OrderStateCycle implements Serializable{
//    @Id
//    @TableGenerator(
//            name = "orderStateCycleGen",
//            table = "ID_GEN",
//            pkColumnName = "GEN_KEY",
//            pkColumnValue = "ORDERSTATECYCLE_ID",
//            valueColumnName = "GEN_VALUE",
//            initialValue = 0,
//            allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.TABLE, generator =
//            "orderStateCycleGen")
//    private Long id;

    private static State newSt;

    private static State inProgressSt;

    private static State cancelledSt;

    private static State doneSt;

//    @Enumerated(EnumType.STRING)
    private State curState;

    public OrderStateCycle(State newSt, State inProgressSt, State cancelledSt, State doneSt) {
        this.newSt = newSt;
        this.inProgressSt = inProgressSt;
        this.cancelledSt = cancelledSt;
        this.doneSt = doneSt;
    }

    public OrderStateCycle() {
    }

    @PostConstruct
    public void init() {
        this.curState = newSt;
    }

    public State nextState() {
        return curState.nextState(this);
    }

    public State previousState() {
        return curState.previousState(this);
    }

    @Override
    public String toString() {
        return "OrderStateCycle{" +
                "curState=" + curState +
                ", newSt=" + newSt +
                ", inProgressSt=" + inProgressSt +
                ", cancelledSt=" + cancelledSt +
                ", doneSt=" + doneSt +
                '}';
    }

    //    @Override
//    public String toString() {
//        return "OrderStateCycle{" +
//                "curState=" + curState +
//                '}';
//    }

    public State getCurState() {
        return curState;
    }

    public void setCurState(State curState) {
        this.curState = curState;
    }

    public State getNewSt() {
        return newSt;
    }

    public void setNewSt(State newSt) {
        this.newSt = newSt;
    }

    public State getInProgressSt() {
        return inProgressSt;
    }

    public void setInProgressSt(State inProgressSt) {
        this.inProgressSt = inProgressSt;
    }

    public State getCancelledSt() {
        return cancelledSt;
    }

    public void setCancelledSt(State cancelledSt) {
        this.cancelledSt = cancelledSt;
    }

    public State getDoneSt() {
        return doneSt;
    }

    public void setDoneSt(State doneSt) {
        this.doneSt = doneSt;
    }
}

package pizzaservice.states;

import infrastructure.Benchmark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by s_okhoda on 10.10.2016.
 */

public class OrderStateCycle {
    private State newSt;
    private State inProgressSt;
    private State cancelledSt;
    private State doneSt;
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
                '}';
    }

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

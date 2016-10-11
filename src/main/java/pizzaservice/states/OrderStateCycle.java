package pizzaservice.states;

/**
 * Created by s_okhoda on 10.10.2016.
 */
public class OrderStateCycle {
    private static final State newSt;
    private static final State inProgressSt;
    private static final State cancelledSt;
    private static final State doneSt;
    private State curState;

    static {
        newSt = new NewState();
        inProgressSt = new InProgressState();
        cancelledSt = new CancelledState();
        doneSt = new DoneState();
    }

    public OrderStateCycle() {
        this.curState = newSt;
    }

    public State nextState(){
        return curState.nextState(this);
    }

    public State previousState(){
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

    public static State getCancelledSt() {
        return cancelledSt;
    }

    public static State getNewSt() {
        return newSt;
    }

    public static State getInProgressSt() {
        return inProgressSt;
    }

    public static State getDoneSt() {
        return doneSt;
    }
}

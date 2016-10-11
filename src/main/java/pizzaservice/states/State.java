package pizzaservice.states;

/**
 * Created by s_okhoda on 10.10.2016.
 */
public abstract class State {
    protected StateEn name;

    public abstract State nextState(OrderStateCycle context);
    public abstract State previousState(OrderStateCycle context);

    public State setStateAndReturn(OrderStateCycle context, State state){
        context.setCurState(state);
        return state;
    }

    @Override
    public String toString() {
        return "State{" +
                "name=" + name +
                '}';
    }
}

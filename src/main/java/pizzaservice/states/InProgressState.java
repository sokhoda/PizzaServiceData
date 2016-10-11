package pizzaservice.states;

import repository.OrderRepository;

/**
 * Created by s_okhoda on 10.10.2016.
 */
public class InProgressState extends State {

    public InProgressState() {
        name = StateEn.IN_PROGRESS;
    }

    @Override
    public State nextState(OrderStateCycle context) {
        return setStateAndReturn(context, OrderStateCycle.getDoneSt());
    }

    @Override
    public State previousState(OrderStateCycle context) {
        return setStateAndReturn(context, OrderStateCycle.getNewSt());
    }


}

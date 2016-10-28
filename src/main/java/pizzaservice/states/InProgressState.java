package pizzaservice.states;

import org.springframework.stereotype.Component;
import repository.OrderRepository;

import java.io.Serializable;

/**
 * Created by s_okhoda on 10.10.2016.
 */
@Component
public class InProgressState extends State {

    public InProgressState() {
        name = StateEn.IN_PROGRESS;
    }

    @Override
    public State nextState(OrderStateCycle context) {
        return setStateAndReturn(context, context.getDoneSt());
    }

    @Override
    public State previousState(OrderStateCycle context) {
        return setStateAndReturn(context, context.getNewSt());
    }


}

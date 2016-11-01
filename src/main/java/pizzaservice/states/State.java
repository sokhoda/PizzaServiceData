package pizzaservice.states;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by s_okhoda on 10.10.2016.
 */
@Entity
public abstract class State implements Serializable{
    @Id
    @TableGenerator(
            name = "stateGen",
            table = "ID_GEN",
            pkColumnName = "GEN_KEY",
            pkColumnValue = "state_ID",
            valueColumnName = "GEN_VALUE",
            initialValue = 0,
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator =
            "stateGen")
    private Long id;
    @Enumerated(EnumType.STRING)
    protected StateEn name;

    public abstract State nextState(OrderStateCycle context);
    public abstract State previousState(OrderStateCycle context);

    public State setStateAndReturn(OrderStateCycle context, State state){
        context.setCurState(state);
        return state;
    }

    public StateEn getName() {
        return name;
    }

    public void setName(StateEn name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "State{" +
                "name=" + name +
                '}';
    }
}

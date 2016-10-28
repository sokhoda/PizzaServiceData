package pizzaservice.cheque;

import domain.Order;

public interface ChequeProducer {

    Order placeCheque(Order order);
}

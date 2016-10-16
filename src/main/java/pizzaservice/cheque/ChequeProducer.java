package pizzaservice.cheque;

import domain.Cheque;
import domain.Order;

public interface ChequeProducer {

    Cheque placeCheque(Order order);
}

package pizzaservice.cheque;

import domain.Orders;

public interface ChequeProducer {

    Orders placeCheque(Orders order);
}

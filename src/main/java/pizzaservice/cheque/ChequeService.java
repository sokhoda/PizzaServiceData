package pizzaservice.cheque;

import domain.Cheque;
import domain.Pizza;

public interface ChequeService {

    Cheque save(Cheque newCheque);

    Cheque findById(Long id);

}

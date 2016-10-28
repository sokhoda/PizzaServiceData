package pizzaservice;

import domain.Cheque;

public interface ChequeService {

    Cheque save(Cheque newCheque);

    Cheque find(Long id);

}

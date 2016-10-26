package pizzaservice.cheque;

import domain.Cheque;

public interface ChequeService {

    Cheque save(Cheque newCheque);

    Cheque findById(Long id);

}

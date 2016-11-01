package repository;

import domain.Cheque;

public interface ChequeRepository {
    Cheque find(Long id);

    Cheque save(Cheque newCheque);
}

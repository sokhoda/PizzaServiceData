package repository;

import domain.Cheque;

import java.util.List;

public interface ChequeRepository {
    Cheque find(Long id);

    Cheque save(Cheque newCheque);
}

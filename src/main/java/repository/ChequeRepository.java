package repository;

import domain.Cheque;
import domain.Customer;

import java.util.List;

public interface ChequeRepository {
    Cheque findById(Long id);

    Cheque save(Cheque newCheque);

    List<Cheque> getChequeList();
}

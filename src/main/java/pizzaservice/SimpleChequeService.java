package pizzaservice;

import domain.Cheque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import repository.ChequeRepository;

@Service
public class SimpleChequeService implements ChequeService {
    @Autowired
    @Qualifier("chequeRepository")
    private ChequeRepository chequeRepository;

    public SimpleChequeService() {
    }

    public SimpleChequeService(ChequeRepository chequeRepository) {
        this.chequeRepository = chequeRepository;
    }

    @Override
    public Cheque save(Cheque newCheque) {
        return chequeRepository.save(newCheque);
    }


    @Override
    public Cheque find(Long id) {
        return chequeRepository.find(id);
    }
}

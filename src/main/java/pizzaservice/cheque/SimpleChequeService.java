package pizzaservice.cheque;

import domain.Cheque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ChequeRepository;

@Service
public class SimpleChequeService implements ChequeService {
    private ChequeRepository chequeRepository;

    public SimpleChequeService() {
    }

    @Autowired
    public SimpleChequeService(ChequeRepository chequeRepository) {
        this.chequeRepository = chequeRepository;
    }

    @Override
    public Cheque save(Cheque newCheque) {
        return chequeRepository.save(newCheque);
    }


    @Override
    public Cheque findById(Long id) {
        return chequeRepository.findById(id);
    }
}

package pizzaservice;

import domain.Cheque;
import domain.DiscountRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.DiscountRecordRepository;

import java.util.List;

@Service("discountRecordService")
public class SimpleDiscountRecordService implements DiscountRecordService{

    @Autowired
    private DiscountRecordRepository discountRecordRepository;

    @Override
    public List<DiscountRecord> findByCheque(Cheque cheque) {
        return discountRecordRepository.findByCheque(cheque);
    }

    @Override
    public DiscountRecord find(Long id) {
        return discountRecordRepository.findOne(id);
    }

    @Override
    public DiscountRecord save(DiscountRecord discountRecord) {
        return discountRecordRepository.save(discountRecord);
    }

}

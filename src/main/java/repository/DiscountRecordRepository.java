package repository;

import domain.Cheque;
import domain.DiscountRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DiscountRecordRepository  extends
        JpaRepository<DiscountRecord, Long> {

    List<DiscountRecord> findByCheque(Cheque cheque);

}

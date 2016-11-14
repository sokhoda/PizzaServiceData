package repository;

import domain.LoyaltyCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LoyaltyCardRepository extends JpaRepository<LoyaltyCard,
        Long> {
    @Query(value = "SELECT lo FROM LoyaltyCard lo WHERE lo.sum > ?1")
    Page<LoyaltyCard> findBySumGreaterThan( Double sum,   Pageable pageable);
//    Page<LoyaltyCard> findBySumGreaterThan( Double sum,  String colName, Pageable pageable);
}


//public interface LoyaltyCardRepository extends JpaRepository<LoyaltyCard,
//        Long> {
//    @Query(value = "SELECT lo FROM LoyaltyCard lo WHERE lo.sum > :sum ORDER BY :colName  DESC")
//    List<LoyaltyCard> findBySumGreaterThan(@Param("sum") Double sum, @Param("colName") String
//            colName);
//}

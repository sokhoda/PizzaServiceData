package repository;

import domain.Cheque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ChequeRepository  extends JpaRepository<Cheque, Long> {

}

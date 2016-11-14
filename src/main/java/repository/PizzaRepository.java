package repository;

import domain.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PizzaRepository  extends JpaRepository<Pizza, Long> {

//    Pizza read(Long id);


}

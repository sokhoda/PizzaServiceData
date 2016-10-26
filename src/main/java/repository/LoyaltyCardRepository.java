package repository;

import domain.LoyaltyCard;
import domain.Pizza;

import java.util.List;

public interface LoyaltyCardRepository {

    LoyaltyCard find(Long id);

//    LoyaltyCard read(Long id);

    LoyaltyCard save(LoyaltyCard LoyaltyCard);

}

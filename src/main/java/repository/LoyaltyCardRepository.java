package repository;

import domain.LoyaltyCard;

public interface LoyaltyCardRepository {

    LoyaltyCard find(Long id);

    LoyaltyCard save(LoyaltyCard LoyaltyCard);

}

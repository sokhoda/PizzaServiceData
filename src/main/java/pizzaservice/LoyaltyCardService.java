package pizzaservice;

import domain.LoyaltyCard;

public interface LoyaltyCardService {
    LoyaltyCard find(Long id);

    LoyaltyCard save(LoyaltyCard loyaltyCard);
}
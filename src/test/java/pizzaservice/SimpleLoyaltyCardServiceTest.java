package pizzaservice;

import domain.LoyaltyCard;
import infrastructure.UnitTestData;
import org.junit.Before;
import org.junit.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.LoyaltyCardRepository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class SimpleLoyaltyCardServiceTest extends UnitTestData{
    @Mock
    LoyaltyCardRepository loyaltyCardRepo;

    @InjectMocks
    SimpleLoyaltyCardService sLoyaltyCardService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void save() throws Exception {
//       GIVEN
        given(loyaltyCardRepo.save(any())).will(AdditionalAnswers.returnsFirstArg());
//        WHEN
        LoyaltyCard actualCustomer = sLoyaltyCardService.save(testLoyaltyCard);
//        THEN
        assertThat(actualCustomer, is(testLoyaltyCard));
        verify(loyaltyCardRepo).save(any());
    }

    @Test
    public void find() throws Exception {
//        GIVEN
        given(loyaltyCardRepo.find(1L)).willReturn(testLoyaltyCard);
//        WHEN
        LoyaltyCard actualLoyaltyCard = sLoyaltyCardService.find(1L);
//        THEN
        assertThat(actualLoyaltyCard, is(testLoyaltyCard));
        verify(loyaltyCardRepo).find(1L);
    }


}
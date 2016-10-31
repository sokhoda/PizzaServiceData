package pizzaservice;

import domain.Cheque;
import domain.LoyaltyCard;
import infrastructure.UnitTestData;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.ChequeRepository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class SimpleChequeServiceTest extends UnitTestData{

    @Mock
    private ChequeRepository chequeRepo;

    @InjectMocks
    SimpleChequeService sChequeService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Ignore
    public void save() throws Exception {
//       GIVEN
        given(chequeRepo.save(any())).will(AdditionalAnswers.returnsFirstArg());
//        WHEN
//        Cheque actualCustomer = sChequeService.save(testLoyaltyCard);
//        THEN
//        assertThat(actualCustomer, is(testLoyaltyCard));
        verify(chequeRepo).save(any());
    }

    @Test
    @Ignore
    public void find() throws Exception {
//        GIVEN
//        given(chequeRepo.find(1L)).willReturn(testLoyaltyCard);
//        WHEN
        Cheque actualLoyaltyCard = sChequeService.find(1L);
//        THEN
        assertThat(actualLoyaltyCard, is(testLoyaltyCard));
        verify(chequeRepo).find(1L);
    }

}
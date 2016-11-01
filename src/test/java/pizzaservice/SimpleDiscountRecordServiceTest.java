package pizzaservice;

import domain.Address;
import domain.Cheque;
import domain.DiscountRecord;
import infrastructure.UnitTestData;
import org.junit.Before;
import org.junit.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.AddressRepository;
import repository.DiscountRecordRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class SimpleDiscountRecordServiceTest extends UnitTestData{
    @Mock
    private DiscountRecordRepository discountRecordRepo;
    @InjectMocks
    private SimpleDiscountRecordService sDiscountRecordService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findByCheque() throws Exception {
        List<DiscountRecord> expectedDiscountRecordList = new ArrayList<>(Arrays.asList
                (testDiscountRecord));
//        GIVEN
        Cheque cheque = testCheque;
        given(discountRecordRepo.findByCheque(cheque)).willReturn
                (expectedDiscountRecordList);
//        WHEN
        List<DiscountRecord> actualDiscountRecordList = sDiscountRecordService
                .findByCheque(cheque);
//        THEN
        assertThat(actualDiscountRecordList, is(expectedDiscountRecordList));
        verify(discountRecordRepo).findByCheque(cheque);
    }

    @Test
    public void find() throws Exception {
//        GIVEN
        given(discountRecordRepo.find(1L)).willReturn(testDiscountRecord);
//        WHEN
        DiscountRecord actualDiscountRecord = sDiscountRecordService.find(1L);
//        THEN
        assertThat(actualDiscountRecord, is(testDiscountRecord));
        verify(discountRecordRepo).find(1L);
    }

    @Test
    public void save() throws Exception {
//       GIVEN
        given(discountRecordRepo.save(any())).will(AdditionalAnswers.returnsFirstArg());
//        WHEN
        DiscountRecord actualDiscountRecord = sDiscountRecordService.save(testDiscountRecord);
//        THEN
        assertThat(actualDiscountRecord, is(testDiscountRecord));
        verify(discountRecordRepo).save(any());
    }

}
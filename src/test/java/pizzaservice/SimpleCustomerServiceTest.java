package pizzaservice;

import domain.Customer;
import infrastructure.UnitTestData;
import org.junit.Before;
import org.junit.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.CustomerRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;


public class SimpleCustomerServiceTest extends UnitTestData {
    @Mock
    private CustomerRepository customerRepo;
    @InjectMocks
    private SimpleCustomerService simpleCustomerService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void save() throws Exception {
//       GIVEN
        given(customerRepo.save(any())).will(AdditionalAnswers.returnsFirstArg());
//        WHEN
        Customer actualCustomer = simpleCustomerService.save(testCustomer);
//        THEN
        assertThat(actualCustomer, is(testCustomer));
        verify(customerRepo).save(any());
    }

    @Test
    public void find() throws Exception {
//        GIVEN
        given(customerRepo.find(1L)).willReturn(testCustomer);
//        WHEN
        Customer actualCustomer = simpleCustomerService.find(1L);
//        THEN
        assertThat(actualCustomer, is(testCustomer));
        verify(customerRepo).find(1L);
    }

    @Test
    public void findByName() throws Exception {
        List<Customer> expectedCustomerList = new ArrayList<>(Arrays.asList
                (testCustomer));
        String customerName = testCustomer.getName();
//        GIVEN
        given(customerRepo.findByName(customerName)).willReturn
                (expectedCustomerList);
//        WHEN
        List<Customer> actualCustomerList = simpleCustomerService.findByName
                (customerName);
//        THEN
        assertThat(actualCustomerList, is(expectedCustomerList));
        verify(customerRepo).findByName(customerName);
    }

    @Test
    public void findByLoyaltyCard() throws Exception {
        List<Customer> expectedCustomerList = new ArrayList<>(Arrays
                .asList(testCustomer));
//        GIVEN
        given(customerRepo.findByLoyaltyCard(testLoyaltyCard)).willReturn(expectedCustomerList);
//        WHEN
        List<Customer> actualCustomerList = simpleCustomerService.findByLoyaltyCard(testLoyaltyCard);
//        THEN
        assertThat(actualCustomerList, is(expectedCustomerList));
        verify(customerRepo).findByLoyaltyCard(testLoyaltyCard);
    }

    @Test
    public void placeNewCustomer() throws Exception {
        SimpleCustomerService sCustomerService = spy(SimpleCustomerService.class);
        sCustomerService.setCustomerRepository(customerRepo);
//      GIVEN
        given(customerRepo.save(any())).will(AdditionalAnswers.returnsFirstArg());
//      WHEN
        doReturn(new Customer()).when(sCustomerService).createNewCustomer();
        Customer actualCustomer = sCustomerService.placeNewCustomer(testCustomerName,
                testAddress, testLoyaltyCard);
//      THEN
        assertThat(actualCustomer, is(testCustomer));
        verify(sCustomerService).createNewCustomer();
        verify(customerRepo).save(any());
    }

    @Test(expected = IllegalStateException.class)
    public void createNewCustomer() throws Exception {
        simpleCustomerService.createNewCustomer();
    }
}
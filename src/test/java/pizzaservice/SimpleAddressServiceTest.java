package pizzaservice;

import domain.Address;
import domain.Cheque;
import infrastructure.UnitTestData;
import org.junit.Before;
import org.junit.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.AddressRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class SimpleAddressServiceTest extends UnitTestData{
    @Mock
    private AddressRepository addressRepo;
    @InjectMocks
    private SimpleAddressService sAddressService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void find() throws Exception {
//        GIVEN
        given(addressRepo.find(1L)).willReturn(testAddress);
//        WHEN
        Address actualAddress = sAddressService.find(1L);
//        THEN
        assertThat(actualAddress, is(testAddress));
        verify(addressRepo).find(1L);
    }

    @Test
    public void findByCityName() throws Exception {
        List<Address> expectedAddressList = new ArrayList<>(Arrays.asList
                (testAddress));
//        GIVEN
        String cityName = testAddress.getCity();
        given(addressRepo.findByCityName(cityName)).willReturn(expectedAddressList);
//        WHEN
        List<Address> actualAddressList = sAddressService.findByCityName(cityName);
//        THEN
        assertThat(actualAddressList, is(expectedAddressList));
        verify(addressRepo).findByCityName(cityName);
    }

    @Test
    public void save() throws Exception {
//       GIVEN
        given(addressRepo.save(any())).will(AdditionalAnswers.returnsFirstArg());
//        WHEN
        Address actualAddress = sAddressService.save(testAddress);
//        THEN
        assertThat(actualAddress, is(testAddress));
        verify(addressRepo).save(any());
    }

}
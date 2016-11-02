package pizzaservice;

import domain.Customer;
import infrastructure.RepoTestConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import repository.CustomerRepository;

import static org.junit.Assert.assertNull;

public class SimpleCustomerServiceTestIT extends RepoTestConfig{
    @Autowired
    private CustomerRepository customerRepository;

    @Before
    public void clearTable(){
        clearAllTables();
    }

    @Test
    public void testDelete() throws Exception {
//        GIVEN
        insertCustomer(testCustomer);
//        WHEN
        int deletedCount = customerRepository.delete(testCustomer);
//        THEN
        Customer expectedCulstomer = getExpectedCustomer(testCustomer.getId());
        assertNull(expectedCulstomer);
    }
}

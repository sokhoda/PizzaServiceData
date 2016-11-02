package pizzaservice;

import domain.Orders;
import infrastructure.RepoTestConfig;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SimpleOrderServiceTestIT extends RepoTestConfig {
    @Autowired
    private OrderService orderService;

    @Before
    public void clearTable() {
        clearAllTables();
    }

    @Test
    public void placeNewOrder() throws Exception {
        insertPizza(testPizza1);
//        Orders expectedOrder1 = orderService.save(expectedOrder);
        //WHEN
        Orders actualOrder = orderService.placeNewOrder(testCustomer, 1L);
        //THEN
        assertThat(actualOrder, is(expectedOrder));
    }

    @Test
    public void findByCustomer() throws Exception {
        insertPizza(testPizza1);
        Orders expectedOrder1 = orderService.save(expectedOrder);
        //WHEN
        List<Orders> actualOrder = orderService.findByCustomer(testCustomer);
        //THEN
        assertThat(actualOrder.get(0), is(expectedOrder1));
    }

    @Test
    public void save() throws Exception {
        insertPizza(testPizza1);
//        WHEN
        Orders actualOrder = orderService.save(expectedOrder);
//        THEN
        assertNotNull(actualOrder.getId());
        assertNotNull(actualOrder.getCustomer());
    }

    @Test
    public void find() throws Exception {
        insertOrder();
        //WHEN
        Orders actualOrder = orderService.find(1L);
        //THEN
        assertThat(actualOrder, is(expectedOrder));
    }

    @Test
    @Ignore
    public void testFindByDateBetween() throws Exception {
        LocalDateTime fromDate = LocalDateTime.of(2016, 9, 10, 0, 0);
        LocalDateTime toDate =  LocalDateTime.of(2016, 10, 10, 0, 0);
        List<Orders> orderList = orderService.findByDateBetween(fromDate,
                toDate);
        System.out.println(orderList);
    }
}

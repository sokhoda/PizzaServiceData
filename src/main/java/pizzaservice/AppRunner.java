package pizzaservice;

import domain.Customer;
import domain.Order;

import javax.xml.transform.sax.SAXSource;

public class AppRunner {
    public static void main(String[] args) {
        Customer customer = null;
        Order order;

        SimpleOrderService orderService = new SimpleOrderService();
//        System.out.println(SimpleOrderService.getPizzaList());
        order = orderService.placeNewOrder(customer, 1L, 2L, 3L);
//
        System.out.println(order);
    }

}

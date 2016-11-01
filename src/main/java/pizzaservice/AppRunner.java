package pizzaservice;

import domain.Customer;
import domain.Orders;
import infrastructure.ApplicationContext;
import infrastructure.Context;
import infrastructure.JavaConfig;

public class AppRunner {
    public static void main(String[] args) {
        Customer customer = null;
        Orders order;
//
//        SimpleOrderService orderService = new SimpleOrderService();
////        System.out.println(SimpleOrderService.getCustomerList());
//        order = orderService.placeNewOrder(customer, 1L, 2L, 3L);
////
//        System.out.println(order);
        Context  context = new ApplicationContext(new JavaConfig());

//        PizzaRepository pizzaRepo = context.getBean("pizzaRepository");
//        PizzaRepository pizzaRepo2 = context.getBean("pizzaRepository");
//        System.out.println(pizzaRepo == pizzaRepo2);
//        System.out.println(pizzaRepo.find(1L));

        OrderService orderService = context.getBean("orderService");
//        PizzaRepository pizzaRepository = context.getBean("pizzaRepository");
//        pizzaRepository.find(1L);
        order = orderService.placeNewOrder(customer, 1L, 2L, 3L);
//
        System.out.println(order);
    }

}

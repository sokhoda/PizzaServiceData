package pizzaservice;

import domain.Cheque;
import domain.Customer;
import domain.Orders;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pizzaservice.cheque.ChequeProducer;
import pizzaservice.states.OrderStateCycle;
import repository.PizzaRepository;

import java.util.Arrays;


public class SpringAppRunner {


    public static void main(String[] args) {
        ConfigurableApplicationContext repoContext = new
                ClassPathXmlApplicationContext("repoContext.xml");

        ConfigurableApplicationContext appContext = new
                ClassPathXmlApplicationContext(new String[]
                {"appContext.xml"}, repoContext);

        System.out.print("repoContext::");
        System.out.println(Arrays.toString(repoContext.getBeanDefinitionNames()));
        System.out.print("appContext::");
        System.out.println(Arrays.toString(appContext.getBeanDefinitionNames()));

        CustomerService customerService = appContext.getBean
                ("customerService", CustomerService.class);
        Customer customer = customerService.find(1L);
        System.out.println(customer);

        PizzaRepository pizzaRepository = (PizzaRepository) repoContext
                .getBean("inMemPizzaRepo");
OrderStateCycle orderStateCycle = appContext.getBean("orderStateCycle",
        OrderStateCycle.class);
        System.out.println(orderStateCycle.toString());


        ChequeProducer chequeProducer = appContext.getBean("chequeProducer",
                ChequeProducer.class);
        OrderService orderService =  appContext.getBean("orderService", OrderService.class);
        System.out.println(orderService);
        Orders order = orderService.placeNewOrder(customer, 1L, 2L, 3L);
        order = orderService.addPizzas(order, 1L, 2L);

        order = chequeProducer.placeCheque(order);
        Cheque cheque = order.getCheque();
        System.out.println(order);
        System.out.println(cheque);
        System.out.println(customer);

        System.out.println("\n\n\n----------Order II----------\n");
        order = orderService.placeNewOrder(customer, 2L, 2L, 3L);

        order = chequeProducer.placeCheque(order);
        cheque = order.getCheque();
        System.out.println(order);
        System.out.println(cheque);
        System.out.println(customer);

        ChequeService chequeService = appContext.getBean
                ("simpleChequeService", SimpleChequeService.class);

        System.out.println("found Cheque:\n" + chequeService.find(2L));
//        System.out.println(appContext.getBean("Pizza"));
//        order.nextState();
//        System.out.println(order);
//        order.nextState();
//        order.cancel();
//        System.out.println(order);
//        order.previousState();
//        order.previousState();
//        order.previousState();
//        System.out.println(order);
//        System.out.println(orderService.getClass());

//        System.out.println(repoContext.getBean("T1", SomeService.class).getString());
//        System.out.println(appContext.getBean("T1", SomeService.class).getString());

        repoContext.close();
        appContext.close();
    }

}

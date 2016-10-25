package pizzaservice;

import domain.*;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pizzaservice.cheque.ChequeProducer;
import pizzaservice.cheque.ChequeService;
import pizzaservice.cheque.SimpleChequeService;
import pizzaservice.states.OrderStateCycle;
import repository.JPAPizzaRepo;
import repository.PizzaRepository;

import java.util.Arrays;


public class SpringJPAAppRunner {


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

//        CustomerService customerService = appContext.getBean
//                ("customerService", CustomerService.class);
//        Customer customer = customerService.findById(1L);
//        System.out.println(customer);
//
        PizzaRepository pizzaRepository = (PizzaRepository)appContext.getBean
                ("pizzaRepository");

        Pizza pizza = new Pizza();
        pizza.setName("Customized");
        pizza.setType(PizzaType.MEAT);
        pizza = pizzaRepository.save(pizza);
        System.out.println(pizza);
        System.out.println(pizza.getId());
//OrderStateCycle orderStateCycle = appContext.getBean("orderStateCycle",
//        OrderStateCycle.class);
//        System.out.println(orderStateCycle.toString());
//
//
//        ChequeProducer chequeProducer = appContext.getBean("chequeProducer",
//                ChequeProducer.class);
//        OrderService orderService =  appContext.getBean("orderService", OrderService.class);
//        System.out.println(orderService);
//        Order order = orderService.placeNewOrder(customer, 1L, 2L, 3L);
//        order = orderService.addPizzas(order, 1L, 2L);
//
//        Cheque cheque = chequeProducer.placeCheque(order);
//        System.out.println(order);
//        System.out.println(cheque);
//        System.out.println(customer);
//
//        System.out.println("\n\n\n----------Order II----------\n");
//        order = orderService.placeNewOrder(customer, 2L, 2L, 3L);
//
//        cheque = chequeProducer.placeCheque(order);
//        System.out.println(order);
//        System.out.println(cheque);
//        System.out.println(customer);
//
//        ChequeService chequeService = appContext.getBean
//                ("simpleChequeService", SimpleChequeService.class);
//
//        System.out.println("found Cheque:\n" + chequeService.findById(2L));

        repoContext.close();
        appContext.close();
    }

}

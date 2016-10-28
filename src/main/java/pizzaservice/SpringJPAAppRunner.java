package pizzaservice;

import domain.*;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pizzaservice.cheque.ChequeProducer;
import pizzaservice.states.OrderStateCycle;
import repository.CustomerRepository;
import repository.PizzaRepository;

import java.util.Arrays;
import java.util.Random;


public class SpringJPAAppRunner {
    private static Random random = new Random();
    public static void init(PizzaService pizzaService,
                            CustomerService customerService) {
        if (pizzaService == null) return;
        pizzaService.save(new Pizza(null, "Tomato", (double)random.nextInt
                (1000), PizzaType.VEGETERIAN));
        pizzaService.save(new Pizza(null, "Chicken", (double)random.nextInt
                (1000), PizzaType.MEAT));
        pizzaService.save(new Pizza(null, "Fish", (double)random.nextInt
                (1000), PizzaType.SEA));
        Customer customer = new Customer("Anna Guyvan", new Address
                ("01032","Kyiv", "Iwana Pidkovy", "Str.",  String.valueOf
                        (random.nextInt(1000)), "18"), new
                LoyaltyCard(0.));
        customerService.save(customer);
    }

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

        CustomerService customerService = appContext.getBean("customerService", CustomerService.class);
//        Customer customer = customerService.find(1L);
//        System.out.println(customer);
//
        CustomerRepository customerRepository = (CustomerRepository)
                appContext.getBean("customerRepository");
        PizzaRepository pizzaRepository = (PizzaRepository)appContext.getBean
                ("pizzaRepository");
        PizzaService pizzaService = (PizzaService) appContext.getBean
                ("pizzaService");
//        for (int i = 0; i < 3; i++) {
//            init(pizzaService, customerService);
//        }

        Pizza pizza = pizzaRepository.read(5L);
        System.out.println(pizza);
        Customer customer = customerRepository.find(2L);
        OrderStateCycle orderStateCycle = (OrderStateCycle)appContext.getBean
                ("orderStateCycle");
        System.out.println(orderStateCycle+ "!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println();
        OrderService orderService =  appContext.getBean("orderService", OrderService.class);
        Order order = orderService.placeNewOrder(customer, 1L, 2L, 4L);
        order = orderService.addPizzas(order, 1L, 2L);

        ChequeProducer chequeProducer = appContext.getBean("chequeProducer",
                ChequeProducer.class);
        System.out.println("Customer::\n" + customer);
        order = chequeProducer.placeCheque(order);
        System.out.println(order);
        System.out.println("Cheque::\n" + order.getCheque());

        System.out.println("Customer::" + customer);
        Order order2 = orderService.placeNewOrder(customer,  3L, 6L);
        order2 = chequeProducer.placeCheque(order2);
        order2.nextState();
        System.out.println(order2);
        System.out.println("Cheque::\n" + order2.getCheque());


//        System.out.println(order + "\n" + order2);
//        order = orderService.save(order);
//        System.out.println(order + "\n" + order2);
//
//
//        Order order11 = orderService.find(1L);
//        System.out.println("order11::\n" + order11);
//
//        Order order22 = orderService.find(2L);
//        System.out.println("order22::\n" + order22);

//        Pizza pizza = new Pizza();
//        pizza.setStrName("Customized");
//        pizza.setType(PizzaType.MEAT);
//        pizza = pizzaService.save(pizza);
//        System.out.println(pizza);
//        System.out.println(pizza.getId());
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
//        System.out.println("found Cheque:\n" + chequeService.find(2L));

        repoContext.close();
        appContext.close();
    }

}

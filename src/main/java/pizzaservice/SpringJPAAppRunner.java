package pizzaservice;

import domain.*;
import infrastructure.SimpleJPARunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pizzaservice.cheque.ChequeProducer;
import pizzaservice.cheque.ChequeService;
import pizzaservice.cheque.SimpleChequeService;
import pizzaservice.states.OrderStateCycle;
import repository.CustomerRepository;
import repository.JPAPizzaRepo;
import repository.PizzaRepository;

import java.util.Arrays;
import java.util.Random;


public class SpringJPAAppRunner {
    private static Random random = new Random();
    public static void init(PizzaRepository pizzaRepository,
                            CustomerService customerService) {
        if (pizzaRepository == null) return;
        pizzaRepository.save(new Pizza(null, "Tomato", (double)random.nextInt
                (1000), PizzaType.VEGETERIAN));
        pizzaRepository.save(new Pizza(null, "Chicken", (double)random.nextInt
                (1000), PizzaType.MEAT));
        pizzaRepository.save(new Pizza(null, "Fish", (double)random.nextInt
                (1000), PizzaType.SEA));
        Customer customer = new Customer("Anna Guyvan", new Address
                ("01032","Kyiv", "Iwana Pidkovy", "Str.", "8","18"), new
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
        for (int i = 0; i < 3; i++) {
            init(pizzaRepository, customerService);
        }

        Pizza pizza = pizzaRepository.read(5L);

        System.out.println(pizza);
//        Pizza pizza = new Pizza();
//        pizza.setStrName("Customized");
//        pizza.setType(PizzaType.MEAT);
//        pizza = pizzaRepository.save(pizza);
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

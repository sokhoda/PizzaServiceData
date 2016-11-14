package pizzaservice;

import domain.*;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import repository.CustomerRepository;
import repository.LoyaltyCardRepository;
import repository.PizzaRepository;

import java.util.*;

public class SpringJPAAppRunner {
    private static Random random = new Random();

    public static void init(PizzaService pizzaService,
                            AddressService addressService) {
        if (pizzaService == null) return;
        pizzaService.save(new Pizza(null, "Tomato", (double) random.nextInt
                (1000), PizzaType.VEGETERIAN));
        pizzaService.save(new Pizza(null, "Chicken", (double) random.nextInt
                (1000), PizzaType.MEAT));
        pizzaService.save(new Pizza(null, "Fish", (double) random.nextInt
                (1000), PizzaType.SEA));

        Customer customer = new Customer("Anna Guyvan", new LoyaltyCard(0.));
        Address address = new Address
                ("01032", "Kyiv", "Iwana Pidkovy", "Str.", String.valueOf
                        (random.nextInt(1000)), String.valueOf
                        (random.nextInt(90)), customer);
        addressService.save(address);
    }

    private static final Pageable EQ_PAGEABLE = new PageRequest(0, 3, new Sort
            (Sort.Direction.ASC, "customer.name", "customer.id"));
//             JpaSort.unsafe(Sort.Direction.DESC, "customer.name+0"));
//            Sort(Sort.Direction.DESC, "customer.name+0"));
//            Sort(Sort.Direction.DESC, "loyaltyId"));

    public static Pageable modifySortProperty(Pageable pageable) {
        Iterator<Sort.Order> it = pageable.getSort().iterator();
        List<String> propertyNames = new ArrayList<>();
        Sort.Order order = null;
        while (it.hasNext()) {
            order = it.next();
            propertyNames.add(order.getProperty() + "+0");
        }

        return (order == null ? pageable :
                new PageRequest(pageable.getPageNumber(), pageable
                        .getPageSize(), JpaSort.unsafe(order.getDirection(),
                        propertyNames)));
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext repoContext = new
                ClassPathXmlApplicationContext("repoContext.xml");

//        ConfigurableApplicationContext appContext = new
//                ClassPathXmlApplicationContext("appContext.xml");
////
//        ConfigurableApplicationContext appContext = new
//                ClassPathXmlApplicationContext(new String[]
//                {"appContext.xml"}, repoContext);

//        System.out.print("repoContext::");
//        System.out.println(Arrays.toString(repoContext.getBeanDefinitionNames()));
//        System.out.print("appContext::");
//        System.out.println(Arrays.toString(appContext.getBeanDefinitionNames()));
//
//        CustomerService customerService = appContext.getBean("customerService", CustomerService.class);
//        AddressService addressService = appContext.getBean("addressService",
//                AddressService.class);
//        CustomerRepository customerRepository = repoContext.getBean
//                ("customerRepository", CustomerRepository.class);
        String colName = "lo.customer.name+0";
        LoyaltyCardRepository loyaltyCardRepository = repoContext
                .getBean("loyaltyCardRepository", LoyaltyCardRepository.class);
//        System.out.println(loyaltyCardRepository.findBySumGreaterThan(0., colName));
        Pageable modifiedPropertyPageable = modifySortProperty(EQ_PAGEABLE);
        Page<LoyaltyCard> page = loyaltyCardRepository.findBySumGreaterThan(
                0., modifiedPropertyPageable);
        System.out.println(page.getContent());
//        Page<LoyaltyCard> page = loyaltyCardRepository.findBySumGreaterThan(0.,
//                colName, EQ_PAGEABLE);


//
//        CustomerRepository customerRepository = (CustomerRepository)
//                appContext.getBean("customerRepository");
//        PizzaRepository pizzaRepository = (PizzaRepository) repoContext.getBean
//                ("pizzaRepository");
//        PizzaService pizzaService = (PizzaService) appContext.getBean
//                ("pizzaService");
//        System.out.println(pizzaRepository.findAll());


//        OrderService orderService = appContext.getBean("orderService", OrderService.class);
//        OrdersRepository orderRepository = appContext.getBean
//                ("orderRepository", OrdersRepository.class);
//        Customer customer1 = customerRepository.find(1L);
//
//        LocalDateTime fromDate = LocalDateTime.of(2016, 10, 4, 0, 0);
//        LocalDateTime toDate = LocalDateTime.of(2016, 11, 10, 0, 0);
//        List<Orders> orderList = orderService.findByDateBetween(fromDate,
//                toDate);
////        orderList = orderService.findByCustomerAndState(customer1, new
////                NewState());
//
//        orderList = orderService.findByStateAndDateBetween(fromDate,
//                toDate, new InProgressState());
//        System.out.println(orderList);

//        orderList = orderService.findByStateAndCustomerAndDateBetween(fromDate,
//                toDate, new NewState(), customer1);
//        System.out.println(orderList);
//
//
////
//        System.out.println("\n\n\n\n\n\n !!!" + orderRepository.findByCustomer
//                (customer1));

//        for (int i = 0; i < 3; i++) {
//            init(pizzaService, addressService);
//        }

//        Pizza pizza = pizzaRepository.read(5L);
//        System.out.println(pizza);
//        Customer customer = customerRepository.find(1L);
//        OrderStateCycle orderStateCycle = (OrderStateCycle)appContext.getBean
//                ("orderStateCycle");
//        System.out.println(orderStateCycle+ "!!!!!!!!!!!!!!!!!!!!!!");
//        System.out.println();
//        Orders order = orderService.placeNewOrder(customer, 1L, 2L, 4L);
//        order = orderService.addPizzas(order, 1L, 2L);
//
//        ChequeProducer chequeProducer = appContext.getBean("chequeProducer",
//                ChequeProducer.class);
//        System.out.println("Customer::\n" + customer);
//        order = chequeProducer.placeCheque(order);
//
//        System.out.println(order);
//        System.out.println("Cheque::\n" + order.getCheque());
//
//        System.out.println("Customer::" + customer);
//        Orders order2 = orderService.placeNewOrder(customer,  3L, 6L);
//        order2 = chequeProducer.placeCheque(order2);
//        order2.nextState();
//        order2 = orderService.save(order2);
//        System.out.println(order2);
//        System.out.println("Cheque::\n" + order2.getCheque());
//
//        System.out.println(orderService.findByCustomer(customer));
//+++++++++++++++++++++++++++++++++++++++++++++++
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
//        System.out.println(pizza.getPizzaId());
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
//        appContext.close();
    }
}

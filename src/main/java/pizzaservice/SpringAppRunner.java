package pizzaservice;

import domain.Customer;
import domain.Order;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import repository.PizzaRepository;

import java.util.Arrays;

public class SpringAppRunner {
    public static void main(String[] args) {
        Customer customer = null;
        ConfigurableApplicationContext repoContext = new
                ClassPathXmlApplicationContext("repoContext.xml");

        ConfigurableApplicationContext appContext = new
                ClassPathXmlApplicationContext(new String[]
                {"appContext.xml"}, repoContext);

        System.out.print("repoContext::");
        System.out.println(Arrays.toString(repoContext.getBeanDefinitionNames()));
        System.out.print("appContext::");
        System.out.println(Arrays.toString(appContext.getBeanDefinitionNames()));

PizzaRepository pizzaRepository = (PizzaRepository) repoContext.getBean("pizzaRepository");
        System.out.println(pizzaRepository.find(1L));
        OrderService orderService = (OrderService)appContext.getBean("orderService");
        Order order = orderService.placeNewOrder(customer, 1L, 2L, 3L);
        System.out.println(order);
        System.out.println(repoContext.getBean("T1", SomeService.class).getString());
        System.out.println(appContext.getBean("T1", SomeService.class).getString());

        repoContext.close();
        appContext.close();
    }
}

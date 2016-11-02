package web.rest;

import domain.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pizzaservice.PizzaService;

import java.util.Arrays;
import java.util.List;

@RestController
public class PizzaRESTController {

    @Autowired
    private PizzaService pizzaService;

    @RequestMapping(value = "pizza/{pizzaID1}/{pizzaID2}", method =
            RequestMethod.GET)
    public List<Pizza> pizza(@PathVariable("pizzaID1") Long pizzaID1,
                             @PathVariable("pizzaID2") Long pizzaID2){
        return Arrays.asList(pizzaService.find(pizzaID1),
                pizzaService.find(pizzaID2));
    }

    @RequestMapping(value = "pizza", method = RequestMethod.POST)
    public void pizza(@RequestBody Pizza pizza){
        System.out.println(pizza);
    }
}

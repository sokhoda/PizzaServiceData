package pizzaservice;

import domain.Pizza;
import infrastructure.UnitTestData;
import org.junit.Before;
import org.junit.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.PizzaRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class SimplePizzaServiceTest extends UnitTestData {
    @Mock
    private PizzaRepository pizzaRepo;
    @InjectMocks
    private SimplePizzaService simplePizzaService;

    @Before
    public  void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFind() throws Exception {
//        GIVEN
        given(pizzaRepo.find(1L)).willReturn(testPizza1);
//        WHEN
        Pizza actualPizza = simplePizzaService.find(1L);
//        THEN
        assertThat(actualPizza, is(testPizza1));
        verify(pizzaRepo).find(1L);
    }

    @Test
    public void save() throws Exception {
//       GIVEN
        given(pizzaRepo.save(any())).will(AdditionalAnswers.returnsFirstArg());
//       WHEN
        final Pizza actualPizza = simplePizzaService.save(testPizza1);
//       THEN
        assertThat(actualPizza, is(testPizza1));
        verify(pizzaRepo).save(any());
    }

    @Test
    public void findAll() throws Exception {
        List<Pizza> expectedPizzaList = new ArrayList<>(Arrays.asList
                (testPizza1, testPizza2));
//        GIVEN
        given(pizzaRepo.findAll()).willReturn(expectedPizzaList);
//        WHEN
        List<Pizza> actualPizzaList = simplePizzaService.findAll();
//        THEN
        assertThat(actualPizzaList,is(expectedPizzaList));
        verify(pizzaRepo).findAll();
    }
}
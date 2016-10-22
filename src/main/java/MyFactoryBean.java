import domain.Pizza;
import domain.PizzaType;
import org.springframework.beans.factory.FactoryBean;

public class MyFactoryBean implements FactoryBean<Pizza> {
    @Override
    public Pizza getObject() throws Exception {
        return new Pizza(1L, "name", 1222., PizzaType.MEAT);
    }

    @Override
    public Class<?> getObjectType() {
        return Pizza.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}

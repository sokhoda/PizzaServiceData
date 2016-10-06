package pizzaservice;

public class Test1SomeService implements SomeService {
    @Override
    public String getString() {
        return "test1";
    }
    public void destroy(){
        System.out.println("destroy");
    }

}

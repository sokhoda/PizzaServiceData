package pizzaservice;

public class UsageSomeService {
    private SomeService someService;

    public UsageSomeService(SomeService someService) {
        this.someService = someService;
    }

    public void init(){
        System.out.println("USAGE:" + someService.getString());
    }
}

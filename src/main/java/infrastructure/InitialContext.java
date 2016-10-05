package infrastructure;


public class InitialContext {
    private static Config config = new JavaConfig();

    public <T> T getInstance(String name) {
        Class<?> type = config.getImp(name);
        try {
            return (T) type.newInstance();
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}

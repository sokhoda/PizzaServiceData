package infrastructure;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ApplicationContext implements Context {
    private final Config config;
    private Map<String, Object> beans = new HashMap<>();

    public ApplicationContext(Config config) {
        this.config = config;
    }

    @Override
    public <T> T getBean(String beanName) {
        if (beans.containsKey(beanName)) {
            return (T) beans.get(beanName);
        }
        Class<?> type = config.getImp(beanName);
        Constructor<?> constructor = type.getConstructors()[0];
        int paramCount = constructor.getParameterCount();
        if (paramCount == 0) {
            return getNewBean(beanName, type);
        }
        else {
            Class<?>[] types = constructor.getParameterTypes();
            return getSubBeans(beanName, constructor, types);
        }
    }

    private <T> T getSubBeans(String beanName, Constructor<?> constructor, Class<?>[] types) {
        Object[] params = new Object[constructor.getParameterCount()];
        try {
            for (int i = 0; i < types.length; i++) {
                String curBeanName = convertTypeToBeanName(types[i]);
                params[i] = getBean(curBeanName);
            }
            T bean = (T) constructor.newInstance(params);
            beans.put(beanName, bean);
            return bean;
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private <T> T getNewBean(String beanName, Class<?> type) {
        try {
            T bean = (T) type.newInstance();
            beans.put(beanName, bean);
            return bean;
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private String convertTypeToBeanName(Class<?> param) {
        String name = param.getSimpleName();

        char[] nameChar = name.toCharArray();
        nameChar[0] = (name.toLowerCase()).charAt(0);
        return String.valueOf(nameChar);
    }
}

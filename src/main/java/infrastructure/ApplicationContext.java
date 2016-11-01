package infrastructure;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
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
        Class<?> type = config.getImp(beanName);
        Object bean = beans.get(beanName);

        if (bean != null) {
            return (T) bean;
        }

        BeanBuilder builder = new BeanBuilder(type);
        builder.createBean(beanName);
        builder.callPostCreateMethod();
//        builder.callInitMethod();

        builder.createBeanProxy();
        bean = builder.build();

        beans.put(beanName, bean);
        return (T) bean;
    }


    class BeanBuilder {
        private final Class<?> type;
        private Object bean;

        public BeanBuilder(Class<?> type) {
            this.type = type;
        }

        public void createBeanProxy() {
            Object proxy = bean;
            Class<?>[] interfaces = bean.getClass().getInterfaces();
            InvocationHandler ih = new InHandler(bean);

            Method[] methods =  bean.getClass().getMethods();
            for (Method met: methods) {
                if (met.getAnnotation(Benchmark.class) != null){
                    proxy =  Proxy.newProxyInstance
                            (bean.getClass().getClassLoader(), interfaces, ih);
                    break;
                }
            }
            bean = proxy;
        }

        public void callPostCreateMethod() {
            Class<?> clazz = bean.getClass();
            Method[] methods = clazz.getMethods();
            for (Method method: methods) {
                if (method.getAnnotation(PostCreate.class) != null){
                    try {
                        method.invoke(bean);
                    }
                    catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }

        }

        public void callInitMethod() {

            Class<?> clazz = bean.getClass();
            Method method = null;
            try {
                method = clazz.getMethod("init");
                method.invoke(bean);
            }
            catch (Exception e) {
                return;
            }
        }

        public <T> T build() {
            return (T) bean;
        }


        private <T> T createBean(String beanName) {

            Constructor<?> constructor = type.getConstructors()[0];
            int paramCount = constructor.getParameterCount();
            if (paramCount == 0) {
                bean = getNewBean(beanName, type);
            }
            else {
                Class<?>[] types = constructor.getParameterTypes();
                bean = getSubBeans(beanName, constructor, types);
            }
            return (T) bean;
        }

        private <T> T getSubBeans(String beanName, Constructor<?> constructor, Class<?>[] types) {
            Object[] params = new Object[constructor.getParameterCount()];
            try {
                for (int i = 0; i < types.length; i++) {
                    String curBeanName = convertTypeToBeanName(types[i]);
                    params[i] = getBean(curBeanName);
                }
                T bean = (T) constructor.newInstance(params);
                return bean;
            }
            catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

        private <T> T getNewBean(String beanName, Class<?> type) {
            try {
                T bean = (T) type.newInstance();
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


}

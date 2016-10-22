package infrastructure;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BenchmarkBeanPostProcessor implements BeanPostProcessor {

    public Object createBeanProxy(Object bean) {
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
        return  proxy;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Created " + beanName);
//        return createBeanProxy(bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Object proxy =createBeanProxy(bean);
        System.out.println("Initialized " + beanName + " " + ObjectUtils
                .identityToString(proxy));
//        return proxy;
        return bean;
    }
}

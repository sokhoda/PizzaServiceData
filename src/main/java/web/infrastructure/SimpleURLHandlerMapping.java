package web.infrastructure;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.servlet.http.HttpServletRequest;

public class SimpleURLHandlerMapping implements HandlerMapping, ApplicationContextAware {
    private ApplicationContext webContext;

//    public SimpleURLHandlerMapping(ApplicationContext webContext) {
//        this.webContext = webContext;
//    }

    @Override
    public MyController getController(HttpServletRequest req) {
        String url = req.getRequestURI();
        String controllerName = getControllerName(url);
        return (MyController) webContext.getBean(controllerName);
    }

    private String getControllerName(String url) {
        return url.substring(url.lastIndexOf("/"));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.webContext = applicationContext;
    }
}

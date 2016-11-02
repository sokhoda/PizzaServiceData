package web.infrastructure;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class DispatcherServlet extends HttpServlet {

    private ConfigurableApplicationContext webContext;
    ConfigurableApplicationContext[] applicationContexts;

    @Override
    public void init() throws ServletException {
        String contextConfigLocation = getServletContext().getInitParameter
                ("contextConfigLocation");
        String[] contexts = contextConfigLocation.split(" ");
        applicationContexts = new ConfigurableApplicationContext[contexts
                .length];
        for (int i = 0; i < applicationContexts.length; i++) {
            ConfigurableApplicationContext context;
            if (i == 0) {
                context = new ClassPathXmlApplicationContext(contexts[i]);
            }
            else {
                context = new ClassPathXmlApplicationContext(
                        new String[]{contexts[i]}, applicationContexts[i - 1]);
            }
            applicationContexts[i] = context;
        }

        String webContextConfigLocation = getInitParameter("contextConfigLocation");
        webContext = new ClassPathXmlApplicationContext(
                new String[]{webContextConfigLocation},
                applicationContexts[applicationContexts.length - 1]);
    }

    @Override
    public void destroy() {
        webContext.close();
        for (int i = applicationContexts.length - 1; i >= 0; i--) {
            applicationContexts[i].close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    public void processRequest(HttpServletRequest req, HttpServletResponse
            resp) {


        HandlerMapping handlerMapping = webContext.getBean
                ("handlerMappingStrategy", HandlerMapping.class);
//                new SimpleURLHandlerMapping(webContext);


        MyController controller = handlerMapping.getController(req);
//                (MyController) webContext.getBean(controllerName);
        System.out.println(Arrays.toString(webContext.getBeanDefinitionNames()));

        if (controller != null) {
            controller.handleRequest(req, resp);
        }

//        try (PrintWriter out = resp.getWriter()) {
//            out.println("HALLOO!!!!\n" + controllerName);
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
    }


}

package web.infrastructure;

import javax.servlet.http.HttpServletRequest;

public interface HandlerMapping {
    public MyController getController(HttpServletRequest req);
}

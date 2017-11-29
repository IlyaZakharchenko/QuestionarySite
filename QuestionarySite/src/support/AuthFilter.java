package support;

import model.database.UserDAO;
import model.entities.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter(filterName = "AuthFilter")
public class AuthFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;

        if (request.getSession().getAttribute("current_user") != null) {
            request.getSession().setAttribute("current_user",
                    UserDAO.getDao().getUserByUsername(((User)request.getSession().getAttribute("current_user")).getUsername())
            );
            chain.doFilter(req, resp);
        }

        else ((HttpServletResponse) resp).sendRedirect("/main");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

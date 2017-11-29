package support;

import model.database.UserDAO;
import model.entities.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "CookiesFilter")
public class CookiesFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;

        UserDAO dao = UserDAO.getDao();

        Cookie[] cookies = request.getCookies();
        User current_user = (User) request.getSession().getAttribute("current_user");

        if (current_user == null && cookies != null) {

            for (Cookie cookie : cookies) {
                if ("user_id".equals(cookie.getName())) {
                    User user = dao.getUserByCookie(cookie.getValue());

                    if (user != null) request.getSession().setAttribute("current_user", user);
                    break;
                }
            }
        }
            chain.doFilter(req, resp);

    }

    public void init(FilterConfig config) throws ServletException {

    }

}

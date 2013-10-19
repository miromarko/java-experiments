package ro.springsoft.guice;

import com.google.inject.servlet.GuiceFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Author: Miroslav MARKO
 * Date: 10/19/13
 */
public class GuiceFilterJSF extends GuiceFilter {
  
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            String path = ((HttpServletRequest) servletRequest).getRequestURI();
            if (path!=null && path.toLowerCase().startsWith("/javax.faces.resource")) {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
        super.doFilter(servletRequest, servletResponse, filterChain);
    }
}

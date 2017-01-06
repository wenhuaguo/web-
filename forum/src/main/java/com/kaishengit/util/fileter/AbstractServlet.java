package com.kaishengit.util.fileter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Acer on 2016/12/15.
 */
public abstract class AbstractServlet implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {

    }
}

package com.kaishengit.web.user;

import com.kaishengit.util.fileter.AbstractServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Acer on 2016/12/15.
 */
public class FilterServlet extends AbstractServlet {
    private String encoding = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String result = filterConfig.getInitParameter("encoding");
        if(StringUtils.isNotEmpty(result)){
           encoding = result;
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encoding);
        servletResponse.setCharacterEncoding(encoding);
        //对请求的过滤进行放行
        filterChain.doFilter(servletRequest,servletResponse);
    }
}

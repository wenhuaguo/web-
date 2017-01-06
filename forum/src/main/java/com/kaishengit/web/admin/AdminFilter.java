package com.kaishengit.web.admin;

import com.kaishengit.entity.Admin;
import com.kaishengit.util.fileter.AbstractServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Acer on 2016/12/28.
 */
public class AdminFilter extends AbstractServlet {
    private List<String> urlList = null;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //获取参数名URL过滤的请求
        String url = filterConfig.getInitParameter("url");
        urlList = Arrays.asList(url.split(","));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //首先强制转换为HttpRequestServlet和HttpResponseServlet
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;
        //获取请求的地址
        String reqUrl  = req.getRequestURI();
        //获取当前用户
        Admin admin = (Admin) req.getSession().getAttribute("admin");
        if(StringUtils.isNotEmpty(reqUrl) && urlList.contains(reqUrl)){
            if(admin != null){
                //如果用户登录那就去该去的地方
                filterChain.doFilter(req,resp);
            }else {
                //否则的话重定向到
                resp.sendRedirect("/admin/login?redirect=" + reqUrl);
            }
        }else {
            //如果访问的页面不再过滤范围内就让用户通过
            filterChain.doFilter(req,resp);
        }
    }
}

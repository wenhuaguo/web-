package com.kaishengit.web.user;

import com.kaishengit.util.fileter.AbstractServlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by Acer on 2016/12/19.
 * 登录过滤验证
 */
public class LoginValidateFilter extends AbstractServlet {
    private List<String> urlList = null;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       //为以后做打算URL数组对URL数组进行分组按","
        String url = filterConfig.getInitParameter("url");
        urlList = Arrays.asList(url.split(","));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //进行转换HttpServletRequset和HttpServletResponse
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;

        //2.获取用户要访问URL
        String requsetUrl = req.getRequestURI();
        System.out.println(requsetUrl);
        //该方法可以获取客户端要访问的网页
        if(requsetUrl != null && urlList.contains(requsetUrl)){
            //查看用户是否登录通过sessionid看是否存有值（对象）
            if(req.getSession().getAttribute("curr_user") == null){
                Map map = req.getParameterMap();
                Set paramSet = map.entrySet();
                Iterator it = paramSet.iterator();
                if(it.hasNext()){
                    requsetUrl += "?";
                    while (it.hasNext()){
                        Map.Entry me = (Map.Entry)it.next();
                        Object key = me.getKey();
                        Object value = me.getValue();
                        String[] strings = (String[])value;
                        String param = "";
                        for(int i=0; i<strings.length;i++ ){
                            param = key+"="+strings[i] + "&";
                            requsetUrl += param;
                        }
                    }
                    requsetUrl = requsetUrl.substring(0,requsetUrl.length() -1);
                    System.out.println("requestUrl:"+requsetUrl);
                }
                System.out.println(requsetUrl);
                //如果用户访问需要过滤用户必须登录才可以访问的的页面，但是用户session中没有存有用户对象，那么就将用重定向到登录页面进行登录，登录后转向用户需要访问的页面
                resp.sendRedirect("/login?redirect=" + requsetUrl);
            }else {
                //如果用户访问的页面不是过滤的页面那么就放行让用户进行访问
                filterChain.doFilter(req,resp);
            }
        }else {
            //如果用户访问的页面不再过滤集合范围内那么就直接放行
            filterChain.doFilter(req,resp);
        }
    }
}

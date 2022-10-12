package com.jiejiezhuzhu.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException
    {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        //判断访问资源路径是否和登录注册相关
        String[] urls = {"/index.html","/pages/register.html","/check_code","/check_code/","/register","/pages/login.html","/img/","/css/","/login","/js/","/mp3/"};
        // 获取当前访问的资源路径
        String url = req.getRequestURL().toString();

        //循环判断
        for (String u : urls) {
            if(url.contains(u)){
                //找到了
                //放行
                chain.doFilter(request, response);
                //break;
                return;
            }
        }


        //1. 判断session中是否有user
        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");

        //2. 判断user是否为null
        if(user != null){
            // 登录过了
            //放行
            chain.doFilter(request, response);
        }else {
            // 没有登陆，存储提示信息，跳转到登录页面

//            req.setAttribute("login_msg","您尚未登陆！");
//            req.getRequestDispatcher("").forward(req,response);
            Cookie c_login_msg = new Cookie("login_msg","您尚未登陆！");
            c_login_msg.setMaxAge(3);
            res.addCookie(c_login_msg);
            res.sendRedirect("/pages/login.html");

        }


    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

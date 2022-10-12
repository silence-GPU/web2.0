package com.jiejiezhuzhu.controller;


import com.jiejiezhuzhu.domain.User;
import com.jiejiezhuzhu.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping
    public Result login(@RequestBody User user, HttpServletResponse response, HttpServletRequest request) throws IOException {
        User user1 = userService.getByNP(user);
        Integer code = user1 != null ? Code.GET_OK : Code.GET_ERR;
        String msg = user1 != null ? "" : "用户名或密码有误，请重试！";
//        System.out.println(user.getRemember());
//        System.out.println(user.getRemember().getClass());
        if(user1 != null){
            //登录成功，跳转到查询所有的BrandServlet

            //判断用户是否勾选记住我
            if("true".equals(user.getRemember())){
                //勾选了，发送Cookie

                //1. 创建Cookie对象
                Cookie c_password = new Cookie("password",user1.getPassword());
                // 设置Cookie的存活时间

                c_password.setMaxAge( 60 * 60 * 24 * 7);
                //2. 发送
                response.addCookie(c_password);

            }
            Cookie c_username = new Cookie("username",user1.getUsername());
            c_username.setMaxAge( 60 * 60 * 24 * 7);
            response.addCookie(c_username);


            //将登陆成功后的user对象，存储到session
            HttpSession session = request.getSession();
            session.setAttribute("user",user1);

//            System.out.println("222");
            return new Result(code,user1,msg);
        }else {
            // 登录失败,

            // 存储错误信息到request
//            System.out.println("111");
            return new Result(code,user1,msg);



        }
    }
}

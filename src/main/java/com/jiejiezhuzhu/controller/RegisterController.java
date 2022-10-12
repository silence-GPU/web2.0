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
@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping
    public Result register(@RequestBody User user, HttpServletResponse response, HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        String checkCode = (String) session.getAttribute("checkCode");
        System.out.println("验证码："+checkCode);
        System.out.println("用户输入："+user.getCheck_code());
        System.out.println(!(checkCode.equals(user.getCheck_code())));
        if (!(checkCode.equalsIgnoreCase(user.getCheck_code().trim()))){
            String msg = "验证码输入错误～";
            Integer code = 0;
            return new Result(code,user,msg);
        }
        User user1 = userService.getByName(user);


//        System.out.println(user.getRemember());
//        System.out.println(user.getRemember().getClass());
        if(user1 == null){
            //将数据添加进数据库
            Boolean flag = userService.save(user);
            Integer code = flag ? Code.SAVE_OK : Code.SAVE_ERR;
//            System.out.println("code:"+code);
//            System.out.println(code == Code.SAVE_OK);
            if (code == Code.SAVE_OK){
//                System.out.println("注册成功");
                Cookie c_username = new Cookie("username",user.getUsername());
                c_username.setMaxAge( 60 * 60 * 24 * 7);
                response.addCookie(c_username);
                String msg = "注册成功!";

                return new Result(code,user,msg);
            }else {
                String msg = "系统错误，注册失败!";
                return new Result(code,user,msg);
            }


        }else {
            // 注册失败,

            Integer code = Code.SAVE_ERR;
            String msg = "用户名重复了，请重写填写~";
            // 存储错误信息到request
//            System.out.println("111");
            return new Result(code,user,msg);
        }
    }
}

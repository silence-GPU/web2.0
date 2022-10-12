package com.jiejiezhuzhu.controller;



import com.jiejiezhuzhu.domain.User;
import com.jiejiezhuzhu.service.UserService;
import com.jiejiezhuzhu.utils.CheckCodeUtil;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/check_code")
public class CheckCodeController {

    @GetMapping("/{id}")
    public void checkcode(HttpServletResponse response, HttpServletRequest request) throws IOException {
        // 生成验证码
        ServletOutputStream os = response.getOutputStream();
        String checkCode = CheckCodeUtil.outputVerifyImage(100, 50, os, 4);


        // 存入Session
        HttpSession session = request.getSession();
        session.setAttribute("checkCode",checkCode);
        System.out.println("验证码"+checkCode);
    }
}

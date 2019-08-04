package cn.oveay.cspuzzle.web.servlet;

import cn.oveay.cspuzzle.util.servlet.AutoFunctionServlet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/login")
public class LoginServlet extends AutoFunctionServlet {
    @RequestMapping("/index")
    public String index(){
        return null;
    }
}

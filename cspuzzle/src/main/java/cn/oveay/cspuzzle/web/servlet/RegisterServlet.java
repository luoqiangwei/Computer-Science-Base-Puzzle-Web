package cn.oveay.cspuzzle.web.servlet;

import cn.oveay.cspuzzle.bean.User;
import cn.oveay.cspuzzle.service.RegisterService;
import cn.oveay.cspuzzle.service.impl.RegisterServiceImpl;
import cn.oveay.cspuzzle.util.commons.Nanoflake;
import cn.oveay.cspuzzle.util.phonesingle.PhoneMS;
import cn.oveay.cspuzzle.util.security.Token;
import cn.oveay.cspuzzle.util.security.Verify;
import cn.oveay.cspuzzle.util.servlet.AutoFunctionServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/register")
public class RegisterServlet extends AutoFunctionServlet {
    @Autowired
    private RegisterService registerService;
    @Autowired
    private Verify verifyService;
    @Autowired
    private Token tokenService;

    @RequestMapping("/index")
    public String index(Model model,  HttpServletRequest request){
        String token = Nanoflake.getNanoflake();
        tokenService.addToken(request.getSession().getId(), token);
        model.addAttribute("token", token);
        return "jsp/register.jsp";
    }

    @RequestMapping("/verify")
    public String verify(Model model,  HttpServletRequest request) throws ServletException, IOException {
        String token = Nanoflake.getNanoflake();
        tokenService.addToken(request.getSession().getId(), token);
        model.addAttribute("token", token);
        return "jsp/verify.jsp";
    }

    public String check(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter("token");
        if(!tokenService.checkToken(req.getSession().getId(), token)){
            req.setAttribute("form", "您的签名不正确，表单失效");
            return "/Register?method=verify";
        }
        User user = (User) req.getSession().getAttribute("ruser");
        if(user == null){
            req.setAttribute("form", "未知错误");
            return "/Register?method=verify";
        }
        String code = req.getParameter("verifycode");
        if(!verifyService.checkVerify(req.getSession().getId(), code)){
            req.setAttribute("form", "短信验证码错误");
            return "/Register?method=verify";
        }
        req.getSession().setAttribute("userInfo", registerService.register(user));;
        return "/index?method=index";
    }


    public String register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter("token");
        String vCode = (String) req.getSession().getAttribute("vCode");
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");
        String repassword = req.getParameter("repassword");
        String vcode = req.getParameter("vcode");

        //System.err.println(vCode);
        //System.err.println(vcode);
        if(vcode.trim().equals("") || phone.trim().equals("") || password.trim().equals("") || repassword.trim().equals("")){
            req.setAttribute("form", "所有信息必须都填写");
            reSet(req, phone, password);
            return "f:/Register?method=index";
        }
        if (!vcode.toUpperCase().equals(vCode.toUpperCase())){
            req.setAttribute("email", "验证码错误");
            reSet(req, phone, password);
            return "f:/Register?method=index";
        }
        if(phone.length() != 11){
            req.setAttribute("email", "电话号码错误");
            reSet(req, phone, password);
            return "f:/Register?method=index";
        }
        if(!password.equals(repassword)){
            req.setAttribute("password", "两次密码不一致");
            reSet(req, phone, password);
            return  "f:/Register?method=index";
        }
        if(password.length() < 8){
            req.setAttribute("password", "密码太短");
            reSet(req, phone, password);
            return  "f:/Register?method=index";
        }
        if(password.length() > 20){
            req.setAttribute("password", "密码太长");
            reSet(req, phone, password);
            return  "f:/Register?method=index";
        }

        if(!tokenService.checkToken(req.getSession().getId(), token)){
            req.setAttribute("form", "您的签名不正确，表单失效");
            reSet(req, phone, password);
            return "/Register?method=index";
        }

        User user = new User();
        user.setPhone(phone);
        user.setPassword(password);
        if(!registerService.check(user)){
            req.setAttribute("form", "该号码已被使用");
            reSet(req, phone, password);
            return "/Register?method=index";
        }

        String code = PhoneMS.sendMsg(phone);
        verifyService.addVerify(req.getSession().getId(), code);

        req.getSession().setAttribute("ruser", user);
        return "f:/Register?method=verify";
    }

    private void reSet(HttpServletRequest req, String email, String password){
        if(!email.trim().equals(""))
            req.setAttribute("rphone", email);
        if(!password.trim().equals(""))
            req.setAttribute("rpassword", password);
    }
}

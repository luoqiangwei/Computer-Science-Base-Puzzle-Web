package cn.oveay.cspuzzle.web.servlet;

import cn.ovea_y.puzzle.bean.User;
import cn.ovea_y.puzzle.service.RegisterService;
import cn.ovea_y.puzzle.service.impl.RegisterServiceImpl;
import cn.ovea_y.puzzle.util.commons.Nanoflake;
import cn.ovea_y.puzzle.util.phonesingle.PhoneMS;
import cn.ovea_y.puzzle.util.security.Token;
import cn.ovea_y.puzzle.util.security.Verify;
import cn.ovea_y.puzzle.util.servlet.AutoFunctionServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Register")
public class RegisterServlet extends AutoFunctionServlet {
    private RegisterService registerService = new RegisterServiceImpl();

    public String index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = Nanoflake.getNanoflake();
        Token.addToken(req.getSession().getId(), token);
        req.setAttribute("token", token);
        return "jsp/register.jsp";
    }

    public String verify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = Nanoflake.getNanoflake();
        Token.addToken(req.getSession().getId(), token);
        req.setAttribute("token", token);
        return "jsp/verify.jsp";
    }

    public String check(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter("token");
        if(!Token.checkToken(req.getSession().getId(), token)){
            req.setAttribute("form", "您的签名不正确，表单失效");
            return "/Register?method=verify";
        }
        User user = (User) req.getSession().getAttribute("ruser");
        if(user == null){
            req.setAttribute("form", "未知错误");
            return "/Register?method=verify";
        }
        String code = req.getParameter("verifycode");
        if(!Verify.checkVerify(req.getSession().getId(), code)){
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

        if(!Token.checkToken(req.getSession().getId(), token)){
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
        Verify.addVerify(req.getSession().getId(), code);

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

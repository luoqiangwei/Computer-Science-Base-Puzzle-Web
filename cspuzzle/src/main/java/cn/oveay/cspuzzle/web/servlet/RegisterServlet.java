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
import org.springframework.web.bind.annotation.SessionAttribute;

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

    @RequestMapping("/check")
    public String check(Model model, String token, String verifycode, HttpServletRequest request){
        if(!tokenService.checkToken(request.getSession().getId(), token)){
            model.addAttribute("form", "您的签名不正确，表单失效");
            return "/register/verify";
        }
        User user = (User) request.getSession().getAttribute("ruser");
        if(user == null){
            model.addAttribute("form", "未知错误");
            return "/register/verify";
        }
        if(!verifyService.checkVerify(request.getSession().getId(), verifycode)){
            model.addAttribute("form", "短信验证码错误");
            return "/register/verify";
        }
        request.getSession().setAttribute("userInfo", registerService.register(user));;
        return "/register/index";
    }

    @RequestMapping("/register")
    public String register(Model model, HttpServletRequest request, String token, String vCode, String phone, String password, String repassword, String vcode) {
        if(vcode.trim().equals("") || phone.trim().equals("") || password.trim().equals("") || repassword.trim().equals("")){
            model.addAttribute("form", "所有信息必须都填写");
            reSet(model, phone, password);
            return "/register/index";
        }
        if (!vcode.toUpperCase().equals(vCode.toUpperCase())){
            model.addAttribute("email", "验证码错误");
            reSet(model, phone, password);
            return "/register/index";
        }
        if(phone.length() != 11){
            model.addAttribute("email", "电话号码错误");
            reSet(model, phone, password);
            return "/register/index";
        }
        if(!password.equals(repassword)){
            model.addAttribute("password", "两次密码不一致");
            reSet(model, phone, password);
            return  "/register/index";
        }
        if(password.length() < 8){
            model.addAttribute("password", "密码太短");
            reSet(model, phone, password);
            return  "/register/index";
        }
        if(password.length() > 20){
            model.addAttribute("password", "密码太长");
            reSet(model, phone, password);
            return  "/register/index";
        }

        if(!tokenService.checkToken(request.getSession().getId(), token)){
            model.addAttribute("form", "您的签名不正确，表单失效");
            reSet(model, phone, password);
            return "/register/index";
        }

        User user = new User();
        user.setPhone(phone);
        user.setPassword(password);
        if(!registerService.check(user)){
            model.addAttribute("form", "该号码已被使用");
            reSet(model, phone, password);
            return "/register/index";
        }

        String code = PhoneMS.sendMsg(phone);
        verifyService.addVerify(request.getSession().getId(), code);

        request.getSession().setAttribute("ruser", user);
        return "/register/verify";
    }

    private void reSet(Model model, String email, String password){
        if(!email.trim().equals(""))
            model.addAttribute("rphone", email);
        if(!password.trim().equals(""))
            model.addAttribute("rpassword", password);
    }
}

package cn.oveay.cspuzzle.util.servlet;

import cn.oveay.cspuzzle.util.listener.base.NewSessionContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/heartbeat")
public class HeartBeatServlet extends AutoFunctionServlet{
    @RequestMapping("/tick")
    @ResponseBody
    public String tick() {
        return "{\"tick\":true, \"timeout\":" + NewSessionContext.getTimeout() / 2 * 1000 + "}";
    }
}

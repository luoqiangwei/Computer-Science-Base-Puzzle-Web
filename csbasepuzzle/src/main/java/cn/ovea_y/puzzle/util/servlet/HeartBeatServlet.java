package cn.ovea_y.puzzle.util.servlet;

import cn.ovea_y.puzzle.util.listener.base.NewSessionContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/HeartBeat", "/list/HeartBeat"})
public class HeartBeatServlet extends AutoFunctionServlet{
    public String tick(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().append("{\"tick\":true, \"timeout\":" + NewSessionContext.getTimeout() / 2 * 1000 + "}");
        return null;
    }
}

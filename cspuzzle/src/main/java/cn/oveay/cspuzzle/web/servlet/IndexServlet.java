package cn.oveay.cspuzzle.web.servlet;

import cn.ovea_y.puzzle.util.servlet.AutoFunctionServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/index")
public class IndexServlet extends AutoFunctionServlet {
    public String index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return "jsp/index.jsp";
    }
}

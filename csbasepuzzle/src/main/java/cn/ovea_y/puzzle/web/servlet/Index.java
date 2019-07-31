package cn.ovea_y.puzzle.web.servlet;

import cn.ovea_y.puzzle.util.servlet.AutoFunctionServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/index")
public class Index extends AutoFunctionServlet {
    public String index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return "html/index.html";
    }
    public String setting(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return "/WEB-INF/html/template/setting.html";
    }
    public String top(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return "/WEB-INF/html/template/top.html";
    }
    public String foot(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return "/WEB-INF/html/template/foot.html";
    }
}


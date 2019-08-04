package cn.oveay.cspuzzle.util.fiter;



import cn.oveay.cspuzzle.util.listener.base.NewSessionContext;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

/**
 * @author QiangweiLuo
 * 管理心跳包，当用户关闭浏览器之时就会触发销毁session的事件
 *
 * 不需要这个过滤器，因为Java Session也可以完成类似功能
 */
//@WebFilter("/*")
public class HeartBeatFilter extends HttpFilter {
    private Boolean isAction = false;
    private Integer timeout = 60 * 1000;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        if(isAction){
            HttpServletRequest request = (HttpServletRequest) req;
            Cookie[] cookies = request.getCookies();
            if(cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("JSESSIONID")) {
                        if(NewSessionContext.getCreateTime(cookie.getValue()) != null) {
                            if (new Date().getTime() - NewSessionContext.getCreateTime(cookie.getValue()) > timeout) {
                                NewSessionContext.getSession(cookie.getValue()).invalidate();
                                ((HttpServletResponse) res).sendRedirect("/index.html");
                                return;
                            } else {
                                NewSessionContext.resetCreateTime(cookie.getValue());
                            }
                        }
                    }
                }
            }
        }
        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        super.init(config);
        // 加载配置文件
        Properties props = new Properties();
        try {
            props.load(getClass().getClassLoader().getResourceAsStream("/config/config.properties"));
        } catch (IOException e) {
            throw new RuntimeException("配置文件读取失败");
        }
        if(props.getProperty("heartBeat") != null) {
            isAction = Boolean.parseBoolean(props.getProperty("heartBeat"));
        }
        if(props.getProperty("timeout") != null){
            timeout = Integer.parseInt(props.getProperty("timeout"));
        }
    }
}

package cn.oveay.cspuzzle.util.fiter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Properties;

/**
 * @author QiangweiLuo
 * 防盗链过滤器
 */
@WebFilter("/*")
public class SecurityFilter extends HttpFilter {
    private String[] securityUrl = null;
    private String[] baseUrl = null;
    private Boolean isSafe;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String check = request.getHeader("Referer");
        if(securityUrl == null){
            chain.doFilter(req, res);
            return;
        }
        if(baseUrl != null){
            for(String url : baseUrl){
                if(request.getRequestURI().startsWith(url)){
                    chain.doFilter(req, res);
                    return;
                }
            }
        }
        isSafe = false;
        if(check != null) {
            for (int i = 0; i < securityUrl.length; i++) {
                if(check.startsWith(securityUrl[i])){
                    isSafe = true;
                }
            }
            if(isSafe){
                chain.doFilter(req, res);
                return;
            }
        }
    }

    /**
     *
     * @param config
     * @throws ServletException
     * 初始化盗链信息，若未配置则视为未设置防盗链
     */
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
        securityUrl = props.getProperty("securityUrl").split("&");
        baseUrl = props.getProperty("baseUrl").split("&");
    }
}

package cn.ovea_y.puzzle.util.fiter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/*", initParams = {@WebInitParam(name = "charset", value = "UTF-8")})
public class EncodingFilter implements Filter {
	private String charset = "UTF-8";
	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		if(req.getMethod().equalsIgnoreCase("GET")) {
			if(!(req instanceof GetRequest)) {
				req = new GetRequest(req, charset);//处理get请求编码
			}
		} else {
			req.setCharacterEncoding(charset);//处理post请求编码
		}
		chain.doFilter(req, response);
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		String charset = fConfig.getInitParameter("charset");
		if(charset != null && !charset.isEmpty()) {
			this.charset = charset;
		}
	}
}

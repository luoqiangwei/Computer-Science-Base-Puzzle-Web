package cn.oveay.cspuzzle.util.fiter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * 对GET请求参数加以处理！
 * @author OVEA
 *
 */
public class GetRequest extends HttpServletRequestWrapper {
	private HttpServletRequest request;
	private String charset;
	private String oldCharset;
	
	public GetRequest(HttpServletRequest request, String charset, String oldCharset) {
		super(request);
		this.request = request;
		this.charset = charset;
		this.oldCharset = oldCharset;
	}

	@Override
	public String getParameter(String name) {
		// 获取参数
		String value = request.getParameter(name);
		if(value == null) return null;//如果为null，直接返回null
		try {
			// 对参数进行编码处理后返回
			return new String(value.getBytes(oldCharset), charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map getParameterMap() {
		Map<String,String[]> map = request.getParameterMap();
		if(map == null) return map;
		// 遍历map，对每个值进行编码处理
		for(String key : map.keySet()) {
			String[] values = map.get(key);
			for(int i = 0; i < values.length; i++) {
				try {
					values[i] = new String(values[i].getBytes(oldCharset), charset);
				} catch (UnsupportedEncodingException e) {
					throw new RuntimeException(e);
				}
			}
		}
		// 处理后返回
		return map;
	}
	
	@Override
	public String[] getParameterValues(String name) {
		String[] values = super.getParameterValues(name);
		for(int i = 0; i < values.length; i++) {
			try {
				values[i] = new String(values[i].getBytes(oldCharset), charset);
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		}
		return values;
	}
}

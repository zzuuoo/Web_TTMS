package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class CodeFilter
 */
//@WebFilter("/CodeFilter")
@WebFilter(urlPatterns = "/*", initParams =
{ @WebInitParam(name = "encoding", value = "UTF-8") })
public class CodeFilter implements Filter {

	private String encoding = null;
    /**
     * Default constructor. 
     */
    public CodeFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		 HttpServletRequest httpreq = (HttpServletRequest) request;
	        if(httpreq.getMethod().equalsIgnoreCase("POST"))
	        {
	            // post方式发送，直接设置字符集
	            request.setCharacterEncoding(encoding);
	            System.out.println("codefilter have run");
	        }

		// pass the request along the filter chain
		chain.doFilter(request, response);
		 System.out.println("codefilter have run again");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		encoding = fConfig.getInitParameter("encoding");
	}

}

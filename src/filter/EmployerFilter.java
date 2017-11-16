package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * 管理者过滤器
 * Servlet Filter implementation class EmployerFilter
 */
@WebFilter("/employer/*")
public class EmployerFilter implements Filter {

    /**
     * Default constructor. 
     */
    public EmployerFilter() {
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
		 System.out.println("进入过滤器EmployerFilter");
	        HttpServletRequest req = (HttpServletRequest) request;
	        String flag = (String) req.getSession().getAttribute("b");
	        if (flag == null || !flag.equals("ok"))
	        {
	            System.out.println("无权访问b路径");
	            request.setAttribute("desc", "无权访问b路径");
	            request.getRequestDispatcher("/error.jsp").forward(request, response);
	        }
	   
	        chain.doFilter(request, response);
	        
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

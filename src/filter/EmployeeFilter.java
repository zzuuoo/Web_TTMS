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
 * 普通用户过滤器
 * @author SHEEO
 *
 */
@WebFilter("/employee/*")
public class EmployeeFilter implements Filter{

	 public void destroy()
	    {
	    }
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		  System.out.println("进入过滤器EmployeeFilter");
	        HttpServletRequest req = (HttpServletRequest) request;
	        String flag = (String) req.getSession().getAttribute("employee");
	        if (flag == null || !flag.equals("ok"))
	        {
	            System.out.println("无权访问employee路径");
	            request.setAttribute("desc", "无权访问employee路径");
	            request.getRequestDispatcher("/error.jsp").forward(request, response);
	        }
	        
	        chain.doFilter(request, response);
		
	}

	 public void init(FilterConfig fConfig) throws ServletException
	    {
	    }
}

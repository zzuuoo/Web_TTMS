package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import bean.Flag;
import bean.employee;
import bean.user;
import net.sf.json.JSONObject;
import service.EmployeeSrv;
import service.UserSrv;

/**
 * Servlet implementation class LoginCheck
 */
@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		  PrintWriter out = response.getWriter();
		System.out.println("清了session");
		  request.getSession().invalidate();//清空所有session
		  
		  //获取登录信息
	      request.setCharacterEncoding("utf-8");
	      String name = request.getParameter("username");
	      String password = request.getParameter("password");
//	      String remeber = request.getParameter("rememberme");
	      Flag flag = new Flag();
	      flag.setFlag("no");
	      String emp_name="游客";
	      
	      
//	      boolean checkOK = false;
	      
	      user us=null;
	   
	  		// TODO Auto-generated method stub
	  		List<user> userList = new UserSrv().Fetch("emp_no='"+name+"'");		
      
			for(user u:userList) {
				if(name.equals(u.getEmp_no())&&password.equals(u.getEmp_pass())) {
					flag.setFlag("yes");
					us=u;
				}
			}
			
			if(flag.getFlag().equals("yes")){

//				request.getSession().setAttribute("login", "ok");
//				request.getSession().setAttribute("employee", lemp.get(0));
//				request.getSession().setAttribute("user", us);
				
//				request.setAttribute("emp_no", name);
//				request.setAttribute("emp_name", lemp.get(0).getEmp_name());
				List<employee> lemp = new EmployeeSrv().Fetch("emp_no = '"+name+"'");
				emp_name=lemp.get(0).getEmp_name();
				if(us.getType()==1) {		
			            request.getSession().setAttribute("employer", "ok");
				}else {
		            request.getSession().setAttribute("employee", "ok");
				}
			}else{
				 request.getSession().setAttribute("desc", "账号或密码错误");
			}
			
			//反馈情况
			JsonObject jsobjcet = new JsonObject();
	        jsobjcet.addProperty("flag", flag.getFlag());
	        jsobjcet.addProperty("type", us.getType());
	        jsobjcet.addProperty("emp_no",name);
	        jsobjcet.addProperty("emp_name",emp_name);
			out.write(jsobjcet.toString());
			out.close();		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
	      int type=0;
	      
	      
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
				List<employee> lemp = new EmployeeSrv().Fetch("emp_no = '"+name+"'");
				 //1. 现在服务器端创建一个cookie  
				emp_name=lemp.get(0).getEmp_name();
	            Cookie emp_no1=new Cookie("emp_no",name);  
	            Cookie emp_name1=new Cookie("emp_name",emp_name);  
	            Cookie head_type=new Cookie("head_path",us.getHead_path()); 
	            Cookie emp_pass=new Cookie("emp_pass",us.getEmp_pass()); 
	            Cookie emp_addr = new Cookie("emp_addr", lemp.get(0).getEmp_addr());
	            Cookie emp_email =new Cookie("emp_email",lemp.get(0).getEmp_email());
	            Cookie emp_id = new Cookie("emp_id", lemp.get(0).getEmp_id()+"");
	            Cookie emp_tel_num  = new Cookie("emp_tel_num", lemp.get(0).getEmp_tel_num());
	              
	            //3. 将该cookie写回到客户端  
	            response.addCookie(emp_no1);  
	            response.addCookie(emp_name1);
	            response.addCookie(head_type);
	            response.addCookie(emp_pass);
	            response.addCookie(emp_addr);
	            response.addCookie(emp_email);
	            response.addCookie(emp_tel_num);
	            response.addCookie(emp_id);
	            

//				request.getSession().setAttribute("login", "ok");
//				request.getSession().setAttribute("employee", lemp.get(0));
//				request.getSession().setAttribute("user", us);
				
//				request.setAttribute("emp_no", name);
//				request.setAttribute("emp_name", lemp.get(0).getEmp_name());
				
				if(us.getType()==1) {		
			            request.getSession().setAttribute("employer", "ok");
				}else {
		            request.getSession().setAttribute("employee", "ok");
				}
				
				type=us.getType();
				
			}else{
				 request.getSession().setAttribute("desc", "账号或密码错误");
			}
			

			
			//反馈情况
			PrintWriter out = response.getWriter();
			JsonObject jsobjcet = new JsonObject();
	        jsobjcet.addProperty("flag", flag.getFlag());
	        jsobjcet.addProperty("type", type);
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

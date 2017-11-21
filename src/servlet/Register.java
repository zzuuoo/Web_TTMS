package servlet;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		  response.setCharacterEncoding("UTF-8");s
		  PrintWriter out = response.getWriter();
//		  JSONObject jsStr = JSONObject.parseObject(requestBody); //将字符串{“id”：1}
		  Flag result = new Flag();
		  result.setFlag("no");
		  int flag_e=0;
		  int flag_u=0;
		  //获取前端数据
		  String emp_no = request.getParameter("emp_no");
		  
	      String emp_name = request.getParameter("emp_name");
	      String emp_tel_num = request.getParameter("emp_tel_num");
	      String emp_addr = request.getParameter("emp_addr");
	      String emp_email = request.getParameter("emp_email");
	      
	      int type = new Integer(request.getParameter("type"));      
	      String emp_pass = request.getParameter("emp_pass");
	      String Head_path = request.getParameter("Head_path");
	      
	      EmployeeSrv eSrv = new EmployeeSrv();
	      
	      //检查该账号是否已被注册，否则注册
	      if(eSrv.Fetch(" emp_no = '"+ emp_no+"'")!=null) {
	      
	      //插入employee数据
	      employee e = new employee();
	      e.setEmp_name(emp_name);
	      e.setEmp_no(emp_no);
	      e.setEmp_tel_num(emp_tel_num);
	      e.setEmp_email(emp_email);
	      e.setEmp_addr(emp_addr);
	      flag_e=eSrv.add(e);
	      

	      //插入user数据
	      user u = new user();
	      u.setEmp_no(emp_no);
	      u.setEmp_pass(emp_pass);
	      u.setType(type);
	      u.setHead_path(Head_path);
	      UserSrv uSrv = new UserSrv();
	      flag_u=uSrv.add(u);
	      if(flag_u==1&&flag_e==1) {
	    	  
	    	  result.setFlag("yes");
	      }
	      //
	      }
	      //回答
	  	//反馈情况
			JsonObject jsobjcet = new JsonObject();
	        jsobjcet.addProperty("flag", result.getFlag());
	        
			out.write(jsobjcet.toString());
			out.close();
	        System.out.println(jsobjcet.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

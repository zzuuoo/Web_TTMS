package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;

import bean.employee;
import bean.studio;
import bean.user;
import net.sf.json.JSONArray;
import service.EmployeeSrv;
import service.StudioSrv;
import service.UserSrv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Servlet implementation class Check
 */
//@WebServlet("/Check")
public class Check extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Check() {
        super();
        // TODO Auto-generated constructor stub
    }
     
    public void init() throws ServletException
    {
        // 执行必需的初始化
       
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		System.out.println("清了session");
//         request.getSession().setAttribute("login", null);
//         request.getSession().setAttribute("a", null);
//         request.getSession().setAttribute("b", null);

		  request.getSession().invalidate();//清空所有session
		  
		  //获取登录信息
		  String page = "/error.jsp";
	      request.setCharacterEncoding("utf-8");
	      String name = request.getParameter("username");
	      String password = request.getParameter("password");
//	      String remeber = request.getParameter("rememberme");
	      
	      
	      boolean checkOK = false;
	      
	      user us=null;
	   
	  		// TODO Auto-generated method stub
	  		List<user> userList = new UserSrv().Fetch("emp_no='"+name+"'");		
      
			for(user u:userList) {
				if(name.equals(u.getEmp_no())&&password.equals(u.getEmp_pass())) {
					checkOK=true;
					us=u;
				}
			}
			List<employee> lemp = new EmployeeSrv().Fetch("emp_no = '"+name+"'");
			if(checkOK){

				request.getSession().setAttribute("login", "ok");
				request.setAttribute("emp_no", name);
				request.setAttribute("emp_name", lemp.get(0).getEmp_name());
				if(us.getType()==1) {		
			            request.getSession().setAttribute("employee", "ok");
				}else {
		            request.getSession().setAttribute("employer", "ok");
				}
//				page="/Lmain.html";
				page="/dir.jsp";
			}else{
				 request.getSession().setAttribute("desc", "账号或密码错误");
				 page="/error.jsp";
			}
			 request.getRequestDispatcher(page).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

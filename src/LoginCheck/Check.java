package LoginCheck;

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

import bean.UserInfo;
import database.DBUtil;
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
		
		
	      request.setCharacterEncoding("utf-8");
	      String name = request.getParameter("Account");
	      String password = request.getParameter("Password");
	      String remeber = request.getParameter("rememberme");
	      
	      response.setCharacterEncoding("utf-8");
	      response.setContentType("text/html;charset=utf-8");
	      PrintWriter out = response.getWriter();
	      
	      boolean checkOK = false;
	      
	      
	   
	  		// TODO Auto-generated method stub
	  		List<UserInfo> userList = new UserSrv().FetchAll();
//	  		UserInfo user;
//	  		userList=new LinkedList<UserInfo>();
//	  		try {
//	  			String sql = "select name, password from user";
//	  			
//	  			DBUtil db = new DBUtil();
//	  			if(!db.openConnection()){
//	  				System.out.print("fail to connect database");
//	  				return ;
//	  			}
//	  			ResultSet rst = db.execQuery(sql);
//	  			if (rst!=null) {
//	  				while(rst.next()){
//	  					user=new UserInfo(rst.getString("name"),rst.getString("password"));
//	  					userList.add(user);
//	  				}
//	  			}
//	  			db.close(rst);
//	  			db.close();
//	  		} catch (Exception e) {
//	  			e.printStackTrace();
//	  		}
//	  			
	  	
	      
	      
	      
			for(UserInfo u:userList) {
				if(name.equals(u.getName())&&password.equals(u.getPassword())) {
					checkOK=true;
				}
			}
			if(checkOK){
				//通过cookie保存账号密码
//				if(remeber!="") {
//				Cookie ck = new Cookie("Account",name);
//				ck.setMaxAge(60*60*24*7);
//				response.addCookie(ck);
//				ck = new Cookie("Password",password);
//				ck.setMaxAge(60*60*24*7);
//				response.addCookie(ck);
//				}
				RequestDispatcher  dispatcher   = 
				           request.getRequestDispatcher("Lmain.html");
				//将request、response可一起带到新页面
				dispatcher.forward(request,response);
			}else{
				RequestDispatcher  dispatcher   = 
				           request.getRequestDispatcher("LoginFailed.html");
				//将request、response可一起带到新页面
				dispatcher.forward(request,response);
				
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Flag;
import bean.employee;
import bean.seat;
import bean.studio;
import dao.SeatDAO;
import idao.DAOFactory;
import idao.iEmployeeDAO;
import net.sf.json.JSONObject;
import service.StudioSrv;

/**
 * Servlet implementation class StudioServlet
 */
@WebServlet("/StudioServlet")
public class StudioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				 String method = request.getParameter("method");
				 System.out.println(method+"studioddd");
			        if (method.equalsIgnoreCase("add"))
			            add(request, response);
			        else if (method.equalsIgnoreCase("delete"))
			            delete(request, response);
			        else if (method.equalsIgnoreCase("update"))
			            update(request, response);
			        else if (method.equalsIgnoreCase("search"))
			            search(request, response);
			        else if (method.equalsIgnoreCase("searchById"))
			            searchById(request, response);
			        else if (method.equalsIgnoreCase("searchByPage"))
			            searchByPage(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	 private void add(HttpServletRequest request, HttpServletResponse response)
	    {
		 PrintWriter out=null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  Flag result= new Flag();
		  result.setFlag("no");
		  int flag=0;
		//获取前端数据
		  String studio_name = request.getParameter("studio_name");
		  int  studio_row_count = new Integer(request.getParameter("studio_row_count"));
		  int studio_col_count = new Integer(request.getParameter("studio_col_count"));//列
		  String studio_introduction = request.getParameter("studio_introduction");
		  
		  int studio_flag = new Integer(request.getParameter("studio_flag"));
		// 0：尚未生成座位，可以根据行列信息生成座位\r\n  1：已经生成座位

//		保存到数据库中
	      studio  s =new studio();
	      s.setStudio_name(studio_name);
	      s.setStudio_col_count(studio_col_count);
	      s.setStudio_flag(studio_flag);
	      s.setStudio_row_count(studio_row_count);
	      s.setStudio_introduction(studio_introduction);
	      
	      StudioSrv srv = new StudioSrv();
	      flag=srv.add(s);
	      
		if(flag==1) {
			System.out.println(s.getStudio_id()+"sid");
			SeatDAO sDao = new SeatDAO();
			seat st = null;
//			成功,设置flag返回信息给前端，插入成功了
			 result.setFlag("yes");
			 for(int i=1;i<=s.getStudio_row_count();i++) {
				 for(int j=1;j<=s.getStudio_col_count();j++) {
					 st = new seat();
					 st.setSeat_row(i);
					 st.setSeat_column(j);
					 st.setSeat_status(1);
					 st.setStudio_id(s.getStudio_id());
					 sDao.insert(st);
					
				 }
			 }
			 
		}
		//反馈情况
		JSONObject object = JSONObject.fromObject(result);
		out.write(object.toString());
		out.close();
	    }

	    private void delete(HttpServletRequest request, HttpServletResponse response)
	    {
	    	//获取回应流，设立flag
			 PrintWriter out=null;
			 System.out.println("studio_delete");
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  Flag result=new Flag();
			  result.setFlag("no");
			  int flag=0;
			  //获取前端数据
			  int studio_id = new Integer(request.getParameter("studio_id"));
			  //执行删除
			  StudioSrv srv = new StudioSrv();
			  flag=srv.delete(studio_id);
			  //判断是否删除成功
			  if(flag==1) {
				  result.setFlag("yes");
			  }
			  //返回结果
			  JSONObject object = JSONObject.fromObject(result);
			  out.write(object.toString());
			  out.close();
	    }

	    private void update(HttpServletRequest request, HttpServletResponse response)
	    {
	    	  PrintWriter out=null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  Flag result= new Flag();
			  result.setFlag("no");
			//获取前端数据
			  int studio_id = new Integer(request.getParameter("studio_id"));
			  String studio_name = request.getParameter("studio_name");
			  int  studio_row_count = new Integer(request.getParameter("studio_row_count"));
			  int studio_col_count = new Integer(request.getParameter("studio_col_count"));//列
			  String studio_introduction = request.getParameter("studio_introduction");
			  
			  int studio_flag = new Integer(request.getParameter("studio_flag"));
			// 0：尚未生成座位，可以根据行列信息生成座位\r\n  1：已经生成座位

//			保存到数据库中
		      studio  s =new studio();
		      s.setStudio_id(studio_id);
		      s.setStudio_name(studio_name);
		      s.setStudio_col_count(studio_col_count);
		      s.setStudio_flag(studio_flag);
		      s.setStudio_row_count(studio_row_count);
		      s.setStudio_introduction(studio_introduction);
		      
		      StudioSrv srv = new StudioSrv();
			if(srv.modify(s)==1) {
//				成功,设置flag返回信息给前端，插入成功了
				System.out.println(s.getStudio_id()+"sid");
				SeatDAO sDao = new SeatDAO();
				seat st = null;
//				成功,设置flag返回信息给前端，插入成功了
				 result.setFlag("yes");
				 for(int i=1;i<=s.getStudio_row_count();i++) {
					 for(int j=1;j<=s.getStudio_col_count();j++) {
						 st = new seat();
						 st.setSeat_row(i);
						 st.setSeat_column(j);
						 st.setSeat_status(1);
						 st.setStudio_id(s.getStudio_id());
						 sDao.insert(st);
						
					 }
				 }
			}
			//反馈情况
			 JSONObject object = JSONObject.fromObject(result);
			  out.write(object.toString());
			out.close();
	    }

	    private void search(HttpServletRequest request, HttpServletResponse response)
	    {
	        String emp_name = request.getParameter("emp_name");
	        iEmployeeDAO dao = DAOFactory.createEmployeeDAO();
	        List<employee> list = null;
	        if (emp_name == null || emp_name.equals(""))
	            list = dao.selectAll();
	        else
	            list = dao.selectwhat(" emp_name = '"+emp_name+"' ");
	        try
	        {
	            request.setAttribute("search_emp_name", emp_name);
	            request.setAttribute("list", list);
	            request.getRequestDispatcher("index.jsp").forward(request, response);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	    }

	    private void searchById(HttpServletRequest request, HttpServletResponse response)
	    {
	        int emp_id = Integer.parseInt(request.getParameter("emp_id"));
	        if (emp_id > 0)
	        {
	            iEmployeeDAO dao =  DAOFactory.createEmployeeDAO();
	            employee emp = dao.select(" emp_id = "+emp_id);
	            request.setAttribute("employee", emp);
	            try
	            {
	                request.getRequestDispatcher("update.jsp").forward(request, response);
	            }
	            catch (Exception e)
	            {
	                e.printStackTrace();
	            }
	        }
	    }

	    private void searchByPage(HttpServletRequest request, HttpServletResponse response)
	    {
	        int currentPage = 1; // 当前页默认为第一页
	        String strpage = request.getParameter("currentPage"); // 获取前台传入当前页
	        if (strpage != null && !strpage.equals(""))
	        {
	            currentPage = Integer.parseInt(strpage) < 1 ? 1 : Integer.parseInt(strpage); // 将字符串转换成整型
	        }
	        String emp_name = request.getParameter("emp_name");
	        iEmployeeDAO dao = DAOFactory.createEmployeeDAO();
	        // 从UserDAO中获取所有用户信息
	        List<employee> list = dao.findEmployeeByPage(currentPage, emp_name);
//	         从UserDAO中获取总记录数
	        int allCount = dao.getAllCount();
	        // 从UserDAO中获取总页数
	        int allPageCount = dao.getAllPageCount();
	        // 从UserDAO中获取当前页
	        currentPage = dao.getCurrentPage();

	        // 存入request中
	        request.setAttribute("allEmployee", list);
	        request.setAttribute("allCount", allCount);
	        request.setAttribute("allPageCount", allPageCount);
	        request.setAttribute("currentPage", currentPage);
	        request.setAttribute("search_emp_name", emp_name);

	        try
	        {
	            request.getRequestDispatcher("index1.jsp").forward(request, response);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	    }

}

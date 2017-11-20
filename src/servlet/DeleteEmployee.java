package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Flag;
import net.sf.json.JSONObject;
import service.EmployeeSrv;

/**
 * Servlet implementation class DeleteEmployee
 */
@WebServlet("/DeleteEmployee")
public class DeleteEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  response.setCharacterEncoding("UTF-8");
		  PrintWriter out = response.getWriter();
		  Flag result = new Flag();
		  result.setFlag("no");
		  int flag=0;
		  //获取前端数据
		  int emp_id = new Integer(request.getParameter("emp_id"));
		  
		  EmployeeSrv eSrv = new EmployeeSrv();
		  flag=eSrv.delete(emp_id);
		  if(flag==1) {
			  result.setFlag("yes");
		  }
			JSONObject object = JSONObject.fromObject(result);
		  out.write(object.toString());
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

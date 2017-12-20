package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.employee;
import dao.EmployeeDao;
import idao.DAOFactory;
import idao.iEmployeeDAO;
import net.sf.json.JSONArray;
import service.EmployeeSrv;

/**
 * Servlet implementation class GetAllEmployee
 */
@WebServlet("/GetAllEmployee")
public class GetAllEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("获取所有员信息");
		response.setCharacterEncoding("UTF-8");
		int page = Integer.valueOf(request.getParameter("page"));
//		int limit = Integer.valueOf(request.getParameter("limit"));
		//获取所有演出厅信息
		iEmployeeDAO employeeDao = DAOFactory.createEmployeeDAO();
		List<employee> lEmployees1 = new EmployeeSrv().FetchAll();
		List<employee> lEmployees = employeeDao.findEmployeeByPage(page, null);
		System.out.println("employee:"+page+",s:"+lEmployees1.size());
//		lEmployees.size();
		//把演出厅信息转换为json类型
		JSONArray jsonArr = JSONArray.fromObject(lEmployees);
		//回答
		String h="{\"code\":0,\"msg\":\"\",\"count\":"+lEmployees1.size()+",\"data\":";
		response.setContentType("json");
        PrintWriter out = response.getWriter();
        out.write(h+jsonArr.toString()+"}");
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

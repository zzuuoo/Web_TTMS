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

import bean.Flag;
import bean.employee;
import idao.DAOFactory;
import idao.iEmployeeDAO;
import net.sf.json.JSONObject;


/**
 * Servlet implementation class EmplloyeeServlet
 */
@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String method = request.getParameter("method");
		 System.out.println(method);
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
	        String emp_no = request.getParameter("emp_no");
	        String emp_name = request.getParameter("emp_name");
	        System.out.println(emp_name);
	        String emp_tel_num = request.getParameter("emp_tel_num");
	        String emp_addr = request.getParameter("emp_addr");
	        String emp_email = request.getParameter("emp_email");
	        employee emp = new employee();
	        emp.setEmp_no(emp_no);
	        emp.setEmp_name(emp_name);
	        emp.setEmp_tel_num(emp_tel_num);
	        emp.setEmp_addr(emp_addr);
	        emp.setEmp_email(emp_email);
	        iEmployeeDAO dao =  DAOFactory.createEmployeeDAO();
	        Flag result = new Flag();
			 result.setFlag("no");
			 result.setFlag(dao.insert(emp)+"");
	        try
	        {
	            if (result.getFlag().equals("1"))
	                request.setAttribute("result", "添加成功!");
	            else
	                request.setAttribute("result", "添加失败!");
	            JSONObject object = JSONObject.fromObject(result);
	            PrintWriter out = response.getWriter();
	    		out.write(object.toString());
	    		out.close();
//	            request.getRequestDispatcher("add.jsp").forward(request, response);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	    }

	    private void delete(HttpServletRequest request, HttpServletResponse response)
	    {
	        int result = 0;
	        System.out.println("进了deleteemployee");
	        int emp_id = Integer.parseInt(request.getParameter("emp_id"));
	        if (emp_id > 0)
	        {
	            iEmployeeDAO dao =  DAOFactory.createEmployeeDAO();
	            result = dao.delete(emp_id);
	            if (result==1)
//	                request.setAttribute("result", "删除成功!");
	            	System.out.println("删除成功");
	            else
//	                request.setAttribute("result", "删除失败!");
	            	System.out.println("删除失败");
	            // 不分页时删除调用全查
//	            search(request, response);
	            // 分页时删除调用分页全查:使用分页index1.jsp时，把这里注释打开
	            // searchByPage(request, response);
	        }
	    }

	    private void update(HttpServletRequest request, HttpServletResponse response)
	    {
	        int emp_id = Integer.parseInt(request.getParameter("emp_id"));
	        String emp_no = request.getParameter("emp_no");
	        String emp_name = request.getParameter("emp_name");
	        String emp_tel_num = request.getParameter("emp_tel_num");
	        String emp_addr = request.getParameter("emp_addr");
	        String emp_email = request.getParameter("emp_email");
	        employee emp = new employee();
	        System.out.println("employeeuuuu");
	        emp.setEmp_id(emp_id);
	        emp.setEmp_no(emp_no);
	        emp.setEmp_name(emp_name);
	        emp.setEmp_tel_num(emp_tel_num);
	        emp.setEmp_addr(emp_addr);
	        emp.setEmp_email(emp_email);
	        iEmployeeDAO dao = DAOFactory.createEmployeeDAO();
	        int result = dao.update(emp);
	        
	        try
	        {
	            if (result==1) {
	            	request.setAttribute("result", "更新成功!");
	            	if(request.getParameter("edit")!=null) {
	            		Cookie emp_name1=new Cookie("emp_name",emp_name);       	      
	     	            Cookie emp_addr1 = new Cookie("emp_addr", emp_addr);
	     	            Cookie emp_email1 =new Cookie("emp_email",emp_email);
	     	            Cookie emp_tel_num1  = new Cookie("emp_tel_num", emp_tel_num);
	            		response.addCookie(emp_name1);
	     	            response.addCookie(emp_addr1);
	     	            response.addCookie(emp_email1);
	     	            response.addCookie(emp_tel_num1);
	            	}
	            }
	                
	            else
	                request.setAttribute("result", "更新失败!");
	            request.setAttribute("employee", emp);
	           
//	            request.getRequestDispatcher("update.jsp").forward(request, response);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
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

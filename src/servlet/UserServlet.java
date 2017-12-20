package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import bean.Flag;
import bean.employee;
import bean.user;
import idao.DAOFactory;
import idao.iEmployeeDAO;
import idao.iUserDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		super.doGet(request, response); 
		String method ;
		 method= request.getParameter("method");
		 System.out.println("方法："+method);
//		 System.out.println(request.getParameter("type"));
		 method=method+"";
	        if (method.equalsIgnoreCase("add"))
	            add(request, response);
	        else if (method.equalsIgnoreCase("delete"))
	            delete(request, response);
	        else if (method.equalsIgnoreCase("update"))
	            update(request, response);
	        else if (method.equalsIgnoreCase("search"))
	            search(request, response);
	        else if (method.equalsIgnoreCase("searchByPage"))
	            searchByPage(request, response);
	        else if(method.equalsIgnoreCase("getEmp_no"))
	        	getEmp_no(request, response);
	        else {
	        	System.out.println("没有？");
	        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		super.doPost(request, response);
		doGet(request, response);
	}
	
	private void getEmp_no(HttpServletRequest request, HttpServletResponse response) {
		iEmployeeDAO iEmp = DAOFactory.createEmployeeDAO();
		List<employee> le = iEmp.selectAll();
		List<user> users = DAOFactory.createUserDAO().selectAll();
		
		List<employee> la=new ArrayList<>();
		int f=0;
		for(employee e:le) {
			for(user u:users) {
				if(e.getEmp_no().equals(u.getEmp_no())) {
					f=1;
				}
			}
			if(f==0) {
				la.add(e);
			}
			f=0;
		}
		
		JSONArray jsonArr = JSONArray.fromObject(la);
		//回答
		
		response.setContentType("json");
        PrintWriter out=null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			 out.write(jsonArr.toString());
		     out.close();
		}
       System.out.println("获取employee");
		
	}

	 private void add(HttpServletRequest request, HttpServletResponse response)
	    {

		 System.out.println("add_user");
	        user info = new user();
	        
	        String paramName = "", paramValue = "";
	        // 创建该对象
	        DiskFileItemFactory dff = new DiskFileItemFactory();
	        // 指定在内存中缓存数据大小,单位为byte
	        dff.setSizeThreshold(1024000);
	        // 创建上传对象
	        ServletFileUpload sfu = new ServletFileUpload(dff);
	        // sfu.setHeaderEncoding("UTF-8");
	        // 指定单个上传文件的最大尺寸(单个文件大小不超过2M)
	        sfu.setFileSizeMax(1024 * 1024 * 2);
	        // 指定一次上传多个文件的总尺寸(总文件大小不超过6M)
	        // sfu.setSizeMax(1024 * 1024 * 6);
	        try
	        {
	            List<FileItem> uploaditems = sfu.parseRequest(request);
	            for (FileItem fileItem : uploaditems)
	            {
	                // 对应表单中的控件的name
	                paramName = fileItem.getFieldName();
	                System.out.print(paramName + ":");

	                if (fileItem.isFormField())
	                {
	                    // 1.如果是普通表单控件，获取文本框中数据
	                    // 重新编码,解决乱码
	                    paramValue = new String(fileItem.getString().getBytes("ISO-8859-1"), "UTF-8");
	                    System.out.println(paramValue);
	                    if ("emp_no".equals(paramName))
	                    	info.setEmp_no(paramValue);
	                    if ("emp_pass".equals(paramName))
	                        info.setEmp_pass(paramValue);
	                    if ("type".equals(paramName)) {
	                    	if("管理员".equals(paramValue)) {
	                    		info.setType(1);
	                    	}else {
	                    		info.setType(0);
	                    	}
	                    }
	                        
	                }
	                else
	                {
	                    // 2.如果是上传文件，则保存在服务器端应用根目录
	                    // 获得文件大小
	                    Long size = fileItem.getSize();
	                    // 获得全路径文件名
	                    String fileName = fileItem.getName();
	                    // 截取文件名
	                    fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
	                    System.out.println("\n文件名：" + fileName + "\t大小：" + size + "byte");

	                    // 将文件保存到指定的路径
	                    File file = new File(getServletContext().getRealPath("/") + fileName);
	                    fileItem.write(file);
	                    info.setHead_path(fileName);
	                }
	            }
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        
	        
	        iUserDao iDao = DAOFactory.createUserDAO();
	        Flag result = new Flag();
			result.setFlag("0");
			result.setFlag(iDao.insert(info)+"");
	        try
	        {
	            if (result.getFlag().equals("1"))
	                request.setAttribute("result", "添加成功!");
	            else
	                request.setAttribute("result", "添加失败!");
//	            JSONObject object = JSONObject.fromObject(result);
//	            PrintWriter out = response.getWriter();
//	    		out.write(object.toString());
//	    		out.close();
	    		response.sendRedirect("./employer/usertable.html");
//	    		return;	
//	            request.getRequestDispatcher("./jump.jsp").forward(request, response);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	    }

	    private void delete(HttpServletRequest request, HttpServletResponse response)
	    {
	        int result = 0;
	        String emp_no = request.getParameter("emp_no");
	        iUserDao iDao = DAOFactory.createUserDAO();
	        result = iDao.delete(emp_no);
	        if (result==1) {
//	        	  request.setAttribute("result", "删除成功!");
	        	System.out.println("删除成功");
	        	 PrintWriter out=null;
				try {
					out = response.getWriter();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	             out.write("删除成功");
	             out.close();
	        }
	            
	        else {
//	        	 request.setAttribute("result", "删除失败!");
	        	System.out.println("删除失败");
	        }
	        // 不分页时删除调用全查
//	        search(request, response);
	        // 分页时删除调用分页全查:使用分页index1.jsp时，把这里注释打开
	        // searchByPage(request, response);
	        
	    }

	    private void update(HttpServletRequest request, HttpServletResponse response)
	    {
	    	 String emp_no = request.getParameter("emp_no");
		     String emp_pass = request.getParameter("emp_pass");
		     String head_path = request.getParameter("head_path");
		     String type = request.getParameter("type");
		     user info = new user();
		     info.setEmp_no(emp_no);
		     info.setEmp_pass(emp_pass);
		     info.setHead_path(head_path);
		     info.setType(Integer.parseInt(type));
		     iUserDao iDao = DAOFactory.createUserDAO();
	        int result = iDao.update(info);
	        try
	        {
	            if (result==1)
	                request.setAttribute("result", "更新成功!");
	            else
	                request.setAttribute("result", "更新失败!");
	            request.setAttribute("user", info);
	           
//	            request.getRequestDispatcher("update.jsp").forward(request, response);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	    }

	    private void search(HttpServletRequest request, HttpServletResponse response)
	    {
	        String emp_no = request.getParameter("emp_no");
	        iUserDao dao = DAOFactory.createUserDAO();
	        List<user> list = null;
	        if (emp_no == null || emp_no.equals(""))
	            list = dao.selectAll();
	        else
	            list = dao.selectwhat(" emp_no = '"+emp_no+"' ");
	        try
	        {
	            request.setAttribute("search_emp_no", emp_no);
	            request.setAttribute("list", list);
//	            request.getRequestDispatcher("index.jsp").forward(request, response);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
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
	        String emp_no = request.getParameter("emp_no");
	        iUserDao dao = DAOFactory.createUserDAO();
	        // 从UserDAO中获取所有用户信息
	        List<user> list = dao.findUserByPage(currentPage, emp_no);
//	         从UserDAO中获取总记录数
	        int allCount = dao.getAllCount();
	        // 从UserDAO中获取总页数
	        int allPageCount = dao.getAllPageCount();
	        // 从UserDAO中获取当前页
	        currentPage = dao.getCurrentPage();

	        // 存入request中
	        request.setAttribute("allUser", list);
	        request.setAttribute("allCount", allCount);
	        request.setAttribute("allPageCount", allPageCount);
	        request.setAttribute("currentPage", currentPage);
	        request.setAttribute("search_emp_no", emp_no);

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

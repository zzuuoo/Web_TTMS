package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.Request;

import bean.studio;
import bean.user;
import idao.DAOFactory;
import idao.iUserDao;
import net.sf.json.JSONArray;
import service.StudioSrv;
import service.UserSrv;

/**
 * Servlet implementation class GetAllUser
 */
@WebServlet("/GetAllUser")
public class GetAllUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("获取所有User信息");
		response.setCharacterEncoding("UTF-8");
		int page = Integer.valueOf(request.getParameter("page"));
		//获取所有信息
		iUserDao userDao = DAOFactory.createUserDAO();
		List<user> lUsers1 = new UserSrv().FetchAll();
		List<user> lUsers = userDao.findUserByPage(page, null);
		//把信息转换为json类型
		JSONArray jsonArr = JSONArray.fromObject(lUsers);
		//回答
//		String h="{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":";
		System.out.println(lUsers1.size());
		String h="{\"code\":0,\"msg\":\"\",\"count\":"+lUsers1.size()+",\"data\":";
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

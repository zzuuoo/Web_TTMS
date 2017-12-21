package servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.studio;
import dao.StudioDao;
import idao.DAOFactory;
import idao.iStudioDAO;
import net.sf.json.JSONArray;
import service.StudioSrv;

/**
 * Servlet implementation class GetAllStudio
 */
//@WebServlet("/GetAllStudio")
public class GetAllStudio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllStudio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("获取所有演出厅信息");
		//字符流设置编码
		response.setCharacterEncoding("UTF-8");
		int page = Integer.valueOf(request.getParameter("page"));
		//获取所有演出厅信息
		List<studio> lStudios = new StudioSrv().FetchAll();
		List<studio> lStudios2=null;
		try {
			lStudios2 = new StudioDao().PagingQuery(page);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(lStudios.get(0).getStudio_name());
		//把演出厅信息转换为json类型
		JSONArray jsonArr = JSONArray.fromObject(lStudios2);
		
		String h="{\"code\":0,\"msg\":\"\",\"count\":"+lStudios.size()+",\"data\":";
		response.setContentType("json");
        PrintWriter out = response.getWriter();
        out.write(h+jsonArr.toString()+"}");
        System.out.println(jsonArr.toString()+"大小："+lStudios.size());
        out.close();
	}

}

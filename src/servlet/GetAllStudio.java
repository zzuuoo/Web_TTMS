package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.studio;
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
		response.setCharacterEncoding("UTF-8");
		//获取所有演出厅信息
		List<studio> lStudios = new StudioSrv().FetchAll();
		System.out.println(lStudios.get(0).getStudio_name());
		//把演出厅信息转换为json类型
		JSONArray jsonArr = JSONArray.fromObject(lStudios);
		//回答
        PrintWriter out = response.getWriter();
        out.write(jsonArr.toString());
        System.out.println(jsonArr.toString());
        out.close();
	}

}

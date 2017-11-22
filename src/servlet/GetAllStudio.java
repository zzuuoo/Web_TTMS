package servlet;

import java.io.IOException;
import java.io.OutputStream;
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
		//字符流设置编码
		response.setCharacterEncoding("UTF-8");
		//获取所有演出厅信息
		List<studio> lStudios = new StudioSrv().FetchAll();
		System.out.println(lStudios.get(0).getStudio_name());
		//把演出厅信息转换为json类型
		JSONArray jsonArr = JSONArray.fromObject(lStudios);
		
//		字节流
//		OutputStream ps = response.getOutputStream();  
//        //这句话的意思，使得放入流的数据是gb2312格式  //经测试，有用
//        ps.write(jsonArr.toString().getBytes("gb2312"));  
//		ps.close();
		//回答response: {
//		  statusName: 'status' //数据状态的字段名称，默认：code
//			  ,statusCode: 200 //成功的状态码，默认：0
//			  ,msgName: 'hint' //状态信息的字段名称，默认：msg
//			  ,countName: 'total' //数据总数的字段名称，默认：count
//			  ,dataName: 'rows' //数据列表的字段名称，默认：data
//			}  
		String h="{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":";
		response.setContentType("json");
        PrintWriter out = response.getWriter();
        out.write(h+jsonArr.toString()+"}");
        System.out.println(jsonArr.toString());
        out.close();
	}

}

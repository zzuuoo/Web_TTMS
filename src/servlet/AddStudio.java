package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.text.StrTokenizer;
import org.omg.CORBA.INTERNAL;

import bean.Flag;
import bean.studio;
import net.sf.json.JSONObject;
import service.StudioSrv;

/**
 * Servlet implementation class AddStudio
 */
@WebServlet("/AddStudio")
public class AddStudio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddStudio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		  PrintWriter out = response.getWriter();
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
//			成功,设置flag返回信息给前端，插入成功了
			 result.setFlag("yes");
		}
		//反馈情况
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

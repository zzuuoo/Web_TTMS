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
import service.StudioSrv;

/**
 * Servlet implementation class DeleteStudio
 */
@WebServlet("/DeleteStudio")
public class DeleteStudio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteStudio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取回应流，设立flag
		 PrintWriter out = response.getWriter();
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

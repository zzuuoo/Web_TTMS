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
import service.PlaySrv;

/**
 * Servlet implementation class DeletePlay
 */
@WebServlet("/DeletePlay")
public class DeletePlay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePlay() {
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
		  int play_id = new Integer(request.getParameter("play_id"));
		  
		  PlaySrv pSrv = new PlaySrv();
		  flag=pSrv.delete(play_id);
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

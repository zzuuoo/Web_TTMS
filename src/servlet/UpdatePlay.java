package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Flag;
import bean.play;
import net.sf.json.JSONObject;
import service.PlaySrv;

/**
 * Servlet implementation class UpdatePlay
 */
@WebServlet("/UpdatePlay")
public class UpdatePlay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePlay() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		  PrintWriter out = response.getWriter();
		  Flag result=new Flag();
		  result.setFlag("no");
		  int flag=0;
		  
		//获取前端数据
		  String play_name = request.getParameter("play_name");
		  String play_image = request.getParameter("play_image");
		  int  play_type_id = new Integer(request.getParameter("play_type_id"));
		  int play_lang_id = new Integer(request.getParameter("play_lang_id"));//列
		  String studio_play_introduction = request.getParameter("play_introduction");
		  int  play_length = new Integer(request.getParameter("play_length"));
		  int play_status = new Integer(request.getParameter("play_status"));//列
		  double play_ticket_price = new Double(request.getParameter("play_ticket_price"));


//		保存到数据库中
	      play  p =new play();
	      p.setPlay_image(play_image);
	      p.setPlay_introduction(studio_play_introduction);
	      p.setPlay_lang_id(play_lang_id);
	      p.setPlay_length(play_length);
	      p.setPlay_name(play_name);
	      p.setPlay_status(play_status);
	      p.setPlay_ticket_price(play_ticket_price);
	      p.setPlay_type_id(play_type_id);
	      
	      
	      PlaySrv srv = new PlaySrv();
	      flag=srv.modify(p);
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

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
import bean.ticket;
import net.sf.json.JSONArray;
import service.StudioSrv;
import service.TicketSrv;

/**
 * Servlet implementation class GetAllTicket
 */
@WebServlet("/GetAllTicket")
public class GetAllTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllTicket() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("获取所有信息");
		response.setCharacterEncoding("UTF-8");
		//获取所有信息
		List<ticket> lTickets = new TicketSrv().FetchAll();
		//把信息转换为json类型
		JSONArray jsonArr = JSONArray.fromObject(lTickets);
		//回答
		String h="{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":";
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

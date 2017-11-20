package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.schedule;
import bean.ticket;
import net.sf.json.JSONArray;
import service.ScheduleSrv;
import service.TicketSrv;

/**
 * Servlet implementation class GetAllSchedule
 */
@WebServlet("/GetAllSchedule")
public class GetAllSchedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllSchedule() {
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
		List<schedule> lSchedules = new ScheduleSrv().Fetch("");
		//把信息转换为json类型
		JSONArray jsonArr = JSONArray.fromObject(lSchedules);
		//回答
        PrintWriter out = response.getWriter();
        out.write(jsonArr.toString());
        System.out.println(jsonArr.toString().getBytes("UTF-8"));
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

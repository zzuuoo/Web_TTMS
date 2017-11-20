package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.seat;
import net.sf.json.JSONArray;
import service.SeatSrv;

/**
 * Servlet implementation class GetAllSeat
 */
@WebServlet("/GetAllSeat")
public class GetAllSeat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllSeat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("获取所座位信息");
		response.setCharacterEncoding("UTF-8");
		//获取所有信息
		List<seat> lSeats = new SeatSrv().FetchAll();
		//把信息转换为json类型
		JSONArray jsonArr = JSONArray.fromObject(lSeats);
		//回答
        PrintWriter out = response.getWriter();
        out.write(jsonArr.toString());
        System.out.println(jsonArr.toString());
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

package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.sale;
import bean.schedule;
import idao.iSaleDAO;
import net.sf.json.JSONArray;
import service.SaleSrv;
import service.ScheduleSrv;

/**
 * Servlet implementation class GetAllSale
 */
@WebServlet("/GetAllSale")
public class GetAllSale extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllSale() {
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
		List<sale> lSales = new SaleSrv().Fetch("");
		//把信息转换为json类型
		JSONArray jsonArr = JSONArray.fromObject(lSales);
		//回答
		String h="{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":";
		
        PrintWriter out = response.getWriter();
        out.write(jsonArr.toString());
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

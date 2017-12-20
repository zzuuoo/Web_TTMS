package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.seat;
import bean.studio;
import dao.SeatDAO;
import dao.StudioDao;

@WebServlet("/SeatManageServlet")
public class SeatManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 这样写避免空指针异常
		System.out.println("进来了");
		String mothed = request.getParameter("mothed") != null ? request.getParameter("mothed") : "";
		try {
			if (mothed.equals("searchByPage")) {
				searchByPage(request, response);
			} else if (mothed.equals("searchByStudioId")) {
				searchByStudioId(request, response);
			} else if (mothed.equals("updata")) {
					System.out.println("来了");
				updata(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 向前台分页返回影厅信息
	private void searchByPage(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int currentPage = 1; // 当前页默认为第一页
		String strpage = request.getParameter("currentPage") != null ? request.getParameter("currentPage") : ""; // 获取前台传入当前页
		if (!strpage.equals("")) {
			currentPage = Integer.parseInt(strpage) < 1 ? 1 : Integer.parseInt(strpage); // 将字符串转换成整型
		}
		StudioDao studioDao = new StudioDao();
		List<studio> studios = studioDao.PagingQuery(currentPage);
		
		request.getSession().setAttribute("allCount", studioDao.getAllCount());
		request.getSession().setAttribute("allPageCount", studioDao.getAllPageCount());
		request.getSession().setAttribute("currentPage", currentPage);
		request.getSession().setAttribute("studios", studios);
		response.sendRedirect(request.getContextPath() + "/employer/seatmanage.jsp");
	}

	// 获得影厅的id然后存取数据到Sission中再到座位管理界面
	private void searchByStudioId(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int studioId = Integer.parseInt(request.getParameter("id"));
		SeatDAO seatDao = new SeatDAO();
		List<seat> Seatlist = seatDao.select(" studio_id = "+studioId);
		StudioDao studioDao = new StudioDao();	
		List<studio> stul = studioDao.selectwhat("studio_id = "+studioId);
		HttpSession session = request.getSession();
		studio stu = stul.get(0);
		session.setAttribute("studioId", studioId);
		session.setAttribute("row", stu.getStudio_row_count());
		session.setAttribute("col", stu.getStudio_col_count());
		session.setAttribute("Seatlist", Seatlist);
		response.sendRedirect(request.getContextPath() + "/employer/SeatTwoLevel.jsp");
	}

	// 接收前端传来的ajax内容
	private void updata(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String ids = request.getParameter("ids");
		String status = request.getParameter("status");
		int studio_id =Integer.parseInt(request.getParameter("studio_id")) ;
		System.out.println(ids);
		System.out.println(status);
		System.out.println(studio_id);
		String[] idlist = ids.split(",");
		String[] split = status.split(",");

		for (int i = 0; i < idlist.length; i++) {
			if (idlist[i] != null && !"".equals(idlist[i]) && !"null".equals(idlist[i]) && split[i] != null
					&& !"".equals(split[i]) && !"null".equals(split[i])) {
				seat s= new seat();
				s.setSeat_id(Integer.parseInt(idlist[i]));
				s.setSeat_status(Integer.parseInt(split[i]));
				System.out.println(idlist[i]+"  "+split[i]);
				s.setStudio_id(studio_id);
				SeatDAO seatDao = new SeatDAO();
				System.out.println(status.toString());
				seatDao.updateSeatStatus(s);
			}
		}
		PrintWriter writer = response.getWriter();
		writer.write("修改成功！");
	}

}

package idao;

import dao.DataDictDAO;
import dao.EmployeeDao;
import dao.PlayDao;
import dao.SaleDAO;
import dao.SaleItemDAO;
import dao.ScheduleDAO;
import dao.SeatDAO;
import dao.StudioDao;
import dao.TicketDAO;
import dao.UserDao;

public class DAOFactory {

	
	public static iEmployeeDAO createEmployeeDAO() {
		return new EmployeeDao();
	}
	public static iStudioDAO createStudioDAO() {
		return new StudioDao();
	}
	public static iPlayDAO createplayDAO() {
		return new PlayDao();
	}
	public static IDataDictDAO createDataDictDAO() {
		return new DataDictDAO();
	}
	public static iSaleItemDAO createSaleItemDAO() {
		return new SaleItemDAO();
	}
	public static iScheduleDAO createScheduleDAO() {
		return new ScheduleDAO();
	}
	public static iSeatDAO createSeatDAO() {
		return  new SeatDAO();
		
	}
	public static iTicketDAO createTicketDAO() {
		return new TicketDAO();
	}
	public static iUserDao createUserDAO(){
		return new UserDao();
	}
	public static iSaleDAO createSaleDAO() {
		return new SaleDAO();
	}
}

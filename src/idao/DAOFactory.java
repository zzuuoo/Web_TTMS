package idao;

import dao.EmployeeDao;
import dao.PlayDao;
import dao.StudioDao;
import dao.UserDao;

public class DAOFactory {

	public static iUserDao creatUserDAO(){
		return new UserDao();
	}
	public static iEmployee createEmployeeDAO() {
		return new EmployeeDao();
	}
	public static iStudioDAO createStudioDAO() {
		return new StudioDao();
	}
	public static iPlayDAO createplayDAO() {
		return new PlayDao();
	}
	
}

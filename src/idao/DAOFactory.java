package idao;

import dao.EmployeeDao;
import dao.UserDao;

public class DAOFactory {

	public static iUserDao creatUserDAO(){
		return new UserDao();
	}
	public static iEmployee createEmployeeDAO() {
		return new EmployeeDao();
	}
	
}

package idao;

import dao.UserDao;

public class DAOFactory {

	public static iUserDao creatUserDAO(){
		return new UserDao();
	}
}

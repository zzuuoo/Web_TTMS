package service;

import java.util.List;

import bean.UserInfo;
import idao.DAOFactory;
import idao.iUserDao;


public class UserSrv {
	private iUserDao uDAO=DAOFactory.creatUserDAO();
	
	public int add(UserInfo u){
		return 0; 		
	}
	
	public int modify(UserInfo u){
		return 0; 		
	}
	
	public int delete(int ID){
		return 0; 		
	}
	
	public List<UserInfo> FetchAll(){
		return uDAO.selectAll();
	}
}

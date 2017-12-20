package service;

import java.util.List;

import bean.user;
import idao.DAOFactory;
import idao.iUserDao;


public class UserSrv {
	private iUserDao uDAO=DAOFactory.createUserDAO();
	
	public int add(user u){
		return uDAO.insert(u); 		
	}
	
	public int modify(user u){
		return uDAO.update(u); 		
	}
	
	public int delete(String emp_no){
		return uDAO.delete(emp_no); 		
	}
	
	public List<user> FetchAll(){
		return uDAO.selectAll();
	}
	public List<user> Fetch(String condt){
		return uDAO.selectwhat(condt);
	}
}

package service;

import java.util.List;

import bean.studio;
import bean.user;
import idao.DAOFactory;
import idao.iStudioDAO;
import idao.iUserDao;

public class StudioSrv {
	private iStudioDAO uDAO=DAOFactory.createStudioDAO();
	
	public int add(studio u){
		return uDAO.insert(u); 		
	}
	
	public int modify(studio u){
		return uDAO.update(u); 		
	}
	
	public int delete(int s_id){
		return uDAO.delete(s_id); 		
	}
	
	public List<studio> FetchAll(){
		return uDAO.selectAll();
	}
	public List<studio> Fetch(String condt){
		return uDAO.selectwhat(condt);
	}
}

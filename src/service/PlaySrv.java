package service;

import java.util.List;

import bean.play;
import bean.studio;
import idao.DAOFactory;
import idao.iPlayDAO;
import idao.iStudioDAO;

public class PlaySrv {
private iPlayDAO uDAO=DAOFactory.createplayDAO();
	
	public int add(play p){
		return uDAO.insert(p); 		
	}
	
	public int modify(play p){
		return uDAO.update(p); 		
	}
	
	public int delete(int s_id){
		return uDAO.delete(s_id); 		
	}
	
	public List<play> FetchAll(){
		return uDAO.selectAll();
	}
	public List<play> Fetch(String condt){
		return uDAO.selectwhat(condt);
	}
}

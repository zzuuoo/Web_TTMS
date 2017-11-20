package service;

import java.util.List;

import bean.seat;
import idao.DAOFactory;
import idao.iSeatDAO;

public class SeatSrv {
	private iSeatDAO uDAO=DAOFactory.createSeatDAO();
	
	public int add(seat u){
		return uDAO.insert(u); 		
	}
	
	public int modify(seat u){
		return uDAO.update(u); 		
	}
	
	public int delete(int s_id){
		return uDAO.delete(s_id); 		
	}
	
	public List<seat> FetchAll(){
		return uDAO.select("");
	}
	public List<seat> Fetch(String condt){
		return uDAO.select(condt);
	}
}

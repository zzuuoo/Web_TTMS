package service;

import java.util.ArrayList;
import java.util.List;

import bean.ticket;
import idao.DAOFactory;
import idao.iTicketDAO;


public class TicketSrv {
	private iTicketDAO stuDAO=DAOFactory.createTicketDAO();
	
	public int add(ticket stu){
		return stuDAO.insert(stu); 		
	}
	
	public int modify(ticket stu){
		return stuDAO.update(stu); 		
	}
	
	public int delete(int ID){
		return stuDAO.delete(ID); 		
	}
	public int delete(String con){
		return stuDAO.delete(con); 		
	}
	public List<ticket> Fetch(String condt){
		return stuDAO.select(condt);		
	}
	public ticket FetchOne(String condt){
		return stuDAO.selectOne(condt);		
	}
	public List<ticket> FetchAll(){
		return stuDAO.select("");		
	}
	
	public int lockTicket(int ID, String time) {
		return stuDAO.lockTicket(ID, time);
	}

	public int unlockTicket(int ID) {
		return stuDAO.unlockTicket(ID);
	}
}

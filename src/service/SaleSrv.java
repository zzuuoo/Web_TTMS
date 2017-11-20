package service;

import java.util.List;

import bean.sale;
import bean.ticket;
import idao.DAOFactory;
import idao.iSaleDAO;

public class SaleSrv {
	private iSaleDAO stuDAO=DAOFactory.createSaleDAO();

	public boolean doSale(List<ticket> tickets){
		return stuDAO.doSale(tickets);
	}
	
	public boolean refund(ticket tickets){
		return stuDAO.refund(tickets);
	}
	
	public List<sale> FetchAll(){
		return stuDAO.select("");
	}
	
	public List<sale> Fetch(String condt){
		return stuDAO.select(condt);
	}
}

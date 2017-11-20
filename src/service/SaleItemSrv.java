package service;

import java.util.List;

import bean.sale_item;
import idao.DAOFactory;
import idao.iSaleItemDAO;

public class SaleItemSrv {
	private iSaleItemDAO stuDAO=DAOFactory.createSaleItemDAO();
	public List<sale_item> Fetch(String condt){
		return stuDAO.select(condt);
	}
}

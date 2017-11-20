package service;

import java.util.List;

import bean.data_dict;
import idao.DAOFactory;
import idao.IDataDictDAO;

public class Data_dictSrv {
	private IDataDictDAO eDAO = DAOFactory.createDataDictDAO();
	public int add(data_dict e){
		return eDAO.insert(e); 		
	}
	
	public int modify(data_dict e){
		return eDAO.update(e); 		
	}
	
	public int delete(int emp_id){
		return eDAO.delete(emp_id); 		
	}
	
	public List<data_dict> Fetch(String condt){
		return eDAO.select(condt);
	}		
}

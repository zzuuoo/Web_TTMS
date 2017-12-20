package service;

import java.util.List;

import bean.employee;
import idao.DAOFactory;
import idao.iEmployeeDAO;

public class EmployeeSrv {
	private iEmployeeDAO eDAO = DAOFactory.createEmployeeDAO();
	public int add(employee e){
		return eDAO.insert(e); 		
	}
	
	public int modify(employee e){
		return eDAO.update(e); 		
	}
	
	public int delete(int emp_id){
		return eDAO.delete(emp_id); 		
	}
	
	public List<employee> FetchAll(){
		return eDAO.selectAll();
	}
	public List<employee> Fetch(String condt){
		return eDAO.selectwhat(condt);
	}		
	public employee FetchOne(String condt) {
		return eDAO.select(condt);
	}
}

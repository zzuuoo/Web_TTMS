package service;

import java.util.List;
import bean.schedule;
import idao.DAOFactory;
import idao.iScheduleDAO;

public class ScheduleSrv {
	private iScheduleDAO eDAO = DAOFactory.createScheduleDAO();
	public int add(schedule e){
		return eDAO.insert(e); 		
	}
	
	public int modify(schedule e){
		return eDAO.update(e); 		
	}
	
	public int delete(int emp_id){
		return eDAO.delete(emp_id); 		
	}
	
	public schedule FetchOne(String condt){
		return eDAO.selectOne(condt);
	}
	public List<schedule> Fetch(String condt){
		return eDAO.select(condt);
	}	
}

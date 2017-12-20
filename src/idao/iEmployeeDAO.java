package idao;

import java.util.ArrayList;
import java.util.List;

import bean.employee;

public interface iEmployeeDAO {
	//从数据库插入employee
		public int insert(employee u);
		//从数据库更新employee
		public int update(employee u);
		//从数据库删除employee
		public int delete(int emp_id);
		//从数据库查询一个employee
		public employee select(String condt); 
		//从数据库查询所有employee
		public List<employee> selectAll(); 
		//从数据库按条件查询一批employee
		public List<employee> selectwhat(String condt) ; 
		
		public ArrayList<employee> findEmployeeByPage(int cPage, String emp_name);
		public ArrayList<employee> findEmployeeByName(String employeeName);
		public employee findEmployeeById(int employeeId);
		public int getAllCount();

		public int getAllPageCount();
		public int getCurrentPage();
}

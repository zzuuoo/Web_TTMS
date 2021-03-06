package idao;

import java.util.ArrayList;
import java.util.List;

import bean.user;

public interface iUserDao {

	public int insert(user u);
	public int update(user u);
	public int delete(String emp_no);
	public user select(String condt); 
	public List<user> selectAll(); 
	public List<user> selectwhat(String condt) ;
	public ArrayList<user> findUserByPage(int cPage, String emp_no);
	public int getAllCount();

	public int getAllPageCount();
	public int getCurrentPage();
}

package idao;

import java.util.List;

import bean.UserInfo;

public interface iUserDao {

	public int insert(UserInfo emp);
	public int update(UserInfo emp);
	public int delete(String name);
	public UserInfo select(String condt); 
	public List<UserInfo> selectAll(); 
	public List<UserInfo> selectwhat(String condt) ;
}

package dao;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import bean.UserInfo;
import database.DBUtil;
import idao.iUserDao;

public class UserDao implements iUserDao{

	@Override
	public int insert(UserInfo u) {
		try {
			String sql = "insert into user(name, password)"
					+ " values("
					+ "'"
					+ u.getName()
					+ "','" 
					+ u.getPassword()
					+ "'"
					+ "')";
			DBUtil db = new DBUtil();
			db.openConnection();
			System.out.println("数据库连接成功");
			ResultSet rst = db.getInsertObjectIDs(sql);

			db.close(rst);
			db.close();
			
			//System.out.println(stu.getID());
			return 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(UserInfo emp) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserInfo select(String condt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserInfo> selectAll() {
		List<UserInfo> userList = null;
  		UserInfo user;
  		userList=new LinkedList<UserInfo>();
  		try {
  			String sql = "select name, password from user";
  			
  			DBUtil db = new DBUtil();
  			if(!db.openConnection()){
  				System.out.print("fail to connect database");
  			
  			}
  			ResultSet rst = db.execQuery(sql);
  			if (rst!=null) {
  				while(rst.next()){
  					user=new UserInfo(rst.getString("name"),rst.getString("password"));
  					userList.add(user);
  				}
  			}
  			db.close(rst);
  			db.close();
  		} catch (Exception e) {
  			e.printStackTrace();
  		}
  			
  		return userList;
	}

	@Override
	public List<UserInfo> selectwhat(String condt) {
		// TODO Auto-generated method stub
		return null;
	}

}

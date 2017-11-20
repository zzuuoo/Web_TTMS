package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

//import com.mysql.jdbc.Connection;
import bean.user;

import idao.iUserDao;
import tomcatDb.ConnectionManager;
//private String emp_no;//用户名
//private String emp_pass;//密码
//private int type;//'用户类型：0为普通用户，1是管理员',
//private String Head_path;//头像路径
public class UserDao implements iUserDao{

	@Override
	public int insert(user u) {
		try {

			String sql = "insert into user"
					+ " values(?,?,?,?)";
			ConnectionManager cManager = ConnectionManager.getInstance();
			Connection connection = cManager.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			
			pstmt.setString(1, u.getEmp_no()+"");
			pstmt.setString(2, u.getEmp_pass()+"");
			pstmt.setInt(3, u.getType());
			pstmt.setString(4, u.getHead_path()+"");
			pstmt.executeUpdate();
			ResultSet rst = pstmt.getGeneratedKeys();	
			cManager.close(rst, pstmt, connection);

			System.out.println("user插入成功");
			return 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}


	@Override
	public int update(user u) {
		try {
			String sql = "update user set "
					+ "emp_pass='" + u.getEmp_pass()+ "',"
					+ "type="+u.getType()+","
					+"head_path = '"+u.getHead_path()+ "' ";
			sql += " where emp_no = '" + u.getEmp_no()+"'";
			ConnectionManager cManager = ConnectionManager.getInstance();
			Connection connection =  cManager.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.executeUpdate();
			ResultSet rst = pstmt.getGeneratedKeys();	
			cManager.close(rst, pstmt, connection);

			return 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(String emp_no) {
		try {
			String sql = "delete from  user ";
			sql += " where emp_no = '" + emp_no+"'";
			ConnectionManager cManager = ConnectionManager.getInstance();
			Connection connection =  cManager.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.executeUpdate();
			ResultSet rst = pstmt.getGeneratedKeys();	
			cManager.close(rst, pstmt, connection);

			return 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public user select(String condt) {
		user u=null;
  		
  		try {
  			String sql = "select emp_no, emp_pass,type,head_path from user	";

  			if(condt!=""&&condt!=null) {
  			sql = sql+"where "+condt;
  			}
  		//用线程池	
  			ConnectionManager cManager = ConnectionManager.getInstance();
  			Connection connection=null;
  			connection =  cManager.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.executeQuery(sql);
			ResultSet rst = pstmt.executeQuery(sql);

  			if (rst!=null) {
  				while(rst.next()){
  					u=new user();
  					u.setEmp_no(rst.getString("emp_no"));
  					u.setEmp_pass(rst.getString("emp_pass"));
  					u.setType(rst.getInt("type"));
  					u.setHead_path(rst.getString("Head_path"));
  				}
  			}

  			
  			cManager.close(rst, pstmt, connection);
  			
  		} catch (Exception e) {
  			e.printStackTrace();
  		}
  			
  		return u;
	}

	@Override
	public List<user> selectAll() {
		List<user> userList = null;
		user u;
  		userList=new LinkedList<user>();
  		try {
  			String sql = "select emp_no, emp_pass,type,head_path from user	";

  		//用线程池	
  			ConnectionManager cManager = ConnectionManager.getInstance();
  			Connection connection=null;
  			connection =  cManager.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.executeQuery(sql);
			ResultSet rst = pstmt.executeQuery(sql);
			

  			if (rst!=null) {
  				while(rst.next()){
  					u=new user();
  					u.setEmp_no(rst.getString("emp_no"));
  					u.setEmp_pass(rst.getString("emp_pass"));
  					u.setType(rst.getInt("type"));
  					u.setHead_path(rst.getString("Head_path"));
  					userList.add(u);
  				}
  			}

  			
  			cManager.close(rst, pstmt, connection);
  			
  		} catch (Exception e) {
  			e.printStackTrace();
  		}
  			

  		return userList;
	}

	@Override
	public List<user> selectwhat(String condt) {
		List<user> userList = null;
		user u;
  		userList=new LinkedList<user>();
  		try {
  			String sql = "select emp_no, emp_pass,type,head_path from user	";

  			if(condt!=""&&condt!=null) {
  			sql = sql+"where "+condt;
  			}
  		//用线程池	
  			ConnectionManager cManager = ConnectionManager.getInstance();
  			Connection connection=null;
  			connection =  cManager.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.executeQuery(sql);
			ResultSet rst = pstmt.executeQuery(sql);

  			if (rst!=null) {
  				while(rst.next()){
  					u=new user();
  					u.setEmp_no(rst.getString("emp_no"));
  					u.setEmp_pass(rst.getString("emp_pass"));
  					u.setType(rst.getInt("type"));
  					u.setHead_path(rst.getString("Head_path"));
  					userList.add(u);
  				}
  			}

  			
  			cManager.close(rst, pstmt, connection);
  			
  		} catch (Exception e) {
  			e.printStackTrace();
  		}
  			
  		return userList;
	}
	

}

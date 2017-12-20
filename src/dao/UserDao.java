package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import bean.employee;
//import com.mysql.jdbc.Connection;
import bean.user;

import idao.iUserDao;
import tomcatDb.ConnectionManager;
//private String emp_no;//用户名
//private String emp_pass;//密码
//private int type;//'用户类型：0为普通用户，1是管理员',
//private String Head_path;//头像路径
public class UserDao implements iUserDao{
	public static final int PAGE_SIZE = 10; // 每页显示条数
	private int allCount; // 数据库中条数
	private int allPageCount; // 总页数
	private int currentPage; // 当前页
	
	public int getAllCount()
	{
	return allCount;
	}
	public int getAllPageCount()
	{
	return allPageCount;
	}
	public int getCurrentPage()
	{
	return currentPage;
	}
	@SuppressWarnings("finally")
	public ArrayList<user> findUserByPage(int cPage, String emp_no)
	{
		currentPage = cPage;
		ArrayList<user> list = new ArrayList<user>();
		// 若未指定查找某人，则默认全查
		if (null == emp_no || emp_no.equals("null"))
		{
			emp_no = "";
		}
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			// 获取记录总数
			String sql1 = "select count(emp_no) as AllRecord from user where emp_no like ?";
			conn = ConnectionManager.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, "%" + emp_no + "%");
			rs = pstmt.executeQuery();
			if (rs.next())
				allCount = rs.getInt("AllRecord");
			rs.close();
			pstmt.close();
			// 记算总页数
			allPageCount = (allCount + PAGE_SIZE - 1) / PAGE_SIZE;
			// 如果当前页数大于总页数，则赋值为总页数
			if (allPageCount > 0 && currentPage > allPageCount)
				currentPage = allPageCount;
			// 获取第currentPage页数据
			String sql2 = "select * from user where emp_no like ? limit ?,?";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, "%" + emp_no + "%");
			pstmt.setInt(2, PAGE_SIZE * (currentPage - 1));
			pstmt.setInt(3, PAGE_SIZE);
			rs = pstmt.executeQuery();
			user info = null;
			while (rs.next())
			{
				info = new user();
				info.setEmp_no(rs.getString("emp_no"));
				info.setType(rs.getInt("type"));
				info.setHead_path(rs.getString("head_path"));
				info.setEmp_pass(rs.getString("emp_pass"));
				
				list.add(info);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			ConnectionManager.close(rs, pstmt, conn);
			return list;
		}
	}
	

	@Override
	public int insert(user u) {
		ConnectionManager cManager = ConnectionManager.getInstance();
		Connection connection = cManager.getConnection();
		PreparedStatement pstmt = null;
		try {

			String sql = "insert into user"
					+ " values(?,?,?,?)";
			pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			
			pstmt.setString(1, u.getEmp_no()+"");
			pstmt.setString(2, u.getEmp_pass()+"");
			pstmt.setInt(3, u.getType());
			pstmt.setString(4, u.getHead_path()+"");
			pstmt.executeUpdate();
		


			System.out.println("user插入成功");
			return 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionManager.close(null, pstmt, connection);
		}
		return 0;
	}


	@Override
	public int update(user u) {
		ConnectionManager cManager = ConnectionManager.getInstance();
		Connection connection =  cManager.getConnection();
		PreparedStatement pstmt = null;
		try {

			String sql = "update user set emp_pass=?,type=?,head_path=? "
					+ " where emp_no = ?;";
			pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, u.getEmp_pass());
			pstmt.setInt(2, u.getType());
			pstmt.setString(3, u.getHead_path());
			pstmt.setString(4, u.getEmp_no());
			pstmt.executeUpdate();
			
			return 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionManager.close(null, pstmt, connection);
		}
		return 0;
	}

	@Override
	public int delete(String emp_no) {
		ConnectionManager cManager = ConnectionManager.getInstance();
		Connection connection =  cManager.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "delete from user where emp_no = ?";
			pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, emp_no);
			pstmt.executeUpdate();	
			return 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionManager.close(null, pstmt, connection);

		}
		return 0;
	}

	@Override
	public user select(String condt) {
		user u=null;
		ConnectionManager cManager = ConnectionManager.getInstance();
		Connection connection=null;
		connection =  cManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rst = null;
  		try {
  			String sql = "select emp_no, emp_pass,type,head_path from user	";

  			if(condt!=""&&condt!=null) {
  			sql = sql+"where "+condt;
  			}
  		//用线程池	
  			pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.executeQuery(sql);
			rst = pstmt.executeQuery(sql);

  			if (rst!=null) {
  				while(rst.next()){
  					u=new user();
  					u.setEmp_no(rst.getString("emp_no"));
  					u.setEmp_pass(rst.getString("emp_pass"));
  					u.setType(rst.getInt("type"));
  					u.setHead_path(rst.getString("Head_path"));
  				}
  			}

  			
  		} catch (Exception e) {
  			e.printStackTrace();
  		}finally {
  			ConnectionManager.close(rst, pstmt, connection);
		}
  			
  		return u;
	}

	@Override
	public List<user> selectAll() {
		List<user> userList = null;
		user u;
  		userList=new LinkedList<user>();
  		ConnectionManager cManager = ConnectionManager.getInstance();
		Connection connection=null;
		connection =  cManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rst = null;
  		try {
  			String sql = "select emp_no, emp_pass,type,head_path from user	";

  		//用线程池	
  			
			pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.executeQuery(sql);
			rst = pstmt.executeQuery(sql);
			

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

  			
  			
  			
  		} catch (Exception e) {
  			e.printStackTrace();
  		}finally {
  			ConnectionManager.close(rst, pstmt, connection);
		}
  			

  		return userList;
	}

	@Override
	public List<user> selectwhat(String condt) {
		List<user> userList = null;
		user u;
  		userList=new LinkedList<user>();
  		ConnectionManager cManager = ConnectionManager.getInstance();
		Connection connection=null;
		connection =  cManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rst = null;
  		try {
  			String sql = "select emp_no, emp_pass,type,head_path from user	";

  			if(condt!=""&&condt!=null) {
  			sql = sql+"where "+condt;
  			}
  		//用线程池	
  			
			pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.executeQuery(sql);
			rst = pstmt.executeQuery(sql);

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

  			
  			
  			
  		} catch (Exception e) {
  			e.printStackTrace();
  		}finally {
  			ConnectionManager.close(rst, pstmt, connection);
		}
  			
  		return userList;
	}
	

	
	

}

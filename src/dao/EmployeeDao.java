package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Year;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import bean.employee;
import idao.iEmployeeDAO;
import tomcatDb.ConnectionManager;

public class EmployeeDao implements iEmployeeDAO{
	
	
	
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
	

	@Override
	public int insert(employee u) {
		
		
		  Connection connection = ConnectionManager.getInstance().getConnection();
	      PreparedStatement pstmt = null;
		try {
			
			String sql = "insert into employee(emp_no, emp_name,emp_tel_num,emp_addr,emp_email)"
					+ " values(?,?,?,?,?)";
			
			 pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			//获取PreparedStatement借口实例
			
		
			pstmt.setString(1, u.getEmp_no());
			pstmt.setString(2, u.getEmp_name());
			pstmt.setString(3, u.getEmp_tel_num());
			pstmt.setString(4, u.getEmp_addr());
			pstmt.setString(5, u.getEmp_email());
				//初始化sql中的参数
				
			pstmt.executeUpdate();
				//执行sql语句
		
			
			System.out.println("employee插入成功");
			
			return 1;
			//操作成功返回1
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionManager.close(null, pstmt, connection);
			//关闭所有连接
		}
		return 0;
	}

	@Override
	public int update(employee u) {
		ConnectionManager cManager = ConnectionManager.getInstance();
		Connection connection =  cManager.getConnection();
		PreparedStatement pstmt=null;
		try {

			
			String sql = "update employee set emp_no= ? ,emp_name= ? ,emp_tel_num=? "
					+ ",emp_addr=? ,emp_email=? where emp_id = ?";

			pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, u.getEmp_no());
			pstmt.setString(2, u.getEmp_name());
			pstmt.setString(3, u.getEmp_tel_num());
			pstmt.setString(4, u.getEmp_addr());
			pstmt.setString(5, u.getEmp_email());
			pstmt.setInt(6, u.getEmp_id());
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
	public int delete(int emp_id) {
		ConnectionManager cManager = ConnectionManager.getInstance();
		Connection connection =  cManager.getConnection();
		PreparedStatement pstmt=null;
		try {
			String sql = "delete from employee where emp_id=?";
			pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, emp_id);
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
	public employee select(String condt) {
	
		employee e=null;
		ConnectionManager cManager = ConnectionManager.getInstance();
		Connection connection=null;
		connection =  cManager.getConnection();
		PreparedStatement pstmt =null;
		ResultSet rst =null;
  		try {
  			String sql = "select * from employee	";
  			if(condt!=null&&condt!="") {
  				sql=sql+"where "+condt;
  			}
  		//用线程池	
  			
			pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.executeQuery(sql);
			rst = pstmt.executeQuery(sql);
			
  			if (rst!=null) {
  				while(rst.next()){
  						e = new employee();
  						e.setEmp_no(rst.getString("emp_no"));
  						e.setEmp_addr(rst.getString("emp_addr"));
  						e.setEmp_email(rst.getString("emp_email"));
  						e.setEmp_id(rst.getInt("emp_id"));
  						e.setEmp_name(rst.getString("emp_name"));
  						e.setEmp_tel_num(rst.getString("emp_tel_num"));
  						
  				}
  			}

  			
  		} catch (Exception a) {
  			a.printStackTrace();
  		}finally {
  			ConnectionManager.close(rst, pstmt, connection);
  		}
  			
  		return e;
	}

	@Override
	public List<employee> selectAll() {
		List<employee> employeeList = null;
		employee e;
		employeeList=new LinkedList<employee>();
		//用线程池	
			ConnectionManager cManager = ConnectionManager.getInstance();
			Connection connection=null;
			connection =  cManager.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rst = null;
  		try {
  			String sql = "select * from employee	";

  		//用线程池	
  			
			pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.executeQuery(sql);
			rst = pstmt.executeQuery(sql);
			
  			if (rst!=null) {
  				while(rst.next()){
  						e = new employee();
  						e.setEmp_no(rst.getString("emp_no"));
  						e.setEmp_addr(rst.getString("emp_addr"));
  						e.setEmp_email(rst.getString("emp_email"));
  						e.setEmp_id(rst.getInt("emp_id"));
  						e.setEmp_name(rst.getString("emp_name"));
  						e.setEmp_tel_num(rst.getString("emp_tel_num"));
  						employeeList.add(e);
  						
  				}
  			}

  			
  			
  		} catch (Exception a) {
  			a.printStackTrace();
  		}finally {
  			ConnectionManager.close(rst, pstmt, connection);
		}
  			
  		return employeeList;
	}

	@Override
	public List<employee> selectwhat(String condt) {
		List<employee> employeeList = null;
		employee e;
		employeeList=new LinkedList<employee>();
		ConnectionManager cManager = ConnectionManager.getInstance();
		Connection connection=null;
		connection =  cManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rst = null;
  		try {
  			String sql = "select * from employee  where ";
  			sql = sql+condt;
 
  		//用线程池	
  			
			pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//			pstmt.setString(1, condt);
			pstmt.executeQuery(sql);
			rst = pstmt.executeQuery(sql);
			
  			if (rst!=null) {
  				while(rst.next()){
  						e = new employee();
  						e.setEmp_no(rst.getString("emp_no"));
  						e.setEmp_addr(rst.getString("emp_addr"));
  						e.setEmp_email(rst.getString("emp_email"));
  						e.setEmp_id(rst.getInt("emp_id"));
  						e.setEmp_name(rst.getString("emp_name"));
  						e.setEmp_tel_num(rst.getString("emp_tel_num"));
  						employeeList.add(e);
  						
  				}
  			}

  			
  			
  		} catch (Exception a) {
  			a.printStackTrace();
  		}finally {
  			ConnectionManager.close(rst, pstmt, connection);
		}
  			
  		return employeeList;
	}
	
	
	
	//用于分页的方法
	@SuppressWarnings("finally")
	public ArrayList<employee> findEmployeeByPage(int cPage, String emp_name)
	{
		currentPage = cPage;
		ArrayList<employee> list = new ArrayList<employee>();
		// 若未指定查找某人，则默认全查
		if (null == emp_name || emp_name.equals("null"))
		{
			emp_name = "";
		}
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			// 获取记录总数
			String sql1 = "select count(emp_no) as AllRecord from employee where emp_name like ?";
			conn = ConnectionManager.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, "%" + emp_name + "%");
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
			String sql2 = "select * from employee where emp_name like ? limit ?,?";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, "%" + emp_name + "%");
			pstmt.setInt(2, PAGE_SIZE * (currentPage - 1));
			pstmt.setInt(3, PAGE_SIZE);
			rs = pstmt.executeQuery();
			employee emp = null;
			while (rs.next())
			{
				emp = new employee();
				emp.setEmp_id(rs.getInt("emp_id"));
				emp.setEmp_no(rs.getString("emp_no"));
				emp.setEmp_name(rs.getString("emp_name"));
				emp.setEmp_tel_num(rs.getString("emp_tel_num"));
				emp.setEmp_addr(rs.getString("emp_addr"));
				emp.setEmp_email(rs.getString("emp_email"));
				// 将该用户信息插入列表
				list.add(emp);
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
	
	
	
	/**
	* 根据用户名获取用户信息(一般用于和界面交互)-模糊匹配
	* @return Employee列表
	*/
	@SuppressWarnings("finally")
	public ArrayList<employee> findEmployeeByName(String employeeName)
	{
		if (employeeName == null || employeeName.equals(""))
			return null;
		ArrayList<employee> list = new ArrayList<employee>();
		employee info = null;
		Connection con = ConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			// 获取所有用户数据:模糊查询
			pstmt = con.prepareStatement("select * from employee where emp_name like ?");
			pstmt.setString(1, "%" + employeeName + "%");// 拼接模糊查询串
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				// 如果有值的话再实例化
				info = new employee();
				info.setEmp_id(rs.getInt("emp_id"));
				info.setEmp_no(rs.getString("emp_no"));
				info.setEmp_name(rs.getString("emp_name"));
				info.setEmp_tel_num(rs.getString("emp_tel_num"));
				info.setEmp_addr(rs.getString("emp_addr"));
				info.setEmp_email(rs.getString("emp_email"));
				// 加入列表
				list.add(info);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			ConnectionManager.close(rs, pstmt, con);
			return list;
		}
	}
	
	
	
	@SuppressWarnings("finally")
	public employee findEmployeeById(int employeeId)
	{
		employee info = null;
		if (employeeId == 0)
			return info;
		Connection con = ConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			// 获取所有用户数据
			pstmt = con.prepareStatement("select * from employee where emp_id=?");
			pstmt.setInt(1, employeeId);
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				// 如果有值的话再实例化
				info = new employee();
				info.setEmp_id(employeeId);
				info.setEmp_no(rs.getString("emp_no"));
				info.setEmp_name(rs.getString("emp_name"));
				info.setEmp_tel_num(rs.getString("emp_tel_num"));
				info.setEmp_addr(rs.getString("emp_addr"));
				info.setEmp_email(rs.getString("emp_email"));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			ConnectionManager.close(rs, pstmt, con);
			return info;
		}
		}

}

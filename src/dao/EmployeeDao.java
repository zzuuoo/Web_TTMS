package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import bean.employee;
import idao.iEmployee;
import tomcatDb.ConnectionManager;

public class EmployeeDao implements iEmployee{

	@Override
	public int insert(employee u) {
		try {
			
			String sql = "insert into employee(emp_no, emp_name,emp_tel_num,emp_addr,emp_email)"
					+ " values(?,?,?,?,?)";
			//sql语句，参数未初始化
			
			ConnectionManager cManager = ConnectionManager.getInstance();
			//调用连接类获取该类实例
			
			Connection connection = cManager.getConnection();
			//通过连接类实例获取数据库连接对象
			
			PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			//获取PreparedStatement借口实例
			
		
			pstmt.setString(1, u.getEmp_no());
			pstmt.setString(2, u.getEmp_name());
			pstmt.setString(3, u.getEmp_tel_num());
			pstmt.setString(4, u.getEmp_addr());
			pstmt.setString(5, u.getEmp_email());
				//初始化sql中的参数
				
			pstmt.executeUpdate();
				//执行sql语句
				
			
			ResultSet rst = pstmt.getGeneratedKeys();	
			//获取结果集
			
			if(rst.next()&&rst.first()){
				u.setEmp_id(rst.getInt(1));
			}
			
			cManager.close(rst, pstmt, connection);
			//关闭所有连接
			
			System.out.println("employee插入成功");
			
			return 1;
			//操作成功返回1
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(employee u) {
		try {
			String sql = "update employee set "
					+ " emp_no= '"+ u.getEmp_no()+ "', "
					+ "emp_name='" + u.getEmp_name()+ "',"
					+ "emp_tel_num='"+u.getEmp_tel_num()+"',"
					+"emp_addr = '"+u.getEmp_addr()+ "',"
					+"emp_email='"+u.getEmp_email()+
					"' ";
			sql += " where emp_id = " + u.getEmp_id();
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
	public int delete(int emp_id) {
		try {
			String sql = "delete from  employee ";
			sql += " where emp_id = " + emp_id;
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
	public employee select(String condt) {
	
		employee e=null;
  		try {
  			String sql = "select * from employee	";
  			if(condt!=null&&condt!="") {
  				sql=sql+"where "+condt;
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
  						e = new employee();
  						e.setEmp_no(rst.getString("emp_no"));
  						e.setEmp_addr(rst.getString("emp_addr"));
  						e.setEmp_email(rst.getString("emp_email"));
  						e.setEmp_id(rst.getInt("emp_id"));
  						e.setEmp_name(rst.getString("emp_name"));
  						e.setEmp_tel_num(rst.getString("emp_tel_num"));
  						
  				}
  			}

  			cManager.close(rst, pstmt, connection);
  			
  		} catch (Exception a) {
  			a.printStackTrace();
  		}
  			
  		return e;
	}

	@Override
	public List<employee> selectAll() {
		List<employee> employeeList = null;
		employee e;
		employeeList=new LinkedList<employee>();
  		try {
  			String sql = "select * from employee	";

  		//用线程池	
  			ConnectionManager cManager = ConnectionManager.getInstance();
  			Connection connection=null;
  			connection =  cManager.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.executeQuery(sql);
			ResultSet rst = pstmt.executeQuery(sql);
			
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

  			cManager.close(rst, pstmt, connection);
  			
  		} catch (Exception a) {
  			a.printStackTrace();
  		}
  			
  		return employeeList;
	}

	@Override
	public List<employee> selectwhat(String condt) {
		List<employee> employeeList = null;
		employee e;
		employeeList=new LinkedList<employee>();
  		try {
  			String sql = "select * from employee	";
  			if(condt!=null&&condt!=null) {
  				sql=sql+"where "+condt;
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

  			cManager.close(rst, pstmt, connection);
  			
  		} catch (Exception a) {
  			a.printStackTrace();
  		}
  			
  		return employeeList;
	}

}

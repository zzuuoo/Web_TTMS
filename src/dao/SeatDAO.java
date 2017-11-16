package dao;

import java.util.LinkedList;
import java.util.List;

import bean.seat;
import idao.iSeatDAO;
import tomcatDb.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class SeatDAO implements iSeatDAO {
	 
	public int insert(seat stu) {

		try {
			
			
			String sql = "insert into seat(studio_id, seat_row, seat_column,seat_status )"
					+ " values(?,?,?,?)";
			ConnectionManager cManager = ConnectionManager.getInstance();
			//调用连接类获取该类实例
			
			Connection connection = cManager.getConnection();
			//通过连接类实例获取数据库连接对象
			
			PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			//获取PreparedStatement借口实例
			
			pstmt.setInt(1, stu.getStudio_id());
			pstmt.setInt(2, stu.getSeat_row());
			pstmt.setInt(3, stu.getSeat_column());
			pstmt.setInt(4, stu.getSeat_status());
				//初始化sql中的参数
				
			pstmt.executeUpdate();
				//执行sql语句
				
			
			ResultSet rst = pstmt.getGeneratedKeys();	
			//获取结果集
			
			if(rst.next()&&rst.first()){
				stu.setSeat_id(rst.getInt(1));
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

	
	public int update(seat stu) {
		int rtn = 1;
		try {
			String sql = "update seat set " + " studio_id ="
					+ stu.getStudio_id() + ", " + " seat_row = "
					+ stu.getSeat_row() + ", "  + " seat_column = "
					+ stu.getSeat_column() + " ";

			sql += " where sched_id = " + stu.getSeat_id();
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

	 
	public int delete(int ID) {
		int rtn = 0;
		try {
			String sql = "delete from seat ";
			sql += " where seat_id = " + ID;
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

	public int delete(String condt) {
		int rtn = 0;
		try {
			String sql = "delete from seat ";
			sql += " where  " + condt;
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

	 
	public List<seat> select(String condt) {
		List<seat> stuList = null;
		stuList = new LinkedList<seat>();
		try {
			String sql = "select * from seat ";
			condt.trim();
			if (!condt.isEmpty())
				sql += " where " + condt;
			ConnectionManager cManager = ConnectionManager.getInstance();
  			Connection connection=null;
  			connection =  cManager.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.executeQuery(sql);
			ResultSet rst = pstmt.executeQuery(sql);
			if (rst != null) {
				System.out.println("rst!=null");
				while (rst.next()) {
					seat stu = new seat();
					stu.setSeat_id(rst.getInt("seat_id"));
					stu.setStudio_id(rst.getInt("studio_id"));
					stu.setSeat_row(rst.getInt("seat_row"));
					stu.setSeat_column(rst.getInt("seat_column"));
					stuList.add(stu);
				}
			}
			cManager.close(rst, pstmt, connection);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

		return stuList;
	}


}

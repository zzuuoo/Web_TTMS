package dao;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import bean.schedule;
import idao.iScheduleDAO;
import tomcatDb.ConnectionManager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;


public class ScheduleDAO implements iScheduleDAO {
	 
	public int insert(schedule stu) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		try {
			String sql = "insert into schedule(studio_id, play_id, sched_time, sched_ticket_price)"
					+ " values(?,?,?)";
			ConnectionManager cManager = ConnectionManager.getInstance();
			//调用连接类获取该类实例
			
			Connection connection = cManager.getConnection();
			//通过连接类实例获取数据库连接对象
			
			PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			//获取PreparedStatement借口实例
			
			//非空值为空，则不能插入
			
			pstmt.setInt(1, stu.getStudio_id());
			pstmt.setInt(2, stu.getPlay_id());
			pstmt.setTimestamp(3,new java.sql.Timestamp(stu.getSched_time().getTime()));
			pstmt.setDouble(4, stu.getSched_ticket_price());
				//初始化sql中的参数
				
			pstmt.executeUpdate();
				//执行sql语句

			ResultSet rst = pstmt.getGeneratedKeys();	
			//获取结果集
			
			if(rst.next()&&rst.first()){
				stu.setSched_id(rst.getInt(1));
			}
			
			cManager.close(rst, pstmt, connection);
			
			return 1;
			//操作成功返回1
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	 
	public int update(schedule stu) {
		int rtn = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		try {
			String sql = "update schedule set " + " studio_id ="
					+ stu.getStudio_id() + ", " + " play_id = "
					+ stu.getPlay_id() + ", " + " sched_time = '"
					+sdf.format(stu.getSched_time())
					+ "', " + " sched_ticket_price = "
					+ stu.getSched_ticket_price() + " ";

			sql += " where sched_id = " + stu.getSched_id();
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
			String sql = "delete from  schedule ";
			sql += " where sched_id = " + ID;
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

	 
	public List<schedule> select(String condt) {
		List<schedule> stuList = null;
		stuList = new LinkedList<schedule>();
		try {
			String sql = "select * from schedule ";
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
				while (rst.next()) {
					schedule stu = new schedule();
					stu.setSched_id(rst.getInt("sched_id"));
					stu.setStudio_id(rst.getInt("studio_id"));
					stu.setPlay_id(rst.getInt("play_id"));
					stu.setSched_time(rst.getTimestamp("sched_time"));
					stu.setSched_ticket_price(rst.getDouble("sched_ticket_price"));
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
	
	public schedule selectOne(String condt) {
		schedule s = null;
		try {
			String sql = "select * from schedule ";
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
				while (rst.next()) {
					s  = new schedule();
					s.setSched_id(rst.getInt("sched_id"));
					s.setStudio_id(rst.getInt("studio_id"));
					s.setPlay_id(rst.getInt("play_id"));
					s.setSched_time(rst.getTimestamp("sched_time"));
					s.setSched_ticket_price(rst.getDouble("sched_ticket_price"));
					
				}
			}
			cManager.close(rst, pstmt, connection);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

		return s;
	}
}

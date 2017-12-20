package dao;

import java.util.LinkedList;
import java.util.List;

import bean.ticket;
import idao.iTicketDAO;
import tomcatDb.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class TicketDAO implements iTicketDAO {
	 
	public int insert(ticket stu) {
		try {
			String sql = "insert into ticket(seat_id, sched_id, ticket_price,ticket_status)"
					+ " values(?,?,?,?)";
					ConnectionManager cManager = ConnectionManager.getInstance();
			//调用连接类获取该类实例
			
			Connection connection = cManager.getConnection();
			//通过连接类实例获取数据库连接对象
			
			PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			//获取PreparedStatement借口实例
			
		
			pstmt.setInt(1, stu.getSeat_id());
			pstmt.setInt(2, stu.getSched_id());
			pstmt.setDouble(3, stu.getTicket_price());
			pstmt.setInt(4, stu.getTicket_status());
				//初始化sql中的参数
				
			pstmt.executeUpdate();
				//执行sql语句
				
			
			ResultSet rst = pstmt.getGeneratedKeys();	
			//获取结果集
			
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

	 
	public int update(ticket stu) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		int rtn = 0;
		if(stu.getTicket_locked_time()!=null){
		try {
//			String sql = "update ticket set " + " seat_id ="
//					+ stu.getSeat_id() + ", " + " sched_id = "
//					+ stu.getSched_id() + ", " + " ticket_price= "
//					+ stu.getTicket_price() + ", " + " ticket_status = "
//					+ stu.getTicket_status() + ", "+"ticket_locked_time='"
//					+sdf.format(stu.getTicket_locked_time())+"'";
//
//			sql += " where ticket_id = " + stu.getTicket_id();
			String sql = "update ticket set  seat_id = ?,"
					+ " sched_id = ?, ticket_price= ?,"
					+ " ticket_status = ?,"
					+"ticket_locked_time=? where ticket_id = ?;";
			ConnectionManager cManager = ConnectionManager.getInstance();
			Connection connection =  cManager.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, stu.getSeat_id());
			pstmt.setInt(2, stu.getSched_id());
			pstmt.setDouble(3, stu.getTicket_price());
			pstmt.setInt(4, stu.getTicket_status());
			pstmt.setTimestamp(5, new java.sql.Timestamp(stu.getTicket_locked_time().getTime()));
			pstmt.setInt(6, stu.getTicket_id());
			pstmt.executeUpdate();
			ResultSet rst = pstmt.getGeneratedKeys();	
			cManager.close(rst, pstmt, connection);
			rtn=1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		}else{
				try {
					String sql = "update ticket set " + " seat_id ="
							+ stu.getSeat_id() + ", " + " sched_id = "
							+ stu.getSched_id() + ", " + " ticket_price= "
							+ stu.getTicket_price() + ", " + " ticket_status = "
							+ stu.getTicket_status() + ", "+"ticket_locked_time= null";

					sql += " where ticket_id = " + stu.getTicket_id();
					ConnectionManager cManager = ConnectionManager.getInstance();
					Connection connection =  cManager.getConnection();
					PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					pstmt.executeUpdate();
					ResultSet rst = pstmt.getGeneratedKeys();	
					cManager.close(rst, pstmt, connection);
					rtn=1;
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		return rtn;
	}

	 
	public int delete(int ID) {
		try {
			String sql = "delete from ticket  where ticket_id = ?;";
			ConnectionManager cManager = ConnectionManager.getInstance();
			Connection connection =  cManager.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, ID);
			pstmt.executeUpdate();
			ResultSet rst = pstmt.getGeneratedKeys();	
			cManager.close(rst, pstmt, connection);

			return 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int delete(String  con) {
		try {
			String sql = "delete from ticket ";
			sql += " where  " + con;
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

	 
	public List<ticket> select(String condt) {
		List<ticket> stuList = null;
		stuList = new LinkedList<ticket>();
		try {
			String sql = "select * from ticket ";
			condt.trim();
			if (!condt.isEmpty())
				sql += " where " + condt;
			//用线程池	
  			ConnectionManager cManager = ConnectionManager.getInstance();
  			Connection connection=null;
  			connection =  cManager.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.executeQuery(sql);
			ResultSet rst = pstmt.executeQuery(sql);
			if (rst != null) {
				while (rst.next()) {
					ticket stu = new ticket();
					stu.setSched_id(rst.getInt("sched_id"));
					stu.setSeat_id(rst.getInt("seat_id"));
					stu.setTicket_id(rst.getInt("ticket_id"));
					stu.setTicket_locked_time(rst.getDate("ticket_locked_time"));
					stu.setTicket_price(rst.getDouble("ticket_price"));
					stu.setTicket_status(rst.getInt("ticket_status"));
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

	 
	public int lockTicket(int ID, String time) {
		int rtn=0;
		try {
			String sql = "update ticket set ticket_status=1, ticket_locked_time='" + time + "'";
			sql += " where ticket_id = " + ID;
			ConnectionManager cManager = ConnectionManager.getInstance();
			Connection connection =  cManager.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.executeUpdate();
			ResultSet rst = pstmt.getGeneratedKeys();	
			cManager.close(rst, pstmt, connection);
			rtn=1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}

	 
	public int unlockTicket(int ID) {
		int rtn=0;
		try {
			String sql = "update ticket set ticket_status=0,ticket_locked_time = NULL ";
			sql += " where ticket_id = " + ID;
			ConnectionManager cManager = ConnectionManager.getInstance();
			Connection connection =  cManager.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.executeUpdate();
			ResultSet rst = pstmt.getGeneratedKeys();	
			cManager.close(rst, pstmt, connection);
			rtn=1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}


	@Override
	public ticket selectOne(String condt) {
		ticket stu = new ticket();
		try {
			String sql = "select * from ticket ";
			condt.trim();
			if (!condt.isEmpty())
				sql += " where " + condt;
			//用线程池	
  			ConnectionManager cManager = ConnectionManager.getInstance();
  			Connection connection=null;
  			connection =  cManager.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.executeQuery(sql);
			ResultSet rst = pstmt.executeQuery(sql);
			if (rst != null) {
				while (rst.next()) {
					stu.setSched_id(rst.getInt("sched_id"));
					stu.setSeat_id(rst.getInt("seat_id"));
					stu.setTicket_id(rst.getInt("ticket_id"));
					stu.setTicket_locked_time(rst.getDate("ticket_locked_time"));
					stu.setTicket_price(rst.getDouble("ticket_price"));
					stu.setTicket_status(rst.getInt("ticket_status"));
				}
			}
			cManager.close(rst, pstmt, connection);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

		return stu;
	}


}

package dao;

import java.util.ArrayList;
import java.util.List;

import bean.seat;
import idao.iSeatDAO;
import tomcatDb.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class SeatDAO implements iSeatDAO {
	public static final int PAGE_SIZE = 5; // 每页显示条数
	private int allCount; // 数据库中条数
	private int allPageCount; // 总页数
	private int currentPage; // 当前页
//	QueryRunner run = new QueryRunner();
	
	public static int getPageSize() {
		return PAGE_SIZE;
	}

	public int getAllCount() {
		return allCount;
	}

	public int getAllPageCount() {
		return allPageCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}
	public int insert(seat stu) {
		ConnectionManager cManager = ConnectionManager.getInstance();
		//调用连接类获取该类实例
		
		Connection connection = cManager.getConnection();
		//通过连接类实例获取数据库连接对象
		
		PreparedStatement pstmt = null;
		try {
			
			
			String sql = "insert into seat(studio_id, seat_row, seat_column,seat_status )"
					+ " values(?,?,?,?)";
			pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			//获取PreparedStatement借口实例
			
			pstmt.setInt(1, stu.getStudio_id());
			pstmt.setInt(2, stu.getSeat_row());
			pstmt.setInt(3, stu.getSeat_column());
			pstmt.setInt(4, stu.getSeat_status());
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

	public int updateSeatStatus(seat stu) {
		
		ConnectionManager cManager = ConnectionManager.getInstance();
		Connection connection =  cManager.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "UPDATE seat SET seat_status=? WHERE seat_id=? AND studio_id=?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, stu.getSeat_status());
			pstmt.setInt(2, stu.getSeat_id());
			pstmt.setInt(3, stu.getStudio_id());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionManager.close(null, pstmt, connection);

		}
		return 1;
	}

	 
	public int delete(int ID) {
		int rtn = 0;
		ConnectionManager cManager = ConnectionManager.getInstance();
		Connection connection =  cManager.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "delete from seat ";
			sql += " where seat_id = ?";
			pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, ID);
			pstmt.executeUpdate();
			
			return 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionManager.close(null, pstmt, connection);

		}
		return 0;
	}

	public int delete(String condt) {
		int rtn = 0;
		ConnectionManager cManager = ConnectionManager.getInstance();
		Connection connection =  cManager.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "delete from seat ";
			sql += " where  ?";
			pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, condt);
			pstmt.executeUpdate();
			return 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionManager.close(null, pstmt, connection);

		}
		return 0;
	}

	 
	public List<seat> select(String condt) {
		List<seat> stuList = null;
		stuList = new ArrayList<seat>();
		ConnectionManager cManager = ConnectionManager.getInstance();
			Connection connection=null;
			connection =  cManager.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "select * from seat ";
			condt.trim();
			if (!condt.isEmpty())
				sql += " where " + condt;
			pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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
					stu.setSeat_status(rst.getInt("seat_status"));
					stuList.add(stu);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(null, pstmt, connection);
		}

		return stuList;
	}

	@Override
	public int update(seat stu) {
		// TODO Auto-generated method stub
		return 0;
	}


}

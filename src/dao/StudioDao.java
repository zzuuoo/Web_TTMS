package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import bean.studio;
import idao.iStudioDAO;
import tomcatDb.ConnectionManager;
//private int studio_id;
//private String studio_name;
//private int studio_row_count;
//private int studio_col_count;
//private String studio_introduction;
//private int studio_flag;// 0：尚未生成座位，可以根据行列信息生成座位\r\n  1：已经根据影厅的座位信息
public class StudioDao implements iStudioDAO{
	
	public static final int PAGE_SIZE = 5; // 每页显示条数
	private int allCount; // 数据库中条数
	private int allPageCount; // 总页数
	private int currentPage; // 当前页
	public int getAllCount() {
		return allCount;
	}
	public void setAllCount(int allCount) {
		this.allCount = allCount;
	}
	public int getAllPageCount() {
		return allPageCount;
	}
	public void setAllPageCount(int allPageCount) {
		this.allPageCount = allPageCount;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	@Override
	public int insert(studio p) {
		ConnectionManager cManager = ConnectionManager.getInstance();
		//调用连接类获取该类实例
		
		Connection connection = cManager.getConnection();
		//通过连接类实例获取数据库连接对象
		
		PreparedStatement pstmt = null;
		try {
			
			String sql = "insert into studio(studio_name,studio_row_count,"
					+ "studio_col_count,studio_introduction,studio_flag)"
					+ " values(?,?,?,?,?)";
			//sql语句，参数未初始化
			
			
			
			pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			//获取PreparedStatement借口实例
			
			//非空值为空，则不能插入
			
				pstmt.setString(1, p.getStudio_name());
				pstmt.setInt(2, p.getStudio_row_count());
				pstmt.setInt(3, p.getStudio_col_count());
				pstmt.setString(4, p.getStudio_introduction());
				pstmt.setInt(5, p.getStudio_flag());
				//初始化sql中的参数
				
				pstmt.executeUpdate();
				//执行sql语句
				
	
			
			System.out.println("studio插入成功");
			
			return 1;
			//操作成功返回1
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionManager.close(null, pstmt, connection);
		}
		return 0;
	}

	@Override
	public int update(studio stu) {
		ConnectionManager cManager = ConnectionManager.getInstance();
		Connection connection =  cManager.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "update studio set " + " studio_name ='"
					+ stu.getStudio_name() + "', " + " studio_row_count = "
					+ stu.getStudio_row_count() + ", " + " studio_col_count = "
					+ stu.getStudio_col_count() + ", " + " studio_introduction = '"
					+ stu.getStudio_introduction() + "' ,"+" studio_flag = "
					+stu.getStudio_flag();

			sql += " where studio_id = " + stu.getStudio_id();
			pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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
	public int delete(int s_id) {
		ConnectionManager cManager = ConnectionManager.getInstance();
		Connection connection =  cManager.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "delete from  studio ";
			sql += " where studio_id = ?";
			pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, s_id);
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
	public studio select(String condt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<studio> selectAll() {
		List<studio> studios = null;
		studio s;
		studios=new ArrayList<studio>();
		ConnectionManager cManager = ConnectionManager.getInstance();
			Connection connection=null;
			connection =  cManager.getConnection();
		PreparedStatement pstmt = null;
  		try {
  			String sql = "select * from studio	";

  		//用线程池	
  			pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rst = pstmt.executeQuery(sql);
			
  			if (rst!=null) {
  				while(rst.next()){
  						s = new studio();
  						s.setStudio_name(rst.getString("studio_name"));
  						s.setStudio_id(rst.getInt("studio_id"));
  						s.setStudio_col_count(rst.getInt("studio_col_count"));
  						s.setStudio_row_count(rst.getInt("studio_row_count"));
  						s.setStudio_introduction(rst.getString("studio_introduction"));
  						s.setStudio_flag(rst.getInt("studio_flag"));
  						studios.add(s);
  						
  				}
  			}
  		} catch (Exception a) {
  			a.printStackTrace();
  		}finally {
  			ConnectionManager.close(null, pstmt, connection);
		}
  			
  		return studios;
	}

	@Override
	public List<studio> selectwhat(String condt) {
		List<studio> studios = null;
		studio s;
		studios=new LinkedList<studio>();
		ConnectionManager cManager = ConnectionManager.getInstance();
			Connection connection=null;
			connection =  cManager.getConnection();
		PreparedStatement pstmt = null;
  		try {
  			String sql = "select * from studio	where ";
  			sql=sql+condt;

  		//用线程池	
  			pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.executeQuery(sql);
			ResultSet rst = pstmt.executeQuery(sql);
			
  			if (rst!=null) {
  				while(rst.next()){
  						s = new studio();
  						s.setStudio_name(rst.getString("studio_name"));
  						s.setStudio_id(rst.getInt("studio_id"));
  						s.setStudio_col_count(rst.getInt("studio_col_count"));
  						s.setStudio_row_count(rst.getInt("studio_row_count"));
  						s.setStudio_introduction(rst.getString("studio_introduction"));
  						s.setStudio_flag(rst.getInt("studio_flag"));
  						studios.add(s);
  				}
  			}	
  		} catch (Exception a) {
  			a.printStackTrace();
  		}finally {
  			ConnectionManager.close(null, pstmt, connection);
		}
  			
  		return studios;
	}

	public studio selectByStudioId(int id) {
		List<studio> studios = null;
		studio s;
		studios=new LinkedList<studio>();
		ConnectionManager cManager = ConnectionManager.getInstance();
		Connection connection=null;
		connection =  cManager.getConnection();
		PreparedStatement pstmt = null;
  		try {
  			String sql = "select * from studio	where ";
  			sql=sql+" studio_id = "+id;

  		//用线程池	
  			pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.executeQuery(sql);
			ResultSet rst = pstmt.executeQuery(sql);
			
  			if (rst!=null) {
  				while(rst.next()){
  						s = new studio();
  						s.setStudio_name(rst.getString("studio_name"));
  						s.setStudio_id(rst.getInt("studio_id"));
  						s.setStudio_col_count(rst.getInt("studio_col_count"));
  						s.setStudio_row_count(rst.getInt("studio_row_count"));
  						s.setStudio_introduction(rst.getString("studio_introduction"));
  						s.setStudio_flag(rst.getInt("studio_flag"));
  						studios.add(s);
  						
  				}
  			}

  			
  			
  		} catch (Exception a) {
  			a.printStackTrace();
  		}finally {
  			ConnectionManager.close(null, pstmt, connection);
		}
  			
  		return studios.get(0);
	}
	
	public List<studio> PagingQuery(int pag) throws SQLException {
		System.out.println("pagestudio");
		List<studio> list1 = selectAll();
		allCount=list1.size();
		System.out.println(allCount);
		allPageCount=(allCount+PAGE_SIZE-1)/PAGE_SIZE;
		currentPage=pag;
		if(allPageCount>0&&currentPage>allPageCount) {
			currentPage=allPageCount;
		}
		ConnectionManager cManager = ConnectionManager.getInstance();
		Connection connection=null;
		connection =  cManager.getConnection();
		String sql="select * from studio limit ?,?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1,PAGE_SIZE*(currentPage-1));
		ps.setInt(2, PAGE_SIZE);
		ResultSet rs = ps.executeQuery();
		List<studio> studios = new ArrayList<studio>();
		studio s;
		if (rs!=null) {
				while(rs.next()){
						s = new studio();
						s.setStudio_name(rs.getString("studio_name"));
						s.setStudio_id(rs.getInt("studio_id"));
						s.setStudio_col_count(rs.getInt("studio_col_count"));
						s.setStudio_row_count(rs.getInt("studio_row_count"));
						s.setStudio_introduction(rs.getString("studio_introduction"));
						s.setStudio_flag(rs.getInt("studio_flag"));
						studios.add(s);
				}
			}
//		List<Studio> list = run.query(conn, sql,new BeanListHandler<Studio>(Studio.class),PAGE_SIZE*(currentPage-1),PAGE_SIZE);
		cManager.close(rs, ps, connection);
		return studios;
	}

}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import bean.employee;
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

	@Override
	public int insert(studio p) {
		try {
			
			String sql = "insert into studio(studio_name,studio_row_count,"
					+ "studio_col_count,studio_introduction,studio_flag)"
					+ " values(?,?,?,?,?)";
			//sql语句，参数未初始化
			
			ConnectionManager cManager = ConnectionManager.getInstance();
			//调用连接类获取该类实例
			
			Connection connection = cManager.getConnection();
			//通过连接类实例获取数据库连接对象
			
			PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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
				
			
			ResultSet rst = pstmt.getGeneratedKeys();	
			//获取结果集
			
			if(rst.next()&&rst.first()){
				p.setStudio_id(rst.getInt(1));
			}
			
			cManager.close(rst, pstmt, connection);
			//关闭所有连接
			
			System.out.println("studio插入成功");
			
			return 1;
			//操作成功返回1
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(studio stu) {
		try {
			String sql = "update studio set " + " studio_name ='"
					+ stu.getStudio_name() + "', " + " studio_row_count = "
					+ stu.getStudio_row_count() + ", " + " studio_col_count = "
					+ stu.getStudio_col_count() + ", " + " studio_introduction = '"
					+ stu.getStudio_introduction() + "' ,"+" studio_flag = "
					+stu.getStudio_flag();

			sql += " where studio_id = " + stu.getStudio_id();
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
	public int delete(int s_id) {
		try {
			String sql = "delete from  studio ";
			sql += " where studio_id = " + s_id;
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
	public studio select(String condt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<studio> selectAll() {
		List<studio> studios = null;
		studio s;
		studios=new LinkedList<studio>();
  		try {
  			String sql = "select * from studio	";

  		//用线程池	
  			ConnectionManager cManager = ConnectionManager.getInstance();
  			Connection connection=null;
  			connection =  cManager.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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

  			cManager.close(rst, pstmt, connection);
  			
  		} catch (Exception a) {
  			a.printStackTrace();
  		}
  			
  		return studios;
	}

	@Override
	public List<studio> selectwhat(String condt) {
		// TODO Auto-generated method stub
		return null;
	}

}

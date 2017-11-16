package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import bean.play;
import idao.iPlayDAO;
import tomcatDb.ConnectionManager;
//private int play_id;
//private int play_type_id;
//private int play_lang_id;
//private String play_name;
//private String play_introduction;
//private String play_image;
//private int play_length;
//private double play_ticket_price;
//private int play_status;//    0：待安排演出1：已安排演出 -1：下线',
/**
 * 影片DAO
 * @author SHEEO
 *
 */
public class PlayDao implements iPlayDAO{

	@Override
	public int insert(play p) {
		try {

			String sql = "insert into play(play_type_id,play_lang_id,"
					+ "play_name,play_introduction,play_image,play_length,"
					+ "play_ticket_price,play_status)"
					+ " values(?,?,?,?,?,?,?,?)";
			
			ConnectionManager cManager = ConnectionManager.getInstance();
			Connection connection = cManager.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			
			pstmt.setInt(1, p.getPlay_type_id());
			pstmt.setInt(2, p.getPlay_lang_id());
			pstmt.setString(3, p.getPlay_name());
			pstmt.setString(4, p.getPlay_introduction());
			pstmt.setString(5, p.getPlay_image());
			pstmt.setInt(6, p.getPlay_length());
			pstmt.setDouble(7, p.getPlay_ticket_price());
			pstmt.setInt(8, p.getPlay_status());
			
			pstmt.executeUpdate();
			ResultSet rst = pstmt.getGeneratedKeys();	
			cManager.close(rst, pstmt, connection);

			
			System.out.println("play插入成功");
			return 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(play p) {
		
		return 0;
	}

	@Override
	public int delete(int p_id) {
		try {
			String sql = "delete from  play ";
			sql += " where play_id = " + p_id;
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
	public play select(String condt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<play> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<play> selectwhat(String condt) {
		// TODO Auto-generated method stub
		return null;
	}

}

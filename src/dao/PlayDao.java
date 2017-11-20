package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import bean.play;
import bean.studio;
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
	public int update(play stu) {
		try {

			String sql = "update play set "  + " play_name = '"
					+ stu.getPlay_name() + "' ,"+"play_introduction = '"
					+stu.getPlay_introduction()+"',"+"play_image='"
					+stu.getPlay_image()+"',"+"play_length="
					+stu.getPlay_length()+","+"play_ticket_price="
					+stu.getPlay_ticket_price()+","+"play_status="
					+stu.getPlay_status();

			sql += " where play_id = " + stu.getPlay_id();
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
		List<play> plays = null;
		play p=null;
		plays=new LinkedList<play>();
  		try {
  			String sql = "select * from play	";

  		//用线程池	
  			ConnectionManager cManager = ConnectionManager.getInstance();
  			Connection connection=null;
  			connection =  cManager.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rst = pstmt.executeQuery(sql);
			
  			if (rst!=null) {
  				while(rst.next()){
  						p=new play();
  						p.setPlay_id(rst.getInt("play_id"));
  						p.setPlay_image(rst.getString("play_image"));
  						p.setPlay_introduction(rst.getString("play_introduction"));
  						p.setPlay_lang_id(rst.getInt("play_lang_id"));
  						p.setPlay_length(rst.getInt("play_length"));
  						p.setPlay_name(rst.getString("play_name"));
  						p.setPlay_status(rst.getInt("play_status"));
  						p.setPlay_ticket_price(rst.getDouble("play_ticket_price"));
  						p.setPlay_type_id(rst.getInt("play_type_id"));
  						plays.add(p);
  	
  						
  				}
  			}

  			cManager.close(rst, pstmt, connection);
  			
  		} catch (Exception a) {
  			a.printStackTrace();
  		}
  			
  		return plays;
	}

	@Override
	public List<play> selectwhat(String condt) {
		// TODO Auto-generated method stub
		return null;
	}

}

package database;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JOptionPane; 
/**
 * 数据库连接工具类
 * @author SHEEO
 *
 */
public class DBUtil {
//	private final String dbConnFile = "resource/database/jdbc.properties";
	private Connection conn=null;
	private String dbDriver;	//定义驱动  
    private String dbURL;		//定义URL  
    private String userName;	//定义用户名  
    private String password;	//定义密码	
    
    //从配置文件取数据库链接参数  
    private void loadConnProperties(){  
//        Properties props = new Properties();  
//        try {  
//            props.load(new FileInputStream(dbConnFile));//根据配置文件路径Conf加载配置文件  
//        } catch (FileNotFoundException e) {  
//            e.printStackTrace();  
//        } catch (IOException e) {  
//            e.printStackTrace();  
//        }  
//        this.dbDriver = props.getProperty("driver");//从配置文件中取得相应的参数并设置类变量  
//        this.dbURL = props.getProperty("url");  
//        this.userName = props.getProperty("username");  
//        this.password = props.getProperty("password");  
    	userName = "root";//数据库登陆用户名
    	password = "123456";//数据库登陆密码
    	dbURL = "jdbc:mysql://127.0.0.1:3306/web_ttms?useUnicode=true&characterEncoding=utf-8";
    	dbDriver = "com.mysql.jdbc.Driver";
     
    }
    
	public boolean openConnection(){
		try {  
			loadConnProperties();
            Class.forName(dbDriver);  
            this.conn = DriverManager.getConnection(dbURL,userName,password);
            return true;
        } catch(ClassNotFoundException classnotfoundexception) {  
              classnotfoundexception.printStackTrace();  
            System.err.println("db: " + classnotfoundexception.getMessage());  
        } catch(SQLException sqlexception) {  
            System.err.println("db.getconn(): " + sqlexception.getMessage());  
        }
		return	false;
	}
	
	
	protected void finalize() throws Exception{
		try {
		if(null!=conn)
			conn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
     }
//	con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
//	con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	
	// 查询并得到结果集
	public ResultSet execQuery(String sql) throws Exception {
		ResultSet rstSet = null;
		try {
			if (null == conn)
				throw new Exception("Database not connected!");
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rstSet = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rstSet;
	}

	// 插入一条新纪录，并获取标识列的值
		public ResultSet getInsertObjectIDs(String insertSql) throws Exception{
			ResultSet rst = null;
			try {
				if(null==conn)
					throw new Exception("Database not connected!");

				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				stmt.executeUpdate(insertSql, Statement.RETURN_GENERATED_KEYS);
				rst = stmt.getGeneratedKeys();
				
			} catch (SQLException e) {
				e.printStackTrace();
//				JOptionPane.showMessageDialog(null, "不能添加已存在的");
			}
			return rst;
		}
		
		//以参数SQL模式插入新纪录，并获取标识列的值
		public ResultSet getInsertObjectIDs(String insertSql, Object[] params) throws Exception {
			ResultSet rst = null;
			PreparedStatement pstmt = null ;
			try {
				if (null == conn)
					throw new Exception("Database not connected!");
				pstmt = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
				
				if(null != params){  
		            for (int i = 0; i < params.length; i++) {  
		            	pstmt.setObject(i + 1, params[i]);  
		            }  
		        }
				pstmt.executeUpdate();
				rst = pstmt.getGeneratedKeys();			
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return rst;
		}
		
		

	// 插入、更新、删除
	public int execCommand(String sql) throws Exception{
		int flag = 0;
		try {
			if(null==conn)
				throw new Exception("Database not connected!");
			
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			flag = stmt.executeUpdate(sql);
			
			stmt.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}


	
	// 释放资源
	public void close(ResultSet rst) throws Exception {
		try {
			Statement stmt = rst.getStatement();
			rst.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	// 释放资源
	public void close(Statement stmt) throws Exception {
		try {
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 释放资源
	public void close() throws SQLException, Exception{
		if(null!=conn){
			conn.close();
			conn=null;
		}
	}

	public Connection getConn() {
		return conn;
	}

}
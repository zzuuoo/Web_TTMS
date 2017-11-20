package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import bean.data_dict;
import idao.IDataDictDAO;
import tomcatDb.ConnectionManager;


public class DataDictDAO implements IDataDictDAO{

//	private int dict_id;
//	private int dict_parent_id;
//	private int dict_index;	
//	private String dict_name;
//	private String dict_value;
	@Override
	public int insert(data_dict ddict) {
		// TODO Auto-generated method stub
		
		try {
			String sql = "insert into data_dict( dict_parent_id,  dict_index , dict_name , dict_value )"
					+ " values(?,?,?,?)";
			ConnectionManager cManager = ConnectionManager.getInstance();
			//调用连接类获取该类实例
			
			Connection connection = cManager.getConnection();
			//通过连接类实例获取数据库连接对象
			
			PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			//获取PreparedStatement借口实例
			
			
				pstmt.setInt(1, ddict.getDict_parent_id());
				pstmt.setInt(2, ddict.getDict_index());
				pstmt.setString(3,ddict.getDict_name());
				pstmt.setString(4, ddict.getDict_value());

				//初始化sql中的参数
				
				pstmt.executeUpdate();
				//执行sql语句
	
			
			ResultSet rst = pstmt.getGeneratedKeys();	
			//获取结果集
			
			if(rst.next()&&rst.first()){
				ddict.setDict_id(rst.getInt(1));
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
	public int update(data_dict ddict) {
		// TODO Auto-generated method stub
		int rtn=0;
		try {
			String sql = "update data_dict set " 
		            + " dict_parent_id =" + ddict.getDict_parent_id()+ ", " 
					+ "  dict_index = " +  ddict.getDict_index() + ", " 
					+ " dict_name = '" + ddict.getDict_name() + "', "
					+ " dict_value = '"+ ddict.getDict_value()+ "' ";

			sql += " where dict_id = " + ddict.getDict_id();
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
	public int delete(int ID) {
		// TODO Auto-generated method stub
		int rtn=0;		
		try{
			String sql = "delete from  data_dict ";
			sql += " where dict_id = " + ID;
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
	public List<data_dict> select(String condt) {
		// TODO Auto-generated method stub
		List<data_dict> ddictList = null;
		data_dict dd = null;
		ddictList=new LinkedList<data_dict>();
		try {
			String sql = "select * from data_dict ";
			condt.trim();
			if(!condt.isEmpty())
				sql+= " where " + condt;
			
  		//用线程池	
  			ConnectionManager cManager = ConnectionManager.getInstance();
  			Connection connection=null;
  			connection =  cManager.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.executeQuery(sql);
			ResultSet rst = pstmt.executeQuery(sql);
			
  			if (rst!=null) {
  				while(rst.next()){
  						dd = new data_dict();
  						dd.setDict_id(rst.getInt("dict_id"));
  						dd.setDict_index(rst.getInt("dict_index"));
  						dd.setDict_name(rst.getString("dict_name"));
  						dd.setDict_parent_id(rst.getInt("dict_parent_id"));
  						dd.setDict_value(rst.getString("dict_value"));
  						ddictList.add(dd);
  						
  				}
  			}

  			cManager.close(rst, pstmt, connection);
  			
  		} catch (Exception a) {
  			a.printStackTrace();
  		}
  			
  		return ddictList;
	}
		 
	public List<data_dict> findByID(int id) {
		return select(" dict_parent_id =" +id);
	
	}
	
	public void findAllSonByID(List<data_dict> list, int id)
	{
		List<data_dict> childList = findByID(id);
		for(int i=0;i<childList.size();i++){
			if(!hasChildren(childList.get(i).getDict_id()))
				list.add(childList.get(i));
			else 
			{
				 findAllSonByID(list, childList.get(i).getDict_id());
			}
		}
	}
	
	// 给定的节点是否有孩子节点
	public boolean hasChildren(int id) {
		List<data_dict> list = select(" dict_parent_id =" +id);
		return list.size()>0 ? true : false;
	}
	
	
//	public static void main(String[] args)
//	{
//		data_dictDAO dictDAO = new data_dictDAO();
//		List<data_dict> list = dictDAO.findByID(1);
//		
//		//for(int i=0;i<list.size();i++) System.out.println(list.get(i).getName());
//		System.out.println(dictDAO.hasChildren(2));
//		
//		List<data_dict> list1 = new ArrayList<data_dict>();
//		
//		dictDAO.findAllSonByID(list1,1);
//		for(int i=0;i<list1.size();i++) System.out.println(list1.get(i).getId());
//	}

	@Override
	public data_dict selectOne(String condt) {
		// TODO Auto-generated method stub
		data_dict dd = null;
		try {
			String sql = "select * from data_dict ";
			condt.trim();
			if(!condt.isEmpty())
				sql+= " where " + condt;
			
  		//用线程池	
  			ConnectionManager cManager = ConnectionManager.getInstance();
  			Connection connection=null;
  			connection =  cManager.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.executeQuery(sql);
			ResultSet rst = pstmt.executeQuery(sql);
			
  			if (rst!=null) {
  				while(rst.next()){
  						dd = new data_dict();
  						dd.setDict_id(rst.getInt("dict_id"));
  						dd.setDict_index(rst.getInt("dict_index"));
  						dd.setDict_name(rst.getString("dict_name"));
  						dd.setDict_parent_id(rst.getInt("dict_parent_id"));
  						dd.setDict_value(rst.getString("dict_value"));
  						
  				}
  			}

  			cManager.close(rst, pstmt, connection);
  			
  		} catch (Exception a) {
  			a.printStackTrace();
  		}
  			
  		return dd;
	}

}

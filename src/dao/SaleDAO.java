package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import bean.GlobalVariable;
import bean.sale;
import bean.ticket;
import idao.iSaleDAO;
import tomcatDb.ConnectionManager;

public class SaleDAO implements iSaleDAO{

	@Override
	public boolean doSale(List<ticket> tickets) {
		try {
		int id = -1;
		
		ConnectionManager cManager = ConnectionManager.getInstance();
		Connection connection = cManager.getConnection();		
		connection.setAutoCommit(false);
		double n=0;
		if(!tickets.isEmpty()){
			n=tickets.get(0).getTicket_price();
		}
		double payment = tickets.size()*n;
        String sql = "insert into sale(sale_time, emp_id,sale_payment,sale_type, sale_status) VALUES(?,"+GlobalVariable.emp_id+","+payment+",1,1)";  
        PreparedStatement prep = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);  
        prep.setTimestamp(1, new Timestamp(new Date().getTime()));  
        prep.executeUpdate();  
        ResultSet rs = prep.getGeneratedKeys();  
        if (rs.next()) {  
            id = rs.getInt(1);  
        }
        if(id>0){
        	for(ticket t : tickets){
	        	double price = t.getTicket_price();
		        String sql2 = "insert into sale_item(ticket_id, sale_ID, sale_item_price) VALUES(" +
	        	t.getTicket_id() + ", " + id + ", " + price + ")";  
		        prep = connection.prepareStatement(sql2, PreparedStatement.RETURN_GENERATED_KEYS);   
	        	int flag = prep.executeUpdate(); 
	        	if(flag==1){
			        String sql3 = "update ticket set ticket_status=9 where ticket_id = " + t.getTicket_id();
			        prep = connection.prepareStatement(sql3, PreparedStatement.RETURN_GENERATED_KEYS);  
			        int flag2 = prep.executeUpdate();  
		        	if(flag2!=1){
		        		cManager.close(rs, prep, connection);
		        		return false;
		        	}
	        	}else
	        		return false;
        	}
        }
        connection.commit();
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	} finally {
//		cManager.close(rst, pstmt, connection);
	}
		
	return true;
	}

	@Override
	public boolean refund(ticket tickets) {
		try {
		int id = -1;
		ConnectionManager cManager = ConnectionManager.getInstance();
		Connection connection =  cManager.getConnection();
		connection.setAutoCommit(false);

		double n=0;
//		if(!tickets.isEmpty()){
//			n=tickets.get(0).getPrice();
//		}
		if(tickets!=null){
			n=tickets.getTicket_price();
		}
//		private int type;  // 1：销售单  -1：退款单
//		private int status;  // 0：待付款   1：已付款 2:已退款
		double payment = n;
        String sql = "insert into sale(sale_time, emp_id,sale_payment,sale_type, sale_status) VALUES(?,"+ GlobalVariable.emp_id+","+payment+",-1,2)";  
        PreparedStatement prep = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);  
        prep.setTimestamp(1, new Timestamp(new Date().getTime()));  
        prep.executeUpdate();  
        ResultSet rs = prep.getGeneratedKeys();  
        if (rs.next()) {  
            id = rs.getInt(1);  
        }
        if(id>0){
//        	for(Ticket t : tickets){
	        	double price = tickets.getTicket_price();
		        String sql2 = "insert into sale_item(ticket_id, sale_ID, sale_item_price) VALUES(" +
		        		tickets.getTicket_id() + ", " + id + ", " + price + ")";  
		        prep = connection.prepareStatement(sql2, PreparedStatement.RETURN_GENERATED_KEYS);  
	        	int flag = prep.executeUpdate();
	        	if(flag==1){
//	        		 String sql4 = "update ticket set ticket_locked_time = NULL where ticket_id = " + tickets.getId();
			        String sql3 = "update ticket set ticket_status=0,ticket_locked_time = NULL where ticket_id = " + tickets.getTicket_id();
			        prep = connection.prepareStatement(sql3, PreparedStatement.RETURN_GENERATED_KEYS);  
		        	int flag2 = prep.executeUpdate();
		        	if(flag2!=1){
		        		return false;	
		        	}
	        	}else
	        		return false;
//        	}
        }
        connection.commit();
	} catch (Exception e) {
		e.printStackTrace();
//		try {
//			connection.rollback();
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//			return false;
//		}
		return false;
	} finally {
//		try {
//			con.setAutoCommit(true);
//			db.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
	}
	return true;
	}

	@Override
	public List<sale> select(String condt) {
		List<sale> stuList = null;
//	SimpleDateFormat sdf = new SimpleDateFormat("YY-MM-dd HH:mm");
	stuList = new LinkedList<sale>();
	try {
		String sql = "select sale_ID, emp_id,sale_time, sale_payment, sale_change,sale_type,sale_status from sale ";
		condt.trim();
		if (!condt.isEmpty())
			sql += " where " + condt;
		ConnectionManager cManager = ConnectionManager.getInstance();
		Connection connection = cManager.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
		ResultSet rst = pstmt.executeQuery(sql);
		if (rst != null) {
			while (rst.next()) {
				sale stu = new sale();
				stu.setSale_ID(rst.getInt("sale_ID"));
				stu.setEmp_id(rst.getInt("emp_id"));
				stu.setSale_time(rst.getTimestamp("sale_time"));
				stu.setSale_payment(rst.getFloat("sale_payment"));
				stu.setSale_change(rst.getFloat("sale_change"));
				stu.setSale_type(rst.getInt("sale_type"));
				stu.setSale_status(rst.getInt("sale_status"));
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

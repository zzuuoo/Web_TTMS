//package dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.LinkedList;
//import java.util.List;
//
//import bean.ticket;
//
//
//
//public class SaleDAO implements iSaleDAO {
//
//	 
//	public boolean doSale(List<ticket> tickets) {
//		try {
//			int id = -1;
//			db = new DBUtil();
//			db.openConnection();
//			con = db.getConn();
//			con.setAutoCommit(false);
//
//			double n=0;
//			if(!tickets.isEmpty()){
//				n=tickets.get(0).getPrice();
//			}
//			double payment = tickets.size()*n;
//	        String sql = "insert into sale(sale_time, emp_id,sale_payment,sale_type, sale_status) VALUES(?,"+GlobalVariable.emp_id+","+payment+",1,1)";  
//	        PreparedStatement prep = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);  
//	        prep.setTimestamp(1, new Timestamp(new Date().getTime()));  
//	        prep.executeUpdate();  
//	        ResultSet rs = prep.getGeneratedKeys();  
//	        if (rs.next()) {  
//	            id = rs.getInt(1);  
//	        }
//	        if(id>0){
//	        	for(Ticket t : tickets){
//		        	double price = t.getSchedule().getSched_ticket_price();
//			        String sql2 = "insert into sale_item(ticket_id, sale_ID, sale_item_price) VALUES(" +
//		        	t.getId() + ", " + id + ", " + price + ")";  
//		        	int flag = db.execCommand(sql2);
//		        	if(flag==1){
//				        String sql3 = "update ticket set ticket_status=9 where ticket_id = " + t.getId();
//			        	int flag2 = db.execCommand(sql3);
//			        	if(flag2!=1){
//			        		return false;
//			        	}
//		        	}else
//		        		return false;
//	        	}
//	        }
//			con.commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//			try {
//				con.rollback();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//				return false;
//			}
//			return false;
//		} finally {
//			try {
//				con.setAutoCommit(true);
//				db.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//				return false;
//			}
//		}
//		return true;
//	}
//
//
//	@Override
//	public boolean refund(Ticket tickets) {
//		try {
//			int id = -1;
//			db = new DBUtil();
//			db.openConnection();
//			con = db.getConn();
//			con.setAutoCommit(false);
//
//			double n=0;
////			if(!tickets.isEmpty()){
////				n=tickets.get(0).getPrice();
////			}
//			if(tickets!=null){
//				n=tickets.getPrice();
//			}
////			private int type;  // 1：销售单  -1：退款单
////			private int status;  // 0：待付款   1：已付款 2:已退款
//			double payment = n;
//	        String sql = "insert into sale(sale_time, emp_id,sale_payment,sale_type, sale_status) VALUES(?,"+ GlobalVariable.emp_id+","+payment+",-1,2)";  
//	        PreparedStatement prep = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);  
//	        prep.setTimestamp(1, new Timestamp(new Date().getTime()));  
//	        prep.executeUpdate();  
//	        ResultSet rs = prep.getGeneratedKeys();  
//	        if (rs.next()) {  
//	            id = rs.getInt(1);  
//	        }
//	        if(id>0){
////	        	for(Ticket t : tickets){
//		        	double price = tickets.getSchedule().getSched_ticket_price();
//			        String sql2 = "insert into sale_item(ticket_id, sale_ID, sale_item_price) VALUES(" +
//			        		tickets.getId() + ", " + id + ", " + price + ")";  
//		        	int flag = db.execCommand(sql2);
//		        	if(flag==1){
////		        		 String sql4 = "update ticket set ticket_locked_time = NULL where ticket_id = " + tickets.getId();
//				        String sql3 = "update ticket set ticket_status=0,ticket_locked_time = NULL where ticket_id = " + tickets.getId();
//			        	int flag2 = db.execCommand(sql3);
//			        	if(flag2!=1){
//			        		return false;	
//			        	}
//		        	}else
//		        		return false;
////	        	}
//	        }
//			con.commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//			try {
//				con.rollback();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//				return false;
//			}
//			return false;
//		} finally {
//			try {
//				con.setAutoCommit(true);
//				db.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//				return false;
//			}
//		}
//		return true;
//	}
//
//
//	@Override
//	public List<Sale> select(String condt) {
//		List<Sale> stuList = null;
////		SimpleDateFormat sdf = new SimpleDateFormat("YY-MM-dd HH:mm");
//		stuList = new LinkedList<Sale>();
//		try {
//			String sql = "select sale_ID, emp_id,sale_time, sale_payment, sale_change,sale_type,sale_status from sale ";
//			condt.trim();
//			if (!condt.isEmpty())
//				sql += " where " + condt;
//			DBUtil db = new DBUtil();
//			if (!db.openConnection()) {
//				System.out.print("fail to connect database");
//				return null;
//			}
//			ResultSet rst = db.execQuery(sql);
//			if (rst != null) {
//				while (rst.next()) {
//					Sale stu = new Sale();
//					stu.setId(rst.getInt("sale_ID"));
//					stu.setEmpId(rst.getInt("emp_id"));
//					stu.setTime(rst.getTimestamp("sale_time"));
//					stu.setPayment(rst.getFloat("sale_payment"));
//					stu.setChange(rst.getFloat("sale_change"));
//					stu.setType(rst.getInt("sale_type"));
//					stu.setStatus(rst.getInt("sale_status"));
//					stuList.add(stu);
//				}
//			}
//			db.close(rst);
//			db.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//
//		}
//
//		return stuList;
//	}
//}

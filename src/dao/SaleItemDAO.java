package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import bean.sale_item;
import idao.iSaleItemDAO;
import tomcatDb.ConnectionManager;

public class SaleItemDAO implements iSaleItemDAO{

	@Override
	public List<sale_item> select(String condt) {
		// TODO Auto-generated method stub
		List<sale_item> stuList = null;
//		SimpleDateFormat sdf = new SimpleDateFormat("YY-MM-dd HH:mm");
		stuList = new LinkedList<sale_item>();
		try {
			String sql = "select sale_item_id, ticket_id,sale_ID, sale_item_price  from sale_item ";
			condt.trim();
			if (!condt.isEmpty())
				sql += " where " + condt;
			ConnectionManager cManager = ConnectionManager.getInstance();
  			Connection connection=null;
  			connection =  cManager.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.executeQuery(sql);
			ResultSet rst = pstmt.executeQuery(sql);
			if (rst != null) {
				while (rst.next()) {
					sale_item stu = new sale_item();
					stu.setSale_ID(rst.getInt("sale_id"));
					stu.setSale_item_id(rst.getInt("sale_item_id"));
					stu.setSale_item_price(rst.getDouble("sale_item_price"));
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
//		return null;

}

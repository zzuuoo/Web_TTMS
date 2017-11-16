/**
 * 
 */
package idao;

import java.util.List;

import bean.sale;
import bean.ticket;

/**
 * @author Administrator
 *
 */
public interface iSaleDAO {
	public boolean doSale(List<ticket> tickets);
	public boolean refund(ticket tickets);
	public List<sale> select(String condt);
}

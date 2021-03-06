/**
 * 
 */
package idao;

import java.util.List;

import bean.ticket;

/**
 * @author Administrator
 *
 */
public interface iTicketDAO {
	public int insert(ticket stu);
	public int update(ticket stu);
	public int delete(int ID);
	public int lockTicket(int ID, String time);
	public int unlockTicket(int ID);
	public List<ticket> select(String condt); 
	public int delete(String  con);
	public ticket selectOne(String condt); 
}

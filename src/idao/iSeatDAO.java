/**
 * 
 */
package idao;

import java.util.List;

import bean.seat;

/**
 * @author Administrator
 *
 */
public interface iSeatDAO {
	public int insert(seat stu);
	public int update(seat stu);
	public int delete(int ID);
	public int delete(String condt);
	public List<seat> select(String condt); 
}

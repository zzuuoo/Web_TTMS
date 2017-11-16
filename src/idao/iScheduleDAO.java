/**
 * 
 */
package idao;

import java.util.List;

import bean.schedule;

/**
 * @author Administrator
 *
 */
public interface iScheduleDAO {
	public int insert(schedule stu);
	public int update(schedule stu);
	public int delete(int ID);
	public List<schedule> select(String condt); 
	public schedule selectOne(String condt);
}

package idao;

import java.util.List;

import bean.studio;

public interface iStudioDAO {
	public int insert(studio p);
	public int update(studio p);
	public int delete(int s_id);
	public studio select(String condt); 
	public List<studio> selectAll(); 
	public List<studio> selectwhat(String condt) ; 
}

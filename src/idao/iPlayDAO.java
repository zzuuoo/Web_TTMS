package idao;

import java.util.List;

import bean.play;

public interface iPlayDAO {
	public int insert(play p);
	public int update(play p);
	public int delete(int p_id);
	public play select(String condt); 
	public List<play> selectAll(); 
	public List<play> selectwhat(String condt) ; 
}

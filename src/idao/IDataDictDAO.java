package idao;

import java.util.List;

import bean.data_dict;

public interface IDataDictDAO {
	public int insert(data_dict ddict);
	public int update(data_dict ddict);
	public int delete(int ID);
	public List<data_dict> select(String condt); 
	public data_dict selectOne(String condt); 
	public List<data_dict> findByID(int id) ;
	public void findAllSonByID(List<data_dict> list, int id);
	public boolean hasChildren(int id) ;
}

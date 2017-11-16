package idao;

import java.util.List;

import bean.sale_item;

public interface iSaleItemDAO {

	public List<sale_item> select(String condt);
}

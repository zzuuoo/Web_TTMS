package bean;

public class data_dict {
	private int dict_id;
	private int dict_parent_id;
	private int dict_index;	
	private String dict_name;
	private String dict_value;
	public int getDict_id() {
		return dict_id;
	}
	public void setDict_id(int dict_id) {
		this.dict_id = dict_id;
	}
	public int getDict_parent_id() {
		return dict_parent_id;
	}
	public void setDict_parent_id(int dict_parent_id) {
		this.dict_parent_id = dict_parent_id;
	}
	public int getDict_index() {
		return dict_index;
	}
	public void setDict_index(int dict_index) {
		this.dict_index = dict_index;
	}
	public String getDict_name() {
		return dict_name;
	}
	public void setDict_name(String dict_name) {
		this.dict_name = dict_name;
	}
	public String getDict_value() {
		return dict_value;
	}
	public void setDict_value(String dict_value) {
		this.dict_value = dict_value;
	}
	
}
//CREATE TABLE `data_dict` (
//		  `dict_id` int(11) NOT NULL AUTO_INCREMENT,
//		  `dict_parent_id` int(11) DEFAULT NULL,
//		  `dict_index` int(11) DEFAULT NULL,
//		  `dict_name` varchar(200) DEFAULT NULL,
//		  `dict_value` varchar(100) NOT NULL,
//		  PRIMARY KEY (`dict_id`),
//		  KEY `FK_super_child_dict` (`dict_parent_id`),
//		  CONSTRAINT `FK_super_child_dict` FOREIGN KEY (`dict_parent_id`) REFERENCES `data_dict` (`dict_id`)
//		) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='数据字典(影片类型、锁定时长、语言)';

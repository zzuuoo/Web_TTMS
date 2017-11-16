package bean;

import java.awt.HeadlessException;

public class user {
	private String emp_no;//用户名，非空
	private String emp_pass;//密码，非空
	private int type;//'用户类型：0为普通用户，1是管理员',非空
	private String Head_path;//头像路径
	public String getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}
	public String getEmp_pass() {
		return emp_pass;
	}
	public void setEmp_pass(String emp_pass) {
		this.emp_pass = emp_pass;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getHead_path() {
		return Head_path;
	}
	public void setHead_path(String head_path) {
		Head_path = head_path;
	}
	
}
//CREATE TABLE `user` (
//		  `emp_no` varchar(20) NOT NULL COMMENT '用户名',
//		  `emp_pass` varchar(20) NOT NULL DEFAULT '123456' COMMENT '密码',
//		  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '用户类型：0为普通用户，1是管理员',
//		  `head_path` varchar(500) DEFAULT NULL COMMENT '头像路径',
//		  PRIMARY KEY (`emp_no`),
//		  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`emp_no`) REFERENCES `employee` (`emp_no`) ON DELETE CASCADE ON UPDATE CASCADE
//		) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登陆信息表';

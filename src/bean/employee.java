package bean;

public class employee {
	private int emp_id;
	private String emp_no;
	private String emp_name;
	private String emp_tel_num;
	private String emp_addr;
	private String emp_email;
	public String getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getEmp_tel_num() {
		return emp_tel_num;
	}
	public void setEmp_tel_num(String emp_tel_num) {
		this.emp_tel_num = emp_tel_num;
	}
	public String getEmp_addr() {
		return emp_addr;
	}
	public void setEmp_addr(String emp_addr) {
		this.emp_addr = emp_addr;
	}
	public String getEmp_email() {
		return emp_email;
	}
	public void setEmp_email(String emp_email) {
		this.emp_email = emp_email;
	}
	/**
	 * @return the emp_id
	 */
	public int getEmp_id() {
		return emp_id;
	}
	/**
	 * @param emp_id the emp_id to set
	 */
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	
}
//CREATE TABLE `employee` (
//		  `emp_id` int(11) NOT NULL AUTO_INCREMENT,
//		  `emp_no` varchar(20) NOT NULL,
//		  `emp_name` varchar(100) NOT NULL,
//		  `emp_tel_num` varchar(20) DEFAULT NULL,
//		  `emp_addr` varchar(200) DEFAULT NULL,
//		  `emp_email` varchar(100) DEFAULT NULL,
//		  PRIMARY KEY (`emp_id`),
//		  UNIQUE KEY `emp_no` (`emp_no`)
//		) 
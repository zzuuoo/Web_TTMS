package bean;

import java.io.Serializable;

public class seat implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8693940514254107039L;
	private int seat_id;
	private int studio_id;
	private int seat_row;
	private int seat_column;
	private int seat_status;//
	public int getSeat_id() {
		return seat_id;
	}
	public void setSeat_id(int seat_id) {
		this.seat_id = seat_id;
	}
	public int getStudio_id() {
		return studio_id;
	}
	public void setStudio_id(int studio_id) {
		this.studio_id = studio_id;
	}
	public int getSeat_row() {
		return seat_row;
	}
	public void setSeat_row(int seat_row) {
		this.seat_row = seat_row;
	}
	public int getSeat_column() {
		return seat_column;
	}
	public void setSeat_column(int seat_column) {
		this.seat_column = seat_column;
	}
	public int getSeat_status() {
		return seat_status;
	}
	public void setSeat_status(int seat_status) {
		this.seat_status = seat_status;
	}
	
}
//CREATE TABLE `seat` (
//		  `seat_id` int(11) NOT NULL AUTO_INCREMENT,
//		  `studio_id` int(11) DEFAULT NULL,
//		  `seat_row` int(11) DEFAULT NULL,
//		  `seat_column` int(11) DEFAULT NULL,
//		  `seat_status` smallint(6) NOT NULL DEFAULT '1' COMMENT '取值含义：\r\n                       0：此处是空位，没有安排座椅\r\n                       1：完好的座位，可以使用\r\n                       -1：座位损坏，不能安排座位',
//		  PRIMARY KEY (`seat_id`),
//		  KEY `FK_studio_seat` (`studio_id`),
//		  CONSTRAINT `FK_studio_seat` FOREIGN KEY (`studio_id`) REFERENCES `studio` (`studio_id`)
//		) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COMMENT='座位表';

package bean;

import java.io.Serializable;

public class play implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2908610880493503611L;
	private int play_id;
	private int play_type_id;
	private int play_lang_id;
	private String play_name;
	private String play_introduction;
	private String play_image;
	private int play_length;
	private double play_ticket_price;
	private int play_status;//    0：待安排演出1：已安排演出 -1：下线',
	public int getPlay_id() {
		return play_id;
	}
	public void setPlay_id(int play_id) {
		this.play_id = play_id;
	}
	public int getPlay_type_id() {
		return play_type_id;
	}
	public void setPlay_type_id(int play_type_id) {
		this.play_type_id = play_type_id;
	}
	public int getPlay_lang_id() {
		return play_lang_id;
	}
	public void setPlay_lang_id(int play_lang_id) {
		this.play_lang_id = play_lang_id;
	}
	public String getPlay_name() {
		return play_name;
	}
	public void setPlay_name(String play_name) {
		this.play_name = play_name;
	}
	public String getPlay_introduction() {
		return play_introduction;
	}
	public void setPlay_introduction(String play_introduction) {
		this.play_introduction = play_introduction;
	}
	public String getPlay_image() {
		return play_image;
	}
	public void setPlay_image(String play_image) {
		this.play_image = play_image;
	}
	public int getPlay_length() {
		return play_length;
	}
	public void setPlay_length(int play_length) {
		this.play_length = play_length;
	}
	public double getPlay_ticket_price() {
		return play_ticket_price;
	}
	public void setPlay_ticket_price(double play_ticket_price) {
		this.play_ticket_price = play_ticket_price;
	}
	public int getPlay_status() {
		return play_status;
	}
	public void setPlay_status(int play_status) {
		this.play_status = play_status;
	}
	
}
//CREATE TABLE `play` (
//		  `play_id` int(11) NOT NULL AUTO_INCREMENT,
//		  `play_type_id` int(11) DEFAULT NULL,
//		  `play_lang_id` int(11) DEFAULT NULL,
//		  `play_name` varchar(200) DEFAULT NULL,
//		  `play_introduction` varchar(2000) DEFAULT NULL,
//		  `play_image` varchar(1000) DEFAULT NULL,
//		  `play_length` int(11) DEFAULT NULL,
//		  `play_ticket_price` decimal(10,2) DEFAULT NULL,
//		  `play_status` smallint(6) DEFAULT NULL COMMENT '取值含义：\r\n            0：待安排演出\r\n            1：已安排演出\r\n            -1：下线',
//		  PRIMARY KEY (`play_id`),
//		  KEY `FK_dict_lan_play` (`play_lang_id`),
//		  KEY `FK_dict_type_play` (`play_type_id`),
//		  CONSTRAINT `FK_dict_lan_play` FOREIGN KEY (`play_lang_id`) REFERENCES `data_dict` (`dict_id`),
//		  CONSTRAINT `FK_dict_type_play` FOREIGN KEY (`play_type_id`) REFERENCES `data_dict` (`dict_id`)
//		) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='影片信息表';

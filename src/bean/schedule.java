package bean;

import java.io.Serializable;
import java.util.Date;

public class schedule implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2188919427835202313L;
	private int sched_id;
	private int studio_id;
	private int play_id;
	private Date sched_time;
	private double sched_ticket_price;
	public int getSched_id() {
		return sched_id;
	}
	public void setSched_id(int sched_id) {
		this.sched_id = sched_id;
	}
	public int getStudio_id() {
		return studio_id;
	}
	public void setStudio_id(int studio_id) {
		this.studio_id = studio_id;
	}
	public int getPlay_id() {
		return play_id;
	}
	public void setPlay_id(int play_id) {
		this.play_id = play_id;
	}
	public Date getSched_time() {
		return sched_time;
	}
	public void setSched_time(Date sched_time) {
		this.sched_time = sched_time;
	}
	public double getSched_ticket_price() {
		return sched_ticket_price;
	}
	public void setSched_ticket_price(double sched_ticket_price) {
		this.sched_ticket_price = sched_ticket_price;
	}
	
}
//CREATE TABLE `schedule` (
//		  `sched_id` int(11) NOT NULL AUTO_INCREMENT,
//		  `studio_id` int(11) DEFAULT NULL,
//		  `play_id` int(11) DEFAULT NULL,
//		  `sched_time` datetime NOT NULL,
//		  `sched_ticket_price` decimal(10,2) DEFAULT NULL,
//		  PRIMARY KEY (`sched_id`),
//		  KEY `FK_play_sched` (`play_id`),
//		  KEY `FK_studio_sched` (`studio_id`),
//		  CONSTRAINT `FK_play_sched` FOREIGN KEY (`play_id`) REFERENCES `play` (`play_id`),
//		  CONSTRAINT `FK_studio_sched` FOREIGN KEY (`studio_id`) REFERENCES `studio` (`studio_id`)
//		)
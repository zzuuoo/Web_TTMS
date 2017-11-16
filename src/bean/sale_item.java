package bean;

public class sale_item {
	private int sale_item_id;
	private int ticket_id;
	private int sale_ID;
	private double sale_item_price;
	public int getSale_item_id() {
		return sale_item_id;
	}
	public void setSale_item_id(int sale_item_id) {
		this.sale_item_id = sale_item_id;
	}
	public int getTicket_id() {
		return ticket_id;
	}
	public void setTicket_id(int ticket_id) {
		this.ticket_id = ticket_id;
	}
	public int getSale_ID() {
		return sale_ID;
	}
	public void setSale_ID(int sale_ID) {
		this.sale_ID = sale_ID;
	}
	public double getSale_item_price() {
		return sale_item_price;
	}
	public void setSale_item_price(double sale_item_price) {
		this.sale_item_price = sale_item_price;
	}
	
}
//CREATE TABLE `sale_item` (
//		  `sale_item_id` bigint(20) NOT NULL AUTO_INCREMENT,
//		  `ticket_id` bigint(20) DEFAULT NULL,
//		  `sale_ID` bigint(20) DEFAULT NULL,
//		  `sale_item_price` decimal(10,2) DEFAULT NULL,
//		  PRIMARY KEY (`sale_item_id`),
//		  KEY `FK_sale_sale_item` (`sale_ID`),
//		  KEY `FK_ticket_sale_item` (`ticket_id`),
//		  CONSTRAINT `FK_sale_sale_item` FOREIGN KEY (`sale_ID`) REFERENCES `sale` (`sale_ID`),
//		  CONSTRAINT `FK_ticket_sale_item` FOREIGN KEY (`ticket_id`) REFERENCES `ticket` (`ticket_id`)
//		)
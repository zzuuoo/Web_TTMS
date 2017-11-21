/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : ttms

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2017-08-18 19:24:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for data_dict
-- ----------------------------
DROP TABLE IF EXISTS `data_dict`;
CREATE TABLE `data_dict` (
  `dict_id` int(11) NOT NULL AUTO_INCREMENT,
  `dict_parent_id` int(11) DEFAULT NULL,
  `dict_index` int(11) DEFAULT NULL,
  `dict_name` varchar(200) DEFAULT NULL,
  `dict_value` varchar(100) NOT NULL,
  PRIMARY KEY (`dict_id`),
  KEY `FK_super_child_dict` (`dict_parent_id`),
  CONSTRAINT `FK_super_child_dict` FOREIGN KEY (`dict_parent_id`) REFERENCES `data_dict` (`dict_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='数据字典(影片类型、锁定时长、语言)';

-- ----------------------------
-- Records of data_dict
-- ----------------------------
INSERT INTO `data_dict` VALUES ('1', null, '1', '数据字典', '根');
INSERT INTO `data_dict` VALUES ('2', '1', '2', 'PLAYTYPE', '影片类型');
INSERT INTO `data_dict` VALUES ('3', '1', '3', 'PLAYLANG', '影片语言');
INSERT INTO `data_dict` VALUES ('4', '1', '4', '锁失效时间', '5');
INSERT INTO `data_dict` VALUES ('5', '2', '1', 'Costume Drama', '古装剧');
INSERT INTO `data_dict` VALUES ('6', '2', '2', 'Anime Piece', '动漫片');
INSERT INTO `data_dict` VALUES ('7', '2', '3', 'Life Drama', '生活剧');
INSERT INTO `data_dict` VALUES ('8', '2', '4', 'Horror Movies', '恐怖片');
INSERT INTO `data_dict` VALUES ('9', '2', '5', 'War Movies', '战争片');
INSERT INTO `data_dict` VALUES ('10', '2', '6', 'Science Fiction', '科幻片');
INSERT INTO `data_dict` VALUES ('11', '3', '1', 'Chinese', '国语');
INSERT INTO `data_dict` VALUES ('12', '3', '2', 'English', '英语');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `emp_id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_no` varchar(20) NOT NULL,
  `emp_name` varchar(100) NOT NULL,
  `emp_tel_num` varchar(20) DEFAULT NULL,
  `emp_addr` varchar(200) DEFAULT NULL,
  `emp_email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`emp_id`),
  UNIQUE KEY `emp_no` (`emp_no`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='员工表';

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', '000001', '张三', '13311111111', '长安南路563号', '111@163.com');
INSERT INTO `employee` VALUES ('2', '000002', '李四', '13322222222', '莲湖路22号', '222@21cn.com');
INSERT INTO `employee` VALUES ('3', '000003', '王五', '13333333333', '东五路33号', '333@yeah.net');
INSERT INTO `employee` VALUES ('4', '000004', '刘六', '13344444444', '西一路44号', '444@sina.com.cn');
INSERT INTO `employee` VALUES ('5', '000005', '郑七', '13355555555', '南稍门55号', '555@tom.com');
INSERT INTO `employee` VALUES ('6', '000006', '张三丰', '13366666666', '大溪地66号', '666@xiyou.edu.cn');
INSERT INTO `employee` VALUES ('7', 'test01', '小赵', '13312345678', '长安南路563号', 'xiaozhao@163.com');
INSERT INTO `employee` VALUES ('8', 'test02', '小王', '13312345678', '五路口23号', 'xiaowang@yeah.net');
INSERT INTO `employee` VALUES ('9', 'test03', '小田', '15712345678', '北大街123号', 'xiaotian@sina.com.cn');
INSERT INTO `employee` VALUES ('10', 'test04', '小胡', '17812345678', '吉祥村65号', 'xiaohu@tom.com');
INSERT INTO `employee` VALUES ('11', 'test05', '小钱', '15612345678', '西斜七路90号', 'xiaoqian@yahoo.com.cn');
INSERT INTO `employee` VALUES ('12', 'guest01', '老赵', '13312345678', '长安南路001号', 'laozhao@163.com');
INSERT INTO `employee` VALUES ('13', 'guest02', '老王', '13312345678', '五路口002号', 'laowang@yeah.net');
INSERT INTO `employee` VALUES ('14', 'guest03', '老田', '15712345678', '北大街003号', 'laotian@sina.com.cn');
INSERT INTO `employee` VALUES ('15', 'guest04', '老胡', '17812345678', '吉祥村004号', 'laohu@tom.com');
INSERT INTO `employee` VALUES ('16', 'guest05', '老钱', '15612345678', '西斜七路005号', 'laoqian@yahoo.com.cn');
INSERT INTO `employee` VALUES ('17', 'guest06', '老章', '18710905922', '西长安街121号', 'laozhang@sohu.com');
INSERT INTO `employee` VALUES ('18', 'guest07', '老周', '17812345678', '书香路201号', 'laozhou@21cn.com');
INSERT INTO `employee` VALUES ('19', 'guest08', '老刘', '18912345678', '子午大道30号', 'laoliu@126.com.cn');
INSERT INTO `employee` VALUES ('20', '000007', '张欣瑶', '13377777777', '长安南路77号', 'zxy@163.com');

-- ----------------------------
-- Table structure for play
-- ----------------------------
DROP TABLE IF EXISTS `play`;
CREATE TABLE `play` (
  `play_id` int(11) NOT NULL AUTO_INCREMENT,
  `play_type_id` int(11) DEFAULT NULL,
  `play_lang_id` int(11) DEFAULT NULL,
  `play_name` varchar(200) DEFAULT NULL,
  `play_introduction` varchar(2000) DEFAULT NULL,
  `play_image` varchar(1000) DEFAULT NULL,
  `play_length` int(11) DEFAULT NULL,
  `play_ticket_price` decimal(10,2) DEFAULT NULL,
  `play_status` smallint(6) DEFAULT NULL COMMENT '取值含义：\r\n            0：待安排演出\r\n            1：已安排演出\r\n            -1：下线',
  PRIMARY KEY (`play_id`),
  KEY `FK_dict_lan_play` (`play_lang_id`),
  KEY `FK_dict_type_play` (`play_type_id`),
  CONSTRAINT `FK_dict_lan_play` FOREIGN KEY (`play_lang_id`) REFERENCES `data_dict` (`dict_id`),
  CONSTRAINT `FK_dict_type_play` FOREIGN KEY (`play_type_id`) REFERENCES `data_dict` (`dict_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='影片信息表';

-- ----------------------------
-- Records of play
-- ----------------------------
INSERT INTO `play` VALUES ('1', '9', '11', '战狼2', '被开除军籍的冷锋本是因找寻龙小云来到非洲，但是却突然被卷入一场非洲国家的叛乱。因为国家之间政治立场的关系，中国军队无法在非洲实行武装行动撤离华侨。而作为退伍老兵的冷锋无法忘记曾经为军人的使命，本来可以安全撤离的他毅然决然地回到了沦陷区，孤身一人带领身陷屠杀中的同胞和难民，展开生死逃亡。随着斗争的持续，体内的狼性逐渐复苏，最终闯入战乱区域，为同胞而战斗。', null, '120', '35.00', '0');
INSERT INTO `play` VALUES ('2', '8', '11', '侠盗联盟', '江洋大盗张丹（刘德华 饰）在一次行动中遭遇黑吃黑，失手被擒入狱。三年后，出狱的张丹会合老搭档小宝（杨祐宁 饰），与新入伙的叶红（舒淇 饰）联手，在追捕自己多年的法国警探皮埃尔（让·雷诺 饰）眼皮底下盗窃得手。小试身手之后，张丹找到宝物的买家：与自己情同父子的犯罪组织头目金刚（曾志伟 饰），二人重逢之后，张丹接到新的任务。与此同时，为将张丹一伙人捉拿归案，皮埃尔说服对张丹因爱生恨的前女友Amber（张静初 饰）加入追捕行动。从戛纳到布拉格，跨越欧洲大陆的猫鼠游戏正激烈上演。几次短兵相接后，张丹的行踪逐渐被皮埃尔掌握。而随着一行人接近终极目标，张丹的真实计划也逐渐浮出水面', null, '108', '30.00', '0');
INSERT INTO `play` VALUES ('3', '9', '12', '敦刻尔克', '1940年5月25日至6月4日之间，在德国机械化部队的快速攻势下崩溃的英法联军，在位于法国东北部、靠近比利时边境的港口小城敦刻尔克，依靠英国海军和民众自发组织的“无敌舰队”，进行了史上最大规模的军事疏散行动，成功挽救了超过33万8000名来自英国、法国、比利时军人的生命。', null, '125', '33.00', '0');

-- ----------------------------
-- Table structure for sale
-- ----------------------------
DROP TABLE IF EXISTS `sale`;
CREATE TABLE `sale` (
  `sale_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `emp_id` int(11) DEFAULT NULL,
  `sale_time` datetime DEFAULT NULL,
  `sale_payment` decimal(10,2) DEFAULT NULL,
  `sale_change` decimal(10,2) DEFAULT NULL,
  `sale_type` smallint(6) DEFAULT NULL COMMENT '类别取值含义：\r\n            1：销售单\r\n            -1：退款单',
  `sale_status` smallint(6) DEFAULT NULL COMMENT '销售单状态如下：\r\n            0：代付款\r\n            1：已付款',
  PRIMARY KEY (`sale_ID`),
  KEY `FK_employee_sale` (`emp_id`),
  CONSTRAINT `FK_employee_sale` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`emp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='销售表';

-- ----------------------------
-- Records of sale
-- ----------------------------
INSERT INTO `sale` VALUES ('1', '1', '2017-08-10 11:14:43', '35.00', '0.00', '1', '1');
INSERT INTO `sale` VALUES ('2', '2', '2017-08-11 11:19:19', '35.00', '0.00', '1', '1');
INSERT INTO `sale` VALUES ('3', '3', '2017-08-11 11:28:37', '30.00', '0.00', '1', '1');
INSERT INTO `sale` VALUES ('4', '1', '2017-08-11 12:07:46', '35.00', '0.00', '1', '1');

-- ----------------------------
-- Table structure for sale_item
-- ----------------------------
DROP TABLE IF EXISTS `sale_item`;
CREATE TABLE `sale_item` (
  `sale_item_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ticket_id` bigint(20) DEFAULT NULL,
  `sale_ID` bigint(20) DEFAULT NULL,
  `sale_item_price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`sale_item_id`),
  KEY `FK_sale_sale_item` (`sale_ID`),
  KEY `FK_ticket_sale_item` (`ticket_id`),
  CONSTRAINT `FK_sale_sale_item` FOREIGN KEY (`sale_ID`) REFERENCES `sale` (`sale_ID`),
  CONSTRAINT `FK_ticket_sale_item` FOREIGN KEY (`ticket_id`) REFERENCES `ticket` (`ticket_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='销售明细表';

-- ----------------------------
-- Records of sale_item
-- ----------------------------
INSERT INTO `sale_item` VALUES ('1', '1', '1', '35.00');
INSERT INTO `sale_item` VALUES ('2', '1', '2', '35.00');
INSERT INTO `sale_item` VALUES ('3', '3', '3', '30.00');
INSERT INTO `sale_item` VALUES ('4', '4', '4', '35.00');

-- ----------------------------
-- Table structure for schedule
-- ----------------------------
DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule` (
  `sched_id` int(11) NOT NULL AUTO_INCREMENT,
  `studio_id` int(11) DEFAULT NULL,
  `play_id` int(11) DEFAULT NULL,
  `sched_time` datetime NOT NULL,
  `sched_ticket_price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`sched_id`),
  KEY `FK_play_sched` (`play_id`),
  KEY `FK_studio_sched` (`studio_id`),
  CONSTRAINT `FK_play_sched` FOREIGN KEY (`play_id`) REFERENCES `play` (`play_id`),
  CONSTRAINT `FK_studio_sched` FOREIGN KEY (`studio_id`) REFERENCES `studio` (`studio_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='排片表';

-- ----------------------------
-- Records of schedule
-- ----------------------------
INSERT INTO `schedule` VALUES ('1', '1', '1', '2017-08-11 08:00:00', '35.00');
INSERT INTO `schedule` VALUES ('2', '1', '1', '2017-08-11 10:30:00', '30.00');
INSERT INTO `schedule` VALUES ('3', '1', '2', '2017-08-11 15:00:00', '33.00');
INSERT INTO `schedule` VALUES ('4', '2', '3', '2017-08-11 08:00:00', '25.00');
INSERT INTO `schedule` VALUES ('5', '2', '2', '2017-08-11 10:30:00', '30.00');

-- ----------------------------
-- Table structure for seat
-- ----------------------------
DROP TABLE IF EXISTS `seat`;
CREATE TABLE `seat` (
  `seat_id` int(11) NOT NULL AUTO_INCREMENT,
  `studio_id` int(11) DEFAULT NULL,
  `seat_row` int(11) DEFAULT NULL,
  `seat_column` int(11) DEFAULT NULL,
  `seat_status` smallint(6) NOT NULL DEFAULT '1' COMMENT '取值含义：\r\n                       0：此处是空位，没有安排座椅\r\n                       1：完好的座位，可以使用\r\n                       -1：座位损坏，不能安排座位',
  PRIMARY KEY (`seat_id`),
  KEY `FK_studio_seat` (`studio_id`),
  CONSTRAINT `FK_studio_seat` FOREIGN KEY (`studio_id`) REFERENCES `studio` (`studio_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COMMENT='座位表';

-- ----------------------------
-- Records of seat
-- ----------------------------
INSERT INTO `seat` VALUES ('1', '1', '1', '1', '1');
INSERT INTO `seat` VALUES ('2', '1', '1', '2', '1');
INSERT INTO `seat` VALUES ('3', '1', '1', '3', '1');
INSERT INTO `seat` VALUES ('4', '1', '2', '1', '1');
INSERT INTO `seat` VALUES ('5', '1', '2', '2', '1');
INSERT INTO `seat` VALUES ('6', '1', '2', '3', '1');
INSERT INTO `seat` VALUES ('7', '1', '3', '1', '1');
INSERT INTO `seat` VALUES ('8', '1', '3', '2', '1');
INSERT INTO `seat` VALUES ('9', '1', '3', '3', '1');
INSERT INTO `seat` VALUES ('10', '2', '1', '1', '1');
INSERT INTO `seat` VALUES ('11', '2', '1', '2', '1');
INSERT INTO `seat` VALUES ('12', '2', '1', '3', '1');
INSERT INTO `seat` VALUES ('13', '2', '1', '4', '1');
INSERT INTO `seat` VALUES ('14', '2', '1', '5', '1');
INSERT INTO `seat` VALUES ('15', '2', '2', '1', '1');
INSERT INTO `seat` VALUES ('16', '2', '2', '2', '1');
INSERT INTO `seat` VALUES ('17', '2', '2', '3', '1');
INSERT INTO `seat` VALUES ('18', '2', '2', '4', '1');
INSERT INTO `seat` VALUES ('19', '2', '2', '5', '1');
INSERT INTO `seat` VALUES ('20', '2', '3', '1', '1');
INSERT INTO `seat` VALUES ('21', '2', '3', '2', '1');
INSERT INTO `seat` VALUES ('22', '2', '3', '3', '1');
INSERT INTO `seat` VALUES ('23', '2', '3', '4', '1');
INSERT INTO `seat` VALUES ('24', '2', '3', '5', '1');
INSERT INTO `seat` VALUES ('25', '2', '4', '1', '1');
INSERT INTO `seat` VALUES ('26', '2', '4', '2', '1');
INSERT INTO `seat` VALUES ('27', '2', '4', '3', '1');
INSERT INTO `seat` VALUES ('28', '2', '4', '4', '1');
INSERT INTO `seat` VALUES ('29', '2', '4', '5', '1');
INSERT INTO `seat` VALUES ('30', '2', '5', '1', '1');
INSERT INTO `seat` VALUES ('31', '2', '5', '2', '1');
INSERT INTO `seat` VALUES ('32', '2', '5', '3', '1');
INSERT INTO `seat` VALUES ('33', '2', '5', '4', '1');
INSERT INTO `seat` VALUES ('34', '2', '5', '5', '1');

-- ----------------------------
-- Table structure for studio
-- ----------------------------
DROP TABLE IF EXISTS `studio`;
CREATE TABLE `studio` (
  `studio_id` int(11) NOT NULL AUTO_INCREMENT,
  `studio_name` varchar(100) NOT NULL,
  `studio_row_count` int(11) DEFAULT NULL,
  `studio_col_count` int(11) DEFAULT NULL,
  `studio_introduction` varchar(2000) DEFAULT NULL,
  `studio_flag` smallint(6) DEFAULT NULL COMMENT '取值含义：\r\n                        0：尚未生成座位，可以根据行列信息生成座位\r\n                        1：已经根据影厅的座位信息安排了座位，不能再安排座位；\r\n                        -1：影厅损坏或者废弃，不能使用',
  PRIMARY KEY (`studio_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='影厅表';

-- ----------------------------
-- Records of studio
-- ----------------------------
INSERT INTO `studio` VALUES ('1', '1号厅', '3', '3', '小厅', '1');
INSERT INTO `studio` VALUES ('2', '2号厅', '5', '5', '激光厅', '1');

-- ----------------------------
-- Table structure for ticket
-- ----------------------------
DROP TABLE IF EXISTS `ticket`;
CREATE TABLE `ticket` (
  `ticket_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `seat_id` int(11) DEFAULT NULL,
  `sched_id` int(11) DEFAULT NULL,
  `ticket_price` decimal(10,2) DEFAULT NULL,
  `ticket_status` smallint(6) DEFAULT NULL COMMENT '含义如下：\r\n            0：待销售\r\n            1：锁定\r\n            9：卖出',
  `ticket_locked_time` datetime DEFAULT NULL,
  PRIMARY KEY (`ticket_id`),
  KEY `FK_sched_ticket` (`sched_id`),
  KEY `FK_seat_ticket` (`seat_id`),
  CONSTRAINT `FK_sched_ticket` FOREIGN KEY (`sched_id`) REFERENCES `schedule` (`sched_id`),
  CONSTRAINT `FK_seat_ticket` FOREIGN KEY (`seat_id`) REFERENCES `seat` (`seat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='电影票表';

-- ----------------------------
-- Records of ticket
-- ----------------------------
INSERT INTO `ticket` VALUES ('1', '1', '1', '35.00', '9', null);
INSERT INTO `ticket` VALUES ('2', '2', '1', '35.00', '9', null);
INSERT INTO `ticket` VALUES ('3', '10', '4', '30.00', '9', null);
INSERT INTO `ticket` VALUES ('4', '5', '3', '35.00', '9', null);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `emp_no` varchar(20) NOT NULL COMMENT '用户名',
  `emp_pass` varchar(20) NOT NULL DEFAULT '123456' COMMENT '密码',
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '用户类型：0为普通用户，1是管理员',
  `head_path` varchar(500) DEFAULT NULL COMMENT '头像路径',
  PRIMARY KEY (`emp_no`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`emp_no`) REFERENCES `employee` (`emp_no`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登陆信息表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('000001', '123456', '1', '');
INSERT INTO `user` VALUES ('000002', '123456', '0', '');
INSERT INTO `user` VALUES ('000003', '123456', '0', '');
INSERT INTO `user` VALUES ('000004', '123456', '0', '');
INSERT INTO `user` VALUES ('000005', '123456', '0', '');
INSERT INTO `user` VALUES ('000006', '123456', '0', '');
INSERT INTO `user` VALUES ('000007', '123456', '0', '');
INSERT INTO `user` VALUES ('test01', '123456', '0', '');

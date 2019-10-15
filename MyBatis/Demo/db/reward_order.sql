/*
SQLyog Professional v12.08 (64 bit)
MySQL - 5.7.18-20170830-log : Database - reward
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`reward` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `reward`;

/*Table structure for table `reward_order` */

DROP TABLE IF EXISTS `reward_order`;

CREATE TABLE `reward_order` (
  `reward_id` varchar(36) NOT NULL COMMENT '悬赏主键',
  `reward_name` varchar(36) DEFAULT NULL COMMENT '悬赏名称',
  `reward_content` varchar(4000) DEFAULT NULL COMMENT '悬赏详情',
  `reward_stage` int(1) DEFAULT NULL COMMENT '任务状态：0草稿，1已发布，2已被接，3已完成，4未完成，5已取消，6已删除',
  `create_user` int(5) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` int(5) DEFAULT NULL COMMENT '修改者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`reward_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `reward_order` */

insert  into `reward_order`(`reward_id`,`reward_name`,`reward_content`,`reward_stage`,`create_user`,`create_time`,`update_user`,`update_time`) values ('1','送快递','谁来给送挂',1,10001,'2019-04-09 22:34:33',10001,'2019-04-08 22:34:37'),('cc161f3e-6052-11e9-b5e2-525400c6cf95','来此','尽快赶到此地，有重大发现！！！',NULL,NULL,NULL,NULL,NULL),('db964b1f-6052-11e9-b5e2-525400c6cf95','来此','尽快赶到此地，有重大发现！！！',NULL,NULL,NULL,NULL,NULL),('deaab4bf-5ade-11e9-b5e2-525400c6cf95','打饭','快去给我打饭吧，饿了！！！',NULL,NULL,NULL,NULL,NULL),('e6ba0d68-5add-11e9-b5e2-525400c6cf95','打饭','快去给我打饭吧，饿了！！！',NULL,NULL,NULL,NULL,NULL),('XXX123','打饭','快去给我打饭吧，饿了！！！',NULL,NULL,NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

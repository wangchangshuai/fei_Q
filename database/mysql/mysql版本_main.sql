/*
MySQL Backup
Source Server Version: 5.5.18
Source Database: main
Date: 2012-1-5 16:33:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
--  Table structure for `canUse`
-- ----------------------------
DROP TABLE IF EXISTS `canUse`;
CREATE TABLE `canUse` (
  `qq` varchar(7) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `mailAdress`
-- ----------------------------
DROP TABLE IF EXISTS `mailAdress`;
CREATE TABLE `mailAdress` (
  `qq` varchar(7) NOT NULL DEFAULT '',
  `adress` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`qq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `mainInfo`
-- ----------------------------
DROP TABLE IF EXISTS `mainInfo`;
CREATE TABLE `mainInfo` (
  `qq` varchar(7) NOT NULL,
  `nickname` varchar(20) DEFAULT NULL,
  `sex` varchar(6) DEFAULT NULL,
  `age` int(11) DEFAULT '0',
  `birthday_year` int(11) DEFAULT NULL,
  `birthday_month` int(11) DEFAULT NULL,
  `birthday_day` int(11) DEFAULT NULL,
  `animal` varchar(4) DEFAULT NULL,
  `constellation` varchar(10) DEFAULT NULL,
  `bloodType` varchar(5) DEFAULT NULL,
  `country` varchar(30) DEFAULT NULL,
  `province` varchar(20) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `headImage` int(11) DEFAULT '0',
  `status` int(11) DEFAULT '0',
  `signature` varchar(100) DEFAULT NULL,
  `phoneNumber` varchar(11) DEFAULT NULL,
  `collage` varchar(30) DEFAULT NULL,
  `personalInfo` varchar(200) DEFAULT NULL,
  `language` varchar(10) DEFAULT NULL,
  `occupation` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`qq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `password`
-- ----------------------------
DROP TABLE IF EXISTS `password`;
CREATE TABLE `password` (
  `qq` varchar(7) NOT NULL,
  `password` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`qq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `qqIp`
-- ----------------------------
DROP TABLE IF EXISTS `qqIp`;
CREATE TABLE `qqIp` (
  `qq` varchar(7) NOT NULL,
  `ip` varchar(15) DEFAULT NULL,
  `date` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`qq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `systemMessage`
-- ----------------------------
DROP TABLE IF EXISTS `systemMessage`;
CREATE TABLE `systemMessage` (
  `qq` varchar(7) NOT NULL DEFAULT '',
  `warning` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`qq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `whetherCanAdd`
-- ----------------------------
DROP TABLE IF EXISTS `whetherCanAdd`;
CREATE TABLE `whetherCanAdd` (
  `qq` varchar(7) NOT NULL DEFAULT '',
  `can` int(11) DEFAULT NULL,
  PRIMARY KEY (`qq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


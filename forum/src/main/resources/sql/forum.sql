/*
Navicat MySQL Data Transfer

Source Server         : aliyun
Source Server Version : 50644
Source Host           : 39.105.136.112:3306
Source Database       : forum

Target Server Type    : MYSQL
Target Server Version : 50644
File Encoding         : 65001

Date: 2019-11-23 21:02:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for admin_opretor
-- ----------------------------
DROP TABLE IF EXISTS `admin_opretor`;
CREATE TABLE `admin_opretor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) NOT NULL,
  `opretor` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_admin_id` (`admin_id`),
  CONSTRAINT `fk_admin_id` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(32) NOT NULL,
  `author` bigint(20) NOT NULL,
  `created` datetime NOT NULL,
  `content` text NOT NULL,
  `type` int(5) NOT NULL COMMENT '1.数模文 2.技术文',
  `click` bigint(20) NOT NULL DEFAULT '0',
  `comment_count` int(10) NOT NULL DEFAULT '0',
  `alike` int(10) NOT NULL DEFAULT '0',
  `state` int(5) NOT NULL DEFAULT '1' COMMENT '1.审核通过 0.在审核 -1.审核不通过 2.回收站',
  `sort` double(20,0) NOT NULL DEFAULT '0',
  `state_date` datetime DEFAULT NULL COMMENT '更改状态的日期',
  `images` varchar(255) DEFAULT NULL,
  `istop` tinyint(1) NOT NULL DEFAULT '0' COMMENT '1.置顶 0.不置顶',
  PRIMARY KEY (`id`),
  KEY `index_created` (`created`) USING BTREE,
  KEY `fk_author` (`author`),
  KEY `index_type` (`type`) USING BTREE,
  KEY `index_title` (`title`),
  CONSTRAINT `fk_author` FOREIGN KEY (`author`) REFERENCES `user_detail` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=240 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for article_rank
-- ----------------------------
DROP TABLE IF EXISTS `article_rank`;
CREATE TABLE `article_rank` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `art_id` bigint(20) NOT NULL,
  `rank_type` int(1) NOT NULL COMMENT '1.日榜 2.周榜 3.月榜',
  `rank_sort` int(5) NOT NULL COMMENT '优先顺序（10以内，越大越靠前）',
  PRIMARY KEY (`id`),
  KEY `index_type` (`rank_type`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `art_id` bigint(20) NOT NULL,
  `com_content` varchar(500) NOT NULL,
  `com_create` datetime NOT NULL,
  `com_author` bigint(20) NOT NULL,
  `com_like` int(10) NOT NULL DEFAULT '0',
  `com_author_name` varchar(32) NOT NULL,
  `rep_count` int(10) NOT NULL DEFAULT '0',
  `com_state` int(5) NOT NULL DEFAULT '1' COMMENT '1审核通过 0待审核 -1不通过',
  PRIMARY KEY (`id`),
  KEY `index_artid` (`art_id`),
  CONSTRAINT `fk_art` FOREIGN KEY (`art_id`) REFERENCES `article` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for function
-- ----------------------------
DROP TABLE IF EXISTS `function`;
CREATE TABLE `function` (
  `fun_id` int(10) NOT NULL AUTO_INCREMENT,
  `fun_name` varchar(32) NOT NULL,
  `fun_state` int(5) NOT NULL DEFAULT '1',
  `fun_comment` varchar(255) NOT NULL,
  PRIMARY KEY (`fun_id`),
  KEY `index_fun_name` (`fun_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
  `rep_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment_id` bigint(20) NOT NULL,
  `rep_content` varchar(500) NOT NULL,
  `rep_create` datetime NOT NULL,
  `rep_author` bigint(20) NOT NULL,
  `rep_like` int(10) NOT NULL DEFAULT '0',
  `rep_author_name` varchar(32) NOT NULL,
  `rep_state` int(5) NOT NULL DEFAULT '1' COMMENT '1通过 0审核 -1不通过',
  `rep_type` int(5) NOT NULL DEFAULT '1' COMMENT '1.对评论回复 2.对回复进行回复',
  `to_rep_author` bigint(255) DEFAULT NULL COMMENT '若对回复进行回复时 对象的id',
  `to_rep_author_name` varchar(255) DEFAULT NULL COMMENT '若对回复进行回复时 对象的name',
  PRIMARY KEY (`rep_id`),
  KEY `index_comid` (`comment_id`),
  CONSTRAINT `fk_com` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for source
-- ----------------------------
DROP TABLE IF EXISTS `source`;
CREATE TABLE `source` (
  `source_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `source_title` varchar(255) NOT NULL,
  `source_create` datetime NOT NULL,
  `source_click` int(20) NOT NULL DEFAULT '0',
  `pay` tinyint(1) NOT NULL DEFAULT '0' COMMENT '1付费 2免费',
  `price` bigint(20) NOT NULL DEFAULT '0' COMMENT '价格 单位分',
  `author` bigint(20) NOT NULL,
  `author_name` varchar(255) NOT NULL,
  PRIMARY KEY (`source_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for source_detail
-- ----------------------------
DROP TABLE IF EXISTS `source_detail`;
CREATE TABLE `source_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `source_id` bigint(20) NOT NULL,
  `baiduyun` varchar(255) NOT NULL,
  `code` varchar(10) NOT NULL,
  `github` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_source_id` (`source_id`),
  CONSTRAINT `fk_source_id` FOREIGN KEY (`source_id`) REFERENCES `source` (`source_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_detail
-- ----------------------------
DROP TABLE IF EXISTS `user_detail`;
CREATE TABLE `user_detail` (
  `user_id` bigint(20) NOT NULL,
  `username` varchar(32) DEFAULT NULL,
  `realname` varchar(32) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `birth` datetime DEFAULT NULL,
  `college` varchar(32) DEFAULT NULL,
  `profession` varchar(32) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  `hobby` varchar(32) DEFAULT NULL,
  `head` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `uid` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `phone` varchar(11) NOT NULL,
  `password` varchar(32) NOT NULL,
  `created` datetime NOT NULL,
  `salt` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for website
-- ----------------------------
DROP TABLE IF EXISTS `website`;
CREATE TABLE `website` (
  `web_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `web_pv` int(32) NOT NULL DEFAULT '0',
  `web_uv` int(32) NOT NULL DEFAULT '0',
  `web_ip` int(32) NOT NULL DEFAULT '0',
  `schedule` datetime NOT NULL,
  `web_sm` int(20) NOT NULL DEFAULT '0',
  `web_js` int(20) NOT NULL DEFAULT '0',
  `web_info` int(20) NOT NULL DEFAULT '0',
  `web_login` int(20) NOT NULL DEFAULT '0',
  `web_register` int(20) NOT NULL DEFAULT '0',
  `web_source` int(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`web_id`),
  KEY `index_schedule` (`schedule`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for website_detail
-- ----------------------------
DROP TABLE IF EXISTS `website_detail`;
CREATE TABLE `website_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `schedule` datetime NOT NULL,
  `pv` varchar(255) NOT NULL DEFAULT '',
  `uv` varchar(255) NOT NULL,
  `ip` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_schedule` (`schedule`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

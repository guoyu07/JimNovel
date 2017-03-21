/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50712
 Source Host           : localhost
 Source Database       : JimNovel

 Target Server Type    : MySQL
 Target Server Version : 50712
 File Encoding         : utf-8

 Date: 03/22/2017 01:46:21 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `password` varchar(200) NOT NULL,
  `admin_id` int(11) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `admin_folder`
-- ----------------------------
DROP TABLE IF EXISTS `admin_folder`;
CREATE TABLE `admin_folder` (
  `admin_id` int(20) NOT NULL,
  `folder_id` int(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `article`
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `article_id` int(20) NOT NULL,
  `folder_id` int(20) NOT NULL,
  `path` varchar(200) NOT NULL,
  `owner_id` int(11) NOT NULL COMMENT '文章小说所有者id',
  `img_url` varchar(200) DEFAULT NULL COMMENT '所旅途',
  `title` varchar(20) DEFAULT NULL,
  `keyword` varchar(100) DEFAULT NULL,
  `view_count` int(10) DEFAULT NULL COMMENT '浏览数 热门判断依据',
  `status` tinyint(4) DEFAULT '0' COMMENT '-1:审核失败 0:未审核  1：审核通过 ',
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `article`
-- ----------------------------
BEGIN;
INSERT INTO `article` VALUES ('1', '1', '1', '10001', '/upload/slide1.jpg', '小说一', '我们的大脑只不过是用来挂记忆这件衣服的钉子而已，必须要有钉子挂住它，但衣服却不在钉子里。', '11', '1', '2017-03-19 22:49:18', '2017-03-19 22:49:22'), ('2', '4', '1', '10002', '/upload/slide2.jpg', '小说二', '恩怨纠葛如浮云过,她遗憾没在最好的年华里遇上他。', '19', '1', '2017-03-20 00:44:38', '2017-03-20 00:44:43'), ('3', '3', '1', '10003', '/upload/slide3.jpg', '小说三', '因为她俩本是一人。', '111', '1', '2017-03-20 00:46:36', '2017-03-20 00:46:39'), ('4', '2', '1', '10004', '/upload/slide4.jpg', '小说四', '三生三世，须臾几百年的爱恋，或许是天地混沌时就结下的缘。天地之下，', '55', '1', '2017-03-20 00:47:44', '2017-03-20 00:47:47');
COMMIT;

-- ----------------------------
--  Table structure for `chapter`
-- ----------------------------
DROP TABLE IF EXISTS `chapter`;
CREATE TABLE `chapter` (
  `chapter_id` int(20) NOT NULL,
  `article_id` int(20) NOT NULL,
  `chapter_title` varchar(200) NOT NULL,
  `content` mediumtext,
  `comment_count` int(11) DEFAULT NULL COMMENT '评论数量',
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`chapter_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `commoent_id` int(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(20) NOT NULL,
  `father_id` int(20) DEFAULT NULL COMMENT '父评论',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '-1:审核拒绝 0:待审核 1：审核通过',
  `content` text NOT NULL,
  `ip` varchar(45) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`commoent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `comment`
-- ----------------------------
BEGIN;
INSERT INTO `comment` VALUES ('1', '10001', '1', '1', '这是一条评论', '120.76.223.75', '2017-03-19 22:50:33', '2017-03-19 22:50:35');
COMMIT;

-- ----------------------------
--  Table structure for `config`
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config` (
  `keymap` varchar(45) NOT NULL,
  `value` varchar(45) NOT NULL,
  `description` text,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`keymap`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `config`
-- ----------------------------
BEGIN;
INSERT INTO `config` VALUES ('novel_template', 'blog', '模板', '2017-03-19 21:57:19', '2017-03-19 21:57:21');
COMMIT;

-- ----------------------------
--  Table structure for `folder`
-- ----------------------------
DROP TABLE IF EXISTS `folder`;
CREATE TABLE `folder` (
  `folder_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '分类目录ID',
  `father_id` int(20) NOT NULL DEFAULT '0' COMMENT '父亲Id，用于构建目录树',
  `ename` varchar(45) NOT NULL COMMENT '英文名',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '中文名',
  `path` varchar(200) NOT NULL DEFAULT '' COMMENT '路径',
  `content` text,
  `level` tinyint(4) DEFAULT '1' COMMENT '层级',
  `sort` tinyint(4) DEFAULT '0' COMMENT '排序',
  `width` int(11) DEFAULT '0',
  `height` int(11) DEFAULT '0',
  `count` int(11) DEFAULT '0' COMMENT '文件数',
  `display` tinyint(4) DEFAULT '1' COMMENT '1:显示 0：隐藏',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`folder_id`),
  UNIQUE KEY `idx_ename` (`ename`) USING BTREE,
  KEY `idx_status` (`father_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='目录';

-- ----------------------------
--  Records of `folder`
-- ----------------------------
BEGIN;
INSERT INTO `folder` VALUES ('1', '0', 'xuanhuan', '玄幻', '1', '玄幻小说', '1', '1', '0', '0', '0', '1', '2017-03-19 19:55:51', '2017-03-20 10:05:35'), ('2', '0', 'wuxia', '武侠', '1', '武侠小说', '1', '2', '0', '0', '0', '1', '2017-03-19 22:45:16', '2017-03-20 10:05:37'), ('3', '1', 'chuanyue', '穿越', '1#3', '穿越小说', '2', '1', '0', '0', '0', '1', '2017-03-19 22:48:11', '2017-03-20 10:05:39'), ('4', '1', 'aiqing', '爱情', '1#4', '爱情小说', '1', '2', '0', '0', '0', '1', '2017-03-22 01:15:34', '2017-03-22 01:15:37');
COMMIT;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `type` int(2) NOT NULL DEFAULT '0' COMMENT '读者：0 作者：1',
  `password` varchar(200) NOT NULL,
  `open_id` varchar(200) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL,
  `avater_url` varchar(200) DEFAULT NULL COMMENT '头像路径',
  `email` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10005 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('10001', '管理员', '1', '123', '123', '2017-03-19 22:51:00', '2017-03-19 22:51:02', null, 'chenrunfa@xhuabu.com'), ('10002', '吴亦凡', '1', '123', '123', '2017-03-22 01:06:42', '2017-03-22 01:06:44', null, null), ('10003', '五杀', '1', '1213', '1231', '2017-03-22 01:07:20', '2017-03-22 01:07:22', null, null), ('10004', '陈坤', '1', '123', '123', '2017-03-22 01:07:01', '2017-03-22 01:07:03', null, null);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

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

 Date: 04/17/2017 23:52:31 PM
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `admin`
-- ----------------------------
BEGIN;
INSERT INTO `admin` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '10000', '2017-04-11 21:29:48', '2017-04-11 21:29:50');
COMMIT;

-- ----------------------------
--  Table structure for `article`
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `article_id` int(20) NOT NULL AUTO_INCREMENT,
  `folder_id` int(20) NOT NULL,
  `path` varchar(200) NOT NULL,
  `owner_id` int(11) NOT NULL COMMENT '文章小说所有者id',
  `small_img_url` varchar(200) DEFAULT NULL,
  `img_url` varchar(200) DEFAULT NULL COMMENT '所旅途',
  `title` varchar(20) DEFAULT NULL,
  `keyword` varchar(100) DEFAULT NULL,
  `view_count` int(10) DEFAULT NULL COMMENT '浏览数 热门判断依据',
  `status` tinyint(4) DEFAULT '0' COMMENT '-1:审核失败 0:未审核  1：审核通过 ',
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `article`
-- ----------------------------
BEGIN;
INSERT INTO `article` VALUES ('2', '4', '1#4', '10005', '/upload/dsl.jpg', '/upload/slide2.jpg', '爱情小说二', '恩怨纠葛如浮云过,她遗憾没在最好的年华里遇上他。', '19', '0', '2017-03-20 00:44:38', '2017-03-20 00:44:43'), ('3', '3', '1#3', '10005', '/upload/dpcq.jpg', '/upload/slide3.jpg', '穿越小说三', '因为她俩本是一人。', '111', '-1', '2017-03-20 00:46:36', '2017-03-20 00:46:39'), ('4', '2', '2', '10005', '/upload/clmdsl.jpg', '/upload/slide4.jpg', '武侠小说四', '三生三世，须臾几百年的爱恋，或许是天地混沌时就结下的缘。天地之下，', '55', '0', '2017-03-20 00:47:44', '2017-03-20 00:47:47'), ('7', '4', '1#4', '10005', '/upload/2017/04/16/d5d447732a0548d89b802e55081fffa4.jpg', '/upload/2017/04/16/42c5ccc538fd48f89f8ca23b6c938e99.jpg', 'sa', 'asd', '123', '1', '2017-04-16 23:58:38', '2017-04-16 23:58:38'), ('8', '3', '1#3', '10008', '/upload/2017/04/17/86f53826f2ef4d26aeab5281ed0b82b9.jpg', '/upload/2017/04/17/b53b2b2253bf4aab8c20bd847f949632.jpg', 'sasda', 'asdas', '0', '0', '2017-04-17 15:20:37', '2017-04-17 15:20:37'), ('9', '8', '1#4#8', '10008', '/upload/2017/04/17/33c2708017ad49a6a24c010c00d19769.jpg', '/upload/2017/04/17/27aed474dab24fc6a38682013cfdf93a.jpg', '是', '阿斯达', '0', '0', '2017-04-17 22:49:06', '2017-04-17 22:49:06');
COMMIT;

-- ----------------------------
--  Table structure for `chapter`
-- ----------------------------
DROP TABLE IF EXISTS `chapter`;
CREATE TABLE `chapter` (
  `chapter_id` int(20) NOT NULL AUTO_INCREMENT,
  `article_id` int(20) NOT NULL,
  `chapter_title` varchar(200) NOT NULL,
  `content` mediumtext,
  `comment_count` int(11) DEFAULT NULL COMMENT '评论数量',
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `sort` int(20) DEFAULT '0' COMMENT '章节顺序',
  PRIMARY KEY (`chapter_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `chapter`
-- ----------------------------
BEGIN;
INSERT INTO `chapter` VALUES ('1', '5', '第一章：大圣出世阿斯达', '第一章：大圣出世阿斯达', '1', '2017-03-25 23:18:19', '2017-04-16 23:16:08', null), ('2', '5', '第二章：东海拜师', '猴子到东海拜师傅', '23', '2017-03-26 01:08:36', '2017-03-26 01:08:38', '0'), ('3', '5', '第三章：噢噢噢噢', '哦', '11', '2017-03-26 12:54:28', '2017-03-26 12:54:31', '0'), ('4', '2', 'asda', 'asda', '0', '2017-04-16 00:00:00', '2017-04-17 22:57:34', '0'), ('5', '2', 'asasfasf送达方式', '&lt;p&gt;按时发顺丰大师傅&lt;img src=&quot;/upload/image/20170416/1492354689351046426.jpg&quot; title=&quot;1492354689351046426.jpg&quot; alt=&quot;p1.jpg&quot;/&gt;&lt;/p&gt;', '0', '2017-04-16 00:00:00', '2017-04-16 00:00:00', '1');
COMMIT;

-- ----------------------------
--  Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `commoent_id` int(20) NOT NULL AUTO_INCREMENT,
  `chapter_id` int(20) DEFAULT NULL,
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
INSERT INTO `comment` VALUES ('1', null, '10001', '1', '1', '这是一条评论', '120.76.223.75', '2017-03-19 22:50:33', '2017-03-19 22:50:35');
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
INSERT INTO `config` VALUES ('admin_template', 'admin', '管理后台模板', '2017-04-11 16:34:26', '2017-04-11 16:34:28'), ('novel_template', 'blog', '模板', '2017-03-19 21:57:19', '2017-03-19 21:57:21'), ('shishuo_seo_description', '专业的电子小说网站', '网站描述', '2017-03-24 01:08:15', '2017-03-24 01:08:16'), ('shishuo_seo_title', 'JimNovel在线小说网站', '网站名称', '2017-03-24 01:07:16', '2017-03-24 01:07:18'), ('shishuo_static', 'false', '是否启用全站静态化', '2017-03-24 00:29:20', '2017-03-24 00:29:23');
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
  `img_url` varchar(200) DEFAULT NULL COMMENT '小说类别图片',
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='目录';

-- ----------------------------
--  Records of `folder`
-- ----------------------------
BEGIN;
INSERT INTO `folder` VALUES ('1', '0', 'xuanhuan', '玄幻', '1', '/upload/spec4.jpg', '玄幻小说', '1', '1', '0', '0', '0', '1', '2017-03-19 19:55:51', '2017-03-20 10:05:35'), ('2', '0', 'wuxia', '武侠', '2', '/upload/ssssslth.jpg', '武侠小说', '1', '2', '0', '0', '0', '1', '2017-03-19 22:45:16', '2017-03-20 10:05:37'), ('3', '1', 'chuanyue', '穿越', '1#3', '/upload/spec2.jpg', '穿越小说', '2', '4', '0', '0', '0', '1', '2017-03-19 22:48:11', '2017-03-20 10:05:39'), ('4', '1', 'aiqing', '爱情', '1#4', '/upload/spec3.jpg', '爱情小说', '1', '7', '0', '0', '0', '1', '2017-03-22 01:15:34', '2017-03-22 01:15:37'), ('5', '0', 'donghua', '动画', '3', '/upload/spec1.jpg', '动漫', '1', '5', '0', '0', '1', '1', '2017-03-22 22:04:55', '2017-04-17 21:20:38'), ('9', '5', 'asfasfdasfdaasfda', 'sad', '3#9', '/upload/2017/04/17/c560766ad14147648652189531fd9562.jpg', '阿斯达啊是', '2', '1', '0', '0', '0', '1', '2017-04-17 22:44:17', '2017-04-17 22:44:17');
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
  `email` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10009 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('10001', '测试14', '1', '123123', '123', '2017-03-19 22:51:00', '2017-03-19 22:51:02', '/upload/1492102712769.jpg', 'chenrunfa@vip.qq.com2'), ('10002', '吴亦凡', '1', '123123', '123', '2017-03-22 01:06:42', '2017-03-22 01:06:44', null, null), ('10003', '五杀', '1', '123123', '1231', '2017-03-22 01:07:20', '2017-03-22 01:07:22', null, null), ('10004', '陈坤', '1', '123123', '123', '2017-03-22 01:07:01', '2017-03-22 01:07:03', null, null), ('10005', '未命名', '0', 'e10adc3949ba59abbe56e057f20f883e', '000', '2017-04-15 10:19:48', '2017-04-15 10:19:50', null, 'chenrunfa@vip.qq.com'), ('10006', '未命名', '0', 'e10adc3949ba59abbe56e057f20f883e', '123', '2017-03-29 01:08:24', '2017-03-29 01:08:24', '/upload/1492407346193.jpg', 'chenrunfa@vip.qq.com4'), ('10008', '用户30025', '0', '4297f44b13955235245b2497399d7a93', '0', '2017-04-17 14:15:44', '2017-04-17 14:15:44', null, 'chenrunfa@xhuabu.com');
COMMIT;

-- ----------------------------
--  Table structure for `user_collect`
-- ----------------------------
DROP TABLE IF EXISTS `user_collect`;
CREATE TABLE `user_collect` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(20) DEFAULT NULL,
  `flag` tinyint(1) DEFAULT '0' COMMENT '是否删除 1：删除  0：默认',
  `article_id` int(20) DEFAULT NULL COMMENT '用户书签',
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `user_collect`
-- ----------------------------
BEGIN;
INSERT INTO `user_collect` VALUES ('5', '10001', '0', '3', '2017-03-30 01:31:54', '2017-03-30 01:31:54'), ('6', '10001', '0', '1', '2017-03-30 01:33:58', '2017-03-30 01:33:58'), ('7', '10001', '0', '5', '2017-03-30 02:06:22', '2017-03-30 02:06:22');
COMMIT;

-- ----------------------------
--  Table structure for `user_folder`
-- ----------------------------
DROP TABLE IF EXISTS `user_folder`;
CREATE TABLE `user_folder` (
  `user_id` int(20) DEFAULT NULL,
  `folder_id` int(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `user_folder`
-- ----------------------------
BEGIN;
INSERT INTO `user_folder` VALUES ('10005', '1', '2017-04-15 15:05:51', '2017-04-15 15:05:53'), ('10005', '2', '2017-04-15 15:05:59', '2017-04-15 15:06:01'), ('10005', '3', '2017-04-15 15:06:09', '2017-04-15 15:06:10'), ('10008', '4', '2017-04-15 15:06:18', '2017-04-17 22:50:23'), ('10008', '5', '2017-04-15 15:06:28', '2017-04-17 22:50:28');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

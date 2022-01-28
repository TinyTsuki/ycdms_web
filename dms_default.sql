/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50726
Source Host           : localhost:23306
Source Database       : dms

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2021-12-01 16:14:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for attendances
-- ----------------------------
DROP TABLE IF EXISTS `attendances`;
CREATE TABLE `attendances` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eid` int(4) DEFAULT NULL COMMENT '活动编号',
  `uid` int(4) DEFAULT NULL COMMENT '会员编号',
  `status` int(1) DEFAULT '0' COMMENT '1请假 2出勤',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of attendances
-- ----------------------------

-- ----------------------------
-- Table structure for checks
-- ----------------------------
DROP TABLE IF EXISTS `checks`;
CREATE TABLE `checks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `token` varchar(32) NOT NULL,
  `uid` int(5) unsigned DEFAULT NULL,
  `creatime` int(13) unsigned NOT NULL,
  `usetime` int(13) unsigned DEFAULT NULL,
  `worker` int(3) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `token` (`token`),
  KEY `uid` (`uid`),
  KEY `worker` (`worker`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of checks
-- ----------------------------

-- ----------------------------
-- Table structure for cookies
-- ----------------------------
DROP TABLE IF EXISTS `cookies`;
CREATE TABLE `cookies` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key` varchar(255) DEFAULT NULL,
  `value` longtext,
  `time` bigint(13) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of cookies
-- ----------------------------

-- ----------------------------
-- Table structure for events
-- ----------------------------
DROP TABLE IF EXISTS `events`;
CREATE TABLE `events` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL COMMENT '活动日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of events
-- ----------------------------

-- ----------------------------
-- Table structure for members
-- ----------------------------
DROP TABLE IF EXISTS `members`;
CREATE TABLE `members` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` char(6) NOT NULL COMMENT '姓名',
  `cn` varchar(16) NOT NULL,
  `series` varchar(16) NOT NULL COMMENT '系别',
  `major` varchar(16) NOT NULL COMMENT '专业',
  `classe` int(3) unsigned NOT NULL COMMENT '班级',
  `phone` bigint(11) NOT NULL COMMENT '手机号',
  `qq` bigint(10) NOT NULL COMMENT 'QQ号',
  `tung` int(3) unsigned NOT NULL COMMENT '寝室栋数',
  `room` int(5) unsigned NOT NULL COMMENT '寝室房间号',
  `photo` int(3) unsigned DEFAULT NULL COMMENT '是否已交照片',
  `qrid` int(10) unsigned NOT NULL COMMENT '二维码ID',
  `department` tinyint(4) NOT NULL COMMENT '1活动 2技术 3秘书 4宣传 5财务',
  `grade` smallint(5) unsigned NOT NULL COMMENT '年级',
  `sex` tinyint(3) unsigned NOT NULL COMMENT '性别',
  `nation` varchar(8) NOT NULL COMMENT '民族',
  `politics` tinyint(4) NOT NULL COMMENT '政治面貌',
  `status` tinyint(1) DEFAULT '1' COMMENT '删除状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `qq` (`qq`) USING BTREE,
  UNIQUE KEY `qrid` (`qrid`) USING BTREE,
  KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of members
-- ----------------------------

-- ----------------------------
-- Table structure for options
-- ----------------------------
DROP TABLE IF EXISTS `options`;
CREATE TABLE `options` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key` varchar(255) DEFAULT NULL,
  `value` longtext,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of options
-- ----------------------------
INSERT INTO `options` VALUES ('1', 'clubTitle', '萤川动漫社');
INSERT INTO `options` VALUES ('2', 'groupUrl', '');
INSERT INTO `options` VALUES ('3', 'groupNo', '');
INSERT INTO `options` VALUES ('4', 'adminInfo1', '马以QQ：');
INSERT INTO `options` VALUES ('5', 'regTips', '1. 请确保QQ号正确，后面所有信息均可以通过QQ机器人修改，所以QQ号是最重要的。<br>\r\n2. 此页面一次提交有效，提交一次后该页面将无法二次打开，注册期间请尽量不要离开网页。<br>\r\n3. 注册完成后请加入新生群 <a target=\"_blank\" href=\"\">223861687</a>并添加机器人QQ 为好友。<br>\r\n4. 注册完成后可以通过机器人查询信息、修改资料，也请在资料变动的时候第一时间修改（如换寝室、换专业、分班）<br>\r\n5. CN指圈名（CoserName），（如：银月/马以），如果没有可以考虑使用网名或QQ昵称或者现想一个<br>\r\n☆ 学院要求，一个人最多能加两个社团 ☆');
INSERT INTO `options` VALUES ('6', 'departmentInfo1', '活动部：主要负责举办和参与活动，同时如果你是coser或者妆娘建议优先考虑加入此部门。');
INSERT INTO `options` VALUES ('7', 'departmentInfo2', '技术部：负责社团图片/视频等制作以及绘画，有PS/AE/PR/绘画基础建议优先考虑加入。');
INSERT INTO `options` VALUES ('8', 'departmentInfo3', '秘书部：负责社团会员信息管理，出勤统计签到等。');
INSERT INTO `options` VALUES ('9', 'departmentInfo4', '宣传部：负责社团和每次活动的文案以及宣传。');
INSERT INTO `options` VALUES ('10', 'departmentInfo5', '财务部：负责社团资金管理，以及活动物品采购。');
INSERT INTO `options` VALUES ('11', 'botQQ', '');
INSERT INTO `options` VALUES ('12', 'adminCN', '马以');
INSERT INTO `options` VALUES ('13', 'adminQQ', '');
INSERT INTO `options` VALUES ('14', null, null);

-- ----------------------------
-- Table structure for qrcodes
-- ----------------------------
DROP TABLE IF EXISTS `qrcodes`;
CREATE TABLE `qrcodes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `token` varchar(32) NOT NULL,
  `uid` int(5) unsigned DEFAULT NULL,
  `creatime` bigint(13) unsigned NOT NULL,
  `usetime` bigint(13) unsigned DEFAULT NULL,
  `worker` int(3) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `token` (`token`) USING BTREE,
  KEY `uid` (`uid`),
  KEY `worker` (`worker`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of qrcodes
-- ----------------------------

-- ----------------------------
-- Table structure for workers
-- ----------------------------
DROP TABLE IF EXISTS `workers`;
CREATE TABLE `workers` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `name` char(3) NOT NULL,
  `job` varchar(7) NOT NULL,
  `token` char(16) NOT NULL,
  `qq` bigint(10) DEFAULT NULL,
  `psw` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of workers
-- ----------------------------

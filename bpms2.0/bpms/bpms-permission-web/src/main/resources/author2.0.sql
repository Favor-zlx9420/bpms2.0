/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : author2.0

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 17/03/2020 18:09:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(255) NOT NULL COMMENT 'access tokenMD5加密后的值',
  `token` blob COMMENT '唯一令牌，存储OAurh2AccessToken对象序列化后的二进制',
  `authentication_id` varchar(128) NOT NULL COMMENT '认证id，生成值的规则时根据当前的username、client_id与scope通过MD5架米生成的',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `client_id` varchar(50) DEFAULT NULL COMMENT '客户端id',
  `authentication` blob COMMENT '存储OAuth2Authentication对象序列化后的二进制数据',
  `refresh_token` varchar(255) DEFAULT NULL COMMENT '刷新令牌',
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='认证访问令牌';

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(50) NOT NULL COMMENT '客户端唯一标识',
  `resource_ids` varchar(255) DEFAULT NULL COMMENT '客户端访问的资源id集合',
  `client_secret` varchar(125) NOT NULL COMMENT '客户端(client)访问密钥',
  `scope` varchar(255) DEFAULT NULL COMMENT '客户端申请的权限范围',
  `authorized_grant_types` varchar(255) DEFAULT '' COMMENT '指定客户端支持的grant_type,可选值包括authorization_code,password,refresh_token,implicit,client_credentials',
  `web_server_redirect_uri` varchar(255) DEFAULT NULL COMMENT '客户端的重定向URI,客户端的重定向URI,可为空, 当grant_type为authorization_code或implicit时, 在Oauth的流程中会使用并检查与注册时填写的redirect_uri是否一致',
  `authorities` varchar(255) DEFAULT NULL COMMENT '客户端所拥有的权限值',
  `access_token_validity` int(11) DEFAULT '60' COMMENT '设定客户端的access_token的有效时间值(单位:秒),若不设定值则使用默认的有效时间值(60 * 60 * 12, 12小时)',
  `refresh_token_validity` int(11) DEFAULT NULL COMMENT '设定客户端的refresh_token的有效时间值(单位:秒),若不设定值则使用默认的有效时间值(60 * 60 * 24 * 30, 30天)',
  `additional_information` varchar(255) DEFAULT NULL COMMENT '扩展字段,若需使用,必须是JSON格式的数据',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `archived` int(1) unsigned zerofill DEFAULT '0' COMMENT '用于标识客户端是否已存档(即实现逻辑删除),默认值为''0''(即未存档)',
  `trusted` int(1) unsigned zerofill DEFAULT '0' COMMENT '设置客户端是否为受信任的(0 : 不受信任的,1 : 受信任的). ',
  `autoapprove` varchar(255) DEFAULT '1' COMMENT '是否自动授权,该字段只适用于grant_type="authorization_code"的情况',
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='认证客户端详细信息';

-- ----------------------------
-- Table structure for oauth_client_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token` (
  `token_id` varchar(255) NOT NULL COMMENT '从服务器端获取到的access_token的值.',
  `token` blob COMMENT '唯一令牌',
  `authentication_id` varchar(255) NOT NULL COMMENT '认证id---根据当前的username(如果有),client_id与scope通过MD5加密生成的',
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名',
  `client_id` varchar(50) NOT NULL COMMENT '客户端标识',
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='认证客户端令牌';

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `code` varchar(125) NOT NULL COMMENT '存储服务端系统生成的code的值',
  `authentication` blob COMMENT '存储AuthorizationRequestHolder序列化后的二进制数据.',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='认证code';

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(255) NOT NULL COMMENT '该字段的值是将refresh_token的值通过MD5加密后存储的.',
  `token` blob COMMENT '存储将OAuth2RefreshToken对象序列化后的二进制数据.',
  `authentication` blob COMMENT '存储将OAuth2Authentication对象序列化后的二进制数据.',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='认证刷新令牌';

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL COMMENT '用户名',
  `series` varchar(64) NOT NULL COMMENT '密钥',
  `token` varchar(64) NOT NULL COMMENT '令牌',
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后使用时间',
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登陆系统记录';

-- ----------------------------
-- Table structure for sys_code
-- ----------------------------
DROP TABLE IF EXISTS `sys_code`;
CREATE TABLE `sys_code` (
  `id` varchar(32) NOT NULL,
  `code` varchar(128) DEFAULT '' COMMENT '码值唯一标识, 例如，SEX、SEX_1、SEX_2',
  `code_name` varchar(128) DEFAULT NULL COMMENT '码值的中文名',
  `code_suffix` varchar(128) DEFAULT NULL COMMENT '码值的后缀，一般用数字表示',
  `type` varchar(128) DEFAULT NULL COMMENT '1：目录 2: 字典码',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父级ID',
  `serial_number` varchar(4) DEFAULT NULL COMMENT '在同一级节点中的序号',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `enable` varchar(10) NOT NULL DEFAULT '' COMMENT '是否启用 true false',
  `open` varchar(10) NOT NULL DEFAULT '' COMMENT '是否展开 true false',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统数据字典';

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `key` varchar(50) DEFAULT NULL COMMENT 'key',
  `value` varchar(2000) DEFAULT NULL COMMENT 'value',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `key` (`key`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='系统配置信息表';

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11176 DEFAULT CHARSET=utf8 COMMENT='系统日志';

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` int(10) DEFAULT NULL COMMENT '父级ID',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(255) DEFAULT NULL COMMENT '菜单链接',
  `icon` varchar(255) DEFAULT NULL COMMENT '菜单图标',
  `serial_number` int(10) DEFAULT NULL COMMENT '序号',
  `status` int(10) NOT NULL DEFAULT '0' COMMENT '状态 （0: 显示，1:隐藏）',
  `operat_permission` varchar(255) DEFAULT NULL COMMENT '操作权限标识',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` date DEFAULT NULL COMMENT '更新时间',
  `enable` varchar(20) DEFAULT NULL COMMENT '是否启用 true false',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统菜单表';

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(10) unsigned zerofill NOT NULL COMMENT '用户id',
  `user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '用户名',
  `url` varchar(255) NOT NULL DEFAULT '' COMMENT '权限路径(与注解控制权限时互斥且只适合单一项目)',
  `role_permission` varchar(255) NOT NULL DEFAULT '' COMMENT '角色权限',
  `description` varchar(50) DEFAULT '' COMMENT '描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_status` int(1) unsigned zerofill NOT NULL COMMENT '是否有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统权限';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission` VALUES (0000000001, 0000000001, 'admin', '', 'ROLE_ADMIN', '超级管理员拥有所有权限', '2020-01-09 15:24:05', '2020-01-09 15:25:30', 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `user_id` int(10) unsigned zerofill NOT NULL COMMENT '用户id',
  `permission_id` int(10) unsigned zerofill NOT NULL COMMENT '权限id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (0000000001, 0000000001);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` int(10) NOT NULL COMMENT '角色id',
  `menu_id` int(10) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`menu_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色菜单表';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT '主键',
  `remark_name` varchar(20) NOT NULL DEFAULT '' COMMENT '备注名',
  `user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(255) NOT NULL DEFAULT '' COMMENT '密码(加密后)',
  `phone_number` varchar(20) NOT NULL DEFAULT '' COMMENT '电话号码(在获取token时，作为支持)',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_status` int(1) unsigned zerofill NOT NULL COMMENT '是否有效(0:无效，1:有效)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (0000000001, '超级管理员', 'admin', '$2a$10$fWcRmUMfwQ6RPeAAByxOGukYfN6UoBb2vUkQraX0hHHcZ121trBPe', '13266516033', '2020-01-09 15:24:05', '2020-01-11 12:47:18', 1);
COMMIT;

-- ----------------------------
-- Procedure structure for addToDatabase
-- ----------------------------
DROP PROCEDURE IF EXISTS `addToDatabase`;
delimiter ;;
CREATE PROCEDURE `author2.0`.`addToDatabase`(IN userName VARCHAR (50),
	IN cipherText VARCHAR (255),
	IN phoneNumber VARCHAR (50),
	IN rolePermission VARCHAR (50),
	IN createTime TIMESTAMP,
	IN updateTime TIMESTAMP,
	OUT result INT)
  COMMENT '用户注册'
BEGIN

DECLARE userId,
 permissionId,
 returnRes INT DEFAULT 0;

START TRANSACTION;

INSERT INTO `author2.0`.oauth_user (
	`id`,
	`remark_name`,
	`user_name`,
	`password`,
	`phone_number`,
	`create_time`,
	`update_time`,
	`is_status`
)
VALUES
	(
		NULL,
		'',
		userName,
		cipherText,
		phoneNumber,
		createTime,
		updateTime,
		1
	);


SET returnRes = 1;


IF (returnRes <> 1) THEN
	ROLLBACK;


ELSE
	SELECT
		id INTO userId
	FROM
		`author2.0`.oauth_user
	WHERE
		user_name = userName LIMIT 1;


END
IF;

INSERT INTO `author2.0`.oauth_permission (
	`id`,
	`user_id`,
	`user_name`,
	`url`,
	`role_permission`,
	`description`,
	`create_time`,
	`update_time`,
	`is_status`
)
VALUES
	(
		NULL,
		userId,
		userName,
		'',
		rolePermission,
		'',
		createTime,
		updateTime,
		1
	);


SET returnRes = returnRes + 1;


IF (returnRes < 1) THEN
	ROLLBACK;


ELSE
	SELECT
		id INTO permissionId
	FROM
		`author2.0`.oauth_permission
	WHERE
		user_id = userId;


END
IF;


IF (userId <> 0)
AND (permissionId <> 0) THEN
	INSERT INTO `author2.0`.oauth_role (`user_id`, `permission_id`)
VALUES
	(userId, permissionId);


ELSE
	ROLLBACK;


END
IF;

SELECT
	ROW_COUNT() INTO result;

COMMIT;


END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;

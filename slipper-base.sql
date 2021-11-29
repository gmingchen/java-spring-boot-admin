/*
 Navicat Premium Data Transfer

 Source Server         : Chen
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : 139.196.182.46:3306
 Source Schema         : slipper-base

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 29/11/2021 15:53:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '盐',
  `nickname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `mobile` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `avatar` bigint NULL DEFAULT NULL COMMENT '头像',
  `status` tinyint NULL DEFAULT 1 COMMENT '状态 0：禁用 1：正常',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `created_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `updated_at` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 76 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '管理员' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d', 'YzcmCZNvbXocrsz9dm8e', 'gumingchen', '15295798912', '1240235512@qq.com', NULL, 1, NULL, '1995-08-30 05:20:00', 1, '2021-11-29 15:47:17', 0);

-- ----------------------------
-- Table structure for admin__role
-- ----------------------------
DROP TABLE IF EXISTS `admin__role`;
CREATE TABLE `admin__role`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `admin_id` bigint NULL DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint NULL DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户 角色 关联' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin__role
-- ----------------------------

-- ----------------------------
-- Table structure for captcha
-- ----------------------------
DROP TABLE IF EXISTS `captcha`;
CREATE TABLE `captcha`  (
  `uuid` varchar(72) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'UUID',
  `code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '验证码',
  `expired_at` datetime NULL DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '图片验证码' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of captcha
-- ----------------------------

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `parent_id` bigint NOT NULL COMMENT '父ID',
  `name_cn` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '中文名称',
  `name_en` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '英文名称',
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单URL',
  `permission` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int NULL DEFAULT 0 COMMENT '类型   0：目录   1：菜单   2：按钮	',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图标',
  `sort` int NULL DEFAULT 1 COMMENT '排序',
  `is_display` tinyint NULL DEFAULT 1 COMMENT '是否显示',
  `is_tab` tinyint NULL DEFAULT 1 COMMENT '是否显示在标签栏',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `created_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `updated_at` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint NULL DEFAULT 0,
  `is_multiple` tinyint NULL DEFAULT 0 COMMENT '是否支持标签栏多开',
  `is_alive` tinyint NULL DEFAULT 0 COMMENT '是否缓存',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 97 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单权限' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, 0, '开发管理', 'Development', NULL, '', 0, 'development', 999, 1, 1, 1, '2021-03-31 10:50:08', NULL, NULL, 0, 0, 0);
INSERT INTO `menu` VALUES (2, 1, '菜单管理', 'Menu', 'develop/menu/index', 'backstage:menu:list', 1, 'menu', 1, 1, 1, 1, '2021-03-31 10:54:16', 1, '2021-06-28 16:27:40', 0, 0, 1);
INSERT INTO `menu` VALUES (3, 2, '查看', 'View', NULL, 'backstage:menu:info', 2, NULL, 1, 1, 1, 1, '2021-04-21 22:35:37', 1, '2021-05-13 15:49:02', 0, 0, 0);
INSERT INTO `menu` VALUES (4, 2, '新增', 'Add', NULL, 'backstage:menu:create;backstage:menu:select', 2, NULL, 2, 1, 1, 1, '2021-04-21 22:35:37', 1, '2021-05-13 15:49:13', 0, 0, 0);
INSERT INTO `menu` VALUES (5, 2, '编辑', 'Edit', NULL, 'backstage:menu:update;backstage:menu:info;backstage:menu:select', 2, NULL, 3, 1, 1, 1, '2021-04-21 22:35:37', 1, '2021-05-13 15:49:39', 0, 0, 0);
INSERT INTO `menu` VALUES (6, 2, '删除', 'Delete', NULL, 'backstage:menu:delete', 2, NULL, 4, 1, 1, 1, '2021-04-21 22:35:37', 1, '2021-05-13 15:49:55', 0, 0, 0);
INSERT INTO `menu` VALUES (7, 2, '显示控制', 'Display control', NULL, 'backstage:menu:display', 2, NULL, 5, 1, 1, 1, '2021-04-21 22:35:37', 1, '2021-05-13 15:50:48', 0, 0, 0);
INSERT INTO `menu` VALUES (8, 2, 'tab控制', 'Tab control', NULL, 'backstage:menu:tab', 2, NULL, 6, 1, 1, 1, '2021-04-21 22:35:37', 1, '2021-05-13 15:51:14', 0, 0, 0);
INSERT INTO `menu` VALUES (9, 2, 'tab多开控制', 'Tab multiple open control', NULL, 'backstage:menu:multiple', 2, NULL, 7, 1, 1, 1, '2021-04-21 22:35:37', 1, '2021-05-13 15:51:50', 0, 0, 0);
INSERT INTO `menu` VALUES (10, 2, '缓存控制', 'Cache control', NULL, 'backstage:menu:alive', 2, NULL, 8, 1, 1, 1, '2021-04-21 22:35:37', 1, '2021-05-13 15:52:20', 0, 0, 0);
INSERT INTO `menu` VALUES (11, 0, '系统管理', 'System', '', '', 0, 'system', 997, 1, 1, 1, '2021-04-21 22:35:37', 1, '2021-11-29 14:43:26', 0, 0, 0);
INSERT INTO `menu` VALUES (12, 11, '角色管理', 'Role', 'system/role/index', 'backstage:role:page', 1, 'role', 1, 1, 1, 1, '2021-04-21 22:51:03', 1, '2021-06-28 16:27:16', 0, 0, 1);
INSERT INTO `menu` VALUES (13, 12, '查看', 'View', NULL, 'backstage:role:info', 2, NULL, 1, 1, 1, 1, '2021-04-21 23:10:17', 1, '2021-05-13 15:45:12', 0, 0, 0);
INSERT INTO `menu` VALUES (14, 12, '新增', 'Add', NULL, 'backstage:role:create;backstage:menu:self:select', 2, NULL, 2, 1, 1, 1, '2021-04-21 23:12:18', 1, '2021-05-13 15:45:42', 0, 0, 0);
INSERT INTO `menu` VALUES (15, 12, '编辑', 'Edit', NULL, 'backstage:role:update;backstage:role:info;backstage:menu:self:select', 2, NULL, 3, 1, 1, 1, '2021-04-21 23:13:16', 1, '2021-05-13 15:45:56', 0, 0, 0);
INSERT INTO `menu` VALUES (16, 12, '删除', 'Delete', NULL, 'backstage:role:delete', 2, NULL, 4, 1, 1, 1, '2021-04-21 23:18:02', 1, '2021-05-13 15:46:11', 0, 0, 0);
INSERT INTO `menu` VALUES (17, 11, '管理员列表', 'Administrator', 'system/admin/index', 'backstage:admin:page', 1, 'admin', 1, 1, 1, 1, NULL, 1, '2021-06-28 16:27:24', 0, 0, 1);
INSERT INTO `menu` VALUES (18, 17, '查看', 'View', NULL, 'backstage:admin:info', 2, NULL, 1, 1, 1, 1, NULL, 1, '2021-05-13 15:46:26', 0, 0, 0);
INSERT INTO `menu` VALUES (19, 17, '新增', 'Add', NULL, 'backstage:admin:create;backstage:role:select', 2, NULL, 2, 1, 1, 1, NULL, 1, '2021-05-13 15:46:39', 0, 0, 0);
INSERT INTO `menu` VALUES (20, 17, '编辑', 'Edit', NULL, 'backstage:admin:update;backstage:admin:info;backstage:role:select', 2, NULL, 3, 1, 1, 1, NULL, 1, '2021-05-13 15:46:59', 0, 0, 0);
INSERT INTO `menu` VALUES (21, 17, '删除', 'Delete', NULL, 'backstage:admin:delete', 2, NULL, 4, 1, 1, 1, NULL, 1, '2021-05-13 15:47:13', 0, 0, 0);
INSERT INTO `menu` VALUES (22, 17, '状态控制', 'State control', NULL, 'backstage:admin:status', 2, NULL, 5, 1, 1, 1, NULL, 1, '2021-05-13 15:47:52', 0, 0, 0);
INSERT INTO `menu` VALUES (23, 1, '接口文档', 'Interface document', 'http://localhost:8806/slipper/static/apidoc/index.html', '', 1, 'request', 20, 1, 1, 1, '2021-05-30 22:26:37', 1, '2021-11-29 14:42:47', 0, 0, 1);
INSERT INTO `menu` VALUES (24, 1, 'Druid监控', 'Druid monitoring', 'http://localhost:8806/slipper/druid/index.html', '', 1, 'druid', 21, 1, 1, 1, '2021-05-30 22:35:11', 1, '2021-11-29 14:43:00', 0, 0, 1);
INSERT INTO `menu` VALUES (25, 0, '日志管理', 'Log', '', '', 0, 'log', 998, 1, 1, 1, '2021-07-08 15:17:55', 1, '2021-07-08 15:19:57', 0, 0, 0);
INSERT INTO `menu` VALUES (26, 25, '操作日志', 'Operation log', 'log/operation/index', 'backstage:log:operation:page', 1, 'operation-log', 2, 1, 1, 1, '2021-07-08 15:39:13', 1, '2021-07-08 18:53:01', 0, 0, 1);
INSERT INTO `menu` VALUES (27, 26, '清除', 'Clear', '', 'backstage:log:operation:truncate', 2, NULL, 2, 1, 1, 1, '2021-07-08 15:39:46', 1, '2021-07-09 18:35:53', 0, 0, 0);
INSERT INTO `menu` VALUES (28, 26, '查看', 'View', '', 'backstage:log:operation:page	', 2, NULL, 1, 1, 1, 1, '2021-07-09 18:35:46', NULL, NULL, 0, 0, 0);

-- ----------------------------
-- Table structure for operation_log
-- ----------------------------
DROP TABLE IF EXISTS `operation_log`;
CREATE TABLE `operation_log`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '用户名',
  `operation` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作',
  `url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求URL',
  `method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求方式',
  `params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '参数',
  `class_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类名和方法名',
  `ip` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'ip地址',
  `agent` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `times` bigint NOT NULL COMMENT '执行时长',
  `creator` bigint NULL DEFAULT NULL COMMENT '操作者',
  `created_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 686 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '操作日志' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of operation_log
-- ----------------------------
INSERT INTO `operation_log` VALUES (1, 'admin', '编辑菜单', '/slipper/backstage/menu/update', 'POST', '[{\"id\":23,\"parentId\":1,\"nameCn\":\"接口文档\",\"nameEn\":\"Interface document\",\"url\":\"http://localhost:8806/slipper/static/apidoc/index.html\",\"permission\":\"\",\"type\":1,\"icon\":\"request\",\"sort\":20,\"isDisplay\":1,\"isAlive\":1,\"isTab\":1,\"isMultiple\":0,\"updater\":1,\"updatedAt\":\"Nov 29, 2021 2:42:47 PM\"}]', 'com.slipper.modules.develop.menu.MenuController.update()', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36', 35, 1, '2021-11-29 14:42:47');
INSERT INTO `operation_log` VALUES (2, 'admin', '编辑菜单', '/slipper/backstage/menu/update', 'POST', '[{\"id\":24,\"parentId\":1,\"nameCn\":\"Druid监控\",\"nameEn\":\"Druid monitoring\",\"url\":\"http://localhost:8806/slipper/druid/index.html\",\"permission\":\"\",\"type\":1,\"icon\":\"druid\",\"sort\":21,\"isDisplay\":1,\"isAlive\":1,\"isTab\":1,\"isMultiple\":0,\"updater\":1,\"updatedAt\":\"Nov 29, 2021 2:42:59 PM\"}]', 'com.slipper.modules.develop.menu.MenuController.update()', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36', 30, 1, '2021-11-29 14:43:00');
INSERT INTO `operation_log` VALUES (3, 'admin', '编辑菜单', '/slipper/backstage/menu/update', 'POST', '[{\"id\":11,\"parentId\":0,\"nameCn\":\"系统管理\",\"nameEn\":\"System\",\"url\":\"\",\"permission\":\"\",\"type\":0,\"icon\":\"system\",\"sort\":997,\"isDisplay\":1,\"isAlive\":0,\"isTab\":1,\"isMultiple\":0,\"updater\":1,\"updatedAt\":\"Nov 29, 2021 2:43:25 PM\"}]', 'com.slipper.modules.develop.menu.MenuController.update()', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36', 23, 1, '2021-11-29 14:43:26');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `is_display` tinyint NOT NULL DEFAULT 1 COMMENT '是否显示',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `created_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `updated_at` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 70 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role
-- ----------------------------

-- ----------------------------
-- Table structure for role__menu
-- ----------------------------
DROP TABLE IF EXISTS `role__menu`;
CREATE TABLE `role__menu`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint NULL DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint NULL DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4493 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色 菜单 关联\r\n' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role__menu
-- ----------------------------

-- ----------------------------
-- Table structure for token
-- ----------------------------
DROP TABLE IF EXISTS `token`;
CREATE TABLE `token`  (
  `admin_id` bigint NOT NULL COMMENT '用户ID',
  `token` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'token',
  `expired_at` datetime NULL DEFAULT NULL COMMENT '到期时间',
  `updated_at` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '管理员token' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of token
-- ----------------------------
INSERT INTO `token` VALUES (1, '2a20ae14246faabc442fd57e41ee78ac', '2021-11-29 16:47:27', '2021-11-29 15:47:27');

SET FOREIGN_KEY_CHECKS = 1;

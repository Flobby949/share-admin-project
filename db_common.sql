/*
 Navicat Premium Data Transfer

 Source Server         : localhost-dev
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : db_common

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 28/02/2024 10:30:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `pk_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键唯一字段',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '名称',
  `number` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '编号',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '描述',
  `delete_flag` tinyint(0) NOT NULL DEFAULT 0 COMMENT '0:未删除  1:已删除',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`pk_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_dict_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_config`;
CREATE TABLE `sys_dict_config`  (
  `pk_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键唯一字段',
  `dict_number` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典编号',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据值',
  `delete_flag` tinyint(0) NOT NULL DEFAULT 0 COMMENT '0:未删除  1:已删除',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`pk_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_manager
-- ----------------------------
DROP TABLE IF EXISTS `sys_manager`;
CREATE TABLE `sys_manager`  (
  `pk_id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `avatar` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '头像',
  `super_admin` tinyint(0) NOT NULL DEFAULT 0 COMMENT '超级管理员   0：否   1：是',
  `status` tinyint(0) NOT NULL DEFAULT 1 COMMENT '状态  0：停用   1：正常',
  `delete_flag` tinyint(0) NOT NULL DEFAULT 0 COMMENT '删除标识  0：正常   1：已删除',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`pk_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_manager
-- ----------------------------
INSERT INTO `sys_manager` VALUES (1, 'admin', '{bcrypt}$2a$10$mW/yJPHjyueQ1g26WNBz0uxVPa0GQdJO1fFZmqdkqgMTGnyszlXxu', 'https://cdn.maku.net/images/avatar.png', 1, 1, 0, '2023-05-18 18:18:55', '2024-02-28 09:27:51');

-- ----------------------------
-- Table structure for sys_manager_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_manager_role`;
CREATE TABLE `sys_manager_role`  (
  `pk_id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` int(0) NOT NULL COMMENT '角色ID',
  `manager_id` int(0) NOT NULL COMMENT '用户ID',
  `delete_flag` tinyint(0) NOT NULL DEFAULT 0 COMMENT '删除标识  0：正常   1：已删除',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`pk_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_manager_role
-- ----------------------------
INSERT INTO `sys_manager_role` VALUES (1, 1, 1, 0, '2024-02-28 10:17:32', '2024-02-28 10:17:32');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `pk_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `parent_id` int(0) NOT NULL DEFAULT 0 COMMENT '父级id',
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '名称',
  `title` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '标题',
  `path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '路径',
  `component` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '组件路径',
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '菜单类型 menu: 菜单 button: 按钮',
  `open_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '打开类型 tab: 选项卡 url: 外链',
  `url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '外链地址',
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '菜单图标',
  `auth` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '授权标识(多个用逗号分隔，如：sys:menu:list,sys:menu:save)',
  `keep_alive` tinyint(0) NOT NULL DEFAULT 0 COMMENT '是否缓存 0:true 1:false',
  `sort` int(0) NOT NULL DEFAULT 0 COMMENT '排序',
  `delete_flag` tinyint(0) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0-未删除，1-删除)',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `hide` tinyint(0) NOT NULL DEFAULT 1 COMMENT '是否隐藏（0:true,1fasle）',
  PRIMARY KEY (`pk_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 113 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, 'system', '系统管理', '/system', '', 'MENU_DIR', '', '', 'HomeFilled', '', 0, 1, 0, '2023-07-11 07:36:12', '2023-11-01 03:24:33', 1);
INSERT INTO `sys_menu` VALUES (2, 1, 'Manager', '管理员管理', '/system/manager', '/System/Manager', 'MENU', 'TAB', '', 'HomeFilled', 'sys:manager:list', 0, 1, 0, '2023-07-11 07:37:01', '2023-07-27 01:23:53', 1);
INSERT INTO `sys_menu` VALUES (3, 1, 'Role', '角色管理', '/system/role', '/System/Role', 'MENU', 'TAB', '', 'BellFilled', 'sys:role:list', 0, 2, 0, '2023-07-11 07:37:01', '2023-07-27 01:35:51', 1);
INSERT INTO `sys_menu` VALUES (4, 1, 'Menu', '菜单管理', '/system/menu', '/System/Menu', 'MENU', 'TAB', '', 'HomeFilled', 'sys:menu:list', 0, 3, 0, '2023-07-11 07:37:01', '2024-02-27 08:19:24', 1);
INSERT INTO `sys_menu` VALUES (5, 4, 'view', '查看', '', '', 'BUTTON', '', '', '', 'sys:menu:view', 1, 0, 0, '2023-07-11 07:37:19', '2024-02-28 10:25:05', 1);
INSERT INTO `sys_menu` VALUES (6, 4, 'add', '新增', '', '', 'BUTTON', '', '', '', 'sys:menu:add', 1, 1, 0, '2023-07-11 07:37:19', '2024-02-28 10:25:07', 1);
INSERT INTO `sys_menu` VALUES (7, 4, 'edit', '编辑', '', '', 'BUTTON', '', '', '', 'sys:menu:edit', 1, 2, 0, '2023-07-11 07:37:19', '2024-02-28 10:25:09', 1);
INSERT INTO `sys_menu` VALUES (8, 4, 'remove', '删除', '', '', 'BUTTON', '', '', '', 'sys:menu:remove', 1, 3, 0, '2023-07-11 07:37:19', '2024-02-28 10:25:12', 1);
INSERT INTO `sys_menu` VALUES (9, 3, 'view', '查看', '', '', 'BUTTON', '', '', '', 'sys:role:view', 1, 0, 0, '2023-07-11 07:37:19', '2024-02-28 10:25:15', 1);
INSERT INTO `sys_menu` VALUES (10, 3, 'add', '新增', '', '', 'BUTTON', '', '', '', 'sys:role:add', 1, 1, 0, '2023-07-11 07:37:19', '2024-02-28 10:25:18', 1);
INSERT INTO `sys_menu` VALUES (11, 3, 'edit', '编辑', '', '', 'BUTTON', '', '', '', 'sys:role:edit', 1, 2, 0, '2023-07-11 07:37:19', '2024-02-28 10:25:20', 1);
INSERT INTO `sys_menu` VALUES (12, 3, 'remove', '删除', '', '', 'BUTTON', '', '', '', 'sys:role:remove', 1, 3, 0, '2023-07-11 07:37:19', '2024-02-28 10:25:23', 1);
INSERT INTO `sys_menu` VALUES (13, 2, 'add', '新增', '', '', 'BUTTON', '', '', '', 'sys:manager:add', 1, 0, 0, '2023-07-26 09:01:40', '2024-02-28 10:25:31', 1);
INSERT INTO `sys_menu` VALUES (14, 2, 'view', '查看', '', '', 'BUTTON', '', '', '', 'sys:manager:view', 1, 1, 0, '2023-07-26 09:02:05', '2024-02-28 10:25:36', 1);
INSERT INTO `sys_menu` VALUES (15, 2, 'edit', '编辑', '', '', 'BUTTON', '', '', '', 'sys:manager:edit', 1, 2, 1, '2023-07-26 09:02:21', '2024-02-28 10:25:38', 1);
INSERT INTO `sys_menu` VALUES (16, 2, 'reset-psw', '重置密码', '', '', 'BUTTON', '', '', '', 'sys:manager:reset-psw', 1, 3, 0, '2023-07-26 09:03:16', '2024-02-28 10:25:42', 1);
INSERT INTO `sys_menu` VALUES (17, 2, 'remove', '删除', '', '', 'BUTTON', '', '', '', 'sys:manager:remove', 1, 4, 0, '2023-07-26 09:03:33', '2024-02-28 10:25:45', 1);
INSERT INTO `sys_menu` VALUES (18, 0, 'home', '首页', '/home', '/Home/Home', 'MENU', 'TAB', '', 'HomeFilled', 'none', 1, 0, 0, '2023-11-01 03:38:01', '2024-02-28 10:25:49', 1);
INSERT INTO `sys_menu` VALUES (19, 2, 'edit', '编辑', '', '', 'BUTTON', '', '', '', 'sys:manager:edit', 1, 0, 0, '2023-12-02 01:56:23', '2024-02-28 10:25:53', 1);
INSERT INTO `sys_menu` VALUES (20, 1, 'Dict', '字典管理', '/system/dict', '/System/Dict/index', 'MENU', 'TAB', '', 'Memo', 'sys:dict:list', 1, 4, 0, '2024-02-27 08:16:57', '2024-02-28 10:26:03', 1);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `pk_id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '角色名称',
  `remark` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '备注',
  `delete_flag` tinyint(0) NOT NULL DEFAULT 0 COMMENT '删除标识  0：正常   1：已删除',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`pk_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '高级管理员', '高级管理员', 0, '2024-02-28 10:18:26', '2024-02-28 10:18:26');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `pk_id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` int(0) NOT NULL COMMENT '角色ID',
  `menu_id` int(0) NOT NULL COMMENT '菜单ID',
  `delete_flag` tinyint(0) NOT NULL DEFAULT 0 COMMENT '删除标识  0：正常   1：已删除',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`pk_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色菜单关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 1, 1, 0, '2024-02-28 10:27:25', '2024-02-28 10:27:25');
INSERT INTO `sys_role_menu` VALUES (2, 1, 2, 0, '2024-02-28 10:27:28', '2024-02-28 10:27:28');
INSERT INTO `sys_role_menu` VALUES (3, 1, 3, 0, '2024-02-28 10:27:35', '2024-02-28 10:27:35');
INSERT INTO `sys_role_menu` VALUES (4, 1, 4, 0, '2024-02-28 10:27:39', '2024-02-28 10:27:39');
INSERT INTO `sys_role_menu` VALUES (5, 1, 5, 0, '2024-02-28 10:27:44', '2024-02-28 10:27:44');
INSERT INTO `sys_role_menu` VALUES (6, 1, 6, 0, '2024-02-28 10:27:50', '2024-02-28 10:27:50');
INSERT INTO `sys_role_menu` VALUES (7, 1, 7, 0, '2024-02-28 10:27:54', '2024-02-28 10:27:54');
INSERT INTO `sys_role_menu` VALUES (8, 1, 8, 0, '2024-02-28 10:27:59', '2024-02-28 10:27:59');
INSERT INTO `sys_role_menu` VALUES (9, 1, 9, 0, '2024-02-28 10:28:04', '2024-02-28 10:28:04');
INSERT INTO `sys_role_menu` VALUES (10, 1, 10, 0, '2024-02-28 10:28:12', '2024-02-28 10:28:12');
INSERT INTO `sys_role_menu` VALUES (11, 1, 11, 0, '2024-02-28 10:28:16', '2024-02-28 10:28:16');
INSERT INTO `sys_role_menu` VALUES (12, 1, 12, 0, '2024-02-28 10:28:21', '2024-02-28 10:28:21');
INSERT INTO `sys_role_menu` VALUES (13, 1, 13, 0, '2024-02-28 10:28:25', '2024-02-28 10:28:25');
INSERT INTO `sys_role_menu` VALUES (14, 1, 14, 0, '2024-02-28 10:28:29', '2024-02-28 10:28:29');
INSERT INTO `sys_role_menu` VALUES (15, 1, 15, 0, '2024-02-28 10:28:35', '2024-02-28 10:28:35');
INSERT INTO `sys_role_menu` VALUES (16, 1, 16, 0, '2024-02-28 10:28:40', '2024-02-28 10:28:40');
INSERT INTO `sys_role_menu` VALUES (17, 1, 17, 0, '2024-02-28 10:28:45', '2024-02-28 10:28:45');
INSERT INTO `sys_role_menu` VALUES (18, 1, 18, 0, '2024-02-28 10:28:50', '2024-02-28 10:28:50');
INSERT INTO `sys_role_menu` VALUES (19, 1, 19, 0, '2024-02-28 10:28:55', '2024-02-28 10:28:55');
INSERT INTO `sys_role_menu` VALUES (20, 1, 20, 0, '2024-02-28 10:28:59', '2024-02-28 10:28:59');

SET FOREIGN_KEY_CHECKS = 1;

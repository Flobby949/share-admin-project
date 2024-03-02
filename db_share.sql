/*
 Navicat Premium Data Transfer

 Source Server         : Rds
 Source Server Type    : MySQL
 Source Server Version : 80034 (8.0.34)
 Source Host           : rm-cn-x0r3mu5un0004oho.rwlb.rds.aliyuncs.com:3306
 Source Schema         : db_share

 Target Server Type    : MySQL
 Target Server Version : 80034 (8.0.34)
 File Encoding         : 65001

 Date: 02/03/2024 16:33:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `pk_id` int NOT NULL AUTO_INCREMENT COMMENT '主键唯一字段',
  `title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '名称',
  `number` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '编号',
  `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '描述',
  `delete_flag` tinyint NOT NULL DEFAULT 0 COMMENT '0:未删除  1:已删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`pk_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dict_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_config`;
CREATE TABLE `sys_dict_config`  (
  `pk_id` int NOT NULL AUTO_INCREMENT COMMENT '主键唯一字段',
  `dict_number` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '字典编号',
  `title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '名称',
  `value` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '数据值',
  `delete_flag` tinyint NOT NULL DEFAULT 0 COMMENT '0:未删除  1:已删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`pk_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_config
-- ----------------------------

-- ----------------------------
-- Table structure for sys_manager
-- ----------------------------
DROP TABLE IF EXISTS `sys_manager`;
CREATE TABLE `sys_manager`  (
  `pk_id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `avatar` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '头像',
  `super_admin` tinyint NOT NULL DEFAULT 0 COMMENT '超级管理员   0：否   1：是',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态  0：停用   1：正常',
  `delete_flag` tinyint NOT NULL DEFAULT 0 COMMENT '删除标识  0：正常   1：已删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`pk_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户管理' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_manager
-- ----------------------------
INSERT INTO `sys_manager` VALUES (1, 'admin', '{bcrypt}$2a$10$mW/yJPHjyueQ1g26WNBz0uxVPa0GQdJO1fFZmqdkqgMTGnyszlXxu', 'https://cdn.maku.net/images/avatar.png', 1, 1, 0, '2023-05-18 18:18:55', '2024-02-28 09:27:51');

-- ----------------------------
-- Table structure for sys_manager_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_manager_role`;
CREATE TABLE `sys_manager_role`  (
  `pk_id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` int NOT NULL COMMENT '角色ID',
  `manager_id` int NOT NULL COMMENT '用户ID',
  `delete_flag` tinyint NOT NULL DEFAULT 0 COMMENT '删除标识  0：正常   1：已删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`pk_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色关系' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_manager_role
-- ----------------------------
INSERT INTO `sys_manager_role` VALUES (1, 1, 1, 0, '2024-02-28 10:17:32', '2024-02-28 10:17:32');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `pk_id` int NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `parent_id` int NOT NULL DEFAULT 0 COMMENT '父级id',
  `name` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '名称',
  `title` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '标题',
  `path` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '路径',
  `component` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '组件路径',
  `type` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '菜单类型 menu: 菜单 button: 按钮',
  `open_type` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '打开类型 tab: 选项卡 url: 外链',
  `url` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '外链地址',
  `icon` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '菜单图标',
  `auth` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '授权标识(多个用逗号分隔，如：sys:menu:list,sys:menu:save)',
  `keep_alive` tinyint NOT NULL DEFAULT 0 COMMENT '是否缓存 0:true 1:false',
  `sort` int NOT NULL DEFAULT 0 COMMENT '排序',
  `delete_flag` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除(0-未删除，1-删除)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `hide` tinyint NOT NULL DEFAULT 1 COMMENT '是否隐藏（0:true,1fasle）',
  PRIMARY KEY (`pk_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 121 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

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
INSERT INTO `sys_menu` VALUES (113, 1, 'User', '用户管理', '/user/usermanage', '/User/UserManage', 'MENU', 'TAB', '', 'Avatar', '', 0, 1, 0, '2024-03-01 13:04:01', '2024-03-01 13:04:01', 1);
INSERT INTO `sys_menu` VALUES (114, 0, 'resource', '资源管理', '/resource', '', 'MENU_DIR', '', '', 'Folder', '', 0, 1, 0, '2024-03-01 13:33:22', '2024-03-01 13:33:22', 1);
INSERT INTO `sys_menu` VALUES (115, 114, 'Tag', '标签管理', '/resource/tag', '/Resource/Tag', 'MENU', 'TAB', '', 'PriceTag', '', 0, 1, 0, '2024-03-01 13:52:56', '2024-03-01 13:58:45', 1);
INSERT INTO `sys_menu` VALUES (116, 114, 'Notice', '通知管理', '/resource/notice', '/Resource/Notice', 'MENU', 'TAB', '', 'Message', '', 0, 1, 0, '2024-03-01 13:54:06', '2024-03-01 13:54:06', 1);
INSERT INTO `sys_menu` VALUES (117, 114, 'Category', '分类管理', '/resource/category', '/Resource/Category', 'MENU', 'TAB', '', 'Histogram', '', 0, 1, 0, '2024-03-01 13:55:11', '2024-03-01 15:20:56', 1);
INSERT INTO `sys_menu` VALUES (118, 114, 'Resource', '资源管理', '/resource/resource', '/Resource/Resource', 'MENU', 'TAB', '', 'IceDrink', '', 0, 1, 0, '2024-03-01 15:35:09', '2024-03-01 15:35:09', 1);
INSERT INTO `sys_menu` VALUES (119, 113, 'view', '查看', '', '', 'BUTTON', '', '', '', 'sys:user:view', 0, 0, 0, '2024-03-02 12:50:27', '2024-03-02 12:50:27', 1);
INSERT INTO `sys_menu` VALUES (120, 113, 'edit', '编辑', '', '', 'BUTTON', '', '', '', 'sys:user:edit', 0, 0, 0, '2024-03-02 12:50:48', '2024-03-02 12:50:48', 1);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `pk_id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '角色名称',
  `remark` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '备注',
  `delete_flag` tinyint NOT NULL DEFAULT 0 COMMENT '删除标识  0：正常   1：已删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`pk_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色管理' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '高级管理员', '高级管理员', 0, '2024-02-28 10:18:26', '2024-02-28 10:18:26');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `pk_id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` int NOT NULL COMMENT '角色ID',
  `menu_id` int NOT NULL COMMENT '菜单ID',
  `delete_flag` tinyint NOT NULL DEFAULT 0 COMMENT '删除标识  0：正常   1：已删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`pk_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色菜单关系' ROW_FORMAT = DYNAMIC;

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

-- ----------------------------
-- Table structure for t_action
-- ----------------------------
DROP TABLE IF EXISTS `t_action`;
CREATE TABLE `t_action`  (
  `pk_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int NOT NULL COMMENT '用户id',
  `resource_id` int NOT NULL COMMENT '资源id',
  `type` tinyint NOT NULL COMMENT '类型（0-收藏，1-分享，2-下载）',
  `delete_flag` tinyint NOT NULL COMMENT '删除标识（0-未删除，1-删除）',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`pk_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_action
-- ----------------------------

-- ----------------------------
-- Table structure for t_bonus_log
-- ----------------------------
DROP TABLE IF EXISTS `t_bonus_log`;
CREATE TABLE `t_bonus_log`  (
  `pk_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int NOT NULL COMMENT '用户id',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户行为',
  `bonus` int NOT NULL COMMENT '积分',
  `delete_flag` tinyint NOT NULL COMMENT '删除标识（0-未删除，1-删除）',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`pk_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_bonus_log
-- ----------------------------
INSERT INTO `t_bonus_log` VALUES (1, 1, '资源审核通过', 10, 0, '2024-03-02 14:27:27', '2024-03-02 13:56:18');
INSERT INTO `t_bonus_log` VALUES (2, 1, '资源审核通过', 10, 0, '2024-03-02 14:27:27', '2024-03-02 13:59:03');
INSERT INTO `t_bonus_log` VALUES (3, 1, '资源审核通过', 10, 0, '2024-03-02 14:27:27', '2024-03-02 14:00:18');
INSERT INTO `t_bonus_log` VALUES (4, 1, '资源审核通过', 10, 0, '2024-03-02 14:27:27', '2024-03-02 14:00:38');
INSERT INTO `t_bonus_log` VALUES (5, 1, '每日签到', 10, 0, '2024-03-02 14:27:27', '2024-03-02 14:00:38');
INSERT INTO `t_bonus_log` VALUES (6, 2, '每日签到', 10, 0, '2024-03-02 14:27:27', '2024-03-02 14:00:38');

-- ----------------------------
-- Table structure for t_category
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category`  (
  `pk_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分类名称',
  `type` tinyint NOT NULL COMMENT '分类类型（0-网盘类型，1-资源类型）',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `delete_flag` tinyint NOT NULL COMMENT '删除标识（0-未删除，1-删除）',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`pk_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_category
-- ----------------------------
INSERT INTO `t_category` VALUES (3, '百度网盘', 0, '百度网盘', 0, '2024-03-02 12:40:35', '2024-03-02 12:40:35');
INSERT INTO `t_category` VALUES (4, '阿里网盘', 0, '阿里网盘', 0, '2024-03-02 12:40:44', '2024-03-02 12:40:44');
INSERT INTO `t_category` VALUES (5, '夸克网盘', 0, '夸克网盘', 0, '2024-03-02 12:40:53', '2024-03-02 12:40:53');
INSERT INTO `t_category` VALUES (6, '其他网盘', 0, '其他网盘', 0, '2024-03-02 12:41:00', '2024-03-02 12:41:00');
INSERT INTO `t_category` VALUES (7, '学习区', 1, '学习区', 0, '2024-03-02 12:41:07', '2024-03-02 12:41:07');
INSERT INTO `t_category` VALUES (8, '软件区', 1, '软件区', 0, '2024-03-02 12:41:15', '2024-03-02 12:41:15');
INSERT INTO `t_category` VALUES (9, '电子书', 1, '电子书', 0, '2024-03-02 12:41:22', '2024-03-02 12:41:22');
INSERT INTO `t_category` VALUES (10, '素材区', 1, '素材区', 0, '2024-03-02 12:41:29', '2024-03-02 12:41:29');
INSERT INTO `t_category` VALUES (11, '其他区', 1, '其他区', 0, '2024-03-02 12:41:36', '2024-03-02 12:41:36');

-- ----------------------------
-- Table structure for t_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice`  (
  `pk_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '封面图',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内容',
  `admin_id` int NOT NULL COMMENT '发布人id',
  `is_top` tinyint NOT NULL COMMENT '是否置顶（0-否，1-是）',
  `is_swiper` tinyint NOT NULL COMMENT '是否轮播（0-否，1-是）',
  `delete_flag` tinyint NOT NULL COMMENT '删除标识（0-未删除，1-删除',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`pk_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_notice
-- ----------------------------
INSERT INTO `t_notice` VALUES (3, 'hellp', NULL, '<p>hellphellphellphellphellphellphellphellphellphellphellphellphellphellphellphellphellphellphellphellphellphellphellphellphellphellphellphellphellphellphellphellphellphellphellphellp</p>', 1, 1, 0, 0, '2024-03-01 15:10:05', '2024-03-01 15:10:05');
INSERT INTO `t_notice` VALUES (4, '轮播图', 'https://flobby529.oss-cn-nanjing.aliyuncs.com/2024/03/02/de847a19-65c9-4d6d-a200-3e55a9c7e7bc.jpg', '<p>轮播图</p>', 1, 1, 1, 0, '2024-03-02 14:50:21', '2024-03-02 14:50:21');

-- ----------------------------
-- Table structure for t_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_resource`;
CREATE TABLE `t_resource`  (
  `pk_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `author` int NOT NULL COMMENT '发布人id',
  `disk_type` int NOT NULL COMMENT '网盘分类id',
  `res_type` json NOT NULL COMMENT '资源分类id,多个',
  `tags` json NOT NULL COMMENT '标签',
  `download_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '资源链接',
  `detail` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '详情',
  `price` int NOT NULL COMMENT '价格',
  `like_num` int NOT NULL COMMENT '点赞量',
  `is_top` tinyint NOT NULL COMMENT '是否置顶（0-否，1-是）',
  `status` tinyint NOT NULL COMMENT '审核状态（0-待审核，1-通过，2-拒绝）',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审核结果描述',
  `delete_flag` tinyint NOT NULL COMMENT '删除标识（0-未删除，1-删除）',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`pk_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_resource
-- ----------------------------
INSERT INTO `t_resource` VALUES (1, '测试', 1, 3, '[7, 8]', '[4, 5, 6]', '#', '#', 12, 0, 0, 1, '审核状态', 0, '2024-03-02 13:16:52', '2024-03-02 12:37:53');

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag`  (
  `pk_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签名',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `is_hot` tinyint NOT NULL COMMENT '是否热门（0-否，1-是）',
  `delete_flag` tinyint NOT NULL COMMENT '删除标识（0-未删除，1-删除）',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`pk_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_tag
-- ----------------------------
INSERT INTO `t_tag` VALUES (4, '极客空间', '极客空间', 0, 0, '2024-03-02 12:42:04', '2024-03-02 12:42:04');
INSERT INTO `t_tag` VALUES (5, '短视频', '短视频', 0, 0, '2024-03-02 12:42:10', '2024-03-02 12:42:10');
INSERT INTO `t_tag` VALUES (6, 'IT', 'IT', 0, 0, '2024-03-02 12:42:28', '2024-03-02 12:42:28');
INSERT INTO `t_tag` VALUES (7, '江苏', '江苏', 0, 0, '2024-03-02 12:42:33', '2024-03-02 12:42:33');
INSERT INTO `t_tag` VALUES (8, '短视频', '短视频', 0, 0, '2024-03-02 12:42:39', '2024-03-02 12:42:39');
INSERT INTO `t_tag` VALUES (9, '下载', '下载', 0, 0, '2024-03-02 12:42:45', '2024-03-02 12:42:45');
INSERT INTO `t_tag` VALUES (10, '前端', '前端', 0, 0, '2024-03-02 12:42:49', '2024-03-02 12:42:49');
INSERT INTO `t_tag` VALUES (11, '后端', '后端', 0, 0, '2024-03-02 12:42:54', '2024-03-02 12:42:54');
INSERT INTO `t_tag` VALUES (12, '公务员', '公务员', 1, 0, '2024-03-02 12:43:00', '2024-03-02 12:43:00');
INSERT INTO `t_tag` VALUES (13, '鸿蒙', '鸿蒙', 1, 0, '2024-03-02 12:43:07', '2024-03-02 12:43:07');
INSERT INTO `t_tag` VALUES (14, '付费', '付费', 0, 0, '2024-03-02 12:43:13', '2024-03-02 12:43:13');
INSERT INTO `t_tag` VALUES (15, '全栈', '全栈', 0, 0, '2024-03-02 12:43:20', '2024-03-02 12:43:20');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `pk_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `wx_open_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信openId',
  `nickname` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '昵称',
  `avatar` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '头像',
  `gender` tinyint NULL DEFAULT NULL COMMENT '性别（0-男，1-女）',
  `birthday` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生日',
  `bonus` int NULL DEFAULT NULL COMMENT '积分',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备用字段',
  `enabled` tinyint NOT NULL COMMENT '是否可用（0-冻结,1-可用）',
  `delete_flag` tinyint NOT NULL COMMENT '删除标识（0-未删除，1-删除）',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`pk_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, '13456780000', NULL, 'ahang1', 'https://cdn.maku.net/images/avatar.png', 1, '2001-09-18', 140, '123', 1, 0, '2024-03-02 16:32:57', '2024-03-01 13:43:49');
INSERT INTO `t_user` VALUES (2, '13356780000', NULL, 'flobby', 'https://flobby529.oss-cn-nanjing.aliyuncs.com/2024/03/02/8216accd-8e05-40bc-949b-e2ffc3c183ae.jpg', 0, '2001-08-28', 100, '123', 1, 0, '2024-03-02 16:33:02', '2024-03-02 13:44:07');

SET FOREIGN_KEY_CHECKS = 1;

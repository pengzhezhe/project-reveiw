/*
 Navicat MySQL Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : localhost:3306
 Source Schema         : project_review

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 20/04/2020 16:22:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for announcement
-- ----------------------------
DROP TABLE IF EXISTS `announcement`;
CREATE TABLE `announcement`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '公告标题',
  `content` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '公告内容',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of announcement
-- ----------------------------
INSERT INTO `announcement` VALUES (2, '测试公告2', '测试测试测试测试测试测试测试测试测试测试', '2020-04-03 20:00:34', '2020-04-03 20:00:37');
INSERT INTO `announcement` VALUES (3, '测试公告3', '测试测试测试测试测试测试测试测试测试测试', '2020-04-03 20:00:44', '2020-04-03 20:00:48');
INSERT INTO `announcement` VALUES (4, '测试公告4', '测试测试测试测试测试测试测试测试测试测试', '2020-04-03 20:00:54', '2020-04-03 20:00:57');
INSERT INTO `announcement` VALUES (5, '测试公告5', '测试测试测试测试测试测试测试测试测试测试', '2020-04-03 20:01:02', '2020-04-03 20:01:17');
INSERT INTO `announcement` VALUES (6, '测试测试测试测试', '测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试', '2020-04-12 15:47:56', '2020-04-12 15:47:56');
INSERT INTO `announcement` VALUES (7, '测试测试测试测试', '测试测试测试测试测试测试测试测试测试测试测试测试测试测试', '2020-04-12 15:48:01', '2020-04-12 15:48:01');
INSERT INTO `announcement` VALUES (8, '测试测试测试测试', '测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试', '2020-04-12 15:48:06', '2020-04-12 15:48:06');
INSERT INTO `announcement` VALUES (9, '测试测试测试测试', '测试测试测试测试测试测试测试测试测试测试测试测试测试测试', '2020-04-12 15:48:12', '2020-04-12 15:48:12');
INSERT INTO `announcement` VALUES (10, '测试测试测试测试', '测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试', '2020-04-12 15:48:17', '2020-04-12 15:48:17');
INSERT INTO `announcement` VALUES (11, '测试测试测试测试', '测试测试测试测试测试测试测试测试测试测试测试测试测试测试', '2020-04-12 15:48:21', '2020-04-12 15:48:21');
INSERT INTO `announcement` VALUES (12, '测试测试测试测试', '测试测试测试测试测试测试测试测试测试测试测试测试测试测试', '2020-04-12 15:48:26', '2020-04-12 15:48:26');
INSERT INTO `announcement` VALUES (13, '12312312312', '312312222222222222222222222222222222', '2020-04-16 15:51:00', '2020-04-16 15:51:00');
INSERT INTO `announcement` VALUES (14, '12345123131313', '12222222222222222222222222', '2020-04-16 16:49:06', '2020-04-16 16:49:06');
INSERT INTO `announcement` VALUES (15, '123124124', '123asdasdasdas', '2020-04-17 12:59:07', '2020-04-17 12:59:07');

-- ----------------------------
-- Table structure for attachment
-- ----------------------------
DROP TABLE IF EXISTS `attachment`;
CREATE TABLE `attachment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `project_id` int(11) NULL DEFAULT NULL COMMENT '所属项目',
  `original_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '附件原始名',
  `filename` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '附件文件名',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `attachment_ibfk_1`(`project_id`) USING BTREE,
  CONSTRAINT `attachment_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of attachment
-- ----------------------------
INSERT INTO `attachment` VALUES (1, 25, '以太坊.md', '61526e1f-9407-472c-88ca-c50c8fc33c72.md', '2020-04-20 16:20:14', '2020-04-20 16:20:14');
INSERT INTO `attachment` VALUES (2, 25, '基于以太坊的项目评审系统设计与实现.md', '4e55d635-4cf7-42f2-8b01-af7065d6afbd.md', '2020-04-20 16:20:14', '2020-04-20 16:20:14');
INSERT INTO `attachment` VALUES (3, 25, '计算机学院关于2020届本科毕业设计（论文）检测和答辩的通知安排.pdf', '84bc9c21-0b30-4ac5-84fb-801d48f3cb04.pdf', '2020-04-20 16:20:14', '2020-04-20 16:20:14');

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '项目名',
  `introduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '项目描述',
  `user_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '项目发起人姓名',
  `user_id` int(11) NOT NULL COMMENT '项目发起人',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '项目状态 0 待审核 1 已审核',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `project_ibfk_1`(`user_id`) USING BTREE,
  CONSTRAINT `project_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES (1, '测试项目', '测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试', '彭哲喆', 1, 0, '2020-04-01 17:28:24', '2020-04-20 16:14:32');
INSERT INTO `project` VALUES (2, '测试项目', '测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试', '彭哲喆', 1, 0, '2020-04-01 17:33:04', '2020-04-20 16:14:32');
INSERT INTO `project` VALUES (3, '测试项目', '测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试', '彭哲喆', 1, 0, '2020-04-01 17:33:58', '2020-04-20 16:14:32');
INSERT INTO `project` VALUES (4, '测试项目', '测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试', '彭哲喆', 1, 0, '2020-04-01 17:33:58', '2020-04-18 18:10:22');
INSERT INTO `project` VALUES (5, '测试项目', '测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试', '彭哲喆', 1, 0, '2020-04-01 17:33:58', '2020-04-18 18:10:22');
INSERT INTO `project` VALUES (6, '测试项目', '测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试', '彭哲喆', 1, 0, '2020-04-10 16:29:24', '2020-04-12 18:04:30');
INSERT INTO `project` VALUES (7, '测试项目', '测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试', '彭哲喆', 1, 0, '2020-04-10 16:51:14', '2020-04-12 18:04:30');
INSERT INTO `project` VALUES (8, '测试项目', '测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试', '彭哲喆', 1, 0, '2020-04-10 16:55:59', '2020-04-12 18:04:30');
INSERT INTO `project` VALUES (9, '测试项目', '测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试', '彭哲喆', 1, 0, '2020-04-10 16:58:28', '2020-04-12 18:04:30');
INSERT INTO `project` VALUES (10, '测试项目', '测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试', '彭哲喆', 1, 0, '2020-04-10 17:00:53', '2020-04-12 18:04:30');
INSERT INTO `project` VALUES (11, '测试项目', '测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试', '彭哲喆', 1, 0, '2020-04-10 17:03:46', '2020-04-12 18:04:30');
INSERT INTO `project` VALUES (12, '测试项目', '测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试', '彭哲喆', 1, 0, '2020-04-10 17:05:34', '2020-04-12 18:04:30');
INSERT INTO `project` VALUES (13, '测试项目', '测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试', '彭哲喆', 1, 0, '2020-04-10 17:14:31', '2020-04-12 18:04:30');
INSERT INTO `project` VALUES (14, '测试项目', '测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试', '彭哲喆', 1, 0, '2020-04-10 17:15:31', '2020-04-12 18:04:30');
INSERT INTO `project` VALUES (15, '测试项目', '测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试', '彭哲喆', 1, 0, '2020-04-10 19:47:58', '2020-04-12 18:04:30');
INSERT INTO `project` VALUES (16, '测试项目', '测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试', '彭哲喆', 1, 0, '2020-04-10 19:53:14', '2020-04-12 18:04:30');
INSERT INTO `project` VALUES (17, '测试项目', '测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试', '彭哲喆', 1, 0, '2020-04-10 19:55:28', '2020-04-12 18:04:30');
INSERT INTO `project` VALUES (18, '测试项目', '测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试', '彭哲喆', 1, 0, '2020-04-10 19:56:16', '2020-04-12 18:04:30');
INSERT INTO `project` VALUES (19, '测试项目', '测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试', '彭哲喆', 1, 0, '2020-04-10 19:58:51', '2020-04-12 18:04:30');
INSERT INTO `project` VALUES (20, '测试项目', '测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试', '彭哲喆', 1, 0, '2020-04-10 20:04:19', '2020-04-12 18:04:30');
INSERT INTO `project` VALUES (21, '测试项目', '测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试', '彭哲喆', 1, 0, '2020-04-10 20:13:16', '2020-04-12 18:04:30');
INSERT INTO `project` VALUES (22, '测试项目', '测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试', '彭哲喆', 1, 0, '2020-04-10 20:18:08', '2020-04-12 18:04:30');
INSERT INTO `project` VALUES (23, '测试项目', '测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试', '彭哲喆', 1, 0, '2020-04-10 20:19:15', '2020-04-12 18:04:30');
INSERT INTO `project` VALUES (24, '测试项目', '测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试', '彭哲喆', 1, 0, '2020-04-10 20:27:10', '2020-04-12 18:04:30');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '姓名',
  `sex` int(11) NOT NULL DEFAULT 0 COMMENT '性别 0 男 1 女',
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '邮箱',
  `user_type` int(11) NOT NULL DEFAULT 0 COMMENT '用户类型，0普通用户，1管理员',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'pengzhezhe', '123456', '彭哲喆', 0, '448116718@qq.com', 0, '2020-03-20 20:54:02', '2020-04-20 16:18:34');
INSERT INTO `user` VALUES (3, 'qwer1', '123456', 'qwer', 0, '448116718@qq.com', 0, '2020-04-14 17:15:28', '2020-04-17 12:44:13');
INSERT INTO `user` VALUES (4, 'qwer2', '123456', '4321', 0, '448116718@qq.com', 0, '2020-04-14 17:15:28', '2020-04-14 22:10:35');
INSERT INTO `user` VALUES (5, 'qwer3', '123456', '4321', 0, '448116718@qq.com', 0, '2020-04-14 17:15:28', '2020-04-14 22:10:36');
INSERT INTO `user` VALUES (6, 'test1', '123456', 'pzz', 0, '448116718@qq.com', 0, '2020-04-14 17:45:16', '2020-04-14 22:10:37');
INSERT INTO `user` VALUES (7, 'test2', '123456', 'pzz', 0, '448116718@qq.com', 0, '2020-04-14 17:45:21', '2020-04-14 22:10:38');
INSERT INTO `user` VALUES (8, 'test3', '123456', 'pzz', 0, '448116718@qq.com', 0, '2020-04-14 17:45:21', '2020-04-14 22:10:38');
INSERT INTO `user` VALUES (9, 'test4', '123456', 'pzz', 0, '448116718@qq.com', 0, '2020-04-14 17:45:21', '2020-04-14 22:10:39');
INSERT INTO `user` VALUES (10, 'test5', '123456', 'pzz', 0, '448116718@qq.com', 0, '2020-04-14 17:45:21', '2020-04-14 22:10:39');
INSERT INTO `user` VALUES (11, 'test6', '123456', 'pzz', 0, '448116718@qq.com', 0, '2020-04-14 17:45:21', '2020-04-14 22:10:40');
INSERT INTO `user` VALUES (12, 'test7', '123456', 'pzz', 0, '448116718@qq.com', 0, '2020-04-14 17:45:22', '2020-04-14 22:10:41');
INSERT INTO `user` VALUES (13, 'test8', '123456', 'pzz', 0, '448116718@qq.com', 0, '2020-04-14 17:45:22', '2020-04-14 22:10:42');
INSERT INTO `user` VALUES (14, 'test9', '123456', 'pzz', 0, '448116718@qq.com', 0, '2020-04-14 17:45:22', '2020-04-14 22:10:43');
INSERT INTO `user` VALUES (15, 'test11', '123456', 'pzz', 0, '448116718@qq.com', 0, '2020-04-14 17:45:22', '2020-04-14 22:10:45');
INSERT INTO `user` VALUES (16, 'test12', '123456', 'pzz', 0, '448116718@qq.com', 0, '2020-04-14 17:45:22', '2020-04-14 22:10:46');
INSERT INTO `user` VALUES (17, 'test13', '123456', 'pzz', 0, '448116718@qq.com', 0, '2020-04-14 17:45:22', '2020-04-14 22:10:48');
INSERT INTO `user` VALUES (18, 'test14', '123456', 'pzz', 0, '448116718@qq.com', 0, '2020-04-14 17:45:23', '2020-04-14 22:10:49');
INSERT INTO `user` VALUES (19, 'test15', '123456', 'pzz', 0, '448116718@qq.com', 0, '2020-04-14 17:45:23', '2020-04-14 22:10:49');
INSERT INTO `user` VALUES (20, 'test16', '123456', 'pzz', 0, '448116718@qq.com', 0, '2020-04-14 17:45:23', '2020-04-14 22:10:51');
INSERT INTO `user` VALUES (21, 'test17', '123456', 'pzz', 0, '448116718@qq.com', 0, '2020-04-14 17:45:23', '2020-04-14 22:10:52');
INSERT INTO `user` VALUES (22, 'test18', '123456', 'pzz', 0, '448116718@qq.com', 0, '2020-04-14 17:45:24', '2020-04-14 22:10:53');
INSERT INTO `user` VALUES (23, 'test19', '123456', 'pzz', 0, '448116718@qq.com', 0, '2020-04-14 17:45:24', '2020-04-14 22:10:55');
INSERT INTO `user` VALUES (24, 'test20', '123456', 'pzz', 0, '448116718@qq.com', 0, '2020-04-14 17:45:24', '2020-04-14 22:10:57');
INSERT INTO `user` VALUES (25, 'qwert', '123456', 'qwer', 0, '448116718@qq.com', 0, '2020-04-14 22:36:51', '2020-04-14 22:36:51');
INSERT INTO `user` VALUES (26, 'admin', '123456', '管理员A', 0, '448116718@qq.com', 1, '2020-04-19 13:18:48', '2020-04-19 20:38:45');
INSERT INTO `user` VALUES (27, 'admin2', '123456', '彭哲喆', 0, '448116718@qq.com', 1, '2020-04-19 14:34:03', '2020-04-19 14:34:03');
INSERT INTO `user` VALUES (28, 'pzz123', '123456', '1231231', 0, '44@qq.com', 0, '2020-04-19 19:06:50', '2020-04-19 19:06:50');
INSERT INTO `user` VALUES (29, 'qwer123', '123456', '1234', 0, '44@qq.com', 0, '2020-04-19 19:07:40', '2020-04-19 19:07:40');

SET FOREIGN_KEY_CHECKS = 1;

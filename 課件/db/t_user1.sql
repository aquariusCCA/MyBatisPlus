/*
 Navicat MySQL Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50562
 Source Host           : localhost:3306
 Source Schema         : mybatis_plus

 Target Server Type    : MySQL
 Target Server Version : 50562
 File Encoding         : 65001

 Date: 13/02/2023 00:22:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `uid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `sex` int(11) NULL DEFAULT NULL COMMENT '性别',
  `is_deleted` int(11) NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 103 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'Jone', 18, 'test1@baomidou.com', NULL, 0);
INSERT INTO `t_user` VALUES (2, 'Jack', 20, 'test2@baomidou.com', NULL, 0);
INSERT INTO `t_user` VALUES (3, 'Tom', 28, 'test3@baomidou.com', NULL, 0);
INSERT INTO `t_user` VALUES (4, 'lcx123', 25, 'lcx123@linchenxi123.com', NULL, 0);
INSERT INTO `t_user` VALUES (5, 'lcx123', 25, 'lcx123@linchenxi123.com', NULL, 0);
INSERT INTO `t_user` VALUES (6, '张三', 23, 'zhangsan@atguigu.com', NULL, 0);
INSERT INTO `t_user` VALUES (100, '张三', 23, 'zhangsan@atguigu.com', NULL, 0);
INSERT INTO `t_user` VALUES (101, '张三', 23, 'zhangsan@atguigu.com', NULL, 1);
INSERT INTO `t_user` VALUES (102, 'Enum', 20, NULL, 1, 0);

SET FOREIGN_KEY_CHECKS = 1;

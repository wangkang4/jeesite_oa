/*
Navicat MySQL Data Transfer

Source Server         : 26
Source Server Version : 50544
Source Host           : 192.168.3.26:3306
Source Database       : oa_thd

Target Server Type    : MYSQL
Target Server Version : 50544
File Encoding         : 65001

Date: 2018-01-26 16:17:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for pms_project
-- ----------------------------
DROP TABLE IF EXISTS `pms_project`;
CREATE TABLE `pms_project` (
  `project_id` char(32) NOT NULL COMMENT '项目Id',
  `project_name` char(50) NOT NULL COMMENT '项目名称',
  `customer_id` char(32) DEFAULT '' COMMENT '所属客户id',
  `customer_contact_id` char(32) DEFAULT '' COMMENT '客户联系人id',
  `status` char(20) DEFAULT '' COMMENT '目前阶段(机会点、项目启动、技术交流、招标准备、招标、签订合同、交付中、已收款，可自定义见字典)',
  `progress` double DEFAULT NULL COMMENT '进度',
  `money` double DEFAULT NULL COMMENT '项目金额',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `statusment_time` date DEFAULT NULL COMMENT '结单日期',
  `costomer_analysis` varchar(255) DEFAULT '' COMMENT '客户分析',
  `dec_mak_chain_analysis` varchar(255) DEFAULT '' COMMENT '决策链分析',
  `competitors_analysis` varchar(255) DEFAULT '' COMMENT '竞争对手分析',
  `chance_point` varchar(255) DEFAULT '' COMMENT '机会点',
  `problem_point` varchar(255) DEFAULT '' COMMENT '问题点',
  `target` varchar(255) DEFAULT '' COMMENT '项目目标',
  `market_strategy_tactics` varchar(255) DEFAULT '' COMMENT '市场策略及战术',
  `implementation_plan` varchar(255) DEFAULT '' COMMENT '实施计划',
  `resource_help‏` varchar(255) DEFAULT '' COMMENT '资源求助',
  `product_type` char(20) DEFAULT '' COMMENT '产品类型(自有产品、代理产品、服务产品)',
  `create_by` char(32) NOT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` char(32) NOT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标识  0：未删除 1：已删除',
  `ext1` char(200) DEFAULT '',
  `ext2` char(200) DEFAULT '',
  `ext3` char(200) DEFAULT '',
  `ext4` char(200) DEFAULT '',
  `ext5` char(200) DEFAULT '',
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pms_project_comment
-- ----------------------------
DROP TABLE IF EXISTS `pms_project_comment`;
CREATE TABLE `pms_project_comment` (
  `comment_id` char(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '评论Id',
  `project_id` char(32) NOT NULL COMMENT '所属项目id',
  `content` varchar(255) DEFAULT '' COMMENT '评论内容',
  `comment_by` char(32) NOT NULL COMMENT '评论人id',
  `comment_time` datetime DEFAULT NULL COMMENT '评论时间',
  `create_by` char(32) NOT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` char(32) NOT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标识  0：未删除 1：已删除',
  `ext1` char(200) DEFAULT NULL,
  `ext2` char(200) DEFAULT '',
  `ext3` char(200) DEFAULT '',
  `ext4` char(200) DEFAULT '',
  `ext5` char(200) DEFAULT '',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pms_project_document
-- ----------------------------
DROP TABLE IF EXISTS `pms_project_document`;
CREATE TABLE `pms_project_document` (
  `document_id` char(32) NOT NULL COMMENT '项目文档id',
  `project_id` char(32) COMMENT '所属项目',
  `document_name` char(200) DEFAULT '' COMMENT '文档名称',
  `upload_by` char(32) NOT NULL COMMENT '上传人',
  `upload_time` datetime DEFAULT NULL COMMENT '上传时间',
  `upload_addr` char(200) DEFAULT '' COMMENT '上传地址',
  `create_by` char(32) NOT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` char(32) NOT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标识  0：未删除 1：已删除',
  `ext1` char(200) DEFAULT '',
  `ext2` char(200) DEFAULT '',
  `ext3` char(200) DEFAULT '',
  `ext4` char(200) DEFAULT '',
  `ext5` char(200) DEFAULT '',
  PRIMARY KEY (`document_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pms_project_expense
-- ----------------------------
DROP TABLE IF EXISTS `pms_project_expense`;
CREATE TABLE `pms_project_expense` (
  `expense_id` char(32) NOT NULL COMMENT '项目费用id',
  `project_id` char(32) NOT NULL COMMENT '所属项目',
  `money` double NOT NULL COMMENT '金额',
  `status` char(20) DEFAULT '' COMMENT '费用类型(市场费用、品牌、交付、收款、维护 可自定义)',
  `create_by` char(32) NOT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` char(32) NOT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标识  0：未删除 1：已删除',
  `ext1` char(200) DEFAULT '',
  `ext2` char(200) DEFAULT '',
  `ext3` char(200) DEFAULT '',
  `ext4` char(200) DEFAULT '',
  `ext5` char(200) DEFAULT '',
  PRIMARY KEY (`expense_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pms_project_help
-- ----------------------------
DROP TABLE IF EXISTS `pms_project_help`;
CREATE TABLE `pms_project_help` (
  `help_id` char(32) NOT NULL COMMENT '求助Id',
  `project_id` char(32) NOT NULL COMMENT '所属项目id',
  `content` varchar(255) DEFAULT '' COMMENT '协助内容',
  `helper` char(32) DEFAULT '' COMMENT '协助者id',
  `help_by` char(32) DEFAULT '' COMMENT '被协助者id',
  `status` char(20) DEFAULT '' COMMENT '状态(解决中,已解决、未解决)',
  `help_time` datetime DEFAULT NULL COMMENT '请求时间',
  `create_by` char(32) NOT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` char(32) NOT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标识  0：未删除 1：已删除',
  `ext1` char(200) DEFAULT '',
  `ext2` char(200) DEFAULT '',
  `ext3` char(200) DEFAULT '',
  `ext4` char(200) DEFAULT '',
  `ext5` char(200) DEFAULT '',
  PRIMARY KEY (`help_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pms_project_operation
-- ----------------------------
DROP TABLE IF EXISTS `pms_project_operation`;
CREATE TABLE `pms_project_operation` (
  `operation_id` char(32) NOT NULL COMMENT '项目操作id',
  `project_id` char(32) NOT NULL COMMENT '所属项目',
  `operation_by` char(32) NOT NULL COMMENT '操作人',
  `operation_time` datetime NOT NULL COMMENT '操作时间',
  `content` varchar(256) DEFAULT '' COMMENT '操作内容',
  `create_by` char(32) NOT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` char(32) NOT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标识  0：未删除 1：已删除',
  `ext1` char(200) DEFAULT '',
  `ext2` char(200) DEFAULT '',
  `ext3` char(200) DEFAULT '',
  `ext4` char(200) DEFAULT '',
  `ext5` char(200) DEFAULT '',
  PRIMARY KEY (`operation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

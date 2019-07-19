
CREATE TABLE `pms_contract` (
  `id` char(32) NOT NULL COMMENT '合同管理Id',
  `title` char(32) NOT NULL COMMENT '合同标题',
  `customer_id` char(32) NOT NULL COMMENT '客户Id',
  `project_id` char(32) NOT NULL COMMENT '项目Id',
  `amount` double NOT NULL COMMENT '合同金额',
  `total_amount` double DEFAULT NULL COMMENT '累计金额',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `service_period` char(20) DEFAULT NULL COMMENT '服务期限',
  `contract_terms` varchar(1000) DEFAULT NULL COMMENT '合同条款',
  `attachment_name` char(200) DEFAULT '' COMMENT '附件名字',
  `attachment_address` char(200) DEFAULT '' COMMENT '附件地址',
  `create_date` datetime NOT NULL,
  `create_by` char(32) NOT NULL,
  `update_date` datetime NOT NULL,
  `update_by` char(32) NOT NULL,
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标识 0-未删除 1-已删除',
  `ext1` char(200) DEFAULT '',
  `ext2` char(200) DEFAULT '',
  `ext3` char(200) DEFAULT '',
  `ext4` char(200) DEFAULT '',
  `ext5` char(200) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `pms_payment` (
  `payment_id` char(32) NOT NULL COMMENT '回款计划Id',
  `contract_id` char(32) NOT NULL COMMENT '合同管理Id',
  `payment_time` datetime NOT NULL COMMENT '回款时间',
  `payment_amount` double NOT NULL COMMENT '回款金额',
  `payment_mode` char(32) NOT NULL COMMENT '回款方式 字典模式：1.现金 2.支付宝 3.微信 4.银行卡 5.电汇',
  `payment_status` char(20) NOT NULL COMMENT '回款状态 字典模式：PAID:已回款，WAIT_PAID 待回款',
  `create_date` datetime NOT NULL,
  `create_by` char(32) NOT NULL,
  `update_date` datetime NOT NULL,
  `update_by` char(32) NOT NULL,
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标识 0-未删除 1-已删除',
  `ext1` char(200) DEFAULT '',
  `ext2` char(200) DEFAULT '',
  `ext3` char(200) DEFAULT '',
  `ext4` char(200) DEFAULT '',
  `ext5` char(200) DEFAULT '',
  PRIMARY KEY (`payment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



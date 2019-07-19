

CREATE TABLE `pms_customer` (
  `customer_id`                       char(32)          PRIMARY KEY                      COMMENT '客户Id',
  `customer_name`                     char(50)          NOT NULL   UNIQUE                COMMENT '客户名称',
  `address`                           char(200)         DEFAULT ''                       COMMENT '客户地址',
  `category`                          char(20)          NOT NULL                         COMMENT '所属类别 (公司级别、办事处级别  自定义见字典)',
  `industry`                          char(20)          NOT NULL                         COMMENT '所属行业(大企业、矿山、高校、金融、司法 自定义见字典)',
  `office`                            char(20)          NOT NULL                         COMMENT '所属办事处(合肥办、济南办 自定义见字典)',
  `area`                              char(20)          NOT NULL                         COMMENT '所属区域(办事处是一级、区域是二级，区域对应城市 自定义见字典)',
  `attachment_name`                   char(200)         DEFAULT ''                       COMMENT '附件名字',
  `attachment_address`                char(200)         DEFAULT ''                       COMMENT '附件地址',
  `create_date`                       datetime          NOT NULL,
  `create_by`                         char(32)          NOT NULL,
  `update_date`                       datetime          NOT NULL,
  `update_by`                         char(32)          NOT NULL,
  `del_flag`                          char(1)           NOT NULL   DEFAULT '0'           COMMENT '删除标识 0-未删除 1-已删除',
  `ext1`                              char(200)         DEFAULT '',
  `ext2`                              char(200)         DEFAULT '',
  `ext3`                              char(200)         DEFAULT '',
  `ext4`                              char(200)         DEFAULT '',
  `ext5`                              char(200)         DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `pms_project_relativer` (
  `id`                                char(32)          NOT NULL           COMMENT '客户Id或者项目Id',
  `relative_type`                     char(1)           NOT NULL           COMMENT '关联类别 0-客户 1-项目',
  `employees_id`                      char(32)          NOT NULL           COMMENT '员工Id',
  `employees_type`                    char(32)          NOT NULL           COMMENT '员工类型'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `pms_customer_contact` (
  `customer_contact_id`            char(32)         PRIMARY KEY           COMMENT '客户联系人Id',
  `customer_id`                    char(32)         NOT NULL              COMMENT '客户信息表Id',
  `customer_contact_name`          char(50)         NOT NULL              COMMENT '客户联系人姓名',
  `code_name`                      char(50)         DEFAULT ''            COMMENT '客户联系人代号',
  `phone`                          char(11)         DEFAULT ''            COMMENT '客户联系人电话',
  `weixin`                         char(50)         DEFAULT ''            COMMENT '客户联系人微信',
  `email`                          char(50)         DEFAULT ''            COMMENT '客户联系人邮箱',
  `position`                       char(20)         DEFAULT ''            COMMENT '客户联系人职位',
  `birthday`                       datetime         DEFAULT NULL          COMMENT '客户联系人生日',
  `interest`                       char(200)        DEFAULT ''            COMMENT '兴趣爱好',
  `customer_character`             char(200)        DEFAULT ''            COMMENT '客户联系人性格特征',
  `note`                           char(200)        DEFAULT ''            COMMENT '备注',
  `create_date`                    datetime         NOT NULL,    
  `create_by`                      char(32)         NOT NULL,
  `update_date`                    datetime         NOT NULL,
  `update_by`                      char(32)         NOT NULL,
  `del_flag`                       char(1)          NOT NULL DEFAULT '0'  COMMENT '删除标识 0-未删除 1-已删除',
  `ext1`                           char(200)        DEFAULT '',
  `ext2`                           char(200)        DEFAULT '',
  `ext3`                           char(200)        DEFAULT '',
  `ext4`                           char(200)        DEFAULT '',
  `ext5`                           char(200)        DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `pms_visit` (
  `visit_id`                       char(32)         PRIMARY KEY                  COMMENT '拜访Id',
  `title`                          char(50)         NOT NULL                     COMMENT '拜访标题',
  `visit_time`                     datetime         NOT NULL                     COMMENT '拜访时间',
  `visit_summary`                  char(200)        NOT NULL                     COMMENT '拜访纪要',
  `next_plan`                      char(200)        DEFAULT ''                   COMMENT '下一步计划',
  `customer_contact_id`            char(32)         NOT NULL                     COMMENT '客户联系人Id',
  `customer_id`                    char(32)         NOT NULL                     COMMENT '客户Id',
  `visit_address`                  char(200)        DEFAULT ''                   COMMENT '拜访地址',
  `create_by`                      char(32)         NOT NULL,
  `create_date`                    datetime         NOT NULL,
  `update_date`                    datetime         NOT NULL,
  `update_by`                      char(32)         NOT NULL,
  `del_flag`                       char(1)          NOT NULL DEFAULT '0'         COMMENT '删除标识 0-未删除 1-已删除',
  `ext1`                           char(200)        DEFAULT '',
  `ext2`                           char(200)        DEFAULT '',
  `ext3`                           char(200)        DEFAULT '',
  `ext4`                           char(200)        DEFAULT '',
  `ext5`                           char(200)        DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of visit
-- ----------------------------

-- ----------------------------
-- (songhuiping)
-- Table structure for `t_dict`
-- 字典表
-- ----------------------------
DROP TABLE IF EXISTS `t_dict`;
CREATE TABLE `t_dict` (
  `dict_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dict_name` varchar(32) NOT NULL DEFAULT '' COMMENT '字典名称',
  `from_table` varchar(64) NOT NULL DEFAULT '' COMMENT '枚举来源于哪个表',
  `dict_remark` varchar(128) NOT NULL DEFAULT '' COMMENT '字典描述',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`dict_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='字典表';

-- ----------------------------
-- Records of t_dict
-- 初始化数据
-- ----------------------------
INSERT INTO `t_dict` VALUES ('1', 'product_type', 't_grxxsqs_params', '产品类型', '2017-07-14 16:57:42', '2017-07-14 16:57:46');
INSERT INTO `t_dict` VALUES ('2', 'credit_side', 't_syqrs_params', '贷款方', '2017-07-14 18:27:11', '2017-07-14 18:27:11');
INSERT INTO `t_dict` VALUES ('3', 'guarantor', 't_syqrs_params', '担保方', '2017-07-14 18:27:34', '2017-07-14 18:27:34');


-- ----------------------------
-- Table structure for `t_dict_item`
-- ----------------------------
DROP TABLE IF EXISTS `t_dict_item`;
CREATE TABLE `t_dict_item` (
  `dict_item_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dict_item_code` varchar(32) NOT NULL DEFAULT '' COMMENT '字段编码',
  `dict_item_name` varchar(32) NOT NULL DEFAULT '' COMMENT '字典值',
  `dict_id` int(11) NOT NULL DEFAULT '0' COMMENT 'dict_表主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`dict_item_id`),
  KEY `index_dict_id` (`dict_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='字典枚举详细表';

-- ----------------------------
-- Records of t_dict_item
-- 初始化数据
-- ----------------------------
INSERT INTO `t_dict_item` VALUES ('1', '1', 'TYTCBT', '1', '2017-07-14 16:58:13', '2017-07-14 16:58:16');
INSERT INTO `t_dict_item` VALUES ('2', '2', 'TYSYJ', '1', '2017-07-14 17:01:24', '2017-07-14 17:01:24');
INSERT INTO `t_dict_item` VALUES ('3', '3', 'YDHBD', '1', '2017-07-14 17:01:26', '2017-07-14 17:01:26');
INSERT INTO `t_dict_item` VALUES ('4', '4', 'YDYNGJB', '1', '2017-07-14 17:01:29', '2017-07-14 17:01:29');
INSERT INTO `t_dict_item` VALUES ('5', '5', 'BDJXJ', '1', '2017-07-14 17:01:30', '2017-07-14 17:01:30');
INSERT INTO `t_dict_item` VALUES ('6', '6', 'QNJQH', '1', '2017-07-14 17:01:31', '2017-07-14 17:01:31');
INSERT INTO `t_dict_item` VALUES ('7', '7', 'JDSYH', '1', '2017-07-14 17:01:34', '2017-07-14 17:01:34');
INSERT INTO `t_dict_item` VALUES ('8', '1', '成都维仕小额贷款有限公司', '2', '2017-07-14 18:28:36', '2017-07-14 18:28:36');
INSERT INTO `t_dict_item` VALUES ('9', '1', '维仕担保有限公司', '3', '2017-07-14 18:29:09', '2017-07-14 18:29:09');
-- ----------------------------
-- Table structure for `t_grxxsqs_gener_status`
-- 个人信息授权书模块
-- ----------------------------
DROP TABLE IF EXISTS `t_grxxsqs_gener_status`;
CREATE TABLE `t_grxxsqs_gener_status` (
  `gener_status_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `grxxsqs_uuid` varchar(32) NOT NULL DEFAULT '0' COMMENT '生成合同所需基本信息',
  `generate_status` tinyint(2) NOT NULL DEFAULT '2' COMMENT '生成合同状态0失败 1成功 2初始化  合同生成完之后更改此状态',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '合同生成完之后更改此时间',
  PRIMARY KEY (`gener_status_id`),
  KEY `index_grxxsqs_uuid` (`grxxsqs_uuid`) USING BTREE,
  KEY `index_create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4458200 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `t_grxxsqs_params`
-- ----------------------------
DROP TABLE IF EXISTS `t_grxxsqs_params`;
CREATE TABLE `t_grxxsqs_params` (
  `grxxsqs_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `grxxsqs_uuid` varchar(32) NOT NULL DEFAULT '' COMMENT '个人信息授权书唯一id',
  `cust_id` varchar(32) NOT NULL DEFAULT '' COMMENT '客户id',
  `contact_type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '合同类型1 个人信息授权书',
  `credit_time` varchar(32) NOT NULL DEFAULT '' COMMENT '授信时间',
  `cust_id_no` varchar(20) NOT NULL DEFAULT '' COMMENT '身份证',
  `cust_name` varchar(15) NOT NULL DEFAULT '' COMMENT '客户姓名',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `cust_mobile` varchar(20) NOT NULL DEFAULT '' COMMENT '客户手机号',
  `params_bk1` varchar(10) NOT NULL DEFAULT '' COMMENT '备用字段1',
  `params_bk2` varchar(15) NOT NULL DEFAULT '' COMMENT '备用2',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`grxxsqs_id`),
  KEY `index_create_time` (`create_time`) USING BTREE,
  KEY `index_grxxsqs_uuid` (`grxxsqs_uuid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4458218 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `t_grxxsqs_recode`
-- ----------------------------
DROP TABLE IF EXISTS `t_grxxsqs_recode`;
CREATE TABLE `t_grxxsqs_recode` (
  `generate_recode_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `grxxsqs_uuid` varchar(32) NOT NULL COMMENT '个人信息授权书uuid',
  `file_name` varchar(64) NOT NULL DEFAULT '' COMMENT '文件名称',
  `file_url` varchar(255) NOT NULL DEFAULT '' COMMENT '文件保存路径',
  `file_type` varchar(32) NOT NULL DEFAULT '' COMMENT '文件类型',
  `cust_id` varchar(32) NOT NULL DEFAULT '' COMMENT '客户id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`generate_recode_id`),
  KEY `index_create_time` (`create_time`) USING BTREE,
  KEY `index_grxxsqs_uuid` (`grxxsqs_uuid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=481 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `t_syqrs_gener_status`
--  借据合同模块
-- ----------------------------
DROP TABLE IF EXISTS `t_syqrs_gener_status`;
CREATE TABLE `t_syqrs_gener_status` (
  `gener_status_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `syqrs_uuid` varchar(40) NOT NULL DEFAULT '' COMMENT '使用授权书参数表uuid',
  `generate_status` tinyint(2) NOT NULL DEFAULT '2' COMMENT '状态0失败 1成功 2初始化  合同生成完之后更改此状态',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '合同生成完之后更改此时间',
  PRIMARY KEY (`gener_status_id`),
  KEY `index_syqrs_uuid` (`syqrs_uuid`) USING BTREE,
  KEY `index_create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `t_syqrs_params`
-- ----------------------------
DROP TABLE IF EXISTS `t_syqrs_params`;
CREATE TABLE `t_syqrs_params` (
  `syqrs_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `syqrs_uuid` varchar(40) NOT NULL DEFAULT '' COMMENT '唯一id',
  `cust_id` int(10) NOT NULL DEFAULT '0' COMMENT '客户ID',
  `cust_name` varchar(20) NOT NULL DEFAULT '' COMMENT '客户姓名',
  `cust_id_no` varchar(20) NOT NULL DEFAULT '' COMMENT '身份证',
  `cust_mobile` varchar(15) NOT NULL DEFAULT '0' COMMENT '手机号',
  `loan_amount` varchar(15) NOT NULL DEFAULT '' COMMENT '贷款金额',
  `consume_time` datetime NOT NULL DEFAULT '1990-06-26 00:00:00' COMMENT '消费时间',
  `final_repayment_date` varchar(30) NOT NULL DEFAULT '' COMMENT '最后还款时间 xxxx年x月x日',
  `repayment_account` varchar(32) NOT NULL DEFAULT '' COMMENT '还款账户 我们取最新的一张卡',
  `repayment_cust_name` varchar(15) NOT NULL DEFAULT '' COMMENT '持卡人姓名 按理跟客户姓名一样',
  `guarantee_fee_rate` varchar(12) NOT NULL DEFAULT '' COMMENT '担保费率',
  `gurantee_fee` varchar(10) NOT NULL DEFAULT '0' COMMENT '担保费',
  `overdue_payment` varchar(12) NOT NULL DEFAULT '' COMMENT '滞纳金',
  `repayment_date` tinyint(2) NOT NULL DEFAULT '0' COMMENT '还款日 x日',
  `credit_side` tinyint(2) NOT NULL DEFAULT '1' COMMENT '贷款方1 成都维仕小额贷款有限公司 见字典表',
  `guarantor` tinyint(2) NOT NULL DEFAULT '1' COMMENT '担保方1 维仕担保有限公司 见字典表',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`syqrs_id`),
  KEY `index_syqrs_uuid` (`syqrs_uuid`) USING BTREE,
  KEY `index_create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `t_syqrs_recode`
-- ----------------------------
DROP TABLE IF EXISTS `t_syqrs_recode`;
CREATE TABLE `t_syqrs_recode` (
  `generate_recode_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `syqrs_uuid` varchar(40) NOT NULL COMMENT '使用确认书uuid',
  `file_name` varchar(64) NOT NULL DEFAULT '' COMMENT '文件名称',
  `file_url` varchar(255) NOT NULL DEFAULT '' COMMENT '文件保存路径',
  `file_type` varchar(32) NOT NULL DEFAULT '' COMMENT '文件类型',
  `cust_id_no` varchar(20) NOT NULL COMMENT '身份证号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`generate_recode_id`),
  KEY `index_create_time` (`create_time`) USING BTREE,
  KEY `index_syqrs_uuid` (`syqrs_uuid`) USING BTREE,
  KEY `index_cust_id_no` (`cust_id_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `t_sequence`
--  模拟序列，用以生成合同号
-- ----------------------------
DROP TABLE IF EXISTS `t_sequence`;
CREATE TABLE `t_sequence` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(10) NOT NULL COMMENT '序列号名称，唯一',
  `current_value` int(6) NOT NULL COMMENT '当前值',
  `increment` tinyint(1) NOT NULL COMMENT '增幅 - 每次增加的值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='模拟Sequence，用于记录序列号';

-- ----------------------------
-- Records of t_sequence
-- ----------------------------
INSERT INTO `t_sequence` VALUES ('2', 'CONTRACTNO', '376', '1');
INSERT INTO `t_sequence` VALUES ('3', 'TYTCBT', '93', '1');


-- MySQL函数 脚本

/*==============================================================*/
/* Index: 赋值                                                  */
/*==============================================================*/
DROP FUNCTION IF EXISTS setval;
CREATE FUNCTION setval (seq_name VARCHAR(50), `value` INTEGER)
  RETURNS INTEGER
CONTAINS SQL
  BEGIN
    UPDATE t_sequence
    SET  current_value = `value`
    WHERE name = seq_name;
    RETURN currval(seq_name);
  END
;
/*==============================================================*/
/* Index: 当前值                                                */
/*==============================================================*/
DROP FUNCTION IF EXISTS currval;
CREATE FUNCTION currval (seq_name VARCHAR(50))
  RETURNS INTEGER
CONTAINS SQL
  BEGIN
    DECLARE `value` INTEGER;
    SET value = 0;
    SELECT current_value INTO `value`
    FROM t_sequence
    WHERE name = seq_name;
    RETURN `value`;
  END
;
/*==============================================================*/
/* Index: 下一个值                                                */
/*==============================================================*/
DROP FUNCTION IF EXISTS nextval;
CREATE FUNCTION nextval (seq_name VARCHAR(50))
  RETURNS INTEGER
CONTAINS SQL
  BEGIN
    UPDATE t_sequence
    SET current_value = current_value + increment
    WHERE name = seq_name;
    RETURN currval(seq_name);
  END
;
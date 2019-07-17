/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : webside

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2016-12-06 18:25:23
*/

SET FOREIGN_KEY_CHECKS=0;


-- ----------------------------
-- Table structure for tb_login_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_login_info`;
CREATE TABLE `tb_login_info` (
  `l_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) DEFAULT NULL,
  `u_account_name` varchar(255) DEFAULT NULL,
  `l_ip` varchar(255) DEFAULT NULL,
  `l_region` varchar(255) DEFAULT NULL,
  `l_province` varchar(255) DEFAULT NULL,
  `l_city` varchar(255) DEFAULT NULL,
  `l_login_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`l_id`)
) ENGINE=InnoDB AUTO_INCREMENT=378 DEFAULT CHARSET=utf8;



-- ----------------------------
-- Table structure for tb_log_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_log_info`;
CREATE TABLE `tb_log_info` (
  `l_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `u_id` bigint(20) NOT NULL,
  `l_account_name` varchar(100) DEFAULT NULL,
  `l_operation` varchar(255) DEFAULT NULL COMMENT '用户所做的操作',
  `l_content` varchar(1000) DEFAULT NULL COMMENT '日志内容',
  `l_create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`l_id`)
) ENGINE=InnoDB AUTO_INCREMENT=161 DEFAULT CHARSET=utf8;



-- ----------------------------
-- Table structure for tb_resource
-- ----------------------------
DROP TABLE IF EXISTS `tb_resource`;
CREATE TABLE `tb_resource` (
  `s_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '资源id',
  `s_parent_id` int(11) DEFAULT NULL COMMENT '资源父id',
  `s_name` varchar(100) NOT NULL COMMENT '资源名称',
  `s_source_key` varchar(100) NOT NULL COMMENT '资源唯一标识',
  `s_type` int(11) NOT NULL COMMENT '资源类型,0:目录;1:菜单;2:按钮',
  `s_source_url` varchar(500) DEFAULT NULL COMMENT '资源url',
  `s_level` int(11) DEFAULT NULL COMMENT '层级',
  `s_icon` varchar(100) DEFAULT '' COMMENT '图标',
  `s_is_hide` int(11) DEFAULT '0' COMMENT '是否隐藏',
  `s_description` varchar(100) DEFAULT NULL COMMENT '描述',
  `s_create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `s_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`s_id`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8 COMMENT='资源表';

-- ----------------------------
-- Records of tb_resource
-- ----------------------------
INSERT INTO `tb_resource` VALUES ('1', null, '控制台', 'desktop', '0', '/welcome.jsp', '1', 'fa fa-tachometer', '0', '控制台', '2016-01-12 17:08:55', '2016-02-25 14:07:48');
INSERT INTO `tb_resource` VALUES ('2', null, '系统基础管理', 'system', '0', '', '1', 'fa fa-list', '0', '系统基础管理', '2016-01-05 12:11:12', '2016-02-25 14:07:48');
INSERT INTO `tb_resource` VALUES ('3', '2', '用户管理', 'system:user', '0', '/user/listUI.html', '2', '', '0', '用户管理', '2016-01-08 12:37:10', '2016-06-30 20:53:36');
INSERT INTO `tb_resource` VALUES ('4', '2', '角色管理', 'system:role', '0', '/role/listUI.html', '2', '', '0', '角色管理', '2016-01-11 22:51:07', '2016-06-30 20:53:38');
INSERT INTO `tb_resource` VALUES ('5', '2', '资源管理', 'system:resource', '0', '/resource/listUI.html', '2', '', '0', '资源管理', '2016-01-11 22:51:55', '2016-06-30 20:53:40');
INSERT INTO `tb_resource` VALUES ('6', null, '系统监控管理', 'monitor', '0', '', '1', 'fa fa-pencil-square-o', '0', '系统监控管理', '2016-01-05 12:11:12', '2016-02-25 14:07:48');
INSERT INTO `tb_resource` VALUES ('7', '6', 'Sirona监控', 'monitor:sirona', '0', '/sirona', '2', '', '0', 'Sirona监控', '2016-01-05 12:11:12', '2016-06-30 20:53:41');
INSERT INTO `tb_resource` VALUES ('8', '6', 'Druid监控', 'monitor:druid', '0', '/druid', '2', '', '0', 'Druid监控', '2016-01-11 22:45:27', '2016-06-30 20:53:44');
INSERT INTO `tb_resource` VALUES ('9', null, '日志信息管理', 'logininfo', '0', '', '1', 'fa fa-tag', '0', '日志信息管理', '2016-01-11 22:46:39', '2016-02-25 14:07:48');
INSERT INTO `tb_resource` VALUES ('10', '9', '用户登录信息', 'logininfo:userLogin', '0', '/loginInfo/listUI.html', '2', '', '0', '用户登录信息', '2016-01-11 22:47:41', '2016-02-25 14:07:48');
INSERT INTO `tb_resource` VALUES ('11', '3', '添加用户', 'user:add', '1', '/user/add.html', '3', null, '0', '添加用户', '2016-01-22 00:18:40', '2016-12-29 13:37:59');
INSERT INTO `tb_resource` VALUES ('12', '3', '编辑用户', 'user:edit', '1', '/user/edit.html', '3', null, '0', '编辑用户', '2016-01-22 00:18:40', '2016-12-29 13:37:49');
INSERT INTO `tb_resource` VALUES ('13', '3', '删除用户', 'user:deleteBatch', '1', '/user/deleteBatch.html', null, null, '0', '删除用户', '2016-02-05 15:26:32', '2016-12-29 13:38:38');
INSERT INTO `tb_resource` VALUES ('14', '3', '重置密码', 'user:resetPassword', '1', '/user/resetPassword.html', null, null, '0', '重置密码', '2016-02-27 23:55:13', '2016-06-30 20:54:45');
INSERT INTO `tb_resource` VALUES ('15', '4', '添加角色', 'role:add', '1', '/role/add.html', null, null, '0', '添加', '2016-02-27 23:56:52', '2016-12-29 13:37:34');
INSERT INTO `tb_resource` VALUES ('16', '4', '编辑角色', 'role:edit', '1', '/role/edit.html', null, null, '0', '编辑', '2016-02-27 23:57:35', '2016-12-29 13:37:22');
INSERT INTO `tb_resource` VALUES ('17', '4', '删除角色', 'role:deleteBatch', '1', '/role/deleteBatch.html', null, null, '0', '删除角色', '2016-02-27 23:58:02', '2017-01-03 17:25:42');
INSERT INTO `tb_resource` VALUES ('18', '4', '分配权限', 'role:permission', '1', '/role/permission.html', null, null, '0', '分配权限', '2016-02-27 23:59:20', '2016-06-30 20:54:48');
INSERT INTO `tb_resource` VALUES ('19', '5', '添加资源', 'resource:add', '1', '/resource/add.html', null, null, '0', '添加', '2016-02-28 00:01:15', '2016-12-29 13:37:07');
INSERT INTO `tb_resource` VALUES ('20', '5', '编辑资源', 'resource:edit', '1', '/resource/edit.html', null, null, '0', '编辑', '2016-02-28 00:02:01', '2017-01-03 17:18:22');
INSERT INTO `tb_resource` VALUES ('21', '5', '删除资源', 'resource:deleteBatch', '1', '/resource/deleteBatch.html', null, null, '0', '删除', '2016-02-28 00:03:03', '2016-12-29 13:40:13');
INSERT INTO `tb_resource` VALUES ('22', '9', '用户操作信息', 'loginfo:userOperation', '0', '/logInfo/listUI.html', null, 'fa  fa-sticky-note-o', '0', '用户操作信息', '2016-03-08 22:00:36', '2016-07-01 16:14:54');
INSERT INTO `tb_resource` VALUES ('24', null, '合作开发组菜单', 'menu', '0', '', null, 'fa  fa-asterisk', '0', '一级菜单', '2016-06-30 19:51:57', '2017-07-25 13:34:03');
INSERT INTO `tb_resource` VALUES ('25', '24', '融祥菜单', 'menu:baidu', '0', '', null, '', '0', '二级菜单', '2016-06-30 19:54:01', '2017-07-25 13:28:36');
INSERT INTO `tb_resource` VALUES ('26', '24', '三级菜单', 'menu:baidu:menu2', '0', '', null, 'fa  fa-cloud-download', '1', '三级菜单', '2016-06-30 19:54:29', '2017-08-02 16:00:19');
INSERT INTO `tb_resource` VALUES ('27', '26', '四级菜单', 'menu:baidu:menu2:menu3', '0', '/logInfo/listUI.html', null, 'fa  fa-line-chart', '1', '四级菜单', '2016-06-30 19:55:53', '2017-07-27 09:35:23');
INSERT INTO `tb_resource` VALUES ('28', '25', '三级菜单1', 'menu:baidu:page21', '0', '/resource/listUI.html', null, 'fa  fa-balance-scale', '1', '三级菜单1', '2016-06-30 20:16:44', '2017-07-27 09:35:25');
INSERT INTO `tb_resource` VALUES ('29', '24', '融祥页面1', 'menu:baidu:page1', '0', '', null, 'fa  fa-comment', '0', '二级菜单1', '2016-07-01 15:41:44', '2017-07-25 14:22:46');
INSERT INTO `tb_resource` VALUES ('30', null, '计划任务管理', 'schedule', '0', '', null, 'fa  fa-list-ol', '0', '计划任务管理', '2016-07-17 01:09:27', '2016-07-20 16:01:08');
INSERT INTO `tb_resource` VALUES ('32', '38', '添加jobUI', 'schedule:addJobUI', '1', '/scheduleJob/addJobUI.html', null, null, '0', '添加任务', '2016-07-17 01:15:38', '2016-12-29 13:47:48');
INSERT INTO `tb_resource` VALUES ('34', '38', '删除job', 'schedule:deleteJob', '1', '/scheduleJob/deleteJob.html', null, null, '0', '删除任务', '2016-07-17 01:19:24', '2016-07-26 17:32:05');
INSERT INTO `tb_resource` VALUES ('35', '38', '执行job', 'schedule:executeJob', '1', '/scheduleJob/executeJob.html', null, null, '0', '立即执行一次', '2016-07-17 01:22:01', '2016-07-26 17:33:26');
INSERT INTO `tb_resource` VALUES ('36', '39', '暂停job', 'schedule:pauseJob', '1', '/scheduleJob/pauseJob.html', null, null, '0', '暂停任务', '2016-07-17 01:24:13', '2016-07-26 17:32:20');
INSERT INTO `tb_resource` VALUES ('37', '39', '恢复job', 'schedule:resumeJob', '1', '/scheduleJob/resumeJob.html', null, null, '0', '恢复任务', '2016-07-17 01:25:23', '2016-07-26 17:32:26');
INSERT INTO `tb_resource` VALUES ('38', '30', '计划中任务', 'schedule:planSchedule', '0', '/scheduleJob/planningJobListUI.html', null, 'fa  fa-hourglass', '0', '计划中任务', '2016-07-19 18:51:54', '2016-12-29 10:27:36');
INSERT INTO `tb_resource` VALUES ('39', '30', '运行中任务', 'schedule:runSchedule', '0', '/scheduleJob/runningJobListUI.html', null, 'fa  fa-hourglass-2', '0', '运行中任务', '2016-07-19 18:53:45', '2016-12-29 10:27:55');
INSERT INTO `tb_resource` VALUES ('40', '38', '触发器管理', 'schedule:trigger', '1', '/scheduleJob/jobTriggerListUI.html', null, null, '0', '查看触发器列表', '2016-07-21 21:19:57', '2016-12-29 10:34:05');
INSERT INTO `tb_resource` VALUES ('41', '40', '添加triggerUI', 'schedule:addTriggerUI', '1', '/scheduleJob/addTriggerUI.html', null, null, '0', '给当前job添加trigger页面', '2016-07-26 19:31:07', '2016-12-29 13:49:44');
INSERT INTO `tb_resource` VALUES ('42', '38', '暂停job', 'schedule:pauseJob', '1', '/scheduleJob/pauseJob.html', null, '', '0', '暂停job', '2016-07-26 19:32:15', '2016-07-26 19:32:15');
INSERT INTO `tb_resource` VALUES ('43', '38', '恢复job', 'schedule:resumeJob', '1', '/scheduleJob/resumeJob.html', null, '', '0', '恢复job', '2016-07-26 19:32:52', '2016-07-26 19:32:52');
INSERT INTO `tb_resource` VALUES ('44', '40', '编辑triggerUI', 'schedule:editTriggerUI', '1', '/scheduleJob/editTriggerUI.html', null, null, '0', '编辑trigger页面', '2016-07-26 19:34:44', '2016-12-29 14:04:06');
INSERT INTO `tb_resource` VALUES ('45', '40', '删除trigger', 'schedule:deleteTrigger', '1', '/scheduleJob/deleteTrigger.html', null, null, '0', '删除trigger', '2016-07-26 19:35:23', '2016-07-26 19:36:50');
INSERT INTO `tb_resource` VALUES ('46', '40', '暂停trigger', 'schedule:pauseTrigger', '1', '/scheduleJob/pauseTrigger.html', null, null, '0', '暂停trigger', '2016-07-26 19:36:37', '2017-01-03 17:50:15');
INSERT INTO `tb_resource` VALUES ('47', '40', '恢复trigger', 'schedule:resumeTrigger', '1', '/scheduleJob/resumeTrigger.html', null, '', '0', '恢复trigger', '2016-07-26 19:37:22', '2016-07-26 19:37:22');
INSERT INTO `tb_resource` VALUES ('48', '3', '锁定', 'user:lock', '1', '/user/lock.html', null, null, '0', '锁定用户账户', '2016-12-26 23:35:14', '2016-12-26 23:39:33');
INSERT INTO `tb_resource` VALUES ('49', '3', '解锁', 'user:unlock', '1', '/user/unlock.html', null, null, '0', '解锁账户', '2016-12-26 23:36:12', '2016-12-26 23:39:46');
INSERT INTO `tb_resource` VALUES ('50', '6', '在线用户', 'session', '0', '/session/listUI.html', null, 'fa  fa-heartbeat', '0', '在线用户', '2016-12-27 00:45:41', '2016-12-29 10:41:11');
INSERT INTO `tb_resource` VALUES ('51', '50', '踢出', 'session:kickout', '1', '/session/kickout.html', null, null, '0', '踢出在线用户', '2016-12-28 11:25:57', '2016-12-29 10:43:33');
INSERT INTO `tb_resource` VALUES ('52', '3', '用户列表', 'user:list', '1', '/user/list.html', null, null, '0', '用户列表', '2016-12-29 10:05:04', '2016-12-29 10:07:15');
INSERT INTO `tb_resource` VALUES ('53', '4', '角色列表', 'role:list', '1', '/role/list.html', null, 'fa  fa-reorder', '0', '角色列表', '2016-12-29 10:09:36', '2016-12-29 10:09:36');
INSERT INTO `tb_resource` VALUES ('54', '5', '资源列表', 'resource:list', '1', '/resource/list.html', null, 'fa  fa-reorder', '0', '资源列表', '2016-12-29 10:10:41', '2016-12-29 10:10:41');
INSERT INTO `tb_resource` VALUES ('55', '10', '登陆日志列表', 'loginInfo:list', '1', '/loginInfo/list.html', null, null, '0', '用户登陆列表', '2016-12-29 10:12:37', '2016-12-29 10:15:05');
INSERT INTO `tb_resource` VALUES ('56', '22', '操作日志列表', 'logInfo:list', '1', '/logInfo/list.html', null, 'fa  fa-bars', '0', '操作日志列表', '2016-12-29 10:14:50', '2016-12-29 10:14:50');
INSERT INTO `tb_resource` VALUES ('57', '38', '计划中任务列表', 'schedule:planScheduleList', '1', '/scheduleJob/planningJobList.html', null, 'fa  fa-bars', '0', '计划中任务列表', '2016-12-29 10:25:51', '2016-12-29 10:25:51');
INSERT INTO `tb_resource` VALUES ('58', '39', '运行中任务列表', 'schedule:runScheduleList', '1', '/scheduleJob/runningJobList.html', null, 'fa  fa-bars', '0', '运行中任务列表', '2016-12-29 10:29:46', '2016-12-29 10:29:46');
INSERT INTO `tb_resource` VALUES ('59', '40', '触发器列表', 'schedule:triggerList', '1', '/scheduleJob/jobTriggerList.html', null, 'fa  fa-bars', '0', '触发器列表', '2016-12-29 10:33:32', '2016-12-29 10:33:32');
INSERT INTO `tb_resource` VALUES ('60', '50', '在线用户列表', 'session:list', '1', '/session/list.html ', null, 'fa  fa-bars', '0', '在线用户列表', '2016-12-29 10:41:49', '2016-12-29 10:41:49');
INSERT INTO `tb_resource` VALUES ('61', '3', '添加用户UI', 'user:addUI', '1', '/user/addUI.html', null, '', '0', '添加用户页面', '2016-12-29 13:41:07', '2016-12-29 13:41:07');
INSERT INTO `tb_resource` VALUES ('62', '4', '添加角色UI', 'role:addUI', '1', '/role/addUI.html', null, '', '0', '添加角色页面', '2016-12-29 13:44:07', '2016-12-29 13:44:07');
INSERT INTO `tb_resource` VALUES ('63', '5', '添加资源UI', 'resource:addUI', '1', '/resource/addUI.html', null, '', '0', '添加资源页面', '2016-12-29 13:44:50', '2016-12-29 13:44:50');
INSERT INTO `tb_resource` VALUES ('64', '38', '添加job', 'schedule:addJob', '1', '/scheduleJob/addJob.html', null, '', '0', '添加job', '2016-12-29 13:48:19', '2016-12-29 13:48:19');
INSERT INTO `tb_resource` VALUES ('65', '40', '添加trigger', 'schedule:addTrigger', '1', '/scheduleJob/addTrigger.html', null, '', '0', '添加trigger', '2016-12-29 13:49:34', '2016-12-29 13:49:34');
INSERT INTO `tb_resource` VALUES ('66', '4', '分配权限UI', 'role:permissionUI', '1', '/role/permissionUI.html', null, '', '0', '分配权限页面', '2016-12-29 13:54:44', '2016-12-29 13:54:44');
INSERT INTO `tb_resource` VALUES ('67', '3', '编辑用户UI', 'user:editUI', '1', '/user/editUI.html', null, '', '0', '编辑用户页面', '2016-12-29 13:56:06', '2016-12-29 13:56:06');
INSERT INTO `tb_resource` VALUES ('68', '4', '编辑角色UI', 'role:editUI', '1', '/role/editUI.html', null, '', '0', '编辑角色页面', '2016-12-29 13:57:03', '2016-12-29 13:57:03');
INSERT INTO `tb_resource` VALUES ('69', '5', '编辑资源UI', 'resource:editUI', '1', '/resource/editUI.html', null, '', '0', '编辑资源页面', '2016-12-29 13:57:54', '2017-01-03 17:17:58');
INSERT INTO `tb_resource` VALUES ('70', '40', '编辑trigger', 'schedule:editTrigger', '1', '/scheduleJob/editTrigger.html', null, '', '0', '编辑trigger', '2016-12-29 14:03:47', '2016-12-29 14:03:47');
INSERT INTO `tb_resource` VALUES ('71', '50', '在线用户详情', 'session:info', '1', '/session/info.html', null, '', '0', '在线用户详情', '2016-12-30 17:49:32', '2016-12-30 17:49:32');
INSERT INTO `tb_resource` VALUES ('72', '3', '个人信息', 'user:infoUI', '1', '/user/infoUI.html', null, '', '0', '个人信息详页', '2017-01-03 18:03:06', '2017-01-03 18:03:06');
INSERT INTO `tb_resource` VALUES ('73', '3', '个人信息编辑', 'user:info', '1', '/user/info.html', null, '', '0', '个人信息编辑', '2017-01-03 18:03:51', '2017-01-03 18:03:51');
INSERT INTO `tb_resource` VALUES ('74', '3', '修改密码UI', 'user:passwork', '1', '/user/passwordUI.html', null, '', '0', '修改个人密码UI', '2017-01-03 18:05:13', '2017-01-03 18:05:13');
INSERT INTO `tb_resource` VALUES ('75', '3', '修改个人密码', 'user:password', '1', '/user/password.html', null, '', '0', '修改个人密码', '2017-01-03 18:05:58', '2017-01-03 18:05:58');
INSERT INTO `tb_resource` VALUES ('76', '29', '列表', 'test', '1', '/resource/listGrid.html', null, '', '0', '列表', '2017-01-12 15:04:47', '2017-01-12 15:04:47');
INSERT INTO `tb_resource` VALUES ('89', '2', '资源管理(TREEGRID)', 'system:resource', '0', '/resource/listGridUI.html', null, 'fa  fa-indent', '0', '资源管理(TREEGRID)', '2017-01-17 15:17:08', '2017-01-17 15:19:35');
INSERT INTO `tb_resource` VALUES ('90', '89', '资源列表(TREEGRID)', 'resource:listGrid', '1', '/resource/listGrid.html', null, '', '0', '资源列表(TREEGRID)', '2017-01-17 15:21:21', '2017-01-17 15:21:21');
INSERT INTO `tb_resource` VALUES ('115', '102', '数据源rx', 'rx:listUI', '0', '/rx/listUI.html', null, '', '0', 'testrx', '2017-07-17 11:38:26', '2017-08-02 16:03:24');
INSERT INTO `tb_resource` VALUES ('116', '115', 'rx订单list', 'rx:list', '1', '/rx/list.html', null, null, '0', 'rx订单list', '2017-07-17 11:46:50', '2017-07-17 14:00:19');
INSERT INTO `tb_resource` VALUES ('117', '102', 'rx订单冻结额度', 'rx:frozenUI', '0', '/rx/frozenUI.html', null, '', '0', 'rx订单冻结额度', '2017-07-17 11:47:48', '2017-08-02 16:03:30');
INSERT INTO `tb_resource` VALUES ('120', '117', '冻结额度list', 'rx:frozen', '1', '/rx/frozen.html', null, null, '0', '冻结额度list', '2017-07-17 19:20:30', '2017-07-18 14:48:59');
INSERT INTO `tb_resource` VALUES ('123', '117', '解冻按钮', 'rx:unfrozen', '1', '/rx/unfrozen.html', null, null, '0', '结清订单', '2017-07-18 14:47:45', '2017-07-18 15:54:10');
INSERT INTO `tb_resource` VALUES ('124', '117', '是否清贷贷后接口ajax', 'menu:rx:isClearLoan', '1', '/rx/isClearLoan.html', '3', '', '0', '是否清贷ajax按钮调用接口', '2017-07-27 14:42:19', '2017-07-27 15:03:14');
INSERT INTO `tb_resource` VALUES ('125', '117', '拿取vbsId接口ajax', 'menu:rx:queryVbsId', '1', '/rx/queryVbsId.html', '3', '', '0', '通过creditId、orderId拿取vbsId接口ajax', '2017-07-28 11:07:41', '2017-07-28 11:08:57');
INSERT INTO `tb_resource` VALUES ('301', '25', '融祥订单查询', 'menu:baidu:indent', '0', '/baidu/indent/indentDataUI.jsp', null, '', '0', '查询及跳转详情', '2017-07-26 09:44:16', '2017-07-26 10:17:36');
INSERT INTO `tb_resource` VALUES ('302', '25', '异常数据查询', 'baidu:exceptionData', '0', '/baidu/loanandexception/exceptionData.html', null, '', '0', '异常数据查询', '2017-07-25 14:32:17', '2017-07-26 11:44:01');
INSERT INTO `tb_resource` VALUES ('303', '302', '异常数据的查询', 'baidu:exceptionDataList', '1', '/baidu/loanandexception/exceptionDataList.html', null, '', '0', '异常数据的查询', '2017-07-25 14:34:32', '2017-07-26 11:45:35');
INSERT INTO `tb_resource` VALUES ('304', '302', '异常数据的详细', 'baidu:exceptionDataDetail', '1', '/baidu/loanandexception/exceptionDataDetail.html', null, '', '0', '异常数据的详细', '2017-07-25 14:35:44', '2017-07-26 11:45:25');
INSERT INTO `tb_resource` VALUES ('305', '302', '更新解决状态', 'baidu:updateExceptionData', '1', '/baidu/loanandexception/updateExceptionData.html', null, '', '0', '更新解决状态', '2017-07-25 14:36:44', '2017-07-26 11:45:21');
INSERT INTO `tb_resource` VALUES ('306', '25', '放款查询', 'baidu:loanData', '0', '/baidu/loanandexception/loanData.html', null, '', '0', '放款查询', '2017-07-25 14:38:02', '2017-07-26 11:44:50');
INSERT INTO `tb_resource` VALUES ('307', '306', '放款信息查询', 'baidu:loanDataList', '1', '/baidu/loanandexception/loanDataList.html', null, '', '0', '放款信息查询', '2017-07-25 14:38:59', '2017-07-26 11:45:42');
INSERT INTO `tb_resource` VALUES ('308', '306', '解约和重新放款', 'baidu:submitLoan', '1', '/baidu/loanandexception/submitLoan.html', null, '', '0', '解约和重新放款', '2017-07-25 14:40:15', '2017-07-26 11:45:51');
INSERT INTO `tb_resource` VALUES ('351', '25', '授信', 'baidu:credit', '0', '/baidu/credit/credit.html', null, '', '0', '授信', '2017-07-25 11:51:22', '2017-07-26 11:47:12');
INSERT INTO `tb_resource` VALUES ('352', '351', '查询授信', 'baidu:cashListButton', '1', '/baidu/credit/creditList.html', null, null, '0', '授信查询', '2017-07-25 11:52:22', '2017-07-26 11:47:23');
INSERT INTO `tb_resource` VALUES ('353', '351', '查看授信详情', 'baidu:credidfg', '1', '/baidu/credit/creditDetail.html', null, null, '0', '查看授信详情', '2017-07-25 13:10:22', '2017-07-26 11:47:26');
INSERT INTO `tb_resource` VALUES ('354', '25', '提现', 'baidu:cashList', '0', '/baidu/cash/cash.html', null, '', '0', '提现', '2017-07-25 13:04:05', '2017-07-26 11:48:32');
INSERT INTO `tb_resource` VALUES ('355', '354', '提现查询', 'baidu:cashListButton', '1', '/baidu/cash/cashList.html', null, null, '0', '提现查询', '2017-07-25 13:04:44', '2017-07-26 11:48:45');
INSERT INTO `tb_resource` VALUES ('356', '354', '提现查看拒绝原因', 'baidu:cashDetail', '1', '/baidu/cash/cashDetail.html', null, null, '0', '查看拒绝原因', '2017-07-25 15:47:29', '2017-07-26 11:49:18');
INSERT INTO `tb_resource` VALUES ('398', '399', '融祥订单list', 'menu:baidu:indentData', '1', '/baidu/indent/data.html', null, '', '0', '融祥订单查询list', '2017-07-31 10:45:39', '2017-07-31 10:46:36');
INSERT INTO `tb_resource` VALUES ('399', '25', '融祥订单查询', 'menu:baidu:indent', '0', '/baidu/indent/dataUI.html', null, '', '0', '融祥订单查询（暂时不放账单状态）', '2017-07-31 10:42:19', '2017-07-31 10:46:20');
INSERT INTO `tb_resource` VALUES ('502', '24', '融祥汽车租赁菜单', 'menu:jd', '0', '', null, '', '0', '融祥汽车租赁菜单', '2017-07-27 14:37:49', '2017-07-27 14:38:05');
INSERT INTO `tb_resource` VALUES ('504', '502', '放款状态查询', 'menu:jd:queryLoanStatus', '0', '/jd/loan/queryLoanStatus.html', null, '', '0', '融祥汽车租赁放宽状态查询', '2017-07-27 14:51:12', '2017-08-01 09:49:09');
INSERT INTO `tb_resource` VALUES ('505', '504', '查询订单放款状态', 'menu:jd:queryLoanStatus:button', '1', '/jd/loan/list.html', null, null, '0', '查询订单放款状态权限', '2017-07-28 16:43:30', '2017-08-01 09:48:20');
INSERT INTO `tb_resource` VALUES ('506', '306', '再次放款', 'menu:againLoan', '1', '/jd/loan/againLoan.html', null, '', '0', '再次放款权限', '2017-08-01 16:06:23', '2017-08-01 16:06:51');
INSERT INTO `tb_resource` VALUES ('507', '504', '融祥汽车租赁订单解约', 'menu:jd:queryLoanStatus:surrender', '1', '/jd/loan/surrender.html', null, '', '0', '融祥汽车租赁订单解约', '2017-08-02 11:33:39', '2017-08-02 11:34:08');

-- ----------------------------
-- Table structure for tb_resources_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_resources_role`;
CREATE TABLE `tb_resources_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `s_id` int(11) DEFAULT NULL COMMENT '资源id',
  `r_id` int(11) DEFAULT NULL COMMENT '角色id',
  `t_create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `FK_r_resource_role` (`s_id`),
  KEY `FK_r_role_resource` (`r_id`),
#   CONSTRAINT `FK_r_resource_role` FOREIGN KEY (`s_id`) REFERENCES `tb_resource` (`s_id`),
  CONSTRAINT `FK_r_role_resource` FOREIGN KEY (`r_id`) REFERENCES `tb_role` (`r_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2300 DEFAULT CHARSET=utf8 COMMENT='角色权限映射表';

-- ----------------------------
-- Records of tb_resources_role
-- ----------------------------
INSERT INTO `tb_resources_role` VALUES ('3412', '3', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3413', '2', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3414', '4', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3415', '5', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3416', '89', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3417', '6', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3418', '50', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3419', '10', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3420', '9', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3421', '22', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3422', '302', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3423', '25', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3424', '24', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3425', '306', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3426', '351', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3427', '354', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3428', '399', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3429', '504', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3430', '502', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3431', '38', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3432', '30', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3433', '40', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3434', '39', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3435', '1', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3436', '11', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3437', '12', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3438', '13', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3439', '14', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3440', '48', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3441', '49', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3442', '52', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3443', '61', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3444', '67', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3445', '72', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3446', '73', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3447', '74', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3448', '75', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3449', '15', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3450', '16', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3451', '17', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3452', '18', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3453', '53', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3454', '62', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3455', '66', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3456', '68', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3457', '19', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3458', '20', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3459', '21', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3460', '54', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3461', '63', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3462', '69', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3464', '90', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3465', '7', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3466', '8', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3467', '51', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3468', '60', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3469', '71', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3470', '55', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3471', '56', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3472', '303', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3473', '304', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3474', '305', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3475', '307', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3476', '308', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3477', '506', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3478', '352', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3479', '353', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3480', '355', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3481', '356', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3483', '398', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3484', '505', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3485', '507', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3486', '32', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3487', '34', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3488', '35', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3489', '41', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3490', '44', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3491', '45', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3492', '46', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3493', '47', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3494', '59', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3495', '65', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3496', '70', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3497', '42', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3498', '43', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3499', '57', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3500', '64', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3501', '36', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3502', '37', '1', '2017-08-02 15:59:28');
INSERT INTO `tb_resources_role` VALUES ('3503', '58', '1', '2017-08-02 15:59:28');



-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `r_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `r_name` varchar(50) NOT NULL COMMENT '角色名称',
  `r_key` varchar(50) NOT NULL COMMENT '角色key',
  `r_status` int(11) DEFAULT '0' COMMENT '角色状态,0：正常；1：删除',
  `r_description` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `r_create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `r_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`r_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('1', '超级管理员', 'administrator', '0', '超级管理员', '2016-01-05 12:07:42', '2016-10-06 04:00:55');
INSERT INTO `tb_role` VALUES ('2', '管理员', 'admin', '0', '管理员', '2016-01-05 12:07:42', '2016-02-27 22:30:22');
INSERT INTO `tb_role` VALUES ('3', '普通用户', 'user', '0', '普通用户而已', '2016-02-28 17:09:40', '2017-01-09 10:02:02');
INSERT INTO `tb_role` VALUES ('4', '华东部经理', 'faq', '0', '华东区域管理', '2016-07-05 19:07:45', '2017-06-30 09:47:30');
INSERT INTO `tb_role` VALUES ('5', '运营主管', '海艳', '0', '数据管理查询', '2017-06-30 09:47:05', '2017-07-21 09:56:12');
INSERT INTO `tb_role` VALUES ('6', '运营', 'operater', '0', '查询操作（暂时）', '2017-07-03 10:27:04', '2017-07-03 10:27:04');
INSERT INTO `tb_role` VALUES ('7', '产品dog', 'dog', '0', '产品dog、产品dog、产品dog', '2017-07-05 10:39:38', '2017-07-05 10:39:38');
INSERT INTO `tb_role` VALUES ('8', '贝贝', '贝贝', '0', '贝贝', '2017-07-05 13:18:37', '2017-07-05 13:18:37');
INSERT INTO `tb_role` VALUES ('9', '测试test', 'TestingGuy', '0', '测试人员', '2017-07-19 09:05:31', '2017-07-19 09:05:31');

-- ----------------------------
-- Table structure for tb_role_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_user`;
CREATE TABLE `tb_role_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `r_id` int(11) DEFAULT NULL COMMENT '角色id',
  `u_id` int(11) DEFAULT NULL COMMENT '用户id',
  `t_create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `FK_r_role_user` (`r_id`),
  KEY `FK_r_user_role` (`u_id`),
  CONSTRAINT `FK_r_role_user` FOREIGN KEY (`r_id`) REFERENCES `tb_role` (`r_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='用户角色映射表';

-- ----------------------------
-- Records of tb_role_user
-- ----------------------------
INSERT INTO `tb_role_user` VALUES ('4', '1', '9', null);
INSERT INTO `tb_role_user` VALUES ('19', '1', '26', '2017-06-29 14:16:16');
INSERT INTO `tb_role_user` VALUES ('20', '1', '4', '2017-06-29 15:34:26');
INSERT INTO `tb_role_user` VALUES ('21', '1', '5', '2017-06-29 15:34:26');
INSERT INTO `tb_role_user` VALUES ('23', '8', '30', '2017-06-29 17:45:30');
INSERT INTO `tb_role_user` VALUES ('24', '3', '31', '2017-06-30 11:57:45');
INSERT INTO `tb_role_user` VALUES ('25', '5', '32', '2017-07-03 10:52:00');
INSERT INTO `tb_role_user` VALUES ('26', '6', '33', '2017-07-03 10:53:30');
INSERT INTO `tb_role_user` VALUES ('27', '6', '34', '2017-07-03 10:55:03');
INSERT INTO `tb_role_user` VALUES ('28', '6', '35', '2017-07-03 10:56:03');
INSERT INTO `tb_role_user` VALUES ('29', '6', '36', '2017-07-03 10:57:06');
INSERT INTO `tb_role_user` VALUES ('30', '6', '37', '2017-07-03 10:57:46');
INSERT INTO `tb_role_user` VALUES ('31', '6', '38', '2017-07-03 10:59:00');
INSERT INTO `tb_role_user` VALUES ('32', '6', '39', '2017-07-03 11:00:30');
INSERT INTO `tb_role_user` VALUES ('33', '4', '40', '2017-07-04 12:59:12');
INSERT INTO `tb_role_user` VALUES ('34', '7', '41', '2017-07-05 10:39:17');
INSERT INTO `tb_role_user` VALUES ('35', '3', '42', '2017-07-10 13:59:56');
INSERT INTO `tb_role_user` VALUES ('36', '9', '44', '2017-07-19 09:06:57');
INSERT INTO `tb_role_user` VALUES ('37', '9', '45', '2017-07-27 09:32:55');
INSERT INTO `tb_role_user` VALUES ('38', '9', '46', '2017-07-28 11:53:57');
INSERT INTO `tb_role_user` VALUES ('39', '4', '47', '2017-08-02 16:08:01');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `u_channel_id` INT(11) NOT NULL COMMENT '渠道表id',
  `u_name` varchar(100) NOT NULL COMMENT '真实姓名',
  `u_account_name` varchar(100) NOT NULL COMMENT '账户名称',
  `u_password` varchar(100) NOT NULL COMMENT '用户密码',
  `u_delete_status` int(11) DEFAULT '0' COMMENT '逻辑删除状态',
  `u_locked` int(11) DEFAULT '0' COMMENT '是否锁定',
  `u_description` varchar(200) DEFAULT NULL COMMENT '用户描述',
  `u_credentials_salt` varchar(500) NOT NULL COMMENT '加密盐',
  `u_creator_name` varchar(100) NOT NULL COMMENT '创建者',
  `u_create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `u_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `u_account_name_unique` (`u_account_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='用户账户表';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('5', '11', '450499175@qq.com', '450499175@qq.com', '2Oxv92jYvRpyicKPaUquig==', '0', '0', 'xia', '4463205abfd060eb28c3edad2bcd3cd2', '450499175@qq.com', '2017-06-29 15:34:26', '2017-07-06 18:32:38');
INSERT INTO `tb_user` VALUES ('9', '11', 'admin@ect888.com', 'admin@ect888.com', 'cpEgsh6kzhTlWM51DpQ6yQ==', '0', '0', '123123', '4463205abfd060eb28c3edad2bcd3cd1', '1234567890@qq.com', '2017-06-29 15:34:26', '2017-07-11 15:57:06');
INSERT INTO `tb_user` VALUES ('26', '11', 'ww', '1234567890@qq.com', '2Oxv92jYvRpyicKPaUquig==', '0', '0', 'superadmin', '13040593ecb6eaac608cfe167baba39b', '', '2017-06-29 14:16:17', '2017-07-03 17:23:18');
INSERT INTO `tb_user` VALUES ('30', '0', '贝贝', 'caobei@vcredit.com', 'qW1T55ww8X+eqVla/ksTzQ==', '0', '0', '123123', 'afcec8bcb1f1e4301dd32cb0fd02a525', 'admin@ect888.com', '2017-06-29 17:45:30', '2017-07-05 13:19:48');
INSERT INTO `tb_user` VALUES ('31', '0', '何发强', 'hefaqiang@vcredit.com', '3M1y9k8E+POzzKjQ1J/7Mw==', '0', '0', '123123', '20fbb27546a0da0bd7aed05732563514', 'admin@ect888.com', '2017-06-30 11:57:45', '2017-06-30 11:57:45');
INSERT INTO `tb_user` VALUES ('32', '0', '赵海艳', 'zhaohaiyan@vcredit.com', 'kVDr2dZa5UT0FFZMyB9vHg==', '0', '0', '初始密码123123', '48e3b7ff7edb37dcb378808a971f9198', '1234567890@qq.com', '2017-07-03 10:52:00', '2017-07-21 10:10:17');
INSERT INTO `tb_user` VALUES ('33', '0', '褚振文', 'chuzhenwen@vcredit.com', '8hAHyph5jIedGsH/N/jZQw==', '0', '0', '初始密码123123', '2b82f6814663ddff040fedbbd14bb8b8', '1234567890@qq.com', '2017-07-03 10:53:30', '2017-07-03 10:53:30');
INSERT INTO `tb_user` VALUES ('34', '0', '崔安', 'cuian@vcredit.com', 'ifxH9+45pBKPd62Imq5OyA==', '0', '0', '初始密码123123', '620145c7246d0c007d6e412d1cb2564e', '1234567890@qq.com', '2017-07-03 10:55:03', '2017-07-03 10:55:03');
INSERT INTO `tb_user` VALUES ('35', '0', '郝思杰', 'haosijie@vcredit.com', 'W/c1Qb0F1M1meYvIL2kBhg==', '0', '0', '初始密码123123', 'a3c64407f47427928abafef8f982cfcf', '1234567890@qq.com', '2017-07-03 10:56:03', '2017-07-03 10:56:03');
INSERT INTO `tb_user` VALUES ('36', '0', '太子', 'jiwendi01@vcredit.com', 'vQ093iCGbvItiWcCbPqO6w==', '0', '0', '初始密码123123', '8dab2310e074c619d233c312ba699faa', '1234567890@qq.com', '2017-07-03 10:57:06', '2017-07-03 10:57:06');
INSERT INTO `tb_user` VALUES ('37', '0', '吴文迪', 'wuwendi@vcredit.com', 'FmKeaNQmJ//kP9q9WDx9Sg==', '0', '0', '初始密码123123', '86e15823e989e61f8a447d8c7547413f', '1234567890@qq.com', '2017-07-03 10:57:46', '2017-07-03 10:57:46');
INSERT INTO `tb_user` VALUES ('38', '0', '杨杰', 'yangjie05@vcredit.com', 'O0m9rvYYOUtlOWnl+iRV0A==', '0', '0', '初始密码123123', '74b339ee2bd08a37b22ae1df45a60c43', '1234567890@qq.com', '2017-07-03 10:59:01', '2017-07-03 10:59:00');
INSERT INTO `tb_user` VALUES ('39', '0', '聂琦', 'nieqi01@vcredit.com', 'VTFUeUn1RntzDZDaXR4kWw==', '0', '0', '初始密码123123', '5304c1e82d014b45c5cabf7d11e6d0c9', '1234567890@qq.com', '2017-07-03 11:00:31', '2017-07-03 11:00:30');
INSERT INTO `tb_user` VALUES ('40', '0', '叶俊杉', 'yejunshan@vcredit.com', 'DunVthLHlW+mtJOV3NuOyA==', '0', '0', '产品', 'b2b5a33857c4e23abb43d28864dd13ca', '1234567890@qq.com', '2017-07-04 12:59:13', '2017-07-04 12:59:12');
INSERT INTO `tb_user` VALUES ('41', '0', '吴茂敏', 'wumaomin@vcredit.com', 'Co4DvsKvm/SsB8e+s7smGg==', '0', '0', '产品', 'cd005693f386d0cbc447eb869d39107d', 'admin@ect888.com', '2017-07-05 10:39:18', '2017-07-06 17:00:58');
INSERT INTO `tb_user` VALUES ('42', '0', '王永建', 'wangyongjian@vcredit.com', '9gUusgsldsDNenhZdOY71Q==', '0', '0', '开发11', '19755d5a8d6e9c64ef797b855b18750f', '1234567890@qq.com', '2017-07-10 13:58:45', '2017-07-17 16:51:37');
INSERT INTO `tb_user` VALUES ('44', '0', '贾继祖', 'jiajizu@vcredit.com', 'fIW/N4t2w7PCA/zaUdnh4w==', '0', '0', '测试组贾继祖', '98f536036429ed6f0d0fcdf2fe53bc34', '1234567890@qq.com', '2017-07-19 09:06:57', '2017-07-19 09:06:57');
INSERT INTO `tb_user` VALUES ('45', '0', '朱郦', 'zhuli@vcredit.com', '+pD2A4BA74PL3tU9QAMabw==', '0', '0', '朱郦test', 'effd6cd53f6d36645b16b236a457b918', '1234567890@qq.com', '2017-07-27 09:32:56', '2017-07-27 09:32:55');
INSERT INTO `tb_user` VALUES ('46', '0', '黎祖山', 'lizushan@vcredit.com', 'le02ZWW27w0pH8oW94HPlQ==', '0', '0', 'test用户', '1598367dfc25bb4d1ff1ac33b38beaf1', '1234567890@qq.com', '2017-07-28 11:53:58', '2017-07-28 11:53:57');
INSERT INTO `tb_user` VALUES ('47', '0', '史栋舟', 'shidongzhou@vcredit.com', 'ushzL96EtLoWvD9w0ThqDQ==', '0', '0', '初始密码123123', '8348eb396b2037638034f523a75dab8f', '1234567890@qq.com', '2017-08-02 16:08:01', '2017-08-02 16:08:01');

-- ----------------------------
-- Table structure for tb_user_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_info`;
CREATE TABLE `tb_user_info` (
  `id` int(11) NOT NULL COMMENT '用户id',
  `u_sex` int(11) DEFAULT NULL COMMENT '性别',
  `u_birthday` date DEFAULT NULL COMMENT '出生日期',
  `u_telephone` varchar(20) DEFAULT NULL COMMENT '电话',
  `u_email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `u_address` varchar(200) DEFAULT NULL COMMENT '住址',
  `u_create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户扩展信息表';

-- ----------------------------
-- Records of tb_user_info
-- ----------------------------
INSERT INTO `tb_user_info` VALUES ('4', '1', '2016-04-03', '15361427685', 'admin@ect888.com', '江苏省无锡市国家软件园射手座', '2016-02-18 16:43:28');
INSERT INTO `tb_user_info` VALUES ('21', null, null, null, null, null, null);
INSERT INTO `tb_user_info` VALUES ('22', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for tb_channel
-- ----------------------------
# DROP TABLE IF EXISTS `tb_channel`;
# CREATE TABLE `tb_channel` (
#   `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '渠道id',
#   `c_name` VARCHAR(100) NOT NULL COMMENT '渠道姓名',
#   `c_delete_status` int(11) DEFAULT '0' COMMENT '逻辑删除状态',
#   `c_locked` int(11) DEFAULT '0' COMMENT '是否锁定',
#   `c_description` varchar(200) DEFAULT NULL COMMENT '渠道描述',
#   `c_creator_name` varchar(100) NOT NULL COMMENT '创建者',
#   `c_create_time` datetime DEFAULT NULL COMMENT '创建时间',
#   `c_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
#   `c_update_by` VARCHAR(100) COMMENT '更新者',
#   PRIMARY KEY (`id`)
# ) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='渠道表';
-- ----------------------------
-- Records of tb_channel
-- ----------------------------
# INSERT INTO tb_channel VALUES ( '11' , '农', '0', '0', 'nongxinyin','admin','2017-02-06 14:41:47','2017-02-06 14:42:16','admin');


-- ----------------------------
-- Changes of tb_resource
-- ----------------------------
INSERT INTO `tb_resource` VALUES ('99', '2', '渠道管理', 'system:channel', '0', '/channel/listUI.html', '2', '', '0', '渠道管理', '2016-01-11 22:51:07', '2016-06-30 20:53:38');
-- 贾继祖、徐乐功能菜单
INSERT INTO `tb_resource` VALUES (302, 25, '异常数据查询', 'baidu:exceptionData', 0, '/baidu/loanandexception/exceptionData.html', NULL, '', 0, '异常数据查询', '2017-7-25 14:32:17', '2017-7-26 11:44:01');
INSERT INTO `tb_resource` VALUES (303, 302, '异常数据的查询', 'baidu:exceptionDataList', 1, '/baidu/loanandexception/exceptionDataList.html', NULL, '', 0, '异常数据的查询', '2017-7-25 14:34:32', '2017-7-26 11:45:35');
INSERT INTO `tb_resource` VALUES (304, 302, '异常数据的详细', 'baidu:exceptionDataDetail', 1, '/baidu/loanandexception/exceptionDataDetail.html', NULL, '', 0, '异常数据的详细', '2017-7-25 14:35:44', '2017-7-26 11:45:25');
INSERT INTO `tb_resource` VALUES (305, 302, '更新解决状态', 'baidu:updateExceptionData', 1, '/baidu/loanandexception/updateExceptionData.html', NULL, '', 0, '更新解决状态', '2017-7-25 14:36:44', '2017-7-26 11:45:21');
INSERT INTO `tb_resource` VALUES (306, 25, '放款查询', 'baidu:loanData', 0, '/baidu/loanandexception/loanData.html', NULL, '', 0, '放款查询', '2017-7-25 14:38:02', '2017-7-26 11:44:50');
INSERT INTO `tb_resource` VALUES (307, 306, '放款信息查询', 'baidu:loanDataList', 1, '/baidu/loanandexception/loanDataList.html', NULL, '', 0, '放款信息查询', '2017-7-25 14:38:59', '2017-7-26 11:45:42');
INSERT INTO `tb_resource` VALUES (308, 306, '解约和重新放款', 'baidu:submitLoan', 1, '/baidu/loanandexception/submitLoan.html', NULL, '', 0, '解约和重新放款', '2017-7-25 14:40:15', '2017-7-26 11:45:51');
-- 融祥汽车平台：融祥汽车租赁放款查询菜单相关资源sql脚本(王永建）
insert into `tb_resource` VALUES ('106','24','融祥汽车租赁菜单','menu:jd','0','',NULL,'','0','融祥汽车租赁菜单','2017-07-27 14:37:49','2017-07-27 14:38:05');
insert into `tb_resource` VALUES ('504','106','放款状态查询','menu:jd:queryLoanStatus','0','/jd/loan/queryLoanStatus.html',NULL,'','0','融祥汽车租赁放款状态查询','2017-07-27 14:51:12','2017-08-01 09:49:09');
insert into `tb_resource` VALUES ('505','504','查询订单放款状态','menu:jd:queryLoanStatus:button','1','/jd/loan/list.html',NULL,NULL,'0','查询订单放款状态权限','2017-07-28 16:43:30','2017-08-01 09:48:20');
insert into `tb_resource` VALUES ('506','504','再次放款','menu:againLoan','1','/jd/loan/againLoan.html',NULL,NULL,'0','再次放款权限','2017-08-01 16:06:23','2017-08-04 13:53:30');
insert into `tb_resource` VALUES ('507','504','融祥汽车租赁订单解约','menu:jd:queryLoanStatus:surrender','1','/jd/loan/surrender.html',NULL,'','0','融祥汽车租赁订单解约','2017-08-02 11:33:39','2017-08-02 11:34:08');




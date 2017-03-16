/*
Navicat MySQL Data Transfer

Source Server         : dev
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : dubbo_dev

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2017-03-15 17:47:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `ADMIN_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '业务ID',
  `ADMIN_NAME` varchar(20) DEFAULT NULL COMMENT '管理员账号',
  `SEX` int(11) DEFAULT NULL COMMENT '性别 1-男，2-女 ',
  `EMAIL` varchar(32) DEFAULT NULL COMMENT '邮箱',
  `PASSWORD` varchar(32) DEFAULT NULL COMMENT '密码',
  `NICKNAME` varchar(50) DEFAULT NULL COMMENT '昵称',
  `STATUS` int(11) DEFAULT NULL COMMENT '状态 0-禁用，1-启用',
  `CREATE_BY` varchar(20) DEFAULT NULL COMMENT '创建人ID',
  `CREATE_BY_NAME` varchar(20) DEFAULT NULL COMMENT '创建人名称',
  `CREATE_TIME` date DEFAULT NULL COMMENT '创建时间',
  `LAST_UPDATE_BY` varchar(20) DEFAULT NULL COMMENT '最后修改人ID',
  `LAST_UPDATE_NAME` varchar(20) DEFAULT NULL COMMENT '最后修改人名称',
  `LAST_UPDATE_TIME` date DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`ADMIN_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8 COMMENT='管理员账号表';

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES ('10000', 'admin', '1', 'iChanl@iChanl.com', '57dd03ed397eabaeaa395eb740b770fd', '超级管理员', '1', '10000', 'super', '2017-01-11', '10000', 'super', '2017-02-21');

-- ----------------------------
-- Table structure for t_adminlog
-- ----------------------------
DROP TABLE IF EXISTS `t_adminlog`;
CREATE TABLE `t_adminlog` (
  `LOG_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '业务ID',
  `LOG_CODE` int(11) DEFAULT NULL COMMENT '日志码 -1=异常， 1=正常 ',
  `LOG_TIME` bigint(20) NOT NULL COMMENT '操作时间',
  `ADMIN_ID` varchar(20) DEFAULT NULL COMMENT '管理员ID',
  `LOG_CONTENT` varchar(256) DEFAULT NULL COMMENT '日志内容',
  `DESCC` varchar(2000) DEFAULT NULL COMMENT '日志描述',
  PRIMARY KEY (`LOG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员日志表';

-- ----------------------------
-- Records of t_adminlog
-- ----------------------------

-- ----------------------------
-- Table structure for t_adminmenu
-- ----------------------------
DROP TABLE IF EXISTS `t_adminmenu`;
CREATE TABLE `t_adminmenu` (
  `MENU_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '业务ID',
  `MENU_NAME` varchar(20) NOT NULL DEFAULT 'MENU_NAME' COMMENT '菜单名',
  `ICON` varchar(200) DEFAULT NULL COMMENT '图片URL',
  `DESCC` varchar(200) DEFAULT NULL COMMENT '描述',
  `URL` varchar(50) DEFAULT NULL COMMENT '菜单url',
  `PARENT_MENU` varchar(20) DEFAULT NULL COMMENT '父菜单',
  `LEAF` int(11) NOT NULL COMMENT '是否为目录 1-是 0-不是',
  `NORTNO` int(11) NOT NULL COMMENT '排序号',
  `CREATE_BY` varchar(20) DEFAULT NULL COMMENT '创建人ID',
  `CREATE_BY_NAME` varchar(20) DEFAULT NULL COMMENT '创建人名称',
  `CREATE_TIME` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `LAST_UPDATE_BY` varchar(20) DEFAULT NULL COMMENT '最后修改人ID',
  `LAST_UPDATE_NAME` varchar(20) DEFAULT NULL COMMENT '最后修改人名称',
  `LAST_UPDATE_TIME` bigint(20) DEFAULT NULL COMMENT '最后修改时间',
  `STATUS` varchar(20) DEFAULT NULL COMMENT '菜单状态：0： 禁用 1： 启用',
  PRIMARY KEY (`MENU_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=677663617784745985 DEFAULT CHARSET=utf8 COMMENT='管理员菜单表';

-- ----------------------------
-- Records of t_adminmenu
-- ----------------------------
INSERT INTO `t_adminmenu` VALUES ('1', '用戶管理', null, '用戶管理', null, null, '1', '1', null, null, null, null, null, null, '1');
INSERT INTO `t_adminmenu` VALUES ('2', '视频管理', null, '视频管理', null, null, '1', '2', null, null, null, null, null, null, '1');
INSERT INTO `t_adminmenu` VALUES ('3', '广告管理', null, '广告管理', null, null, '1', '3', null, null, null, null, null, null, '1');
INSERT INTO `t_adminmenu` VALUES ('4', '运营管理', null, '运营管理', null, null, '1', '4', null, null, null, null, null, null, '1');
INSERT INTO `t_adminmenu` VALUES ('5', '报表管理', null, '报表管理', null, null, '1', '5', null, null, null, null, null, null, '1');
INSERT INTO `t_adminmenu` VALUES ('6', '系统管理', null, '系统管理', null, null, '1', '6', null, null, null, '10000', 'super', '1450235143660', '1');
INSERT INTO `t_adminmenu` VALUES ('7', '用戶資訊', null, '用户信息', 'user/toUser.do', '1', '0', '1', '10000', 'super', '1450233919283', '10000', 'super', '1450233919283', '1');
INSERT INTO `t_adminmenu` VALUES ('8', '使用者等級管理', null, '用户等级管理', 'user/toLevel.do', '1', '0', '2', null, null, null, '10000', 'super', '1450235067333', '1');
INSERT INTO `t_adminmenu` VALUES ('9', '用戶播幣明細', null, '用户播币明细', 'user/toIntegrationDetail.do', '1', '0', '3', '10000', 'super', '1450233852791', '10000', 'super', '1450233852791', '1');
INSERT INTO `t_adminmenu` VALUES ('10', '视频信息', null, '视频信息', 'video/goVideoList.do', '2', '0', '1', '10000', 'super', '1450233902117', '10000', 'super', '1450233902117', '1');
INSERT INTO `t_adminmenu` VALUES ('11', '视频审核', null, '视频审核', 'Approve/toUploadDetail.do', '2', '0', '2', '10000', 'super', '1449481241834', '10000', 'super', '1449481241834', '1');
INSERT INTO `t_adminmenu` VALUES ('13', '一级类型管理', null, '一级类型管理', 'type/toTypeOne.do', '4', '0', '1', '10000', 'super', '1450233904815', '10000', 'super', '1450233904815', '1');
INSERT INTO `t_adminmenu` VALUES ('14', '二级类型管理', null, '二级类型管理', 'type/toTypeTwo.do', '4', '0', '2', '10000', 'super', '1450233905625', '10000', 'super', '1450233905625', '1');
INSERT INTO `t_adminmenu` VALUES ('15', '提现管理', null, '提现管理', 'withdrawDeposit/toWithdrawDeposit.do', '4', '0', '3', null, null, null, '10000', 'super', '1450234973225', '1');
INSERT INTO `t_adminmenu` VALUES ('16', '评论管理', null, '评论管理', 'commentt/toCommentt.do', '4', '0', '5', '10000', 'super', '1450233971780', '10000', 'super', '1450233971780', '1');
INSERT INTO `t_adminmenu` VALUES ('17', '清除缓存', null, '清除缓存', 'jedis/clearJedise.do', '4', '0', '6', '10000', 'super', '1450234156767', '10000', 'super', '1450235015571', '1');
INSERT INTO `t_adminmenu` VALUES ('19', '系统通知', null, '系统通知', 'SystemMessage/toSystemMessage.do', '4', '0', '8', null, null, null, '10000', 'super', '1450235033717', '1');
INSERT INTO `t_adminmenu` VALUES ('20', '帮助中心', null, '帮助中心', 'Help/toHelp.do', '4', '0', '9', null, null, null, null, null, null, '1');
INSERT INTO `t_adminmenu` VALUES ('21', '广告商用户管理', null, '广告商用户管理', 'AdvertUser/toAdvertUser.do', '3', '0', '1', null, null, null, null, null, null, '1');
INSERT INTO `t_adminmenu` VALUES ('22', '视频广告管理', null, '视频广告管理', 'AdvertVideo/toAdvertVideo.do', '3', '0', '2', '10000', 'super', '1449909884295', '10000', 'super', '1449909884295', '1');
INSERT INTO `t_adminmenu` VALUES ('23', '图片广告管理', null, '图片广告管理', 'advertPause/toAdvertPause.do', '3', '0', '3', '10000', 'super', '1449909914624', '10000', 'super', '1449909914624', '1');
INSERT INTO `t_adminmenu` VALUES ('24', '专题广告管理', null, '专题广告管理', 'advertImg/toAdvertImg.do', '3', '0', '4', '10000', 'super', '1449909938623', '10000', 'super', '1449909938623', '1');
INSERT INTO `t_adminmenu` VALUES ('25', '广告点播明细', null, '广告点播明细', 'OnDemand/toOnDemand.do', '3', '0', '5', null, null, null, null, null, null, '1');
INSERT INTO `t_adminmenu` VALUES ('26', '用户注册统计', null, '用户注册统计', 'report/toRegisterUserReport.do', '5', '0', '1', null, null, null, null, null, null, '1');
INSERT INTO `t_adminmenu` VALUES ('27', '视频审核统计', null, '视频审核统计', 'report/toVideoCheckRepost.do', '5', '0', '2', null, null, null, null, null, null, '1');
INSERT INTO `t_adminmenu` VALUES ('28', '播币获取统计', null, '播币获取统计', 'report/toVideoGetBbRepost.do', '5', '0', '3', null, null, null, null, null, null, '1');
INSERT INTO `t_adminmenu` VALUES ('29', '审核人统计', null, '审核人统计', 'report/toAppAdmin.do', '5', '0', '4', null, null, null, null, null, null, '1');
INSERT INTO `t_adminmenu` VALUES ('30', '管理员信息', null, '管理员信息', 'admin/toAdmin.do', '6', '0', '1', null, null, null, null, null, null, '1');
INSERT INTO `t_adminmenu` VALUES ('31', '菜单信息', null, '菜单信息', 'admin/toMenu.do', '6', '0', '2', null, null, null, null, null, null, '1');
INSERT INTO `t_adminmenu` VALUES ('32', '角色信息', null, '角色信息', 'admin/toRole.do', '6', '0', '3', null, null, null, null, null, null, '1');
INSERT INTO `t_adminmenu` VALUES ('33', '管理员日志', null, '管理员日志', 'admin/toAdminLog.do', '6', '0', '8', null, null, null, null, null, null, '1');
INSERT INTO `t_adminmenu` VALUES ('34', '用户日志', null, '用户日志', 'user/toUserLog.do', '6', '0', '9', null, null, null, null, null, null, '1');
INSERT INTO `t_adminmenu` VALUES ('35', '热门关键词管理', null, '热门关键词管理', 'search/HotWord.do', '4', '0', '4', null, null, null, null, null, null, '1');
INSERT INTO `t_adminmenu` VALUES ('36', '数据字典管理', null, '数据字典管理', 'dataDict/toDataDict.do', '6', '0', '4', null, null, null, null, null, null, '1');
INSERT INTO `t_adminmenu` VALUES ('37', '运营策略配置', null, '运营策略配置', 'dataDict/toStrategyGroup.do', '6', '0', '5', null, null, null, null, null, null, '1');
INSERT INTO `t_adminmenu` VALUES ('3213213', '合作', null, '合作', '', '', '1', '1', '10000', 'super', '1448457446916', '10000', 'super', '1450235154030', '2015112411432');
INSERT INTO `t_adminmenu` VALUES ('669505415889031168', '友情链接', null, '友情链接', 'cooperation/toPage.do', '3213213', '0', '2', '10000', 'super', '1448457504611', '10000', 'super', '1448457504611', '1');
INSERT INTO `t_adminmenu` VALUES ('669783374659063808', '举报管理', null, '举报配置 和 查询举报信息', 'dataDict/toAccusation.do', '4', '0', '1', '10000', 'super', '1448526155967', '10000', 'super', '1448526155967', '1');
INSERT INTO `t_adminmenu` VALUES ('670518495079043072', '视频推荐管理', null, '', 'videoTecommend/toVideoTecommend.do', '4', '0', '3', '10000', 'super', '1448699041509', '10000', 'super', '1448699041509', '1');
INSERT INTO `t_adminmenu` VALUES ('670518656631050240', '频道推荐管理', null, '', 'ichannelTecommend/toIchannelTecommend.do', '4', '0', '3', '10000', 'super', '1448699080030', '10000', 'super', '1448699080030', '1');
INSERT INTO `t_adminmenu` VALUES ('671513247450664960', '视频播放统计', null, '视频播放统计', 'report/toVideoClickReport.do', '5', '0', '5', '10000', 'super', '1448942607060', '10000', 'super', '1448942607060', '1');
INSERT INTO `t_adminmenu` VALUES ('675551580564754432', 'APP广告配置管理', null, '', 'adver/advercfg.do', '3', '0', '3', '10000', 'super', '1449899022603', '10000', 'super', '1449899022603', '1');
INSERT INTO `t_adminmenu` VALUES ('675553229609897984', 'PC广告配置管理', null, '3', 'advert/toadvertcfg.do', '3', '0', '3', '10000', 'super', '1449899415764', '10000', 'super', '1449899415764', '1');
INSERT INTO `t_adminmenu` VALUES ('676236512714821232', '资讯管理', '', '', 'consultive/toConsultive.do', '4', '0', '10000', 'super', '1450062323151', '10000', 'super', '1450062323151', '1450062323151', '1');
INSERT INTO `t_adminmenu` VALUES ('676236512714821632', '资讯管理', null, '资讯管理', 'consultive/toConsultive.do', '4', '0', '5', '10000', 'super', '1450062323151', '10000', 'super', '1450062323151', '1');
INSERT INTO `t_adminmenu` VALUES ('676666763144073216', '索引管理', null, '索引管理', 'solrManager/page.do', '6', '0', '15', '10000', 'super', '1450164902842', '10000', 'super', '1450164902842', '1');
INSERT INTO `t_adminmenu` VALUES ('677663617784745984', '222', null, '', '', '677663559391645696', '0', '222', '10000', 'super', '1450402571903', '10000', 'super', '1450402571903', '1');

-- ----------------------------
-- Table structure for t_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_role`;
CREATE TABLE `t_admin_role` (
  `admin_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_admin_role
-- ----------------------------
INSERT INTO `t_admin_role` VALUES ('10000', '10000');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `ROLE_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `ROLE_NAME` varchar(20) DEFAULT NULL COMMENT '角色名称',
  `ROLE_DESCRIPTION` varchar(128) DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=677420014235815937 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('10000', 'ADMIN_SUPER', '超级管理权限');
INSERT INTO `t_role` VALUES ('674144014106365952', 'sao', '临时工权限');
INSERT INTO `t_role` VALUES ('676225391287078912', '郭鹏', '一般管理员');
INSERT INTO `t_role` VALUES ('677420014235815936', '阿萨德', '阿萨德');

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu` (
  `ROLE_ID` bigint(20) NOT NULL COMMENT '角色id',
  `MENU_ID` bigint(20) NOT NULL COMMENT '管理员菜单ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与菜单中间表';

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES ('10000', '4');
INSERT INTO `t_role_menu` VALUES ('10000', '1');
INSERT INTO `t_role_menu` VALUES ('10000', '7');
INSERT INTO `t_role_menu` VALUES ('10000', '8');
INSERT INTO `t_role_menu` VALUES ('10000', '9');
INSERT INTO `t_role_menu` VALUES ('10000', '3213213');
INSERT INTO `t_role_menu` VALUES ('10000', '669505415889031168');
INSERT INTO `t_role_menu` VALUES ('10000', '2');
INSERT INTO `t_role_menu` VALUES ('10000', '10');
INSERT INTO `t_role_menu` VALUES ('10000', '11');
INSERT INTO `t_role_menu` VALUES ('10000', '3');
INSERT INTO `t_role_menu` VALUES ('10000', '21');
INSERT INTO `t_role_menu` VALUES ('10000', '22');
INSERT INTO `t_role_menu` VALUES ('10000', '675553229609897984');
INSERT INTO `t_role_menu` VALUES ('10000', '675551580564754432');
INSERT INTO `t_role_menu` VALUES ('10000', '23');
INSERT INTO `t_role_menu` VALUES ('10000', '24');
INSERT INTO `t_role_menu` VALUES ('10000', '25');
INSERT INTO `t_role_menu` VALUES ('10000', '13');
INSERT INTO `t_role_menu` VALUES ('10000', '669783374659063808');
INSERT INTO `t_role_menu` VALUES ('10000', '14');
INSERT INTO `t_role_menu` VALUES ('10000', '670518656631050240');
INSERT INTO `t_role_menu` VALUES ('10000', '15');
INSERT INTO `t_role_menu` VALUES ('10000', '670518495079043072');
INSERT INTO `t_role_menu` VALUES ('10000', '35');
INSERT INTO `t_role_menu` VALUES ('10000', '16');
INSERT INTO `t_role_menu` VALUES ('10000', '17');
INSERT INTO `t_role_menu` VALUES ('10000', '19');
INSERT INTO `t_role_menu` VALUES ('10000', '20');
INSERT INTO `t_role_menu` VALUES ('10000', '676236512714821232');
INSERT INTO `t_role_menu` VALUES ('10000', '5');
INSERT INTO `t_role_menu` VALUES ('10000', '26');
INSERT INTO `t_role_menu` VALUES ('10000', '27');
INSERT INTO `t_role_menu` VALUES ('10000', '28');
INSERT INTO `t_role_menu` VALUES ('10000', '29');
INSERT INTO `t_role_menu` VALUES ('10000', '671513247450664960');
INSERT INTO `t_role_menu` VALUES ('10000', '6');
INSERT INTO `t_role_menu` VALUES ('10000', '30');
INSERT INTO `t_role_menu` VALUES ('10000', '31');
INSERT INTO `t_role_menu` VALUES ('10000', '32');
INSERT INTO `t_role_menu` VALUES ('10000', '36');
INSERT INTO `t_role_menu` VALUES ('10000', '37');
INSERT INTO `t_role_menu` VALUES ('10000', '33');
INSERT INTO `t_role_menu` VALUES ('10000', '34');
INSERT INTO `t_role_menu` VALUES ('10000', '676666763144073216');
INSERT INTO `t_role_menu` VALUES ('10000', '677421707979657216');
INSERT INTO `t_role_menu` VALUES ('10000', '677425756544176128');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '3');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '1');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '7');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '8');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '9');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '3213213');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '669505415889031168');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '2');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '10');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '11');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '21');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '22');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '23');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '24');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '25');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '4');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '669783374659063808');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '13');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '14');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '15');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '670518495079043072');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '670518656631050240');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '35');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '676236512714821632');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '16');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '17');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '19');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '20');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '676236512714821232');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '5');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '26');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '27');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '28');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '29');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '671513247450664960');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '6');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '30');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '31');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '32');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '36');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '37');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '33');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '34');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '676666763144073216');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '677663559391645696');
INSERT INTO `t_role_menu` VALUES ('674144014106365952', '677663617784745984');

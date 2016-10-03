/*字典表*/
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `leaseholder_id` varchar(50) NOT NULL COMMENT '租户编码',
  `type` varchar(20) NOT NULL COMMENT '类型',
  `code` varchar(50) NOT NULL COMMENT '编码',
  `name` varchar(40) DEFAULT NULL COMMENT '名称',
  `parent_code` varchar(50) DEFAULT NULL COMMENT '父级编码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE_CODE` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*模版表*/
DROP TABLE IF EXISTS `template`;
CREATE TABLE `template` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `leaseholder_id` varchar(50) NOT NULL COMMENT '租户编码',
  `type` varchar(20) DEFAULT NULL COMMENT '模版类型',
  `code` varchar(50) NOT NULL COMMENT '模版编码',
  `name` varchar(100) DEFAULT NULL COMMENT '模版名称',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL,
  `random` varchar(2) DEFAULT NULL COMMENT '是否随机显示',
  `category` varchar(4) DEFAULT NULL COMMENT '模版分类',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE_CODE` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*章节**/
DROP TABLE IF EXISTS `section`;
CREATE TABLE `section` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `leaseholder_id` varchar(50) NOT NULL COMMENT '租户编码',
  `template_code` varchar(50) NOT NULL COMMENT '模版编码',
  `code` varchar(50) DEFAULT NULL COMMENT '章节编码',
  `name` varchar(300) DEFAULT NULL COMMENT '章节名称',
  `sort` int(10) DEFAULT NULL COMMENT '序号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE_CODE` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*问题表*/
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `leaseholder_id` varchar(50) NOT NULL COMMENT '租户编码',
  `tempalte_code` varchar(50) DEFAULT NULL COMMENT '模版编号',
  `section_code` varchar(50) DEFAULT NULL COMMENT '章节编号',
  `section_name` varchar(100) DEFAULT NULL COMMENT '章节名称',
  `code` varchar(50) DEFAULT NULL COMMENT '题目编号',
  `sort` int(10) DEFAULT NULL COMMENT '序号',
  `title` varchar(300) DEFAULT NULL COMMENT '题目标题',
  `type` varchar(20) DEFAULT NULL COMMENT '题目类型',
  `score` int(11) DEFAULT NULL COMMENT '题目分数',
  `require` int(1) DEFAULT '0' COMMENT '是否必答0-不是 1-是',
  `ignore` int(1) DEFAULT '0' COMMENT '是否跳过0-不跳过 1-跳过',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE_CODE` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*问题选项表*/
DROP TABLE IF EXISTS `questionitem`;
CREATE TABLE `questionitem` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `leaseholder_id` varchar(50) NOT NULL COMMENT '租户编号',
  `tempalte_code` varchar(50) DEFAULT NULL COMMENT '模版编号',
  `section_code` varchar(50) DEFAULT NULL COMMENT '章节编号',
  `question_code` varchar(50) DEFAULT NULL COMMENT '问题编号',
  `code` varchar(50) DEFAULT NULL COMMENT '编号',
  `sort` int(11) DEFAULT NULL COMMENT '序号',
  `mark` varchar(10) DEFAULT NULL COMMENT '别名',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `score` int(10) DEFAULT NULL COMMENT '分数',
  `content` varchar(300) DEFAULT NULL COMMENT '填空内容',
  `image` varchar(300) DEFAULT NULL COMMENT '图片地址',
  `tip` varchar(100) DEFAULT NULL COMMENT '提示',
  `isAnswer` int(1) DEFAULT NULL COMMENT '是否是标准答案',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE_CODE` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*问卷结果*/
DROP TABLE IF EXISTS `questionresult`;
CREATE TABLE `questionresult` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `leaseholder_id` varchar(50) NOT NULL COMMENT '租户编码',
  `code` varchar(50) NOT NULL COMMENT '编码',
  `activity_code` varchar(50) NOT NULL COMMENT '活动编码',
  `activity_name` varchar(300) DEFAULT NULL COMMENT '活动名称',
  `tempalte_code` varchar(50) DEFAULT NULL COMMENT '模版编码',
  `template_name` varchar(300) DEFAULT NULL COMMENT '模版名称',
  `section_code` varchar(50) DEFAULT NULL COMMENT '章节编码',
  `question_code` varchar(50) DEFAULT NULL COMMENT '问题编码',
  `question_item_code` varchar(50) DEFAULT NULL COMMENT '问题选项编码',
  `content` varchar(300) DEFAULT NULL COMMENT '答案内容',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE_CODE` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*用户表*/
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `leaseholder_id` varchar(50) NOT NULL COMMENT '租户编码',
  `student_id` varchar(50) NOT NULL COMMENT '工号',
  `name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `english_name` varchar(100) DEFAULT NULL COMMENT '英文名称',
  `pinyin_name` varchar(100) DEFAULT NULL COMMENT '拼音名称',
  `ever_name` varchar(100) DEFAULT NULL COMMENT '曾用名',
  `sex_code` varchar(10) DEFAULT NULL COMMENT '性别码',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `bornAddress_code` varchar(10) DEFAULT NULL COMMENT '出生地码',
  `hometown_code` varchar(10) DEFAULT NULL COMMENT '籍贯码',
  `nation_code` varchar(10) DEFAULT NULL COMMENT '民族码',
  `country_code` varchar(10) DEFAULT NULL COMMENT '国籍码',
  `idCardType_code` varchar(10) DEFAULT NULL COMMENT '证件类型码',
  `idCard_number` varchar(30) DEFAULT NULL COMMENT '证件编号',
  `marray_code` varchar(10) DEFAULT NULL COMMENT '婚姻码',
  `gotqw_code` varchar(10) DEFAULT NULL COMMENT '港澳台侨外码',
  `policy_code` varchar(10) DEFAULT NULL COMMENT '政治面貌码',
  `health_code` varchar(10) DEFAULT NULL COMMENT '健康状况码',
  `faith_code` varchar(10) DEFAULT NULL COMMENT '宗教信仰码',
  `blood_code` varchar(10) DEFAULT NULL COMMENT '血型码',
  `photo` varchar(100) DEFAULT NULL COMMENT '照片',
  `idCard_validity` int(10) DEFAULT NULL COMMENT '证件有效期(天)',
  `single_code` varchar(10) DEFAULT NULL COMMENT '单身码',
  `type` varchar(10) DEFAULT NULL COMMENT '类型1-老师 2-学生',
  `zip_code` varchar(10) DEFAULT NULL COMMENT '邮政编码',
  `address` varchar(180) DEFAULT NULL COMMENT '通信地址',
  `phone` varchar(30) DEFAULT NULL COMMENT '电话',
  `mobile` varchar(30) DEFAULT NULL COMMENT '移动电话',
  `fax` varchar(30) DEFAULT NULL COMMENT '传真',
  `email` varchar(30) DEFAULT NULL COMMENT '邮箱',
  `internet_address` varchar(60) DEFAULT NULL COMMENT '网络地址',
  `chat_no` varchar(40) DEFAULT NULL COMMENT '即时通讯号',
  `weixin` varchar(40) DEFAULT NULL COMMENT '微信',
  `qq` varchar(40) DEFAULT NULL COMMENT 'qq',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `open_id` varchar(64) DEFAULT NULL COMMENT '第三方id',
  `post` varchar(6) DEFAULT NULL COMMENT '员工岗位',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE_L_S` (`leaseholder_id`,`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


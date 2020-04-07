SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE meeting_category;
DROP TABLE meeting_join_user;
DROP TABLE meeting_live_channel;
DROP TABLE meeting_meeting_info;
DROP TABLE meeting_ppt_operation_records;




/* Create Tables */

-- 树结构表
CREATE TABLE meeting_category
(
	-- 编号
	id varchar(64) NOT NULL COMMENT '编号',
	-- 父级编号
	parent_id varchar(64) NOT NULL COMMENT '父级编号',
	-- 所有父级编号
	parent_ids varchar(2000) NOT NULL COMMENT '所有父级编号',
	-- 名称
	name varchar(100) NOT NULL COMMENT '名称',
	-- 排序
	sort decimal(10,0) NOT NULL COMMENT '排序',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date datetime NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date datetime NOT NULL COMMENT '更新时间',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 删除标记（0：正常；1：删除）
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '树结构表';


-- 业务数据表
CREATE TABLE meeting_join_user
(
	-- 编号
	id varchar(64) NOT NULL COMMENT '编号',
	-- 名称
	name varchar(100) COMMENT '名称',
	-- ip
	ip varchar(256) COMMENT 'ip',
	-- 上次操作时间
	up_operation_time datetime COMMENT '上次操作时间',
	-- 操作时间
	operation_time datetime COMMENT '操作时间',
	-- 页号信息
	page_index int(4) COMMENT '页号信息',
	-- 国家
	countries varchar(64) COMMENT '国家',
	-- 省市
	provinces varchar(64) COMMENT '省市',
	-- 区/县
	city varchar(64) COMMENT '区/县',
	-- 网络运营商
	network varchar(64) COMMENT '网络运营商',
	-- 参会时间
	start_time datetime COMMENT '参会时间',
	-- 离开时间
	end_time datetime COMMENT '离开时间',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date datetime NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date datetime NOT NULL COMMENT '更新时间',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 删除标记（0：正常；1：删除）
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	-- sessionid
	sessionid varchar(64) COMMENT 'sessionid',
	PRIMARY KEY (id)
) COMMENT = '业务数据表';


-- 业务数据子表
CREATE TABLE meeting_live_channel
(
	-- 编号
	id varchar(64) NOT NULL COMMENT '编号',
	-- 名称
	name varchar(100) COMMENT '名称',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date datetime NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date datetime NOT NULL COMMENT '更新时间',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 删除标记（0：正常；1：删除）
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	-- 频道logo
	logo varchar(256) COMMENT '频道logo',
	-- rtmp高清
	rtmp_hd varchar(256) COMMENT 'rtmp高清',
	-- rtmp标清
	rtmp_sd varchar(256) COMMENT 'rtmp标清',
	-- rtmp普清
	rtmp_nd varchar(256) COMMENT 'rtmp普清',
	-- rtmp声音
	rtmp_vn varchar(256) COMMENT 'rtmp声音',
	-- hls高清
	hls_hd varchar(256) COMMENT 'hls高清',
	-- hls标清
	hls_sd varchar(256) COMMENT 'hls标清',
	-- hls普清
	hls_nd varchar(256) COMMENT 'hls普清',
	-- hls声音
	hls_vn varchar(256) COMMENT 'hls声音',
	-- 状态(上线、下线)
	status varchar(256) COMMENT '状态(上线、下线)',
	PRIMARY KEY (id)
) COMMENT = '业务数据子表';


-- 业务数据表
CREATE TABLE meeting_meeting_info
(
	-- 编号
	id varchar(64) NOT NULL COMMENT '编号',
	-- 会议名称
	name varchar(100) COMMENT '会议名称',
	-- 简介
	content text COMMENT '简介',
	-- 主讲人
	present varchar(64) COMMENT '主讲人',
	-- 会议时长（分钟）
	length int(6) COMMENT '会议时长（分钟）',
	-- 封面
	img varchar(256) COMMENT '封面',
	-- 图片介绍
	imgs text COMMENT '图片介绍',
	-- 讲稿
	ppt varchar(256) COMMENT '讲稿',
	-- 讲稿UUID
	ppt_uuid varchar(64) COMMENT '讲稿UUID',
	-- 录播视频
	video varchar(256) COMMENT '录播视频',
	-- 会议类型(直播/点播)
	type int COMMENT '会议类型(直播/点播)',
	-- 所属频道
	channel_id varchar(64) COMMENT '所属频道',
	-- 开始时间
	start_time datetime COMMENT '开始时间',
	-- 结束时间
	end_time datetime COMMENT '结束时间',
	-- 参会人数上限
	max_number int(4) COMMENT '参会人数上限',
	-- 登录类型（不需要登录/需要登录/需要报名/需要报名并且审核）
	login_type int(1) COMMENT '登录类型（不需要登录/需要登录/需要报名/需要报名并且审核）',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date datetime NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date datetime NOT NULL COMMENT '更新时间',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 删除标记（0：正常；1：删除）
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '业务数据表';


-- 业务数据表
CREATE TABLE meeting_ppt_operation_records
(
	-- 编号
	id varchar(64) NOT NULL COMMENT '编号',
	-- 名称
	name varchar(100) COMMENT '名称',
	-- ip
	ip varchar(256) COMMENT 'ip',
	-- 上次操作时间
	up_operation_time datetime COMMENT '上次操作时间',
	-- 操作时间
	operation_time datetime COMMENT '操作时间',
	-- 页号信息
	page_index int(4) COMMENT '页号信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date datetime NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date datetime NOT NULL COMMENT '更新时间',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 删除标记（0：正常；1：删除）
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '业务数据表';




SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS live_business_system;
DROP TABLE IF EXISTS live_channel_files;
DROP TABLE IF EXISTS live_live_channel;




/* Create Tables */

-- 直播接入业务系统
CREATE TABLE live_business_system
(
	-- 编号
	id varchar(64) NOT NULL COMMENT '编号',
	name varchar(64) COMMENT '接入系统名称',
	-- 图标
	logo varchar(256) COMMENT '图标',
	-- 公共key
	public_key varchar(64) COMMENT '公共key',
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
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '直播接入业务系统';


-- 直播频道文件列表
CREATE TABLE live_channel_files
(
	-- 编号
	id varchar(64) NOT NULL COMMENT '编号',
	-- 文件名
	name varchar(64) COMMENT '文件名',
	-- 文件大小
	file_size bigint COMMENT '文件大小',
	-- 下载地址
	download_url varchar(256) COMMENT '下载地址',
	-- 开始录制时间
	start_datetime datetime COMMENT '开始录制时间',
	-- 结束录制时间
	end_datetime datetime COMMENT '结束录制时间',
	-- 总时长
	play_time varchar(64) COMMENT '总时长',
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
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '直播频道文件列表';


-- 直播频道
CREATE TABLE live_live_channel
(
	-- 编号
	id varchar(64) NOT NULL COMMENT '编号',
	-- 名称
	name varchar(100) COMMENT '名称',
	-- 外部系统关联id
	rel_id varchar(256) COMMENT '外部系统关联id',
	-- 频道秘钥
	channel_secret_key varchar(64) COMMENT '频道秘钥',
	push_rtmp_url varchar(256) COMMENT '推送rtmp流地址',
	-- 转码参数
	transcoding_paras text COMMENT '转码参数',
	-- 频道logo
	logo varchar(256) COMMENT '频道logo',
	-- rtmp地址
	rtmp_url varchar(256) COMMENT 'rtmp播放地址',
	-- hls播放地址
	hls_url varchar(256) COMMENT 'hls播放地址',
	-- 状态(上线、下线)
	status varchar(256) COMMENT '状态(上线、下线)',
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
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '直播频道';




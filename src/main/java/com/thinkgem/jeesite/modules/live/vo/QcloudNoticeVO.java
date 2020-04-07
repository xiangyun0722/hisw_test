package com.thinkgem.jeesite.modules.live.vo;
/***
 * 腾讯云返回的通知对象。
 * @author tanwenkai（谭文开）   E-mail:tanwenkai@qq.com QQ:497460409
 * @version 创建时间：2017年6月7日 上午9:50:22
 */
public class QcloudNoticeVO {

	private String app;
	private String appname;
	private String channel_id;
	private Integer event_type;
	//event_type = 0 代表断流，event_type = 1 代表推流，event_type = 100 代表有新的录制文件生成， event_type = 200 代表有新的截图图片生成。
	/***
	 * 断流
	 */
	public static int EVENT_TYPE_CAT_STREAM = 0;
	/**
	 * 推流
	 */
	public static int EVENT_TYPE_PUSH_STREAM = 1;
	/***
	 * 录制文件
	 */
	public static int EVENT_TYPE_RECORDING_FILE = 100;
	/***
	 * 截图
	 */
	public static int EVENT_TYPE_CAT_IMAGE = 200;
	
	private String sign;
	private String stream_id;
	private Long t;
	private Long event_time;
	private String sequence;
	private String node;
	private String user_ip;
	private Integer errcode;
	private String errmsg;
	private String stream_param;
	
	public String getApp() {
		return app;
	}
	public void setApp(String app) {
		this.app = app;
	}
	public String getAppname() {
		return appname;
	}
	public void setAppname(String appname) {
		this.appname = appname;
	}
	public String getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}
	public Integer getEvent_type() {
		return event_type;
	}
	public void setEvent_type(Integer event_type) {
		this.event_type = event_type;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getStream_id() {
		return stream_id;
	}
	public void setStream_id(String stream_id) {
		this.stream_id = stream_id;
	}
	public Long getT() {
		return t;
	}
	public void setT(Long t) {
		this.t = t;
	}
	public Long getEvent_time() {
		return event_time;
	}
	public void setEvent_time(Long event_time) {
		this.event_time = event_time;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}
	public String getUser_ip() {
		return user_ip;
	}
	public void setUser_ip(String user_ip) {
		this.user_ip = user_ip;
	}
	public Integer getErrcode() {
		return errcode;
	}
	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public String getStream_param() {
		return stream_param;
	}
	public void setStream_param(String stream_param) {
		this.stream_param = stream_param;
	}
	@Override
	public String toString() {
		return "QcloudNoticeVO [app=" + app + ", appname=" + appname + ", channel_id=" + channel_id + ", event_type="
				+ event_type + ", sign=" + sign + ", stream_id=" + stream_id + ", t=" + t + ", event_time=" + event_time
				+ ", sequence=" + sequence + ", node=" + node + ", user_ip=" + user_ip + ", errcode=" + errcode
				+ ", errmsg=" + errmsg + ", stream_param=" + stream_param + "]";
	}
	
}


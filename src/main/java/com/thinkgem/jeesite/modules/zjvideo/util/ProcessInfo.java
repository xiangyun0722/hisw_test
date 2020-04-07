package com.thinkgem.jeesite.modules.zjvideo.util;

import java.io.Serializable;

/**
 * 进度条Entity
 * @author j.feng
 *
 */
public class ProcessInfo implements Serializable{
	
	private static final long serialVersionUID = 3998449195852051094L;
	
	public long totalSize = 1;  //总共大小
    public long readSize = 0;  	//已读大小
    public String show = "";  	//显示
    public int itemNum = 0;  	//条目数
    public int rate = 0;		//百分比
    
    public ProcessInfo() {
    	super();
    }
    
	public ProcessInfo(long totalSize, long readSize, String show, int itemNum,int rate) {
		super();
		this.totalSize = totalSize;
		this.readSize = readSize;
		this.show = show;
		this.itemNum = itemNum;
		this.rate = rate;
	}

	public long getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}

	public long getReadSize() {
		return readSize;
	}

	public void setReadSize(long readSize) {
		this.readSize = readSize;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

	public int getItemNum() {
		return itemNum;
	}

	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}
}

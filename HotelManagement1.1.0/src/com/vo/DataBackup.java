package com.vo;

import java.math.BigDecimal;
import java.util.Date;

public class DataBackup {
	int dataId;
	String dataName;
	String dataSize;
	Date dataTime;
	public int getDataId() {
		return dataId;
	}
	public void setDataId(int dataId) {
		this.dataId = dataId;
	}
	public String getDataName() {
		return dataName;
	}
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}
	public String getDataSize() {
		return dataSize;
	}
	public void setDataSize(String dataSize) {
		this.dataSize = dataSize;
	}
	public Date getDataTime() {
		return dataTime;
	}
	public void setDataTime(Date dataTime) {
		this.dataTime = dataTime;
	}
	public DataBackup(int dataId, String dataName, String dataSize, Date dataTime) {
		super();
		this.dataId = dataId;
		this.dataName = dataName;
		this.dataSize = dataSize;
		this.dataTime = dataTime;
	}
	public DataBackup() {
		super();
	}
	
}

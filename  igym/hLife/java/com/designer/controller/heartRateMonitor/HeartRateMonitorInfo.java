package com.designer.controller.heartRateMonitor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.designer.common.framework.ApplicationConfig;

public class HeartRateMonitorInfo {

	private Date dateTime = null;
	private Integer interval = null;
	private Integer startGradation = null;
	private Integer endGradation = null;
	
	public String getDate() {
		DateFormat df = new SimpleDateFormat(ApplicationConfig.getInstance().getConfigValue("datePattern.tini"));
		return df.format(dateTime);
	}

	public String getTime() {
		DateFormat df = new SimpleDateFormat(ApplicationConfig.getInstance().getConfigValue("timePattern.tini"));
		return df.format(dateTime);
	}
	
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	
	public Integer getEndGradation() {
		return endGradation;
	}
	
	public void setEndGradation(Integer endGradation) {
		this.endGradation = endGradation;
	}
	
	public Integer getInterval() {
		return interval;
	}
	public void setInterval(Integer interval) {
		this.interval = interval;
	}
	
	public Integer getStartGradation() {
		return startGradation;
	}
	
	public void setStartGradation(Integer startGradation) {
		this.startGradation = startGradation;
	}
	
}

package com.designer.model.heartRateMonitor;

import java.util.Date;
import java.util.List;

public class HeartRateMonitor {

	private Integer id = null;
	private String userId;
	private Date dateTime = null;
	private Integer interval = null;
	private Integer startGradation = null;
	private Integer endGradation = null;
	private List<Integer> readings = null;
	private Long average;
	
	
	//-------------~ Getters & Setters
	
	
	public Long getAverage() {
		return average;
	}

	public void setAverage(Long average) {
		this.average = average;
	}

	public Date getDateTime() {
		return dateTime;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}

	public List<Integer> getReadings() {
		return readings;
	}

	public void setReadings(List<Integer> readings) {
		this.readings = readings;
	}

	public Integer getStartGradation() {
		return startGradation;
	}

	public void setStartGradation(Integer startGradation) {
		this.startGradation = startGradation;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}

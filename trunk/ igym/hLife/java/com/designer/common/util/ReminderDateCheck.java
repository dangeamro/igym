package com.designer.common.util;

public class ReminderDateCheck {
	
	private Integer reminderId = null;
	private String colSqlName = null;
	private String colDisplayName = null;
	private Integer olderThan = null;
	
	public ReminderDateCheck(Integer reminderId, String colSqlName, Integer olderThan) {
		
		this(reminderId, colSqlName, "", olderThan);
	}
	public ReminderDateCheck(Integer reminderId, String colSqlName, String colDisplayName, Integer olderThan) {
		if(reminderId == null || colDisplayName == null|| colSqlName == null || olderThan == null)
			throw new RuntimeException("None of the argument can be null");
		
		this.reminderId = reminderId;
		this.colSqlName = colSqlName;
		this.colDisplayName = colDisplayName;
		this.olderThan = olderThan;
	}
	
	public Integer getReminderId() {
		return reminderId;
	}
	public void setReminderId(Integer reminderId) {
		this.reminderId = reminderId;
	}
	public String getColDisplayName() {
		return colDisplayName;
	}
	public void setColDisplayName(String colDisplayName) {
		this.colDisplayName = colDisplayName;
	}
	public String getColSqlName() {
		return colSqlName;
	}
	public void setColSqlName(String colSqlName) {
		this.colSqlName = colSqlName;
	}
	public Integer getOlderThan() {
		return olderThan;
	}
	public void setOlderThan(Integer olderThan) {
		this.olderThan = olderThan;
	}
}

package com.designer.model.reminder;

import java.util.ArrayList;
import java.util.List;

import com.designer.common.util.ReminderDateCheck;

public class Reminder {

	private Integer id = null;
	private String name = null;
	private String desc = null;
	private String statusFlag = null;
	private String messageBody = null;
	private List<ReminderDateCheck> dateChecks = new ArrayList<ReminderDateCheck>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatusFlag() {
		return statusFlag;
	}
	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
	}
	public String getMessageBody() {
		return messageBody;
	}
	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}
	public List<ReminderDateCheck> getDateChecks() {
		return dateChecks;
	}
	public void addDateCheck(ReminderDateCheck dateCheck) {
		this.dateChecks.add(dateCheck);
	}
}

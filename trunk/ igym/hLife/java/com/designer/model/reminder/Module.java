package com.designer.model.reminder;

import java.util.ArrayList;
import java.util.List;

public class Module {

	public static final String ON = "ON";
	public static final String OFF = "OFF";
	
	private Integer id = null;
	private String tag = null;
	private String name = null;
	private String desc = null;
	private String managerClass = null;
	private String statusFlag = null;
	private List<Reminder> reminders = new ArrayList<Reminder>(); 
	
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
	public String getManagerClass() {
		return managerClass;
	}
	public void setManagerClass(String managerClass) {
		this.managerClass = managerClass;
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
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public List<Reminder> getReminders() {
		return reminders;
	}
	public void setReminders(List<Reminder> reminders) {
		this.reminders = reminders;
	} 
	public void addReminder(Reminder reminder) {
		this.reminders.add(reminder);
	} 
}

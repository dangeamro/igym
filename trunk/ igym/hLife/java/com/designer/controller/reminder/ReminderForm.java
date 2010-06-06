package com.designer.controller.reminder;

import java.util.ArrayList;
import java.util.List;

import com.designer.common.util.ReminderDateCheck;
import com.designer.model.reminder.Module;
import com.designer.model.reminder.Reminder;

public class ReminderForm {

	private Integer id = null;
	private String name = null;
	private String desc = null;
	private boolean isOn = false;
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
	public boolean isOn() {
		return isOn;
	}
	public void setOn(boolean isOn) {
		this.isOn = isOn;
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
	public void setDateChecks(List<ReminderDateCheck> dateChecks) {
		this.dateChecks = dateChecks;
	}

	public void populateFromModel(Reminder rem)
	{
		name = rem.getName();
		desc = rem.getDesc();
		messageBody = rem.getMessageBody();
		isOn = rem.getStatusFlag().equals(Module.ON);
		id = rem.getId();
		dateChecks = rem.getDateChecks();
	}
	public Reminder populateToModel()
	{
		Reminder rem = new Reminder();
		rem.setName(name);
		rem.setDesc(desc);
		rem.setMessageBody(messageBody);
		rem.setStatusFlag(isOn ? Module.ON : Module.OFF);
		rem.setId(id);
		
		return rem;
	}
}

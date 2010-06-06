package com.designer.controller.reminder;

import java.util.ArrayList;
import java.util.List;

import com.designer.model.reminder.Module;
import com.designer.model.reminder.Reminder;

public class ModuleForm {

	private Integer id = null;
	private String tag = null;
	private String name = null;
	private String desc = null;
	private String managerClass = null;
	private boolean isOn = false;
	private List<ReminderForm> reminderForms = new ArrayList<ReminderForm>(); 
	
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
	public boolean isOn() {
		return isOn;
	}
	public void setOn(boolean isOn) {
		this.isOn = isOn;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public List<ReminderForm> getReminders() {
		return reminderForms;
	}
	public void setReminders(List<ReminderForm> reminderForms) {
		this.reminderForms = reminderForms;
	} 
	public void addReminderForm(ReminderForm reminderForm) {
		this.reminderForms.add(reminderForm);
	}
	
	public void populateFromModel(Module module)
	{
		id = module.getId();
		tag = module.getTag();
		name = module.getName();
		desc = module.getDesc();
		managerClass = module.getManagerClass();
		isOn = module.getStatusFlag().equals(Module.ON);
		for(Reminder rem : module.getReminders())
		{
			ReminderForm form = new ReminderForm();
			form.populateFromModel(rem);
			reminderForms.add(form);
		}
	}
	public Module populateToModel()
	{
		Module module = new Module();
		module.setId(id);
		module.setTag(tag);
		module.setName(name);
		module.setDesc(desc);
		module.setManagerClass(managerClass);
		module.setStatusFlag(isOn ? Module.ON : Module.OFF);
		for(ReminderForm form : reminderForms)
			module.addReminder(form.populateToModel());
		
		return module;
	}
}

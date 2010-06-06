package com.designer.dao.reminder;

import java.util.List;

import com.designer.common.framework.ApplicationException;
import com.designer.common.util.ReminderDateCheck;
import com.designer.common.util.ReminderInstance;
import com.designer.model.reminder.Module;
import com.designer.model.reminder.Reminder;

public interface ReminderDAO {
	
	public List<ReminderInstance> getReminders(String moduleKey) throws ApplicationException;

	public List<Module> getModules() throws ApplicationException;

	public Module getModule(String moduleKey) throws ApplicationException;
	
	public List<Reminder> getRemindersForModule(String moduleKey) throws ApplicationException;

	public void updateChecks(List<ReminderDateCheck> checks) throws ApplicationException;

	public void updateEnabled(List<Integer> enabledList) throws ApplicationException;

	public List<String> getModuleKeys() throws ApplicationException;
}

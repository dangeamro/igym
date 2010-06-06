package com.designer.manager.reminder;

import java.util.List;

import com.designer.common.framework.ApplicationException;
import com.designer.common.util.ReminderDateCheck;
import com.designer.common.util.ReminderInstance;
import com.designer.dao.reminder.ReminderDAO;
import com.designer.dao.reminder.ReminderDAOImpl;
import com.designer.model.reminder.Module;
import com.designer.model.reminder.Reminder;

public class ReminderManager {

	private ReminderDAO dao = null;
	
	public ReminderManager()
	{
		dao = new ReminderDAOImpl();
	}
	
	public List<ReminderInstance> getReminders(String moduleKey) throws ApplicationException
	{
		return dao.getReminders(moduleKey);
	}

	public List<String> getModuleKeys() throws ApplicationException
	{
		return dao.getModuleKeys();
	}
	public List<Module> getModules() throws ApplicationException
	{
		List<Module> modules = dao.getModules();
		for(Module module : modules)
		{
			module.setReminders(getRemindersForModule(module.getTag()));
		}
		
		return modules;
	}
	
	public List<Reminder> getRemindersForModule(String moduleKey) throws ApplicationException
	{
		return dao.getRemindersForModule(moduleKey);
	}

	public Module getModule(String moduleKey) throws ApplicationException
	{
		return dao.getModule(moduleKey);
	}

	public void updateChecks(List<ReminderDateCheck> checks) throws ApplicationException
	{
		dao.updateChecks(checks);
	}
	public void updateEnabled(List<Integer> enabledList) throws ApplicationException
	{
		dao.updateEnabled(enabledList);
	}
}

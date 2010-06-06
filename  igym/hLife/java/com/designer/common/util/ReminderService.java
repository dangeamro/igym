package com.designer.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.designer.common.framework.ApplicationConfig;

public class ReminderService implements Runnable{
	
	private Map<String, ReminderModule> registeredReminders = null;
	private static ReminderService thisObj = null;
	
	static {
		thisObj = new ReminderService();
	}
	public static ReminderService getInstance()
	{
		return thisObj;
	}
	
	private ReminderService()
	{
		registeredReminders = new Hashtable<String, ReminderModule>();
	}
	
	public void registerReminders(List<String> keys)
	{
		for(String key:keys)
			registerReminder(key);
	}
	public void registerReminder(String key)
	{
		if(key == null || key.trim().equals(""))
			return;
		
		if(registeredReminders.get(key) == null)
		{
			ReminderModule remInstance = ReminderModule.getInstance(key);
			remInstance.refresh();
			registeredReminders.put(key, remInstance);
		}
	}
	
	public void refreshReminder(String key)
	{
		registeredReminders.get(key).refresh();
	}
	
	public void run() {
		
		while(true)
		{
			int i = 0;
			
			for(ReminderModule remInstance: registeredReminders.values())
			{
				remInstance.remind();
				i++;
			}
			
			System.out.println(new SimpleDateFormat("MMM dd, yyyy hh:mm").format(new Date(System.currentTimeMillis())) + " - Total modules reminded: " + i);
			
			try
			{
				Thread.sleep(Integer.parseInt(ApplicationConfig.getInstance().getConfigValue("reminderService.interval.hours")) * 1000 * 3600);
			}
			catch(InterruptedException ex)
			{
				// TODO 
			}
		}
	}
	
	protected void finalize()
	{
		thisObj = this;
		System.out.println("ReminderService.finalize() was called");
	}
}

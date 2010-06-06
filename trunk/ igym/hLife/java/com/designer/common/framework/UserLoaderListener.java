package com.designer.common.framework;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.designer.common.util.ReminderService;
import com.designer.dao.user.UserDAO;
import com.designer.dao.user.UserDAOImpl;
import com.designer.manager.reminder.ReminderManager;

public class UserLoaderListener implements ServletContextListener {

	private static boolean threadStarted = false;
	
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	private void doBeforeInit(String fileName, ServletContext context){
		ApplicationConfig.init(fileName);
		
		//context.setAttribute("applicationConfig", ApplicationConfig.getInstance());
		
		System.out.println("<<<< Application Configuration Properties list >>>>"); 
		ApplicationConfig.getInstance().getAllConfigProperties();
		
	}

	public void contextInitialized(ServletContextEvent event) {
		
		String configFileName = event.getServletContext().getInitParameter("ApplicationPropertiesFileName");
		//To load ApplicationConfig.properties file at the application startup
		doBeforeInit(configFileName, event.getServletContext());
		
		UserDAO userDao = new UserDAOImpl();
		try {
			UserMap.getInstance().setUserMap(userDao.getUsers());
			
			event.getServletContext().setAttribute("userMap", UserMap.getInstance());
		}
		catch(ApplicationException ex)
		{
			ex.printStackTrace();
		}
		
		initiateReminderService();
	}
	
	private void initiateReminderService()
	{
		try
		{
			ReminderService.getInstance().registerReminders(new ReminderManager().getModuleKeys());
		}
		catch(ApplicationException ex)
		{
			ex.printStackTrace();
			// TODO
		}
		
		if(!threadStarted)
		{
			Thread reminderService = new Thread(ReminderService.getInstance());
			reminderService.setPriority(Thread.MIN_PRIORITY);
			reminderService.setDaemon(true);
			reminderService.start();
			threadStarted = true;
		}
	}
}

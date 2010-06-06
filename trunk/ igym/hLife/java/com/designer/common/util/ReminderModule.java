package com.designer.common.util;

import java.sql.Timestamp;

import com.designer.common.framework.ApplicationException;
import com.designer.controller.message.MessageForm;
import com.designer.manager.message.MessagingManager;
import com.designer.manager.reminder.ReminderManager;

public class ReminderModule {

	private String key = null;
	
	private ReminderModule(String key) {
		
		this.key = key;
	}
	
	public static ReminderModule getInstance(String key)
	{
		if(key == null || key.trim().equals(""))
			return null;
		return new ReminderModule(key);
	}
	
	public void refresh()
	{
		
	}
	
	public String getKey()
	{
		return key;
	}
	
	public boolean shouldRemind()
	{
		return true;
	}

	public void remind()
	{
		MessageForm message = null;
		ReminderManager manager = new ReminderManager();
		try
		{
			for(ReminderInstance rem : manager.getReminders(key))
			{
				
				message = new MessageForm();
				
		//		msgBody.append("Hello Mr <Customer Name>")
		//			.append("\r\n\r\nThis is to remind you that you have missed making entries your Food Journal in HLiFE. ")
		//			.append("\r\n\r\nYou are getting this reminder email because you have failed to make Food Journal entries regularly into HLiFE. ")
		//			.append("Your regular entries help us in keeping a close watch on your day to day activities and your progress towards the goals. ")
		//			.append("Please find some time to make the entries in the future. ")
		//			.append("For the entries that you have missed in the past, you need to send an email to the administrator.")
		//			.append("\r\n\r\nPS: This is an automated reminder sent by HLiFE. Please do not reply this messgae")
		//			.append("\r\n\r\nWith warm Regards,")
		//			.append("\r\nAdministration Team,")
		//			.append("\r\nDesigner Bodies");
				
				
				message.setBody(rem.getBody());
				message.setSentDate(new Timestamp(System.currentTimeMillis()));
				message.setSubject("Gental ReminderInstance...");
				message.setSender("reminder");
				message.setPriority(rem.getPriority());
				message.setTo(rem.getRecipients());
				
				try
				{
					MessagingManager.getInstance().sendMessage(message.populateToModel());
				}
				catch(ApplicationException ex)
				{
					ex.printStackTrace();
					// TODO
				}
				System.out.println("Sent mail......");
			}
		}
		catch(ApplicationException ex)
		{
			ex.printStackTrace();
			// TODO
		}
	}
}

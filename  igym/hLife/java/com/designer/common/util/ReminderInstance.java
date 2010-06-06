package com.designer.common.util;

import java.util.ArrayList;
import java.util.List;

import com.designer.model.message.Recipient;

public class ReminderInstance {
	
	private String body = null;
	
	private String recipients = null;
	
	private Integer priority = 0;
	
	private Integer modulePrimaryKeyId = null;

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getRecipients() {
		return recipients;
	}

	public void setRecipient(String recipients) {
		this.recipients = recipients;
	}

	public void setModulePrimaryKeyId(Integer modulePrimaryKeyId) {
		this.modulePrimaryKeyId = modulePrimaryKeyId;
	}
	
	public boolean equals(Object o)
	{
		if(o instanceof ReminderInstance)
			return modulePrimaryKeyId.equals(((ReminderInstance)o).modulePrimaryKeyId);
		
		return false;
	}
}

package com.designer.model.message;

import com.designer.common.framework.UserMap;
import com.designer.controller.user.UserInfo;


public class Recipient implements Comparable<Recipient>{
	
	public static final int PERSON = 0;
	public static final int GROUP = 1;
	public static final int ROLE = 2;
	public static final String TO = "to";
	public static final String CC = "cc";
	public static final String BCC = "bcc";
	
	private String recipientId = null;
	private String recipientLocation = null;
	private int recipientType;
	private UserInfo userInfo = null;

	public Recipient(String recipientId, String recipientLocation, int recipientType)
	{
		this.recipientId = recipientId;
		if(recipientLocation == null || !recipientLocation.equals(TO) && !recipientLocation.equals(CC) && !recipientLocation.equals(BCC))
			throw new RuntimeException("Unknown location specified for the recipient. Location can be one of the 'to', 'cc' and 'bcc'.");
		this.recipientLocation = recipientLocation;
		if(recipientType < PERSON || recipientType > ROLE)
			throw new RuntimeException("Unknown recipient type. A recipient can either be PERSON or GROUP or ROLE");
		this.recipientType = recipientType;
		
		if(this.recipientType == PERSON)
			userInfo = UserMap.getInstance().getUserInfo(recipientId);
	}

	public String getRecipientLocation() {
		return recipientLocation;
	}

	public String getRecipientId() {
		return recipientId;
	}

	public int getRecipientType() {
		return recipientType;
	}
	
	public String getDisplayName()
	{
		switch(recipientType)
		{
		case GROUP:
			return "+" + recipientId; 
		case ROLE:
			return "*" + recipientId;
		case PERSON:
			return userInfo.getDisplayName();
		}

		return recipientId;
	}
	
	public int compareTo(Recipient r)
	{
		return this.recipientId.compareTo(r.recipientId);
	}
	public boolean equals(Object r)
	{
		if(!(r instanceof Recipient))
			return false;
		return this.recipientId.equals(((Recipient)r).recipientId);
	}
}

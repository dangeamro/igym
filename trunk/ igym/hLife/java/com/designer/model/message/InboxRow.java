package com.designer.model.message;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class InboxRow implements Comparable<InboxRow>{
	
	private String ownerId = null;
	
	private int priority = 0;
	private Integer messageId = null;
	private Integer rootMessageId = null;
	private String lastSender = null;
	private String to = null;
	private String cc = null;
	private String bcc = null;
	private int totalCount = 0;
	private String readStatus = null;
	private String subject = null;
	private Timestamp sentOn = null;
	
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public Integer getRootMessageId() {
		return rootMessageId;
	}
	public void setRootMessageId(Integer rootMessageId) {
		this.rootMessageId = rootMessageId;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public Integer getMessageId() {
		return messageId;
	}
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}
	public String getLastSender() {
		return lastSender;
	}
	public void setLastSender(String lastSender) {
		this.lastSender = lastSender;
	}
	public String getBcc() {
		return bcc;
	}
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public String getReadStatus() {
		return readStatus;
	}
	public void setReadStatus(String readStatus) {
		this.readStatus = readStatus;
	}
	public Timestamp getSentOn() {
		return sentOn;
	}
	public void setSentOn(Timestamp sentOn) {
		this.sentOn = sentOn;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public int compareTo(InboxRow row)
	{
		int returnValue;
		if((returnValue = this.sentOn.compareTo(row.sentOn)) == 0)
			if(this.lastSender == null || (returnValue = this.lastSender.compareTo(row.lastSender)) == 0)
				if(this.subject == null || (returnValue = this.subject.compareTo(row.subject)) == 0)
					if((returnValue = this.readStatus.compareTo(row.readStatus)) == 0)
						if((returnValue = (this.priority - row.priority) * -1) == 0)
							if((returnValue = this.rootMessageId.compareTo(row.rootMessageId)) == 0)
								returnValue = this.messageId.compareTo(row.messageId);
		
		return returnValue * -1;
	}
}

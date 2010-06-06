package com.designer.controller.message;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.designer.common.framework.ApplicationConfig;
import com.designer.common.framework.UserMap;
import com.designer.controller.user.UserInfo;
import com.designer.model.message.InboxRow;
import com.designer.model.message.Recipient;

@SuppressWarnings("serial")
public class InboxRowForm extends ActionForm implements Comparable<InboxRowForm>{
	
	private final int MAX_RECIPIENTS_DISPLAY_LEN = 30;
	
	private String ownerId = null;

	private int priority = 0;
	private Integer messageId = null;
	private Integer rootMessageId = null;
	private UserInfo lastSender = null;
	private String to = null;
	private String cc = null;
	private String bcc = null;
	private int totalCount = 0;
	private boolean isRead = false;
	private String subject = null;
	private Timestamp sentOn = null;
	
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
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
	public Integer getRootMessageId() {
		return rootMessageId;
	}
	public void setRootMessageId(Integer rootMessageId) {
		this.rootMessageId = rootMessageId;
	}
	public String getLastSender() {
		if(ownerId != null && lastSender.getUserId().equals(ownerId))
				return "me";
		return lastSender.getDisplayName();
	}
	public void setLastSender(String lastSenderId) {
		this.lastSender = UserMap.getInstance().getUserInfo(lastSenderId);
	}
	public String getRecipients() {
		
		StringBuffer sb = new StringBuffer();
		if(to != null && !to.equals(""))
			sb.append(to).append("; ");
		if(cc != null && !cc.equals(""))
			sb.append(cc).append("; ");
		if(bcc != null && !bcc.equals(""))
			sb.append(bcc).append("; ");
		
		if(sb.length() > 2)
			sb.setLength(sb.length() - 2);

		if(sb.length() > MAX_RECIPIENTS_DISPLAY_LEN)
			sb.replace(MAX_RECIPIENTS_DISPLAY_LEN - 3, sb.length(), "...");
		return sb.toString();
	}
	public void setTo(String to) {
		this.to = to;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public boolean isRead() {
		return isRead;
	}
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
	public String getSentOn() {
		return new SimpleDateFormat(ApplicationConfig.getInstance().getConfigValue("dateTimePattern")).format(new Date(sentOn.getTime()));
	}
	private void setSentOn(Timestamp sentOn) {
		this.sentOn = sentOn;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public int compareTo(InboxRowForm row)
	{
		int returnValue;
		if((returnValue = this.sentOn.compareTo(row.sentOn)) == 0)
			if(this.lastSender == null || (returnValue = this.lastSender.compareTo(row.lastSender)) == 0)
				if(this.subject == null || (returnValue = this.subject.compareTo(row.subject)) == 0)
					if((returnValue = (this.isRead?"Y":"N").compareTo(row.isRead?"Y":"N") * -1) == 0)
						if((returnValue = (this.priority - row.priority) * -1) == 0)
							if((returnValue = this.rootMessageId.compareTo(row.rootMessageId)) == 0)
								returnValue = this.messageId.compareTo(row.messageId);
		
		return returnValue * -1;
	}
	
	protected void populateFromModel(InboxRow row)
	{
		setOwnerId(row.getOwnerId());
		setPriority(row.getPriority());
		setMessageId(row.getMessageId());
		setRootMessageId(row.getRootMessageId());
		setLastSender(row.getLastSender());
		to = row.getTo();
		cc = row.getCc();
		bcc = row.getBcc();
		//setRecipients(row.getRecipients());
		setTotalCount(row.getTotalCount());
		setRead(row.getReadStatus() == null || row.getReadStatus().equals("Y"));
		setSubject(row.getSubject());
		setSentOn(row.getSentOn());
	}
}

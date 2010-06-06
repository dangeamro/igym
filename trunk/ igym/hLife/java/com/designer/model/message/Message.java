package com.designer.model.message;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class Message{
	
	public final static Integer LOW_PRIORITY = 0;
	public final static Integer MEDIUM_PRIORITY = 1;
	public final static Integer HIGH_PRIORITY  = 2;
	
	public final static String MSG_HTML  = "html:";
	public final static String MSG_PLAIN  = "palintext:";
	
	private String ownerId = null;

	private Integer id = null;
	private String sender = null;
	private String subject = null;
	private String body = null;
	private Integer priority = 0;
	private String to = "";
	private String cc = "";
	private String bcc = "";
	private List<Recipient> recipients = null;
	private Timestamp sentDate = null;
	private Integer replyOf = null;
	private Integer rootMessageId = null;
	private List<Message> replies = null;
	
	/** This variable is used to determine whether the message is send via reminder service or its from user.
	*	It needs to be set 'N' in populateToModel() method.
	*/
	private String isReminder = "Y";
	 

	//~-----	Constructors

	public Message(){
		recipients = new ArrayList<Recipient>();
		replies = new ArrayList<Message>();
	};
	
	//~----		Getters & Setters
	
	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
		for(Message reply: replies)
			reply.setOwnerId(ownerId);
	}

	public Integer getRootMessageId() {
		return rootMessageId;
	}

	public void setRootMessageId(Integer rootMessageId) {
		this.rootMessageId = rootMessageId;
	}

	public String getBody() {
		return body;
	}
	
	public String getDisplayBody(){
		return body.replaceFirst(MSG_HTML, "").replaceFirst(MSG_PLAIN, ""); 
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	public void setBody(String body, String format) {
		setBody(format + body);
	}

	public boolean isplainText(){
		return !body.startsWith(MSG_HTML);
	}
	
	public Integer getPriority() {
		return priority;
	}


	public void setPriority(Integer priority) {
		this.priority = priority;
	}


	public String getBcc() {
		return bcc;
	}

	public void setBcc(String bcc) {
		if(bcc != null)
			this.bcc = bcc;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		if(cc != null)
			this.cc = cc;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		if(to != null)
			this.to = to;
	}

	public List<Recipient> getRecipients() {
		return recipients;
	}


	public void addRecipients(Recipient recipient) {
		this.recipients.add(recipient);
	}


	public void setRecipients(List<Recipient> list) {
		recipients = list;
	}


	public Integer getReplyOf() {
		return replyOf;
	}


	public void setReplyOf(Integer replyOf) {
		this.replyOf = replyOf;
	}


	public String getSender() {
		return sender;
	}


	public void setSender(String sender) {
		this.sender = sender;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Timestamp getSentDate() {
		return sentDate;
	}

	public void setSentDate(Timestamp sentDate) {
		this.sentDate = sentDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public List<Message> getReplies() {
		return replies;
	}

	public void addReply(Message reply) {
		this.replies.add(reply);
	}

	public String getReminderFlag() {
		return isReminder;
	}

	public void setReminder(boolean isReminder) {
		if(!isReminder)
			this.isReminder = "N";
	}
}

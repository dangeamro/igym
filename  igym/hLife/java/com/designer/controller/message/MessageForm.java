package com.designer.controller.message;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.designer.common.framework.ApplicationConfig;
import com.designer.common.framework.UserMap;
import com.designer.controller.user.UserInfo;
import com.designer.model.message.Message;
import com.designer.model.message.Recipient;


@SuppressWarnings("serial")
public class MessageForm extends ActionForm implements Comparable<MessageForm> {

	private ActionErrors errors = new ActionErrors();
	
	private String ownerId = null;
	
	private Integer id = null;
	private UserInfo sender = null;
	private String subject = null;
	private String body = null;
	private Integer priority = null;
	private String to = null;
	private String cc = null;
	private String bcc = null;
	private List<Recipient> recipients = null;
	private Timestamp sentDate = null;
	private Integer replyOf = null;
	private Integer rootMessageId = null;
	private List<MessageForm> replies = null;
	
	private boolean isplainText = false;
	private boolean isCompose = false;
	 

	//~-----	Constructors

	public MessageForm(){
		recipients = new ArrayList<Recipient>();
		replies = new ArrayList<MessageForm>();
	}
	
	public MessageForm(Message msg){
		this();
		populateFromModel(msg);
	}
	
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		super.reset(arg0, arg1);
		recipients = new ArrayList<Recipient>();
	}

	//~----		Getters & Setters
	
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

	public String getBody() {
		if(isplainText)
			return formHtmlText(body);

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


	public String getTo() {
		
		//return getRecipients(Recipient.TO);
		return format(to);
	}

	public String getCc() {
		
		return format(cc);
		//return getRecipients(Recipient.CC);
	}

	public String getBcc() {
		
		return format(bcc);
		//return getRecipients(Recipient.BCC);
	}

	private String getRecipients(String loc) {
		StringBuffer s = new StringBuffer();
		for(Recipient recipient:recipients)
		{
			if(!recipient.getRecipientLocation().equals(loc))
				continue;
			if(isCompose)
				s.append(recipient.getRecipientId()).append(';');
			else if(ownerId != null && recipient.getRecipientId().equals(ownerId))
				s.append("me").append(';');
			else
				s.append(recipient.getDisplayName()).append(';');
		}
		if(s.length() > 0)
			s.setLength(s.length() - 1);
		return s.toString();
	}
	private String format(String recipients) {
		
		if(recipients == null || recipients.trim().equals(""))
			return "";
		
		StringBuffer s = new StringBuffer(recipients);

		int index;
		if(isCompose || ownerId == null || (index = recipients.indexOf(ownerId)) == -1)
			return recipients;

		int start = -1, end, tmp = -1;
		while((tmp = s.indexOf(";", tmp + 1)) < index && tmp != -1)
			start = tmp;
		
		start++;
		if(s.charAt(start) == ' ')
			start++;
		
		end = s.indexOf(";", index);
		
		s.replace(start, end == -1 ? s.length() : end, "me");

		return formHtmlText(s.toString());
	}


	public void setTo(String recipient) {
		
		to = recipient;
		setRecipients(recipient, Recipient.TO);
	}

	public void setCc(String recipient) {
		
		cc = recipient;
		setRecipients(recipient, Recipient.CC);
	}

	public void setBcc(String recipient) {
		
		bcc = recipient;
		setRecipients(recipient, Recipient.BCC);
	}

	public Integer getReplyOf() {
		return replyOf;
	}


	public void setReplyOf(Integer replyOf) {
		this.replyOf = replyOf;
	}


	public String getSender() {
		if(ownerId != null && sender.getUserId().equals(ownerId))
			return "me";
		return sender.getDisplayName();
	}

	public String getSenderId() {
		return sender.getUserId();
	}


	public void setSender(String senderId) {
		this.sender = UserMap.getInstance().getUserInfo(senderId);
	}


	public String getSubject() {
		return formHtmlText(subject);
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSentDate() {
		return  new SimpleDateFormat(ApplicationConfig.getInstance().getConfigValue("dateTimePattern")).format(new Date(sentDate.getTime()));
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
	
	
	public List<MessageForm> getReplies() {
		return replies;
	}

	public void addReply(MessageForm reply) {
		this.replies.add(reply);
	}

	// private Methods
	private void setRecipients(String recipient, String loc)
	{
		if(recipient == null || recipient.trim().equals(""))
			return;
		String [] recipients = recipient.trim().split(";");
		int type;
		String rec;
		for(int i = 0; i < recipients.length; i++)
		{
			rec = recipients[i].trim();
			if(rec.indexOf('<') != -1)
			{
				if(rec.indexOf('>') == -1)
				{
					errors.add("idList", new ActionMessage("error.message.invalid.email.id"));
					continue;
				}
				else
				{
					char c = rec.charAt(0);
					switch(c)
					{
					case '+':
						type = Recipient.GROUP;
						break;
					case '*':
						type = Recipient.ROLE;
						break;
					default:
							type = Recipient.PERSON;
					}

					rec = rec.substring(rec.indexOf('<') + 1, rec.indexOf('>')).trim();
				}
			}
			else
			{
				char c = rec.charAt(0);
				switch(c)
				{
				case '+':
					type = Recipient.GROUP;
					rec = rec.substring(1);
					break;
				case '*':
					type = Recipient.ROLE;
					rec = rec.substring(1);
					break;
				default:
						type = Recipient.PERSON;
				}
			}
			try {
				this.recipients.add(new Recipient(rec, loc, type));
			}catch(Exception ex)
			{ }
		}
	}
	
	
	// populate
	public Message populateToModel()
	{
		Message msg = new Message();
		msg.setBody(body);
		msg.setPriority(getPriority());
		msg.setTo(to);
		msg.setCc(cc);
		msg.setBcc(bcc);
		msg.setRecipients(recipients);
		msg.setReplyOf(getReplyOf());
		msg.setRootMessageId(getRootMessageId());
		msg.setSender(getSenderId());
		msg.setSentDate(new Timestamp(System.currentTimeMillis()));
		msg.setSubject(subject);
		msg.setReminder(false);
		
		return msg;
	}

	public void populateFromModel(Message msg)
	{
		setOwnerId(msg.getOwnerId());
		setId(msg.getId());
		setBody(msg.getDisplayBody());
		setPriority(msg.getPriority());
		to = msg.getTo();
		cc = msg.getCc();
		bcc = msg.getBcc();
		recipients.addAll(msg.getRecipients());
		setReplyOf(msg.getReplyOf());
		setRootMessageId(msg.getRootMessageId());
		setSender(msg.getSender());
		setSentDate(msg.getSentDate());
		setSubject(msg.getSubject());
		
		for(Message reply:msg.getReplies())
		{
			addReply(new MessageForm(reply));
		}
		isplainText = msg.isplainText();
	}

	public void list(Collection<MessageForm> c)
	{
		c.add(this);
		for(MessageForm msg:replies)
		{
			msg.list(c);
		}
	}
	
	private String formHtmlText(String str)
	{
		if(str == null || str.trim().equals(""))
			return "";
		return str.replace("&", "&amp;").replace("<", "&lt;").replace(">",
				"&gt;").replace("\r\n", "<br>").replace("\n\r", "<br>");
	}

	public int compareTo(MessageForm msg)
	{
		int retVal = this.sentDate.compareTo(msg.sentDate);
		if(retVal == 0)
			retVal = this.id.compareTo(msg.id);
		
		return retVal; 
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		
		StringBuffer to = new StringBuffer();
		StringBuffer cc = new StringBuffer();
		StringBuffer bcc = new StringBuffer();
		
		UserMap userMap = UserMap.getInstance();
		if(subject == null || subject.trim().equals(""))
		{
			errors.add("subject", new ActionMessage("common.error.field.required"));
		}
		
		if(body == null || body.trim().equals(""))
		{
			errors.add("body", new ActionMessage("common.error.field.required"));
		}
		
		if(recipients.size() == 0)
		{
			errors.add(Recipient.TO, new ActionMessage("common.error.field.required"));
		}
		
		for(Recipient recipient:recipients)
		{
			if(!userMap.contains(recipient))
			{
				if(recipient.getRecipientLocation().equals(Recipient.TO))
					to.append(recipient.getRecipientId()).append(", ");
				else if(recipient.getRecipientLocation().equals(Recipient.CC))
					cc.append(recipient.getRecipientId()).append(", ");
				else
					bcc.append(recipient.getRecipientId()).append(", ");
			}
		}
		
		if(to.length() > 0 )
		{
			to.setLength(to.length() - 2);
			errors.add(Recipient.TO, new ActionMessage("error.message.invalid.email.id"));
		}
		if(cc.length() > 0 )
		{
			cc.setLength(cc.length() - 2);
			errors.add(Recipient.CC, new ActionMessage("error.message.invalid.email.id"));
		}
		if(bcc.length() > 0 )
		{
			bcc.setLength(bcc.length() - 2);
			errors.add(Recipient.BCC, new ActionMessage("error.message.invalid.email.id"));
		}
		
		isCompose = true;

		if(errors.size() > 0)
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.message.header"));
		
		
		return errors;
	}
}

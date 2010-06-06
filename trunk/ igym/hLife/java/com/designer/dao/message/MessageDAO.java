package com.designer.dao.message;

import com.designer.common.framework.ApplicationException;
import com.designer.model.message.Inbox;
import com.designer.model.message.Message;

public interface MessageDAO {
	
	public Inbox getInbox(String userId, String senderId) throws ApplicationException;
	
	public Inbox getSentItems(String userId, String recieverId) throws ApplicationException;
	
	public Message getMessage(Integer messageId, String userId) throws ApplicationException;
	
	public void sendMessage(Message message) throws ApplicationException;
	
	public void eraseSentMessage(Integer messageId, String userId) throws ApplicationException;
	
	public void eraseConversationFromInbox(Integer conversationId, String userId) throws ApplicationException;
	
	public void markUnread(Integer messageId, String userId) throws ApplicationException;
	
	public Integer getUnreadCount(String userId) throws ApplicationException;
	
}

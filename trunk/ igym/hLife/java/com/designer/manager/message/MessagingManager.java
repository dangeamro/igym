package com.designer.manager.message;

import com.designer.common.framework.ApplicationException;
import com.designer.dao.message.MessageDAOImpl;
import com.designer.manager.AbstractManager;
import com.designer.model.message.Inbox;
import com.designer.model.message.Message;

public class MessagingManager extends AbstractManager{
	
	private static MessagingManager messagingManager = null;
	
	static {
		messagingManager = new MessagingManager();
	}
	
	private MessagingManager() {}
	
	public static MessagingManager getInstance()
	{
		return messagingManager;
	}

	public Inbox getInbox(String userId) throws ApplicationException
	{
		return getInbox(userId, null);
	}
	
	public Inbox getInbox(String userId, String senderId) throws ApplicationException
	{
		return new MessageDAOImpl().getInbox(userId, senderId);
	}
	
	public Inbox getSentItems(String userId) throws ApplicationException
	{
		return getSentItems(userId, null);
	}
	
	public Inbox getSentItems(String userId, String recieverId) throws ApplicationException
	{
		return new MessageDAOImpl().getSentItems(userId, recieverId);
	}
	
	public Message getMessage(Integer messageId, String userId) throws ApplicationException
	{
		return new MessageDAOImpl().getMessage(messageId, userId);
	}
	
	public void sendMessage(Message message) throws ApplicationException
	{
		new MessageDAOImpl().sendMessage(message);
	}
	
	public void eraseSentMessage(Message message, String userId) throws ApplicationException
	{
		eraseSentMessage(message.getId(), userId);
	}
	
	public void eraseSentMessage(Integer messageId, String userId) throws ApplicationException
	{
		new MessageDAOImpl().eraseSentMessage(messageId, userId);
	}
	
	public void eraseSentMessages(Integer[] messageIds, String userId) throws ApplicationException
	{
		for(int i = 0; i < messageIds.length; i++)
		{
			eraseSentMessage(messageIds[i], userId);
		}
	}
	
	public void eraseConversationFromInbox(Message conversation, String userId) throws ApplicationException
	{
		eraseConversationFromInbox(conversation.getRootMessageId(), userId);
	}
	
	public void eraseConversationFromInbox(Integer conversationId, String userId) throws ApplicationException
	{
		new MessageDAOImpl().eraseConversationFromInbox(conversationId, userId);
	}
	
	public void eraseConversationsFromInbox(Integer[] conversationIds, String userId) throws ApplicationException
	{
		for(int i = 0; i < conversationIds.length; i++)
		{
			eraseConversationFromInbox(conversationIds[i], userId);			
		}
	}
	
	public void markUnread(Integer messageId, String userId) throws ApplicationException
	{
		new MessageDAOImpl().markUnread(messageId, userId);		
	}
	
	public Integer getUnreadCount(String userId) throws ApplicationException
	{
		return new MessageDAOImpl().getUnreadCount(userId);
	}
}
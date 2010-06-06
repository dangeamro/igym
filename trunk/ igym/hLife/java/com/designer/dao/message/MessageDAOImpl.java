package com.designer.dao.message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.designer.common.framework.ApplicationDataSource;
import com.designer.common.framework.ApplicationException;
import com.designer.model.message.Inbox;
import com.designer.model.message.InboxRow;
import com.designer.model.message.Message;
import com.designer.model.message.Recipient;

public class MessageDAOImpl implements MessageDAO {
	
	ApplicationDataSource appDataSource = ApplicationDataSource.getInstance();
	
	public Inbox getInbox(String userId, String senderId) throws ApplicationException {
		
		List<InboxRow> inboxRows = new ArrayList<InboxRow>();

		try {
			loadMailsForUser(inboxRows, userId, senderId);
			loadMailsForUserRole(inboxRows, userId, senderId);
			loadMailsForUserGroup(inboxRows, userId, senderId);
		}
		catch(SQLException ex)
		{
			throw new ApplicationException(ex.getMessage());
		}
		
		return new Inbox(inboxRows);
	}
	
	public Inbox getSentItems(String userId, String recieverId) throws ApplicationException {
		
		List<InboxRow> sentItems = new ArrayList<InboxRow>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		InboxRow row = null;

		try {
			con = appDataSource.getConnection();
			
			StringBuffer sql = new StringBuffer();
			
			if(recieverId != null)
			{
				sql.append("select MESSAGES.ID ID,SUBJECT,PRIORITY,SENT_DATE,ROOT_MESSAGE_ID,READ_STATUS, TO_, CC, BCC")
				.append(" from MESSAGES, MESSAGE_RECIPIENTS recipients")
				.append(" where recipients.MESSAGE_ID = MESSAGES.ID and SENDER = ? and")
				.append(" MESSAGES.DEL_FLAG = 'N'")
				.append(" and (recipients.REC_PERSON = ? or")
				.append(" recipients.GROUP_ID in (select GROUP_ID from USER_GROUP_LINK where USER_ID = ?) or")
				.append(" recipients.ROLE_ID in (select USER_ROLE.ROLE_ID from USER_ROLE, USER_DETAIL where USER_DETAIL.ROLE_ID = USER_ROLE.ROLE_ID and USER_DETAIL.USER_ID = ?))");
			}
			else
				sql.append("select * from MESSAGES where SENDER = ? and MESSAGES.DEL_FLAG = 'N'");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, userId);
			if(recieverId != null)
			{
				pstmt.setString(2, recieverId);
				pstmt.setString(3, recieverId);
				pstmt.setString(4, recieverId);
			}
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				row = new InboxRow();
				row.setMessageId(rs.getInt("ID"));
				row.setSubject(rs.getString("SUBJECT"));
				row.setPriority(rs.getInt("PRIORITY"));
				row.setSentOn(rs.getTimestamp("SENT_DATE"));
				row.setRootMessageId(rs.getInt("ROOT_MESSAGE_ID"));
				//row.setReadStatus(rs.getString("READ_STATUS"));
				row.setOwnerId(userId);
				row.setTo(rs.getString("TO_"));
				row.setCc(rs.getString("CC"));
				row.setBcc(rs.getString("BCC"));

				//row.setRecipients(getRecipients(row.getMessageId(), con));
				
				sentItems.add(row);
			}
		}
		catch(SQLException ex)
		{
			throw new ApplicationException(ex.getMessage());
		}
		finally {
			try {
				
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			}
			catch(SQLException ex){}
		}
		return new Inbox(sentItems);
	}
	
	public Message getMessage(Integer messageId, String userId) throws ApplicationException
	{
		Connection con = null;
		Message message = null;
		
		try {
			con = appDataSource.getConnection();
			message = loadMessage(messageId, con);
			message.setOwnerId(userId);
			markConversationRead(message.getId(), userId, con);
		}
		catch(SQLException ex)
		{
			throw new ApplicationException(ex.getMessage());
		}
		finally {
			try {
				if(con != null)
					con.close();
			}
			catch(SQLException ex) {}
		}
		return message;
	}

	public void sendMessage(Message message) throws ApplicationException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		try {
			con = appDataSource.getConnection();
			//con.setAutoCommit(false);
			
			pstmt = con.prepareStatement("insert into MESSAGES(SENDER, SUBJECT, BODY, PRIORITY, SENT_DATE, REPLY_OF, DEL_FLAG, TO_, CC, BCC) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, message.getSender());
			pstmt.setString(2, message.getSubject());
			pstmt.setString(3, message.getBody());
			pstmt.setInt(4, message.getPriority());
			pstmt.setTimestamp( 5, message.getSentDate());
			if(message.getReplyOf() != null)
				pstmt.setInt(6, message.getReplyOf());
			else
				pstmt.setNull(6, java.sql.Types.BIGINT);
			pstmt.setString(7, message.getReminderFlag());
			pstmt.setString(8, message.getTo());
			pstmt.setString(9, message.getCc());
			pstmt.setString(10, message.getBcc());
			
			pstmt.executeUpdate();
			
			updateMessageId(message, con);
			
			pstmt2 = con.prepareStatement("update MESSAGES set ROOT_MESSAGE_ID = ? where ID = ?");
			if(message.getReplyOf() == null)
				pstmt2.setInt(1, message.getId());
			else
				pstmt2.setInt(1, message.getRootMessageId());
			pstmt2.setInt(2, message.getId());
			pstmt2.executeUpdate();
			
			updateRecipients(message, con);
			
		}
		catch(SQLException ex)
		{
			throw new ApplicationException(ex.getMessage());
		}
		finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			}
			catch(SQLException ex){}
		}
	}
	
	public void eraseSentMessage(Integer messageId, String userId) throws ApplicationException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = appDataSource.getConnection();
			pstmt = con.prepareStatement("update MESSAGES set DEL_FLAG = 'Y' where SENDER = ? and ID = ?");
			pstmt.setString(1, userId);
			pstmt.setInt(2, messageId);
			pstmt.executeUpdate();

			clearMessages(con);
		}
		catch(SQLException ex)
		{
			throw new ApplicationException(ex.getMessage());
		}
		finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			}
			catch(SQLException ex){}
		}
	}
	
	public void eraseConversationFromInbox(Integer conversationId, String userId) throws ApplicationException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = appDataSource.getConnection();
			pstmt = con.prepareStatement("delete from MESSAGE_RECIPIENTS where REC_PERSON = ? and MESSAGE_ID in (select ID from MESSAGES where ROOT_MESSAGE_ID = ?)");
			pstmt.setString(1, userId);
			pstmt.setInt(2, conversationId);
			pstmt.executeUpdate();
			
			clearMessages(con);
		}
		catch(SQLException ex)
		{
			throw new ApplicationException(ex.getMessage());
		}
		finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			}
			catch(SQLException ex){}
		}
	}
	
	public void markUnread(Integer messageId, String userId) throws ApplicationException
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = appDataSource.getConnection();
			pstmt = con.prepareStatement("update MESSAGE_RECIPIENTS set READ_STATUS = 'N' where MESSAGE_ID = ? AND REC_PERSON = ?");
			pstmt.setInt(1, messageId);
			pstmt.setString(2, userId);
			pstmt.executeUpdate();
		}
		catch(SQLException ex)
		{
			throw new ApplicationException(ex.getMessage());
		}
		finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			}
			catch(SQLException ex){}
		}
	}
	// private methods
	private void updateMessageId(Message message, Connection con) throws SQLException
	{
		Connection con2 = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con2 = appDataSource.getConnection();
			pstmt = con2.prepareStatement("select ID from MESSAGES where SENDER = ? and SENT_DATE = ?");
			pstmt.setString(1, message.getSender());
			pstmt.setTimestamp(2, message.getSentDate());
			
			rs = pstmt.executeQuery();
			
			rs.next();
			message.setId(rs.getInt(1));
		}
		finally {
			rs.close();
			pstmt.close();
			con2.close();
		}
	}
	
	private void updateRecipients(Message message, Connection con) throws SQLException, ApplicationException
	{
		PreparedStatement pstmt = null;
		PreparedStatement pstmtRole = null;
		PreparedStatement pstmtGroup = null;
		ResultSet rsRole = null;
		ResultSet rsGroup = null;
		try {
			pstmt = con.prepareStatement("insert into MESSAGE_RECIPIENTS(MESSAGE_ID, REC_PERSON, ROLE_ID, GROUP_ID, REC_LOCATION) values(?,?,?,?,?)");
			pstmtRole = con.prepareStatement("select USER_ID, ROLE_ID from USER_DETAIL where ROLE_ID in (select ROLE_ID from USER_ROLE where ROLE_TAG = ?)");
			pstmtGroup = con.prepareStatement("select USER_ID, GROUP_ID from USER_GROUP_LINK where GROUP_ID in (select GROUP_ID from USER_GROUP where GROUP_TAG = ?)");
			
			for(Recipient recipient:message.getRecipients())
			{
				pstmt.setInt(1, message.getId());

				switch(recipient.getRecipientType())
				{
				case Recipient.PERSON:
					pstmt.setString(2, recipient.getRecipientId());
					pstmt.setNull(3, java.sql.Types.BIGINT);
					pstmt.setNull(4, java.sql.Types.BIGINT);
					pstmt.setString(5, recipient.getRecipientLocation());
					pstmt.executeUpdate();
					break;
					
				case Recipient.ROLE:
					{
						try {
							pstmtRole.setString(1, recipient.getRecipientId());
							rsRole = pstmtRole.executeQuery();
							while(rsRole.next())
							{
								pstmt.setString(2, rsRole.getString("USER_ID"));
								pstmt.setInt(3, rsRole.getInt("ROLE_ID"));
								pstmt.setNull(4, java.sql.Types.BIGINT);
								pstmt.setString(5, recipient.getRecipientLocation());
								pstmt.executeUpdate();
							}
						}
						finally {
							if(rsRole != null)
								rsRole.close();
						}
					}
					break;

				case Recipient.GROUP:
				{
					try {
						pstmtGroup.setString(1, recipient.getRecipientId());
						rsGroup = pstmtGroup.executeQuery();
						while(rsGroup.next())
						{
							pstmt.setString(2, rsRole.getString("USER_ID"));
							pstmt.setNull(3, java.sql.Types.BIGINT);
							pstmt.setInt(4, rsRole.getInt("GROUP_ID"));
							pstmt.setString(5, recipient.getRecipientLocation());
							pstmt.executeUpdate();
						}
					}
					finally {
						if(rsGroup != null)
							rsGroup.close();
					}
				}
					break;
					
				default:
					throw new ApplicationException("Unsupported recipient type");						
				}
			}
		}
		finally {
			if(pstmt != null)
				pstmt.close();
		}
	}
	
//	private Integer getRoleIdForUser(String tag, Connection con) throws SQLException
//	{
//		ResultSet rs = null;
//		Integer id = null;
//		try {
//			rs = con.prepareStatement("select ROLE_ID from USER_ROLE where ROLE_TAG = '" + tag + "'").executeQuery();
//			rs.next();
//			id = rs.getInt(1);
//		}
//		finally {
//			rs.close();
//		}
//		return id;
//	}
//	private Integer getGroupIdForTag(String tag, Connection con) throws SQLException
//	{
//		ResultSet rs = null;
//		Integer id = null;
//		try {
//			rs = con.prepareStatement("select GROUP_ID from USER_GROUP where GROUP_TAG = '" + tag + "'").executeQuery();
//			rs.next();
//			id = rs.getInt(1);
//		}
//		finally {
//			rs.close();
//		}
//		return id;
//	}
//	
	private void loadMailsForUser(List<InboxRow> list, String userId, String senderId) throws SQLException
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		InboxRow row = null;
		String readStatus = null;
		
		try {
			con = appDataSource.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("select summary.*, sender, subject, priority from messages,")
			.append(" (select rec_person, max(sent_date) as sent_on, messageCount.* from messages, message_recipients recipients,")
			.append(" (select root_message_id, count(*) cnt from MESSAGES group by root_message_id) messageCount")
			.append(" where messageCount.root_message_id = messages.root_message_id and messages.id = recipients.message_id and recipients.rec_person = ?")
			.append(" group by rec_person, messageCount.root_message_id, cnt) summary")
			.append(" where messages.root_message_id = summary.root_message_id and messages.sent_date = summary.sent_on");			
			
			if(senderId != null)
				sql.append(" and messages.sender = '").append(senderId).append('\'');
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				row = new InboxRow();
				row.setPriority(rs.getInt("PRIORITY"));
				row.setRootMessageId(rs.getInt("ROOT_MESSAGE_ID"));
				row.setTotalCount(rs.getInt("cnt"));
				row.setSubject(rs.getString("SUBJECT"));
				row.setSentOn(rs.getTimestamp("sent_on"));
				row.setLastSender(rs.getString("SENDER"));
				row.setOwnerId(userId);
				
				pstmt2 = con.prepareStatement("select MESSAGES.ID from MESSAGES, MESSAGE_RECIPIENTS where MESSAGES.id = MESSAGE_RECIPIENTS.MESSAGE_ID and ROOT_MESSAGE_ID = ? and READ_STATUS = 'N' and sender != ? and REC_PERSON = ?");
				pstmt2.setInt(1, rs.getInt("ROOT_MESSAGE_ID"));
				pstmt2.setString(2, userId);
				pstmt2.setString(3, userId);
				rs2 = pstmt2.executeQuery();
				if(rs2.next())
					readStatus = "N";
				else
					readStatus = "Y";
				row.setReadStatus(readStatus);
				
				list.add(row);
			}
		}
		finally {
			if(rs != null)
				rs.close();
			if(pstmt != null)
				pstmt.close();
			if(rs2!= null)
				rs2.close();
			if(pstmt2 != null)
				pstmt2.close();
			if(con != null)
				con.close();
		}
	}
	private void loadMailsForUserRole(List<InboxRow> list, String userId, String senderId) throws SQLException
	{
	}
	private void loadMailsForUserGroup(List<InboxRow> list, String userId, String senderId) throws SQLException
	{
	}

	private List<Recipient> getRecipients(Integer messageId, Connection con) throws SQLException
	{
		List<Recipient> recipients = new ArrayList<Recipient>();
		loadRecipientUsers(messageId, recipients, con);
		loadRecipientGroups(messageId, recipients, con);
		loadRecipientRoles(messageId, recipients, con);
		
		return recipients;
	}

	private void loadRecipientUsers(Integer messageId, List<Recipient> recipients, Connection con) throws SQLException
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Recipient recipient = null;

		try {
			pstmt = con.prepareStatement("select REC_PERSON, REC_LOCATION from MESSAGE_RECIPIENTS where ROLE_ID is NULL and GROUP_ID is NULL and MESSAGE_ID = ?");
			pstmt.setInt(1, messageId);
			
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				recipient = new Recipient(rs.getString("REC_PERSON"), rs.getString("REC_LOCATION"), Recipient.PERSON);
				recipients.add(recipient);
			}
		}
		finally {
			if(rs != null)
				rs.close();
			if(pstmt != null)
				pstmt.close();
		}
	}
	private void loadRecipientGroups(Integer messageId, List<Recipient> recipients, Connection con) throws SQLException
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Recipient recipient = null;

		try {
			pstmt = con.prepareStatement("select distinct GROUP_TAG, REC_LOCATION from MESSAGE_RECIPIENTS, USER_GROUP where MESSAGE_RECIPIENTS.GROUP_ID = USER_GROUP.GROUP_ID and MESSAGE_RECIPIENTS.MESSAGE_ID = ?");
			pstmt.setInt(1, messageId);
			
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				recipient = new Recipient(rs.getString("GROUP_TAG"), rs.getString("REC_LOCATION"), Recipient.GROUP);
				recipients.add(recipient);
			}
		}
		finally {
			if(rs != null)
				rs.close();
			if(pstmt != null)
				pstmt.close();
		}
	}
	private void loadRecipientRoles(Integer messageId, List<Recipient> recipients, Connection con) throws SQLException
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Recipient recipient = null;

		try {
			pstmt = con.prepareStatement("select distinct ROLE_TAG, REC_LOCATION from MESSAGE_RECIPIENTS, USER_ROLE where MESSAGE_RECIPIENTS.ROLE_ID = USER_ROLE.ROLE_ID and MESSAGE_RECIPIENTS.MESSAGE_ID = ?");
			pstmt.setInt(1, messageId);
			
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				recipient = new Recipient(rs.getString("ROLE_TAG"), rs.getString("REC_LOCATION"), Recipient.ROLE);
				recipients.add(recipient);
			}
		}
		finally {
			if(rs != null)
				rs.close();
			if(pstmt != null)
				pstmt.close();
		}
	}

	private Message loadMessage(Integer messageId, Connection con) throws SQLException
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Message message = new Message();
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM MESSAGES WHERE ID = ?");
			pstmt.setInt(1, messageId);
			
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				message.setId(rs.getInt("ID"));
				message.setSender(rs.getString("SENDER"));
				message.setSubject(rs.getString("SUBJECT"));
				message.setBody(rs.getString("BODY"));
				message.setPriority(rs.getInt("PRIORITY"));
				message.setSentDate(rs.getTimestamp("SENT_DATE"));
				message.setReplyOf(rs.getInt("REPLY_OF")==0?null:rs.getInt("REPLY_OF"));
				message.setRootMessageId(rs.getInt("ROOT_MESSAGE_ID"));
				message.setTo(rs.getString("TO_"));
				message.setCc(rs.getString("CC"));
				message.setBcc(rs.getString("BCC"));
			}
			
			message.setRecipients(getRecipients(messageId, con));
			
			loadRepliesFor(message, con);
		}
		finally {
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
			}
			catch(SQLException ex){}
		}
		
		return message;
	}
	
	private void loadRepliesFor(Message message, Connection con) throws SQLException {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement("select ID from MESSAGES where REPLY_OF = ?");
			pstmt.setInt(1, message.getId());
			
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				message.addReply(loadMessage(rs.getInt("ID"), con));				
			}
		}
		finally {
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
			}
			catch(SQLException ex) {}
		}
		
	}
	private int markConversationRead(Integer messageId, String userId, Connection con) throws SQLException
	{
		PreparedStatement pstmt = con.prepareStatement("update MESSAGE_RECIPIENTS set READ_STATUS = 'Y' where REC_PERSON = ? and MESSAGE_ID in (select ID from MESSAGES where ROOT_MESSAGE_ID = ?)");
		int ret = 0;
		pstmt.setString(1, userId);
		pstmt.setInt(2, messageId);
		try {
			ret = pstmt.executeUpdate();
		}
		finally {
			if(pstmt != null)
				pstmt.close();
		}
		
		return ret;
	}
	
	private void clearMessages(Connection con) throws SQLException
	{
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement("delete from MESSAGES where DEL_FLAG = 'Y' and ID not in (select MESSAGE_ID from MESSAGE_RECIPIENTS)");
			pstmt.executeUpdate();
		}
		finally
		{
			if(pstmt != null)
				pstmt.close();
		}
	}
	
	public Integer getUnreadCount(String userId) throws ApplicationException
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Integer unreadCount = 0;
		
		try {
			con = appDataSource.getConnection();
			pstmt = con.prepareStatement("SELECT COUNT(*) FROM MESSAGE_RECIPIENTS WHERE READ_STATUS = 'N' AND REC_PERSON = ?");
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if(rs.next())
				unreadCount = rs.getInt(1);
		}
		catch(SQLException ex)
		{
			throw new ApplicationException(ex.getMessage());
		}
		finally {
			try {
				
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			}
			catch(SQLException ex){}
		}
		
		return unreadCount;
	}
}

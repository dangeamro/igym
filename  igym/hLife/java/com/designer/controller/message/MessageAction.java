package com.designer.controller.message;

import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.designer.common.framework.ApplicationException;
import com.designer.controller.user.UserInfo;
import com.designer.manager.message.MessagingManager;

public class MessageAction extends DispatchAction {

	private MessagingManager manager = MessagingManager.getInstance();

	public ActionForward getInbox(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		InboxForm inboxForm = new InboxForm();

		String userId = ((UserInfo)request.getSession().getAttribute("LOGIN_CREDENTIALS")).getUserId();
		String targetUserId = (String)request.getParameter("targetUserId");

		try{
			inboxForm.populateFromModel(manager.getInbox(userId, targetUserId));
			
		}catch(ApplicationException ae) {
			return mapping.findForward("failure");
		}
		
		request.setAttribute("inboxRows", inboxForm.getInboxRows());		
		return mapping.findForward("success");
	}
	
	public ActionForward getSentItems(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		InboxForm inboxForm = new InboxForm();
		
		String userId = ((UserInfo)request.getSession().getAttribute("LOGIN_CREDENTIALS")).getUserId();
		String targetUserId = (String)request.getParameter("targetUserId");
		
		try{
			inboxForm.populateFromModel(manager.getSentItems(userId, targetUserId));
			
		}catch(ApplicationException ae) {
			return mapping.findForward("failure");
		}
		
		request.setAttribute("sentItemRows", inboxForm.getInboxRows());		
		return mapping.findForward("success");
	}
	
	public ActionForward getCompose(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		return mapping.findForward("success");
	}
	
	public ActionForward deleteMailsFromInbox(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		String userId = ((UserInfo)request.getSession().getAttribute("LOGIN_CREDENTIALS")).getUserId();
		String ids = request.getParameter("idList");
		String[] idArray = ids.split(",");
		Integer[] iIds = new Integer[idArray.length];
		
		for(int i = 0; i < iIds.length; i++)
			iIds[i] = Integer.parseInt(idArray[i]);
		
		try {
			manager.eraseConversationsFromInbox(iIds, userId);
		}
		catch(Exception ex)
		{
			return mapping.findForward("failure");
		}

		ActionForward fwd = mapping.findForward("success");
		String targetUserId = request.getParameter("targetUserId");
		if(targetUserId == null || targetUserId.equals(""))
			return fwd;
		
		ActionForward newFwd = new ActionForward();
		newFwd.setName(fwd.getName());
		newFwd.setPath(fwd.getPath() + "&targetUserId=" + request.getParameter("targetUserId"));

		return newFwd;
	}

	public ActionForward deleteSentMails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		String userId = ((UserInfo)request.getSession().getAttribute("LOGIN_CREDENTIALS")).getUserId();
		String ids = request.getParameter("idList");
		String[] idArray = ids.split(",");
		Integer[] iIds = new Integer[idArray.length];
		
		for(int i = 0; i < iIds.length; i++)
			iIds[i] = Integer.parseInt(idArray[i]);
		
		try {
			manager.eraseSentMessages(iIds, userId);
		}
		catch(Exception ex)
		{
			return mapping.findForward("failure");
		}

		ActionForward fwd = mapping.findForward("success");
		String targetUserId = request.getParameter("targetUserId");
		if(targetUserId == null || targetUserId.equals(""))
			return fwd;
		
		ActionForward newFwd = new ActionForward();
		newFwd.setName(fwd.getName());
		newFwd.setPath(fwd.getPath() + "&targetUserId=" + request.getParameter("targetUserId"));

		return newFwd;
	}

	public ActionForward sendMail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		try
		{
			manager.sendMessage(((MessageForm)form).populateToModel());
		}
		catch(Exception ex)
		{
			return mapping.findForward("failure");
		}
		
		ActionForward fwd = mapping.findForward("success");
		String targetUserId = request.getParameter("targetUserId");
		if(targetUserId == null || targetUserId.equals(""))
			return fwd;
		
		ActionForward newFwd = new ActionForward();
		newFwd.setName(fwd.getName());
		newFwd.setPath(fwd.getPath() + "&targetUserId=" + request.getParameter("targetUserId"));

		return newFwd;
	}
	
	public ActionForward getMail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		Integer id = Integer.valueOf(request.getParameter("id"));
		String userId = ((UserInfo)request.getSession().getAttribute("LOGIN_CREDENTIALS")).getUserId();
		
		TreeSet<MessageForm> set = new TreeSet<MessageForm>();
		
		try {
			MessageForm msg = new MessageForm(manager.getMessage(id, userId));
			msg.list(set);
		}
		catch(ApplicationException ex)
		{
			ex.printStackTrace();
			return mapping.findForward("failure");
		}

		request.setAttribute("conversation", set);
		return mapping.findForward("success");
	}

	public ActionForward markUnread(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		String userId = ((UserInfo)request.getSession().getAttribute("LOGIN_CREDENTIALS")).getUserId();
		
		try {
			manager.markUnread(Integer.valueOf(request.getParameter("id")), userId);
		}
		catch(Exception ex)
		{
			return mapping.findForward("failure");
		}

		ActionForward fwd = mapping.findForward("success");
		String targetUserId = request.getParameter("targetUserId");
		if(targetUserId == null || targetUserId.equals(""))
			return fwd;
		
		ActionForward newFwd = new ActionForward();
		newFwd.setName(fwd.getName());
		newFwd.setPath(fwd.getPath() + "&targetUserId=" + request.getParameter("targetUserId"));

		return newFwd;
	}
}

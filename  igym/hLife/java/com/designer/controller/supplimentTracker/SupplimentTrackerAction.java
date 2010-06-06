package com.designer.controller.supplimentTracker;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.designer.common.framework.ApplicationException;
import com.designer.controller.user.UserInfo;
import com.designer.dao.supplimentTracker.SupplimentTrackerDAO;
import com.designer.dao.supplimentTracker.SupplimentTrackerDAOImpl;
import com.designer.model.supplimentTracker.SupplimentTracker;


public class SupplimentTrackerAction extends DispatchAction{
	
	
	public ActionForward getSupplimentTracker(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		ActionMessages msg = new ActionMessages();
		
		SupplimentTrackerForm supplimentTrackerForm = null;
		int pageCount = 0;

		String userId = (String)request.getParameter("targetUserId");
		if(userId == null || userId.trim().equals(""))
			userId = ((UserInfo)request.getSession().getAttribute("LOGIN_CREDENTIALS")).getUserId();
		
		int pageNum=0;
		if(request.getParameter("pager.offset")!=null){
			pageNum=Integer.parseInt(request.getParameter("pageNum"));
		}else{
			pageNum=1;
		}
		

		SupplimentTrackerDAO supplimentTrackerDAO = new SupplimentTrackerDAOImpl();		
		
		try {
			SupplimentTracker tracker = supplimentTrackerDAO.getSupplimentTracker(userId, pageNum);
			if(tracker != null){
				supplimentTrackerForm = new SupplimentTrackerForm();
				supplimentTrackerForm.populateFromModel(tracker);				
				pageCount = supplimentTrackerDAO.getPageCount(userId);
				
				request.setAttribute("pageCount", new Integer(pageCount));		
				request.setAttribute("supplimentTrackerForm",supplimentTrackerForm);
			}
		}
		catch(ApplicationException ae)
		{
			msg.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(ae.getMessage()));
			saveErrors(request, msg);
			return mapping.findForward("failure");
		}
		
		return mapping.findForward("success");
	}
	
	public ActionForward deleteSupplimentTracker(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		ActionMessages msg = new ActionMessages();
		
		Integer supplimentTrackerId= ((SupplimentTrackerForm)form).getSupplimentId();
		
		SupplimentTrackerDAO supplimentTrackerDAO = new SupplimentTrackerDAOImpl();
		
		try {
		
			supplimentTrackerDAO.deleteSupplimentTracker(supplimentTrackerId);		
		}
		catch(ApplicationException ae)
		{
			msg.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(ae.getMessage()));
			saveErrors(request, msg);
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
	
	
	public ActionForward addSupplimentTracker(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		ActionMessages msg = new ActionMessages();
		
		SupplimentTrackerForm supplimentTrackerForm=(SupplimentTrackerForm)form;
		
		SupplimentTrackerDAO supplimentTrackerDAO = new SupplimentTrackerDAOImpl();
		
		try
		{
			supplimentTrackerDAO.addSuppliment(supplimentTrackerForm.populateToModel());
		}
		catch(ApplicationException ae)
		{
			msg.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(ae.getMessage()));
			saveErrors(request, msg);
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
	
	
	public ActionForward getAddSupplimentTracker(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){

		ActionMessages msg = new ActionMessages();
		
		SupplimentTrackerDAO supplimentTrackerDAO = new SupplimentTrackerDAOImpl();
		
		SupplimentTrackerForm monthlyPackValues = new SupplimentTrackerForm();
		
		try
		{
			monthlyPackValues.populateFromModel(supplimentTrackerDAO.getMonthlyPackValues());
		}
		catch(ApplicationException ae)
		{
			msg.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(ae.getMessage()));
			saveErrors(request, msg);
			return mapping.findForward("failure");
		}
		
		request.setAttribute("monthlyDefaultValues",monthlyPackValues);
		
		return mapping.findForward("success");
	}
	public ActionForward changeDefaults(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){

		ActionMessages msg = new ActionMessages();
		
		SupplimentTrackerDAO supplimentTrackerDAO = new SupplimentTrackerDAOImpl();
		
		SupplimentTrackerForm supplimentTrackerForm=(SupplimentTrackerForm)form;		
		
		try
		{
			supplimentTrackerDAO.changeDefaults(supplimentTrackerForm.populateToModel());
		}
		catch(ApplicationException ae)
		{
			msg.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(ae.getMessage()));
			saveErrors(request, msg);
			return mapping.findForward("failure");
		}
		
		return mapping.findForward("success");
	}
	
}

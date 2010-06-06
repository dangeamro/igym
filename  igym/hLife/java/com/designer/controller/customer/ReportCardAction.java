package com.designer.controller.customer;

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
import com.designer.controller.statusAndMeasurement.ReportCardMeasurementsForm;
import com.designer.controller.user.UserInfo;
import com.designer.manager.statusAndMeasurements.StatusAndMeasurementsManager;

public class ReportCardAction extends DispatchAction {

	public ActionForward getReportCard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		ActionMessages errors = new ActionMessages();

		String userId = (String)request.getParameter("targetUserId");
		if(userId == null || userId.trim().equals(""))
			userId = ((UserInfo)request.getSession().getAttribute("LOGIN_CREDENTIALS")).getUserId();
		

		StatusAndMeasurementsManager manager = new StatusAndMeasurementsManager();
		ReportCardMeasurementsForm reportCardMeasurementsForm;
				
		try 
		{
			reportCardMeasurementsForm = manager.getReportCardFor(userId);
						
			if(reportCardMeasurementsForm != null)
			{
				request.setAttribute("reportCard", reportCardMeasurementsForm);
			}
		} catch (ApplicationException ae) {
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(ae.getMessage()));
			saveErrors(request, errors);
			return mapping.findForward("failure");
		}
		
		return mapping.findForward("success");
	}
}

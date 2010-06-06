package com.designer.controller.goal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.designer.common.chart.GoalPieChart;
import com.designer.common.framework.ApplicationException;
import com.designer.controller.user.UserInfo;
import com.designer.manager.GoalManager;
import com.designer.model.goal.Goal;

public class GoalAction extends DispatchAction {

	public ActionForward getGoal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		ActionMessages errors = new ActionMessages();
		
		String userId = (String)request.getParameter("targetUserId");
		if(userId == null || userId.trim().equals(""))
			userId = ((UserInfo)request.getSession().getAttribute("LOGIN_CREDENTIALS")).getUserId();
		
		GoalManager goalManager = new GoalManager();
		GoalForm goalForm = null;
		
		try
		{
			Goal goal = goalManager.getGoalForUser(userId);
			if(goal != null)
			{

				String imgFileName = GoalPieChart.generatePieChart(request, response, goal);
				
				goalForm = new GoalForm();
				goalForm.populateFromModel(goal);
				
				request.setAttribute("fileName", imgFileName);
				request.setAttribute("goalForm", goalForm);
			}
		}
		catch(ApplicationException ae)
		{
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(ae.getMessage()));
			saveErrors(request, errors);
			return mapping.findForward("failure");
		}
		
		return mapping.findForward("success");
	}


	public ActionForward addGoal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		ActionMessages errors = new ActionMessages();
		
		GoalForm goalForm = (GoalForm)form;
		
		String userId = (String)request.getParameter("targetUserId");
		if(userId == null || userId.trim().equals(""))
			userId = ((UserInfo)request.getSession().getAttribute("LOGIN_CREDENTIALS")).getUserId();
		
		GoalManager goalManager = new GoalManager();
		try
		{
			Goal goal = goalForm.populateToModel();
			goalManager.addGoal(goal);
		}
		catch(ApplicationException ae)
		{
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(ae.getMessage()));
			saveErrors(request, errors);
			return mapping.findForward("failure");
		}
		
		return mapping.findForward("success");
	}

	public ActionForward updateGoal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		ActionMessages errors = new ActionMessages();
		
		GoalForm goalForm = (GoalForm)form;
		
		String userId = (String)request.getParameter("targetUserId");
		if(userId == null || userId.trim().equals(""))
			userId = ((UserInfo)request.getSession().getAttribute("LOGIN_CREDENTIALS")).getUserId();
		
		GoalManager goalManager = new GoalManager();
		try
		{
			Goal goal = goalForm.populateToModel();
			goalManager.updateGoal(goal);
		}
		catch(ApplicationException ae)
		{
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(ae.getMessage()));
			saveErrors(request, errors);
			return mapping.findForward("failure");
		}
		
		return mapping.findForward("success");
	}
	
}

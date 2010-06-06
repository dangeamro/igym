package com.designer.controller.heartRateMonitor;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.designer.common.chart.HRLineChart;
import com.designer.common.framework.ApplicationConfig;
import com.designer.common.framework.ApplicationException;
import com.designer.common.framework.DesignerUtils;
import com.designer.common.framework.UserMap;
import com.designer.controller.user.UserInfo;
import com.designer.dao.heartRateMonitor.HeartRateMonitorDAO;
import com.designer.dao.heartRateMonitor.HeartRateMonitorDAOImpl;
import com.designer.manager.message.MessagingManager;
import com.designer.model.heartRateMonitor.HeartRateMonitor;
import com.designer.model.message.Message;
import com.designer.model.message.Recipient;



public class HeartRateMonitorAction extends DispatchAction{
	
	
	public ActionForward getHeartRateMonitor(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		ActionMessages errors = new ActionMessages();
		
		String userId = (String)request.getParameter("targetUserId");
		if(userId == null || userId.trim().equals(""))
			userId = ((UserInfo)request.getSession().getAttribute("LOGIN_CREDENTIALS")).getUserId();
		
		int pageNum=0;
		int pageCount=0;
		
		if(request.getParameter("pager.offset")!=null)
			pageNum=Integer.parseInt(request.getParameter("pageNum"));
		else
			pageNum=1;
		
		
		HeartRateMonitorDAO heartRateMonitorDAO = new HeartRateMonitorDAOImpl();
		HeartRateMonitorForm heartRateMonitorForm = new HeartRateMonitorForm();
		List<HeartRateMonitorForm> heartRateMonitorFormList = new ArrayList<HeartRateMonitorForm>();
		
		try{
			HeartRateMonitor heartRateMonitor = heartRateMonitorDAO.getHeartRateMonitor(userId, pageNum);
			pageCount = heartRateMonitorDAO.getAllMonitors(userId).size();
			
			if(heartRateMonitor != null){
				
				//If there are more than 1 records then pull the oldest one too. 
				/*if(pageCount > 1){
					HeartRateMonitor oldestHeartRateMonitor =heartRateMonitorDAO.getHeartRateMonitor(userId, pageCount);
					HeartRateMonitorForm oldestHeartRateMonitorForm = new HeartRateMonitorForm();
					
					oldestHeartRateMonitorForm.populateFromModel(oldestHeartRateMonitor);
					heartRateMonitorFormList.add(oldestHeartRateMonitorForm);
				}*/
				
				heartRateMonitorForm.populateFromModel(heartRateMonitor);
				heartRateMonitorFormList.add(heartRateMonitorForm);

				//HRLineChart hrLineChart = new HRLineChart();
				String imgFileName = HRLineChart.generateLineChart(request, response, heartRateMonitorFormList);
				
				request.setAttribute("fileName", imgFileName);
				request.setAttribute("PageCount", new Integer(pageCount));
				request.setAttribute("heartRateMonitorForm", heartRateMonitorForm);
			}
		}catch (ApplicationException ae){
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(ae.getMessage()));
			saveErrors(request, errors);
			return mapping.findForward("failure");
		}
		
		
		return mapping.findForward("success");

	}

	
	
	public ActionForward compareHeartRateMonitor(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		ActionMessages errors = new ActionMessages();
		
		String userId = (String)request.getParameter("targetUserId");
		if(userId == null || userId.trim().equals(""))
			userId = ((UserInfo)request.getSession().getAttribute("LOGIN_CREDENTIALS")).getUserId();
		
		String strFromDate = (String)request.getParameter("fromDate");
		String strToDate = (String)request.getParameter("toDate");
		
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Date toDate = new Date(System.currentTimeMillis());
		Date fromDate = new Date(System.currentTimeMillis() - 1L*30L*24L*60L*60L*1000L);
		
		try
		{
			if(strToDate != null && strToDate.length() > 0)
				toDate = df.parse(strToDate);
			
			if(strFromDate != null && strFromDate.length() > 0)
				fromDate = df.parse(strFromDate);
		}
		catch(ParseException pe)
		{/*do nothing. This case will never come as the date is given by calendar component*/}
		
		HeartRateMonitorDAO heartRateMonitorDAO = new HeartRateMonitorDAOImpl();
		
		try
		{
			List<HeartRateMonitor> heartRateMonitors = heartRateMonitorDAO.getMonitorsBetweenDates(userId,fromDate,toDate);
			List<HeartRateMonitorForm> heartRateMonitorFormList = new ArrayList<HeartRateMonitorForm>();
			
			for(HeartRateMonitor heartRateMonitor: heartRateMonitors)
			{
				HeartRateMonitorForm heartRateMonitorForm = new HeartRateMonitorForm();
				heartRateMonitorForm.populateFromModel(heartRateMonitor);
				heartRateMonitorFormList.add(heartRateMonitorForm);
			}
				
			String imgFileName = HRLineChart.generateLineChart(request, response, heartRateMonitorFormList);
			
			request.setAttribute("fileName", imgFileName);
			request.setAttribute("fromDate", df.format(fromDate));
			request.setAttribute("toDate", df.format(toDate));
			
		}catch(ApplicationException ae){
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(ae.getMessage()));
			saveErrors(request, errors);
			ae.printStackTrace();
			return mapping.findForward("failure");
		}
		
		return mapping.findForward("success");
	}
	
	public ActionForward addHeartRateMonitor(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		ActionMessages errors = new ActionMessages();

		String userId = (String)request.getParameter("targetUserId");
		if(userId == null || userId.trim().equals(""))
			userId = ((UserInfo)request.getSession().getAttribute("LOGIN_CREDENTIALS")).getUserId();
		
		String emailFlag =(String)request.getParameter("emailFlag");
		
		HeartRateMonitorForm heartRateMonitorForm = (HeartRateMonitorForm)form;
		heartRateMonitorForm.setUserId(userId);
		
		HeartRateMonitorDAO dao = new HeartRateMonitorDAOImpl();
		try {
			dao.addHeartRateMonitor(heartRateMonitorForm.populateToModel());
			if(emailFlag.length() > 0)
			{
				Message message = new Message();
				List<Recipient> recipients = new ArrayList<Recipient>();
				recipients.add(new Recipient("admin",Recipient.TO,Recipient.ROLE));
				
				StringBuffer msgBody = new StringBuffer();
				msgBody.append("HLiFE User <b>" + UserMap.getInstance().getUserInfo(userId).getDisplayName() );
				msgBody.append("</b> has requested you to view his heart rate entries that he made on <b>");
				msgBody.append(DesignerUtils.formatDate(new Date(), DesignerUtils.DATE_DEFAULT));
				msgBody.append("</b><p><p> Please click ");
				
				msgBody.append("<a href='");
				msgBody.append(ApplicationConfig.getInstance().getConfigValue("application.context.root"));
				msgBody.append("admin/getHeartRateMonitor.do?method=getHeartRateMonitor&targetUserId=" + userId);
				msgBody.append("'>here</a>");
				msgBody.append(" to view his heart rate monitor");
				
				message.setSender(userId);
				message.setRecipients(recipients);
				message.setSubject(UserMap.getInstance().getUserInfo(userId).getDisplayName() + "has requested to view his heart rate entries");
				message.setSentDate(new Timestamp(System.currentTimeMillis()));
				message.setPriority(Message.MEDIUM_PRIORITY);
				message.setReminder(true);
				message.setBody(msgBody.toString(),Message.MSG_HTML);
				MessagingManager messagingManager = MessagingManager.getInstance();
				messagingManager.sendMessage(message);
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
	
	
	
}

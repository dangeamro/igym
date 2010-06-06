package com.designer.controller.statusAndMeasurement;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.designer.common.chart.CompareMeasurementsChart;
import com.designer.common.chart.SAMBarChart;
import com.designer.common.framework.ApplicationException;
import com.designer.controller.user.UserInfo;
import com.designer.dao.statusAndMeasurement.StatusAndMeasurementDAO;
import com.designer.dao.statusAndMeasurement.StatusAndMeasurementDAOImpl;
import com.designer.model.statusAndMeasurements.AutomatedMeasurements;
import com.designer.model.statusAndMeasurements.StatusAndMeasurement;


public class StatusAndMeasurementAction extends DispatchAction{
	

	public ActionForward listStatusAndMeasurement(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		//StatusAndMeasurementForm smForm=(StatusAndMeasurementForm)form;

		//StatusAndMeasurementDAO statusAndMeasurementDAO=new StatusAndMeasurementDAOImpl();
		
		return mapping.findForward("success");
	}
	
	public ActionForward getStatusAndMeasurement(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		ActionMessages msg = new ActionMessages();
		
		StatusAndMeasurementForm smForm=(StatusAndMeasurementForm)form;

		String userId = (String)request.getParameter("targetUserId");
		if(userId == null || userId.trim().equals(""))
			userId = ((UserInfo)request.getSession().getAttribute("LOGIN_CREDENTIALS")).getUserId();

		int pageNum=1;
		if(request.getParameter("pager.offset")!=null){
			String strT = request.getParameter("pageNum");
			if(strT.length()>=1)				
				pageNum=Integer.parseInt(request.getParameter("pageNum"));
		}else{
			pageNum=1;
		}

		StatusAndMeasurementDAO statusAndMeasurementDAO = new StatusAndMeasurementDAOImpl();

		try {
			StatusAndMeasurement sm = statusAndMeasurementDAO.getStatusAndMeasurements(userId, pageNum);
			if(sm!= null) {
				smForm.populateFromModel(sm);
				int pageCount = statusAndMeasurementDAO.getPageCount(userId);
	
				request.setAttribute("pageCount", new Integer(pageCount));		
				request.setAttribute("statusAndMeasurementForm",smForm);
			}
		}
		catch (ApplicationException ae) {
			msg.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(ae.getMessage()));
			saveErrors(request, msg);
			return mapping.findForward("failure");
		}
		
		return mapping.findForward("success");
	}
	
	public ActionForward getAutoStatusAndMeasurement(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		ActionMessages msg = new ActionMessages();
		
		AutomatedMeasurements automatedMeasurements = new AutomatedMeasurements();

		String userId = (String)request.getParameter("targetUserId");
		if(userId == null || userId.trim().equals(""))
			userId = ((UserInfo)request.getSession().getAttribute("LOGIN_CREDENTIALS")).getUserId();

		int pageNum=1;
		if(request.getParameter("pager.offset")!=null){
			String strT = request.getParameter("pageNum");
			if(strT.length()>=1)				
				pageNum=Integer.parseInt(request.getParameter("pageNum"));
		}else{
			pageNum=1;
		}
		
		StatusAndMeasurementDAO statusAndMeasurementDAO = new StatusAndMeasurementDAOImpl();

		try {
			//Amit to implement the following method with pageNum
			//automatedMeasurements = statusAndMeasurementDAO.getAutomatedMeasurements(userId, pageNum);

			automatedMeasurements = statusAndMeasurementDAO.getAutomatedMeasurements(userId,pageNum);
			if(automatedMeasurements!= null) {
				ArrayList charFilenames= SAMBarChart.generateBarChart(request,response, automatedMeasurements);

				int pageCount = statusAndMeasurementDAO.getPageAutomatedCount(userId);
	
				request.setAttribute("pageCount", new Integer(pageCount));		
				request.setAttribute("fileNames",charFilenames);
				request.setAttribute("statusAndMeasurementForm",automatedMeasurements);
			}
		}
		catch (ApplicationException ae) {
			msg.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(ae.getMessage()));
			saveErrors(request, msg);
			return mapping.findForward("failure");
		}
		
		return mapping.findForward("success");

	}
	
	public ActionForward compareAutoStatusAndMeasurement(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		ActionMessages msg = new ActionMessages();		
		AutomatedMeasurements automatedMeasurements = new AutomatedMeasurements();
		String userId = (String)request.getParameter("targetUserId");
		if(userId == null || userId.trim().equals(""))
			userId = ((UserInfo)request.getSession().getAttribute("LOGIN_CREDENTIALS")).getUserId();
		
		
		String strFromDate = (String)request.getParameter("fromDate");
		String strToDate = (String)request.getParameter("toDate");
		
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Date toDate = new Date(System.currentTimeMillis());
		Date fromDate = new Date(System.currentTimeMillis() - 6L*30L*24L*60L*60L*1000L);
		
		try
		{
			if(strToDate != null && strToDate.length() > 0)
				toDate = df.parse(strToDate);
			
			if(strFromDate != null && strFromDate.length() > 0)
				fromDate = df.parse(strFromDate);
		}
		catch(ParseException pe)
		{/*do nothing. This case will never come as the date is given by calendar component*/}
		
				
		StatusAndMeasurementDAO statusAndMeasurementDAO = new StatusAndMeasurementDAOImpl();

		try {
			List<AutomatedMeasurements> measuremntsList=new ArrayList<AutomatedMeasurements>();
			measuremntsList = statusAndMeasurementDAO.compareAutomatedMeasurements(userId,fromDate, toDate);
			if(automatedMeasurements!= null) {
				
				ArrayList charFilenames= CompareMeasurementsChart.generateCompareChart(request, response, measuremntsList);

				//int pageCount = statusAndMeasurementDAO.getPageCount(userId);
	
				request.setAttribute("fileNames",charFilenames);
				request.setAttribute("statusAndMeasurementForm",automatedMeasurements);
				request.setAttribute("fromDate", df.format(fromDate));
				request.setAttribute("toDate", df.format(toDate));
			}
		}
		catch (ApplicationException ae) {
			msg.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(ae.getMessage()));
			saveErrors(request, msg);
			return mapping.findForward("failure");
		}
		
		return mapping.findForward("success");

	}
	
//	public ActionForward getUpdateStatusAndMeasurement(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response){
//		
//		StatusAndMeasurementForm statusAndMeasurementForm=(StatusAndMeasurementForm)form;
//		System.out.println("I am in Action statusAndMeasurementForm  :: GetUpdate");
//		int userId=1; // will get the value from session
//		//Dummy code to test DB connectivity. This code should actually be the part of DAO layer
//		StatusAndMeasurementDAOImpl statusAndMeasurementDAOImpl=new StatusAndMeasurementDAOImpl();
//		
//		//statusAndMeasurementDAOImpl.tempStatus(statusAndMeasurementForm,userId);
//		
//
//		statusAndMeasurementForm.setAnklesLeft(10);
//		statusAndMeasurementForm.setAnklesRight(10);
//		statusAndMeasurementForm.setCalvesLeft(10);
//		statusAndMeasurementForm.setCalvesRight(10);
//		statusAndMeasurementForm.setHips(10);
//		statusAndMeasurementForm.setLowThighsLeft(10);
//		statusAndMeasurementForm.setLowThighsRight(10);
//		statusAndMeasurementForm.setNeck(10);
//		statusAndMeasurementForm.setUppArmLeft(10);
//		statusAndMeasurementForm.setUppArmRight(10);
//		statusAndMeasurementForm.setUpperChest(10);
//		statusAndMeasurementForm.setUppThighsLeft(10);
//		statusAndMeasurementForm.setUppThighsRight(10);
//		statusAndMeasurementForm.setWaist(10);
//		statusAndMeasurementForm.setWeight(70);
//		
//
//		return mapping.findForward("success");
//	}
	
	public ActionForward deleteStatusAndMeasurement(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		ActionMessages msg = new ActionMessages();
		
		StatusAndMeasurementDAO statusAndMeasurementDAO = new StatusAndMeasurementDAOImpl();
		
		Integer smId = ((StatusAndMeasurementForm)form).getStatusId();
		
		try 
		{
			statusAndMeasurementDAO.deleteStatusAndMeasurement(smId);
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

	public ActionForward uploadAutomated(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws java.io.FileNotFoundException,java.text.ParseException{
		
		ActionMessages msg = new ActionMessages();
		
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		diskFileItemFactory.setSizeThreshold(13359536); /* the unit is bytes */
		//Date d=new Date();
		DateFormat df =new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ssa");
		System.out.println("Ant Test in Java method "+ df.format(new Date()));
		StringBuffer sb=new StringBuffer();
		sb.append("autoSM");
		sb.append(df.format(new Date()));
		sb.append(".xls");

		System.out.println("File Name  " + sb.toString());

		String dirFileName = "c:/upload/" + sb.toString();

		File repositoryPath = new File(dirFileName);
		diskFileItemFactory.setRepository(repositoryPath);
		ServletFileUpload servletFileUpload = new ServletFileUpload(
				diskFileItemFactory);
		servletFileUpload.setSizeMax(13359536); /* the unit is bytes */
		try {
			List fileItemsList = servletFileUpload.parseRequest(request);
			/* Process file items... */
			Iterator it = fileItemsList.iterator();

			while (it.hasNext()) {
				FileItem fileItem = (FileItem) it.next();
				fileItem.write(repositoryPath);
			}
		} catch (Exception ex) {
			/* The size of the HTTP request body exceeds the limit */
			System.out.println("I am in Sevvlet Catch " + ex.getMessage());

		}
		try {
			StatusAndMeasurementDAOImpl statusAndMeasurementDAOImpl = new StatusAndMeasurementDAOImpl();
			statusAndMeasurementDAOImpl
					.addNewAutomatedMeasurements(dirFileName);
		} catch (ApplicationException ex) {
			msg.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(ex
					.getMessage()));
			saveErrors(request, msg);
			return mapping.findForward("failure");
		}

		return mapping.findForward("success");      
	}
		
	public ActionForward addStatusAndMeasurement(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		ActionMessages msg = new ActionMessages();
		
		StatusAndMeasurementForm smForm = (StatusAndMeasurementForm)form;
		StatusAndMeasurementDAO statusAndMeasurementDAO = new StatusAndMeasurementDAOImpl();
		
		try
		{
			statusAndMeasurementDAO.addStatusAndMeasurement(smForm.populateToModel());
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
	
//	public ActionForward getAddStatusAndMeasurement(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response){
//		
//		StatusAndMeasurementForm statusAndMeasurementForm=(StatusAndMeasurementForm)form;		
//		System.out.println("I am in Action statusAndMeasurementForm  :: getAdd");
//		
//		return mapping.findForward("success");
//	}

}

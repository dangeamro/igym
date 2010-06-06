package com.designer.manager.statusAndMeasurements;

import java.util.Date;

import com.designer.common.framework.ApplicationException;
import com.designer.controller.statusAndMeasurement.ReportCardMeasurementsForm;
import com.designer.dao.statusAndMeasurement.StatusAndMeasurementDAO;
import com.designer.dao.statusAndMeasurement.StatusAndMeasurementDAOImpl;

public class StatusAndMeasurementsManager {

	private StatusAndMeasurementDAO dao = null;
	
	public StatusAndMeasurementsManager()
	{
		dao = new StatusAndMeasurementDAOImpl();
	}
	
	public ReportCardMeasurementsForm getReportCardFor(String userId) throws ApplicationException
	{
		ReportCardMeasurementsForm reportCardMeasurementsForm = null;

		Date firstEntryDate = dao.getOldestEntryDateFor(userId);
		Date previousEntryDate = (dao.getPreviousEntryDateFor(userId) != null ) ? 
					dao.getPreviousEntryDateFor(userId) : dao.getLatestEntryDateFor(userId);
		Date lastEntryDate = dao.getLatestEntryDateFor(userId);
		
		if(firstEntryDate != null && lastEntryDate != null)
		{
			reportCardMeasurementsForm = new ReportCardMeasurementsForm();
			
			reportCardMeasurementsForm.setInitialMeasurement(dao.getReportCardMeasurements(userId, firstEntryDate));
			reportCardMeasurementsForm.setPreviousMeasurement(dao.getReportCardMeasurements(userId, previousEntryDate));
			reportCardMeasurementsForm.setLatestMeasurement(dao.getReportCardMeasurements(userId, lastEntryDate));
		}
		return reportCardMeasurementsForm;
	}
	
	public void uploadAutoMeasurements() throws ApplicationException
	{
		
	}

}

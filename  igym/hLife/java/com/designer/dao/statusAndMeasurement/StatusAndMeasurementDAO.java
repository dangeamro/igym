package com.designer.dao.statusAndMeasurement;

import java.util.ArrayList;
import java.util.Date;

import com.designer.common.framework.ApplicationException;
import com.designer.model.statusAndMeasurements.AutomatedMeasurements;
import com.designer.model.statusAndMeasurements.ReportCardMeasurements;
import com.designer.model.statusAndMeasurements.StatusAndMeasurement;



public interface StatusAndMeasurementDAO {
	
	public void addStatusAndMeasurement(StatusAndMeasurement sm) throws ApplicationException;
	
	public StatusAndMeasurement getStatusAndMeasurements(String userId, Integer pageNo) throws ApplicationException;
	
	public AutomatedMeasurements getAutomatedMeasurements(String userId,int pageNum) throws ApplicationException;
	
	public ReportCardMeasurements getReportCardMeasurements(String userId, Date date) throws ApplicationException;
	
	public void deleteStatusAndMeasurement(Integer smId) throws ApplicationException;
	
	public int getPageCount(String userId) throws ApplicationException;
	
	public int getPageAutomatedCount(String userId) throws ApplicationException;
	
	public ArrayList<AutomatedMeasurements> compareAutomatedMeasurements(String userId, Date fromDate, Date todate)throws ApplicationException;

	public Date getOldestEntryDateFor(String userId)throws ApplicationException;
	
	public Date getLatestEntryDateFor(String userId)throws ApplicationException;

	public Date getPreviousEntryDateFor(String userId)throws ApplicationException;
}

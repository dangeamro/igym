package com.designer.dao.heartRateMonitor;

import java.util.Date;
import java.util.List;

import com.designer.common.framework.ApplicationException;
import com.designer.controller.heartRateMonitor.HeartRateMonitorInfo;
import com.designer.model.heartRateMonitor.HeartRateMonitor;

public interface HeartRateMonitorDAO {
	
	public void addHeartRateMonitor(HeartRateMonitor heartRateMonitor) throws ApplicationException;
	
	public HeartRateMonitor getHeartRateMonitor(String userId, int pageNum) throws ApplicationException;
	
	public HeartRateMonitor getHeartRateMonitor(String userId, Date date) throws ApplicationException;
	
	public List<HeartRateMonitorInfo> getAllMonitors(String userId) throws ApplicationException;
	
	public List<HeartRateMonitor> getMonitorsBetweenDates(String userId, Date fromDate, Date toDate) throws ApplicationException;
}

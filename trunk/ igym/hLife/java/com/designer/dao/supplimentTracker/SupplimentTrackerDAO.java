package com.designer.dao.supplimentTracker;

import com.designer.common.framework.ApplicationException;
import com.designer.model.supplimentTracker.SupplimentTracker;



public interface SupplimentTrackerDAO {

	public SupplimentTracker getSupplimentTracker(String userId, int pageNum) throws ApplicationException;
	
	public void addSuppliment(SupplimentTracker supplimentTracker) throws ApplicationException;
	
	public int getPageCount(String userId) throws ApplicationException;

	public void deleteSupplimentTracker(Integer supplimentTrackerId) throws ApplicationException;

	public SupplimentTracker getMonthlyPackValues() throws ApplicationException;
	
	public void changeDefaults(SupplimentTracker supplimentTracker) throws ApplicationException;

	//public void deleteStatusAndMeasurement(SupplimentTracker supplimentTrackerForm,int userId) throws ApplicationException;
}

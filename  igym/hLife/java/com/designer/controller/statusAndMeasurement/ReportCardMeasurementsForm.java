package com.designer.controller.statusAndMeasurement;

import org.apache.struts.action.ActionForm;

import com.designer.common.framework.DesignerUtils;
import com.designer.model.statusAndMeasurements.ReportCardMeasurements;

@SuppressWarnings("serial")
public class ReportCardMeasurementsForm extends ActionForm {

	private long miliPerDay = 1000*60*60*24;
	
	private ReportCardMeasurements initialMeasurement = null;
	private ReportCardMeasurements previousMeasurement = null;
	private ReportCardMeasurements latestMeasurement = null;
	
	
	public ReportCardMeasurements getInitialMeasurement() {
		return initialMeasurement;
	}
	public void setInitialMeasurement(ReportCardMeasurements initialMeasurement) {
		this.initialMeasurement = initialMeasurement;
	}
	public ReportCardMeasurements getPreviousMeasurement() {
		return previousMeasurement;
	}
	public void setPreviousMeasurement(ReportCardMeasurements previousMeasurement) {
		this.previousMeasurement = previousMeasurement;
	}
	public ReportCardMeasurements getLatestMeasurement() {
		return latestMeasurement;
	}
	public void setLatestMeasurement(ReportCardMeasurements latestMeasurement) {
		this.latestMeasurement = latestMeasurement;
	}
	
	public Double getWeightChange(){
		return DesignerUtils.formatDouble(latestMeasurement.getWeight() - initialMeasurement.getWeight()) ;
	}
	public Double getLeanBodyMassChange(){
		return DesignerUtils.formatDouble(latestMeasurement.getLeanBodyMass() - initialMeasurement.getLeanBodyMass()) ;
	}
	public Double getBodyFatMassChange(){
		return DesignerUtils.formatDouble(latestMeasurement.getFat() - initialMeasurement.getFat()) ;
	}
	public Double getBmiChange(){
		return DesignerUtils.formatDouble(latestMeasurement.getBmi() - initialMeasurement.getBmi()) ;
	}
	public Double getPbfChange(){
		return DesignerUtils.formatDouble(latestMeasurement.getPbf() - initialMeasurement.getPbf()) ;
	}
	public Double getTbwChange(){
		return DesignerUtils.formatDouble(latestMeasurement.getTbw() - initialMeasurement.getTbw()) ;
	}
	public Double getBasalMetabolicRateChange(){
		return DesignerUtils.formatDouble(latestMeasurement.getBasalMetabolicRate() - initialMeasurement.getBasalMetabolicRate()) ;
	}
	public Double getdryLeanMassChange(){
		return DesignerUtils.formatDouble(latestMeasurement.getdryLeanMass() - initialMeasurement.getdryLeanMass());
	}
	
	public Long getWeeksDifference()
	{
		if(initialMeasurement.getDateTime()== null || latestMeasurement.getDateTime() == null)
			return new Long(0);
		
		return (latestMeasurement.getDateTime().getTime() - initialMeasurement.getDateTime().getTime())/(miliPerDay*7) ;
	}

}

package com.designer.controller.heartRateMonitor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.designer.common.framework.ApplicationConfig;
import com.designer.model.heartRateMonitor.HeartRateMonitor;

@SuppressWarnings("serial")
public class HeartRateMonitorForm extends ActionForm {

	private Integer id = null;
	private String userId;
	private Date dateTime = null;
	private Integer interval = null;
	private Integer startGradation = null;
	private Integer endGradation = null;
	private List<Integer> iReadings = null;

	
	
	//-------------~ Getters & Setters
	
	
	public Double getAverage() {
		if(iReadings.size() == 0)
			return 0.0;
		int total = 0;
		for(Integer reading:iReadings)
			total += reading;
		
		return (total + 0.0)/iReadings.size();
	}

	public String getDateTime() {
		if(dateTime == null)
			return "";
		
		return new SimpleDateFormat(ApplicationConfig.getInstance().getConfigValue("dateTimePattern")).format(dateTime);
	}

	public String getDate() {
		if(dateTime == null)
			return "";

		return new SimpleDateFormat(ApplicationConfig.getInstance().getConfigValue("datePattern.tini")).format(dateTime);
	}

	public String getTime() {
		if(dateTime == null)
			return "";

		return new SimpleDateFormat(ApplicationConfig.getInstance().getConfigValue("timePattern.24.hour")).format(dateTime);
	}

	public void setDateTime(String dateTime) {
		try {
			this.dateTime = new SimpleDateFormat("MM/dd/yyyy-hh:mm").parse(dateTime);
		}
		catch(ParseException ex)
		{
			throw new RuntimeException("Invalid date time format.");
		}
	}

	public Integer getEndGradation() {
		return endGradation;
	}

	public void setEndGradation(Integer endGradation) {
		this.endGradation = endGradation;
	}

/*	public Integer[] getReading() {
		return reading;
	}

	public void setReading(Integer[] reading) {
		this.reading = reading;
		for(int i = 0; i < reading.length; i++)
			readings.add(reading[i]);
	}
*/

	public List<Integer> getReadings() {
		return iReadings;
	}

	public void setReadings1(String readings) {

		String[] readingArr = readings.split(" "); 
		
		for(int i = 0; i < readingArr.length; i++)
		{
			this.iReadings.add(Integer.parseInt(readingArr[i]));
		}
	}
	
	public String getReadings1() {
		
		if(iReadings.size() == 0)
			return "";
			
		StringBuffer strReadings = new StringBuffer();
		
		for(Integer reading: iReadings)
			strReadings.append(reading).append(" ");
		
		strReadings.setLength(strReadings.length() - 1) ;
		
		return strReadings.toString();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}

	public Integer getStartGradation() {
		return startGradation;
	}

	public void setStartGradation(Integer startGradation) {
		this.startGradation = startGradation;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	//-------------~ Methods
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		super.reset(arg0, arg1);
		iReadings = new ArrayList<Integer>();		
	}
	
	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
	
		if(interval == null || interval ==0)
			errors.add("interval", new ActionMessage(
							"common.error.field.required"));
			
		if(startGradation == null || startGradation ==0 || endGradation == null || endGradation ==0)
			errors.add("startGradation", new ActionMessage(
							"common.error.field.required"));
		//else if(endGradation < startGradation)
		//	errors.add("startGradation", new ActionMessage(
		//					"common.error.invalid.value"));
			
		if(errors.size() > 0)
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.message.header"));
		
		return errors;
	}
		
	public void populateFromModel(HeartRateMonitor heartRateMonitor){
		this.id = heartRateMonitor.getId();
		this.dateTime = heartRateMonitor.getDateTime();
		this.interval = heartRateMonitor.getInterval();
		this.startGradation = heartRateMonitor.getStartGradation();
		this.endGradation = heartRateMonitor.getEndGradation();
		this.iReadings = heartRateMonitor.getReadings();
	}
	
	public HeartRateMonitor populateToModel(){
		HeartRateMonitor heartRateMonitor = new HeartRateMonitor();
		
		heartRateMonitor.setId(this.id);
		heartRateMonitor.setUserId(this.userId);
		heartRateMonitor.setDateTime(this.dateTime);
		heartRateMonitor.setInterval(this.interval);
		heartRateMonitor.setStartGradation(this.startGradation);
		heartRateMonitor.setEndGradation(this.endGradation);
		heartRateMonitor.setReadings(this.iReadings);
		
		return heartRateMonitor;
	}
}

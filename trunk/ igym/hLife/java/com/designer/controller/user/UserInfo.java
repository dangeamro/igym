package com.designer.controller.user;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.designer.common.framework.ApplicationConfig;
import com.designer.common.framework.ApplicationException;
import com.designer.controller.customer.CustomerForm;
import com.designer.manager.message.MessagingManager;
import com.designer.model.message.Recipient;

@SuppressWarnings("serial")
public class UserInfo implements Comparable<UserInfo>{

	@SuppressWarnings("unused")
	private long miliPerDay = 1000*60*60*24; 
		
	private String userId = null;
	private String firstName = null;
	private String lastName = null;
	private String displayName = null;
	private String gender = null;
	private String emailAddress = null;
	private String dateOfBirth = null;
	private String dateOfJoining = null;
	private String dateOfStart = null;
	private Integer durationInWeeks = null;
	
	private List<String> roles = new ArrayList<String>();
	private List<String> groups = new ArrayList<String>();
	
	private String requestRootPath = null;
	private Integer entryType = -1;
	
	public UserInfo(Integer entryType)
	{
		this.entryType = entryType;
	}
	
	public UserInfo(CustomerForm cust)
	{
		this(Recipient.PERSON);
		userId = cust.getUserId();
		firstName = cust.getFirstName();
		lastName = cust.getLastName();
		gender = cust.getGender().equalsIgnoreCase("M")?"Male":"Female" ;
		emailAddress = cust.getEmailAddress();
		dateOfBirth = cust.getDob();
		dateOfJoining = cust.getDoj();
		dateOfStart = cust.getDos();
		durationInWeeks = cust.getDurationInWeeks();
		roles.add(cust.getRole());
	}

	// Getters and Setters
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public List<String> getRoles() {
		return roles;
	}
	public String getRolesAsString() {
		StringBuffer sb = new StringBuffer();
		for(String role:roles)
		{
			sb.append(role).append(", ");
		}
		sb.setLength(sb.length() - 2);
		return sb.toString();
	}
	public void addRole(String role) {
		this.roles.add(role);
	}
	public boolean belongsToRole(String roleTag)
	{
		return roles.contains(roleTag);
	}
	
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getDateOfStart() {
		return dateOfStart;
	}

	public void setDateOfStart(String dateOfStart) {
		this.dateOfStart = dateOfStart;
	}

	public Integer getDurationInWeeks() {
		return durationInWeeks;
	}

	public void setDurationInWeeks(Integer durationInWeeks) {
		this.durationInWeeks = durationInWeeks;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGroupsAsString() {
		StringBuffer sb = new StringBuffer();
		for(String group:groups)
		{
			sb.append(group).append(", ");
		}
		sb.setLength(sb.length() - 2);
		return sb.toString();
	}
	public List<String> getGroups() {
		return groups;
	}
	public void addGroup(String group) {
		this.groups.add(group);
	}
	public boolean belongsToGroup(String groupTag)
	{
		return roles.contains(groupTag);
	}
	
	
	public String getDisplayName()
	{
		if(displayName == null)
		{
			StringBuffer name = new StringBuffer(ApplicationConfig.getInstance().getConfigValue("nameDisplayFormat.full"));
			switch(entryType)
			{
			case Recipient.ROLE:
				displayName = '*' + userId;
				break;
			case Recipient.GROUP:
				displayName =  '+' + userId;
				break;
			case Recipient.PERSON:
				int index;
				while((index = name.indexOf("FN")) != -1)
					name.replace(index, index + 2, firstName);
				while((index = name.indexOf("MN")) != -1)
					name.replace(index, index + 2, "");
				while((index = name.indexOf("LN")) != -1)
					name.replace(index, index + 2, lastName);

				index = 0;
				int length = name.length();
				while(index < length && !Character.isLetterOrDigit(name.charAt(index)))
					name.setCharAt(index++, ' ');
				index = length - 1;
				while(index > 0 && !Character.isLetterOrDigit(name.charAt(index)))
					name.setCharAt(index--, ' ');

				displayName = name.toString().trim();
				break;
				
			default:
				displayName = userId;
			}
		}
		return displayName;
	}
	
	public String getAge() 
	{
		if(dateOfBirth == null)
			return "NA";
		
		Calendar calNow = Calendar.getInstance();
		Calendar calAtBirth = Calendar.getInstance();
		
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		
		try
		{
			calAtBirth.setTime( df.parse(this.dateOfBirth));
			Integer years = calNow.get(Calendar.YEAR) - calAtBirth.get(Calendar.YEAR);
			
			return years + " Years" ;
		}
		catch (ParseException e) {
			return "NA";
		}
		finally
		{
			calNow = null;
			calAtBirth = null;
			df = null;
		}
	}
	
	public Long getConsumedDurationInDays()
	{
		if(dateOfStart == null)
			return new Long(0);
		
		Long consumedDays;
		
		try
		{
			consumedDays = (System.currentTimeMillis() - (new SimpleDateFormat("MM/dd/yyyy").parse(this.dateOfStart)).getTime())/miliPerDay ;
		}
		catch (ParseException e) {
			consumedDays = new Long(0);
		}
		
		return consumedDays;
	}
	
	public String getConsumedDurationInWeeksAsString() 
	{
		Long consumedDays = getConsumedDurationInDays();
		
		if(consumedDays > durationInWeeks*7)
			return "Program duration over";

		if(consumedDays < 0)
			return "Program not started";
		
		if(consumedDays == 0)
			return "Starting today";
		
		StringBuffer result = new StringBuffer();
		if(consumedDays / 7 != 0)
			result.append(consumedDays / 7).append(" week").append(consumedDays / 7 == 1 ? " " : "s ");
		if(consumedDays % 7 != 0)
			result.append(consumedDays % 7).append(" day").append(consumedDays % 7 == 1 ? "" : "s");
		return (result.length() == 0? "0 days ":result.toString());
	}
	
	public Long getConsumedDurationInPercentage()
	{
		if(durationInWeeks == 0)
			return new Long(0);
		
		return  (getConsumedDurationInDays()*100)/(durationInWeeks * 7);
	}

	public Integer getUnreadMailCount()
	{
		Integer ret = 0;
		try
		{
			ret = MessagingManager.getInstance().getUnreadCount(userId);
		}
		catch(ApplicationException ex)
		{
			ex.printStackTrace();
		}
		
		return ret;
	}

	public Integer getEntryType() {
		return entryType;
	}

	public void setEntryType(Integer userType) {
		this.entryType = userType;
	}

	public String getRequestRootPath() {
		
		if(requestRootPath == null)
		{
			requestRootPath = ApplicationConfig.getInstance().getConfigValue("request.root.pattern." + roles.get(0));
		}
		return requestRootPath;
	}

	public int compareTo(UserInfo userInfo)
	{
		int result = this.getDisplayName().toLowerCase().compareTo(userInfo.getDisplayName().toLowerCase());
		if(result == 0)
			result = this.userId.compareToIgnoreCase(userInfo.userId);
		return result;
	}
	
	public boolean equals(Object o)
	{
		if(! (o instanceof UserInfo))
			return false;
		
		return this.userId.equals(((UserInfo)o).userId);
	}
	
}
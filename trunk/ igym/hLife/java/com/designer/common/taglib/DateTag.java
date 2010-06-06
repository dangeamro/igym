package com.designer.common.taglib;

import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.html.TextTag;

@SuppressWarnings("serial")
public class DateTag extends TextTag {


	private int startYear = 1950;

	private int dropDownYears = 100;

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	public void setDropDownYears(int dropDownYears) {
		this.dropDownYears = dropDownYears;
	}

	@Override
	public int doStartTag() throws JspException {
		Object field = TagUtils.getInstance().lookup(pageContext, name, property, null);
		if (field != null && field instanceof String) {
			setValue(field.toString());
			/*Date date = (Date)field;
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			setValue(df.format(date));*/
		}
		return super.doStartTag();
	}

	@Override
	public int doEndTag() throws JspException {
		TagUtils.getInstance().write(pageContext, "&nbsp;<img style=\"cursor:hand;\" ");
		TagUtils.getInstance().write(pageContext, "src='resources/images/calendar_icon.png' ");
		TagUtils.getInstance().write(pageContext, "onClick=\"javascript:scwShow(");
		TagUtils.getInstance().write(pageContext, property);
		TagUtils.getInstance().write(pageContext, ",this,");
		TagUtils.getInstance().write(pageContext, String.valueOf(startYear));
		TagUtils.getInstance().write(pageContext, ",");
		TagUtils.getInstance().write(pageContext, String.valueOf(dropDownYears));
		TagUtils.getInstance().write(pageContext, ");\" />");
		return super.doEndTag();
	}}

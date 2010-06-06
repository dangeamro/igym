package com.designer.controller.message;

import java.util.Set;
import java.util.TreeSet;

import org.apache.struts.action.ActionForm;

import com.designer.model.message.InboxRow;

@SuppressWarnings("serial")
public class InboxForm extends ActionForm{
	
	private Set<InboxRowForm> inboxRowForms = null;
	private Integer [] msgChkBox = null;

	public InboxForm()
	{
		inboxRowForms = new TreeSet<InboxRowForm>();
	}
	
	public Set<InboxRowForm> getInboxRows() {
		return inboxRowForms;
	}

	public void addInboxRow(InboxRowForm inboxRowForm) {
		this.inboxRowForms.add(inboxRowForm);
	}
	
	public Integer[] getMsgChkBox() {
		return msgChkBox;
	}

	public void setMsgChkBox(Integer[] msgChkBox) {
		this.msgChkBox = msgChkBox;
	}

	public void populateFromModel(com.designer.model.message.Inbox inbox)
	{
		for(InboxRow row: inbox.getInboxRows())
		{
			InboxRowForm inboxRowForm = new InboxRowForm();
			inboxRowForm.populateFromModel(row);
			addInboxRow(inboxRowForm);
		}
	}

}

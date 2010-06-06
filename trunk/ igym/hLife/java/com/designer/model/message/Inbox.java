package com.designer.model.message;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class Inbox {
	
	private Set<InboxRow> inboxRows = null;

	public Inbox()
	{
		inboxRows = new TreeSet<InboxRow>();
	}
	
	public Inbox(Collection<InboxRow> c)
	{
		this();
		inboxRows.addAll(c);
	}
	
	public Set<InboxRow> getInboxRows() {
		return inboxRows;
	}

	public void addInboxRow(InboxRow inboxRow) {
		this.inboxRows.add(inboxRow);
	}

}

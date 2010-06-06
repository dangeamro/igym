package com.designer.common.framework;

import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import com.designer.controller.user.UserInfo;
import com.designer.model.message.Recipient;

public class UserMap {
	
	private Map<String, UserInfo> map = new Hashtable<String, UserInfo>();
	private Collection<UserInfo> userlist = new TreeSet<UserInfo>();
	
	private static UserMap userMap;
	
	static {
		userMap = new UserMap();
	}
	
	public static UserMap getInstance()
	{
		return userMap;
	}

	public Map<String, UserInfo> getUserMap() {
		return map;
	}
	
	public boolean contains(String id)
	{
		return map.keySet().contains(id);
	}

	public boolean contains(Recipient r)
	{
		return contains(r.getRecipientId());
	}

	public boolean contains(UserInfo u)
	{
		return contains(u.getUserId());
	}

	protected void setUserMap(List<UserInfo> userList) {
		
		for(UserInfo userInfo:userList)
		{
			addUserInfo(userInfo);
		}
	}
	
	public Collection<UserInfo> getUserList() {
		
		return userlist;
	}
	
	public void addUserInfo(UserInfo userInfo)
	{
		map.put(userInfo.getUserId().toLowerCase(), userInfo);
		if(!(userInfo.belongsToRole("system") || userInfo.getUserId().equals("system")))
			userlist.add(userInfo);
	}
	public void removeUser(String userId)
	{
		userlist.remove(map.get(userId));
		map.remove(userId);
	}

	public UserInfo getUserInfo(String userId)
	{
		if(userId == null)
			return null;
		return map.get(userId.toLowerCase());
	}

	public Integer getCount()
	{
		return userlist.size();
	}

    protected void finalize()
    {
		userMap = this;
		System.out.println("UserMap was called by Garbage collector");
    }
}

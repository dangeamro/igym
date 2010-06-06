package com.designer.dao.user;

import java.util.List;

import com.designer.common.framework.ApplicationException;
import com.designer.controller.user.UserInfo;

public interface UserDAO {
	
	public List<UserInfo> getUsers() throws ApplicationException;
}

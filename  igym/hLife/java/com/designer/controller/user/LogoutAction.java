package com.designer.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class LogoutAction extends DispatchAction {

	public ActionForward doLogout(ActionMapping mapping, 
			ActionForm form, HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		
		HttpSession session = request.getSession(true); 
		
		session.removeAttribute("LOGIN_CREDENTIALS");
		session.removeAttribute("TARGET_USER");
		
		session.invalidate();
		
		return mapping.findForward("success");
	}

	
}

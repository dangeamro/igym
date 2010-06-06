package com.designer.common.framework;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.designer.controller.user.UserInfo;

public class applicationFilter implements Filter {

	    public void init(FilterConfig arg0) throws ServletException {
	    }

	    public void destroy() {
	    }

	    public void doFilter(ServletRequest request, ServletResponse response,
		    FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		HttpSession session = req.getSession(true);
		
		if(req.getServletPath().equals(ApplicationConfig.getInstance().getConfigValue("logout.pattern")))
			session.removeAttribute("LOGIN_CREDENTIALS");
		
		UserInfo userInfo = (UserInfo)session.getAttribute("LOGIN_CREDENTIALS");
		if ((userInfo == null || UserMap.getInstance().getUserInfo(userInfo.getUserId()) == null || req.getServletPath().indexOf(userInfo.getRequestRootPath()) != 0) && !req.getServletPath().equals("/login.do") && !req.getServletPath().equals("/getLogin.do")) {
		    String url = req.getContextPath() + "/getLogin.do";
		    res.sendRedirect(url);
		    return;
		}else {
			// TODO
			// If user for targetuserId is deleted, redirect the request to appropriate page
			String userId = request.getParameter("targetUserId");
			if(userId != null)
				request.setAttribute("TARGET_USER", UserMap.getInstance().getUserInfo(userId));
			else
				request.setAttribute("TARGET_USER", userInfo);
		}
			
		filterChain.doFilter(request, response);
	    }

	}


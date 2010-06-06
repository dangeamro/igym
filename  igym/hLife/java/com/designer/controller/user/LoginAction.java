package com.designer.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.designer.common.framework.ApplicationConfig;
import com.designer.common.framework.ApplicationException;
import com.designer.common.framework.InvalidLoginTracker;
import com.designer.common.framework.UserMap;
import com.designer.controller.customer.CustomerForm;
import com.designer.dao.customer.CustomerDAO;
import com.designer.dao.customer.CustomerDAOImpl;
import com.designer.model.customer.Customer;

public class LoginAction extends DispatchAction {
	
	private final int MAX_LOGIN_ATTEMPTS = Integer.parseInt(ApplicationConfig.getInstance().getConfigValue("maximumInvalidLoginAttempts"));
	
	public ActionForward doLogin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		LoginForm loginForm = (LoginForm)form;
		ActionMessages errors = new ActionMessages();
		
		boolean authenticated = false;
		String loginId = loginForm.getLoginId();
		String password = loginForm.getPassword();
		String forwardPath = null;
		CustomerDAO customerDAO = new CustomerDAOImpl();
		CustomerForm customerForm = new CustomerForm();
		
		try{
			Customer customer = customerDAO.getCustomerByUserId(loginId);
			
			if(customer != null)
			{				
				customerForm.populateFromModel(customer);
				if(customerForm.getPasswordForDatabase().equalsIgnoreCase(password)){
					authenticated = true;
				}
			}else{
				// No customer found with the given userId
				errors.add(ActionErrors.GLOBAL_MESSAGE,
						new ActionMessage("error.message.invalid.login"));
				
				saveErrors(request, errors);
				request.setAttribute("ATTEMPTED_USER_ID", loginId);
				return mapping.findForward("failure");
			}
		}catch(ApplicationException ae){
			
			errors.add(ActionErrors.GLOBAL_MESSAGE,
        			new ActionMessage(ae.getMessage()));
			
			saveErrors(request, errors);
			request.setAttribute("ATTEMPTED_USER_ID", loginId);
			return mapping.findForward("failure");
		}
		
		if(authenticated)
		{
            //Remove from invalid login tracker
            InvalidLoginTracker.removeInvalidUserLogin(loginId);

            // Save our logged-in user in the session
    		request.getSession().setAttribute("LOGIN_CREDENTIALS", UserMap.getInstance().getUserInfo(loginId));
    		// Remove the obsolete form bean
            if (mapping.getAttribute() != null) {
                if ("request".equals(mapping.getScope()))
                    request.removeAttribute(mapping.getAttribute());
                else
                    request.getSession().removeAttribute(mapping.getAttribute());
            }
            
    		if(customerForm.getRole().equalsIgnoreCase("admin"))
    			forwardPath = "successForAdmin";
    		else
    			forwardPath = "successForCustomer";
    		
		}else{
            //get invalid login attempt from Login Tracker for user
            int invalidAttempts = InvalidLoginTracker.getInvalidLoginAttempts(loginId);

            //Increment invalid attempts
            invalidAttempts++;

            //If login attempts exceeds MAX_LOGIN_ATTEMPTS, then disable the id
            if ( invalidAttempts > MAX_LOGIN_ATTEMPTS) {

                try {
                    //lock the user
                	customerDAO.lockUser(loginId);
                    errors.add(ActionErrors.GLOBAL_MESSAGE,
                            new ActionMessage("error.message.account.locked"));
                } catch (ApplicationException e) {
                    //do nothing

                }
            }
            else {
                InvalidLoginTracker.putInvalidUserLogin(loginId, invalidAttempts);
                errors.add(ActionErrors.GLOBAL_MESSAGE,
                			new ActionMessage("error.message.invalid.login"));
            }
            
            // Report any errors we have discovered back to the original form
            if (!errors.isEmpty())
                saveErrors(request, errors);
            request.setAttribute("ATTEMPTED_USER_ID", loginId);
            forwardPath = "failure";   
		}
	
		return mapping.findForward(forwardPath);
	}

}
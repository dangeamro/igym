package com.designer.controller.customer;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.designer.common.framework.ApplicationException;
import com.designer.common.framework.InvalidLoginTracker;
import com.designer.common.framework.UserMap;
import com.designer.controller.user.UserInfo;
import com.designer.dao.customer.CustomerDAO;
import com.designer.dao.customer.CustomerDAOImpl;
import com.designer.model.customer.Customer;

public class CustomerAction extends DispatchAction {

	public ActionForward loadUserDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){

		ActionMessages messages = new ActionMessages();

		String id = (String)request.getParameter("id");
		
		CustomerDAO customerDAO = new CustomerDAOImpl();
		
		Customer customer = null; 
		try
		{
			customer = customerDAO.getCustomerByUserId(id);
			if(customer != null)
			{
				CustomerForm userForm = new CustomerForm();
				userForm.populateFromModel(customer);
				request.setAttribute("customerForm", userForm);
			}
		}
		catch(ApplicationException ae)
		{
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(ae.getMessage()));
			saveErrors(request, messages);
			mapping.findForward("failure");
		}
		
		return mapping.findForward("success");
	}
	
	
	public ActionForward addCustomer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		ActionMessages messages = new ActionMessages();
		
		CustomerForm customerForm = (CustomerForm)form;
		
		CustomerDAO customerDAO = new CustomerDAOImpl();
		
		UserMap userMap = UserMap.getInstance();
		
		try{
			customerDAO.addCustomer(customerForm.populateToModel());
			userMap.addUserInfo(new UserInfo(customerForm));
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("info.user.created.succesfully"));
			saveErrors(request, messages);
			
		}catch (ApplicationException ae) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(ae.getMessage()));
			saveErrors(request, messages);
			return mapping.findForward("failure");
		}
		
		ActionForward fwd = null;
		ActionForward newFwd = new ActionForward();
		
		if(customerForm.getRole().equalsIgnoreCase("admin"))
		{
			fwd = mapping.findForward("successForAdminUser");
			newFwd.setPath(fwd.getPath());
		}
		else
		{
			fwd = mapping.findForward("successForCustomerUser");
			newFwd.setPath(fwd.getPath() + "&targetUserId=" + customerForm.getUserId());
		}
			
		newFwd.setRedirect(fwd.getRedirect());

		return newFwd;
	}
	
	public ActionForward searchCustomer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		ActionMessages messages = new ActionMessages();
		
		SearchCustomerForm searchCustomerForm = (SearchCustomerForm)form;
		
		CustomerDAO customerDAO = new CustomerDAOImpl();
		
		try{
			List<Customer> customerList = customerDAO.searchUser(searchCustomerForm.populateToModel(), CustomerDAO.USER_CUSTOMER);
			//System.out.println("# of Records = " + customerList.size());
			request.setAttribute("customerList",customerList);
		}catch (ApplicationException ae) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(ae.getMessage()));
			saveErrors(request, messages);
			return mapping.findForward("failure");
		}
		
		return mapping.findForward("success");
	}

	public ActionForward searchUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		SearchCustomerForm searchCustomerForm = (SearchCustomerForm)form;
		
		CustomerDAO customerDAO = new CustomerDAOImpl();
		
		try{
			List<Customer> customerList = customerDAO.searchUser(searchCustomerForm.populateToModel());
			//System.out.println("# of Records = " + customerList.size());
			request.setAttribute("customerList",customerList);
		}catch (ApplicationException ae) {
			return mapping.findForward("failure");
		}
		
		return mapping.findForward("success");
	}

	public ActionForward listCustomers(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		CustomerDAO customerDAO = new CustomerDAOImpl();
		List <CustomerForm> customerFormList = null;
		
		try{
			List<Customer> customerList = customerDAO.listCustomers(request.getParameter("sortOn"));
			if(customerList.size() > 0)
			{
				customerFormList = new ArrayList<CustomerForm>();
				for(Customer customer:customerList)
				{
					CustomerForm customerForm = new CustomerForm();
					customerForm.populateFromModel(customer);
					customerFormList.add(customerForm);
				}
			}
			
			request.setAttribute("customerList",customerFormList);
		}catch (ApplicationException ae) {
			return mapping.findForward("failure");
		}
		
		return mapping.findForward("success");
	}

	public ActionForward unlockCustomers(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		ActionMessages messages = new ActionMessages();
		
		CustomerDAO customerDAO = new CustomerDAOImpl();
		String[] lockedUserIds = request.getParameter("lockedIds").split(",");
		
		try{
			customerDAO.unlockCustomers(lockedUserIds);
			for(int i=0; i< lockedUserIds.length ; i++)
				InvalidLoginTracker.removeInvalidUserLogin(lockedUserIds[i]);				
		}catch(ApplicationException ae) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(ae.getMessage()));
			saveErrors(request, messages);
			return mapping.findForward("failure");
		}
		
		return mapping.findForward("success");
	}

	public ActionForward deleteCustomer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		ActionMessages messages = new ActionMessages();
		
		CustomerDAO customerDAO = new CustomerDAOImpl();
		
		try{
			customerDAO.deleteUser(request.getParameter("deleteUserId"));
			
		}catch(ApplicationException ae) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(ae.getMessage()));
			saveErrors(request, messages);
			request.setAttribute("ErrorMessage", "Error Message");
			return mapping.findForward("failure");
		}
		
		return mapping.findForward("success");
	}
		
	public ActionForward updateCustomer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){

		ActionMessages messages = new ActionMessages();
		
		CustomerForm customerForm = (CustomerForm)form;
		
		CustomerDAO customerDAO = new CustomerDAOImpl();
		
		UserMap userMap = UserMap.getInstance();
		
		try{
			customerDAO.updateCustomer(customerForm.populateToModel());
			userMap.addUserInfo(new UserInfo(customerForm));
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("info.user.created.succesfully"));
			saveErrors(request, messages);
			
		}catch (ApplicationException ae) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(ae.getMessage()));
			saveErrors(request, messages);
			ActionForward fwd = mapping.findForward("failure");
			ActionForward newFwd = new ActionForward();
			newFwd.setPath(fwd.getPath() + "&id=" + customerForm.getUserId());
			
			return newFwd;
		}
		
		return mapping.findForward("success");
	}

	public ActionForward changePassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){

		ActionMessages messages = new ActionMessages();
		
		ChangePasswordForm cpForm = (ChangePasswordForm)form; 
		
		CustomerDAO customerDAO = new CustomerDAOImpl();
		
		try {
			Customer customer = customerDAO.getCustomerByUserId(cpForm.getUserId());
			
			if(! customer.getPassword().equals(cpForm.getOldPassword()))
				throw new ApplicationException("error.message.wrong.old.password");				
			customerDAO.changePassword(cpForm.populateToModel());
			
		} catch (ApplicationException ae) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(ae.getMessage()));
			saveErrors(request, messages);
			return mapping.findForward("failure");
		}
		
		return mapping.findForward("success");
	}
		
}

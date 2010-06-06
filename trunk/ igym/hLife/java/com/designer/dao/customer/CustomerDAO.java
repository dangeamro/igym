package com.designer.dao.customer;

import java.util.List;

import com.designer.common.framework.ApplicationException;
import com.designer.model.customer.ChangePassword;
import com.designer.model.customer.Customer;
import com.designer.model.customer.SearchCustomer;

public interface CustomerDAO {
	
	public static final byte USER_ADMIN = 1;
	
	public static final byte USER_CUSTOMER = 2;

	public static final byte USER_ALL = 3;
	
	public void addCustomer(Customer customer) throws ApplicationException;
	
	public Customer getCustomerByUserId(String userId) throws ApplicationException;
	
	public void deleteUser(String userId) throws ApplicationException;
	
	public List<Customer> searchUser(SearchCustomer searchCustomer, byte searchFilter) throws ApplicationException;
	
	public List<Customer> searchUser(SearchCustomer searchCustomer) throws ApplicationException;
	
	public List<Customer> listCustomers(String sortOn) throws ApplicationException;
	
	public void lockUser(String userId) throws ApplicationException;
	
	public void unlockCustomers(String[] customerIds) throws ApplicationException;

	public void updateCustomer(Customer customer) throws ApplicationException;
	
	public void changePassword(ChangePassword cp) throws ApplicationException;
}

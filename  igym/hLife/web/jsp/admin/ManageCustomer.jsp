<%@ include file="/jsp/common/Taglibs.jsp" %>
<script>

	function deleteCustomer(userId)
	{
		if(confirm('Are you sure you want to delete userId ' + userId) == false)
		return;
		
		document.manageCustomerForm.action = "<html:rewrite  page='/admin/deleteCustomer.do?deleteUserId='/>" + userId;
		document.getElementsByName("method")[0].value = "deleteCustomer";
		document.manageCustomerForm.submit();
	}	
	
	function doUnlock()
	{
		var lockedChkBoxArray = document.getElementsByName("customerLockedChkBox");
		
		var lockedIds = '';
		
		for(var i=0; i<lockedChkBoxArray.length; i++)
			if(lockedChkBoxArray[i].checked)
				lockedIds += lockedChkBoxArray[i].value + ',';
		
		if(lockedIds.length == '')
		{
			alert('Not a valid selection. Can not unlock any user.');
			return false;
		}
		
		document.getElementsByName("lockedIds")[0].value = lockedIds;
	}
	
</script>
<form name="manageCustomerForm" action="<html:rewrite  page='/admin/unlockCustomers.do'/>" method="post">
<input type="hidden" name="method" value="unlockCustomers"/>
<input type="hidden" name="lockedIds" value=""/>
<table width="80%" align="center" cellspacing="2" cellpadding="2">
	<tr>
		<td height="10"/>
	</tr>
	<tr>
		<td align="center">
			<font class="error-header">
				<html:errors/>
			</font>
		</td>
	</tr>
	<tr>
		<td height="10"/>
	</tr>
	<tr>
		<td>
			<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%" class="content-panel-display-area">
				<tr>
					<td class="content-panel-h2">
						Manage Customers
					</td>
				</tr>
				<tr>
					<td height="10"></td>
				</tr>
				<logic:present name="customerList" scope="request">
					<tr>
						<td>
							<table width="98%" align="center" class="search-result-panel">
								<tr>
									<td class="search-result-header">
										UserId
									</td>
									<td class="search-result-header">
										First Name
									</td>
									<td class="search-result-header">
										Last Name
									</td>
									<td class="search-result-header">
										Type
									</td>
									<td class="search-result-header">
										Start Date
									</td>
									<td width="5%" class="search-result-header">
										Duration (weeks)
									</td>
									
									<td class="search-result-header">
										Email Address
									</td>
									<td class="search-result-header">
										Locked?
									</td>
									<td class="search-result-header">
										Delete
									</td>
								</tr>
								<bean:size id="size" name="customerList" scope="request"/>
								<logic:notEmpty name="customerList" scope="request">
									<logic:iterate id="customer" name="customerList" scope="request" indexId="rowNum">
										<tr>
											<td class="search-result-text-data" nowrap>${customer.userId}</td>
											<td class="search-result-text-data" nowrap><a href='<html:rewrite page="/admin/getUpdateCustomer.do?method=loadUserDetails&id=${customer.userId}" />'>${customer.firstName}</a></td>
											<td class="search-result-text-data" nowrap>${customer.lastName}</td>
											<td class="search-result-text-data" nowrap>${customer.role}</td>
											<td class="search-result-text-data" style="text-align: center;" nowrap>
												<logic:equal name="customer" property="role" value="Admin">
													NA
												</logic:equal>
												<logic:notEqual name="customer" property="role" value="Admin">
													${customer.dos}
												</logic:notEqual>
											</td>
											<td class="search-result-text-data" style="text-align: center;">
												<logic:equal name="customer" property="role" value="Admin">
													NA
												</logic:equal>
												<logic:notEqual name="customer" property="role" value="Admin">
													${customer.durationInWeeks}
												</logic:notEqual>
											</td>
											<td class="search-result-text-data" nowrap>${customer.emailAddress}</td>
											<td class="search-result-text-data" style="text-align: center;" >
												<input type="checkbox" name="customerLockedChkBox" value="${customer.userId}" ${customer.locked? '':'disabled'} >
											</td>
											<td class="search-result-text-data" style="text-align: center;" >
												<logic:notEqual name="customer" property="userId" value="${LOGIN_CREDENTIALS.userId}">
													<img style="cursor:hand;" align="middle" width="20" src='<html:rewrite page="/resources/images/delete.gif"/>' onClick="javascript:deleteCustomer('${customer.userId}')"/>
												</logic:notEqual>
												<logic:equal name="customer" property="userId" value="${LOGIN_CREDENTIALS.userId}">
													<img align="middle" width="20" src='<html:rewrite page="/resources/images/delete-disabled.gif"/>'/>
												</logic:equal>
											</td>
										</tr>
									</logic:iterate>
								</logic:notEmpty>
								<logic:empty name="customerList" scope="request">
									<tr>
										<td colspan="8" height="60" class="search-result-text-data" style="text-align: center;font-family: Arial; font-size: 13;">
											No matching records found for the given search. Please consider changing the search criteria.
										</td>
									</tr>
								</logic:empty>
							</table>
						</td>
					</tr>
				<tr>
					<td height="10"></td>
				</tr>
				<tr>
					<td align="right" style="padding-right: 10px">
						<input type="submit" value="Unlock Selected" onclick="return doUnlock()">
					</td>
				</tr>
				<tr>
					<td height="10"></td>
				</tr>

				</logic:present>
			</table>
		</td>
	</tr>
</table>
</form>
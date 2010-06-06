<%@ include file="/jsp/common/Taglibs.jsp" %>
<script src='<html:rewrite page="/resources/js/scw.js"/>'></script>
<html:form action="/admin/searchCustomer.do" method="post">
<input type="hidden" name="method" value="searchCustomer"/>
<table width="90%" align="center" cellspacing="2" cellpadding="2">
	<tr>
		<td align="center">
			<font class="error-header">
				<html:errors property="org.apache.struts.action.GLOBAL_MESSAGE"/>
			</font>
		</td>
	</tr>
	<tr>
		<td>
			<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%" class="content-panel-display-area">
				<tr>
					<td class="content-panel-h2">
						Customer Search
					</td>
				</tr>
				<tr>
					<td>
						<table align="center" border="0" cellpadding="2" cellspacing="2" width="100%">
							<tr>	
								<td colspan="4" height="10">
								</td>
							</tr>
							<tr>
								<td class="content-panel-label">
									First Name
								</td>
								<td class="content-panel-data">
									<html:text property="firstName" />
								</td>
								<td class="content-panel-label">
									Joining Date<font class="content-panle-sub-label"> (after)</font>
								</td>
								<td class="content-panel-data">
									<html:text property="doj" readonly="true" onfocus="javascript:scwShow(scwID('doj'),this)"/>
									<img style="cursor:hand;" src='<html:rewrite page="/resources/images/calendar.gif"/>' onClick="javascript:scwShow(scwID('doj'),this)"/>
								</td>
								<td class="content-panel-label">
									Email Address
								</td>
								<td class="content-panel-data">
									<html:text property="emailAddress" />
								</td>
							</tr>
							<tr>
								<td class="content-panel-label">
									Last Name
								</td>
								<td class="content-panel-data">
									<html:text property="lastName" />
								</td>
								<td class="content-panel-label">
									Start Date<font class="content-panle-sub-label"> (after)</font>
								</td>
								<td class="content-panel-data">
									<html:text property="dos" readonly="true" onfocus="javascript:scwShow(scwID('dos'),this)"/>
									<img style="cursor:hand;" src='<html:rewrite page="/resources/images/calendar.gif"/>' onClick="javascript:scwShow(scwID('dos'),this)"/>
								</td>
								<td class="content-panel-label">
									Goals
								</td>
								<td class="content-panel-data">
									<html:text property="courses"/>
								</td>
							</tr>
							<tr>	
								<td colspan="4" height="10">
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td align="center">
			<html:submit value="Search" style="font-weight: bolder;"/>
			<html:reset value="Reset"/>			
		</td>
	</tr>
	<logic:present name="customerList" scope="request">
		<tr>
			<td>
				<table width="100%" class="search-result-panel">
					<tr>
						<td class="search-result-header">
							Serial #
						</td>
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
							Date of Joining
						</td>
						<td class="search-result-header">
							Date of Start
						</td>
						<td class="search-result-header">
							Goals
						</td>
						<td class="search-result-header">
							Email Address
						</td>
					</tr>
					<bean:size id="size" name="customerList" scope="request"/>
					<logic:notEmpty name="customerList" scope="request">
						<logic:iterate id="customer" name="customerList" scope="request" indexId="rowNum">
							<tr>
								<td class="search-result-text-data" nowrap>${rowNum + 1}</td>
								<td class="search-result-text-data" nowrap>${customer.userId}</td>
								<td class="search-result-text-data" nowrap><a href='<html:rewrite page="/admin/getCustomerReportCard.do?method=getReportCard&targetUserId=${customer.userId}" />'>${customer.firstName}</a></td>
								<td class="search-result-text-data" nowrap>${customer.lastName}</td>
								<td class="search-result-text-data" nowrap>${customer.doj}</td>
								<td class="search-result-text-data" nowrap>${customer.dob}</td>
								<td class="search-result-text-data" nowrap>${customer.courses}</td>
								<td class="search-result-text-data" nowrap>${customer.emailAddress}</td>
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
	</logic:present>
</table>
</html:form>
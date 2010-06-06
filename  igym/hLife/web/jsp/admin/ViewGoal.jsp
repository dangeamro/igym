<%@ include file="/jsp/common/Taglibs.jsp" %>
<%
	String fileName="";
	if (request.getAttribute("fileName") != null){
		fileName=(String)request.getAttribute("fileName");
	}
	String graphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" + fileName;
%>

<form name="viewGoal" action="<html:rewrite page="/admin/getAddGoal.do" />" method="post">
<input type="hidden" name="method" value="">
<input type="hidden" name="targetUserId" value="${TARGET_USER.userId}">
<table align="center" width="80%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td>
			<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%" class="customer-name-panel">
				<tr>
					<td colspan="4" class="customet-name-panel">
						${TARGET_USER.displayName}
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
	    <td height="5"></td>
	</tr>
	<tr>
	    <td align="center">
	      <font class="error-header" style="font-size: 13;">
	        	<html:errors/>
	      </font>
	    </td>
	</tr>
	<tr>
		<td>
			<table align="center" border="0" cellpadding="2" cellspacing="0" width="100%" class="content-panel-display-area" style="margin-top: 5px;">
				<tr>
					<td colspan="6" class="content-panel-h2">Goal - ${goalForm.goalName}
					</td>
				</tr>
				<logic:present name="goalForm" scope="request">
				<tr>
					<td colspan="6" height="20"></td>
				</tr>
				<!-- tr>
					<td class="content-panel-label" nowrap>
						Start Date
					</td>
					<td class="content-panel-data" nowrap>
						${goalForm.startDate}
					</td>
					<td class="content-panel-label" nowrap>
						End Date
					</td>
					<td class="content-panel-data" nowrap>
						${goalForm.endDate}
					</td>
					<td class="content-panel-label" nowrap>
						Occupation
					</td>
					<td class="content-panel-data" nowrap>
						IT Engineer
					</td>
				</tr>
				<tr>
					<td class="content-panel-label" nowrap>
						Goal Duratrion
					</td>
					<td class="content-panel-data" nowrap>
						<label class="blue">${goalForm.numberOfDays}</label>
					</td>
					<td class="content-panel-label" nowrap>
						Completed days
					</td>
					<td class="content-panel-data" nowrap>
						<label class="red">${goalForm.completedDays}</label>
					</td>
					<td class="content-panel-label" nowrap>
						Remaining days
					</td>
					<td class="content-panel-data" nowrap>
						<label class="green">${goalForm.remainingDays}</label>
					</td>
				</tr>
				<tr>
					<td colspan="6" height="15"/>
				</tr>
				<tr>
					<td colspan="6">
						<table width="95%" border="0" cellspacing="0"
							cellpadding="0" align="center">
							<tr>
								<td align="center" class="sub-heading">Goal Summary</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="6" height="5"/>
				</tr>			
				<tr>
					<td colspan="6">
						<table align="center">
							<tr>
								<td class="content-panel-label" nowrap style="text-align: center;">
									<label class="red">Initial Reading</label>
								</td>
								<td class="content-panel-label" nowrap style="text-align: center;">
									-&nbsp;&nbsp;<label class="green">Goal Reading</label>
								</td>
								<td class="content-panel-label" nowrap style="text-align: center;">
									=&nbsp;&nbsp;<label class="blue">Total Change</label>
								</td>
							</tr>
							<tr>
								<td class="content-panel-label" nowrap style="text-align: center;">
									<label class="red">${goalForm.startReading}<font class="content-panel-sub-label">${goalForm.readingUnit}</label></label>
								</td>
								<td class="content-panel-label" nowrap style="text-align: center;">
									<label class="green">${goalForm.endReading}<font class="content-panel-sub-label">${goalForm.readingUnit}</label></label>
								</td>
								<td class="content-panel-label" nowrap style="text-align: center;">
									<label class="blue">${goalForm.endReading - goalForm.startReading}<font class="content-panel-sub-label">${goalForm.readingUnit}</label></label>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="6" height="10"/>
				</tr>
				<tr>
					<td colspan="6">
						<table align="center">
							<tr>
								<td class="content-panel-label" nowrap style="text-align: center;">
									<label class="black">Total Change</label>
								</td>
								<td class="content-panel-label" nowrap style="text-align: center;">
									/&nbsp;&nbsp;<label class="black">Goal Duration</label>
								</td>
								<td class="content-panel-label" nowrap style="text-align: center;">
									=&nbsp;&nbsp;<label class="blue">Avg Daily Change</label>
								</td>
								<td class="content-panel-label" nowrap style="text-align: center;">
									*&nbsp;&nbsp;<label class="black">7</label>
								</td>
								<td class="content-panel-label" nowrap style="text-align: center;">
									=&nbsp;&nbsp;<label class="blue">Avg Weekly Change</label>
								</td>
							</tr>
							<tr>
								<td class="content-panel-label" nowrap style="text-align: center;">
									<label class="black">${goalForm.startReading - goalForm.endReading}<font class="content-panel-sub-label">${goalForm.readingUnit}</label></label>
								</td>
								<td class="content-panel-label" nowrap style="text-align: center;">
									<label class="black">${goalForm.numberOfDays}</label>
								</td>
								<td class="content-panel-label" nowrap style="text-align: center;">
									<label class="blue">${goalForm.avgDailyChange}<font class="content-panel-sub-label">${goalForm.readingUnit}</font></label>&nbsp;<label class="black">approx</label>
								</td>
								<td>
								</td>
								<td class="content-panel-label" nowrap style="text-align: center;">
									<label class="blue">${goalForm.avgDailyChange * 7}<font class="content-panel-sub-label">${goalForm.readingUnit}</label></label>&nbsp;<label class="black">approx</label>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="6" height="15"/>
				</tr>
				<tr>
					<td colspan="6">
						<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
							<tr>
								<td class="sub-heading">Calorie Balance Equation <label class="content-panel-sub-label">(per day)</label></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="6" height="5"/>
				</tr>
				<tr>
					<td colspan="6" nowrap>
						<table align="center">
							<tr>
								<td class="content-panel-label" nowrap style="text-align: center;">
									<label class="red">Calories You Burn</label>
								</td>
								<td class="content-panel-label" nowrap style="text-align: center;">
									+&nbsp;&nbsp;<label class="green">Calorie Restriction</label>
								</td>
								<td class="content-panel-label" nowrap style="text-align: center;">
									=&nbsp;&nbsp;<label class="blue">Calories you can eat</label>
								</td>
							</tr>
							<tr>
								<td class="content-panel-label" nowrap style="text-align: center;">
									<label class="red">${goalForm.calorieRestrictionPerDay}<font class="content-panel-sub-label">cal</font></label>
								</td>
								<td class="content-panel-label" nowrap style="text-align: center;">
									<label class="green">${goalForm.caloriesBurntPerDay}<font class="content-panel-sub-label">cal</font></label>
								</td>
								<td class="content-panel-label" nowrap style="text-align: center;">
									<label class="blue">${goalForm.calorieRestrictionPerDay - goalForm.caloriesBurntPerDay}<font class="content-panel-sub-label">cal</font></label>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="6" height="15"/>
				</tr>
				<tr>
					<td colspan="6">
						<table width="95%" border="0" cellspacing="0"
							cellpadding="0" align="center">
							<tr>
								<td align="center" class="sub-heading">Macronutrient Balance Equation</td>
							</tr>
						</table>
					</td>
				</tr-->
				<tr>
					<td colspan="6" height="5"/>
				</tr>
				<tr>
					<td colspan="6" nowrap>
						<table width="80%" align="center" style="background-color: #FFF3F4; border: 1px ridge;">
							<tr>
								<td class="content-panel-label" nowrap style="text-align: center;">
									<img src="<%= graphURL %>" width=500 height=300 border=0 usemap="#<%= fileName %>">
								</td>
							</tr>
						</table>
					</td>
				</tr>				
				<tr>
					<td colspan="6" height="20"></td>
				</tr>
				
				</logic:present>
				<logic:notPresent name="goalForm" scope="request">
					<tr>
						<td height="60" class="search-result-text-data" style="text-align: center;font-family: Arial; font-size: 13;">
							No Goals have been created for this  User.
						</td>
					</tr>
				</logic:notPresent>
			</table>
		</td>
	</tr>
	<tr>
		<td height="20"></td>
	</tr>
	<tr>
		<td align="center">
			<input type="submit" value="New Goal"/>
		</td>
	</tr>
	
</table>
</form>
	
	
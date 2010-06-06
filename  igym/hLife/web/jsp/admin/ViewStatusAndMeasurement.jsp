<%@ include file="/jsp/common/Taglibs.jsp" %>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%
	int pageCount=0;

	if(request.getAttribute("pageCount")!=null){
		pageCount=((Integer)request.getAttribute("pageCount")).intValue();
	}

	String meal_Date="";
	if(request.getAttribute("MEAL_DATE") != null)
		 meal_Date=(String)request.getAttribute("MEAL_DATE");
%>

<script language="javascript">

	function doSubmit(offSet){
		document.getUpdateStatusAndMeasurement.method.value="getStatusAndMeasurement";
		document.getUpdateStatusAndMeasurement.action="getStatusAndMeasurements.do?pager.offset="+(offSet-1);
		document.getUpdateStatusAndMeasurement.pageNum.value=(offSet);
		document.getUpdateStatusAndMeasurement.submit();
	}
	function deleteRecord(){
		if(confirm("Do you really want to DELETE this Status Record"))
		{
			document.getUpdateStatusAndMeasurement.action="<html:rewrite page='/admin/deleteStatusAndMeasurements.do?statusId=${statusAndMeasurementForm.statusId}' />";
			document.getUpdateStatusAndMeasurement.method.value="deleteStatusAndMeasurement";
			document.getUpdateStatusAndMeasurement.pageNum.value=1;
			document.getUpdateStatusAndMeasurement.submit();
		} else {
			return false;
		}
	}
	function Automeasurements(){
			document.getUpdateStatusAndMeasurement.action="<html:rewrite page='/admin/getAutoStatusAndMeasurements.do' />";
			document.getUpdateStatusAndMeasurement.method.value="getAutoStatusAndMeasurement";
			document.getUpdateStatusAndMeasurement.submit();
		
	}
</script>	
<form name="getUpdateStatusAndMeasurement" action="<html:rewrite page="/admin/getAddStatusAndMeasurements.do" />" method="post">
	<input type="hidden" name="method" value="getAddStatusAndMeasurement" />
	<input type="hidden" name="mealId" />
	<input type="hidden" name="pageNum" />
	<input type="hidden" name="targetUserId" value="${TARGET_USER.userId}" />
	
	<table align="center" width="80%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td width="*" class="customer-name-panel">
							${TARGET_USER.displayName}
						</td>
						<td width="3%">
							<a href='<html:rewrite page="/admin/getUserMailCompose.do?method=getCompose&targetUserId=${TARGET_USER.userId}"/>'>
								<img class="email-icon" src='<html:rewrite page="/resources/images/send-mail.jpg"/>' title="send a mail to ${TARGET_USER.displayName}">
							</a>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td height="10"></td>
		</tr>
		<tr>
		  <td align="center">
		   <font class="error" style="font-size: 13;">
		    	<html:errors/>
		   </font>
		  </td>
		</tr>
		<tr>
			<td height="10"></td>
		</tr>
		<logic:greaterThan name="pageCount" value="1" scope="request">
		<tr>
			<td>
				<table align="right" border="0" cellpadding="0" cellspacing="2" class="pager-panel">
					<tr>
						<td>
							<!-- Paginatin code starts Here -->			
							<pg:pager items="<%= pageCount %>" index="center" maxPageItems="1" maxIndexPages="6" export="offset,currentPageNumber=pageNumber" scope="request">
							<!-- items will have max rowcount -->
							
							 <input type="hidden" name="pager.offset" value="<%= offset %>" />
					
							 
							 <pg:param name="pg"/>
							 <pg:param name="q"/>
						 
						 	 <pg:index>
						 	 	<font class="pager-item">
							  <pg:prev><a href="#" onClick="doSubmit(<%= pageNumber %>)">&lt;&lt;</a></pg:prev></font>
							  <pg:pages><%
						   
							   if (pageNumber == currentPageNumber) { 
							    %><font class="pager-item-current"><%= pageNumber %></font><%
							   } else { 
							    %><font class="pager-item"><a href="#" onClick="doSubmit(<%= pageNumber %>)"><%= pageNumber %></a></font><%
							   }
							  %>
							  </pg:pages>
							  <font class="pager-item">
							  	<pg:next><a href="#" onClick="doSubmit(<%= pageNumber %>)">&gt;&gt;</a></pg:next>
							  </font>
							 </pg:index>
							</pg:pager>
							<!-- Paginatin code Ends Here -->
						</td>
					</tr>
				</table>
			</td>
		</tr>
		</logic:greaterThan>
		<tr>
			<td>
				<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%" class="content-panel-display-area" style="margin-top: 5px;">
					<tr>
						<td class="content-panel-h2">Manual Measurements 
						<logic:present name="statusAndMeasurementForm" scope="request">
							${statusAndMeasurementForm.date }
						</logic:present>
						</td>
					</tr>	
					<logic:present name="statusAndMeasurementForm" scope="request">
					<tr>
						<td colspan="2">
							<table align="center" border="0" cellpadding="3" cellspacing="0" width="100%">
								<tr>
									<td>
										<table align="center" border="0" cellpadding="3" cellspacing="0" width="100%">
											<tr>
												<td height="15"></td>
											</tr>
											<tr>
												<td class="content-panel-label"> Weight</td>
												<td class="content-panel-data">
													${statusAndMeasurementForm.weight}
													<font class="content-panel-sub-label">lbs</font>
												</td>
											</tr>
											<tr>
												<td class="content-panel-label">Neck :</td>
												<td class="content-panel-data">
													${statusAndMeasurementForm.neck}
													<font class="content-panel-sub-label">cms</font>
												</td>
												<td class="content-panel-label">Upper Chest :</td>
												<td class="content-panel-data">
													${statusAndMeasurementForm.upperChest}
													<font class="content-panel-sub-label">cms</font>
												</td>
											</tr>
											<tr>
												<td class="content-panel-label">Upper Arms - Right :</td>
												<td class="content-panel-data">
													${statusAndMeasurementForm.uppArmRight}
													<font class="content-panel-sub-label">cms</font>
												</td>
												<td class="content-panel-label">Upper Arms - Left :</td>
												<td class="content-panel-data">
													${statusAndMeasurementForm.uppArmLeft}
													<font class="content-panel-sub-label">cms</font>
												</td>
											</tr>
											<tr>
												<td class="content-panel-label">Waist :</td>
												<td class="content-panel-data">${statusAndMeasurementForm.waist}
													<font class="content-panel-sub-label">cms&nbsp;*(Around Belly Button Line)</font>
												</td>
												<td class="content-panel-label">Hips :</td>
												<td class="content-panel-data">
													${statusAndMeasurementForm.hips}
													<font class="content-panel-sub-label">cms&nbsp;*(Around Hip Joint Line)</font>
												</td>
											</tr>
											<tr>
												<td class="content-panel-label">Upper Thighs - Right :</td>
												<td class="content-panel-data">${statusAndMeasurementForm.uppThighsRight}
													<font class="content-panel-sub-label">cms&nbsp;*(Under Butt Cheek)</font>
												</td>
												<td class="content-panel-label">Upper Thighs - Left :</td>
												<td class="content-panel-data">${statusAndMeasurementForm.uppThighsLeft}
													<font class="content-panel-sub-label">cms&nbsp;*(Under Butt Cheek)</font>
												</td>
											</tr>
											<tr>
												<td class="content-panel-label">Lower Thighs - Right :</td>
												<td class="content-panel-data">${statusAndMeasurementForm.lowThighsRight}
													<font class="content-panel-sub-label">cms&nbsp;*(Above Knee)</font>
												</td>
												<td class="content-panel-label">Lower Thighs - Left :</td>
												<td class="content-panel-data">${statusAndMeasurementForm.lowThighsLeft}
													<font class="content-panel-sub-label">cms&nbsp;*(Above Knee)</font>
												</td>				
											</tr>
											<tr>
												<td class="content-panel-label">Calves - Right :</td>
												<td class="content-panel-data">
													${statusAndMeasurementForm.calvesRight}
													<font class="content-panel-sub-label">cms</font>
												</td>
												<td class="content-panel-label">Calves - Left :</td>
												<td class="content-panel-data">
													${statusAndMeasurementForm.calvesLeft}
													<font class="content-panel-sub-label">cms</font>
												</td>
											</tr>
											<tr>
												<td class="content-panel-label">Ankles - Right :</td>
												<td class="content-panel-data">
													${statusAndMeasurementForm.anklesRight}
													<font class="content-panel-sub-label">cms</font>
												</td>
												<td class="content-panel-label">Ankles - Left :</td>
												<td class="content-panel-data">
													${statusAndMeasurementForm.anklesLeft}
													<font class="content-panel-sub-label">cms</font>
												</td>
											</tr>
											<tr>
												<td height="15"></td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					</logic:present>
					<logic:notPresent name="statusAndMeasurementForm" scope="request">
					<tr>
						<td height="60" class="search-result-text-data" style="text-align: center;font-family: Arial; font-size: 13;">
							No Assesment and Progress records found.
						</td>
					</tr>
					</logic:notPresent>			
				</table>
			</td>
		</tr>
		<logic:greaterThan name="pageCount" value="1" scope="request">
		<tr>
			<td>
				<table align="right" border="0" cellpadding="0" cellspacing="2" class="pager-panel" style="margin-top: 5px;">
					<tr>
						<td>
							<!-- Paginatin code starts Here -->			
							<pg:pager items="<%= pageCount %>" index="center" maxPageItems="1" maxIndexPages="6" export="offset,currentPageNumber=pageNumber" scope="request">
							<!-- items will have max rowcount -->
							
							 <input type="hidden" name="pager.offset" value="<%= offset %>" />
					
							 
							 <pg:param name="pg"/>
							 <pg:param name="q"/>
						 
						 	 <pg:index>
						 	 	<font class="pager-item">
							  <pg:prev><a href="#" onClick="doSubmit(<%= pageNumber %>)">&lt;&lt;</a></pg:prev></font>
							  <pg:pages><%
						   
							   if (pageNumber == currentPageNumber) { 
							    %><font class="pager-item-current"><%= pageNumber %></font><%
							   } else { 
							    %><font class="pager-item"><a href="#" onClick="doSubmit(<%= pageNumber %>)"><%= pageNumber %></a></font><%
							   }
							  %>
							  </pg:pages>
							  <font class="pager-item">
							  	<pg:next><a href="#" onClick="doSubmit(<%= pageNumber %>)">&gt;&gt;</a></pg:next>
							  </font>
							 </pg:index>
							</pg:pager>
							<!-- Paginatin code Ends Here -->
						</td>
					</tr>
				</table>
			</td>
		</tr>
		</logic:greaterThan>
		<tr>
			<td height="5"></td>
		</tr>
		<tr>
			<td align="center">
				<input type="submit" value="Add New" style="font-weight: bolder; width: 80;"/>
				<input type="button" value="Delete" onclick="javascript:deleteRecord();"/>
				<input type="button" value="Automatic Measurements" style="width: 160;" onclick="javascript:Automeasurements();"/>
			</td>
		</tr>
	</table>
</form>
<%@ include file="/jsp/common/Taglibs.jsp" %>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@ page import="java.util.ArrayList"%>

<%
	int pageCount=0;
	
	if(request.getAttribute("pageCount")!=null){
		pageCount=((Integer)request.getAttribute("pageCount")).intValue();
	}
	
	String meal_Date="";
	if(request.getAttribute("MEAL_DATE") != null)
	 meal_Date=(String)request.getAttribute("MEAL_DATE");

	ArrayList fileNames = new ArrayList();
	if (request.getAttribute("fileNames") != null){
		fileNames =(ArrayList) request.getAttribute("fileNames");
	}
	else{
		System.out.println("Attribute NOT GOt in Jsp");
	}
	
	String[] graphURL= new String[15];
	if(fileNames != null && fileNames.size()>0){
		int count=0;
		
		while (count < fileNames.size()){			
			graphURL[count] = request.getContextPath() + "/servlet/DisplayChart?filename=" + fileNames.get(count);
			count++;
		}
	}
%>
<script src='<html:rewrite page="/resources/js/overlib.js"/>'></script>
<script>
	function doSubmit(offSet){
		document.getAutoStatusAndMeasurement.method.value="getAutoStatusAndMeasurement";
		document.getAutoStatusAndMeasurement.action="getAutoStatusAndMeasurements.do?pager.offset="+(offSet-1);
		document.getAutoStatusAndMeasurement.pageNum.value=(offSet);
		document.getAutoStatusAndMeasurement.submit();
	}
	function compareProgress(){
		document.getAutoStatusAndMeasurement.method.value="compareAutoStatusAndMeasurement";
		document.getAutoStatusAndMeasurement.action="<html:rewrite page='/admin/compareAutoStatusAndMeasurements.do'/>";
		document.getAutoStatusAndMeasurement.submit();
	}
</script>

<form name="getAutoStatusAndMeasurement" action="<html:rewrite page="/admin/getStatusAndMeasurements.do" />" method="post">
<input type="hidden" name="method" value="getStatusAndMeasurement" />
<input type="hidden" name="pageNum" />
<input type="hidden" name="targetUserId" value="${TARGET_USER.userId}" />

<table align="center" width="95%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td>
			<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td width="*" colspan="4" class="customer-name-panel">
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
						<td class="content-panel-h2">Automatic Measurements
						<logic:present name="statusAndMeasurementForm" scope="request">
							${statusAndMeasurementForm.date_Time }
						</logic:present>
						</td>
					</tr>	
					<logic:present name="statusAndMeasurementForm" scope="request">
					<tr>
						<td>
							<table width="100%">
								<tr>
									<td width="12%" class="content-panel-label"> Name</td>
									<td width="*" class="content-panel-data">
											${TARGET_USER.displayName}
									</td>
									<td width="12%" class="content-panel-label"> Age</td>
									<td width="12%" class="content-panel-data">${statusAndMeasurementForm.age}</td>
									<td width="12%" class="content-panel-label"> Gender </td>
									<td width="12%" class="content-panel-data">${statusAndMeasurementForm.sex}</td>
									<td width="12%" class="content-panel-label"> Height</td>
									<td width="12%" class="content-panel-data">${statusAndMeasurementForm.height}</td>
								</tr>
								<tr>
									<td colspan="8">
										<table align="center" border="0" cellpadding="3" cellspacing="0" width="100%">
											<tr>
												<td height="5"></td>
											</tr>
											<tr>
												<td>
													<table align="center" border="0" cellpadding="2" cellspacing="2" width="100%" style="background-color: #FFF3F4; border: 1px ridge;">
														<tr>
															<th colspan="2" style="color: #024788; background-color: #A88E51; font-family: Arial; font-size: 14px; font-weight: bold">
																Body Composition Analysis
															</th>
														</tr>
														<tr>
															<td width="20%" nowrap style="background-color: #EDD9B6; font-family: Verdana; font-size: 12px;">Weight</td>
															<td><img src="<%= graphURL[0] %>" width=300 height=50 border=0 usemap="#<%= fileNames.get(0) %>"> </td>
														</tr>
														<tr>
															<td width="20%" nowrap style="background-color: #E8DBBB; font-family: Verdana; font-size: 12px;">Lean Body Mass</td>
															<td><img src="<%= graphURL[1] %>" width=300 height=50 border=0 usemap="#<%= fileNames.get(1) %>"> </td>
														</tr>
														<tr>
															<td width="20%" nowrap style="background-color: #E0DEAE; font-family: Verdana; font-size: 12px;">Body Fat Mass</td>
															<td><img src="<%= graphURL[2] %>" width=300 height=50 border=0 usemap="#<%= fileNames.get(2) %>"> </td>
														</tr>
													</table>
												</td>
												<td>
													<table align="center" border="0" cellpadding="2" cellspacing="2" width="100%" style="background-color: #FFF3F4; border: 1px ridge;">
														<tr>
															<th colspan="2" style="color: #024788; background-color: #A0A056; font-family: Arial; font-size: 14px; font-weight: bold">
																Body Water Balance
															</th>
														</tr>
														<tr>
															<td width="20%" nowrap style="background-color: #DCE9BB; font-family: Verdana; font-size: 12px;">Intracellular Water</td>
															<td><img src="<%= graphURL[3] %>" width=300 height=50 border=0 usemap="#<%= fileNames.get(3) %>"> </td>
														</tr>
														<tr>
															<td width="20%" nowrap style="background-color: #D5E2C6; font-family: Verdana; font-size: 12px;">Extracellular Water</td>
															<td><img src="<%= graphURL[4] %>" width=300 height=50 border=0 usemap="#<%= fileNames.get(4) %>"> </td>
														</tr>
														<tr>
															<td width="20%" nowrap style="background-color: #CBDDDF; font-family: Verdana; font-size: 12px;">Total Body Water</td>
															<td><img src="<%= graphURL[5] %>" width=300 height=50 border=0 usemap="#<%= fileNames.get(5) %>"> </td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
										<td>
											<table align="center" border="0" cellpadding="2" cellspacing="2" width="100%" style="background-color: #FFF3F4; border: 1px ridge;">
												<tr>
													<th colspan="4" style="color: #024788; background-color: #73A7CC; font-family: Arial; font-size: 14px; font-weight: bold">
														Segmental Lean Development
													</th>
												</tr>
												<tr>
													<td width="20%" nowrap style="background-color: #CAD8E3; font-family: Verdana; font-size: 12px;">Right Arm</td>
													<td><img src="<%= graphURL[6] %>" width=300 height=70 border=0 usemap="#<%= fileNames.get(6) %>"> </td>
													<!--/tr>
												<tr-->
												</tr>
												<tr>
													<td width="20%" nowrap style="background-color: #E0DDE4; font-family: Verdana; font-size: 12px;">Left Arm</td>
													<td><img src="<%= graphURL[7] %>" width=300 height=70 border=0 usemap="#<%= fileNames.get(7) %>"> </td>
													<!--/tr>
												<tr-->
												</tr>
												<tr>
													<td width="20%" nowrap style="background-color: #CFDCE5; font-family: Verdana; font-size: 12px;">Trunk</td>
													<td><img src="<%= graphURL[8] %>" width=300 height=70 border=0 usemap="#<%= fileNames.get(8) %>"> </td>

												</tr>
												<tr>
													<td width="20%" nowrap style="background-color: #D1D7E3; font-family: Verdana; font-size: 12px;">Right Leg</td>
													<td><img src="<%= graphURL[9] %>" width=300 height=70 border=0 usemap="#<%= fileNames.get(9) %>"> </td>
												
												</tr>
												<tr>
													<td width="20%" nowrap style="background-color: #E0DDE4; font-family: Verdana; font-size: 12px;">Left Leg</td>
													<td><img src="<%= graphURL[10] %>" width=300 height=70 border=0 usemap="#<%= fileNames.get(10) %>"> </td>
												
												</tr>
											</table>
										</td>
										<td valign="top">
											<table align="center" border="0" cellpadding="2" cellspacing="2" width="100%" style="background-color: #FFF3F4; border: 1px ridge;">
												<tr>
													<th colspan="4" style="color: #024788; background-color: #73A7CC; font-family: Arial; font-size: 14px; font-weight: bold">
														Obesity Diagnosis
													</th>
												</tr>
												<tr>
													<td width="25%" style="background-color: #CAD8E3; font-family: Verdana; font-size: 12px;" align="center">BMI <br> (kg/m^2)</td>
													<td align="center" class="content-panel-label">${statusAndMeasurementForm.bmi} </td>
													<td width="25%" nowrap style="background-color: #D5E2C6; font-family: Verdana; font-size: 12px;" Rowspan="2" align="center">Normal Range</td><td align="center" class="content-panel-label">${statusAndMeasurementForm.bmi_Min}~${statusAndMeasurementForm.bmi_Max}</td>													
												</tr>
												<tr>
													<td width="25%" style="background-color: #CAD8E3; font-family: Verdana; font-size: 12px;" align="center">PBF <br> (%)</td>
													<td align="center" class="content-panel-label">${statusAndMeasurementForm.pbf} </td><td align="center" class="content-panel-label">${statusAndMeasurementForm.pbf_Min1}~${statusAndMeasurementForm.pbf_Max1} </td>													
												</tr>
												
											</table>
										</td>
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
							No Automated Assesment and Progress records found.
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
				<input type="submit" value="Manual Measurements" style="font-weight: bolder; width: 160;"/>
				<input type="button" value="Compare Progress" onclick="compareProgress();" style="width: 140;"/>
			</td>
		</tr>
	</table>
</form>
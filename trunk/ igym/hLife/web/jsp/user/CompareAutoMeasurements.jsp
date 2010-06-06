<%@ include file="/jsp/common/Taglibs.jsp" %>
<%@ page import="java.util.ArrayList"%>

<%
	
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
<script src='<html:rewrite page="/resources/js/scw.js"/>'></script>
<script src='<html:rewrite page="/resources/js/overlib.js"/>'></script>
<script>
	function Automeasurements(){
			document.compareAuto.action="<html:rewrite page='/user/getAutoStatusAndMeasurements.do' />";
			document.compareAuto.method.value="getAutoStatusAndMeasurement";
			document.compareAuto.submit();
		
	}
</script>
<body>
<form name="compareAuto" action="<html:rewrite page="/user/compareAutoStatusAndMeasurements.do" />" method="post">
<input type="hidden" name="pageNum">
<input type="hidden" name="method" value="compareAutoStatusAndMeasurement">
<input type="hidden" name="targetUserId" value="${TARGET_USER.userId}">
<table align="center" width="90%" border="0" cellpadding="0" cellspacing="0">
	
	<tr>
		<td>
			<table align="center" border="0" cellpadding="4" cellspacing="4" width="100%" class="content-panel-display-area" style="margin-top: 5px;">
				<tr>
					<td class="content-panel-h2">Compare Auto Measurements
					
					</td>
				</tr>	
				<logic:present name="statusAndMeasurementForm" scope="request">
				<tr>
					<td>
						<table width="60%" align="center">
							<tr>
								<td class="content-panel-label" nowrap>
									From&nbsp;
									<input type=text name="fromDate" value="${fromDate}" maxlength="10" class="field-text-box" readonly onfocus="javascript:scwShow(scwID('fromDate'),this)"/> 
									<img style="cursor:hand;" src='<html:rewrite page="/resources/images/calendar.gif"/>' onClick="javascript:scwShow(scwID('fromDate'),this)"/>												
									&nbsp;&nbsp;
								</td>								
								<td class="content-panel-label" nowrap>
									To&nbsp;
									<input type=text name="toDate" value="${toDate}" maxlength="10" class="field-text-box" readonly onfocus="javascript:scwShow(scwID('toDate'),this)"/> 
									<img style="cursor:hand;" src='<html:rewrite page="/resources/images/calendar.gif"/>' onClick="javascript:scwShow(scwID('toDate'),this)"/>												
									&nbsp;&nbsp;
									<input type="submit" value="Generate Chart">
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td height="20"></td>
				</tr>
				
				<tr>
					<td width="95%">
						<table align="center" border="0" cellpadding="2" cellspacing="2" width="100%" style="background-color: #FFF3F4; border: 1px ridge;">
							<tr>
								<th colspan="2" style="color: #024788; background-color: #A88E51; font-family: Arial; font-size: 14px; font-weight: bold">
									Body Mass Composition
								</th>
							</tr>
							<tr>
								<td colspan="4" align="center">
									<img src="<%= graphURL[0] %>" width=700 height=300 border=0 usemap="#<%= fileNames.get(0) %>">
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td width="95%">
						<table align="center" border="0" cellpadding="2" cellspacing="2" width="100%" style="background-color: #FFF3F4; border: 1px ridge;">
							<tr>
								<th colspan="2" style="color: #024788; background-color: #A0A056; font-family: Arial; font-size: 14px; font-weight: bold">
									Body Water Balance
								</th>
							</tr>
							
							<tr>
								<td colspan="4" align="center">
									<img src="<%= graphURL[1] %>" width=700 height=300 border=0 usemap="#<%= fileNames.get(1) %>">
								</td>
							</tr>
						
						</table>
					</td>
				</tr>
				<tr>
					<td width="95%">
						<table align="center" border="0" cellpadding="2" cellspacing="2" width="100%" style="background-color: #FFF3F4; border: 1px ridge;">
							<tr>
								<th colspan="4" style="color: #024788; background-color: #73A7CC; font-family: Arial; font-size: 14px; font-weight: bold">
									Obesity Diagnosis
								</th>
							</tr>
							
							<tr>
								<td colspan="4" align="center">
									<img src="<%= graphURL[2] %>" width=700 height=300 border=0 usemap="#<%= fileNames.get(2) %>">
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td width="95%">
						<table align="center" border="0" cellpadding="2" cellspacing="2" width="100%" style="background-color: #FFF3F4; border: 1px ridge;">
							<tr>
								<th colspan="4" style="color: #024788; background-color: #73A7CC; font-family: Arial; font-size: 14px; font-weight: bold">
									Segmental Lean Development
								</th>
							</tr>
							<tr>
								<td width="20%" nowrap style="background-color: #CAD8E3; font-family: Verdana; font-size: 12px;" Align = "center">Arms</td>
								<td colspan="4" align="center">
									<img src="<%= graphURL[3] %>" width=700 height=200 border=0 usemap="#<%= fileNames.get(3) %>">
								</td>
							</tr>
							<tr>
								<td width="20%" nowrap style="background-color: #E0DDE4; font-family: Verdana; font-size: 12px; " Align = "center">Trunk</td>													
								<td colspan="4" align="center">
									<img src="<%= graphURL[4] %>" width=700 height=200 border=0 usemap="#<%= fileNames.get(4) %>">
								</td>
							</tr>
							<tr>
								<td width="20%" nowrap style="background-color: #CFDCE5; font-family: Verdana; font-size: 12px; " Align = "center">Legs</td>
								<td colspan="4" align="center">
									<img src="<%= graphURL[5] %>" width=700 height=200 border=0 usemap="#<%= fileNames.get(5) %>">
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
	<tr>
			<td align="center">
				<input type="button" value="Back To Measurements" onclick="javascript:Automeasurements();"/>
			</td>
		</tr>				
								
	
</table>
</form>
</body>
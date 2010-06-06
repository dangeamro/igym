<%@ include file="/jsp/common/Taglibs.jsp" %>

<%
	String fileName="";
	if (request.getAttribute("fileName") != null){
		fileName=(String)request.getAttribute("fileName");
	}
	String graphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" + fileName;
	
%>


<script src='<html:rewrite page="/resources/js/scw.js"/>'></script>
<script src='<html:rewrite page="/resources/js/overlib.js"/>'></script>
<script>
		function  getDaliyView(){
			document.viewHeartRateMonitor.method.value="getHeartRateMonitor";
			document.viewHeartRateMonitor.action="<html:rewrite page='/user/getHeartRateMonitor.do'/>";
			document.viewHeartRateMonitor.submit();
		}

</script>

<body>
<form name="viewHeartRateMonitor" action="<html:rewrite page="/user/compareHeartRateMonitor.do" />" method="post">
<input type="hidden" name="pageNum">
<input type="hidden" name="method" value="compareHeartRateMonitor">
<input type="hidden" name="userId" value="${LOGIN_CREDENTIALS.userId }">
<table align="center" width="80%" border="0" cellpadding="0" cellspacing="0">
	<tr>
	    <td height="15"></td>
	    <td align="center">
	      <font class="error" style="font-size: 13;">
	        	<html:errors/>
	      </font>
	    </td>
	    <td height="15"></td>
	</tr>
	<tr>
		<td>
			<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%" class="content-panel-display-area" style="margin-top: 5px;">
				<tr>
					<td align="left" class="content-panel-h1" style="text-align: left;">
						Heart Rate Monitor
					</td>
				</tr>
				<tr>
					<td height="20"></td>
				</tr>
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
									<input type="submit" value="Generate Chart" style="font-weight: bold; font-size: 12px; width: 110;">
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td height="20"></td>
				</tr>
				<tr>
					<td>
						<table width="80%" align="center" style="background-color: #FFF3F4; border: 1px ridge;">
							<tr>
								<td height="20"></td>
							</tr>
							<tr>
								<td colspan="5" align="center">
									<img src="<%= graphURL %>" width=500 height=300 border=0 usemap="#<%= fileName %>">
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td height="20"></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td height="5"></td>
	</tr>
	<tr>
		<td align="center">
			<input type="button" value="Daily Monitor" style="width: 110;" onclick="getDaliyView()"/>
		</td>
	</tr>
	
</table>
</form>

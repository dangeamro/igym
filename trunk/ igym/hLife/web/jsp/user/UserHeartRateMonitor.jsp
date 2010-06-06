<%@ include file="/jsp/common/Taglibs.jsp" %>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>

<%
	int pageCount=0;

	if(request.getAttribute("PageCount")!=null){
		pageCount=((Integer)request.getAttribute("PageCount")).intValue();
	}
	
	String fileName="";
	if (request.getAttribute("fileName") != null){
		fileName=(String)request.getAttribute("fileName");
	}
	String graphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" + fileName;
	
	
%>

<script src='<html:rewrite page="/resources/js/overlib.js"/>'></script>
<script>
		function  doSubmit(offSet){
			document.viewHeartRateMonitor.method.value="getHeartRateMonitor";
			document.viewHeartRateMonitor.action="getHeartRateMonitor.do?pager.offset="+(offSet-1);
			document.viewHeartRateMonitor.pageNum.value=(offSet);
			document.viewHeartRateMonitor.submit();
		}
		
		function getComparison()
		{
			document.viewHeartRateMonitor.method.value="compareHeartRateMonitor";
			document.viewHeartRateMonitor.action= "<html:rewrite page='/user/compareHeartRateMonitor.do'/>";
			document.viewHeartRateMonitor.submit();
		}
</script>

<form name="viewHeartRateMonitor" action='<html:rewrite page="/user/getAddHeartRateMonitor.do"/>' method="post">
<input type="hidden" name="pageNum">
<input type="hidden" name="method" value="">
<table align="center" width="80%" border="0" cellpadding="0" cellspacing="0">
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
	<logic:greaterThan name="PageCount" value="1" scope="request">
	<tr>
		<td>
			<table align="right"  border="0" cellpadding="0" cellspacing="2" class="pager-panel">
				<tr>
					<td>
						<!-- Paginatin code starts Here -->			
						<pg:pager items="<%= pageCount %>" index="center" maxPageItems="1" maxIndexPages="6"  export="offset,currentPageNumber=pageNumber" scope="request">
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
						    <pg:next><a href="#" onClick="doSubmit(<%= pageNumber %>)">&gt;&gt;</a></pg:next></font>
						    <br></font>
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
			<logic:present name="heartRateMonitorForm" scope="request">
				<tr>
					<td colspan="4" align="left" class="content-panel-h1" style="text-align: left;">
						Heart Rate Monitor for <font style="font-weight: normal; font-size: 11px">${heartRateMonitorForm.dateTime}</font>
					</td>
				</tr>
				<tr>
					<td height="20"></td>
				</tr>
				<tr>
					<td>
						<table width="60%" align="center">
							<td class="content-panel-label">Interval</td>
							<td class="content-panel-data">${heartRateMonitorForm.interval} minutes</td>
							<td class="content-panel-label">Gradation</td>
							<td class="content-panel-data">${heartRateMonitorForm.startGradation} - ${heartRateMonitorForm.endGradation}</td>
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
								<td align="center">
									<img src="<%= graphURL %>" width=500 height=300 border=0 usemap="#<%= fileName %>">
								</td>
							</tr>
						</table>
					</td>
					<tr>
						<td height="20"></td>
					</tr>
			</logic:present>
			</table>
		</td>
	</tr>
	<tr>
	<logic:greaterThan name="PageCount" value="1" scope="request">
	<tr>
		<td>
			<table align="right"  border="0" cellpadding="0" cellspacing="2" class="pager-panel" style="margin-top: 5px;">
				<tr>
					<td>
						<!-- Paginatin code starts Here -->			
						<pg:pager items="<%= pageCount %>" index="center" maxPageItems="1" maxIndexPages="6"  export="offset,currentPageNumber=pageNumber" scope="request">
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
						    <pg:next><a href="#" onClick="doSubmit(<%= pageNumber %>)">&gt;&gt;</a></pg:next></font>
						    <br></font>
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
			<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%"  class="content-panel-display-area">	
			<logic:notPresent name="heartRateMonitorForm" scope="request">
				<tr>
					<td align="left" class="content-panel-h1">
						Heart Rate Monitor
					</td>
				</tr>
				<tr>
					<td height="60" class="search-result-text-data" style="text-align: center;font-family: Arial; font-size: 13;">
						You have not made any Heart rate monitor entries.
					</td>
				</tr>
			</logic:notPresent>
			</table>
		</td>
	</tr>
	<tr>
		<td height="5"></td>
	</tr>
	<tr>
		<td align="center">
			<input type="submit" name="Submit" value="Make New Entry" style="font-weight: bold; width: 130;"/>
			<input type="button" value="Comparison Monitor" style="width: 130;" onclick="getComparison()"/>
		</td>
	</tr>
</table>	
</form>

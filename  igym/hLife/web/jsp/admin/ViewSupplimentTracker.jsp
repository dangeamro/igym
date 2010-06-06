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

		function  doSubmit(offSet){
			document.supplimentTracker.method.value="getSupplimentTracker";
			document.supplimentTracker.action="getSupplimentTracker.do?pager.offset="+(offSet-1);
			document.supplimentTracker.pageNum.value=(offSet);
			document.supplimentTracker.submit();
		}
		
		
		function deleteRecord(){
			if(confirm("Do you really want to DELETE this Suppliment Record"))
			{
				document.supplimentTracker.action="<html:rewrite page='/admin/deleteSupplimentTracker.do?supplimentId=${supplimentTrackerForm.supplimentId}' />";
				document.supplimentTracker.method.value="deleteSupplimentTracker";
				document.supplimentTracker.pageNum.value=1;
				document.supplimentTracker.submit();
			} else {
				return false;
			}
		}
		
</script>	
<form name="supplimentTracker" action="<html:rewrite page="/admin/getAddSupplimentTracker.do" />" method="post">
	<input type="hidden" name="pageNum"  >
	<input type="hidden" name="method" value="getAddSupplimentTracker">
	<input type="hidden" name="targetUserId" value="${TARGET_USER.userId}">
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
				<table align="center" border="0" cellpadding="3" cellspacing="0" width="100%" class="content-panel-display-area" style="margin-top: 5px;">
					<tr>
						<td colspan="4" align="left" class="content-panel-h2" style="text-align: left;">
							Track Supplements
						</td>
					</tr>
					<logic:present name="supplimentTrackerForm" scope="request">
					<tr>
						<td height="15"></td>
					</tr>
					<tr>
						<td class="content-panel-label">Date</td>
						<td class="content-panel-data">${supplimentTrackerForm.date}</td>
						<td class="content-panel-label">Package Type</td>
						<td class="content-panel-data">
						<logic:equal name="supplimentTrackerForm" property="isMonthly" value="1">
							Monthly Package
						</logic:equal>
						<logic:notEqual name="supplimentTrackerForm" property="isMonthly" value="1">
							Non Monthly Package
						</logic:notEqual>
						</td>
					</tr>
					<tr>
						<td class="content-panel-label"> Nutrition Suppor Complex</td>
						<td class="content-panel-data">${supplimentTrackerForm.n_s_Complex} Packs</td>
						<td class="content-panel-label"> Glutamine</td>
						<td class="content-panel-data">${supplimentTrackerForm.glutamine} Packs</td>
					</tr>
					<tr>
						<td class="content-panel-label"> Metabolic Support Oil</td>
						<td class="content-panel-data">${supplimentTrackerForm.m_s_oil} Bottles</td>
						<td width="50%" class="content-panel-label"> Oxygen Optimizer</td>
						<td class="content-panel-data">${supplimentTrackerForm.oxyOptimizer} Bottles</td>
					</tr>
					<tr>
						<td class="content-panel-label"> Metabolic Suppor Complex</td>
						<td class="content-panel-data">${supplimentTrackerForm.m_s_complex} Bottles</td>
						<td class="content-panel-label"> Men/Women Health Support</td>
						<td class="content-panel-data">${supplimentTrackerForm.healthSupport} Bottles</td>
					</tr>
					<tr>
						<td class="content-panel-label"> EFA Support Complex</td>
						<td class="content-panel-data">${supplimentTrackerForm.efaSupport} Bottles</td>
					</tr>
					<tr>
						<td height="15"></td>
					</tr>
					</logic:present>
					<logic:notPresent name="supplimentTrackerForm" scope="request">
					<tr>
						<td height="60" class="search-result-text-data" style="text-align: center;font-family: Arial; font-size: 13;">
							No Supplements for this customer.
						</td>
					</tr>
					</logic:notPresent>			
				
				</table>
			</td>
		</tr>
		<logic:greaterThan name="pageCount" value="1" scope="request">
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
			<td align="center" width="50%">
				<input type="submit" value="Add New" style="font-weight: bolder; width: 80;"/>
				<input type="button" value="delete" style="width: 50;" onClick="javascript:deleteRecord();"/>
			</td>
		</tr>
	</table>
</form>
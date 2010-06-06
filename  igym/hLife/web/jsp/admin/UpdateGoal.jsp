<%@ include file="/jsp/common/Taglibs.jsp" %>
<form name="editGoal" action="<html:rewrite page="/admin/updateGoal.do" />" method="post">
<input type="hidden" name="method" value="editGoal">
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
	    <td height="10"></td>
	    <td align="center">
	      <font class="error" style="font-size: 13;">
	        	<html:errors/>
	      </font>
	    </td>
	    <td height="10"></td>
	</tr>
	<tr>
		<td>
			<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%" class="content-panel-display-area" style="margin-top: 5px;">
			</table>
		</td>
	</tr>
	<tr>
		<td align="center">
			<input type="submit" value="Apply"/>
			<input type="buttin" value="Cancel" onclick="doCancel()"/>
		</td>
	</tr>
	
</table>
</form>
	
	
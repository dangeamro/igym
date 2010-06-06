<%@ include file="/jsp/common/Taglibs.jsp"%>
<script>
  function getTop(control) {
    if (control.offsetParent) {
      return (control.offsetTop + getTop(control.offsetParent));
    } else {
      return (control.offsetTop);
    }
  }

  function getLeft(control) {
    if (control.offsetParent) {
      return (control.offsetLeft + getLeft(control.offsetParent));
    } else {
      return (control.offsetLeft);
    }
  }

  var isToolTipVisible = false;
  
  function showHidePasswordAdvice() {
    if(!isToolTipVisible){
	    showPasswordAdvice();
	}else{
      hidePasswordAdvice();
	}
  }

  function showPasswordAdvice() {
    var toolTip            = document.getElementById("toolTip");
    var controlBox         = document.getElementsByName("oldPassword")[0];
    toolTip.style.pixelLeft = getLeft(controlBox) + controlBox.offsetWidth + 20;
    toolTip.style.pixelTop = getTop(controlBox) + 40;
    toolTip.style.clip = "rect(0 400 150 0)";
    toolTip.style.visibility = "visible";
    isToolTipVisible = true;
  }

  function hidePasswordAdvice(){
    var toolTip            = document.getElementById("toolTip");
    var controlBox         = document.getElementsByName("oldPassword")[0];
    toolTip.style.visibility ="hidden";
    isToolTipVisible = false;
    controlBox.focus();
  }


  function doCancel()
  {
	document.forms[0].action = "getWelcomePage.do?userId=${LOGIN_CREDENTIALS.userId}";
	document.forms[0].submit();
  }
</script>
<html:form action="${LOGIN_CREDENTIALS.requestRootPath }changePassword.do" method="post">
	<html:hidden property="method" value="changePassword" />
	<html:hidden property="userId" value="${LOGIN_CREDENTIALS.userId}"/>
	<table width="50%" cellpadding="2" cellspacing="2" align="center">
		<tr>
			<td height="25"></td>
		</tr>								
		<tr>
			<td align="center">
				<font class="error-header">
					<html:errors property="org.apache.struts.action.GLOBAL_MESSAGE"/>
				</font>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" class="content-panel-display-area">
					<tr>
						<td class="content-panel-h2">
							Change Password
						</td>
					</tr>
					<tr>
						<td>
							<html:errors property="<%= org.apache.struts.action.ActionErrors.GLOBAL_ERROR %>" />
							<table width="100%" border="0" cellspacing="1" cellpadding="1" align="center">
								<tr>
									<td height="5"></td>
								</tr>								
								<tr height="30px">
									<td class="content-panel-label" nowrap>
										Current Password
										<font class="star"> * </font>
									</td>
									<td class="content-panel-data" nowrap>
										<html:password property="oldPassword" maxlength="40" styleClass="field-text-box" /> &nbsp; 
										<font class="error">
											<html:errors property="oldPassword" /><!-- Password could not be confirmed properly -->
										</font>
									</td>
									<td></td>
								</tr>
								<tr height="30px">
									<td class="content-panel-label" nowrap>
										New Password &nbsp; 
										<font class="star"> * </font>
									</td>
									<td>
										<html:password property="newPassword" maxlength="40" styleClass="field-text-box" />
										<img src="<html:rewrite page="/resources/images/bulb-mini.gif"/>" title="Password Advice Tip" onclick="javascript:showHidePasswordAdvice()" /> &nbsp; 
										<font class="error"> 
											<html:errors property="newPassword" /> 
										</font>
										<div id="toolTip" style="position:absolute; cursor:default; background-color:lightyellow; border: 1px solid #000000;visibility:hidden">
											<table width="100%" border="0" cellspacing="2" cellpadding="0">
												<tr>
													<td style="font-size:11px; font-weight: bold; text-decoration:underline; ">
														Password Rules
													</td>
													<td align="right" onclick="javascript:hidePasswordAdvice()" >
														<b>x&nbsp;</b>
													</td>
												</tr>
												<tr>
													<td colspan="2" style="font-size:11px;" nowrap>
														<ol>
															<li>Length should be between {6,20} (both inclusive).</li>
															<li>Alphanumeric(special characters &amp; white spaces also allowed).</li>
															<!-- li>Password contains at least one digit.</li>
															<li>Password doesn't starts or ends with white spaces.</li>
															<li>No character in the password occurs more than 2 times.</li -->
														</ol> 
													</td>
												</tr>
											</table>
										</div>
									</td>
									<td/>
								</tr>
								<tr height="30px">
									<td class="content-panel-label" nowrap>
										Confirm Password
										<font class="star"> * </font>
									</td>
									<td class="content-panel-data" nowrap>
										<html:password property="confirmPassword" maxlength="40" styleClass="field-text-box" /> &nbsp; 
										<font class="error">
											<html:errors property="confirmPassword" /><!-- Password could not be confirmed properly -->
										</font>
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
			<td colspan="4">
				<table width="100%" cellpadding="0" border="0" cellspacing="0" align="center">
					<tr>
						<td height="20"></td>
					</tr>
					<tr>
						<td align="center" height="25" class="add-panel-footer-button" nowrap>
							<input type="submit" value="Change">
							<input type="button" value="Cancel" onclick="doCancel()">
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</html:form>
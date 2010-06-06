<%@ include file="/jsp/common/Taglibs.jsp"%>
<script src='<html:rewrite page="/resources/js/common-scripts.js"/>'></script>
<script src='<html:rewrite page="/resources/js/scw.js"/>'></script>
<script src='<html:rewrite page="/resources/js/common-scripts.js"/>'></script>
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
    var controlBox         = document.getElementsByName("password")[0];
    toolTip.style.pixelLeft = getLeft(controlBox) + controlBox.offsetWidth + 20;
    toolTip.style.pixelTop = getTop(controlBox) - 70;
    toolTip.style.clip = "rect(0 400 170 0)";
    toolTip.style.visibility = "visible";
    isToolTipVisible = true;
  }

  function hidePasswordAdvice(){
    var toolTip            = document.getElementById("toolTip");
    var controlBox         = document.getElementsByName("password")[0];
    toolTip.style.visibility ="hidden";
    isToolTipVisible = false;
    controlBox.focus();
  }
  
  function toggleCalendar(fld1, fld2)
  {
  	if(document.getElementsByName('isAdmin')[0].checked)
  		return;
  	scwShow(fld1, fld2);
  }
  
  function toggleCustomerFields(isAdmin)
  {
  	var fieldsArray = new Array();
  	fieldsArray[0] = 'dob';
  	fieldsArray[1] = 'doj';
  	fieldsArray[2] = 'dos';
  	fieldsArray[3] = 'durationInWeeks';
  	fieldsArray[4] = 'address';
  	fieldsArray[5] = 'mobileNumber';
  	fieldsArray[6] = 'phoneNumber';
  	
  	if(isAdmin)
	{
		for(var i=0; i<fieldsArray.length; i++)
		{
			//document.getElementsByName(fieldsArray[i])[0].value="";
			document.getElementsByName(fieldsArray[i])[0].disabled = true;
			document.getElementById(fieldsArray[i]+'Td').disabled=true;
		}
  	}
  	else
  	{
		for(var i=0; i<fieldsArray.length; i++)
		{
			document.getElementsByName(fieldsArray[i])[0].disabled = false;
			document.getElementById(fieldsArray[i]+'Td').disabled=false;
		}
  	}
  }
  
  function toggleNutrition(isAdmin)
  {
  	var fieldSet = document.getElementById('nutritionControl');
	if(isAdmin)
	{
		document.getElementsByName('suggestedCalorieConsumption')[0].disabled = true;
		document.getElementsByName('protienPercentage')[0].disabled = true;
		document.getElementsByName('carbohydratesPercentage')[0].disabled = true;
		document.getElementsByName('fatPercentage')[0].disabled = true;
		document.getElementsByName('otherPercentage')[0].disabled = true;
		
		fieldSet.disabled=true;
	}
	else
	{
		fieldSet.disabled=false;
		document.getElementsByName('suggestedCalorieConsumption')[0].disabled = false;
		document.getElementsByName('protienPercentage')[0].disabled = false;
		document.getElementsByName('carbohydratesPercentage')[0].disabled = false;
		document.getElementsByName('fatPercentage')[0].disabled = false;
		document.getElementsByName('otherPercentage')[0].disabled = false;
	}
  }
  
  function displayChangePassword()
  {
  	if(document.getElementById('changePasswordLink').innerText.indexOf("Don't") == -1)
  	{
	  	document.getElementById('changePasswordLink').innerText = "Don't change password";
  		document.getElementById('changePasswordArea1').style.display = 'block';
	  	document.getElementById('changePasswordArea2').style.display = 'block';
  		document.getElementById('changePasswordArea3').style.display = 'block';
	  	document.getElementById('changePasswordArea4').style.display = 'block';
	}
	else
	{
	  	document.getElementById('changePasswordLink').innerText = 'Change password';
  		document.getElementById('changePasswordArea1').style.display = 'none';
	  	document.getElementById('changePasswordArea2').style.display = 'none';
  		document.getElementById('changePasswordArea3').style.display = 'none';
	  	document.getElementById('changePasswordArea4').style.display = 'none';
	}
  }


  function doOnLoad(){
  	if(document.getElementsByName('role')[0].value == 'admin')
  		document.getElementsByName('isAdmin')[0].checked = true;

	toggleCustomerFields(document.getElementsByName('isAdmin')[0].checked);
	toggleNutrition(document.getElementsByName('isAdmin')[0].checked);
  }

  function doCancel(){
  	document.customerForm.action = "<html:rewrite page='/admin/manageCustomers.do?method=listCustomers'/>";
  	document.customerForm.submit();
  }
  
  function doSubmit(){
  	if(document.getElementsByName('isAdmin')[0].checked)
  		document.getElementsByName('role')[0].value='admin';
  	else
  		document.getElementsByName('role')[0].value='customer';
  		
  	if(document.getElementById('changePasswordArea1').style.display == 'none')
  	{
  		document.getElementById('changePasswordArea1').innerHTML = ''
  		document.getElementById('changePasswordArea2').innerHTML = ''
  		document.getElementById('changePasswordArea3').innerHTML = ''
  		document.getElementById('changePasswordArea4').innerHTML = ''
  	}
  }


</script>
<body onload="doOnLoad()">
<html:form action="/admin/updateCustomer.do" method="post">
<html:hidden property="method" value="updateCustomer" />
<html:hidden property="creationDate" value="" />
<html:hidden property="updatePage" value="true"/>
<table width="80%" cellpadding="2" cellspacing="2" align="center">
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
						Update User/Customer
					</td>
				</tr>
				<tr>
					<td>
						<html:errors property="<%= org.apache.struts.action.ActionErrors.GLOBAL_ERROR %>" />
						<table width="100%" border="0" cellspacing="1" cellpadding="1" align="center">
							<tr>
								<td height="5"></td>
							</tr>								
							<tr>
								<td></td>
								<td class="content-panel-label" width="25%" nowrap>
								 	User Id
								 <font class="star">*</font></td>
								<td class="content-panel-data" width="25%" nowrap>
									<html:text property="userId" maxlength="20" styleClass="field-text-box" tabindex="1" readonly="true" style="background-color: transparent; border: thin ridge;"/>&nbsp; 
									<label class="link" style="cursor: hand; vertical-align: bottom;" onclick="displayChangePassword();">
										<font id="changePasswordLink" style="font-size: 11px;">
											${customerForm.passwordSet?'Don\'t change password':'Change password'}
										</font>
									</label>
								</td>
								<td>
								</td>
							</tr>
							<tr>
								<td/>
								<td class="content-panel-label" nowrap>
									<div id="changePasswordArea1" style="display: ${customerForm.passwordSet?'block':'none' }">
										New Password &nbsp; 
										<font class="star"> * </font>
									</div>
								</td>
								<td>
									<div id="changePasswordArea2" style="display: ${customerForm.passwordSet?'block':'none' }">										
										<html:password property="password" tabindex="2" maxlength="40" styleClass="field-text-box" />
										<img src="<html:rewrite page="/resources/images/bulb-mini.gif"/>" title="Password Advice Tip" onclick="javascript:showHidePasswordAdvice()" /> &nbsp; 
										<font class="error"> 
											<html:errors property="password" /> 
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
															<li>Alphanumeric(special characters & white spaces also allowed).</li>
															<!-- li>Password contains at least one digit.</li>
															<li>Password doesn't starts or ends with white spaces.</li>
															<li>No character in the password occurs more than 2 times.</li -->
														</ol> 
													</td>
												</tr>
											</table>
										</div>
									</div>
								</td>
								<td/>
							</tr>
							<tr>
								<td></td>
								<td class="content-panel-label" nowrap>
									<div id="changePasswordArea3" style="display: ${customerForm.passwordSet?'block':'none' }">										
										Confirm Password
										<font class="star"> * </font>
									</div>
								</td>
								<td class="content-panel-data" nowrap>
									<div id="changePasswordArea4" style="display: ${customerForm.passwordSet?'block':'none' }">										
										<html:password property="confirmPassword" maxlength="40" tabindex="3" styleClass="field-text-box" /> &nbsp; 
										<font class="error">
											<html:errors property="confirmPassword" /><!-- Password could not be confirmed properly -->
										</font>
									</div>
								</td>
								<td></td>
							</tr>
							<tr>
								<td/><td/>
								<td class="content-panel-label" style="text-align: left;" nowrap>
									<input type="checkbox" id="isAdmin" name="isAdmin" class="field-text-box" tabindex="4" onclick="toggleCustomerFields(this.checked); toggleNutrition(this.checked)"/>
									<label for="isAdmin">User is a Designer Bodies administrator</label>
								</td>
								<td/>
								<html:hidden property="role"/>
							</tr>
							<tr>
								<td height="5"></td>
							</tr>
							<tr>
								<td colspan="4">
									<table width="90%" border="0" cellspacing="0"
										cellpadding="0" align="center">
										<tr>
											<td valign="top" height="1" bgcolor="#003366"></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td height="5"></td>
							</tr>
							<tr>
								<td class="content-panel-label" nowrap>
									First Name
									<font class="star"> * </font>
								</td>
								<td class="content-panel-data" nowrap>
									<html:text property="firstName" maxlength="50" styleClass="field-text-box" tabindex="4"/> &nbsp; 
									<font class="error">
										<html:errors  property="firstName" /> 
									</font>
								</td>
								<td class="content-panel-label" nowrap>
									Email Address
									<font class="star"> * </font>
								</td>
								<td class="content-panel-data" nowrap>
									<html:text property="emailAddress" maxlength="50" tabindex="11" styleClass="field-text-box" /> &nbsp; 
									<font class="error">
										<html:errors  property="emailAddress" /> 
									</font>
								</td>
							</tr>
							<tr>
								<td class="content-panel-label" nowrap>
									Last Name
									<font class="star"> * </font>
								</td>
								<td class="content-panel-data" width="" nowrap>
									<html:text property="lastName" maxlength="50" styleClass="field-text-box" tabindex="5"/> &nbsp; 
									<font class="error"> 
										<html:errors  property="lastName" /> 
									</font>
								</td>
								<td id="phoneNumberTd" class="content-panel-label" nowrap>
									Phone Number&nbsp;
								</td>
								<td class="content-panel-data" nowrap>
									<html:text property="phoneNumber" maxlength="20" styleClass="field-text-box" tabindex="12"/>
								</td>
							</tr>
							<tr>
								<td class="content-panel-label" nowrap>
									Gender
									<font class="star"> * </font>								
								</td>
								<td class="content-panel-data" nowrap>
									<Select name="gender" class="field-option" tabindex="6">
										<option value="M" ${(customerForm.gender == "M")?"SELECTED":"" }>Male</option>
										<option value="F" ${(customerForm.gender == "F")?"SELECTED":"" }>Female</option>													
									</Select>
								</td>
								<td id="mobileNumberTd" class="content-panel-label" nowrap>
									Mobile Number&nbsp;
								</td>
								<td class="content-panel-data" nowrap>
									<html:text property="mobileNumber" maxlength="20" styleClass="field-text-box" tabindex="13"/>
								</td>
							</tr>
							<tr>
								<td id="dobTd" class="content-panel-label" nowrap>
									Date of Birth
									<font class="star"> * </font>
								</td>
								<td id="dobTd" class="content-panel-data" width="" nowrap>
									<html:text property="dob" maxlength="10" tabindex="7" styleClass="field-text-box" readonly="true" onfocus="javascript:scwShow(scwID('dob'),this)"/>
									<img style="cursor:hand;" src='<html:rewrite page="/resources/images/calendar.gif"/>' onClick="javascript:toggleCalendar(scwID('dob'),this)"/>
									<font class="error"> 
										<html:errors property="dob" /> 
									</font>
								</td>
								<td id="addressTd" rowspan="2" class="content-panel-label" nowrap>
									Address&nbsp;
								</td>
								<td rowspan="2" colspan="1">
									<html:textarea property="address" rows="3" cols="35" styleClass="message-body-compose" tabindex="14"/>
								</td>
							</tr>
							<tr>
								<td id="dojTd" class="content-panel-label" nowrap>
									Joining date
									<font class="star"> * </font>											
								</td>
								<td class="content-panel-data" width="" nowrap>
									<html:text property="doj" maxlength="10" tabindex="8" styleClass="field-text-box" readonly="true" onfocus="javascript:scwShow(scwID('doj'),this)"/> 
									<img style="cursor:hand;" src='<html:rewrite page="/resources/images/calendar.gif"/>' onClick="javascript:toggleCalendar(scwID('doj'),this)"/>
									<font class="error"> 
										<html:errors property="doj" /> 
									</font>
								</td>
							</tr>
							<tr>
								<td id="dosTd" class="content-panel-label" nowrap>
									Start date
									<font class="star"> * </font>											
								</td>
								<td class="content-panel-data" width="" nowrap>
									<html:text property="dos" maxlength="10" tabindex="9" styleClass="field-text-box" readonly="true" onfocus="javascript:scwShow(scwID('dos'),this)"/> 
									<img style="cursor:hand;" src='<html:rewrite page="/resources/images/calendar.gif"/>' onClick="javascript:toggleCalendar(scwID('dos'),this)"/>
									<font class="error"> 
										<html:errors property="dos" /> 
									</font>
								</td>
								<td class="content-panel-label" nowrap>
									Hint Question
									<font class="star"> * </font>								
								</td>
								<td class="content-panel-data" nowrap>
									<html:select property="hintQuestion" styleClass="field-option" tabindex="15">
										<option value="">-- Select --</option>
										<!--htmlptions collection="hintQuestionsList" property="questionId" labelProperty="questionDefault" / -->
										<option value="What is your nick name?" ${(customerForm.hintQuestion == "What is your nick name?")?"SELECTED":"" }>What is your nick name?</option>
										<option value="What is your place of birth?" ${(customerForm.hintQuestion == "What is your place of birth?")?"SELECTED":"" }>What is your place of birth?</option>
										<option value="What is your favourite food?" ${(customerForm.hintQuestion == "WWhat is your favourite food?")?"SELECTED":"" }>What is your favourite food?</option>
										<option value="What is your favourite colour?" ${(customerForm.hintQuestion == "What is your favourite colour?")?"SELECTED":"" }>What is your favourite colour?</option>
									</html:select>
									<font class="error">
										<html:errors  property="hintQuestion" /> 
									</font>
								</td>
							</tr>
							<tr>
								<td id="durationInWeeksTd" class="content-panel-label" nowrap>
									Duration
									<font class="star"> * </font>											
								</td>
								<td class="content-panel-data" width="" nowrap>
									<html:text property="durationInWeeks" maxlength="3" tabindex="10" styleClass="field-text-box"/>
									<label class="content-panel-sub-label">Weeks</label>
									<font class="error"> 
										<html:errors property="durationInWeeks" />
									</font>
								</td>
								<td class="content-panel-label" nowrap>
									Answer
									<font class="star"> * </font>
								</td>
								<td class="content-panel-data" width="" nowrap>
									<html:text property="answer" size="20" maxlength="20" styleClass="field-text-box" tabindex="16"/>
									<font class="error">
										<html:errors  property="answer" />
									</font>
								</td>
							</tr>
							<tr>
								<td colspan="4" style="font: Arial; font-size: 11px; text-align: right;">Note: This information is helpful in the situation when user forgets his password</td>
							</tr>
							<tr>
								<td colspan="4" class="content-panel-label" nowrap>
                          		  *&nbsp;
		                          <bean:message key="common.requiredFields" />
		                        </td>
							</tr>
							<tr>
								<td height="5"></td>
							</tr>
							<tr>
								<td colspan="4">
									<table width="90%" border="0" cellspacing="0" cellpadding="0" align="center">
										<tr>
											<td valign="top" height="1" bgcolor="#003366"></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>							
								<td height="5"></td>
							</tr>
							<tr>
								<td colspan="4">
									<fieldset id="nutritionControl" class="fieldset">
										<legend> Nutrition Control (Optional) </legend>
										<table width="90%" align="center">
											<tr>
												<td colspan="4" align="center">
													<font class="error">
													<html:errors property="aggrPercentage"/>
													</font>
												</td>
											</tr>
											<tr>
												<td class="content-panel-label" nowrap>
													Protien
												</td>
												<td class="content-panel-data" nowrap>
													<html:text size="2" maxlength="5" tabindex="17" styleClass="field-text-box"  property="protienPercentage" onkeypress="return checkDecimal(this);"/>&nbsp;<font class="content-panel-sub-label">%</font>
												</td>
												<td class="content-panel-label" nowrap>
													carbohydrates
												</td>
												<td class="content-panel-data" nowrap>
													<html:text size="2" maxlength="5" tabindex="18" styleClass="field-text-box"  property="carbohydratesPercentage" onkeypress="return checkDecimal(this);"/>&nbsp;<font class="content-panel-sub-label">%</font>
												</td>
											</tr>
											<tr>
												<td class="content-panel-label" nowrap>
													Fats
												</td>
												<td class="content-panel-data" nowrap>
													<html:text size="2" maxlength="5" tabindex="19" styleClass="field-text-box"  property="fatPercentage" onkeypress="return checkDecimal(this);"/>&nbsp;<font class="content-panel-sub-label">%</font>
												</td>
												<td class="content-panel-label" nowrap>
													Other nutrients
												</td>
												<td class="content-panel-data" nowrap>
													<html:text size="2" maxlength="5" tabindex="20" styleClass="field-text-box"  property="otherPercentage" onkeypress="return checkDecimal(this);"/>&nbsp;<font class="content-panel-sub-label">%</font>
												</td>
											</tr>
											<tr>
												<td colspan="2" class="content-panel-label" nowrap>
													Suggested Calorie consumption per day
												</td>
												<td colspan="2" class="content-panel-data" nowrap>
													<html:text property="suggestedCalorieConsumption" tabindex="21" styleClass="field-text-box" onkeypress="return checkDecimal(this);"/>
												</td>
											</tr>
										</table>
									</fieldset>
								</td>
								<td></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="4">
			<table width="100%" cellpadding="0" border="0" cellspacing="0" align="center">
				<tr>
					<td align="center" height="25" class="add-panel-footer-button" nowrap>
						<input type="submit" value="Save" tabindex="22" onclick="javascript: doSubmit()">
						<input type="button" value="Cancel" tabindex="23" onclick="doCancel()">
					</td>
				</tr>
				<tr>
					<td height="5"></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</html:form>
</body>

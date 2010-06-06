<%@ include file="/jsp/common/Taglibs.jsp" %>
<script src='<html:rewrite page="/resources/js/TimeValidator.js"/>'></script>
<script src='<html:rewrite page="/resources/js/common-scripts.js"/>'></script>

<script language="javascript">
/*	function validateNumbers(control)
	{
		var val = control.value;
		for(i=0; i<val.length; i++)
			if(val.charAt(i))
				
	}*/
	
	function doSubmit(){
	
		var target = '';
		var retFlag = true;
		var cnt = 0;
		var inteval = document.getElementsByName('interval')[0].value;
		
		var readingArr = document.getElementsByName('reading');

		for(i = readingArr.length; i > 0; i--)
		{
			if(readingArr[i-1].value == '' && target.length == 0)
				continue;
				
			if(target.length > 0 && (readingArr[i-1].value == null || readingArr[i-1].value == '') )
			{
				alert('Grrr!!! Jumping entries not allowed.\nPlease make entry for  minute: ' + inteval*i);
				retFlag = false;
				readingArr[i-1].focus();
				break;
			}
			cnt++;
			if(target == '')
				target = readingArr[i-1].value;
			else
				target = readingArr[i-1].value + ' ' + target;
		}
		
		target.length = target.length - 1;
		
		document.getElementsByName('readings1')[0].value = '' + target;
		if(document.getElementsByName('time')[0].value.length < 5)
			document.getElementsByName('dateTime')[0].value = document.getElementsByName('date')[0].value+'-'+formatDate(new Date(),'hh:mm');
		else
			document.getElementsByName('dateTime')[0].value = document.getElementsByName('date')[0].value + '-' + document.getElementsByName('time')[0].value;
		
		if( retFlag && cnt<5)
		{
			alert('At least 5 entries are medatory.');
			retFlag = false;
		}

		return retFlag;
	
	}
	
	function doCancel(){
		if(confirm('Quit without saving this record?')){
	 		document.forms[0].action = '<html:rewrite page="/user/getHeartRateMonitor.do" />';
			document.forms[0].method.value = 'getHeartRateMonitor';
			document.forms[0].submit();
		}
	}
	
	function doOnLoad(){
		var readingsArray = document.getElementsByName("readings1")[0].value.split(" ");
		
		for(var i=0; i<readingsArray.length; i++)
			document.getElementsByName("reading")[i].value = readingsArray[i];

		var dateTime = document.getElementsByName('dateTime')[0].value;
		if(dateTime == '')
			document.getElementsByName('time')[0].value = formatDate(new Date(),'hh:mm');
		else
			document.getElementsByName('time')[0].value = formatDate(dateTime,'hh:mm');
	
	}
		
</script>
<body onload="doOnLoad()">
<html:form action="/user/addHeartRateMonitor.do" method="post">
	<input type="hidden" name="method" value="addHeartRateMonitor">
	<html:hidden property="readings1"/>
	<html:hidden property="dateTime"/>
	<table align="center" width="80%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td height="20"/>
		</tr>
		<tr>
		    <td align="center">
		      <font class="error-header" style="font-size: 13;">
		        	<html:errors property="org.apache.struts.action.GLOBAL_MESSAGE"/>
		      </font>
		    </td>
		</tr>
		<tr>
			<td>
				<table align="right" width="100%" border="0" cellpadding="0" cellspacing="1" class="content-panel-display-area">
					<tr>
						<td colspan="6" class="content-panel-h2">
							Date : 
							<html:text size="8" property="date" style="border: 0; color: white; background-color: transparent; font-size: 14" readonly="true"/>
						</td>
					</tr>
					<tr>
						<td colspan="6" height="20"/>
					</tr>
					<tr>
						<td class="content-panel-label">
							Time
							<font class="star"> * </font>
						</td>
						<td class="content-panel-text-box">
							<html:text property="time" size="5" maxlength="5" styleClass="field-text-box"
								onkeyup="editValidTime('time',':')"
								onblur="validateTime('time',':')"
								onkeypress="return maskSpaceBar()"/>
							<font class="error">
								<html:errors  property="time" />
							</font>
						</td>
						<td class="content-panel-label">
							Interval
							<font class="star"> * </font>
						</td>
						<td class="content-panel-data">
							<html:text property="interval" size="5" maxlength="2" value="2" readonly="true" styleClass="field-text-box" onkeypress="return checkNumber();"/>
							<font class="error">
								<html:errors  property="interval" />
							</font>
						</td>
						<td class="content-panel-label">
							Gradation
							<font class="star"> * </font>
						</td>
						<td class="content-panel-data">
							<html:text property="startGradation" size="2" maxlength="2" styleClass="field-text-box"  onkeypress="return checkNumber();"/>
							-
							<html:text property="endGradation" size="2" maxlength="2" styleClass="field-text-box"  onkeypress="return checkNumber();"/>
							<font class="error">
								<html:errors  property="startGradation" />
							</font>
						</td>
					</tr>
					<tr>
						<td colspan="6">
							<table width="90%" align="center" class="search-result-panel" cellpadding="0" cellspacing="0">
								<tr>
									<td colspan="20" class="search-result-header" style="text-align: left">
										Heart Rate Readings
										&nbsp;&nbsp;
										<font class="star"><i>(At least 5 entires mandatory; Please maintain the sequence)</i></font>
									</td>
								</tr>
								<tr>
									<td height="10"/>
								</tr>
								<tr>
									<td class="content-panel-label">
										2
									</td>
									<td>
										<input type="text" name="reading" size="1" maxlength="3" onkeypress="return checkNumber();"/>
									</td>
									<td class="content-panel-label">
										4
									</td>
									<td>
										<input type="text" name="reading" size="1" maxlength="3" onkeypress="return checkNumber();"/>
									</td>
									<td class="content-panel-label">
										6
									</td>
									<td>
										<input type="text" name="reading" size="1" maxlength="3" onkeypress="return checkNumber();"/>
									</td>
									<td class="content-panel-label">
										8
									</td>
									<td>
										<input type="text" name="reading" size="1" maxlength="3" onkeypress="return checkNumber();"/>
									</td>
									<td class="content-panel-label">
										10
									</td>
									<td>
										<input type="text" name="reading" size="1" maxlength="3" onkeypress="return checkNumber();"/>
									</td>
									<td class="content-panel-label">
										12
									</td>
									<td>
										<input type="text" name="reading" size="1" maxlength="3" onkeypress="return checkNumber();"/>
									</td>
									<td class="content-panel-label">
										14
									</td>
									<td>
										<input type="text" name="reading" size="1" maxlength="3" onkeypress="return checkNumber();"/>
									</td>
									<td class="content-panel-label">
										16
									</td>
									<td>
										<input type="text" name="reading" size="1" maxlength="3" onkeypress="return checkNumber();"/>
									</td>
									<td class="content-panel-label">
										18
									</td>
									<td>
										<input type="text" name="reading" size="1" maxlength="3" onkeypress="return checkNumber();"/>
									</td>
									<td class="content-panel-label">
										20
									</td>
									<td>
										<input type="text" name="reading" size="1" maxlength="3" onkeypress="return checkNumber();"/>
									</td>
								</tr>
								<tr>
									<td height="5"/>
								</tr>
								<tr>
									<td class="content-panel-label">
										22
									</td>
									<td>
										<input type="text" name="reading" size="1" maxlength="3" onkeypress="return checkNumber();"/>
									</td>
									<td class="content-panel-label">
										24
									</td>
									<td>
										<input type="text" name="reading" size="1" maxlength="3" onkeypress="return checkNumber();"/>
									</td>
									<td class="content-panel-label">
										26
									</td>
									<td>
										<input type="text" name="reading" size="1" maxlength="3" onkeypress="return checkNumber();"/>
									</td>
									<td class="content-panel-label">
										28
									</td>
									<td>
										<input type="text" name="reading" size="1" maxlength="3" onkeypress="return checkNumber();"/>
									</td>
									<td class="content-panel-label">
										30
									</td>
									<td>
										<input type="text" name="reading" size="1" maxlength="3" onkeypress="return checkNumber();"/>
									</td>
									<td class="content-panel-label">
										32
									</td>
									<td>
										<input type="text" name="reading" size="1" maxlength="3" onkeypress="return checkNumber();"/>
									</td>
									<td class="content-panel-label">
										34
									</td>
									<td>
										<input type="text" name="reading" size="1" maxlength="3" onkeypress="return checkNumber();"/>
									</td>
									<td class="content-panel-label">
										36
									</td>
									<td>
										<input type="text" name="reading" size="1" maxlength="3" onkeypress="return checkNumber();"/>
									</td>
									<td class="content-panel-label">
										38
									</td>
									<td>
										<input type="text" name="reading" size="1" maxlength="3" onkeypress="return checkNumber();"/>
									</td>
									<td class="content-panel-label">
										40
									</td>
									<td>
										<input type="text" name="reading" size="1" maxlength="3" onkeypress="return checkNumber();"/>
									</td>
								</tr>
								<tr>
									<td height="10"/>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="6">
							<table width="90%" align="center" cellpadding="0" cellspacing="0">
								<tr>
									<td class="content-panel-data">
										<input type="checkbox" id="emailFlag" name="emailFlag">
										<label for="emailFlag"> Send a mail designer bodies administrator about my readings</label>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="5"></td>
                        <td class="content-panel-label" nowrap>
                            *&nbsp;
                          <bean:message key="common.requiredFields" />
                        </td>
					</tr>
					<tr>
						<td colspan="6" height="10"></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td height="5"></td>
		</tr>
		<tr>
			<td class="content-panel-footer">
				<input type="submit" value="Save" style="font-weight: bold; width: 70;" onclick="return doSubmit();"/> 
				<input type="button" value="Cancel" name="cancel" style="width: 55;" onClick="doCancel()"/>
			</td>
		</tr>
	</table>
</html:form>
<script>
	document.getElementsByName('date')[0].value = formatDate(new Date(),'MM/dd/yyyy');
</script>
</body>
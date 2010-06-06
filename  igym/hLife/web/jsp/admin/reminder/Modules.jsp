<%@ include file="/jsp/common/Taglibs.jsp" %>
<script src='<html:rewrite page="/resources/js/mail-scripts.js"/>'></script>

<script>
	var occupied = false;
	var openedId = -1;

	function submitForm()
	{
		if(occupied)
		{
			alert('Please press Ok button for already opened reminder.');
			return false;
		}

		var checks = document.getElementsByName("check");
		var targetChecks = '';
		for(i = 0; i < checks.length; i++)
		{
			targetChecks += checks[i].value;
			targetChecks += ',';
		}

		var checkBoxes = document.getElementsByName("enabled");
		var targetEnabled = '';
		for(i = 0; i < checkBoxes.length; i++)
		{
			if(!checkBoxes[i].checked)
				continue;
			targetEnabled += checkBoxes[i].value;
			targetEnabled += ',';
		}

		document.getElementsByName("checks")[0].value = targetChecks;
		document.getElementsByName("enabledList")[0].value = targetEnabled;
		//alert("checks- " + document.getElementsByName("checks")[0].value + "\nenabledList-" + document.getElementsByName("enabledList")[0].value);
		
		return true;
	}
</script>
<table width="98%" align="center" cellspacing="2" cellpadding="2">
	<tr>
		<td>
			<form method="post" action='<html:rewrite page="/admin/updateReminders.do" />'>
				<input type="hidden" name="checks"/>
				<input type="hidden" name="enabledList"/>
				<input type="hidden" name="method" value="updateReminders" />
				<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%" class="mail-display-area">
					<tr>
						<td class="mail-header">
							<table>
								<tr>
									<td class="mail-header-item">
										Reminder Manager
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td height="4"></td>
					</tr>
					<logic:present name="modules" scope="request">
						<tr>
							<td>
								<table width="98%" align="center" cellpadding="0" cellspacing="0">
									<logic:notEmpty name="modules" scope="request">
										<logic:iterate id="module" name="modules" scope="request">
											<bean:define id="module" name="module" toScope="request"></bean:define>
											<tr>
												<td align="right">
													<jsp:include flush="true" page="/jsp/admin/reminder/Reminder.jsp"></jsp:include>
												</td>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
									<logic:empty name="modules" scope="request">
										<tr>
											<td colspan="8" height="60" class="search-result-text-data" style="text-align: center;font-family: Arial; font-size: 13;">
												No module found in reminder service.
											</td>
										</tr>
									</logic:empty>
								</table>
							</td>
						</tr>
						<logic:notEmpty name="modules" scope="request">
						<tr>
							<td>
								<table width="98%" cellpadding="0" cellspacing="0" align="center">
									<tr>
										<td height="5"></td>
									</tr>
									<tr>
										<td>
											<input type="submit" value="Apply" class="button" onclick="return submitForm();">
										</td>
									</tr>
								</table>
							</td>
						</tr>
						</logic:notEmpty>
					</logic:present>
					<tr>
						<td height="5"></td>
					</tr>
				</table>
			</form>
		</td>
	</tr>
</table>
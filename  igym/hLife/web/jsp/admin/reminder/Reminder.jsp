<%@ include file="/jsp/common/Taglibs.jsp" %>
<script>

	function getUpdate(rowId, remId, colName, days)
	{
		//alert(rowId + ":" + remId + ":" + colName);
		if(occupied)
		{
			if(rowId != openedId)
				alert('Please press Ok button for already opened reminder.');
			return;
		}
			
		occupied = true;
		openedId = rowId;
		var targetHTML = '';
		targetHTML += '<input type="text" name="days" value=' + days + ' size="1" maxlength="3" class="field-text-box"/>&nbsp;days.&nbsp;<input type="button" value="Ok" class="button" onclick="save(' + rowId +', ' + remId +', \'' + colName + '\');"/>'
		
		document.getElementById("checkLink_" + rowId).innerHTML = targetHTML;
	}
	
	function save(rowId, remId, colName)
	{
		var val = document.getElementsByName("days")[0].value;
		document.getElementById("checkLink_" + rowId).innerHTML = val + ' days.';
		document.getElementById("check_" + rowId).value = remId + ':' + colName + ':' + val;
		occupied = false;
	}
</script>
<%int i = 0; %>
<fieldset class="fieldset" style="padding-left: -4px">
<legend>
<!--	<input type="checkbox" ${module.on?'checked':''}/>-->
	${module.name}
</legend>

	<input type="hidden" name="checks"/>
	<table width="98%" cellpadding="0" cellspacing="1px" style="background-color: #FFFFFF;">
		<logic:notEmpty name="module" property="reminders">
<!--			<tr class="inbox-unread-message-row">-->
<!--				<td colspan="4">Description: ${module.desc}</td>-->
<!--			</tr>-->
<!--			<tr>-->
<!--				<td colspan="4" height="5px"></td>-->
<!--			</tr>-->
			<logic:iterate id="reminder" name="module" property="reminders" scope="request">
				<tr class="reminder-row">
					<td width="5px" nowrap><input type="checkbox" name="enabled" value="${reminder.id }" ${reminder.on?'checked':''}/></td>
					<td>
						${reminder.name}
					</td>
					<td>${reminder.desc}</td>
<!--					<td align="right">-->
<!--						<a href='<html:rewrite page="/admin/getReminder.do?reminderId=${reminder.id}"/>'  class="link" style="font-size: 12px; text-decoration: none;" onclick="return false;">configure</a>-->
<!--					</td>-->
				</tr>
				<logic:iterate id="check" name="reminder" property="dateChecks" scope="page">
					<tr class="reminder-checks-row">
						<td></td>
						<td colspan="3">
							<logic:greaterEqual value="0" name="check" property="olderThan">
								when <b>${check.colDisplayName }</b> older than <label id="checkLink_${module.id}<%= i %>" style="cursor: hand; color: red" onclick="getUpdate(${module.id}<%= i %>, ${reminder.id }, '${check.colSqlName }', ${check.olderThan });"> ${check.olderThan } days.</label>
								<input type="hidden" name="check" id="check_${module.id}<%= i++ %>"/>
							</logic:greaterEqual>
							<logic:lessThan value="0" name="check" property="olderThan">
								when ${check.olderThan } days remaining for ${check.colDisplayName }.
							</logic:lessThan>
						</td>
					</tr>
				</logic:iterate>
			</logic:iterate>
		</logic:notEmpty>
		<logic:empty name="module" property="reminders">
			<tr>
				<td colspan="4" style="text-align: center;font-family: Arial; font-size: 13;">
					No reminders found under ${module.name }.
				</td>
			</tr>
		</logic:empty>
	</table>
</fieldset>
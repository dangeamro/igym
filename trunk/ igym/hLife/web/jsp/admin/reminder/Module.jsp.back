<%@ include file="/jsp/common/Taglibs.jsp" %>
<script src='<html:rewrite page="/resources/js/mail-scripts.js"/>'></script>
<table width="98%" align="center" cellspacing="2" cellpadding="2">
	<tr>
		<td>
			<form method="post" action='<html:rewrite page="/admin/deleteMails.do" />'>
				<input type="hidden" name="method" value="updateModules" />
				<input type="hidden" name="idList" value="" />
				<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%" class="mail-display-area">
					<tr>
						<td class="mail-header">
							<table>
								<tr>
									<td class="mail-header-item">
										<a class="mail-tab" href='<html:rewrite page="/admin/getReminderServices.do?method=getModules" />'>
											Module Manager
										</a> 
										&gt; ${module.name }
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<logic:present name="module" scope="request">
						<tr>
							<td>
								<table width="98%" align="center" class="mail-display-panel" cellpadding="0" cellspacing="0">
									<tr class="inbox-header">
										<td>
											Reminder Name
										</td>
										<td>
											ON/OFF
										</td>
										<td>
											Description
										</td>
										<td>
											Reminder Message
										</td>
									</tr>
									<logic:notEmpty name="module" property="reminders" scope="request">
										<logic:iterate id="row" name="module" property="reminders" scope="request" indexId="rowNum">
											<tr class="inbox-unread-message-row">
												<td>
													<a href='<html:rewrite page="/admin/getReminder.do?moduleKey=${row.id}"/>'  class="link" style="font-size: 12px; text-decoration: none;" onclick="return false;">${row.name}</a>
												</td>
												<td nowrap><input type="checkbox" ${row.on?'checked':''}/></td>
												<td>${row.desc}</td>
												<td>${row.messageBody}</td>
											</tr>
											<tr>
												<td colspan="5" height="1" style="background-color: #B2B4BF;"></td>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
									<logic:empty name="module" property="reminders" scope="request">
										<tr>
											<td colspan="8" height="60" class="search-result-text-data" style="text-align: center;font-family: Arial; font-size: 13;">
												No reminders found under ${module.name }.
											</td>
										</tr>
									</logic:empty>
								</table>
							</td>
						</tr>
						<logic:notEmpty name="module" property="reminders" scope="request">
						<tr>
							<td>
								<table width="98%" cellpadding="0" cellspacing="0" align="center">
									<tr>
										<td height="5"></td>
									</tr>
									<tr>
										<td>
											<input type="submit" value="Update" onclick="return false;">
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
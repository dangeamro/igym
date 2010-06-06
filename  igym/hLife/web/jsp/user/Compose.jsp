<%@ include file="/jsp/common/Taglibs.jsp" %>
<script src='<html:rewrite page="/resources/js/mail-scripts.js"/>'></script>

<table width="98%" align="center" cellspacing="2" cellpadding="2">
	<tr>
		<td height="10"/>
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
			<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%" class="mail-display-area">
				<tr>
					<td class="mail-header">
						<table>
							<tr>
								<td class="mail-header-item">
									<a class="mail-tab" href='<html:rewrite page="/user/getMails.do?method=getInbox" />'>
										Inbox
									</a>
								</td>
								<td class="mail-header-item-selected">
									<a class="mail-tab" href='<html:rewrite page="/user/getMailCompose.do?method=getCompose" />'>
										Compose
									</a>
								</td>
								<td class="mail-header-item">
									<a class="mail-tab" href='<html:rewrite page="/user/getSentItems.do?method=getSentItems" />'>
										Sent Items
									</a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<html:form method="post" action="/user/sendMail.do">
							<input type="hidden" name="method" value="sendMail" />
							<input type="hidden" name="sender" value="${LOGIN_CREDENTIALS.userId }" />
							<table>
								<tr>
									<td height="10"/>
								</tr>
								<tr>
									<td width="7%" class="content-panel-label">
										To
									</td>
									<td>
										<html:text property="to" size="60" maxlength="100" value="*admin" styleClass="field-text-box" readonly="true"/>
										<font class="error">
											<html:errors property="to"/>
										</font>
									</td>
								</tr>
								<tr>
									<td class="content-panel-label">
										Subject
									</td>
									<td>
										<html:text property="subject" size="60" maxlength="100" styleClass="field-text-box"/>
										<font class="error">
											<html:errors property="subject"/>
										</font>
									</td>
								</tr>
								<tr>
									<td class="content-panel-label">
										Priority
									</td>
									<td>
										<html:select property="priority" styleClass="field-option">
											<html:option value="0">Low</html:option>
											<html:option value="1">Medium</html:option>
											<html:option value="2">High</html:option>
										</html:select>
									</td>
								</tr>
								<tr>
									<td>
									</td>
									<td>
										<html:textarea property="body" rows="10" cols="90" styleClass="message-body-compose" />
										<font class="content-panel-sub-label">(Max 1000 characters)</font>
										<font class="error">
											<html:errors property="body"/>
										</font>
									</td>
								</tr>
								<tr>
									<td>
									</td>
									<td>
										<input type="submit" value="Send">
									</td>
								</tr>
							</table>
						</html:form>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
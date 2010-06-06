<%@ include file="/jsp/common/Taglibs.jsp" %>
<script src='<html:rewrite page="/resources/js/mail-scripts.js"/>'></script>

<form method="post" action='<html:rewrite page="/admin/deleteUserMails.do" />'>
	<input type="hidden" name="method" value="deleteMailsFromInbox" />
	<input type="hidden" name="idList" value="" />
	<input type="hidden" name="targetUserId" value="${TARGET_USER.userId}" />
	<table width="98%" align="center" cellspacing="2" cellpadding="2">
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
									<td class="mail-header-item-selected">
										<a class="mail-tab" href='<html:rewrite page="/admin/getUserMails.do?method=getInbox&targetUserId=${TARGET_USER.userId}" />'>
											Inbox
										</a>
									</td>
									<td class="mail-header-item">
										<a class="mail-tab" href='<html:rewrite page="/admin/getUserMailCompose.do?method=getCompose&targetUserId=${TARGET_USER.userId}" />'>
											Compose
										</a>
									</td>
									<td class="mail-header-item">
										<a class="mail-tab" href='<html:rewrite page="/admin/getUserMailSentItems.do?method=getSentItems&targetUserId=${TARGET_USER.userId}" />'>
											Sent Items
										</a>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<bean:define id="targetURL" toScope="request" value="admin/getUserMail.do?method=getMail&targetUserId=${TARGET_USER.userId}" type="java.lang.String" />
					<jsp:include flush="true" page="/jsp/common/InboxDisplay.jsp"></jsp:include>
					<tr>
						<td height="5"></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</form>

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
			<form method="post" action='<html:rewrite page="/admin/deleteMails.do" />'>
				<input type="hidden" name="method" value="deleteMailsFromInbox" />
				<input type="hidden" name="idList" value="" />
				<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%" class="mail-display-area">
					<tr>
						<td class="mail-header">
							<table>
								<tr>
									<td class="mail-header-item-selected">
										<a class="mail-tab" href='<html:rewrite page="/admin/getMails.do?method=getInbox" />'>
											Inbox
										</a>
									</td>
									<td class="mail-header-item">
										<a class="mail-tab" href='<html:rewrite page="/admin/getMailCompose.do?method=getCompose" />'>
											Compose
										</a>
									</td>
									<td class="mail-header-item">
										<a class="mail-tab" href='<html:rewrite page="/admin/getSentItems.do?method=getSentItems" />'>
											Sent Items
										</a>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<bean:define id="targetURL" toScope="request" value="admin/getMail.do?method=getMail" type="java.lang.String" />
					<jsp:include flush="true" page="/jsp/common/InboxDisplay.jsp"></jsp:include>
					<tr>
						<td height="5"></td>
					</tr>
				</table>
			</form>
		</td>
	</tr>
</table>
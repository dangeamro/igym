<%@ include file="/jsp/common/Taglibs.jsp"%>
<table align="center" height="100%" width="100%" border="0" cellpadding="5" cellspacing="5" class="content-panel">
	<tr>
		<td height="30"></td>
	</tr>
	<tr>
		<td>
			<table width="60%" height="80%" align="center" cellpadding="0" cellspacing="15" class="content-panel-display-area">
				<tr valign="top">
					<td align="center" class="welcome-panel-h1" style="text-align: center;">
						Welcome Administrator
					</td>
				</tr>
				<tr valign="top">
					<td>
						<table width="90%" align="center">
							<tr>
								<td height="1" style="background-color: #6d6d6d"></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td align="center">
						<img src='<html:rewrite page="/resources/images/letter.gif"/>'>
						<a href="<html:rewrite page="/admin/getMails.do?method=getInbox"/>" class="link" style="vertical-align: middle; padding-left: 10px;">You have ${LOGIN_CREDENTIALS.unreadMailCount } unread messages</a>
					</td>
				</tr>
				<tr valign="top">
					<td>
						<table width="90%" align="center">
							<tr>
								<td height="1" style="background-color: #6d6d6d"></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td align="center">
						<img src='<html:rewrite page="/resources/images/notebook.gif"/>'>
						<a href="<html:rewrite page="/admin/getUpload.do"/>" class="link" style="vertical-align: middle; padding-left: 10px;">Upload a new file for Automatic Assestment & Progress</a>
					</td>
				</tr>
				<tr valign="top">
					<td>
						<table width="90%" align="center">
							<tr>
								<td height="1" style="background-color: #6d6d6d"></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td align="center">
						<img src='<html:rewrite page="/resources/images/User Accounts.gif"/>'>
						<a href="<html:rewrite page="/admin/getAddCustomer.do"/>" class="link" style="vertical-align: middle; padding-left: 10px;">Add a new User for HLife</a>
					</td>
				</tr>
				<tr valign="top">
					<td>
						<table width="90%" align="center">
							<tr>
								<td height="1" style="background-color: #6d6d6d"></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td align="center">
						<img width="40px" src='<html:rewrite page="/resources/images/compose-mail.gif"/>'>
						<a href='<html:rewrite page="/admin/getMailCompose.do?method=getCompose"/>' class="link" style="vertical-align: middle; padding-left: 10px;">Send an email</a>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

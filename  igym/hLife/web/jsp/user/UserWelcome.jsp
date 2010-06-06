<%@ include file="/jsp/common/Taglibs.jsp"%>
<form>
<input type="hidden" name="userId" value="${LOGIN_CREDENTIALS.userId }"/>
<table align="center" height="100%" width="100%" border="0" cellpadding="5" cellspacing="5" class="content-panel">
	<tr>
		<td height="60"></td>
	</tr>
	<tr>
		<td>
			<table width="60%" height="80%" align="center" cellpadding="0" cellspacing="15" class="content-panel-display-area">
				<tr valign="top">
					<td align="center" class="welcome-panel-h1" style="text-align: center;">
						Welcome ${LOGIN_CREDENTIALS.displayName} 
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
						<a href='<html:rewrite page="/user/getMails.do?method=getInbox"/>' class="link"  style="vertical-align: middle; padding-left: 10px;">You have ${LOGIN_CREDENTIALS.unreadMailCount } unread message(s)</a>
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
						<a href='<html:rewrite page="/user/getAddMeal.do?method=getAddFood" />' class="link"  style="vertical-align: middle; padding-left: 10px;">Make a Food Journal entry for today</a>
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
						<img src='<html:rewrite page="/resources/images/heart-icon-mini.gif"/>'>
						<a href='<html:rewrite page="/user/getHeartRateMonitor.do?method=getHeartRateMonitor"/>' class="link"  style="vertical-align: middle; padding-left: 10px;">View your Heart rate monitor entires</a>
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
						<img width="40" src='<html:rewrite page="/resources/images/compose-mail.gif"/>'>
						<a href='<html:rewrite page="/user/getMailCompose.do?method=getCompose"/>' class="link"  style="vertical-align: middle; padding-left: 10px;">Let the designer bodies know about you through an email</a>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</form>
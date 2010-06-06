<%@ include file="/jsp/common/Taglibs.jsp"%>
<script src='<html:rewrite page="/resources/js/common-scripts.js"/>'></script>
<table width="100%" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td class="header-panel" align="center" valign="bottom">
			<table width="100%" border="0">
				<tr height="75px">
					<td align="center" width="90%">
						<img src="<html:rewrite page="/resources/images/header-text.gif"/>" align="bottom" border="0">
					</td>
					<td nowrap>
						<table>
							<tr>
								<td>
									<a class="header-text-a" href="<html:rewrite page="/getHelpPage.do"/>" target="_blank" )">Help</a>												
								</td>
								<td>
									<a class="header-text-a">|</a>
								</td>
								<td>
									<a class="header-text-a" href="getWelcomePage.do?userId=${LOGIN_CREDENTIALS.userId}">Home</a>
								</td>
								<td>
									<a class="header-text-a">|</a>
								</td>
								<td nowrap>
									<a class="header-text-a" href="getChangePassword.do">Password</a>
								</td>
								<td>
									<a class="header-text-a">|</a>
								</td>
								<td>
									<a class="header-text-a" href="<html:rewrite page="/logout.do?method=doLogout"/>" onclick="return confirm('<bean:message key="logout.confirm.message"/>')">Logout</a>											
								</td>
							</tr>
							<tr>
								<td colspan="7" class="header-text" nowrap>
									User Name:  <b>${LOGIN_CREDENTIALS.displayName}</b>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
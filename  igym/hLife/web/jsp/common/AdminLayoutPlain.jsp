<%@ include file="/jsp/common/Taglibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<tiles:importAttribute name="selectedMainMenuIndex" scope="request" />
<!-- tiles:importAttribute name="selectedContextMenuIndex" scope="request" / -->
<tiles:useAttribute name="pageTitle" classname="java.lang.String" />
<html:html>
	<head>
		<title><bean:message key="${pageTitle}" /></title>
		<link rel="stylesheet" type="text/css" href="<html:rewrite page='/resources/css/designer.css'/>">
	</head>
	<body>
		<table class="form-border" height="100%" width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr height="10%">
				<td align="center" >
					<tiles:insert attribute="header" />
				</td>
			</tr>
			<tr height="5%">
				<td align="left" class="horizontal-menu-container">
					<tiles:insert attribute="mainMenu" />
				</td>
			</tr>
			<tr height="100%">
				<td valign="top">
					<table width="100%" height="100%" align="center" cellpadding="0" cellspacing="0">
						<tr height="100%">
							<td height="100%" align="center" valign="middle" class="content-panel">
								<tiles:insert attribute="content" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr height="5%">
				<td align="center" valign="baseline" class="footer" nowrap>
					<tiles:insert attribute="footer" />
				</td>
			</tr>
		</table>
	</body>
</html:html>

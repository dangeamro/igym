<%@ include file="/jsp/common/Taglibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<tiles:importAttribute name="selectedMenuIndex" scope="request" />
<tiles:useAttribute name="pageTitle" classname="java.lang.String" />
<html:html>
	<head>
		<title><bean:message key="${pageTitle}" /></title>
		<link rel="stylesheet" type="text/css" href="<html:rewrite page='/resources/css/designer.css'/>">
	</head>
	<body>
		<table class="form-border" height="100%" width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td align="center" class="form-border">
					<tiles:insert attribute="header" />
				</td>
			</tr>
			<tr height="100%">
				<td>
				<table width="100%" height="100%" border="0" cellpadding="0"
					cellspacing="0">
					<tr>
						<td width="10%" class="vertical-menu-container">
							<tiles:insert attribute="menu" />
						</td>
						<td height="100%" align="center" valign="middle" class="content-panel">
							<tiles:insert attribute="content" />
						</td>
					</tr>
				</table>
		
				</td>
			</tr>
			</tr>
			<td align="center" valign="baseline" class="footer"><tiles:insert
				attribute="footer" /></td>
			</tr>
		</table>
	</body>
</html:html>

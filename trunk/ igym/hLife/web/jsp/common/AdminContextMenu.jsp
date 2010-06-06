<%@ include file="/jsp/common/Taglibs.jsp" %>
<tiles:useAttribute name="contextMenuList" classname="java.util.List" />
<table width="95%" align="center" cellpadding="0" cellspacing="1" border="0">
  <tr>
    <td align="center">
        <ul id="vertical-menu-list">
          <logic:iterate id="menuItem" name="contextMenuList" indexId="index">
              <bean:define id="link" value="${pageScope.menuItem.link}" />
<%
			pageContext.setAttribute("delim", "&");
			if (link.indexOf("?") != -1) {
			  pageContext.setAttribute("firstDelim", "&");
			} else {
			  pageContext.setAttribute("firstDelim", "?");
			}
%>
            <logic:present name="parameterName" scope="request">
              <html:link styleClass="${requestScope.selectedContextMenuIndex==index?'current_a':'other_a' }" page="${pageScope.menuItem.link}${pageScope.firstDelim}${requestScope.parameterName}=${requestScope.parameterValue}&targetUserId=${TARGET_USER.userId}">
                <logic:equal name="index" value="${requestScope.selectedContextMenuIndex}">
                  <li id="current">
                </logic:equal>
                <logic:notEqual name="index" value="${requestScope.selectedContextMenuIndex}">
                  <li id="other">
                </logic:notEqual>
                <img border="0" src='<html:rewrite page="/${pageScope.menuItem.icon}" />'/><br>
                <logic:present name="bundle" scope="request">
                  <bean:message bundle="${requestScope.bundle}" key="${pageScope.menuItem.value}" />
                </logic:present>
                <logic:notPresent name="bundle" scope="request">
                  <bean:message key="${pageScope.menuItem.value}" />
                </logic:notPresent>
                  </li>
              </html:link>
            </logic:present>
            <logic:notPresent name="parameterName" scope="request">
              <html:link styleClass="${requestScope.selectedContextMenuIndex==index?'current_a':'other_a' }" page="${pageScope.menuItem.link}${pageScope.firstDelim}targetUserId=${TARGET_USER.userId}">
                <logic:equal name="index" value="${requestScope.selectedContextMenuIndex}">
                  <li id="current">
                </logic:equal>
                <logic:notEqual name="index" value="${requestScope.selectedContextMenuIndex}">
                  <li id="other">
                </logic:notEqual>
                <img border="0" src='<html:rewrite page="/${pageScope.menuItem.icon}" />'/><br>
                <logic:present name="bundle" scope="request">
                  <bean:message bundle="${requestScope.bundle}" key="${pageScope.menuItem.value}" />
                </logic:present>
                <logic:notPresent name="bundle" scope="request">
                  <bean:message key="${pageScope.menuItem.value}" />
                </logic:notPresent>
                  </li><br>
              </html:link>
            </logic:notPresent>
          </logic:iterate>
        </ul>
    </td>
  </tr>
</table>
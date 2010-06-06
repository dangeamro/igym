<%@ include file="/jsp/common/Taglibs.jsp" %>
<tiles:useAttribute name="menuList" classname="java.util.List" />
<table width="95%" align="center" cellpadding="0" cellspacing="1" border="0">
  <tr>
    <td align="center">
        <ul id="vertical-menu-list">
          <logic:iterate id="menuItem" name="menuList" indexId="index">
            <logic:present name="parameterName" scope="request">
              <bean:define id="link" value="${pageScope.menuItem.link}" />
<%
              if (link.indexOf("?") != -1) {
                pageContext.setAttribute("delim", "&");
              } else {
                pageContext.setAttribute("delim", "?");
              }
%>
              <html:link styleClass="${requestScope.selectedMenuIndex==index?'current_a':'other_a' }" page="${pageScope.menuItem.link}${pageScope.delim}${requestScope.parameterName}=${requestScope.parameterValue}">
                <logic:equal name="index" value="${requestScope.selectedMenuIndex}">
                  <li id="current">
                </logic:equal>
                <logic:notEqual name="index" value="${requestScope.selectedMenuIndex}">
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
              <html:link styleClass="${requestScope.selectedMenuIndex==index?'current_a':'other_a' }" page="${pageScope.menuItem.link}">
                <logic:equal name="index" value="${requestScope.selectedMenuIndex}">
                  <li id="current">
                </logic:equal>
                <logic:notEqual name="index" value="${requestScope.selectedMenuIndex}">
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
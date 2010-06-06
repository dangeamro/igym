<%@ include file="/jsp/common/Taglibs.jsp" %>
<tiles:useAttribute name="mainMenuList" classname="java.util.List" />
<table width="100%" align="left" cellpadding="1" cellspacing="0" border="0">
  <tr>
    <td align="center">
        <ul id="horizontal-menu-list">
          <logic:iterate id="mainMenuItem" name="mainMenuList" indexId="index">
            <logic:present name="parameterName" scope="request">
              <bean:define id="link" value="${pageScope.mainMenuItem.link}" />
<%
              if (link.indexOf("?") != -1) {
                pageContext.setAttribute("delim", "&");
              } else {
                pageContext.setAttribute("delim", "?");
              }
%>
              <html:link styleClass="${requestScope.selectedMainMenuIndex==index?'current_a':'other_a' }" page="${pageScope.mainMenuItem.link}${pageScope.delim}${requestScope.parameterName}=${requestScope.parameterValue}">
                <logic:equal name="index" value="${requestScope.selectedMainMenuIndex}">
                  <li id="current">
                </logic:equal>
                <logic:notEqual name="index" value="${requestScope.selectedMainMenuIndex}">
                  <li id="other">
                </logic:notEqual>
                <img border="0" height="15" src='<html:rewrite page="/${pageScope.mainMenuItem.icon}" />'/>
                <logic:present name="bundle" scope="request">
                  <bean:message bundle="${requestScope.bundle}" key="${pageScope.mainMenuItem.value}" />
                </logic:present>
                <logic:notPresent name="bundle" scope="request">
                  <bean:message key="${pageScope.mainMenuItem.value}" />
                </logic:notPresent>
                  </li>
              </html:link>
            </logic:present>
            <logic:notPresent name="parameterName" scope="request">
              <html:link  styleClass="${requestScope.selectedMainMenuIndex==index?'current_a':'other_a' }" page="${pageScope.mainMenuItem.link}">
                <logic:equal name="index" value="${requestScope.selectedMainMenuIndex}">
                  <li id="current">
                  <!--li id="current"-->
                </logic:equal>
                <logic:notEqual name="index" value="${requestScope.selectedMainMenuIndex}">
                  <li id="other">
                  <!--li-->
                </logic:notEqual>
                <img border="0" height="15" src='<html:rewrite page="/${pageScope.mainMenuItem.icon}" />'/>
                <logic:present name="bundle" scope="request">
                  <bean:message bundle="${requestScope.bundle}" key="${pageScope.mainMenuItem.value}" />
                </logic:present>
                <logic:notPresent name="bundle" scope="request">
                  <bean:message key="${pageScope.mainMenuItem.value}" />
                </logic:notPresent>
                  </li>
              </html:link>
            </logic:notPresent>
          </logic:iterate>
        </ul>
    </td>
  </tr>
</table>
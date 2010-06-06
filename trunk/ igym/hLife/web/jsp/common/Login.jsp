<!-- GUID	Name	DOJ	DOB	Address	phone	email	user	login	name -->
<%@ page language="java" %>
<%@ include file="/jsp/common/Taglibs.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html:html>
  <head>
    <title>
      <bean:message key="page.title.login" />
    </title>
    <link rel="StyleSheet" href="<html:rewrite page='/resources/css/designer.css'/>" type="text/css">
    <script src='<%= request.getContextPath() + "/html/Common.js" %>'></script>
    <script>
      function hideWarnings() {
        document.getElementById("userIdWarning").style.display = "none";
        document.getElementById("passwordWarning").style.display = "none";
        document.getElementById("forgetPasswordWarning").style.color  = "#D5D9E5";
      }

      function Trim(textField) {
        //This step trims the spaces before the string
        while ('' + textField.value.charAt(0) == ' ') {
          textField.value = textField.value.substring(1, textField.value.length);
        }

        //This step trims the spaces after the string
        while ('' + textField.value.charAt(textField.value.length - 1) == ' ') {
          textField.value = textField.value.substring(0, (textField.value.length - 2)) + "\0";
        }
      }

      function doFieldValidation() {
        var userId   = document.getElementsByName("loginId")[0];
        var password = document.getElementsByName("password")[0];

        Trim(userId);
        Trim(password);
        hideWarnings();
        if (password.value.length == 0) {
          document.getElementById("passwordWarning").style.display = "inline";
          document.forms[0].password.focus();
        }
        if (userId.value.length == 0) {
          document.getElementById("userIdWarning").style.display = "inline";
          document.forms[0].loginId.focus();
        }
        return (!(userId.value.length == 0 || password.value.length == 0));
      }

      function isIdPresent() {
        var userId = document.getElementsByName("loginId")[0];

        Trim(userId);
        hideWarnings();
        if (userId.value.length == 0) {
          document.getElementById("forgetPasswordWarning").style.display = "inline";
          document.getElementById("forgetPasswordWarning").style.color = "red";
          document.forms[0].loginId.focus();
          return false;
        }
        return true;
      }
    </script>
  </head>
  <body style="background-color:#FFFFFF" onload="JavaScript: document.forms[0].loginId.focus();">
    <html:form action="/login">
      <input type="hidden" name="method" value="doLogin">
      <table width="80%" style="background-color: #679ACD" border="0" align="center" cellspacing="0"  cellpadding="0" height="80%" class="form-border">
      	<tr height="10%">
			<td style="background-image: url('<html:rewrite page="/resources/images/header-bg.gif"/>'); " align="center" valign="bottom">
               	<img src="<html:rewrite page="/resources/images/header-text.gif"/>" align="top" border="0">
      		</td>
      	</tr>
        <tr>
          <td height="60"></td>
        </tr>
        <tr>
          <td height="20"></td>
        </tr>
        <tr>
          <td valign="top">
            <table cellSpacing="0" cellPadding="0" width="70%" align="center" border="0" style="background-color: #D5D9E5; border: 1px solid #0B198C;">
                <tr>
                  <td class="login-header" height="20" nowrap>
                    <bean:message key="login.heading" />
                  </td>
                </tr>
                <tr>
                  <td height="20"></td>
                </tr>
		        <tr>
		          <td align="center">
		            <font class="error-header">
		              	<html:errors/>
		            </font>
		          </td>
		        </tr>
                <tr>
                  <td height="10"></td>
                </tr>
                <tr>
                  <td align="center">
                    <table border="0" cellpadding="0" cellspacing="0" width="60%" align="center" class="add-panel-bgcolor">
                      <tr>
                        <td align="center" style="background-color: #EDEFFB; border: 1px outset #FFFFFF;">
                          <table border="0" cellpadding="3" cellspacing="2" width="100%" align="center">
                            <tr>
                              <td height="10"></td>
                            </tr>
                            <tr>
                              <td class="content-panel-label" nowrap>
                                <bean:message key="login.prompt.loginId" />
                                &nbsp;
                                <font class="star">
                                  *
                                </font>
                              </td>
                              <td class="content-panel-text-box" nowrap>
                                <html:text property="loginId" value="${requestScope.ATTEMPTED_USER_ID}" size="22" maxlength="20" styleClass="textbox-left" />
                                &nbsp;
                                <font class="error"></font>
                                <font class="error">
                                  <div id="userIdWarning" style="display:none;">
                                    <bean:message key="common.error.field.required" />
                                    &nbsp;
                                  </div>
                                </font>
                              </td>
                            </tr>
                            <tr>
                              <td class="content-panel-label" nowrap>
                                <bean:message key="login.prompt.password" />
                                &nbsp;
                                <font class="star">
                                  *
                                </font>
                              </td>
                              <td class="content-panel-text-box" nowrap>
                                <html:password property="password" value="" styleClass="textbox-left" size="22" />
                                &nbsp;
                                <font class="error"></font>
                                <font class="error">
                                  <div id="passwordWarning" style="display:none;">
                                    <bean:message key="common.error.field.required" />
                                    &nbsp;
                                  </div>
                                </font>
                              </td>
                            </tr>
                            <tr>
                              <td height="10"></td>
                            </tr>
                          </table>
                        </td>
                      </tr>
                      <tr>
                        <td class="content-panel-label" nowrap>
                            *&nbsp;
                          <bean:message key="common.requiredFields" />
                        </td>
                      </tr>
                      <tr>
                        <td>
                          <table width="100%" align="center" cellpadding="2" cellspacing="0" border="0">
                            <tr>
                              <td align="center" height="25" class="add-panel-footer-button" nowrap>
                                <html:submit style="font-weight: bolder;" onclick="javascript:return doFieldValidation()">
                                  <bean:message key="login.button.login" />
                                </html:submit>
                                <html:reset onclick="javascript: hideWarnings()">
                                  <bean:message key="login.button.reset" />
                                </html:reset>
                              </td>
                            </tr>
                          </table>
                        </td>
                      </tr>
                    </table>
                  </td>
                </tr>
                <tr>
                  <td align="center">
                    <table width="100%" cellpadding="2" cellspacing="0" border="0">
                      <tr>
                        <td class="text-content" nowrap height="20"></td>
                      </tr>
                      <tr>
                        <td align="center" nowrap>
                          <a class="link" target="_blank" href="<html:rewrite page="/remindPassword.do"/>" onclick="javascript:return isIdPresent()">Forgot Password?</a>
                        </td>
                      </tr>
                    </table>
                  </td>
                </tr>
	                  <tr>
	                    <td class="error">
	                      <div id="forgetPasswordWarning" style="color: #D5D9E5; font-size: 12; font-weight: normal;">
	                        <bean:message key="forgetPasswordWarning.userIdBlank" />
	                      </div>
	                    </td>
	                  </tr>

            </table>
          </td>
        </tr>
        <tr>
          <td class="footer" style="color: #FFFFFF; background-color: #024788; height: 18px">
            <bean:message key="designer.copyright" />
          </td>
        </tr>
      </table>
    </html:form>
  </body>
</html:html>
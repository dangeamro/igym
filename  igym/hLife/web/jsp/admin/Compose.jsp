<%@ include file="/jsp/common/Taglibs.jsp" %>
<script src='<html:rewrite page="/resources/js/mail-scripts.js"/>'></script>

<script>

	var div;
	var sel;
	var field;
	var left = 0;
	var top = 0;
	var okToSubmit = false;
	var pressed = false;

	// populating array for suggestion list
	var arr = new Array(${userMap.count*2});
	var indexes = new Array(${userMap.count});
	<logic:iterate id="user" name="userMap" property="userList" indexId="rowNum">
		arr[${rowNum*2}] = '${user.displayName}';
		arr[${rowNum*2 + 1}] = '${user.userId}';
		indexes[${rowNum*2}] = ${rowNum*2};
		indexes[${rowNum*2 + 1}] = ${rowNum*2 + 1};
	</logic:iterate>
	
	function setPressed()
	{
		pressed = true;
	}
	
	function init(f)
	{	
		top = 0; left = 0;
		var parent = f;
		do {
			left += parent.offsetLeft;
			top += parent.offsetTop;
		}while(parent = parent.offsetParent);
		var style1 = div.style;
		style1.left = left;
		style1.top = top + 22;
		sel.style.width = 300;
		
		field = f;
	}
	
	function insertContact()
	{
		if(sel.style.display !='none' && sel.options.length != 0)
		{
			if(field.value.lastIndexOf(';') == -1)
				field.value = sel.options[sel.selectedIndex].innerText + '; ';
			else
				field.value = field.value.substring(0, field.value.lastIndexOf(';') + 1) + ' ' + sel.options[sel.selectedIndex].innerText + '; ';
			sel.options.length = 0;
			div.style.display='none';
			pressed = false;
		}
		else
			okToSubmit = true;
		
		field.focus();
	}
	
	function handleKey(e)
	{
		key = !e.keyCode?e.which:e.keyCode;
		if(sel.options.length == 0 && key != 27)
			return true;
		switch(key){
		case 40:
			sel.options[(sel.selectedIndex + 1) % sel.options.length].selected = true;
			break;
		case 38:
			sel.options[(sel.selectedIndex + sel.options.length - 1) % sel.options.length].selected = true;
			break;
		case 9:
		case 13:
			insertContact()
			return false;
		case 27:
			clearDiv();
			return false;
		}
		return true;
	}
	
	function clearDiv()
	{
			//sel.options.length = 0;
			div.style.display='none';
			pressed = false;
	}
	
	function populateSuggestion(e)
	{
		var keycode = !e.keyCode?e.which:e.keyCode
		var value = field.value.substring(field.value.lastIndexOf(';')+1).toLowerCase().replace(/ /g, '')
		if(keycode == 13)
			return okToSubmit;
		if(value == '')
			div.style.display = 'none';
		else
		{
			if(!pressed && keycode != 8)
				return true;
			
			var result = findResult(value);
			sel.options.length = 0;
			var index = -1;
			var pre = -1;
			for(var i = 0; i < result.length; i++)
			{
				index = result[i] - result[i]%2;
				if(index == pre)
					continue;
				sel.options[sel.options.length] = new Option(arr[index] + ' <' + arr[index+1] + '>');
				pre = index;
			}
			if(sel.options[0])
			{
				sel.options[0].selected = true;
				div.style.display = 'block';
			}
			else
				div.style.display = 'none';
		}
		pressed = false;
		return;
	}
	
	function findResult(str)
	{
		var result = new Array(0);
		result = result.concat(indexes);
		
		var src = '';
		for(var charI = 0; charI < str.length; charI++)
		{
			//find start
			for(var i = result.length - 1; i > -1; i--)
			{
				src = arr[result[i]].toLowerCase();
				
				if(src.charAt(charI) != str.charAt(charI))
				{
					result.splice(i, 1);
				}
			}
		}
		return result;		
	}
</script>

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
			<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%" class="mail-display-area">
				<tr>
					<td class="mail-header">
						<table>
							<tr>
								<td class="mail-header-item">
									<a class="mail-tab" href='<html:rewrite page="/admin/getMails.do?method=getInbox" />'>
										Inbox
									</a>
								</td>
								<td class="mail-header-item-selected">
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
				<tr>
					<td>
						<html:form method="post" action="/admin/sendMail.do">
							<input type="hidden" name="method" value="sendMail" />
							<input type="hidden" name="sender" value="${LOGIN_CREDENTIALS.userId }" />
							<table>
								<tr>
									<td height="10"/>
								</tr>
								<tr>
									<td width="7%" class="content-panel-label">
										To
									</td>
									<td>
										<html:text property="to" size="100" maxlength="100" styleClass="field-text-box" onkeydown="return handleKey(event);" onkeyup="return populateSuggestion(event);" onkeypress="setPressed();" onfocus="init(this);" onblur="clearDiv();"/>
										<font class="error">
											<html:errors property="to"/>
										</font>
										<div id="sugg" style="position: absolute; left: 0; top: 0; z-index: 1; border: 0px solid blue; background-color: red; display: none; " >
											<select id="select" size="6" onclick="insertContact();" ondblclick="insertContact();"></select>
										</div>
										<script type="text/javascript">
											div = document.getElementById("sugg");
											sel = document.getElementById("select");
										</script>
									</td>
								</tr>
								<tr>
									<td class="content-panel-label">
										Subject
									</td>
									<td>
										<html:text property="subject" size="100" maxlength="100" styleClass="field-text-box"/>
										<font class="error">
											<html:errors property="subject"/>
										</font>
									</td>
								</tr>
								<tr>
									<td class="content-panel-label">
										Priority
									</td>
									<td>
										<html:select property="priority" styleClass="field-option">
											<html:option value="0">Low</html:option>
											<html:option value="1">Medium</html:option>
											<html:option value="2">High</html:option>
										</html:select>
									</td>
								</tr>
								<tr>
									<td>
									</td>
									<td>
										<html:textarea property="body" rows="10" cols="103" styleClass="message-body-compose" />
										<font class="content-panel-sub-label">(Max 1000 characters)</font>
										<font class="error">
											<html:errors property="body"/>
										</font>
									</td>
								</tr>
								<tr>
									<td>
									</td>
									<td>
										<input type="submit" value="Send">
									</td>
								</tr>
							</table>
						</html:form>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
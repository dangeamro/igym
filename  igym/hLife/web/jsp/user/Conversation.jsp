<%@ include file="/jsp/common/Taglibs.jsp" %>
<script>
	function reply(replyOf, sender, to, subject, priority, rootMessageId)
	{

		document.getElementsByName("replyOf")[0].value = replyOf;
		document.getElementsByName("sender")[0].value = sender;
		document.getElementsByName("to")[0].value = to;
		document.getElementsByName("subject")[0].value = subject;
		document.getElementsByName("priority")[0].value = priority;
		document.getElementsByName("rootMessageId")[0].value = rootMessageId;
		
		document.getElementById("reply").style.display = 'block';
		document.getElementsByName("body")[0].focus();
	}

	function discard()
	{
		document.getElementById("reply").style.display = 'none';
		return false;
	}
</script>
<table width="90%" align="center" cellspacing="2" cellpadding="2">
	<tr>
		<td height="10"/>
	</tr>
	<tr>
		<td>
			<form method="post" action='<html:rewrite page="/user/sendMail.do" />'>
				<input type="hidden" name="method" value="sendMail" />
				<input type="hidden" name="replyOf" value="" />
				<input type="hidden" name="sender" value="" />
				<input type="hidden" name="subject" value="" />
				<input type="hidden" name="rootMessageId" value="" />
				<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%" class="mail-display-area">
					<tr>
						<td class="mail-header">
							<table>
								<tr>
									<td class="mail-header-item">
										<a class="mail-tab" href='<html:rewrite page="/user/getMails.do?method=getInbox" />'>
											Inbox
										</a>
									</td>
									<td class="mail-header-item">
										<a class="mail-tab" href='<html:rewrite page="/user/getMailCompose.do?method=getCompose" />'>
											Compose
										</a>
									</td>
									<td class="mail-header-item">
										<a class="mail-tab" href='<html:rewrite page="/user/getSentItems.do?method=getSentItems" />'>
											Sent Items
										</a>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<logic:present name="conversation" scope="request">
								<table width="98%" align="left">
									<logic:iterate id="message" name="conversation" scope="request">
										<tr>
											<td>
												<fieldset class="fieldset">
													<legend><img src='<html:rewrite page="/resources/images/prt${message.priority}.gif"/>' />&nbsp;${message.sentDate}</legend>
													<table width="100%" align="left">
														<tr>
															<td width="4%" class="conversation-label">From</td>
															<td class="conversation-value"> ${message.sender}</td>
															<td align="right" rowspan="2" nowrap>
																<logic:notEqual name="LOGIN_CREDENTIALS" property="userId" value="${message.senderId}" >
																<logic:notEqual name="message" property="senderId" value="reminder">
																	<a class="link" href="#" onclick="reply('${message.id}', '${LOGIN_CREDENTIALS.userId}', '${message.senderId}', '${message.subject}', '${message.priority}', '${message.rootMessageId}');">Reply</a>
																	&nbsp;&nbsp;&nbsp;&nbsp;
																	<a href='<html:rewrite page="/user/markUnread.do?method=markUnread&id=${message.id}"/>' class="link">Mark Unread</a>
																</logic:notEqual>
																</logic:notEqual>
															</td>
														</tr>
														<tr>
															<td class="conversation-label">To</td>
															<td class="conversation-value"> ${message.to}</td>
														</tr>
														<tr>
															<td class="conversation-label">Subject</td>
															<td colspan="2" class="conversation-value"> ${message.subject}</td>
														</tr>
														<tr>
															<td colspan="3" class="message-body"> ${message.body} <br> </td>
														</tr>
													</table>
												</fieldset>
											</td>
										</tr>
									</logic:iterate>
									<tr>
										<td align="center">
											<div id="reply" style="display: none;">
												<fieldset class="fieldset">
													<legend>Reply</legend>
													<table border="0">
														<tr>
															<td/>
															<td colspan="2" class="content-panel-label">
																<table>
																	<tr>
																		<td class="content-panel-label">
																			Priority
																		</td>
																		<td>
																			<select name="priority">
																				<option value="0">Low</option>
																				<option value="1">Medium</option>
																				<option value="2">High</option>
																			</select>
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
														<tr>
															<td class="content-panel-label">
																To
															</td>
															<td colspan="2">
																<input type="text" name="to" value="" size="107" class="field-text-box"/>
															</td>
														</tr>
														<tr>
															<td/>
															<td colspan="2">
																<textarea name="body" rows="7" cols="110" class="message-body-compose"></textarea>
															</td>
														</tr>
														<tr>
															<td>
																<font class="error">(Max 1000 characters)</font>
															</td>
															<td align="right">
																<input type="submit" value="Send" onclick="">
																<input type="submit" value="Discard" onclick="return discard();">
															</td>
														</tr>
													</table>
												</fieldset>
											</div>
										</td>
									</tr>
								</table>
							</logic:present>
						</td>
					</tr>
					<tr>
						<td>
							<a name="div"></a>
						</td>
					</tr>
				</table>
			</form>
		</td>
	</tr>
</table>
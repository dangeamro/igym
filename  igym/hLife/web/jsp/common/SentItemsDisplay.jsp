<%@ include file="/jsp/common/Taglibs.jsp" %>

<logic:present name="sentItemRows" scope="request">
	<tr>
		<td>
			<table width="98%" align="center" class="mail-display-panel" cellpadding="0" cellspacing="0">
				<tr class="inbox-header">
					<td width="3%">
<!--						<input type="checkbox" name="headerChkBox">-->
					</td>
					<td width="3%">
					</td>
					<td width="20%">
						To
					</td>
					<td width="*">
						Subject
					</td>
					<td width="20%">
						Sent on
					</td>
				</tr>
				<logic:notEmpty name="sentItemRows" scope="request">
					<logic:iterate id="row" name="sentItemRows" scope="request" indexId="rowNum">
						<tr class="inbox-message-row" id="row${rowNum}">
							<td><input type="checkbox" name="msgChkBox" value="${row.messageId}" onclick="highlightRow('row${rowNum}',${row.read},this.checked);checkUnchekHeader()"></td>
							<td nowrap><img src='<html:rewrite page="/resources/images/prt${row.priority}.gif"/>' /></td>
							<td nowrap>${row.recipients}</td>
							<td nowrap><a href='<html:rewrite page="/${targetURL}&id=${row.rootMessageId}"/>'  class="link" style="font-size: 12px; text-decoration: none;">${row.subject}</a></td>
							<td nowrap>${row.sentOn}</td>
						</tr>
						<tr>
							<td colspan="5" height="1" style="background-color: #B2B4BF;"></td>
						</tr>
					</logic:iterate>
				</logic:notEmpty>
				<logic:empty name="sentItemRows" scope="request">
					<tr>
						<td colspan="8" height="60" class="search-result-text-data" style="text-align: center;font-family: Arial; font-size: 13;">
							No Sent Items.
						</td>
					</tr>
				</logic:empty>
			</table>
		</td>
	</tr>
	<logic:notEmpty name="sentItemRows" scope="request">
	<tr>
		<td>
			<table width="98%" cellpadding="0" cellspacing="0" align="center">
				<tr>
					<td colspan="5" class="content-panel-data">
						Select:&nbsp;&nbsp;<a href="#" class="link" onclick="checkUnchekAll(true);">All</a>&nbsp;&nbsp;<a href="#" class="link" onclick="checkUnchekAll(false);">None</a>
					</td>
				</tr>
				<tr>
					<td height="5"></td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="Delete" onClick="return prepareList();">
					</td>
				</tr>
			</table>
		</td>
	</tr>
	</logic:notEmpty>
</logic:present>
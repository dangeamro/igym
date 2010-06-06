<%@ include file="/jsp/common/Taglibs.jsp" %>
<script src='<html:rewrite page="/resources/js/scw.js"/>'></script>
<script> 
function addZero(vNumber){ 
    return ((vNumber < 10) ? "0" : "") + vNumber 
} 
      
function formatDate(vDate, vFormat){ 
    var vDay              = addZero(vDate.getDate()); 
    var vMonth            = addZero(vDate.getMonth()+1); 
    var vYearLong         = addZero(vDate.getFullYear()); 
    var vYearShort        = addZero(vDate.getFullYear().toString().substring(3,4)); 
    var vYear             = (vFormat.indexOf("yyyy")>-1?vYearLong:vYearShort) 
    var vHour             = addZero(vDate.getHours()); 
    var vMinute           = addZero(vDate.getMinutes()); 
    var vSecond           = addZero(vDate.getSeconds()); 
    var vDateString       = vFormat.replace(/dd/g, vDay).replace(/MM/g, vMonth).replace(/y{1,4}/g, vYear) 
    vDateString           = vDateString.replace(/hh/g, vHour).replace(/mm/g, vMinute).replace(/ss/g, vSecond) 
    return vDateString 
  }
  
  function loadDiv(){
	document.addStatusAndMeasurement.date.value = formatDate(new Date(),'MM/dd/yyyy');
  }
  
  function doCancel(){
  	if(confirm('Quit without saving this record?')){
	  	document.addStatusAndMeasurement.action = '<html:rewrite page="/admin/getStatusAndMeasurements.do" />';
  		document.addStatusAndMeasurement.method.value = 'getStatusAndMeasurement';
  		document.addStatusAndMeasurement.submit();
  	}
  }
  
</script>

<body onload="loadDiv()">

<form name="addStatusAndMeasurement" action="addStatusAndMeasurements.do" method="post">
<input type="hidden" name="method" value="addStatusAndMeasurement">
<input type="hidden" name="targetUserId" value="${TARGET_USER.userId}">
<input type="hidden" name="userId" value="${TARGET_USER.userId}">
<table align="center" width="80%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td>
			<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%" class="customer-name-panel">
				<tr>
					<td colspan="4" class="customet-name-panel">
						${TARGET_USER.displayName}
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%" class="content-panel-display-area">
				<tr>
					<td class="content-panel-h2" colspan="4">Manual Measurements
					</td>
				</tr>	
				<tr>
					<td height="20"></td>
				</tr>
				<tr>
					<td class="content-panel-label">Date</td>
					<td  class="content-panel-val">
						<input type="text" class="field-text-box" size="10" name="date" readonly>
						<img style="cursor:hand;" src='<html:rewrite page="/resources/images/calendar.gif"/>' onClick="javascript:scwShow(scwID('date'),this)"/>
					</td>
				</tr>
				<tr>
					<td height="5">
					</td>
				</tr>
				<tr>
					<td align="left" colspan="4" >
						<table  width="80%" align="center">
							<tr>
								<td height="1" style="background-color: #336699;"></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td height="5">
					</td>
				</tr>
				<tr>	
					<td class="content-panel-label"> Weight</td>
					<td>
						<input type="text" class="field-text-box" name="weight" size="10">
						<font class="content-panel-sub-label">lbs</font>
					</td>
				</tr>
				<tr>
					<td class="content-panel-label">Neck</td>
					<td>
						<input type="text" class="field-text-box" name="neck" size="10">
						<font class="content-panel-sub-label">cms</font>
					</td>
					<td class="content-panel-label">Upper Chest</td>
					<td>
						<input type="text" class="field-text-box" name="upperChest" size="10">
						<font class="content-panel-sub-label">cms</font>
					</td>
				</tr>
				<tr>
					<td class="content-panel-label">Upper Arms - Right </td>
					<td>
						<input type="text" class="field-text-box" name="uppArmRight" size="10">
						<font class="content-panel-sub-label">cms</font>
					</td>
					<td class="content-panel-label">Upper Arms - Left</td>
					<td>
						<input type="text" class="field-text-box" name="uppArmLeft" size="10">
						<font class="content-panel-sub-label">cms</font>
					</td>
				</tr>
				<tr>
					<td class="content-panel-label">Waist</td>
					<td>
						<input type="text" class="field-text-box" name="waist" size="10">
						<font class="content-panel-sub-label">cms</font>
					</td>
					<td class="content-panel-label">Hips</td>
					<td>
						<input type="text" class="field-text-box" name="hips" size="10">
						<font class="content-panel-sub-label">cms</font>
					</td>
				</tr>
				<tr>
					<td class="content-panel-label">Upper Thighs - Right</td>
					<td>
						<input type="text" class="field-text-box" name="uppThighsRight" size="10">
						<font class="content-panel-sub-label">cms</font>
					</td>
					<td class="content-panel-label">Upper Thighs - Left</td>
					<td>
						<input type="text" class="field-text-box" name="uppThighsLeft" size="10">
						<font class="content-panel-sub-label">cms</font>
					</td>
				</tr>
				<tr>
					<td class="content-panel-label">Lower Thighs - Right</td>
					<td>
						<input type="text" class="field-text-box" name="lowThighsRight" size="10">
						<font class="content-panel-sub-label">cms</font>
					</td>
					<td class="content-panel-label">Lower Thighs - Left</td>
					<td>
						<input type="text" class="field-text-box" name="lowThighsLeft" size="10">
						<font class="content-panel-sub-label">cms</font>
					</td>
				</tr>
				<tr>
					<td class="content-panel-label">Calves - Right</td>
					<td>
						<input type="text" class="field-text-box" name="calvesRight" size="10">
						<font class="content-panel-sub-label">cms</font>
					</td>
					<td class="content-panel-label">Calves - Left</td>
					<td>
						<input type="text" class="field-text-box" name="calvesLeft" size="10">
						<font class="content-panel-sub-label">cms</font>
					</td>
				</tr>
				<tr>
					<td class="content-panel-label">Ankles - Right</td>
					<td>
						<input type="text" class="field-text-box" name="anklesRight" size="10">
						<font class="content-panel-sub-label">cms</font>
					</td>
					<td class="content-panel-label">Ankles - Left</td>
					<td>
						<input type="text" class="field-text-box" name="anklesLeft" size="10">
						<font class="content-panel-sub-label">cms</font>
					</td>
				</tr>
				<tr>
					<td height="20"></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr><td>
					<table align="center" border="0" cellpadding="3" cellspacing="0" width="100%">
					<tr><td height="10"/>
					</tr>
					<tr>
						<td align="center"><input type="submit" value="Add" onClick="doSubmit()"/> 
						<input type="button" value="Cancel" name="cancel" onclick="doCancel()" /></td>
					</tr>
					</table>
				</td></tr>
</table>
</form>
</body>

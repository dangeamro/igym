<%@ include file="/jsp/common/Taglibs.jsp" %>
<script src='<html:rewrite page="/resources/js/scw.js"/>'></script>
<script src='<html:rewrite page="/resources/js/common-scripts.js"/>'></script>
      
<script>

function changeMonthly(){
	if(document.supplimentTracker.isMonthly.value=="1"){
	document.supplimentTracker.n_s_Complex.value='${monthlyDefaultValues.n_s_Complex}';
	document.supplimentTracker.n_s_Complex.readOnly=true;
	document.supplimentTracker.glutamine.value='${monthlyDefaultValues.glutamine}';
	document.supplimentTracker.glutamine.readOnly=true;
	document.supplimentTracker.m_s_oil.value='${monthlyDefaultValues.m_s_oil}';
	document.supplimentTracker.m_s_oil.readOnly=true;
	document.supplimentTracker.oxyOptimizer.value='${monthlyDefaultValues.oxyOptimizer}';
	document.supplimentTracker.oxyOptimizer.readOnly=true;
	document.supplimentTracker.m_s_complex.value='${monthlyDefaultValues.m_s_complex}';
	document.supplimentTracker.m_s_complex.readOnly=true;
	document.supplimentTracker.healthSupport.value='${monthlyDefaultValues.healthSupport}';
	document.supplimentTracker.healthSupport.readOnly=true;
	document.supplimentTracker.efaSupport.value='${monthlyDefaultValues.efaSupport}';
	//document.supplimentTracker.efaSupport.readOnly=true;
	document.supplimentTracker.efaSupport.readOnly=true;
	}
	else{
	document.supplimentTracker.n_s_Complex.readOnly=false;
	document.supplimentTracker.glutamine.readOnly=false;
	document.supplimentTracker.m_s_oil.readOnly=false;
	document.supplimentTracker.oxyOptimizer.readOnly=false;
	document.supplimentTracker.m_s_complex.readOnly=false;
	document.supplimentTracker.healthSupport.readOnly=false;
	document.supplimentTracker.efaSupport.readOnly=false;
	
	}
}
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
	document.supplimentTracker.date.value = formatDate(new Date(),'MM/dd/yyyy');
	document.supplimentTracker.isMonthly.value="1";
	changeMonthly();
  }
  
  function doCancel(){
  	document.supplimentTracker.method.value = "getSupplimentTracker";
  	document.supplimentTracker.action = "<html:rewrite page='/admin/getSupplimentTracker.do?targetUserId=${TARGET_USER.userId}'/>";
  	document.supplimentTracker.submit();
  }

</script>
<body onload="loadDiv()">
<form name="supplimentTracker" action="<html:rewrite page="/admin/addSupplimentTracker.do" />" method="post">
<input type="hidden" name="method" value="addSupplimentTracker">
<input type="hidden" name="targetUserId" value="${TARGET_USER.userId}">
<input type="hidden" name="userId" value="${TARGET_USER.userId}">

<table align="center" width="80%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td>
			<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td width="*" class="customer-name-panel">
						${TARGET_USER.displayName}
					</td>
					<td width="3%">
						<a href='<html:rewrite page="/admin/getUserMailCompose.do?method=getCompose&targetUserId=${TARGET_USER.userId}"/>'>
							<img class="email-icon" src='<html:rewrite page="/resources/images/send-mail.jpg"/>' title="send a mail to ${TARGET_USER.displayName}">
						</a>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
	    <td height="10"></td>
	    <td align="center">
	      <font class="error" style="font-size: 13;">
	        	<html:errors/>
	      </font>
	    </td>
	    <td height="10"></td>
	</tr>
	<tr>
		<td>
			<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%" class="content-panel-display-area" style="margin-top: 5px;">
				<tr>
					<td colspan="4" align="left" class="content-panel-h2" style="text-align: left;">
						Add Suppliments
					</td>
				</tr>
				<tr>
					<td height="20"></td>
				</tr>
				<tr>
					<td class="content-panel-label">Date</td>
					<td >
						<input type="text" class="field-text-box" size="10" name="date" readonly onfocus="javascript:scwShow(scwID('date'),this)">
						<img style="cursor:hand;" src='<html:rewrite page="/resources/images/calendar.gif"/>' onClick="javascript:scwShow(scwID('date'),this)"/>
					</td>
					<td class="content-panel-label">Package Type</td>
					<td >
						<select name="isMonthly" onChange="changeMonthly()" class="field-option">
	                    	<option value="1" >Monthly</option>
		                    <option value="2">Non-Monthly</option>
	                    </select>
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
					<td class="content-panel-label"> Nutrition Suppor Complex</td>
					<td>
						<input type="text" class="field-text-box" name="n_s_Complex" size="10" onkeypress="return checkNumber();">
						<font class="content-panel-sub-label">Packs</font>
					</td>

					<td class="content-panel-label"> Glutamine</td>
					<td>
						<input type="text" class="field-text-box" name="glutamine" size="10" onkeypress="return checkNumber();">
						<font class="content-panel-sub-label">Packs</font>
					</td>
				</tr>
				<tr>
					<td class="content-panel-label"> Metabolic Support Oil</td>
					<td>
						<input type="text" class="field-text-box" name="m_s_oil" size="10" onkeypress="return checkNumber();">
						<font class="content-panel-sub-label">Bottles</font>
					</td>

					<td class="content-panel-label"> Oxygen Optimizer</td>
					<td>
						<input type="text" class="field-text-box" name="oxyOptimizer" size="10" onkeypress="return checkNumber();">
						<font class="content-panel-sub-label">Bottles</font>
					</td>
				</tr>
				<tr>
					<td class="content-panel-label"> Metabolic Suppor Complex</td>
					<td>
						<input type="text" class="field-text-box" name="m_s_complex" size="10" onkeypress="return checkNumber();">
						<font class="content-panel-sub-label">Bottles</font>
					</td>

					<td class="content-panel-label"> Men/Women Health Support</td>
					<td>
						<input type="text" class="field-text-box" name="healthSupport" size="10" onkeypress="return checkNumber();">
						<font class="content-panel-sub-label">Bottles</font>
					</td>
				</tr>
				<tr>
					<td class="content-panel-label"> EFA Support Complex</td>
					<td>
						<input type="text" class="field-text-box" name="efaSupport" size="10" onkeypress="return checkNumber();">
						<font class="content-panel-sub-label">Bottles</font>
					</td>
				</tr>
				<tr>
					<td height="20"></td>
				</tr>
	
</table>
</td>
</tr>
</table>

<table align="center" border="0" cellpadding="3" cellspacing="0" width="100%">
	<tr>
		<td height="5"></td>
	</tr>
	<tr>
		<td align="center">
			<input type="submit" value="Save" style="font-weight: bolder; width: 80;"/>
			<input type="button" value="Cancel" name="cancel" style="width: 50;" onclick="doCancel()"/>
		</td>
	</tr>
</table>
</form>
</body>
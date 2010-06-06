<%@ include file="/jsp/common/Taglibs.jsp" %>
<head>
<script language="javascript">


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
		document.mealForm.mealDate.value = formatDate(new Date(),'MM/dd/yyyy');
	}
	
	function doSubmit(mealId){
		document.mealForm.submit();
	}
	
  function doCancel(){
  	if(confirm('Quit without saving this record?')){
	  	document.mealForm.action = '<html:rewrite page="/user/getAllMeals.do" />';
  		document.mealForm.method.value = 'getMeals';
  		document.mealForm.submit();
  	}
  }
	
</script>
</head>
<body onload="loadDiv()">

<form name="mealForm" action="updateMeal.do" method="post">
<input type="hidden" name="method" value="updateMeal">

<input type="hidden" name="mealId" value="${foodForm.mealId}" >
<table align="center" border="0" cellpadding="0" cellspacing="0" width="80%" class="content-panel-display-area">
	<tr>
		<td colspan="4" class="content-panel-h2">
			Date :
			<input type="text" size="8" name="mealDate" style="border: 0; color: white; background-color: transparent; font-size: 14" onfocus="this.blur();">
		 </td>
	</tr>
	<tr>
	<td>
	<table width="100%"	cellpadding="3" cellspacing="0">
	<tr>
		<td class="content-panel-label">Meal Type </td>
		<td class="content-panel-data">${foodForm.mealType}</td>
		<td class="content-panel-label">Time</td>
		<td class="content-panel-data">${foodForm.mealTime}</td>					
	</tr>
	<logic:equal name="foodForm"  property="mealType" value="1"   >
	<tr>
		<td class="content-panel-label">MRP Scoops</td>
		<td class="content-panel-data">${foodForm.mrpScoops}</td>
		<td class="content-panel-label">Metabolic Support Oil</td>
		<td class="content-panel-data">${foodForm.msOil}</td>
	</tr>
	<tr>
		<td class="content-panel-label">Water Amount</td>
		<td class="content-panel-data">${foodForm.waterAmt}</td>
	</tr>
	</logic:equal> 
	
	<logic:equal name="foodForm"  property="mealType" value="2"   >
	<tr>
		<td class="content-panel-label">Protein</td>
		<td class="content-panel-data">${foodForm.proteinName}</td>
		<td class="content-panel-label">Amount</td>
		<td class="content-panel-data">${foodForm.proteinAmt}</td>					
	</tr>
	<tr>
		<td class="content-panel-label">Complex Carbs</td>
		<td class="content-panel-data">${foodForm.complexCarbsName}</td>
		<td class="content-panel-label">Amount</td>
		<td class="content-panel-data">${foodForm.complexCarbsAmt}</td>
	</tr>
	<tr>
		<td class="content-panel-label">Fibrous Carbs</td>
		<td class="content-panel-data">${foodForm.fibrousCarbsName}</td>					
		<td class="content-panel-label">Amount</td>
		<td class="content-panel-data">${foodForm.fibrousCarbsAmt}</td>					
	</tr>
	<tr>
		<td class="content-panel-label">Metabolic Support Oil</td>
		<td class="content-panel-data">${foodForm.msOil}</td>					
		<td class="content-panel-label">EFA Support Complex</td>
		<td class="content-panel-data">${foodForm.efaSupportId}</td>					
	</tr>
	</logic:equal> 	
	<tr>
		<td colspan="4" width="80%" align="center">
			<hr>
		</td>
	</tr>
	<tr>
		<td colspan="4" class="content-panel-label" style="text-align: center">
			Following data can be updated approximately 45-60 minutes after the meal
		</td>
	</tr>
	<tr>
		<td class="content-panel-label">Energy Level ${foodForm.energyLevelName} </td>
		<td class="content-panel-data">
			<select name="energyLevelName" class="field-option" value="${foodForm.energyLevelName}">
				<option value="Dragging" >Dragging</option>
            	<option value="Normal">Normal</option>
            	<option value="Energized">Energized</option>
            </select>
        </td>
		<td class="content-panel-label">Appetite ${foodForm.appetiteName}</td>
		<td class="content-panel-data">
			<select name="appetiteName" class="field-option" value="${foodForm.appetiteName}">
				<option value="Hungry" >Hungry</option>
				<option value="Satisfied">Satisfied</option>
				<option value="Full">Full</option>
			</select>
        </td>
	</tr>
	<tr>
		<td class="content-panel-label">Mood</td>
		<td class="content-panel-data">
			<select name="moodName" class="field-option" value="${foodForm.moodName}">
                  	<option value="Bad" >Bad</option>
                   <option value="Good">Good</option>
                   <option value="Great">Great</option>
                  </select>
              </td>
		<td class="content-panel-label">Digestive State</td>  
		<td class="content-panel-data">
			<select name="digestiveStateName" class="field-option" value="${foodForm.digestiveStateName}">
                  	<option value="Gassy" >Gassy</option>
                   <option value="Normal">Normal</option>
                   <option value="Bloated">Bloated</option>
                  </select>
              </td>
	</tr>
	<tr>
		<td class="content-panel-label">Bowel Movement</td>
		<td class="content-panel-data">
			<select name="bowelMovementName" class="field-option" value="${foodForm.bowelMovementName}">
                  	<option value="yes" >yes</option>
                   <option value="No">No</option>
                  </select>
              </td>
		<td class="content-panel-label">Constipated</td> 
		<td class="content-panel-data">
			<select name="constipatedName" class="field-option" value="${foodForm.constipatedName}">
                      <option value="Normal">Normal</option>
                   <option value="Loose">Loose</option>
                  </select>
              </td>
	</tr>
	<tr>
		<td height="10"></td>
	</tr>	
</table>
	</td>
	</tr>
</table>
<table>
	<tr>
		<td height="10"></td>
	</tr>	
	<tr>
		<td class="content-panel-footer">
			<input type="submit" value="Save"/> 
			<input type="button" value="Cancel" name="cancel" onClick="doCancel()"/>
		</td>
	</tr>
</table>
</form>
</body>

<%@ include file="/jsp/common/Taglibs.jsp"%>
<head>
<script src='<html:rewrite page="/resources/js/TimeValidator.js"/>'></script>
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

  function ChooseType(){
    var index= document.addMeal.mealType.options[document.addMeal.mealType.selectedIndex].value ;
	if(index =="1"){
		 document.getElementById("TypeDiv1").style.display = "block";
 		 document.getElementById("TypeDiv2").style.display = "none";
	}else if(index =="2"){
		 document.getElementById("TypeDiv2").style.display = "block";
		 document.getElementById("TypeDiv1").style.display = "none";
	}	
  }	

  function loadDiv(){
	document.getElementById("TypeDiv1").style.display = "block";
 	document.getElementById("TypeDiv2").style.display = "none";
	document.addMeal.mealDate.value = formatDate(new Date(),'MM/dd/yyyy');
	document.addMeal.mealTime.value= formatDate(new Date(),'hh:mm');
  }

  function doSubmit(){
	var tempTime=document.addMeal.time1.value+document.addMeal.time2.value;
	document.addMeal.mealTime.value="";
	document.addMeal.submit();
  }
	
  function doCancel(){
  	if(confirm('Quit without saving this record?')){
	  	document.addMeal.action = '<html:rewrite page="/user/getAllMeals.do" />';
  		document.addMeal.method.value = 'getMeals';
  		document.addMeal.submit();
  	}
  }
	
</script>
</head>
<body onload="loadDiv()">

<form name="addMeal" action="addMeal.do" method="post">
<input type="hidden" name="method" value="addMeal">

<table align="center" border="0" cellpadding="0" cellspacing="0" width="80%" class="content-panel-display-area">
	<tr>
		<td colspan="4" class="content-panel-h2">
			Date : 
			<input type="text" size="8" name="mealDate" style="border: 0; color: white; background-color: transparent; font-size: 14" onfocus="this.blur();">
		</td>
	</tr>	
	<tr><td>
		<table align="center" border="0" cellpadding="2" cellspacing="2" width="100%">
			<tr>
				<td class="content-panel-label">Meal Type</td>
				<td class="content-panel-data">
					<select name="mealType" onChange="ChooseType()" class="field-option">
                    	<option value="1" >Supplement Meal</option>
	                    <option value="2">FoodMeal</option>
                    </select>
                </td>
				<td class="content-panel-label">Time</td>
				<td class="content-panel-text-box">
					<!-- input type="hidden" name="mealTime" value="test" />
					<input type="hidden" name="mealDate" value="ant"/ -->
					<input type="text" name="mealTime" size="5" maxlength="5" class="field-text-box"
							onkeyup="editValidTime('mealTime',':')"
							onblur="validateTime('mealTime',':')"
							onkeypress="return maskSpaceBar()">
				</td>
				
			</tr>	
		</table>
	</td></tr>

	<tr><td>
		<div id="TypeDiv1" >
		<table align="center" border="0" cellpadding="3" cellspacing="0" width="100%">
			<tr>
				<td class="content-panel-label">MRP Scoops</td>
				<td class="content-panel-data"><input type="text" name="mrpScoops" size="10" class="field-text-box"></td>
				<td class="content-panel-label">Metabolic Support Oil</td>
				<td class="content-panel-data"><input type="text" name="msOil" size="10" class="field-text-box"></td>
			</tr>
			<tr>
				<td class="content-panel-label">Water Amount</td>
				<td class="content-panel-data"><input type="text" name="waterAmt" size="10" class="field-text-box"></td>
			</tr>
	 	 </table></div>
	 </td></tr>
	 
	 <tr><td><div id="TypeDiv2" >
		<table align="center" border="0" cellpadding="3" cellspacing="0" width="100%">		
			<tr>
				<td class="content-panel-label">Protein</td>
				<td class="content-panel-data"><input type="text" name="proteinName" size="10" class="field-text-box"></td>
				<td class="content-panel-label">Amount</td>
				<td class="content-panel-data"><input type="text" name="proteinAmt" size="10" class="field-text-box"></td>
			</tr>
			<tr>
				<td class="content-panel-label">Complex Carbs</td>
				<td class="content-panel-data"><input type="text" name="complexCarbsName" size="10" class="field-text-box"></td>
				<td class="content-panel-label">Amount</td>
				<td class="content-panel-data"><input type="text" name="complexCarbsAmt" size="10" class="field-text-box"></td>
			</tr>
			<tr>
				<td class="content-panel-label">Fibrous Carbs</td>
				<td class="content-panel-data"><input type="text" name="fibrousCarbsName" size="10" class="field-text-box"></td>
				<td class="content-panel-label">Amount</td>
				<td class="content-panel-data"><input type="text" name="fibrousCarbsAmt" size="10" class="field-text-box"></td>
			</tr>
			<tr>
				<td class="content-panel-label">Metabolic Support Oil</td>
				<td class="content-panel-data"><input type="text" name="msOil" size="10" class="field-text-box"></td>
				<td class="content-panel-label">EFA Support Complex</td>
				<td class="content-panel-data"><input type="text" name="efaSupportName" size="10" class="field-text-box"></td>
			</tr>
		</table></div>
	</td></tr>
	
	<tr>
		<td>
		<table align="center" border="0" cellpadding="3" cellspacing="0" width="100%">		
			<tr>
				<td colspan="4" class="content-panel-label" style="text-align: center">
					Please provide the following data approximately 45-60 minutes later of your meal
				</td>
			</tr>
			<tr>
				<td class="content-panel-label">Energy Level</td>
				<td class="content-panel-data">
					<select name="energyLevelName" class="field-option">
                    	<option value="Dragging" >Dragging</option>
	                    <option value="Normal">Normal</option>
	                    <option value="Energized">Energized</option>
                    </select>
                </td>
				<td class="content-panel-label">Appetite</td>
				<td class="content-panel-data">
					<select name="appetiteName" class="field-option">
                    	<option value="Hungry" >Hungry</option>
	                    <option value="Satisfied">Satisfied</option>
	                    <option value="Full">Full</option>
                    </select>
                </td>
			</tr>
			
			<tr>
				<td class="content-panel-label">Mood</td>
				<td class="content-panel-data">
					<select name="moodName" class="field-option">
                    	<option value="Bad" >Bad</option>
	                    <option value="Good">Good</option>
	                    <option value="Great">Great</option>
                    </select>
                </td>
				<td class="content-panel-label">Digestive State</td>  
				<td class="content-panel-data">
					<select name="digestiveStateName" class="field-option">
                    	<option value="Gassy" >Gassy</option>
	                    <option value="Normal">Normal</option>
	                    <option value="Bloated">Bloated</option>
                    </select>
                </td>
			</tr>
			
			<tr>
				<td class="content-panel-label">Bowel Movement</td>
				<td class="content-panel-data">
					<select name="bowelMovementName" class="field-option">
                    	<option value="yes" >yes</option>
	                    <option value="No">No</option>
                    </select>
                </td>
				<td class="content-panel-label">Constipated</td> 
				<td class="content-panel-data">
					<select name="constipatedName" class="field-option">
                        <option value="Normal">Normal</option>
	                    <option value="Loose">Loose</option>
                    </select>
                </td>
			</tr>
		</table>
	</td>
	</tr>
	<tr>
		<td height="10"></td>
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

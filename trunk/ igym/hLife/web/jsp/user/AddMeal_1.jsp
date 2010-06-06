<%@ include file="/jsp/common/Taglibs.jsp"%>
<%@ page import="java.util.ArrayList"%>
<% String foodInfo=""; %>
<html>
<head>
	<title>DHTML Grid samples. dhtmlXGrid - Add/Delete Rows in Grid</title>

	<link rel="STYLESHEET" type="text/css" href='<html:rewrite page="/resources/css/dhtmlXGrid.css"/>'>
	<link rel="STYLESHEET" type="text/css" href='<html:rewrite page="/resources/css/dhtmlXGrid_skins.css"/>'>
	<script  src='<html:rewrite page="/resources/js/grid/dhtmlXCommon.js"/>'></script>
	<script  src='<html:rewrite page="/resources/js/grid/dhtmlXGrid.js"/>'></script>		
	<script  src='<html:rewrite page="/resources/js/grid/dhtmlXGridCell.js"/>'></script>

<style>
	h1 {cursor:hand;font-size:16px;margin-left:10px;line-height:10px}
	xmp {color:green;font-size:12px;margin:0px;font-family:courier;background-color:#e6e6fa;padding:2px}
	div.hdr{
		background-color:lightgrey;
		margin-bottom:10px;
		padding-left:10px;
	}
	.even{
        background-color:#FFFFFF;
    }
    .uneven{
        background-color:#E0F3FE;
    }
</style>

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

	var GridLoadedFlag = 0;
  function chooseType(){
    var flag= document.addMeal.mealType.options[document.addMeal.mealType.selectedIndex].value ;
	if(flag =="1"){
		 document.getElementById("TypeDiv1").style.display = "block";
 		 document.getElementById("TypeDiv2").style.display = "none";
	}else if(flag =="2"){
		 document.getElementById("TypeDiv2").style.display = "block";
		 document.getElementById("TypeDiv1").style.display = "none";
		 if(GridLoadedFlag == 0){
		 	loadSelectedGrid();
		 	GridLoadedFlag = 1;
		 }
	}	
  }	

  function loadDiv(){
	document.addMeal.mealDate.value = formatDate(new Date(),'MM/dd/yyyy');
	document.addMeal.mealTime.value= formatDate(new Date(),'hh:mm');
  }

	
  function doCancel(){
  	if(confirm('Quit without saving this record?')){
	  	document.addMeal.action = '<html:rewrite page="/user/getAllMeals.do" />';
  		document.addMeal.method.value = 'getMeals';
  		document.addMeal.submit();
  	}
  }
 
	/*
	 *Params 
			 1.Event happend on textbox;
			 2.Textbox control;
			 3.List box contrl;
	*/
	function textUpdate(oEvent,fsTextBox,fsListBox) {
		var df			= document.forms[0];
		var curPos		= fsListBox.selectedIndex;
		var optLength	= fsListBox.length;
		var str			= fsTextBox.value.replace('^\\s*','');
		var i			= 0;
		var j 			= 0;
		var k			= -1;
		var flag 		= false;
		var cmpFoodName;
		var foodArr		= new Array();
		
		
		
		i= document.forms[0].selectedIndex.value; // This will be replaced by hidden value
	

	//		
			for(;i<optLength;i++){  // compare entered value with list
				foodArr =foodArray[i].split("\t");
				cmpFoodName = foodArr[1];
				if(str.length <= cmpFoodName.length) {
					cmpFoodName = cmpFoodName.substring(0,str.length );
				}
				if(str.toUpperCase()==cmpFoodName.toUpperCase()){
					flag=true;
					if(k == -1){
						k = i;
					}
				break;
				}
			}		
		
			if(i== optLength){
				alert("Invalid String  "+fsTextBox.value.substring(0,(str.length-1)));
				fsTextBox.value=fsTextBox.value.substring(0,(str.length-1));
				fsListBox.selectedIndex =curPos	;
				document.forms[0].selectedIndex.value=0;
			}else{
				fsListBox.selectedIndex  = k;
				document.forms[0].selectedIndex.value=i;
			}
	
	
	
	
	}
	
	
	/*
	 *Params 
			 1.SelectBox Value;
			 2.SelectBoxTextLength;
			 3.TextBox Control
	*/
	function autoSelect(strOption,strLength,rsTextBox){ 
		var oRange = rsTextBox.createTextRange(); 	// Textbox needs to be selected
		var dealCode=strOption;
		
		if(dealCode.indexOf('|') >= 0) 				// call the func only if the string has '|'
			dealCode=getDealCode(strOption);
		rsTextBox.value = dealCode;
		oRange.moveStart("character",strLength); 
	// 	oRange.moveEnd("character",4);      
	    oRange.select(); 
		rsTextBox.focus();
	}
	
	function doOnLoad(){
		loadSelectedGrid();

	}
	
	function loadSelectedGrid(){
			var i=0;
			var delim="\t";
			selectedgrid.setImagePath("../resources/images/");
			selectedgrid.setHeader("Sno,Food Name,Calories(grams),Fat(grams),Protien(grams),Carbohydrates(grams),dietryFibre(grams),Sugar,Servings,Delete");
			selectedgrid.setInitWidths("35,*,60,60,65,65,65,50,60,50")
			selectedgrid.setColAlign("left,left,left,left,left,left,left,left,left,center");
			selectedgrid.setColTypes("ro,ro,ro,ro,ro,ro,ro,ro,ro,ro");
			selectedgrid.setColSorting("str,str,str,str,str,str,str,str,str,str");
			selectedgrid.setSkin("light");
			selectedgrid.init();
			selectedgrid.enableLightMouseNavigation(true);
			selectedgrid.enableAlterCss("even","uneven");
			selectedgrid.setDelimiter(delim);
		}
		
 
	
	function doAddFood(){

		var listId="";
		var i=0;
		var num=1;
		var foodArr = new Array();
		var foodId;
		var foodName;
		var calories;	
		var fats;
		var protiens;
		var carbohydrates;
		var dietryFibre;
		var sugar;
		var servings;
		var gridString;
		var sno;
		
		sno=document.getElementById("sNo").value;
	
		for (i = 0; i < document.getElementsByName("itemlist")[0].options.length; i++) {
			//alert(document.getElementsByName("itemlist")[0].options[i].value);
			if(document.getElementsByName("itemlist")[0].options[i].selected){
				//alert(document.getElementsByName("itemlist")[0].options[i].value);
				//listId = listId+" "+document.getElementsByName("baseItemList")[i].value;
				var selFoodId=document.getElementsByName("itemlist")[0].options[i].value;
			//	SelectedGridIndex++;
				
				foodArr = foodArray[selFoodId].split('\t');
				foodId= foodArr[0];
				foodName=foodArr[1];
				calories=foodArr[2];	
				fats=foodArr[3];
				protiens=foodArr[4];
				carbohydrates=foodArr[5];
				dietryFibre=foodArr[6];
				sugar=foodArr[7];
				servings=foodArr[8];
				addedFood[index]=""+foodId+"\t"+foodName+"\t"+calories+"\t"+fats+"\t"+protiens+"\t"+carbohydrates+"\t"+dietryFibre+"\t"+sugar+"\t"+servings+"\t"+selFoodId;
				gridString=""+sno+"\t"+foodName+"\t"+calories+"\t"+fats+"\t"+protiens+"\t"+carbohydrates+"\t"+dietryFibre+"\t"+sugar+"\t"+servings;
				sno=(sno*1)+1;
				document.getElementById("sNo").value=sno;
//				selectedgrid.addRow(foodId,gridString+"\t<IMG onclick=\"doDeleteFood("+foodId+")\" width=\"10\" height=\"10\" SRC=\"<html:rewrite page='/resources/images/Delete2.gif'/>\" />");

			 			
				selectedgrid.addRow(selFoodId,gridString+"\t<IMG onclick=\"doDeleteFood("+selFoodId+","+index+")\" width=\"10\" height=\"10\" SRC=\"<html:rewrite page='/resources/images/delete.gif'/>\" />");

			    index++; //increment array index
				document.getElementsByName("itemlist")[0].options[i].selected=false;
				
			}
		}
		updateTotals();
	}
	 

function updateTotals()
{
	var totalCalories = 0;
	var totalProtein = 0;
	var totalCarb = 0;
	var totalFat = 0;

	var dayTotalCal = ${daysNutrientSummary.totalCalories };
	var dayTotalCar = ${daysNutrientSummary.totalCarbohydrates };
	var dayTotalFat = ${daysNutrientSummary.totalFats };
	var dayTotalPro = ${daysNutrientSummary.totalProtien }
	//alert(addedFood.length);	
	for(var i=0; i < addedFood.length; i++ )
	{
		if(addedFood[i] == '$@DEL%#')
			continue;
		var foodArr = addedFood[i].split('\t');
		totalFat += parseFloat(foodArr[3]);
		totalCalories += parseFloat(foodArr[2]);
		totalProtein += parseFloat(foodArr[4]);
		totalCarb += parseFloat(foodArr[5]);
	}

	document.getElementById("mealTotalCal").innerText = roundToTwo(totalCalories);
	document.getElementById("mealTotalPro").innerText = roundToTwo(totalProtein);
	document.getElementById("mealTotalCar").innerText = roundToTwo(totalCarb);
	document.getElementById("mealTotalFat").innerText = roundToTwo(totalFat);
	
	document.getElementById("dayTotalCal").innerText = roundToTwo(dayTotalCal + totalCalories);
	document.getElementById("dayTotalPro").innerText = roundToTwo(dayTotalPro + totalProtein);
	document.getElementById("dayTotalCar").innerText = roundToTwo(dayTotalCar + totalCarb);
	document.getElementById("dayTotalFat").innerText = roundToTwo(dayTotalFat + totalFat);
	
}
function roundToTwo(value){

	return Math.round(value*100)/100;
	
}
	 
function doDeleteFood(selFoodId,arIndex){
 		var foodArr = new Array();
		var foodName;
		var calories;	
		var fats;
		var protiens;
		var foodId;
		var carbohydrates;
		var dietryFibre;
		var sugar;
		var servings;
		var gridString;
		var num=0;
		var increment=1;
		var sno=1;
		var selFoodId;

		foodArr = foodArray[selFoodId].split('\t');
//		foodId= foodArr[0];
//		foodName=foodArr[1];
		calories=foodArr[2];	
		fats=foodArr[3];
		protiens=foodArr[4];
		carbohydrates=foodArr[5];
//		dietryFibre=foodArr[6];
//		sugar=foodArr[7];
//		servings=foodArr[8];


// Remove element from array
	addedFood[arIndex] ="$@DEL%#";
	selectedgrid.clearAll();
	document.getElementById("sNo").value=(document.getElementById("sNo").value*1)-1;
	while(num < addedFood.length ){
		if(!(addedFood[num]=='$@DEL%#')){
			foodArr = addedFood[num].split('\t');
			foodId= foodArr[0];
			foodName=foodArr[1];
			calories=foodArr[2];	
			fats=foodArr[3];
			protiens=foodArr[4];
			carbohydrates=foodArr[5];
			dietryFibre=foodArr[6];
			sugar=foodArr[7];
			servings=foodArr[8];
			selFoodId=foodArr[9];
			gridString=""+(sno++)+"\t"+foodName+"\t"+calories+"\t"+fats+"\t"+protiens+"\t"+carbohydrates+"\t"+dietryFibre+"\t"+sugar+"\t"+servings;
				//alert("Index "+num +" FoodId "+foodId);
			selectedgrid.addRow(selFoodId,gridString+"\t<IMG onclick=\"doDeleteFood("+selFoodId+","+num+")\" width=\"10\" height=\"10\" SRC=\"<html:rewrite page='/resources/images/delete.gif'/>\" />");
		}
		num++;
	}
	document.getElementById("sNo").value=sno;
	updateTotals();	
}	
function changeMotion(){
	if(document.getElementsByName("bowelMovementName")[0].value=="No"){
		document.getElementsByName("motion")[0].selectedIndex=0;
		document.getElementsByName("motion")[0].disabled=true;
	}
	else{
		document.getElementsByName("motion")[0].disabled=false;
	}
}

function doSubmit(){
var num=0;
var foodIdList="";
	while(num < addedFood.length ){
		if(!(addedFood[num]=='$@DEL%#')){
			if(num>0){
				foodIdList+='\t';
			}
			/*foodArr = addedFood[num].split('\t');
			foodId= foodArr[0];
			foodIdList+=foodId;*/
			foodIdList += addedFood[num].split('\t')[0];
		}
		num++;
	}
//alert("foodIdList "+foodIdList);
	document.getElementById("mealId").value=foodIdList;
	document.addMeal.submit();
}
</script>
</head>
<body onload="loadDiv(); chooseType();">

<form name="addMeal" action="addMeal.do" method="post">
<input type="hidden" name="method" value="addMeal">
<input type="hidden" id="mealId" name="mealIdList">


<table align="center" border="0" cellpadding="0" cellspacing="0" width="95%" class="content-panel-display-area">
	<tr>
		<td class="content-panel-h2" >
			<select name="mealType" onChange="chooseType()" class="field-option">
				<option value="2">FoodMeal</option>
				<option value="1" >Supplementary-Food</option>
			</select>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Time
			<input type="text" name="mealTime" size="5" maxlength="5" class="field-text-box"
					onkeyup="editValidTime('mealTime',':')"
					onblur="validateTime('mealTime',':')"
					onkeypress="return maskSpaceBar()">
		</td>
		<td class="content-panel-h2" style="text-align: right">
			Date : 
			<input type="text" size="8" name="mealDate" style="border: 0; color: white; background-color: transparent; font-size: 14" onfocus="this.blur();">
		</td>
		
	</tr>	
	<tr>
		<td>
			<div id="TypeDiv1" >
				<table align="center" border="0" cellpadding="3" cellspacing="0" width="100%">
					<tr>
						<td class="content-panel-label">MRP Scoops</td>
						<td class="content-panel-data"><input type="text" name="mrpScoops" size="10" class="field-text-box"></td>
						<td class="content-panel-label">MRP Scoops Qty</td>
						<td class="content-panel-data"><input type="text" name="mrpScoopsQty" size="10" class="field-text-box"></td>
					</tr>
					<tr>
						<td class="content-panel-label">Metabolic Support Oil</td>
						<td class="content-panel-data"><input type="text" name="msOil" size="10" class="field-text-box"></td>
						<td class="content-panel-label">Water Amount</td>
						<td class="content-panel-data"><input type="text" name="waterAmt" size="10" class="field-text-box"></td>
					</tr>
			 	 </table>
		 	</div>
	 	</td>
	 </tr>
	 <tr>
	 	<td colspan="2"><div id="TypeDiv2" >
		<table align="center" border="0" cellpadding="3" cellspacing="0" width="100%">		
			<tr>
				<td>
					<table width="100%" align="center" border="0">
						<tr><td height="10"></td></tr>
						<tr>
				  			<td class="content-panel-data">
								<input type="hidden" name="selectedIndex" value="0" />
								<input type="hidden" id="sNo" value="1" />
				  				<input type="text" name="testString" size="25" onkeyup ="javascript:textUpdate(event,this,itemlist);"/>
								<input type="button" value="Move&nbsp;&raquo;" onClick="doAddFood();" />
				  			</td>
							<td class="content-panel-label">
							</td>
					  		<!-- td class="content-panel-data">
					  			<input type="button" value="More Items.." style="font-size: 11;"/>
					  		</td-->
				  			
				  		</tr>
				  		<tr>
							<td rowspan="2" class="content-panel-data">
								<select name="itemlist" size="25" multiple class="field-option" style="width: 190pt; font-size: 10;" ondblclick="doAddFood();">
								<%int inc=0; %>	
									<logic:iterate id="foodItem" name="foodList">	
									<option value="<%= inc++ %>" class="${foodItem.colour}" title="${foodItem.itemName}">${foodItem.itemName}</option>
									</logic:iterate>
								</select>
							</td>
							<td>
								<div id="selectGridbox" width="100%" height="245px" style="border-width: 1pt;border-color:black;border-style: solid;background-color:#517298;"></div>
							</td>
						</tr>
					  	<tr>
					  		<td>
						  		<fieldset>
									<table width="80%" align="center" border="0" cellpadding="0" cellspacing="0" style="margin: 5 0 5 0;">
										 <tr style="background-color: #BFBFBF;">
										 	<td></td>
										 	<td class="content-panel-label">
										 		Calories 
										 	</td>
										 	<td class="content-panel-label">
										 		Fat
										 	</td>
										 	<td class="content-panel-label">
										 		Protien
										 	</td>
										 	<td class="content-panel-label">
										 		Carb
										 	</td>
										 </tr>
										 <tr>
										 	<td class="content-panel-label">Actual Totals for this meal</td>
										 	<td class="content-panel-label"><label id="mealTotalCal">0</label> </td>
										 	<td class="content-panel-label"><label id="mealTotalFat">0</label></td>
										 	<td class="content-panel-label"><label id="mealTotalPro">0</label></td>
										 	<td class="content-panel-label"><label id="mealTotalCar">0</label></td>
										 </tr>
										 <tr>
										 	<td colspan="5" height="1" style="background-color: #BFBFBF;"></td>
										 </tr>
										 <tr>
										 	<td class="content-panel-label">Actual Totals for Date</td>
										 	<td class="content-panel-label"><label id="dayTotalCal" class="green">${daysNutrientSummary.totalCalories }</label></td>
										 	<td class="content-panel-label"><label id="dayTotalFat" class="green">${daysNutrientSummary.totalFats}</label></td>
										 	<td class="content-panel-label"><label id="dayTotalPro" class="green">${daysNutrientSummary.totalProtien}</label></td>
										 	<td class="content-panel-label"><label id="dayTotalCar" class="green">${daysNutrientSummary.totalCarbohydrates }</label></td>
										 </tr>
										 <tr>
										 	<td colspan="5" height="1" style="background-color: #BFBFBF;"></td>
										 </tr>
										 <tr style="text-decoration: line-through">
										 	<td class="content-panel-label">Recommended Values</td>
										 	<td class="content-panel-label"><label class="blue">1963</td>
										 	<td class="content-panel-label"><label class="blue">69 gm</td>
										 	<td class="content-panel-label"><label class="blue">347 gm</td>
										 	<td class="content-panel-label"><label class="blue">32 gm</td>
										 </tr>
										 <tr>
										 	<td colspan="5" height="1" style="background-color: #BFBFBF;"></td>
										 </tr>

								    </table>		
							  	</fieldset>
						  	</td>
					  	</tr>
					</table>
				</td>
			
			
		
		</table></div>
	</td></tr>
	<tr>
		<td colspan="2" height="10"/>
	</tr>
	<tr>
		<td colspan="2">
		<fieldset class="fieldset">
		<legend class="legend">Please provide the following data approximately 45-60 minutes later of your meal</legend>
		<table align="center" border="0" cellpadding="3" cellspacing="0" width="100%">		
			<tr>
				<td class="content-panel-label">Energy Level</td>
				<td class="content-panel-data">
					<select name="energyLevelName" class="field-option">
                    	<option value="Dragging" >Dragging</option>
	                    <option value="Normal" selected>Normal</option>
	                    <option value="Energized">Energized</option>
                    </select>
                </td>
				<td class="content-panel-label">Appetite</td>
				<td class="content-panel-data">
					<select name="appetiteName" class="field-option">
                    	<option value="Hungry" >Hungry</option>
	                    <option value="Satisfied" selected>Satisfied</option>
	                    <option value="Full">Full</option>
                    </select>
                </td>
				<td class="content-panel-label">Mood</td>
				<td class="content-panel-data">
					<select name="moodName" class="field-option">
                    	<option value="Bad" >Bad</option>
	                    <option value="Good" selected>Good</option>
	                    <option value="Great">Great</option>
                    </select>
                </td>
			</tr>
			<tr>
				<td class="content-panel-label">Digestive State</td>  
				<td class="content-panel-data">
					<select name="digestiveStateName" class="field-option">
                    	<option value="Gassy" >Gassy</option>
	                    <option value="Normal" selected>Normal</option>
	                    <option value="Bloated">Bloated</option>
                    </select>
                </td>
				<td class="content-panel-label">Bowel Movement</td>
				<td class="content-panel-data">
					<select name="bowelMovementName" class="field-option" onChange="changeMotion();">
                    	<option value="No" selected>No</option>
                    	<option value="Yes" >Yes</option>
                    </select>
                </td>
				<td class="content-panel-label">Motion</td> 
				<td class="content-panel-data">
					<select name="motion" class="field-option" disabled>
                        <option value="NA">-- Select --</option>
                        <option value="Constipated">Constipated</option>
                        <option value="Normal">Normal</option>
	                    <option value="Loose">Loose</option>
	                    <option value="Diarrhea">Diarrhea</option>
                    </select>
                </td>
			</tr>
		</table>
		</fieldset>
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
			<input type="button" value="Save" onclick="doSubmit()"/> 
			<input type="button" value="Cancel" name="cancel" onClick="doCancel()"/>
		</td>
	</tr>
</table>
</form>
</body>
<script language="javascript">

var selectedgrid = new dhtmlXGridObject('selectGridbox');
//var SelectedGridIndex=0;
var foodArray=new Array();
var addedFood=new Array();
var inc =0;
var index=0;
<logic:iterate id="foodItem" name="foodList">		
	foodArray[inc++]=${foodItem.itemId}+"\t"+"${foodItem.itemName}"+"\t"+${foodItem.calories}+"\t"+
	${foodItem.fats}+"\t"+${foodItem.protiens}+"\t"+${foodItem.carbohydrates}+"\t"+${foodItem.dietryFibre}+"\t"+
	${foodItem.sugar}+"\t"+${foodItem.servings}+"\tX"	;
</logic:iterate>
</script> 
</html>


<%@ include file="/jsp/common/Taglibs.jsp"%>
<%@ page import="java.util.ArrayList"%>
<%
	String mealList="";
	if( (request.getAttribute("AssignList") !=null) && ((String)request.getAttribute("AssignList") !="") ){
		mealList = (String) request.getAttribute("AssignList");
	}
 %>
 

<html>
<head>
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

  function doCancel(){
  	if(confirm('Quit without saving this record?')){
	  	document.addMeal.action = '<html:rewrite page="/admin/getAllMeals.do?method=getMeals" />';
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
		var mealIds='<%=mealList%>';
		doLoadFood(mealIds);
		//doLoadFoodItems(mealIds);
	}
	
	
	function doLoadFood(mealIds){
		var incOuter=0;
		var incInner=0;
		var foodArr;
		var foodId;
		var sno=1;
		addedMealList =mealIds.split('\t');
		var temp='';
		while(incOuter <foodArray.length){
			foodArr = foodArray[incOuter].split('\t');
			foodId= foodArr[0];
			incInner=0;
			
			while(incInner < addedMealList.length){
				if(foodId == addedMealList[incInner]){
					foodName=foodArr[1];
					calories=foodArr[2];	
					fats=foodArr[3];
					protiens=foodArr[4];
					carbohydrates=foodArr[5];
					dietryFibre=foodArr[6];
					sugar=foodArr[7];
					servings=foodArr[8];
					selFoodId =incOuter; //selFoodId in addfood represents listbox index
					addedFood[index]=""+foodId+"\t"+foodName+"\t"+calories+"\t"+fats+"\t"+protiens+"\t"+carbohydrates+"\t"+dietryFibre+"\t"+sugar+"\t"+servings+"\t"+selFoodId;
					gridString=""+sno+"\t"+foodName+"\t"+calories+"\t"+fats+"\t"+protiens+"\t"+carbohydrates+"\t"+dietryFibre+"\t"+sugar+"\t"+servings;
					sno=(sno*1)+1;
		
					selectedgrid.addRow(selFoodId,gridString+"\t<IMG onclick=\"doDeleteFood("+selFoodId+","+index+")\" width=\"10\" height=\"10\"  style=\"cursor:hand;\" SRC=\"<html:rewrite page='/resources/images/delete.gif'/>\" />");
				    index++; //increment array index
				}
			incInner++;
			}
		incOuter++;
		}
		document.getElementById("sNo").value=sno;
	
		
	}
	
	function loadSelectedGrid(){
			var i=0;
			var delim="\t";
			selectedgrid.setImagePath("../resources/images/");
			selectedgrid.setHeader("Sno,Food Name,Calories(grams),Fat(grams),Protien(grams),Carbohydrates(grams),dietryFibre(grams),Sugar,Servings,Delete");
			selectedgrid.setInitWidths("40,150,60,60,65,65,65,50,60,50")
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
		var noOfItemsMoved = 0;
		
		sno=document.getElementById("sNo").value;
	
		for (i = 0; i < document.getElementsByName("itemlist")[0].options.length; i++) {
			if(document.getElementsByName("itemlist")[0].options[i].selected){
				if((++noOfItemsMoved) > 50)
				{
					alert('At most 50 food items can be moved at a time. For making more entries please select again');
					return;
				}

				var selFoodId=document.getElementsByName("itemlist")[0].options[i].value;
				
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
//				selectedgrid.addRow(foodId,gridString+"\t<IMG onclick=\"doDeleteFood("+foodId+")\" width=\"10\" height=\"10\" SRC=\"<html:rewrite page='/resources/images/delete.gif'/>\" />");

			 			
				selectedgrid.addRow(selFoodId,gridString+"\t<IMG onclick=\"doDeleteFood("+selFoodId+","+index+")\" width=\"10\" height=\"10\" style=\"cursor:hand;\"  SRC=\"<html:rewrite page='/resources/images/delete.gif'/>\" />");

			    index++; //increment array index
	
				//document.getElementsByName("itemlist")[0].options[i]=null;
			}
		}
		 
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
		calories=foodArr[2];	
		fats=foodArr[3];
		protiens=foodArr[4];
		carbohydrates=foodArr[5];

//Calculation 
			
// Remove element from array
	addedFood[arIndex] ="$@DEL%#"
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
			selectedgrid.addRow(selFoodId,gridString+"\t<IMG onclick=\"doDeleteFood("+selFoodId+","+num+")\" width=\"10\" height=\"10\"  style=\"cursor:hand;\" SRC=\"<html:rewrite page='/resources/images/delete.gif'/>\" />");
		}
		num++;
	}
document.getElementById("sNo").value=sno;
	
}	


function doSubmit(){
var num=0;
var flag="0";
var foodIdList="";
	while(num < addedFood.length ){
		if(!(addedFood[num]=='$@DEL%#')){
			if(flag=="1"){
				foodIdList+='\t';
			}
			foodArr = addedFood[num].split('\t');
			foodId= foodArr[0];
			foodIdList+=foodId;
			flag="1";
		}
		num++;
	}
//alert("foodIdList "+foodIdList);
	document.getElementById("mealId").value=foodIdList;
	document.addMeal.submit();
}
</script>
</head>
<body onload="doOnLoad()">

<form name="addMeal" action="saveAssignMeal.do" method="post">
<input type="hidden" name="method" value="saveAssignMeal">
<input type="hidden" id="mealId" name="mealIdList" value="">
<input type="hidden" name="targetUserId" value="${TARGET_USER.userId}">
<input type="hidden" name="userId" value="${TARGET_USER.userId}">
<table align="center" width="80%" border="0" cellpadding="0" cellspacing="0">

<table align="center" border="0" cellpadding="0" cellspacing="0" width="90%">
	<tr>
		<td>
			<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td width="*" colspan="4" class="customer-name-panel">
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
			<table align="center" border="0" cellpadding="2" cellspacing="0" width="100%" class="content-panel-display-area" style="margin-top: 5px;">
				<tr>
					<td colspan="4" align="left" class="content-panel-h1" style="text-align: left;">
						Assign Food to User
					</td>
				</tr>
				<tr>
					<td height="30"/>
				</tr>
				<tr>
		  			<td colspan="2" class="content-panel-data">
						<input type="hidden" name="selectedIndex" value="0" />
						<input type="hidden" id="sNo" value="1" />
		  				<input type="text" name="testString" size="25" onkeyup ="javascript:textUpdate(event,this,itemlist);"/>
						<input type="button" value="&nbsp;Move&nbsp;&raquo;" onClick="doAddFood();" style="font-size:11px; font-weight: bold; width: 52"/>
		  			</td>
			  		<!-- td class="content-panel-data">
			  			<input type="button" value="More Items.." style="font-size: 11;"/>
			  		</td-->
		  			
		  		</tr>
		  		<tr>
					<td class="content-panel-data">
						<select name="itemlist" size="24" multiple class="field-option" style="width: 190pt; font-size: 10;" ondblclick="doAddFood();">
						<%int inc=0; %>	
							<logic:iterate id="foodItem" name="FoodList">	
							<option name="${foodItem.itemName}"  value="<%= inc++ %>" title="${foodItem.itemName}">${foodItem.itemName}</option>
							</logic:iterate>
						</select>
					</td>
					<td/>
					<td  class="content-panel-data">
						<div id="selectGridbox" height="312px" style="border-width: 1pt;border-color:black;border-style: solid;background-color:#517298; vertical-align: baseline;"></div>
					</td>
				</tr>
				<tr>
					<td height="20"/>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td height="10"></td>
	</tr>	
	<tr>
		<td class="content-panel-footer">
			<input type="button" value="Save" onclick="doSubmit()" style="font-weight: bolder; width: 80"/> 
			<input type="button" value="Cancel" onClick="doCancel()" style="width: 50"/>
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
var addedMealList=new Array();

var inc =0;
var index=0;
<logic:iterate id="foodItem" name="FoodList">		
	foodArray[inc++]=${foodItem.itemId}+"\t"+"${foodItem.itemName}"+"\t"+${foodItem.calories}+"\t"+
	${foodItem.fats}+"\t"+${foodItem.protiens}+"\t"+${foodItem.carbohydrates}+"\t"+${foodItem.dietryFibre}+"\t"+
	${foodItem.sugar}+"\t"+${foodItem.servings}+"\tX"	;
</logic:iterate>
</script> 
</html>


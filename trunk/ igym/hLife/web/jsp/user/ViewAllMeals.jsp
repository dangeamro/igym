<%@ include file="/jsp/common/Taglibs.jsp" %>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%
	int pageCount=0;

	if(request.getAttribute("PageCount")!=null){
		pageCount=((Integer)request.getAttribute("PageCount")).intValue();
	}

	String meal_Date="";
	if(request.getAttribute("MEAL_DATE") != null)
		 meal_Date=(String)request.getAttribute("MEAL_DATE");
	int totalMeals = 0; 

	
	String fileName="";
	if (request.getAttribute("fileName") != null){
		fileName=(String)request.getAttribute("fileName");
	}
	String graphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" + fileName;
	
	
%>


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

		function displayDate(){
			//document.viewMeal.mealDateField.value = formatDate(new Date(),'MM/dd/yyyy');
		}

		function doEdit(mealId){
				document.viewMeal.action="getUpdateMeal.do";
				document.viewMeal.method.value="getMeal";
				document.viewMeal.mealId.value=mealId;		
				document.viewMeal.submit();
		}
		
		function doDelete(mealId,mealNum){
			var answer = confirm ("Want to delete meal number #"+mealNum)
			if (answer){
				document.viewMeal.action="deleteMeal.do";
				document.viewMeal.method.value="deleteFood";
				document.viewMeal.mealId.value=mealId;		
				document.viewMeal.submit();
			}else{
				return;
			}
			
		}

		function  doSubmit(offSet){
			document.viewMeal.method.value="getMeals";
			document.viewMeal.action="getAllMeals.do?pager.offset="+(offSet-1);
			document.viewMeal.pageNum.value=(offSet);
			document.viewMeal.submit();
		}
		function addMealTmp(){
			document.viewMeal.action="addMealTmp.do";
			document.viewMeal.method.value="getAddFood";
			alert(document.viewMeal.method.value);
			document.viewMeal.submit();
		}
		function changeMotion(index){
			if(document.getElementsByName("bowelMovement")[index].value=="No"){
				document.getElementsByName("motionName")[index].selectedIndex=0;
				document.getElementsByName("motionName")[index].disabled=true;
			}
			else{
				document.getElementsByName("motionName")[index].disabled=false;
			}
		}
		function editMood(index){
			for(var i = 0; i< document.getElementsByName("editMood").length;i++){				
				document.getElementsByName("editMood")[i].style.display = "none";
				document.getElementsByName("viewMood")[i].style.display = "block";
			}
			document.getElementsByName("viewMood")[index].style.display = "none";
			document.getElementsByName("editMood")[index].style.display = "block";
					
		}
		function saveMood(index, mealId){
			
			//document.getElementsByName("viewMood")[index].style.display = "block";
			//document.getElementsByName("editMood")[index].style.display = "none";
			document.viewMeal.energyLevelName.value = document.getElementsByName("energyLevel")[index].value;
			//alert(document.viewMeal.energyLevelName.value);
			document.viewMeal.appetiteName.value = document.getElementsByName("appetite")[index].value;
			//alert(document.viewMeal.appetiteName.value);
			document.viewMeal.moodName.value = document.getElementsByName("mood")[index].value;
			//alert(document.viewMeal.moodName.value);
			document.viewMeal.digestiveStateName.value = document.getElementsByName("digestiveState")[index].value;
			//alert(document.viewMeal.digestiveStateName.value);
			document.viewMeal.bowelMovementName.value = document.getElementsByName("bowelMovement")[index].value;
			document.viewMeal.motion.value = document.getElementsByName("motionName")[index].value;
			document.viewMeal.mealId.value = mealId;
			document.viewMeal.method.value = "updateMeal";
			document.viewMeal.action = "<html:rewrite page='/user/updateMeal.do' />";
			document.viewMeal.submit();
		}
		
	</script>
	<script src='<html:rewrite page="/resources/js/overlib.js"/>'></script>
	
</head>
<body onload="displayDate()">
<form name="viewMeal" action="<html:rewrite page="/user/getAddMeal.do" />" method="post">
<input type="hidden" name="method" value="getAddFood" >
<input type="hidden" name="mealId"  >
<input type="hidden" name="appetiteName"  >
<input type="hidden" name="energyLevelName"  >
<input type="hidden" name="moodName"  >
<input type="hidden" name="digestiveStateName"  >
<input type="hidden" name="bowelMovementName"  >
<input type="hidden" name="motion"  >
<input type="hidden" name="pageNum"  >
<table width="80%" align="center" cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td align="center">
			<font class="error-header">
				<html:errors/>
			</font>
		</td>
	</tr>
	<tr>
		<td height="20"/>
	</tr>
	<logic:greaterThan name="PageCount" value="1" scope="request">
	<tr>
		<td>
			<table align="right"  border="0" cellpadding="0" cellspacing="0" class="pager-panel">
				<tr>
					<td>
						<!-- Paginatin code starts Here -->			
						<pg:pager items="<%= pageCount %>" index="center" maxPageItems="1" maxIndexPages="6"  export="offset,currentPageNumber=pageNumber" scope="request">
						<!-- items will have max rowcount -->
						
						  <input type="hidden" name="pager.offset" value="<%= offset %>" />
				
						  
						  <pg:param name="pg"/>
						  <pg:param name="q"/>
					 
					 	  <pg:index>
					 	  	<font class="pager-item">
						    <pg:prev><a href="#" onClick="doSubmit(<%= pageNumber %>)">&lt;&lt;</a></pg:prev></font>
						    <pg:pages><%
					      
						      if (pageNumber == currentPageNumber) { 
						        %><font class="pager-item-current"><%= pageNumber %></font><%
						      } else { 
						        %><font class="pager-item"><a href="#" onClick="doSubmit(<%= pageNumber %>)"><%= pageNumber %></a></font><%
						      }
						    %>
						    </pg:pages>
						    <font class="pager-item">
						    <pg:next><a href="#" onClick="doSubmit(<%= pageNumber %>)">&gt;&gt;</a></pg:next></font>
						    <br></font>
						  </pg:index>
						</pg:pager>
						<!-- Paginatin code Ends Here -->
					</td>
				</tr>
			</table>
		</td>
	</tr>
	</logic:greaterThan>
	<tr>
		<td>
			<table width="100%" align="center" cellpadding="0" cellspacing="0" border="0" class="content-panel-display-area" style="margin-top: 0px;">
				<logic:notPresent name="FoodList" scope="request">
				<tr>
					<td align="center" class="content-panel-h1">
						Food Journal
					</td>
				</tr>
				<tr>
					<td colspan="8" height="60" class="search-result-text-data" style="text-align: center;font-family: Arial; font-size: 13;">
						You have not made any Food Journal entries.
					</td>
				</tr>
				</logic:notPresent>
				<logic:present name="FoodList" scope="request">
				<tr>
					<td class="content-panel-h2">
						Food Journal for : ${MEAL_DATE }
						
					</td>
				</tr>
				<tr>
					<td>
						<table width="100%" align="center" cellpadding="2" cellspacing="2">
							<tr>
								<td>
									<fieldset class="fieldset">
										<legend>Days Total Nutrition Intake</legend>
										<table width=100%>
										<tr>
										<td>
										<table width="100%" align="left" border="0" cellpadding="0" cellspacing="0" style="margin: 5 0 5 0;">
											 <tr style="background-color: #BFBFBF;">
														<td></td>
													 	<td class="content-panel-label">
													 		Totals for the day 
													 	</td>
													 	<td class="content-panel-label">
													 		Suggested Totals
													 	</td>
													 	<td class="content-panel-label">
													 		Difference
													 	</td>
													 	
													 </tr>
													 
											<tr>
												<td class="content-panel-label">Calories</td>
												<td class="content-panel-label" style="text-align: center">${MealSummary.totalCalories }</td>
												<td class="content-panel-label" style="text-align: center"><label class="blue">${SuggestedNutriSummary.suggestedCalorieConsumption }</label></td>
												<td class="content-panel-label">
												${(SuggestedNutriSummary.suggestedCalorieConsumption - MealSummary.totalCalories)}
												&nbsp;
													<img width="12px" src='<html:rewrite page="/resources/images/
													${(SuggestedNutriSummary.suggestedCalorieConsumption - MealSummary.totalCalories) > 0?'green-dot.gif':'red-dot.gif'}"/>' align="bottom" />													
												
												</td>
											</tr>
											<tr>
													 	<td colspan="5" height="1" style="background-color: #BFBFBF;"></td>
													 </tr>
											<tr>
												<td  class="content-panel-label">Fats</td>
												<td  class="content-panel-label" style="text-align: center">${MealSummary.totalFats}</td>
												<td class="content-panel-label" style="text-align: center"><label class="blue">${SuggestedNutriSummary.fatGms }</label></td>
												<td class="content-panel-label">
												${(SuggestedNutriSummary.fatGms - MealSummary.totalFats)}
												&nbsp;
													<img width="12px" src='<html:rewrite page="/resources/images/
													${(SuggestedNutriSummary.fatGms - MealSummary.totalFats) > 0?'green-dot.gif':'red-dot.gif'}"/>' align="bottom" />													
												</td>
											</tr>
											<tr>
											 	<td colspan="5" height="1" style="background-color: #BFBFBF;"></td>
											 </tr>
											<tr>
												<td  class="content-panel-label">Protiens</td>
												<td  class="content-panel-label" style="text-align: center">${MealSummary.totalProtien}</td>
												<td class="content-panel-label" style="text-align: center"><label class="blue">${SuggestedNutriSummary.protienGms }</label></td>
												<td class="content-panel-label">
												${(SuggestedNutriSummary.protienGms - MealSummary.totalProtien)}
												&nbsp;
													<img width="12px" src='<html:rewrite page="/resources/images/
													${(SuggestedNutriSummary.protienGms - MealSummary.totalProtien) > 0?'green-dot.gif':'red-dot.gif'}"/>' align="bottom" />													
												</td>
											</tr>
											<tr>
													 	<td colspan="5" height="1" style="background-color: #BFBFBF;"></td>
													 </tr>
											<tr>
												<td  class="content-panel-label">Carbohydrates</td>
												<td  class="content-panel-label" style="text-align: center">${MealSummary.totalCarbohydrates }</td>
												<td class="content-panel-label" style="text-align: center"><label class="blue">${SuggestedNutriSummary.carbohydratesGms }</label></td>
												<td  class="content-panel-label">
												${SuggestedNutriSummary.carbohydratesGms - MealSummary.totalCarbohydrates}
												&nbsp;
													<img width="12px" src='<html:rewrite page="/resources/images/
													${(SuggestedNutriSummary.carbohydratesGms - MealSummary.totalCarbohydrates) > 0?'green-dot.gif':'red-dot.gif'}"/>' align="bottom" />													
												</td>
											</tr>
											<tr>
												<td colspan="5" height="1" style="background-color: #BFBFBF;"></td>
											 </tr>
										</table>
										</td>
										<td>
												<img src="<%= graphURL %>" width=250 height=100 border=0 usemap="#<%= fileName %>">
										</td>
										</tr>
										</table>
									</fieldset>
								</td>
							</tr>
							<tr>
								<td>
								
								<logic:iterate id="foodItem" name="FoodList" scope="request" indexId="itemNum">
									<fieldset class="fieldset">
									<legend>
										Meal #${itemNum+1} / Time ${foodItem.time}
										<input type="button" value="Delete" onclick="doDelete(${foodItem.mealId},${itemNum+1})" style="font-size: 10px">&nbsp;
									</legend>
									
									
									<table width="100%" align="center" cellpadding="0" cellspacing="0">
									<logic:equal name="foodItem"  property="mealType" value="1">
									<bean:define id="supplementFood" name="foodItem" property="nonFoodMeal" />
										<tr>
											<td colspan="6" height="10"></td>
										</tr>
										<tr>
											<td width="15%" class="content-panel-label">Mrp Scoops</td>
											<td width="15%" class="content-panel-data">${supplementFood.mrpScoops }</td>
											<td width="15%" class="content-panel-label">Mrp Scoops Qty</td>
											<td width="15%" class="content-panel-data">${supplementFood.mrpScoopsQty}</td>
											<td width="15%" class="content-panel-label">Ms Oil</td>
											<td width="15%" class="content-panel-data">${supplementFood.msOil}</td>
										</tr>
										<tr>
											<td width="15%" class="content-panel-label">Water Amount</td>
											<td width="15%" class="content-panel-data">${supplementFood.waterAmt}</td>
										</tr>
										<tr>
											<td colspan="6" height="5"></td>
										</tr>
									</logic:equal>
									<logic:equal name="foodItem" property="mealType" value="2">
										<tr style="background-color: #99AACD;">
											<td class="content-panel-data" style="padding: 2px; border-right: 1px solid; width: *;">
												<b>Item Name</b>
											</td>
											<td class="content-panel-data" style="padding: 2px; border-right: 1px solid; width: 5%;">
												<b>Calories</b>
											</td>
											<td class="content-panel-data" style="padding: 2px; border-right: 1px solid; width: 5%;">
												<b>Fats</b>
											</td>
											<td class="content-panel-data" style="padding: 2px; border-right: 1px solid; width: 5%;">
												<b>Proteins</b>
											</td>
											<td class="content-panel-data" style="padding: 2px; border-right: 1px solid; width: 5%;">
												<b>Carb</b>
											</td>
<!--											<td class="content-panel-data" style="padding: 2px; border-right: 1px solid">-->
<!--												<b>Fibre</b>-->
<!--											</td>-->
<!--											<td class="content-panel-data">-->
<!--												<b>Sugar</b>-->
<!--											</td>-->
										</tr>
										<tr>
											<td colspan="7" height="1" style="background-color: #336699;"></td>
										</tr>
										<logic:iterate id="foodEntry" name="foodItem" property="foodMealList">
											<tr>
												<td class="content-panel-data" style="padding-left: 2px; border-right: 1px solid #BFBFBF;"><label class="${foodEntry.colour}" style="overflow: hidden">${foodEntry.itemName}</label></td>
												<td class="content-panel-data" style="padding-left: 2px; border-right: 1px solid #BFBFBF;">${foodEntry.calories}</td>
												<td class="content-panel-data" style="padding-left: 2px; border-right: 1px solid #BFBFBF;">${foodEntry.fats}</td>
												<td class="content-panel-data" style="padding-left: 2px; border-right: 1px solid #BFBFBF;">${foodEntry.protiens}</td>
												<td class="content-panel-data" style="padding-left: 2px; border-right: 1px solid #BFBFBF;">${foodEntry.carbohydrates}</td>
<!--												<td class="content-panel-data" style="padding-left: 2px; border-right: 1px solid #BFBFBF;">${foodEntry.dietryFibre}</td>-->
<!--												<td class="content-panel-data">${foodEntry.sugar}</td>-->
											</tr>
											<tr>
												<td colspan="7" height="1" style="background-color: #BFBFBF;"></td>
											</tr>
										</logic:iterate>
									</logic:equal>
									<logic:equal name="foodItem" property="mealType" value="3">
										<bean:define id="ManualFood" name="foodItem" property="manualFoodModel" />
										<tr>
											<td class="content-panel-label">Protein</td>
											<td class="content-panel-data">${ManualFood.proteinName }</td>
											<td class="content-panel-label">Amount</td>
											<td class="content-panel-data">${ManualFood.proteinAmt }</td>
										</tr>
										<tr>
											<td class="content-panel-label">Complex Carbs</td>
											<td class="content-panel-data">${ManualFood.complexCarbsName }</td>
											<td class="content-panel-label">Amount</td>
											<td class="content-panel-data">${ManualFood.complexCarbsAmt }</td>
										</tr>
										<tr>
											<td class="content-panel-label">Fibrous Carbs</td>
											<td class="content-panel-data">${ManualFood.fibrousCarbsName }</td>
											<td class="content-panel-label">Amount</td>
											<td class="content-panel-data">${ManualFood.fibrousCarbsAmt }</td>
										</tr>
										<tr>
											<td class="content-panel-label">Metabolic Support Oil</td>
											<td class="content-panel-data">${ManualFood.msOil }</td>
											<td class="content-panel-label">Essential Fats</td>
											<td class="content-panel-data">${ManualFood.esentialFats }</td>
										</tr>
										<tr>
											<td class="content-panel-label">Condiments</td>
											<td class="content-panel-data">${ManualFood.condimentsName }</td>
											<td class="content-panel-label">Amount</td>
											<td class="content-panel-data">${ManualFood.condimentsAmt }</td>
										</tr>
										<tr>
											<td class="content-panel-label">Sauce</td>
											<td class="content-panel-data">${ManualFood.sauceName }</td>
											<td class="content-panel-label">Amount</td>
											<td class="content-panel-data">${ManualFood.sauceAmt }</td>
										</tr>
									</logic:equal>
									<tr>
										<td colspan="7" height="1" style="background-color: #336699;"></td>
									</tr>
									<tr>
									<td colspan="7">
									<div id = viewMood> 	
									<table border="0" width="100%">
										<tr>
											<td class="content-panel-label">Energy Level</td>
											<td class="content-panel-data">${foodItem.energyLevel}</td>
											<td class="content-panel-label">Appetite</td>
											<td class="content-panel-data">${foodItem.appetite}</td>
											<td class="content-panel-label">Mood</td>
											<td class="content-panel-data">${foodItem.mood}</td>
											<td rowspan="2" valign="center"><input type="button" value="Edit Mood" onclick="editMood(${itemNum})" style="font-size: 10px"></td>		
										</tr>
										<tr>
											<td class="content-panel-label">Digestive State</td>  
											<td class="content-panel-data">${foodItem.digestiveState}</td>
											<td class="content-panel-label">Bowel Movement</td>
											<td class="content-panel-data">${foodItem.bowelMovement}</td>
										</tr>
									</table>
									</div>
									<div id = editMood style="display: none"> 	
									<table border="0" width="100%">
										<tr>
											<td class="content-panel-label">Energy Level</td>
											<td class="content-panel-data">
												<select name="energyLevel" class="field-option">
							                    	<option value="Dragging" ${foodItem.energyLevel == 'Dragging'?'selected':''} >Dragging</option>
								                    <option value="Normal" ${foodItem.energyLevel == 'Normal'?'selected':''}>Normal</option>
								                    <option value="Energized" ${foodItem.energyLevel == 'Energized'?'selected':''}>Energized</option>
							                    </select>
							                </td>
											<td class="content-panel-label">Appetite</td>
											<td class="content-panel-data">
												<select name="appetite" class="field-option">
							                    	<option value="Hungry" ${foodItem.appetite == 'Hungry'?'selected':''}>Hungry</option>
								                    <option value="Satisfied" ${foodItem.appetite == 'Satisfied'?'selected':''}>Satisfied</option>
								                    <option value="Full" ${foodItem.appetite == 'Full'?'selected':''}>Full</option>
							                    </select>
							                </td>
											<td class="content-panel-label">Mood</td>
											<td class="content-panel-data">
												<select name="mood" class="field-option">
							                    	<option value="Bad" ${foodItem.mood == 'Bad'?'selected':''}>Bad</option>
								                    <option value="Good" ${foodItem.mood == 'Good'?'selected':''}>Good</option>
								                    <option value="Great" ${foodItem.mood == 'Great'?'selected':''}>Great</option>
							                    </select>
							                </td>
							                <td rowspan="2" valign="center"><input type="button" value="Save" onclick="saveMood(${itemNum},${foodItem.mealId})" style="font-size: 10px"></td>		
										
										</tr>
										<tr>
											<td class="content-panel-label">Digestive State</td>  
											<td class="content-panel-data">
												<select name="digestiveState" class="field-option">
							                    	<option value="Gassy" ${foodItem.digestiveState == 'Gassy'?'selected':''}>Gassy</option>
								                    <option value="Normal" ${foodItem.digestiveState == 'Normal'?'selected':''}>Normal</option>
								                    <option value="Bloated" ${foodItem.digestiveState == 'Bloated'?'selected':''}>Bloated</option>
							                    </select>
							                </td>
											<td class="content-panel-label">Bowel Movement</td>
											<td class="content-panel-data">
												<select name="bowelMovement" class="field-option" onChange="changeMotion(${itemNum});">
							                    	<option value="No" ${foodItem.bowelMovement == 'No'?'selected':''}>No</option>
							                    	<option value="Yes" ${foodItem.bowelMovement != 'No'?'selected':''}>Yes</option>
							                    </select>
							                </td>
											<td class="content-panel-label">Motion</td> 
											<td class="content-panel-data">
												<select name="motionName" class="field-option" ${foodItem.bowelMovement != 'No'?'':'disabled'}>
							                        <option value="NA" ${foodItem.bowelMovement == 'NA'?'selected':''}>-- Select --</option>
							                        <option value="Constipated" ${foodItem.bowelMovement == 'Constipated'?'selected':''}>Constipated</option>
							                        <option value="Normal" ${foodItem.bowelMovement == 'Normal'?'selected':''}>Normal</option>
								                    <option value="Loose" ${foodItem.bowelMovement == 'Loose'?'selected':''}>Loose</option>
								                    <option value="Diarrhea" ${foodItem.bowelMovement == 'Diarrhea'?'selected':''}>Diarrhea</option>
							                    </select>
							                </td>
										</tr>
									</table>
									</div>
									</td>
									</tr>
								</table>
							</fieldset>				
						</logic:iterate>
						</td>
					</tr>
				</logic:present>
		</table>
		</td>
	</tr>
	</table>
		<table border="0" width="100%">
            <logic:greaterThan name="PageCount" value="1" scope="request">
			<tr>
				<td>
					<table align="right"  border="0" cellpadding="0" cellspacing="2" class="pager-panel">
						<tr>
							<td>
								<!-- Paginatin code starts Here -->			
								<pg:pager items="<%= pageCount %>" index="center" maxPageItems="1" maxIndexPages="6"  export="offset,currentPageNumber=pageNumber" scope="request">
								<!-- items will have max rowcount -->
								
								  <input type="hidden" name="pager.offset" value="<%= offset %>" />
						
								  
								  <pg:param name="pg"/>
								  <pg:param name="q"/>
							 
							 	  <pg:index>
							 	  	<font class="pager-item">
								    <pg:prev><a href="#" onClick="doSubmit(<%= pageNumber %>)">&lt;&lt;</a></pg:prev></font>
								    <pg:pages>
								    <%
							      
								      if (pageNumber == currentPageNumber) { 
								        %><font class="pager-item-current"><%= pageNumber %></font><%
								      } else { 
								        %><font class="pager-item"><a href="#" onClick="doSubmit(<%= pageNumber %>)"><%= pageNumber %></a></font><%
								      }
								    %>
								    </pg:pages>
								    <font class="pager-item">
								    <pg:next><a href="#" onClick="doSubmit(<%= pageNumber %>)">&gt;&gt;</a></pg:next></font>
								    <br></font>
								  </pg:index>
								</pg:pager>
								<!-- Paginatin code Ends Here -->
							</td>
						</tr>
					</table>
				</td>
			</tr>
			</logic:greaterThan>
			<tr>
				<td height="5"></td>
			</tr>
			<logic:lessThan name="itemNum" value="6">
			<tr>
				<td align="center">
					<input type="submit" value="Make new Entry" style="font-weight: bolder; width: 130"/>
					<!--input type="button" name="AddNew" value="Add Meal Temp" onClick="addMealTmp();"/-->
				</td>
			</tr>
			</logic:lessThan>
		</table>

</td>
</tr>
</table>
</form>

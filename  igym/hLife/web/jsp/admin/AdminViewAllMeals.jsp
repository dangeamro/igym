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
	
	String fileName="";
	if (request.getAttribute("fileName") != null){
		fileName=(String)request.getAttribute("fileName");
	}
	String graphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" + fileName;
	
	
%>


<head>
	<script language="javascript">

		function  doSubmit(offSet){
			document.viewMeal.method.value="getMeals";
			document.viewMeal.action="getAllMeals.do?pager.offset="+(offSet-1);
			document.viewMeal.pageNum.value=(offSet);
			document.viewMeal.submit();
		}
		
	</script>
</head>
<form name="viewMeal" action="<html:rewrite page="/admin/getAssignMeals.do" />" method="post">
<input type="hidden" name="method" value="getAssignMeals" >
<input type="hidden" name="pageNum">
<input type="hidden" name="targetUserId" value="${TARGET_USER.userId}">
<table width="80%" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td>
			<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td  width="*" colspan="4" class="customer-name-panel">
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
	    <td height="10"/>
	</tr>
	<tr>
		<td align="center">
			<font class="error-header" nowrap>
				<html:errors/>
			</font>
		</td>
	</tr>
	<tr>
		<td height="10"/>
	</tr>
	<tr>
		<td align="center" width="50%">
			<input type="submit" value="Change meal assigned to ${TARGET_USER.displayName}" style="width: 260;"/>
		</td>	
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
			<table width="100%" align="center" cellpadding="0" cellspacing="0" border="0" class="content-panel-display-area" style="margin-top: 5px; margin-bottom: 5px;">
				<logic:notPresent name="FoodList" scope="request">
				<tr>
					<td align="center" class="content-panel-h1">
						Food Journal
					</td>
				</tr>
				<tr>
					<td height="60" class="search-result-text-data" style="text-align: center;font-family: Arial; font-size: 13;">
						The customer has not made any Food Journal entries.
					</td>
				</tr>
				</logic:notPresent>
				<logic:present name="FoodList" scope="request">
				<tr>
					<td class="content-panel-h2">
						Food Journal for : ${requestScope.MEAL_DATE }
					</td>
				</tr>
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
												<td class="content-panel-label">Fats</td>
												<td class="content-panel-label" style="text-align: center">${MealSummary.totalFats}</td>
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
												<td class="content-panel-label">Protiens</td>
												<td class="content-panel-label" style="text-align: center">${MealSummary.totalProtien}</td>
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
												<td class="content-panel-label">Carbohydrates</td>
												<td class="content-panel-label" style="text-align: center">${MealSummary.totalCarbohydrates }</td>
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
								Meal #${itemNum+1} / Time ${foodItem.time}&nbsp;
							</legend>
							<table width="100%" align="center" cellpadding="0" cellspacing="0">
							<logic:equal name="foodItem"  property="mealType" value="1">
								<bean:define id="supplementFood" name="foodItem" property="nonFoodMeal" />
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
										<b> Fats</b>
									</td>
									<td class="content-panel-data" style="padding: 2px; border-right: 1px solid; width: 5%;">
										<b>Proteins</b>
									</td>
									<td class="content-panel-data" style="padding: 2px; border-right: 1px solid; width: 5%;">
										<b>Carb</b>
									</td>
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
									<td colspan="6" >
										<table width="100%">
											<tr>
												<td class="content-panel-label">Energy Level</td>
												<td class="content-panel-data">${foodItem.energyLevel}</td>
												<td class="content-panel-label">Appetite</td>
												<td class="content-panel-data">${foodItem.appetite}</td>
												<td class="content-panel-label">Mood</td>
												<td class="content-panel-data">${foodItem.mood}</td>			
											</tr>
											<tr>
												<td class="content-panel-label">Digestive State</td>  
												<td class="content-panel-data">${foodItem.digestiveState}</td>
												<td class="content-panel-label">Bowel Movement</td>
												<td class="content-panel-data">${foodItem.bowelMovement}</td>
												<td class="content-panel-label">Constipated</td>
												<td class="content-panel-data">${foodItem.bowelMovement}</td>
											</tr>
										</table>
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
	<td align="center" width="50%">
		<input type="submit" value="Change meal assigned to ${TARGET_USER.displayName}" style="width: 260;"/>
	</td>	
</tr>
</table>
</form>

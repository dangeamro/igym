<%@ include file="/jsp/common/Taglibs.jsp"%>
<script src='<html:rewrite page="/resources/js/scw.js"/>'></script>
<script src='<html:rewrite page="/resources/js/common-scripts.js"/>'></script>
<script>
	function doCancel()
	{
	  	if(confirm('Quit without saving this record?'))
	  	{
		  	document.goalForm.action = '<html:rewrite page="/admin/viewGoal.do" />';
	  		document.getElementsByName('method')[0].value = 'getGoal';
	  		document.goalForm.submit();
	  	}
  	}
  	
  	function changeUnits(obj)
  	{
  		var labels = document.getElementsByTagName('label');
  		
  		var units = new Array(4);//
  		units[0] = 'lbs';
  		units[1] = 'lbs';
  		units[2] = 'lbs';
  		units[3] = 'cms';
  		
  		var unit = '';

  		if(obj.selectedIndex != 0)
  			unit = units[obj.selectedIndex - 1];
  		
  		for(i=0; i< labels.length; i++)
  			labels[i].innerText = unit;
  			
  		document.getElementsByName('readingUnit')[0].value = unit;
  	}
</script>
<html:form action="/admin/addGoal.do" method="post">
<input type="hidden" name="method" value="addGoal">
<input type="hidden" name="targetUserId" value="${TARGET_USER.userId}">
<html:hidden property="userId" value="${TARGET_USER.userId}"/>
<html:hidden property="readingUnit" value=""/>
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
	    <td height="5"></td>
	</tr>
	<tr>
	    <td align="center">
	      <font class="error" style="font-size: 13;">
	        	<html:errors/>
	      </font>
	    </td>
	</tr>
	<tr>
		<td>
			<table align="center" border="0" cellpadding="2" cellspacing="0" width="100%" class="content-panel-display-area">
				<tr>
					<td class="content-panel-h2" colspan="4">Add New Goal
					</td>
				</tr>	
				<tr>
					<td colspan="4" height="20"></td>
				</tr>
				<tr>
					<td class="content-panel-label" nowrap>
						Goal
						<font class="star"> * </font>
					</td>
					<td colspan="3" class="content-panel-data" nowrap>
						<html:select property="goalName" styleClass="field-option" onchange="changeUnits(this);">
							<html:option value="">-- Select --</html:option>
							<html:option value="Weight Loss">Weight Loss</html:option>
							<html:option value="Weight Gain">Weight Gain</html:option>
							<html:option value="Fat Reduction">Fat Reduction</html:option>
							<html:option value="Waist Reduction">Waist Reduction</html:option>
						</html:select>
						<font class="error">
							<html:errors  property="goalName" />
						</font>
					</td>
				</tr>
				<tr>
					<td class="content-panel-label" nowrap>
						Start Date
						<font class="star"> * </font>
					</td>
					<td class="content-panel-data" nowrap>
						<html:text property="startDate" styleClass="field-text-box" size="10" readonly="true"/>
						<img style="cursor:hand;" src='<html:rewrite page="/resources/images/calendar.gif"/>' onclick="javascript:scwShow(scwID('startDate'),scwID('startDate'))"/>
						<font class="error">
							<html:errors  property="startDate" />
						</font>
					</td>
					<td class="content-panel-label" nowrap>
						Initial Reading
						<font class="star"> * </font>
					</td>
					<td class="content-panel-data" nowrap="nowrap">
						<html:text property="startReading" styleClass="field-text-box" maxlength="6" onkeypress="return checkDecimal(this);"/>&nbsp;<label class="content-panel-sub-label"></label>
						<font class="error">
							<html:errors  property="startReading" />
						</font>
					</td>
				</tr>
				<tr>
					<td class="content-panel-label" nowrap>
						End Date
						<font class="star"> * </font>
					</td>
					<td class="content-panel-data" nowrap>
						<html:text property="endDate" styleClass="field-text-box" size="10" readonly="true"/>
						<img style="cursor:hand;" src='<html:rewrite page="/resources/images/calendar.gif"/>' onclick="javascript:scwShow(scwID('endDate'),scwID('endDate'))"/>
						<font class="error">
							<html:errors  property="endDate" />
						</font>
					</td>
					<td class="content-panel-label" nowrap>
						Desired Reading
						<font class="star"> * </font>
					</td>
					<td class="content-panel-data" width="30%" nowrap>
						<html:text property="endReading" styleClass="field-text-box" maxlength="6" onkeypress="return checkDecimal(this);"/>&nbsp;<label class="content-panel-sub-label"></label>
						<font class="error">
							<html:errors  property="endReading" />
						</font>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="15"/>
				</tr>
				<tr>
					<td colspan="4">
						<table width="90%" border="0" cellspacing="0"
							cellpadding="0" align="center">
							<tr>
								<td align="center" class="sub-heading">Calorie Budget</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="5"/>
				</tr>
				<tr>
					<td class="content-panel-label" nowrap>
						Calories burnt/day
						<font class="star"> * </font>
					</td>
					<td class="content-panel-data" nowrap>
						<html:text property="caloriesBurntPerDay" styleClass="field-text-box" maxlength="6" onkeypress="return checkDecimal(this);"/>
						<font class="error">
							<html:errors  property="caloriesBurntPerDay" />
						</font>
					</td>
					<td class="content-panel-label" nowrap>
						Calories restriction/day
						<font class="star"> * </font>
					</td>
					<td class="content-panel-data" width="30%" nowrap>
						<html:text property="calorieRestrictionPerDay" styleClass="field-text-box" maxlength="6" onkeypress="return checkDecimal(this);"/>
						<font class="error">
							<html:errors  property="calorieRestrictionPerDay" />
						</font>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="15"/>
				</tr>
				<tr>
					<td colspan="4">
						<table width="90%" border="0" cellspacing="0"
							cellpadding="0" align="center">
							<tr>
								<td align="center" class="sub-heading">Macronutrient Budget</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="5"/>
				</tr>
				<tr>
					<td class="content-panel-label" nowrap>
						Protien
					</td>
					<td class="content-panel-data" nowrap>
						<html:text size="2" maxlength="5" styleClass="field-text-box"  property="protienPercentage" onkeypress="return checkDecimal(this);"/>&nbsp;<font class="content-panel-sub-label">%</font>
					</td>
					<td class="content-panel-label" nowrap>
						carbohydrates
					</td>
					<td class="content-panel-data" nowrap>
						<html:text size="2" maxlength="5" styleClass="field-text-box"  property="carbohydratesPercentage" onkeypress="return checkDecimal(this);"/>&nbsp;<font class="content-panel-sub-label">%</font>
					</td>
				</tr>
				<tr>
					<td class="content-panel-label" nowrap>
						Fats
					</td>
					<td class="content-panel-data" nowrap>
						<html:text size="2" maxlength="5" styleClass="field-text-box"  property="fatPercentage" onkeypress="return checkDecimal(this);"/>&nbsp;<font class="content-panel-sub-label">%</font>
					</td>
					<td class="content-panel-label" nowrap>
						Other nutrients
					</td>
					<td class="content-panel-data" nowrap>
						<html:text size="2" maxlength="5" styleClass="field-text-box"  property="otherNutrientPercentage" onkeypress="return checkDecimal(this);"/>&nbsp;<font class="content-panel-sub-label">%</font>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="10"/>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td height="10"></td>
	</tr>
	<tr>
		<td align="center">
			<input type="submit" value="Add"/>
			<input type="button" value="Cancel" name="cancel" onclick="doCancel()" />
		</td>
	</tr>
</table>
</html:form>

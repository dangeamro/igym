<%@ include file="/jsp/common/Taglibs.jsp" %>
<table align="center" width="80%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="*">
			<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td colspan="4" class="customer-name-panel">
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
			<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%" class="content-panel-display-area">
				<tr>
					<td colspan="4" class="content-panel-h2">
						Report Card
					</td>
				</tr>
				<tr>
					<td>
						<table align="center" border="0" cellpadding="2" cellspacing="2" width="100%">
							<tr>	
								<td colspan="6" height="10"/>
							</tr>
							<tr>
								<td class="content-panel-label">
									Name
								</td>
								<td class="content-panel-data">
									${TARGET_USER.displayName}
								</td>
								<td class="content-panel-label">
									Age
								</td>
								<td class="content-panel-data">
									${TARGET_USER.age}
								</td>		
								<td class="content-panel-label">
									Gender
								</td>
								<td class="content-panel-data">
									${TARGET_USER.gender}
								</td>
							</tr>
							<tr>
								<td class="content-panel-label">
									Height
								</td>
								<td class="content-panel-data">
									${reportCard.initialMeasurement.height } cms
								</td>		
								<td class="content-panel-label">
									Start date
								</td>
								<td class="content-panel-data">
									${TARGET_USER.dateOfStart}
								</td>
								<td class="content-panel-label">
									Duration
								</td>
								<td class="content-panel-data">
									<font class="content-panel-sub-label"><b>${TARGET_USER.durationInWeeks}</b> weeks</font>
								</td>
							</tr>
							<tr>
								<td colspan="6" height="10"/>
							</tr>
							<tr>
								<td/>
								<td colspan="4" class="content-panel-data">
									${TARGET_USER.consumedDurationInWeeksAsString} 
								</td>
								<td align="left">
									<a href="<html:rewrite page='/admin/getUpdateCustomer.do?method=loadUserDetails&id=${TARGET_USER.userId}' />" class="link"><u>edit profile</u></a>
								</td>
							</tr>
							<tr>
								<td colspan="6">
									<table align="center" width="88%" cellpadding="0" cellspacing="0" class="form-border">
										<tr>
											<td height="10px" width="${TARGET_USER.consumedDurationInPercentage == 0?'*':TARGET_USER.consumedDurationInPercentage }%" style="background-color: #93CA3D;"><font style="font-size: 2px">&nbsp;</font></td>
											<td width="${TARGET_USER.consumedDurationInPercentage == 100?'*': 100 - TARGET_USER.consumedDurationInPercentage}%" style="background-color: #FFFFAA"><font style="font-size: 1px">&nbsp;</font></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td colspan="6" height="5"/>
							</tr>
							<tr>
								<td colspan="6" height="5"/>
							</tr>
							<tr>
								<td colspan="6">
									<table width="95%" border="0" cellspacing="0"
										cellpadding="0" align="center">
										<tr>
											<td align="center" class="sub-heading">Measurements Summary</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td colspan="6" height="10"/>
							</tr>
							<tr>
								<td colspan="6">
									<table align="center" width="80%" cellpadding="0" cellspacing="0" style="border: 1px  outset;">
										<tr  style="background-color: #BFBFBF;">
											<td/><td/>
											<td class="content-panel-label" style="text-align: center;">
												<label class="blue">First Assessment</label>
											</td>
											<td class="content-panel-label" style="text-align: center;">
												<label class="blue-green">Last Assessment</label>
											</td>
											<td class="content-panel-label" style="text-align: center;">
												<label class="green">Current Assessment</label>
											</td>
											<td colspan="2" class="content-panel-label" nowrap style="text-align: center;">
												<label class="red">Total Change <br><font class="content-panel-sub-label">(current - initial)</font></label>
											</td>
										</tr>
										<tr>
											<td colspan="7" height="1" style="border-bottom: dashed;"></td>
										</tr>
										<tr>
											<td/><td/>
											<td class="content-panel-label" nowrap style="text-align: center;">
												<label class="blue">${reportCard ==null? 'NA': reportCard.initialMeasurement.strDateTime}</label>
											</td>
											<td class="content-panel-label" nowrap style="text-align: center;">
												<label class="blue-green">${reportCard ==null? 'NA': reportCard.previousMeasurement.strDateTime}</label>
											</td>

											<td class="content-panel-label" nowrap style="text-align: center;">
												<label class="green">${reportCard ==null? 'NA': reportCard.latestMeasurement.strDateTime}</label>
											</td>
											<td colspan="2" class="content-panel-label" nowrap style="text-align: center;">
												<label class="red">${reportCard ==null? 'NA': reportCard.weeksDifference + weeks }<font class="content-panel-sub-label">Week(s) approx</font></label>
											</td>
										</tr>
										<tr>
											<td colspan="7" height="1" style="background-color: #BFBFBF;"></td>
										</tr>
										<tr>
											<td class="content-panel-label" nowrap>
												Weight
											</td>
											<td class="content-panel-sub-label">(lbs)</td>
											<td class="content-panel-data" nowrap style="text-align: center;">
												<label class="blue">${reportCard ==null? 'NA': reportCard.initialMeasurement.weight}</label>
											</td>
											<td class="content-panel-data" nowrap style="text-align: center;">
												<label class="blue-green">${reportCard ==null? 'NA': reportCard.previousMeasurement.weight}</label>
											</td>
											<td class="content-panel-data" nowrap style="text-align: center;">
												<label class="green">${reportCard == null? 'NA': reportCard.latestMeasurement.weight}</label>
											</td>
											<td class="content-panel-data" nowrap style="text-align: right; padding-right: 5px;">
												<label class="red">${reportCard == null? 'NA': reportCard.weightChange}</label>
											</td>
											<td>
												&nbsp;
												<logic:present name="reportCard">
													<logic:lessThan name="reportCard" property="weightChange" value="0">
														<img width="12px" src='<html:rewrite page="/resources/images/down.gif"/>' align="bottom" />
													</logic:lessThan>
													<logic:greaterThan name="reportCard" property="weightChange" value="0">
														<img width="12px" src='<html:rewrite page="/resources/images/up.gif"/>' align="bottom" />
													</logic:greaterThan>
												</logic:present>
											</td>
										</tr>
										<tr>
											<td colspan="7" height="1" style="background-color: #B2B4BF;"></td>
										</tr>
										<tr>
											<td class="content-panel-label" nowrap>
												Lean Body Mass
											</td>
											<td class="content-panel-sub-label">(lbs)</td>
											<td class="content-panel-data" nowrap style="text-align: center;">
												<label class="blue">${reportCard == null? 'NA':reportCard.initialMeasurement.leanBodyMass}</label>
											</td>
											<td class="content-panel-data" nowrap style="text-align: center;">
												<label class="blue-green">${reportCard == null? 'NA':reportCard.previousMeasurement.leanBodyMass}</label>
											</td>
											<td class="content-panel-data" nowrap style="text-align: center;">
												<label class="green">${reportCard == null? 'NA': reportCard.latestMeasurement.leanBodyMass}</label>
											</td>
											<td class="content-panel-data" nowrap style="text-align: right; padding-right: 5px;">
												<label class="red">${reportCard == null? 'NA': reportCard.leanBodyMassChange}</label>
											</td>
											<td>
												&nbsp;
												<logic:present name="reportCard">
													<logic:lessThan name="reportCard" property="leanBodyMassChange" value="0">
														<img width="12px" src='<html:rewrite page="/resources/images/down.gif"/>' align="bottom" />
													</logic:lessThan>
													<logic:greaterThan name="reportCard" property="leanBodyMassChange" value="0">
														<img width="12px" src='<html:rewrite page="/resources/images/up.gif"/>' align="bottom" />
													</logic:greaterThan>
												</logic:present>
											</td>
										</tr>
										<tr>
											<td colspan="7" height="1" style="background-color: #B2B4BF;"></td>
										</tr>
										<tr>
											<td class="content-panel-label" nowrap>
												Dry Lean Mass
											</td>
											<td class="content-panel-sub-label">(lbs)</td>
											<td class="content-panel-data" nowrap style="text-align: center;">
												<label class="blue">${reportCard == null? 'NA': reportCard.initialMeasurement.dryLeanMass}</label>
											</td>
											<td class="content-panel-data" nowrap style="text-align: center;">
												<label class="blue-green">${reportCard == null? 'NA': reportCard.previousMeasurement.dryLeanMass}</label>
											</td>
											<td class="content-panel-data" nowrap style="text-align: center;">
												<label class="green">${reportCard == null? 'NA': reportCard.latestMeasurement.dryLeanMass}</label>
											</td>
											<td class="content-panel-data" nowrap style="text-align: right; padding-right: 5px;">
												<label class="red">${reportCard == null? 'NA': reportCard.dryLeanMassChange}</label>
											</td>
											<td>
												&nbsp;
												<logic:present name="reportCard">
													<logic:lessThan name="reportCard" property="dryLeanMassChange" value="0">
														<img width="12px" src='<html:rewrite page="/resources/images/down.gif"/>' align="bottom" />
													</logic:lessThan>
													<logic:greaterThan name="reportCard" property="dryLeanMassChange" value="0">
														<img width="12px" src='<html:rewrite page="/resources/images/up.gif"/>' align="bottom" />
													</logic:greaterThan>
												</logic:present>
											</td>
										</tr>
										<tr>
											<td colspan="7" height="1" style="background-color: #B2B4BF;"></td>
										</tr>
										<tr>
											<td class="content-panel-label" nowrap>
												Total Body Water
											</td>
											<td class="content-panel-sub-label">(lbs)</td>
											<td class="content-panel-data" nowrap style="text-align: center;">
												<label class="blue">${reportCard == null? 'NA': reportCard.initialMeasurement.tbw}</label>
											</td>
											<td class="content-panel-data" nowrap style="text-align: center;">
												<label class="blue-green">${reportCard == null? 'NA': reportCard.previousMeasurement.tbw}</label>
											</td>
											<td class="content-panel-data" nowrap style="text-align: center;">
												<label class="green">${reportCard == null? 'NA': reportCard.latestMeasurement.tbw}</label>
											</td>
											<td class="content-panel-data" nowrap style="text-align: right; padding-right: 5px;">
												<label class="red">${reportCard == null? 'NA': reportCard.tbwChange}</label>
											</td>
											<td>
												&nbsp;
												<logic:present name="reportCard">
													<logic:lessThan name="reportCard" property="tbwChange" value="0">
														<img width="12px" src='<html:rewrite page="/resources/images/down.gif"/>' align="bottom" />
													</logic:lessThan>
													<logic:greaterThan name="reportCard" property="tbwChange" value="0">
														<img width="12px" src='<html:rewrite page="/resources/images/up.gif"/>' align="bottom" />
													</logic:greaterThan>
												</logic:present>
											</td>
										</tr>
										<tr>
											<td colspan="7" height="1" style="background-color: #B2B4BF;"></td>
										</tr>
										<tr>
											<td class="content-panel-label" nowrap>
												Body Fat Mass
											</td>
											<td class="content-panel-sub-label">(lbs)</td>
											<td class="content-panel-data" nowrap style="text-align: center;">
												<label class="blue">${reportCard == null? 'NA': reportCard.initialMeasurement.fat}</label>
											</td>
											<td class="content-panel-data" nowrap style="text-align: center;">
												<label class="blue-green">${reportCard == null? 'NA': reportCard.previousMeasurement.fat}</label>
											</td>
											<td class="content-panel-data" nowrap style="text-align: center;">
												<label class="green">${reportCard == null? 'NA': reportCard.latestMeasurement.fat}</label>
											</td>
											<td class="content-panel-data" nowrap style="text-align: right; padding-right: 5px;">
												<label class="red">${reportCard == null? 'NA': reportCard.bodyFatMassChange}</label>
											</td>
											<td>
												&nbsp;
												<logic:present name="reportCard">
													<logic:lessThan name="reportCard" property="bodyFatMassChange" value="0">
														<img width="12px" src='<html:rewrite page="/resources/images/down.gif"/>' align="bottom" />
													</logic:lessThan>
													<logic:greaterThan name="reportCard" property="bodyFatMassChange" value="0">
														<img width="12px" src='<html:rewrite page="/resources/images/up.gif"/>' align="bottom" />
													</logic:greaterThan>
												</logic:present>
											</td>
										</tr>
										<tr>
											<td colspan="7" height="1" style="background-color: #B2B4BF;"></td>
										</tr>
										<tr>
											<td class="content-panel-label" nowrap>
												Body Fat
											</td>
											<td class="content-panel-sub-label">(%)</td>
											<td class="content-panel-data" nowrap style="text-align: center;">
												<label class="blue">${reportCard == null? 'NA': reportCard.initialMeasurement.pbf}</label>
											</td>
											<td class="content-panel-data" nowrap style="text-align: center;">
												<label class="blue-green">${reportCard == null? 'NA': reportCard.previousMeasurement.pbf}</label>
											</td>
											<td class="content-panel-data" nowrap style="text-align: center;">
												<label class="green">${reportCard == null? 'NA': reportCard.latestMeasurement.pbf}</label>
											</td>
											<td class="content-panel-data" nowrap style="text-align: right; padding-right: 5px;">
												<label class="red">${reportCard == null? 'NA': reportCard.pbfChange}</label>
											</td>
											<td>
												&nbsp;
												<logic:present name="reportCard">
													<logic:lessThan name="reportCard" property="pbfChange" value="0">
														<img width="12px" src='<html:rewrite page="/resources/images/down.gif"/>' align="bottom" />
													</logic:lessThan>
													<logic:greaterThan name="reportCard" property="pbfChange" value="0">
														<img width="12px" src='<html:rewrite page="/resources/images/up.gif"/>' align="bottom" />
													</logic:greaterThan>
												</logic:present>
											</td>
										</tr>
										<tr>
											<td colspan="7" height="1" style="background-color: #B2B4BF;"></td>
										</tr>
										<tr>
											<td class="content-panel-label" nowrap>
												Body Mass Index
											</td>
											<td class="content-panel-sub-label">(lbs)</td>
											<td class="content-panel-data" nowrap style="text-align: center;">
												<label class="blue">${reportCard == null? 'NA': reportCard.initialMeasurement.bmi}</label>
											</td>
											<td class="content-panel-data" nowrap style="text-align: center;">
												<label class="blue-green">${reportCard == null? 'NA': reportCard.previousMeasurement.bmi}</label>
											</td>
											<td class="content-panel-data" nowrap style="text-align: center;">
												<label class="green">${reportCard == null? 'NA': reportCard.latestMeasurement.bmi}</label>
											</td>
											<td class="content-panel-data" nowrap style="text-align: right; padding-right: 5px;">
												<label class="red">${reportCard == null? 'NA': reportCard.bmiChange}</label>
											</td>
											<td>
												&nbsp;
												<logic:present name="reportCard">
													<logic:lessThan name="reportCard" property="bmiChange" value="0">
														<img width="12px" src='<html:rewrite page="/resources/images/down.gif"/>' align="bottom" />
													</logic:lessThan>
													<logic:greaterThan name="reportCard" property="bmiChange" value="0">
														<img width="12px" src='<html:rewrite page="/resources/images/up.gif"/>' align="bottom" />
													</logic:greaterThan>
												</logic:present>
											</td>
										</tr>
										<tr>
											<td colspan="7" height="1" style="background-color: #B2B4BF;"></td>
										</tr>
										<tr>
											<td class="content-panel-label" nowrap>
												Metabolic Rate
											</td>
											<td class="content-panel-sub-label">(cals)</td>
											<td class="content-panel-data" nowrap style="text-align: center;">
												<label class="blue">${reportCard == null? 'NA': reportCard.initialMeasurement.basalMetabolicRate}</label>
											</td>
											<td class="content-panel-data" nowrap style="text-align: center;">
												<label class="blue-green">${reportCard == null? 'NA': reportCard.previousMeasurement.basalMetabolicRate}</label>
											</td>
											<td class="content-panel-data" nowrap style="text-align: center;">
												<label class="green">${reportCard == null? 'NA': reportCard.latestMeasurement.basalMetabolicRate}</label>
											</td>
											<td class="content-panel-data" nowrap style="text-align: right; padding-right: 5px;">
												<label class="red">${reportCard == null? 'NA': reportCard.basalMetabolicRateChange}</label>
											</td>
											<td>
												&nbsp;
												<logic:present name="reportCard">
													<logic:lessThan name="reportCard" property="basalMetabolicRateChange" value="0">
														<img width="12px" src='<html:rewrite page="/resources/images/down.gif"/>' align="bottom" />
													</logic:lessThan>
													<logic:greaterThan name="reportCard" property="basalMetabolicRateChange" value="0">
														<img width="12px" src='<html:rewrite page="/resources/images/up.gif"/>' align="bottom" />
													</logic:greaterThan>
												</logic:present>
											</td>
										</tr>
										<tr>
											<td colspan="7" height="1" style="background-color: #B2B4BF;"></td>
										</tr>

									</table>
								</td>
							</tr>
							<tr>
								<td colspan="6" height="10"/>
							</tr>
							<tr>	
								<td height="10">
								</td>
							</tr>
							<tr>
								<td colspan="6">
									<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
										<tr>
											<td align="center" class="sub-heading">Other Details</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td height="10">
								</td>
							</tr>
							<tr>	
								<td height="10">
								</td>
							</tr>
						</table>
					<td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/common/Taglibs.jsp"%>

<html>
    <head>
        <title>Change Default Values For Monthly Supplement Package</title>
       	<script src='<html:rewrite page="/resources/js/common-scripts.js"/>'></script>
       	
		<SCRIPT LANGUAGE="JavaScript1.2" >
	 
	 
		</SCRIPT>
	</head>
 
 
 
<body>
<form name="changeDefault" action='<html:rewrite page="/admin/changeDefaultSupplements.do" />' method="post">
<input type="hidden" name="method" value="changeDefaults">
<table align="center" width="80%" border="0" cellpadding="0" cellspacing="0">
	<tr>
	    <td align="center">
	      <font class="error" style="font-size: 13;">
	        	<html:errors/>
	      </font>
	    </td>
	</tr>
	<tr>
		<td>
			<table width="100%" align="right"  border="0" cellpadding="0" cellspacing="2" class="content-panel-display-area">
				<tr>
					<td colspan="4" align="left" class="content-panel-h2" style="text-align: left;">
						Change Default Values For Monthly Supplement Package
					</td>
				</tr>
				<tr>
					<td height="15"></td>
				</tr>
				<tr>
					<td class="content-panel-label"> Nutrition Suppor Complex</td>
					<td>
						<input type="text" class="field-text-box" name="n_s_Complex" size="10" value='${monthlyDefaultValues.n_s_Complex}' onkeypress="return checkNumber();">
						<font class="content-panel-sub-label">Packs</font>
					</td>

					<td class="content-panel-label"> Glutamine</td>
					<td>
						<input type="text" class="field-text-box" name="glutamine" size="10" value='${monthlyDefaultValues.glutamine}' onkeypress="return checkNumber();">
						<font class="content-panel-sub-label">Packs</font>
					</td>
				</tr>
				<tr>
					<td class="content-panel-label"> Metabolic Support Oil</td>
					<td>
						<input type="text" class="field-text-box" name="m_s_oil" size="10" value='${monthlyDefaultValues.m_s_oil}' onkeypress="return checkNumber();">
						<font class="content-panel-sub-label">Bottles</font>
					</td>

					<td class="content-panel-label"> Oxygen Optimizer</td>
					<td>
						<input type="text" class="field-text-box" name="oxyOptimizer" size="10" value='${monthlyDefaultValues.oxyOptimizer}' onkeypress="return checkNumber();">
						<font class="content-panel-sub-label">Bottles</font>
					</td>
				</tr>
				<tr>
					<td class="content-panel-label"> Metabolic Suppor Complex</td>
					<td>
						<input type="text" class="field-text-box" name="m_s_complex" size="10" value='${monthlyDefaultValues.m_s_complex}' onkeypress="return checkNumber();">
						<font class="content-panel-sub-label">Bottles</font>
					</td>

					<td class="content-panel-label"> Men/Women Health Support</td>
					<td>
						<input type="text" class="field-text-box" name="healthSupport" size="10" value='${monthlyDefaultValues.healthSupport}' onkeypress="return checkNumber();">
						<font class="content-panel-sub-label">Bottles</font>
					</td>
				</tr>
				<tr>
					<td class="content-panel-label"> EFA Support Complex</td>
					<td>
						<input type="text" class="field-text-box" name="efaSupport" size="10" value='${monthlyDefaultValues.efaSupport}' onkeypress="return checkNumber();">
						<font class="content-panel-sub-label">Bottles</font>
					</td>
				</tr>
				<tr>
					<td height="20"></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td height="15"></td>
	</tr>
	<tr>
		<td align="center">
			<html:submit>Save</html:submit>
			<html:reset>Reset</html:reset>
		</td>
	</tr>
</table>
</form>
</body>
</html>
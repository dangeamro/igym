<%@ include file="/jsp/common/Taglibs.jsp" %>
<html>
<head>
<link rel="STYLESHEET" type="text/css" href='<html:rewrite page="/resources/css/dhtmlXGrid.css"/>'>
<link rel="STYLESHEET" type="text/css" href='<html:rewrite page="/resources/css/dhtmlXGrid_skins.css"/>'>
<script  src='<html:rewrite page="/resources/js/grid/dhtmlXCommon.js"/>'></script>
<script  src='<html:rewrite page="/resources/js/grid/dhtmlXGrid.js"/>'></script>		
<script  src='<html:rewrite page="/resources/js/grid/dhtmlXGridCell.js"/>'></script>

<meta name="KEYWORDS" content="dhtmlxgrid, dhtml grid, javascript grid, javascript, DHTML, grid, grid control, dynamical scrolling, xml, AJAX, API, cross-browser, checkbox, XHTML, compatible, gridview, navigation, script, javascript navigation, web-site, dynamic, javascript grid, dhtml grid, dynamic grid, item, row, cell, asp, .net, jsp, cold fusion, custom tag, loading, widget, checkbox, drag, drop, drag and drop, component, html, scand">
<meta name="DESCRIPTION" content="Cross-browser DHTML grid with XML support and powerful API. This DHTML JavaScript grid can load its content dynamically from server using AJAX technology.">
</head>
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

<script type="text/javascript1.2">
function test(){
	alert("amit test");
}
function doOnLoad(){
	var mygrid = new dhtmlXGridObject('gridbox');
	var i = 0;
	var delim = '\t';
	mygrid.setImagePath("../resources/images/");
	mygrid.setHeader("<input type='checkbox' name='selectAl'></input>,Template Code,Description");
	mygrid.setInitWidths("45,200,*")
	mygrid.setColAlign("center,left,left")
	mygrid.setColTypes("ro,ro,ro");
	mygrid.setColSorting("int,str,str");
	mygrid.setSkin("light");
	mygrid.init();
	mygrid.enableLightMouseNavigation(true);
	mygrid.enableAlterCss("even","uneven");
	mygrid.setDelimiter(delim);	
	mygrid.addRow(1,"<input type='checkbox' name='selectAll'></input>\t<a href='getAddTemplate.do?method=getAddFoodTemplate'>Weight Reducing</a>\t Brief Description Of This Template\t",1);
	mygrid.addRow(2,"<input type='checkbox' name='selectAll'></input>\t<a href='getAddTemplate.do?method=getAddFoodTemplate'>Weight Gaining</a>\t Brief Description Of This Template\t",1);
	mygrid.addRow(3,"<input type='checkbox' name='selectAll'></input>\t<a href='getAddTemplate.do?method=getAddFoodTemplate'>Daibetic</a>\t Brief Description Of This Template\t",1);
	mygrid.addRow(4,"<input type='checkbox' name='selectAll'></input>\t<a href='getAddTemplate.do?method=getAddFoodTemplate'>Atheletic</a>\t Brief Description Of This Template\t",1);
	mygrid.addRow(5,"<input type='checkbox' name='selectAll'></input>\t<a href='getAddTemplate.do?method=getAddFoodTemplate'>Fat Reducing</a>\t Brief Description Of This Template\t",1);
	
}
</script>

<body>

<form name="supplimentTracker" action="<html:rewrite page="/admin/getAddTemplate.do" />" method="post">
<input type="hidden" name="pageNum"  >
<input type="hidden" name="method" value="getAddFoodTemplate">
<table align="center" width="80%" border="0" cellpadding="0" cellspacing="0">
	
	<tr><td height="20"/>
	</tr>

	<tr>
		<td>
			
<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%" class="content-panel-display-area">
	<tr>
		<td colspan="4" class="content-panel-h2">
			Food Journal Template Management : <i></i>		
		</td>
		
	</tr>	
	<tr><td colspan>

<table align="center" border="0" cellpadding="3" cellspacing="0" width="100%">
	
	<tr><td>
	<table width="95%" align="center" border="0">
		
		
		<tr>
			<td align="center">
				<div id="gridbox" width="100%" height="300px" style="border-width: 1pt;border-color:black;border-style: solid;background-color:#517298;">
							</div>
			</td>			
		</tr>
		<tr>
			<td align="center"><input type="button" value="Add New" onClick="submit();"/> <input type="button" value="Delete Selected"/></td>
		</tr>
	
	</table>
		
		
	</td></tr>
	
	
</table>
</td>
</tr>

</table>


</table>
</form>
</body>
<script type="text/javascript1.2">
	doOnLoad();
</script>
</html>
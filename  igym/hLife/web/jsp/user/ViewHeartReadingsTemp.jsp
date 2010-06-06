<%@ include file="/jsp/common/Taglibs.jsp" %>
<head>
<link rel="STYLESHEET" type="text/css" href='<html:rewrite page="/resources/css/dhtmlXGrid.css"/>'>
<link rel="STYLESHEET" type="text/css" href='<html:rewrite page="/resources/css/dhtmlXGrid_skins.css"/>'>
<script  src='<html:rewrite page="/resources/js/dhtmlXCommon.js"/>'></script>
<script  src='<html:rewrite page="/resources/js/dhtmlXGrid.js"/>'></script>		
<script  src='<html:rewrite page="/resources/js/dhtmlXGridCell.js"/>'></script>

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



<script  LANGUAGE="JavaScript1.2">
function doOnLoad(){
	//alert("Test Inside Grid");
	
	var mygrid = new dhtmlXGridObject('gridbox');
	var i = 0;
	var delim = '\t';
	mygrid.setImagePath("../resources/images/");
	mygrid.setHeader("<input type='checkbox' name='selectAll' onClick='selectUnselectList(\"selectAll\");' ></input>,Tab 1,Tab 2,Tab 3,Tab 4");
	mygrid.setInitWidths("45,70,*,70,100")
	mygrid.setColAlign("center,left,left,center,left")
	mygrid.setColTypes("ro,ro,ro,ro,ro");
	mygrid.setColSorting("int,str,int,str,str");
	mygrid.setSkin("light");
	mygrid.enableBuffering(10);
	
	mygrid.init();
	mygrid.enableLightMouseNavigation(true);
	mygrid.enableAlterCss("even","uneven");
	mygrid.setDelimiter(delim);
	
	for(i=1;i<=100;i++){
	
	//alert(i);
	mygrid.addRow(i,"Testb\tc\t"+i+"\t",1);
	}
}

</script>


<body onLoad="javascript:doOnLoad();">

<form name="heartRateReadings" action="<html:rewrite page="/admin/getAddSupplimentTracker.do" />" method="post">
<input type="hidden" name="method" value="getAddSupplimentTracker">
<table align="center" width="80%" border="0" cellpadding="0" cellspacing="0">
	
	<tr><td height="30"/>
	</tr>

	<tr>
		<td>
			


	<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%" class="content-panel-display-area">
		<tr>
			<td colspan="4" class="content-panel-h2">
				Heart Rate Readings : <i></i>		
			</td>
			
		</tr>	
		<tr><td colspan>
	
	<table align="center" border="0" cellpadding="3" cellspacing="0" width="100%">
		
		<tr><td>
			<table align="center" border="0" cellpadding="3" cellspacing="0" width="100%">
				<tr><td height="15"></td></tr>
					<tr>
						<td>
							<div id="gridbox" width="100%" height="300px" style="border-width: 1pt;border-color:black;border-style: solid;background-color:#517298;">
							</div>
						</td>
					</tr>		
				<tr><td height="15"></td></tr>
			</table>
		</td></tr>
		
		
	</table>
	</td>
	</tr>
	
</table>


</table>
</form>
</body>

<%@ include file="/jsp/common/Taglibs.jsp" %>
<%@ page import="java.util.ArrayList"%>
<html>
<head>
	<title>DHTML Grid samples. dhtmlXGrid - Add/Delete Rows in Grid</title>
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
<%
	ArrayList ar=(ArrayList)request.getAttribute("DataList");
%>
<SCRIPT LANGUAGE="JavaScript1.2">
		
var SelectedGridIndex=0;

function doOnLoad(){
	loadBaseGrid();
	loadSelectedGrid();
}

function loadBaseGrid(){
		var i=0;
		var delim="\t";
		basegrid.setImagePath("../resources/images/");
		basegrid.setHeader("Select,Food,Size,Weight(grams),Protien(grams),Carbohydrates(grams),Fat(grams),Energy(grams)");
		basegrid.setInitWidths("60,200,100,100,100,100,100,100")
		basegrid.setColAlign("left,left,left,left,left,left,left,left");
		basegrid.setColTypes("ro,ro,ro,ro,ro,ro,ro,ro");
		basegrid.setColSorting("str,str,str,str,str,str,str,str");
		basegrid.setSkin("light");
		basegrid.init();
		basegrid.enableLightMouseNavigation(true);
		basegrid.enableAlterCss("even","uneven");
		basegrid.setDelimiter(delim);

	 	var i=0;
	 	var chkbx;
	 	var rownum;
	 	var code;
       //	for (i=0;i<10;++i){
	   //    	rownum="rownum"+i;
	   //    	chkbx = '<input type="checkbox" name="baseItemList" id="chkbox"  value="'+rownum+'"  ></input>';
	//	}
		<%	if(ar != null){
			int index=0;
			String data="";
			System.out.println("Size of Array in jsp "+ar.size());
				while(index< ar.size()){
					data=(String)ar.get(index);
					index++;
					System.out.println("\nAnd Data  "+data);
		%>
					code= "<%=data %>";
					//alert(code.substring(0,));
					//alert(code.substring(0,code.indexOf('\t')));
					chkbx = '<input type="checkbox" name="baseItemList" id="chkbox"  value="'+code.substring(0,code.indexOf('\t'))+'"  ></input>';
					basegrid.addRow(<%= index %>,""+chkbx+"\t"+"<%= data %>",1);
		<%
				}//while
			}//if
		%>



	}

function loadSelectedGrid(){
		var i=0;
		var delim="\t";
		selectedgrid.setImagePath("../resources/images/");
		selectedgrid.setHeader("Food Item,Delete");
		selectedgrid.setInitWidths("*,60")
		selectedgrid.setColAlign("left,left");
		selectedgrid.setColTypes("ro,ro");
		selectedgrid.setColSorting("str,str");
		selectedgrid.setSkin("light");
		selectedgrid.init();
		selectedgrid.enableLightMouseNavigation(true);
		selectedgrid.enableAlterCss("even","uneven");
		selectedgrid.setDelimiter(delim);
	}

function doMove(){
	var listId="";
	var i=0;
	var num=1;
	for (i = 0; i < document.getElementsByName("baseItemList").length; i++) {
		if(document.getElementsByName("baseItemList")[i].checked){
			listId = listId+" "+document.getElementsByName("baseItemList")[i].value;
			SelectedGridIndex++;
			selectedgrid.addRow(SelectedGridIndex,""+document.getElementsByName("baseItemList")[i].value+"\t<IMG onclick=\"selectedgrid.deleteRow("+SelectedGridIndex+")\" width=\"10\" height=\"10\" SRC=\"<html:rewrite page='/resources/images/delete.gif'/>\" />",1);
			num++;
			document.getElementsByName("baseItemList")[i].checked=false;
		}
	}
}


function doSubmit(){
		document.forms[0].submit();
	}

</SCRIPT>
<body>

<form name="supplimentTracker" action="<html:rewrite page="/admin/getAddSupplimentTracker.do" />" method="post">
	<input type="hidden" name="pageNum"  >
	<input type="hidden" name="method" value="getAddSupplimentTracker">
	<link rel="STYLESHEET" type="text/css" href='<html:rewrite page="/resources/css/dhtmlXGrid.css"/>'>
	<link rel="STYLESHEET" type="text/css" href='<html:rewrite page="/resources/css/dhtmlXGrid_skins.css"/>'>
	<script  src='<html:rewrite page="/resources/js/grid/dhtmlXCommon.js"/>'></script>
	<script  src='<html:rewrite page="/resources/js/grid/dhtmlXGrid.js"/>'></script>		
	<script  src='<html:rewrite page="/resources/js/grid/dhtmlXGridCell.js"/>'></script>

	<table align="center" width="80%" border="0" cellpadding="0" cellspacing="0">
		<tr><td height="20"/>
		</tr>
		<tr>
			<td>
			
		<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%" class="content-panel-display-area">
			<tr>
				<td colspan="4" class="content-panel-h2">
					Add Food Template : <i></i>		
				</td>
				
			</tr>	
			<tr><td colspan>
		
		<table align="center" border="0" cellpadding="3" cellspacing="0" width="100%">
		  <tr><td>
			<table width="95%" align="center" border="0">
				<tr><td height="10"></td></tr>
				<tr><td class="content-panel-label">Name</td>
					<td class="content-panel-text-box">
						<input type="text" property="name" class="field-text-box"/>
					</td>
					<td class="content-panel-label">Description</td>
					<td class="content-panel-text-box">
						<input type="text" property="description" maxlength="60" size="40" class="field-text-box"/>
					</td>
				</tr>
				
				<tr><td height="10"></td></tr>
				<tr>
					<td width="100%" colspan="4">
						<div id="baseGridbox" width="100%" height="300" style="border-width: 1pt;border-color:black;border-style: solid;background-color:#517298;"></div>
					</td>
								
				</tr>
				<tr>
				<td colspan="4" align="center">
					<input type="button" name="move" value="Move >>" onClick="doMove()"/>
				</td>
			</tr>
			
			</table>
			<table width="95%" align="center" border="0">			
			<tr>
				<td valign="top">
					<table width="100%" height="100" border="0" cellspacing="2">
							<tr>
								<td class="content-panel-label" style="text-align: left;">Pre Defined Template(s)</td>
							</tr>
							<tr>
								<td Align="center">
									<select name="itemlist" size="15" multiple style="width:250;  border: 1px solid black;">
										<option name=ALABAMA value="ALABAMA" style="background-color:#E0F3FE">Weight Reducing</option>     
										<option name=ALASKA    value="ALASKA">Weight Gaining</option>  
										<option name=AMERICAN SAMOA  value=AMERICAN SAMOA  style="background-color:#E0F3FE">Daibetic</option>     
										<option name=ARIZONA     value=ARIZONA  >Atheletic </option> 
										<option name=ARKANSAS      value=ARKANSAS style="background-color:#E0F3FE">Fat Reducing </option>     
									</select>
								</td>
								<td width="5"></td>
						  		<td>
							  		<div id="selectGridbox" width="100%" height="250px" style="border-width: 1pt;border-color:black;border-style: solid;background-color:#517298;"></div>
							  	</td>
							</tr>
							<tr>
								<td align="right"><input type="button" value="Use"/></td>
								<td width="5"></td>
						  		<td Align="center">
						  			<input type="button" value="Save"/>&nbsp;
						  			<input type="button" value="Cancel" onClick="history.back();"/>
						  		</td>
							</tr>
						</table>
				</td>
		  	</tr>
		  	<tr><td height="20"/></tr>
		  	
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
	basegrid = new dhtmlXGridObject('baseGridbox');
	selectedgrid = new dhtmlXGridObject('selectGridbox');
	doOnLoad();
</script>
</html>
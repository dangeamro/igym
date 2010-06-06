<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<%@ page contentType="text/html" %>
<%@ page import="com.otx.common.util.DOMHelper"%>
<%@ page import="com.otx.common.util.*" %>
<%@ page import="com.otx.framework.BusinessEvent"%>
<%@ page import="org.w3c.dom.*"%>
<%@ page import="org.w3c.dom.Document"%>
<%@ page contentType="text/html" %>
<%@ taglib uri="/WEB-INF/tlds/xtags.tld" prefix="xtags"%>
<%@ page import="com.otx.common.models.userprofile.helper.*"%>
<% 
ApplicationConfig appConfig = ApplicationConfig.getInstance();
String str_actionUrl = null;
if (session.getAttribute("CONTEXT_ROOT")!=null){
	str_actionUrl = (String) session.getAttribute("CONTEXT_ROOT");
}

  String xmlResponse = (String)request.getAttribute("xmlresponse");
    String message = "";
  
    if (xmlResponse != null) {
 	 if(xmlResponse.indexOf("<Message>") > 0) {
 		message = (xmlResponse.substring((xmlResponse.indexOf("<Message>")+9), xmlResponse.lastIndexOf("</Message>")));
 	 }
 	 if(xmlResponse.indexOf("</Data>") > 0) {
 		xmlResponse = (xmlResponse.substring(xmlResponse.indexOf("<Data>"), xmlResponse.lastIndexOf("</Data>"))) + "</Data>";
 	 }
    }
    else {
 	xmlResponse = "<Data></Data>";
   }
   
   System.err.println(xmlResponse);
   %>
   <% 
   String appNameLower = "reo";
   boolean isRegistered = false;
   String appPath = "OFWErrorPage.jsp?message='Access Denied. \r Please contact the administrator for details.'";
   if (request.isSecure()) {
       appPath = appConfig.getConfigValue("APPLICATION_PATH_SSL");
       
   }
   else {
       appPath = appConfig.getConfigValue("APPLICATION_PATH");
       System.err.println("inside of template list application path is ------++++++++--------"+appPath);
   }
%>
<%
  String divType;  
%>

<xtags:parse>
      <%=xmlResponse%>
</xtags:parse>


<HEAD>
<TITLE>REO Workflow Templates List</TITLE>

<link rel=templtListFormsheet href="css/workflow.css" type="text/css">
<link rel="STYLESHEET" type="text/css" href="css/dhtmlXGrid.css">
<link rel="STYLESHEET" type="text/css" href="css/dhtmlXGrid_skins.css">

<SCRIPT LANGUAGE="JavaScript1.2" SRC="js/CalenderComponent.js"></SCRIPT>
<script  src="js/dhtmlXCommon.js"></script>
<script  src="js/dhtmlXGrid.js"></script>		
<script  src="js/dhtmlXGridCell.js"></script>


<meta name="KEYWORDS" content="dhtmlxgrid, dhtml grid, javascript grid, javascript, DHTML, grid, grid control, dynamical scrolling, xml, AJAX, API, cross-browser, checkbox, XHTML, compatible, gridview, navigation, script, javascript navigation, web-site, dynamic, javascript grid, dhtml grid, dynamic grid, item, row, cell, asp, .net, jsp, cold fusion, custom tag, loading, widget, checkbox, drag, drop, drag and drop, component, html, scand">

<meta name="DESCRIPTION" content="Cross-browser DHTML grid with XML support and powerful API. This DHTML JavaScript grid can load its content dynamically from server using AJAX technology.">
</HEAD>
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
<SCRIPT LANGUAGE="JavaScript1.2">
function newDetails(){

        var operation = "<Operations><target>SERVER</target><clienttype>HTML</clienttype><Operation><SourceFormId>WF_Main.jsp</SourceFormId><RequestId>getAddTemplate</RequestId><Data><ApplicationName>workflow</ApplicationName></Data></Operation></Operations>";        
        //alert(operation);
        document.templtListForm.operationsXML.value = operation;
        document.templtListForm.action = "<%=appPath%>";
        document.templtListForm.divType.value = 'wft';
        //alert(document.templtListForm.divType.value);
     	document.templtListForm.destPageName.value = 'WF_TemplateMaintenance.jsp';
        document.templtListForm.submit();
}
function updateTemplate(){
		//alert("inside update template");
		
        var operation = "<Operations><target>SERVER</target><clienttype>HTML</clienttype><Operation><SourceFormId>WF_Main.jsp</SourceFormId><RequestId>getUpdateTemplate</RequestId><Data><ApplicationName>workflow</ApplicationName></Data></Operation></Operations>";        
        //alert(operation);
        document.templtListForm.operationsXML.value = operation;
        document.templtListForm.action = "<%=appPath%>";
        document.templtListForm.divType.value = 'wft';
        //alert(document.templtListForm.divType.value);
     	document.templtListForm.destPageName.value = 'WF_TemplateMaintenance.jsp';
        document.templtListForm.submit();
    }
    
function delTemplate(){

	for (i = 0; i < document.getElementsByName("selectList").length; i++) {
	if(document.getElementsByName("selectList")[i].checked){
	
	alert("i="+i+" ||value="+document.getElementsByName("selectList")[i].value +"||---Name="+document.getElementsByName("selectList")[i].name );
	
	var templtID= document.getElementsByName("selectList")[i].value;
	
	var operation ="<Operations><target>SERVER</target><clienttype>HTML</clienttype><Operation><SourceFormId>WF_Main.jsp</SourceFormId><RequestId>deleteTemplate</RequestId><Data><Templt_ID>"+templtID+"</Templt_ID><ApplicationName>workflow</ApplicationName></Data></Operation></Operations>";
	document.templtListForm.operationsXML.value = operation;
    document.templtListForm.action = "<%=appPath%>";
    document.templtListForm.divType.value = 'templt';
    document.templtListForm.destPageName.value = 'WF_TemplsList.jsp';
    //document.templtListForm.submit();
	
	}
	}

}
    
    
function selectUnselectList(chkBoxId) {
		if (document.getElementsByName(chkBoxId)[0].checked) {
			for (i = 0; i < document.getElementsByName("selectList").length; i++) {
				document.getElementsByName("selectList")[i].checked = true ;
			}
		} else {
	        for (i = 0; i <document.getElementsByName("selectList").length; i++) {
	        	document.getElementsByName("selectList")[i].checked = false;
	        }
	    }
	}

function checkUncheckBox(field) {
		var selected = true;
			for (i = 0; i <document.getElementsByName(field).length; i++) {
				if (!(document.getElementsByName(field)[i].checked)) {
				selected = false;
				break;
			}
		}
		document.getElementsByName("selectAll")[0].checked = selected;
	}
	
function doOnLoad(){
		//alert("Test Inside Grid");		
		var mygrid = new dhtmlXGridObject('gridbox');
		var i=0;
		var delim='\t';
		mygrid.setImagePath("images/");
		mygrid.setHeader("<input type='checkbox' name='selectAll' onClick='selectUnselectList(\"selectAll\");' ></input>,Template Code,TemplateDescription,Template Type,Inactive,Copy New Acct,Allow Global Cmplt,Activate Method,Changed By,Appl Cde");
		mygrid.setInitWidths("45,70,*,70,100,80,70,80,100,150")
		mygrid.setColAlign("center,left,left,center,left,left,right,center,right,center")
		mygrid.setColTypes("ro,ro,ro,ro,ro,ro,ro,ro,ro,ro");
		mygrid.setColSorting("int,str,str,str,str,str,int,str,str,str")
		mygrid.setSkin("light");
		mygrid.init();
		mygrid.enableLightMouseNavigation(true);
		mygrid.enableAlterCss("even","uneven");
		mygrid.setDelimiter(delim);
		
		     
		<xtags:forEach select="/Data/WF/TempList">
		var TemplateID= '<xtags:valueOf  select="./Templt_ID"/>';
		i++
			var chkbx = '<input type="checkbox" name="selectList" value="'+TemplateID+'" id="chkbox" onClick="checkUncheckBox(\'selectList\');" ></input>';
			
			var TemplateCode= '<a href="#" id="viewDetail" title = "Click To View/Update list Details" onclick="updateTemplate()"><xtags:valueOf  select="./Templt_Code"/></a>';
			var TemplateDescription = '<xtags:valueOf  select="./Templt_Desc"/>';
			var TemplateType = '<xtags:valueOf  select="./Templt_Type"/></a>';
			var Inactive = '<xtags:valueOf  select="./Inactive"/>';
			
			var CopyNewAcct = '<xtags:valueOf  select="./Copy_NewAcct"/>';
			var AllowGlobalCmplt = '<xtags:valueOf  select="./Allow_GlblComplt"/>';
         	var ActivateMethod = '<xtags:valueOf  select="./Act_Method"/>';
         	var ChangedBy = '<xtags:valueOf  select="./Changed_By"/>';
         	var ApplCde = '<xtags:valueOf  select="./Appl_Code"/>';
		mygrid.addRow(i,""+chkbx+"\t"+TemplateCode+"\t"+TemplateDescription+"\t"+TemplateType+"\t"+Inactive+"\t"+CopyNewAcct +"\t"+AllowGlobalCmplt+"\t"+ActivateMethod+"\t"+ChangedBy+"\t"+ApplCde,1);
		</xtags:forEach>
}

function showList(){

 document.getElementById("list").style.display = "block";
document.getElementById("del").style.display = "block";

}
function hideList(){
 document.getElementById("list").style.display = "none";
 
 document.getElementById("del").style.display = "none";

}
</SCRIPT>


<form name="templtListForm" id="templtListForm" action="#">
		<input type="hidden" name="operationsXML"> 
		<input type="hidden" name="divType">
		<input type="hidden" name="destPageName"/>


<table width="85%" align="center" cellpadding="0" cellspacing="0" border="0" class="threeD-border">
  <tr class="header-bgcolor">
    <td align="center">
      WorkFlow Template Search Criteria
    </td>
  </tr>

  <tr>
  <td>
	<table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="5">
			</td>
		</tr>
		<tr>
			
			        <td align="left">
			        <input type="button" value="Search " onclick="showList()" class="form-button" title = "Click To search Template">
			        &nbsp;<input type="reset" value=" Reset " onclick="hideList();" class="form-button" title = "Click To reset search criteria for Temaplate">
			        &nbsp;<input type="button" value="  New  " onclick="newDetails();"  class="form-button" title = "Click To create a new Template" >
			        </td>
		</tr>
	</table>
</td>
  </tr>
  <tr>
  	<td height="15"></td>
  </tr>
  <tr id="list">
  	<td>
  		<table width="100%" align="center" cellpadding="0" cellspacing="0">
			<tr  class="header-bgcolor"  align="center" > 
				<td width="90%">Workflow Templates List</td>
			</tr>
			<tr>
				<td>
					<div id="gridbox" width="100%" height="300px" style="border-width: 1pt;border-color:black;border-style: solid;background-color:#517298;">
					</div>
				</td>
			</tr>
		</table>
	</td>
  </tr>

</table>
<table width="85%" align="center" border="0" cellspacing="0" cellpadding="0" id="del">

<tr>
<td height="5">
</td>
</tr>
	<tr>
		<td align="left">
        <input type="button" value="Delete " onclick="delTemplate()" class="form-button" title = "Click To Delete selected Template" >
        </td>
       
	</tr>
</table>


</form>

<script type="text/javascript">
	doOnLoad();hideList();
</script>
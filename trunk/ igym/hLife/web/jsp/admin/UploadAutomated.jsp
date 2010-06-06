<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/common/Taglibs.jsp"%>

<html>
    <head>
        <title>Simple upload page</title>
       
<SCRIPT LANGUAGE="JavaScript1.2" >
 
function doSubmit(){
            var strPassed=document.forms[0].BUT_Browse.value;
            var startIndex=-1;
            var endIndex=0;
            var fileExt="";
            if(!strPassed){
                  alert("Please mention the location of the File");
                  document.forms[0].BUT_Browse.focus();
            }                 
            else if(strPassed.lastIndexOf(".")!=-1){
                  startIndex=strPassed.lastIndexOf(".");
                  endIndex=strPassed.length;
 
                  fileExt=strPassed.substring(startIndex+1,endIndex);
                  if(fileExt == ""){
                        alert("Please mention the FileExtension");
                        document.forms[0].BUT_Browse.focus();
                  }
            }else{
                  alert("Please mention the FileExtension");
                  document.forms[0].BUT_Browse.focus();
            }
            
            if(fileExt == "xls"){
                  document.forms[0].submit();
            }else{
                  alert("Please select excel file");
                  document.forms[0].BUT_Browse.focus();
            }
      }
 
</SCRIPT>
    </head>
 
 
 
<body>
<form name="UploadAutomated" action='<html:rewrite page="/admin/uploadAutomated.do?method=uploadAutomated" />' enctype="multipart/form-data" method="post">
<input type="hidden" name="method" value="uploadAutomated">
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
						Upload Automated Measurements
					</td>
				</tr>
				<tr>
					<td height="15"></td>
				</tr>
				<tr>
					<td width="40"></td>
					<td>
						<input class="default" type="file"  size="40" name="BUT_Browse"/>
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
			<input type="button" value="Upload" id="uploadbutton" onClick="doSubmit()" />
		</td>
	</tr>
</table>
</form>
</body>
</html>
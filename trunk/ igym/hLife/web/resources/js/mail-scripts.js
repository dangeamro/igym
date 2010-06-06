	var checkedFlag = false;
	
	function highlightRow(rowId,isRead,isChecked)
	{
		if(isChecked){
			if(isRead)
				document.getElementById(rowId).className = 'inbox-selected-message-row';
			else
				document.getElementById(rowId).className = 'inbox-selected-unread-message-row';
		}else{
			if(isRead)
				document.getElementById(rowId).className = 'inbox-message-row';
			else
				document.getElementById(rowId).className = 'inbox-unread-message-row';
		}
	}
	
	function checkUnchekAll(flag)
	{	
		checkedFlag = true;
		var chkBoxArray = document.getElementsByName("msgChkBox");

		if(chkBoxArray == null)
		{
			alert('Grrr...No customers available');
			return false;
		}
		
		for(i=0; i<chkBoxArray.length; i++)
		{
			chkBoxArray[i].checked = flag;
			chkBoxArray[i].onclick();
		}
		
		checkedFlag = false;
		return true;
	}
	
	function prepareList()
	{
		var chkBoxArray = document.getElementsByName("msgChkBox");

		var idList = '';
		var isFirst = true;
		for(i = 0; i < chkBoxArray.length; i++)
		{
			if(!chkBoxArray[i].checked)
				continue;
			if(!isFirst)
				idList = idList + ',';
			idList = idList + chkBoxArray[i].value;
			isFirst = false;
		}
		
		if(idList == '')
		{
			alert("Please select message(s) for deletion");
			return false;
		}
		
		document.getElementsByName("idList")[0].value = idList;
		
		return confirm("Delete the selected items?");
	}

	function checkSize()
	{
		
	}

	
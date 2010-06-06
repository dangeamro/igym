//*** Phone number format methods start  ***//
function formatPhNumber(number)
{
	var val = neutralizePhNumber(number);
	var result = new Array(14);
	var j = 0;
	
	for(i = 1; j < val.length && i < 14; i++)
	{
		if(i == 4 || i == 5 || i == 9)
			continue;
		result[i] = val.charAt(j++);
	}
	//for(i = j; i &lt; 14; i++)
		//result[i] = ' ';
		
	result[0] = '(';
	result[4] = ')';
	result[5] = ' ';
	result[9] = '-';
	
	return result.join('');
}

function neutralizePhNumber(number)
{
	var val = '' + number;
	var result = new Array(10);
	var j = 0;
	for(i = 0; i < val.length && j < 14; i++)
	{
		if(parseInt(val.charAt(i)) || val.charAt(i) == '0')
			 result[j++] = val.charAt(i);
	}
	
	return result.join('');
}
function formatPhNumberFld(fldObject)
{
	fldObject.value = formatPhNumber(fldObject.value);
}

function neutralizePhNumberFld(fldObject)
{
	fldObject.value = neutralizePhNumber(fldObject.value);
}

//*** Phone number format methods end  ***//


//----------------------------------------------------------------------//

//***  Date format methods start   ***//

  function addZero(vNumber){ 
    return ((vNumber < 10) ? "0" : "") + vNumber 
  } 
        
  function formatDate(vDate, vFormat){ 
    var vDay              = addZero(vDate.getDate()); 
    var vMonth            = addZero(vDate.getMonth()+1); 
    var vYearLong         = addZero(vDate.getFullYear()); 
    var vYearShort        = addZero(vDate.getFullYear().toString().substring(3,4)); 
    var vYear             = (vFormat.indexOf("yyyy")>-1?vYearLong:vYearShort) 
    var vHour             = addZero(vDate.getHours()); 
    var vMinute           = addZero(vDate.getMinutes()); 
    var vSecond           = addZero(vDate.getSeconds()); 
    var vDateString       = vFormat.replace(/dd/g, vDay).replace(/MM/g, vMonth).replace(/y{1,4}/g, vYear) 
    vDateString           = vDateString.replace(/hh/g, vHour).replace(/mm/g, vMinute).replace(/ss/g, vSecond) 
    return vDateString 
  }

//***  Date format methods end  ***//

//----------------------------------------------------------------------//

//*** String Util methods start  ***//

// Removes leading whitespaces
function lTrim(str) {
  var re = /\s*((\S+\s*)*)/;
  return str.replace(re, "$1");
}

// Removes ending whitespaces
function rTrim(str) {
  var re = /((\s*\S+)*)\s*/;
  return str.replace(re, "$1");
}

// Removes leading and ending whitespaces
// Optionally control characters
function trim(str, stripControlChars) {
  str = lTrim(rTrim(str));
  if (stripControlChars) {
    var r = '';
    for (i=0; i < str.length; i++) {
      if (str.charAt(i) != '\n' && str.charAt(i) != '\r' && str.charAt(i) != '\t') {
        r += str.charAt(i);
      } else {
        r += ' ';
      }
    }
    return r;
  } else {
    return str;
  }
}
//*** String Util methods ends ***//

//----------------------------------------------------------------------//

//*** Number Util methods start  ***//
	function checkNumber()
	{
		if(event.keyCode > 57 || event.keyCode < 48)
			return false;
		return true;
	}
	
	function checkSignedNumber()
	{
		if( event.keyCode != 45 && event.keyCode != 43 && !checkNumber())
			return false;
		return true;
	} 
	
	function checkDecimal(obj)
	{
		if(checkNumber() || event.keyCode == 46 && obj.value.indexOf('.') == -1)
			return true;
			
		return false;
	}
//*** Number Util methods ends ***//

//----------------------------------------------------------------------//
	
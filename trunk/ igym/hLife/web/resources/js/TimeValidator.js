function validateTime(obj,sep)
{
var timeObj = document.getElementById(obj);
var val = timeObj.value;
var len = val.length;
if(len == 1)
{
x = val.substr(0,1);
if(x==sep)x="0";
timeObj.value = "0"+x+sep+"00";
}
else if(len==2)
{
x = val.substr(0,1);
y = val.substr(1,1);
if(x==sep){x="00";timeObj.value=x+sep+"0"+y;return ;}
if(y==sep)y="0";
timeObj.value = x+y+sep+"00";
}
else if(len == 3)
{
x = val.substr(0,1);
y = val.substr(1,1);
z = val.substr(2,3);
if(x==sep){x="00";timeObj.value=x+sep+y+z;return;}
if(y==sep){y=x; x="0"}
if(z==sep){ z="0"}
timeObj.value = x+y+sep+"0"+z;
}
else if(len == 4 )
{
x = val.substr(0,1);
y = val.substr(1,1);
z = val.substr(2,1);
w = val.substr(3,1);
if(y==sep){y=x; x="0"}
if(z==sep){ z="0"}
timeObj.value = x+y+sep+z+w;
return;
}
else if(len==5)
{
x = val.substr(0,1);
y = val.substr(1,1);
z = val.substr(2,1);
w = val.substr(3,1);
p = val.substr(4,1);
editValidTime(obj,sep)
}

}


function editValidTime(obj,sep)
{
if(window.event.keyCode ==37){return true; }
if(window.event.keyCode ==46){return true; }
if(window.event.keyCode ==8){ return true; }

var timeObj = document.getElementById(obj);
var val = timeObj.value;
var len = val.length;
if(len == 1)
{
x = val.substr(0,1);
if(!(x>=0 && x<=9)){ x=""; timeObj.value=""; return;}
if(x == 1 || x== 2 || x == 0) {timeObj.value = x; }
else{timeObj.value = "0" + x + sep; }
}
else if(len == 2)
{
if(window.event.keyCode == "8")sep = "";
x = val.substr(0,1);
y = val.substr(1,2);
if(!(x>=0 && x<=9)){ x="0"; }
if(!(y>=0 && y<=9))
{
if(y!=sep){ y="0";}
timeObj.value = x; return;
}
if(x == 1 || x==0){}
else if( x== 2)
{
if(y > 3){timeObj.value = x; return; }
if(y == sep){timeObj.value = "0" + x + sep; return;}
}
else
{
if(y == sep){timeObj.value = "0" + x + sep; return; }
timeObj.value = "0" + y + sep;
return;
}
if(y == sep){timeObj.value = "0" + x + sep; return;}
timeObj.value = x + y + sep;
}
else if(len == 3)
{
x = val.substr(0,1);
y = val.substr(1,1);
z = val.substr(2,3);
if(!(x>=0 && x<=9)){ x="0";}
if(!(y>=0 && y<=9)){if(y!=sep){ y="0";}}
if(!(z>=0 && z<=5)){if(z!=sep){ z="";}}
else{ if(z>5 &&z<=9)z = "0" + z;}
if(x == 1 || x==0){}
else if( x== 2){if(y > 3){timeObj.value = x; return;}}
else
{
if(y == sep){timeObj.value = "0"+x+sep+z; return;}
timeObj.value = "0" + y +sep;
return;
}
if(z == sep){ timeObj.value = x+y+sep; return;}
if(y == sep){ timeObj.value = "0"+x+sep+z; return;}
timeObj.value = x+y+sep+z;
}

else if(len == 4 )
{
x = val.substr(0,1);
y = val.substr(1,1);
z = val.substr(2,1);
w = val.substr(3,1);
if(!(x>=0 && x<=9)){ x="0";}
if(!(y>=0 && y<=9)){if(y!=sep){ y="0"; }}
if(!(w>=0 && w<=9)){w=""}
if(y == sep){timeObj.value = "0" + x+sep+z+w; return;}
if(x == 1 || x==0){}
else if( x== 2){if(y > 3){timeObj.value = "0"+x+sep+x+w; return;}}
else{timeObj.value = "0" + y + sep + w;return; }
if(z != sep){timeObj.value = x+y+sep+z+w; return;}
if(w>5){timeObj.value = x+y+sep+"0"+w; return;}
timeObj.value = x+y+sep+w;
return;
}

else if(len == 5 )
{
x = val.substr(0,1);
y = val.substr(1,1);
z = val.substr(2,1);
w = val.substr(3,1);
p = val.substr(4,1); //alert(x+y+z+w+p)
if(!(x>=0 && x<=9)){ x="0"; }
if(!(y>=0 && y<=9)){if(y!=sep){ y="0";}}
if(!(w>=0 && w<=9)){w=""}
if(!(p>=0 && p<=9)){p=""}
if(x == 1 || x==0){}
else if( x== 2){if(y > 3){timeObj.value = "0"+x+sep+w+p; return; }}
else{timeObj.value = "0" + y + sep + w + p;return;}
if(w>5)w="0";
timeObj.value = x+y+sep+w+p;
}
}

function maskSpaceBar(obj)
{
var key = window.event.keyCode;
if(window.event.keyCode ==32){return false;}
if(!(key>=48 && key<=58)){return false;}
}

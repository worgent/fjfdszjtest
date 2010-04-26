n<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>输入框显示下拉菜单提示效果</title>
<style type="text/css">
<!--
body {background:#fff}
#testbox{padding:20px; background:#D0E6F0; border:1px #09c;}

.Menu {position:relative;width:204px;height:127px; z-index:1; background: #FFF; border:1px solid #000; margin-top:-100px; display:none;}
.Menu2 {position: absolute; left:0; top:0; width:100%; height:auto; overflow:hidden; z-index:1;}
.Menu2 ul {margin:0; padding:0}
.Menu2 ul li {width:100%; height:25px;line-height:25px;text-indent:15px;border-bottom:1px dashed #ccc;color:#666;cursor:pointer;change:expression(this.onmouseover=function(){ this.style.background="#F2F5EF";},this.onmouseout=function() {this.style.background="";})}
input {width:190px}
.form {width:200px;height:auto; background:#ff0;}
.form div {position:relative;top:0;left:0;margin-bottom:5px}
#List1, #List2, #List3 {left:0px;top:93px;}
-->
</style>
<script type="text/javascript">
function showAndHide(obj,types){
  var Layer=window.document.getElementById(obj);
  switch(types){
    case "show":
    Layer.style.display="block";
    break;
    case "hide":
    Layer.style.display="none";
    break;
  }
}
function getValue(obj,str){
  var input=window.document.getElementById(obj);
  input.value=str;
 
}
</script>
<link rel="StyleSheet" href="<%=request.getContextPath() %>/css/dtree.css" type="text/css" />   
            <script type="text/javascript" src="<%=request.getContextPath() %>/js/dtree.js"></script> 
</head>
<body>
此实例效果不错，且很实用，但嵌入到页面中点击时会将外层box(蓝灰色区域)撑开一点点，菜单不会浮于页面上，改用"position: absolute;"如何定位？不知道如何可以解决这个bug。
求高手解决，不胜感激。
<div id="testbox">
<!--主体-->
    <div class="form">
    
        <div> Location：
        <input type="text" id="txt" name="txt" onfocus="showAndHide('List1','show');" 
          ></div>
        <!--列表1-->
        <div class="Menu" id="List1">
           <div class="dtree">
                 <script type="text/javascript">   
                d = new dTree('d');
                d.add(0,-1,'444',"javascript:getValue('txt,'444');showAndHide('List1','hide');");  
                d.add(1,0,'555',"javascript:getValue('txt','555');showAndHide('List1','hide');");  
                d.add(2,1, '666',"javascript:getValue('txt','666');showAndHide('List1','hide');");  
                d.add(3,0,'777',"javascript:getValue('txt','777');showAndHide('List1','hide');");  
                d.add(4,3,'888',"javascript:getValue('txt','888');showAndHide('List1','hide');");  
                
                d.add(5,0,'7777',"javascript:getValue('txt','7777');showAndHide('List1','hide');");  
                d.add(6,5,'8882',"javascript:getValue('txt','888'2);showAndHide('List1','hide');");  
                d.add(7,0,'7774',"javascript:getValue('txt','7774');showAndHide('List1','hide');");  
                d.add(8,7,'8884',"javascript:getValue('txt','8884');showAndHide('List1','hide');");  
                document.write(d);
    </script> 
    </div>   
    
        </div>
        

       
       
        
    </div>
<!--主体-->
</div>
<br/>
<br/>
</body>
</html>

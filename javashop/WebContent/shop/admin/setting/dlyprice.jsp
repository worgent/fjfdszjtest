<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<div style="" class="deliveryexp">

首重费用 <input type="text"   name="firstprice" style="width: 30px;" disabled="true" dataType="float" required="true"/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
续重费用 <input type="text"   name="continueprice" style="width: 30px;" disabled="true" dataType="float" required="true"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<span  class="lnk showexp">使用公式</span>

</div>
 <input type="hidden" value="0" name="useexp" disabled="true" >
<div style="display: none;" class="deliveryexp">配送公式
 <input type="text"     name="expressions" style="width: 300px;" disabled="true"/>
 <span   class="checkexp sysbtn">验证</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 <span   class="lnk hideexp">取消公式</span>

</div>

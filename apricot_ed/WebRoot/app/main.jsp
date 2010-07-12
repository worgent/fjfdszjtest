<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="eating-apricot" prefix="xhtml"%>
<xhtml:html javascript="app/js/main.js" title="餐饮系统">
<style>
<!--
.msg .x-box-mc {
    font-size:14px;
}
#msg-div {
    position:absolute;
    left:35%;
    top:10px;
    width:250px;
    z-index:20000;
}

-->
</style>
<script language="">
Ext.example = function(){
    var msgCt;

    function createBox(t, s){
        return ['<div class="msg">',
                '<div class="x-box-tl"><div class="x-box-tr"><div class="x-box-tc"></div></div></div>',
                '<div class="x-box-ml"><div class="x-box-mr"><div class="x-box-mc"><h3>', t, '</h3>', s, '</div></div></div>',
                '<div class="x-box-bl"><div class="x-box-br"><div class="x-box-bc"></div></div></div>',
                '</div>'].join('');
    }
    return {
        msg : function(title, format){
            if(!msgCt){
                msgCt = Ext.DomHelper.insertFirst(document.body, {id:'msg-div'}, true);
            }
            msgCt.alignTo(document, 't-t');
            var s = String.format.apply(String, Array.prototype.slice.call(arguments, 1));
            var m = Ext.DomHelper.append(msgCt, {html:createBox(title, s)}, true);
            m.slideIn('t').pause(15).ghost("t", {remove:true});

            
        },        init : function(){

            var lb = Ext.get('lib-bar');
            if(lb){
                lb.show();
            }
        }
    };
}();
Ext.onReady(Ext.example.init, Ext.example);

function GlobalPrinterStatus(){
   doGet("/print/error_list.print",{},function(d){
    for(var i=0;i<d.data.length;i++){
      var a=d.data[i];

      Ext.example.msg(a.printer,a.status+"如想看详细信息，请访问系统管理->打印监控!");
}
});
}
GlobalPrinterStatus();
window.setInterval(GlobalPrinterStatus,30*1000);
</script>
</xhtml:html>
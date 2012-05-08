$(function () {
    $(".tab .table1 td:first").addClass("current"); //为第一个SPAN添加当前效果样式 
    $(".tab ul li:not(:first)").hide(); //隐藏其它的UL 
    $(".tab .table1 .tab_tit").click(function () {
        $(".tab .table1 td").removeClass("current"); //去掉所有SPAN的样式 
        $(this).addClass("current");
        $(".tab ul li").hide();
        $("." + $(this).attr("id")).fadeIn('slow');
    });
    if ($("#editFlag").val() != "VOIP") {
        f_changemode($("#NetWorkingMode").val());
    }
});
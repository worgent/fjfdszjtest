
/** add by cpe **/
function f_convertData(booldata, flag) {
switch(flag){
    case "Whether":
        if (booldata == 1)
            return "是";
        else
            return "否";
        break;
    case "FPState":
        if (booldata == "0")
            return "待接收";
        if (booldata == "1")
            return "已接收";
        if (booldata == "2")
            return "已退回";
        else
            return "待接收";
        break;
    case "IOType":
        if (booldata == "IList")
            return "入库";
        if (booldata == "OList")
            return "出库";
        else
            return "调拨";
        break;
    case "DistricType":
        if (booldata == "1")
            return "省汇";
        if (booldata == "2")
            return "市级";
        if (booldata == "3")
            return "区县";
        if (booldata == "4")
            return "驻点";
        else
            return "全级";
        break;
    }
}
function f_convertEquip(booldata) {
    if (booldata == 1)
        return "用户机房";
    else
        return "局端";
}
function f_convertIO(booldata) {
    alert(booldata);
    if (booldata == "IList")
        return "入库";
    if (booldata == "OList")
        return "出库";
    else
        return "调拨";
}
/** add end **/


/** add by hld **/
function f_convertDate (date, dateformat) {
    if (date == "NaN") return null;
    var format = dateformat;
    var o = {
        "M+": date.getMonth() + 1,
        "d+": date.getDate(),
        "h+": date.getHours(),
        "m+": date.getMinutes(),
        "s+": date.getSeconds(),
        "q+": Math.floor((date.getMonth() + 3) / 3),
        "S": date.getMilliseconds()
    }
    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (date.getFullYear() + "")
            .substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
                : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
}
/** add end **/
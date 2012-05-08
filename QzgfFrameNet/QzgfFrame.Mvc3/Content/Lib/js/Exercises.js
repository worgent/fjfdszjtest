//在线练习 页面读取 数据







//获取“在线练习”页面信息
function GetExercisesData(num) {
    var subjectary = new Array();
    
    //第一个循环，是判断页面有几个table
    for (var j = 1; j <= num; j++) {
        var ary = new Array();
        ary.length = 0;
        var tableName = "previewsubjectOrderNo";
        tableName += j;
        var signFrame = findObj(tableName, document);
        var rowscount = signFrame.rows.length;

        //获取试题类型
        var typeName = signFrame.rows[0].cells[0].getElementsByTagName("input")[0].value;
        
        switch (typeName) {
            case "选择题":
                var subject = new Object();
                //选择题取答案
                subject["OrderNo"] = signFrame.rows[1].cells[0].getElementsByTagName("input")[0].value;
                subject["TestSubjectId"] = signFrame.rows[1].cells[0].getElementsByTagName("input")[1].value;
                subject["SubjectTypeID"] = signFrame.rows[1].cells[0].getElementsByTagName("input")[2].value;
                //第二个循环，是判断table里面有几行
                for (i = 1; i < rowscount; i++) {
                    if (signFrame.rows[i].cells[1].getElementsByTagName("input")[0].checked) {
                        subject["Answer"] = signFrame.rows[i].cells[1].getElementsByTagName("input")[0].value;
                    }
                }
                ary.push(subject);
                break;
            case "填空题":
                var subject = new Object();
                subject["OrderNo"] = signFrame.rows[1].cells[0].getElementsByTagName("input")[0].value;
                subject["TestSubjectId"] = signFrame.rows[1].cells[0].getElementsByTagName("input")[1].value;
                subject["SubjectTypeID"] = signFrame.rows[1].cells[0].getElementsByTagName("input")[2].value;
                subject["Answer"] = signFrame.rows[1].cells[1].getElementsByTagName("input")[0].value;
                ary.push(subject);
                break;
            case "判断题":
                var subject = new Object();
                subject["TestSubjectId"] = signFrame.rows[1].cells[0].getElementsByTagName("input")[0].value;
                subject["SubjectTypeID"] = signFrame.rows[1].cells[0].getElementsByTagName("input")[1].value;
                if (signFrame.rows[1].cells[1].getElementsByTagName("input")[0].checked) {
                    subject["Answer"] = signFrame.rows[1].cells[1].getElementsByTagName("input")[0].value;
                }
                if (signFrame.rows[1].cells[1].getElementsByTagName("input")[1].checked) {
                    subject["Answer"] = signFrame.rows[1].cells[1].getElementsByTagName("input")[1].value;
                }
                ary.push(subject);
                break;
        }
        
        subjectary.push(ary);
    }
    return subjectary;
}
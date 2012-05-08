var defaultValidateErrorPlacement = function (lable, element) {
    if (element.hasClass("l-textarea")) element.ligerTip({ content: lable.html(), appendIdTo: lable });
    else if (element.hasClass("l-text-field")) element.parent().ligerTip({ content: lable.html(), appendIdTo: lable });
    else lable.appendTo(element.parents("td:first").next("td"));
};

var defaultValidateSuccess = function (lable) {
    lable.ligerHideTip();
};

var deafultValidate = function (validateElements) {
    return validateElements.validate({
        errorPlacement: function (lable, element) {
            defaultValidateErrorPlacement(lable, element);
        },
        success: function (lable) {
            defaultValidateSuccess(lable);
        }
    });
};
function findObj(theObj, theDoc) {
    var p, i, foundObj;
    if (!theDoc) theDoc = document;
    if ((p = theObj.indexOf("?")) > 0 && parent.frames.length) {
        theDoc = parent.frames[theObj.substring(p + 1)].document;
        theObj = theObj.substring(0, p);
    }
    if (!(foundObj = theDoc[theObj]) && theDoc.all)
        foundObj = theDoc.all[theObj];
    for (i = 0; !foundObj && i < theDoc.forms.length; i++)
        foundObj = theDoc.forms[i][theObj];
    for (i = 0; !foundObj && theDoc.layers && i < theDoc.layers.length; i++)
        foundObj = findObj(theObj, theDoc.layers[i].document);
    if (!foundObj && document.getElementById)
        foundObj = document.getElementById(theObj);
    return foundObj;
}

$(function () {
    if (jQuery.validator) {
        //字母数字
        jQuery.validator.addMethod("alnum", function (value, element) {
            return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
        }, "只能包括英文字母和数字");

        // 手机号码验证   
        jQuery.validator.addMethod("cellphone", function (value, element) {
            var length = value.length;
            return this.optional(element) || (length == 11 && /^(1\d{10})$/.test(value));
        }, "请正确填写手机号码");

        // 电话号码验证   
        jQuery.validator.addMethod("telephone", function (value, element) {
            var tel = /^(\d{3,4}-?)?\d{7,9}$/g;
            return this.optional(element) || (tel.test(value));
        }, "请正确填写电话号码");

        // 邮政编码验证
        jQuery.validator.addMethod("zipcode", function (value, element) {
            var tel = /^[0-9]{6}$/;
            return this.optional(element) || (tel.test(value));
        }, "请正确填写邮政编码");

        // 汉字
        jQuery.validator.addMethod("chcharacter", function (value, element) {
            var tel = /^[\u4e00-\u9fa5]+$/;
            return this.optional(element) || (tel.test(value));
        }, "请输入汉字");

        // 汉字
        jQuery.validator.addMethod("qq", function (value, element) {
            var tel = /^[1-9][0-9]{4,}$/;
            return this.optional(element) || (tel.test(value));
        }, "请输入正确的QQ");

        // 用户名
        jQuery.validator.addMethod("username", function (value, element) {
            return this.optional(element) || /^[a-zA-Z][a-zA-Z0-9_]+$/.test(value);
        }, "用户名格式不正确");
    }
});


var GetUrlParam = function (name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
};

var SetButtons = function (toolbar, url) {
    if (!url) {
        url = '/system/menu/GetButton?menuid=';
        url += GetUrlParam('_menuid');
    }
    //debugger; 

    url += "&rnd" + Math.random();
    $.getJSON(url, function (data) {
        if (!data) return;
        var buttons = [];
        $(data).each(function (i, dataitem) {
            var btn = { text: this.name, icon: this.icon, id: this.id };
            if (f_btnClick) btn.click = f_btnClick;
            buttons.push(btn);
        });
        toolbar.ligerToolBar({ items: buttons });
    });
};

//提交用户信息
function f_submit(path, formdata,id) {

    $.ajax({
        mode: "abort",
        type: "post",
        url: path,
        data: formdata,
        dataType: 'json',
        cache: false,
        async: false,
        success: function (result) {
            $.ligerDialog.close();
            if (result.Type) {
                f_success(id);
            }
            else {
                f_error(result.Message);
            }
        },
        complete: function () {
        },
        beforeSend: function (xhr) {
        },
        error: function () {
            $.ligerDialog.alert("服务器忙");
        }
    });
    /*
    $.getJSON(path + "?" + form1data, { Rnd: Math.random() }, function (data) {
    if (data.Type) {
    $.ligerDialog.success("删除成功");
    }
    else {
    $.ligerDialog.error("删除失败!<BR>" + data.Message);
    }
    $.ligerDialog.closeWaitting();
    return data.Type;
    });
    */
}
//begin 数组删除指定元素
Array.prototype.indexOf = function (val) {
    for (var i = 0; i < this.length; i++) {
        if (this[i] == val) return i;
    }
    return -1;
};
Array.prototype.remove = function (val) {
    var index = this.indexOf(val);
    if (index > -1) {
        this.splice(index, 1);
    }
};
//end 数组删除指定元素
//-----------高级查询临时方案----------------
function f_find(gridManager, gridsearch) {
    if (!gridManager) return;
    gridManager.setOptions(
                { parms: [{ name: 'gridsearch', value: gridsearch}] }
    );
    gridManager.loadData(true);
}
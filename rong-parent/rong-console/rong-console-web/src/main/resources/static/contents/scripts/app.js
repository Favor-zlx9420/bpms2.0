//back
//返回结果代码
var MONEY_DIGIT = 1;//100表示数据库存的是分，10表示角、1表示元，以此类推
var ResultStatus={
		no_login:"401",
		no_right:"403",
		ok:"200"
};
var SpecType={
		text:1,
		img:2
};
FundsDirectionDesc = {
    income : 0,
    outcome : 1,
    preoincome : 2,
    preoutcome : 3
}
var fileTypeIcon={
    pdf:"file-pdf",
    audio:"file-audio",
    mp4:"file-movie",
    word:"file-word",
    excel:"file-excel",
    text_plain:"file-excel",
    poweroint:"file-powerpoint",
    image_jpeg:"file-image",
    archive:"file-archive",
    other:"file-code"
};
var loanState={
    notpublish:{key:"未发布",value:0},
    investing:{key:"可投标",value:1},
    full:{key:"已满标",value:2},
    repaying:{key:"还款中",value:3},
    repaid:{key:"已还款",value:4},
    flowed:{key:"已流标",value:5},
    cannotinvest:{key:"不可投",value:6},
    failedpublish:{key:"审核不通过",value:7}
};
var colorType={
    boolean_false:"#e75033",
    boolean_true:"#329d38",
    commState_0:"#e75033",
    commState_1:"#329d38",
    commState_2:"#c1c1c1",
    commState_99:"gray",
    amountDirection_0:"#329d38",
    amountDirection_1:"#e75033",
    amountDirection_2:"#DB70DB",
    amountDirection_3:"#ed980f",
    rechargeState_0:"#03a2b6",
    rechargeState_1:"#329d38",
    rechargeState_2:"#e75033",
    rechargeState_3:"#ed980f",
    rechargeState_4:"#f1f1f1",
    withdrawState_0:"#03a2b6",
    withdrawState_1:"#329d38",
    withdrawState_2:"#329d38",
    withdrawState_3:"#e75033",
    creditCertifyState_未验证:"gray",
    creditCertifyState_申请验证中:"#03b8cf",
    creditCertifyState_验证通过:"#2e9434",
    creditCertifyState_验证无效:"#e75033",
    creditCertifyState_重新申请:"#03a2b6",
    loanDebitState_0:"#c1c1c1",
    loanDebitState_1:"#329d38",
    loanDebitState_2:"#ed980f",
    loanDebitState_3:"#03b8cf",
    loanDebitState_4:"#808080",
    loanDebitState_5:"#e75033",
    loanDebitState_6:"#bd7b46",
    loanDebitState_7:"#bc8f8f",
    mhxy_amount_0:"#000",//0-10000
    mhxy_amount_1:"#0000ff",//10000-100000
    mhxy_amount_2:"#238E23",//100000-1000000
    mhxy_amount_3:"#FF2400",//1000000-10000000
    mhxy_amount_4:"#DB70DB",//10000000-100000000
    mhxy_amount_5:"#ed980f",//100000000以上
    mhxy_amount_6:"#e75033",//小于0
    loanRepayState_0:"#03b8cf",//未到期
    loanRepayState_1:"#329d38",//已结清
    loanRepayState_2:"#ed980f",//未结清
    loanRepayMode_11:"#bc8f8f",//正常
    loanRepayMode_33:"#329d38",//提前
    loanRepayMode_55:"#03b8cf",//待还
    loanRepayMode_77:"#c1c1c1",//部分
    loanRepayMode_99:"#e75033",//逾期
    loanRepayMode_88:"#ed980f",//快到期
    customerOrderPayState_0:"#e75033",
    customerOrderPayState_1:"#DB70DB",
    customerOrderPayState_2:"#ed980f",
    customerOrderPayState_3:"#329d38",
}
function findNameByValueFromConsts(e,v){
    for(var x in e){
        if(e[x] == v){
            return x;
        }
    }
    return "";
}
function commStateFunc(tr,v,i){
    var desc = findNameByValueFromConsts(Consts.State,v);
    return stateDescFunc(tr,desc,i);
}
function bindYesOrNoFunc(tr,v,i){
    var desc = findNameByValueFromConsts(Consts.YesOrNo,v);
    return getColorWrap(colorType["commState_"+v],desc);
}
function dateTimeFunc(tr,v,i){
    if(LE.isEmpty(v)){
        return v;
    }
    return LE.formateDateBymilTime(v);
}
function bindDateTimeFunc(tr,v,i){
    if(LE.isEmpty(v)){
        return v;
    }
    return LE.formateDateBymilTime(v);
}
function bindDateFunc(tr,v,i){
    if(LE.isEmpty(v)){
        return v;
    }
    return LE.formateShortDateBymilTime(v);
}
function getColorWrap(color,v){
    return "<span style='color:" + color+";'>"+v+"</span>";
}
function stateDescFunc(tr,v,i){
    return getColorWrap(colorType["commState_"+tr["state"]],v);
}
function booleanDescFunc(tr,v,i){
    return getColorWrap(colorType["boolean_"+v],v ? "是":"否");
}
function integerDescFunc(tr,v,i){
    var state = v > 0 ? 1 : 0;
    return getColorWrap(colorType["commState_" + state],v);
}
function getRespAmount(v,b){
    //v /=MONEY_DIGIT;
    var d = 6;
    if(b) {
        if (v >= 0 && v < 10000) {
            d = 0;
        } else if (v >= 10000 && v < 100000) {
            d = 1;
        } else if (v >= 100000 && v < 1000000) {
            d = 2;
        } else if (v >= 1000000 && v < 10000000) {
            d = 3;
        } else if (v >= 10000000 && v < 100000000) {
            d = 4;
        } else if (v >= 100000000) {
            d = 5;
        }
    }
    return {v:v,d:d};
}
function respInpAmountOfColor(entity,v,i,target,inited){//inited：true初始化，其他的keyup
    var fv = v;
    if(null == fv || fv == ""){fv = 0;}
    var o = getRespAmount(fv,true);
    $(target).css("color",colorType["mhxy_amount_" + o.d]);
    return inited ? LE.toThousandth(o.v,2).replace(/,/g,''):v;
}
function respOriAmountOfColor(tr,v,i,p){
    if(null == v){
        return "--";
    }
    var o = getRespAmount(v,true);
    var _o={
        pre:"",
        suff:"",
    }
    if(null != p) {
        $.extend(_o, p);
    }
    return getColorWrap(colorType["mhxy_amount_" + o.d],_o.pre+LE.toThousandth(o.v,2)+_o.suff);
}
function respInpDecimal_2(entity,v,i,target){
    return LE.toThousandth(v,2).replace(/,/g,'');
}
function respOriDecimal_2(tr,v,i){
    return LE.toThousandth(v,2);
}
function showLoanDebitState(tr,v,i){
    return getColorWrap(colorType["loanDebitState_"+tr["state"]],v);
}
function showDebitStateByDebitState(tr,v,i){
    return getColorWrap(colorType["loanDebitState_"+tr["debitState"]],v);
}
function showLoanRepayState(tr,v,i){
    return getColorWrap(colorType["loanRepayState_"+tr["state"]],v);
}
function showDebitNameNumber(tr,v,i){
    return "<div><span>"+showDebitStateByDebitState(tr,tr["loanDebitName"],i)+"</span>/<span>"+tr["loanDebitNumber"]+"</span></div>"
}
function showRepayPrincipal(tr,v,i){
    return "<div>应还：<span>"+respOriAmountOfColor(tr,tr["shrepayPrincipal"],i)+"</span></div>"
        +"<div>已还：<span>"+respOriAmountOfColor(tr,tr["repaidPrincipal"],i)+"</span></div>";
}
function showRepayInterest(tr,v,i){
    return "<div>应还：<span>"+respOriAmountOfColor(tr,tr["shrepayInterest"],i)+"</span></div>"
        +"<div>已还：<span>"+respOriAmountOfColor(tr,tr["repaidInterest"],i)+"</span></div>";
}
function showRepayTotalAmount(tr,v,i){
    return "<div>应还：<span>"+respOriAmountOfColor(tr,tr["shrepayAmount"],i)+"</span></div>"
        +"<div>已还：<span>"+respOriAmountOfColor(tr,tr["repaidAmount"],i)+"</span></div>";
}
function showRepaymentDate(tr,v,i){
    return "<div>预计：<span>"+tr["expectedRepaymentDate"]+"</span></div>"
        +"<div>实际：<span>"+(tr["actualRepaymentDate"] ? tr["actualRepaymentDate"]:"--") +"</span></div>";
}

function showRecoveryDate(tr,v,i){
    return "<div>预计：<span>"+tr["expectedRecoveryDate"]+"</span></div>"
        +"<div>实际：<span>"+(tr["actualSettleDate"] ? tr["actualSettleDate"]:"--") +"</span></div>";
}
function showRecoveryPrincipal(tr,v,i){
    return "<div>应收：<span>"+respOriAmountOfColor(tr,tr["deservePrincipal"],i)+"</span></div>"
        +"<div>已收：<span>"+respOriAmountOfColor(tr,tr["recoveredPrincipal"],i)+"</span></div>";
}
function showRecoveryInterest(tr,v,i){
    return "<div>应收：<span>"+respOriAmountOfColor(tr,tr["deserveInterest"],i)+"</span></div>"
        +"<div>已收：<span>"+respOriAmountOfColor(tr,tr["recoveredInterest"],i)+"</span></div>";
}
function respOriLongOfColor(tr,v,i,p){
    if(null == v){
        return "--";
    }
    var o = getRespAmount(v,true);
    var _o={
        pre:"",
        suff:"",
    }
    if(null != p) {
        $.extend(_o, p);
    }
    return getColorWrap(colorType["mhxy_amount_" + o.d],_o.pre+LE.toThousandth(o.v,0)+_o.suff);
}
function amountFc(tr,v,i){
    var sv ="";
    if(tr["direction"] == FundsDirectionDesc.income){
        sv ="+";
    }else if(v > 0){
        sv ="-";
    }
    return respOriAmountOfColor(tr,v,i,{pre:sv});
}
function showFunsType(tr,v,i){
    return getColorWrap(colorType["amountDirection_"+tr["direction"]],v);
}
function showMemName(tr,v,i){
    var disp = tr["userName"];
    var memRealName = tr["memRealName"];
    if(!LE.isEmpty(memRealName)){
        disp += "/" + memRealName;
    }
    return disp;
}
function showMemNameByEntity(entity){
    var disp = entity["userName"];
    var memRealName = entity["memRealName"];
    if(!LE.isEmpty(memRealName)){
        disp += "/" + memRealName;
    }
    return disp;
}
function getSuffRepayDateOfDate(expDate,actDate){
    var expday = LE.formateShortDateBymilTime(expDate.getTime()).replace(/-/g,'');
    var now = LE.formateShortDateBymilTime(actDate.getTime()).replace(/-/g,'');
    return parseInt(now) - parseInt(expday);
}


var PCEdit={
		getSelectItem:function(value,sval){//下拉框选项
            var sb = "";
            var vals = value.split("|");
            var sel = false;
            for (var i = 0; i < vals.length; ++i)
            {
                sel = (","+sval+",").indexOf(","+vals[i]+",") > -1;
                sb +="<option value='"+vals[i]+"' "+(sel?"selected='selected'":"")+">";
                sb +=vals[i];
                sb +="</option>";
            }
            return sb;
		},
		getSingleListBox:function(value,isClick,sval,name){//单选框
            var sb = "";
            var vals = value.split("|");
            var sel = false;
            for (var i = 0; i < vals.length; ++i)
            {
                sel = (","+sval+",").indexOf(","+vals[i]+",") > -1;
                sb +="<label class='viyui-radio'><input type='radio' "+(sel?"checked='checked'":"")+" "+name+" title='"+vals[i]+"' value='"+vals[i]+"' />"+vals[i]+"</label>";
            }
            return sb;
		},
		getMultiListBox:function(value,isClick,sval,name){//多选框
            var sb = "";
            var vals = value.split("|");
            var sel = false;
            for (var i = 0; i < vals.length; ++i)
            {
                sel = (","+sval+",").indexOf(","+vals[i]+",") > -1;
                sb +="<label class='viyui-checkbox'><input type='checkbox' "+(sel?"checked='checked'":"")+" "+name+" value='"+vals[i]+"' />"+vals[i]+"</label>";
            }
            return sb;
		},
		getExpTypeInput:function(epid,type,val,sVal,isClick,index){
            var _nid = "name='dynList["+index+"].id'";
            var _nval = "name='dynList["+index+"].value'";
            var html = "<input type='hidden' "+_nid+" value='"+epid+"' />";
            if(val == null){
            	val = "";
            }
            if(sVal == null){
            	sVal = "";
            }
            sVal = sVal.replace('|', '-').replace('\"', '\'');
            //Func fun = new Func();
            //fun.AddScript("alert('" + sVal.ToString() + "----');");
            switch (parseInt(type))
            {
                case 0://单选下拉框:使用原有的（class='no-render'）
                    html +="<select class='no-render' "+(isClick ? "" : "disabled='disabled'")+" "+_nval+" >";
                    html +=PCEdit.getSelectItem(val,sVal);
                    html +="</select>";
                    break;
                case 1://多选下拉框:使用原有的（class='no-render'）
                    html +="<select class='no-render' class='selMulti' tabindex='5' multiple='multiple' "+_nval+" >";
                    html +=PCEdit.getSelectItem(val,sVal);
                    html +="</select>";
                    break;
                case 2://单行文本框
                    html +="<input "+(isClick ? "" : "disabled='disabled'")+" type='text' class='input validator' "+_nval+" value='";
                    html +=sVal.replace('\"', '\'')+"' />";
                    break;
                case 3://多行文本框
                    html +="<textarea"+(isClick ? "" : "disabled='disabled'")+" cols=\"80\" rows='4' class=\"input textarea\" "+_nval+" >"+sVal+"</textarea>";
                    break;
                case 4://单选框
                    html +="<div class='le-form-element'>";
                    html +=PCEdit.getSingleListBox(val, isClick,sVal,_nval);
                    html +="</div>";
                    break;
                case 5://多选框
                    html +="<div class='select-com-box le-form-element'>";
                    html +=PCEdit.getMultiListBox(val, isClick,sVal,_nval);
                    html +="</div>";
                    break;
            }
            return html;
		},
        getFormElementForJson:function(epid,type,val,sVal,isClick,index){
            var _nid = " data-dynList-name='id' ";
            var _nval = " data-dynList-name='value' ";
            var html = "<input type='hidden' "+_nid+" value='"+epid+"' />";
            if(val == null){
                val = "";
            }
            if(sVal == null){
                sVal = "";
            }
            sVal = sVal.replace('|', '-').replace('\"', '\'');
            //Func fun = new Func();
            //fun.AddScript("alert('" + sVal.ToString() + "----');");
            switch (parseInt(type))
            {
                case 0://单选下拉框:使用原有的（class='no-render'）
                    html +="<select class='no-render' "+(isClick ? "" : "disabled='disabled'")+" "+_nval+" >";
                    html +=PCEdit.getSelectItem(val,sVal);
                    html +="</select>";
                    break;
                case 1://多选下拉框:使用原有的（class='no-render'）
                    html +="<select class='no-render' class='selMulti' tabindex='5' multiple='multiple' "+_nval+" >";
                    html +=PCEdit.getSelectItem(val,sVal);
                    html +="</select>";
                    break;
                case 2://单行文本框
                    html +="<input "+(isClick ? "" : "disabled='disabled'")+" type='text' class='input validator' "+_nval+" value='";
                    html +=sVal.replace('\"', '\'')+"' />";
                    break;
                case 3://多行文本框
                    html +="<textarea"+(isClick ? "" : "disabled='disabled'")+" cols=\"80\" rows='4' class=\"input textarea\" "+_nval+" >"+sVal+"</textarea>";
                    break;
                case 4://单选框
                    html +="<div class='le-form-element'>";
                    html +=PCEdit.getSingleListBox(val, isClick,sVal,_nval + "name='dynList["+index+"].value'");
                    html +="</div>";
                    break;
                case 5://多选框
                    html +="<div class='select-com-box le-form-element'>";
                    html +=PCEdit.getMultiListBox(val, isClick,sVal,_nval);
                    html +="</div>";
                    break;
            }
            return html;
    }
};
var LE = {
    D: document,
    SUA: new String(navigator.userAgent),
    $: function (id) { return this.D.getElementById(id); },
    isEmpty:function(s){
    	return null == s || s.length == 0;
    },
    trim: function (v, c) {
        var r = /(^\s+)|(\s+$)/g;
        if (typeof c == "string") {
            r = new RegExp("(^(" + c + "|\\s)+)|((" + c + "|\\s)+$)", "g");
        }
        return v.replace(r, '');
    },
    cwn: function (s, b) {
        var reg = /^([_A-Za-z])(\w){1,}$/;
        if (b) {
            reg = /^(\w)+$/;
        }
        return reg.test(s);
    }, //验证是否为字母或者下划线开头的字母、数字、下划线组合---b说明是否免除首字母限制
    ranStr: function () {
        var x = "123456789poiuytrewqasdfghjklmnbvcxzQWERTYUIPLKJHGFDSAZXCVBNM";
        var tmp = "";
        var l = 20;
        for (var i = 0; i < l; i++) {
            tmp += x.charAt(Math.ceil(Math.random() * 100000000) % x.length);
        }
        return tmp;
    },
    isIE: function () {
        return LE.SUA.indexOf("compatible") > -1 &&
            LE.SUA.indexOf("MSIE") > -1 &&
            LE.SUA.indexOf("Opera") == -1;
    },
    isNormalPassword: function (s) { var myreg = /((?=.*\d)(?=.*\D)|(?=.*[a-zA-Z])(?=.*[^a-zA-Z]))^.{1,32}$/; return myreg.test(s); },
    isChineseName: function (s) { var myreg = /^[\u4e00-\u9fa5]{0,12}$/; return myreg.test(s); },
    isMemberName: function (s) { var myreg = /^[a-zA-Z0-9_$]{1,}$/; return myreg.test(s); },
    isIdNumber:function(s){
        return /^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/.test(s)
            ||
            /^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}[0-9Xx]$/.test(s);
            ;
    },
    isNormalStr: function (s) { var myreg = /^[a-zA-Z0-9_-]{1,}$/; return myreg.test(s); },
    isEmail: function (s) { var myreg = /^([a-z0-9A-Z_]+[-|\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\.)+[a-zA-Z]{2,}$/; return myreg.test(s); },
    isPhone: function (s) { var reg = /^((13)|(14)|(15)|(16)|(17)|(18)|(19))(([0-9]{9})$)/; return reg.test(s); },
    isTellCall: function (s) { var reg = /(^(\d{3}(-)?)(\d{8})$)|(^(\d{4}(-)?)(\d{7})$)/; return reg.test(s); },
    isInteger: function (s) { var reg = /^-?\d+$/; return reg.test(s); },//整数
    isPosint: function (s) { var reg = /^[1-9]\d*$/; return reg.test(s); },//正整数
    isNonNegativeNum: function (s) { var reg = /^[0-9]\d*$/; return reg.test(s); },//非负整数
    isFloat_2: function (s) { var reg = /^[-+]?(([1-9]\d{0,11})|0)(\.\d{1,2})?$/; return reg.test(s); },//两位小数点的数字
    isPosFloat_2: function (s) { var reg = /^[+]?(([1-9]\d{0,11})|0)(\.\d{1,2})?$/; return reg.test(s); },//非负的两位小数点的数字
    isFloat_x: function (s,x) { var reg = new RegExp("^[-+]?(([1-9]\\d{0,11})|0)(\\.\\d{1,"+x+"})?$"); return reg.test(s); },//两位小数点的数字
    removeEvent: function (arr, eArr, evt) {
        for (var len = arr.length - 1; len >= 0; --len) {
            EventUtil.removeHandler(arr[len], eArr, evt);
        }
    },
    plus:function(a,b){
      var sum = 0;
      a = parseFloat(a);
      if(!isNaN(a)){
          sum +=a;
      }
      b = parseFloat(b);
      if(!isNaN(b)){
          sum +=b;
      }
      return sum;
    },
    toThousandth:function(num,mil){//千分位处理(四舍五入)，num：字符串/数值，mil：小数位
    	if (num == null || num.length == 0 || isNaN(num)) {
            num = 0;
        }
    	if(mil > 20){
    		mil = 20;
    	}
        return (parseFloat(num).toFixed(mil) + '').replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
    },
    toDate:function(str){
    	var date=null;
    	str=$.trim(str);
    	if(str.length == 0){
    		date = null;
    	}else{
        	try{
        		date=new Date(str);
        	}catch(e){
        		console.error(e);
        	}
    	}
    	return date;
    },
    dayBetween:function(f,s){//两个日期距离多少天：进一法s-f
        return Math.ceil((s.getTime()-f.getTime())/(1000*60*60*24));
    },
    afterDay:function(date,day){//几天之后
        date.setDate(date.getDate()+day);
        return date;
    },
	// 将毫秒数转成当前时间whh
	// 如1427943612000 转成时间为 Thu Apr 02 2015 11:00:12 GMT+0800 (中国标准时间)
	// 返回2015-04-02 11:00:12
    formateDateBymilTime:function(milTime){
    	var date = new Date(milTime);
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        if (month < 10) {
            month = "0" + month;
        }
        var day = date.getDate();
        if (day < 10) {
            day = "0" + day;
        }
        var hour = date.getHours();
        if (hour < 10) {
            hour = "0" + hour;
        }
        var mit = date.getMinutes();
        if (mit < 10) {
            mit = "0" + mit;
        }
        var sec = date.getSeconds();
        if (sec < 10) {
            sec = "0" + sec;
        }
        return year + "-" + month + "-" + day + " " + hour + ":" + mit + ":" + sec;
    },
    // 将毫秒数转成当前时间whh
    // 如1427943612000 转成时间为 Thu Apr 02 2015 11:00:12 GMT+0800 (中国标准时间)
    // 返回2015-04-02
    formateShortDateBymilTime:function(milTime,dateFormat){
    	var fm={year:"-",month:"-",day:""};
    	if(dateFormat != null){
    		$.extend(fm,dateFormat);
    	}
    	if($.trim(milTime).length==0)
    	{
    		return "";
    	}
    	
        var date = new Date(milTime);
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        if (month < 10) {
            month = "0" + month;
        }
        var day = date.getDate();
        if (day < 10) {
            day = "0" + day;
        }
        return year + fm.year + month + fm.month + day + fm.day;
    },
    jsonToParam:function(obj,pre){
        var arr=[];
        for(var i in obj){
            var _attr = obj[i];
            if(typeof _attr == "object"){
                if(!Array.isArray(_attr)){
                    arr = arr.concat(LE.jsonToParam(_attr,pre+i+"."));
                }
            }else{arr.push({name:pre+""+i,value:_attr});}
        }
        return arr;
    },
    complexJsonToSimple:function(complexObj,outOjb,pre){
        for(var i in complexObj){
            var _attr = complexObj[i];
            if(typeof _attr == "object"){
                if(!Array.isArray(_attr)){
                    LE.complexJsonToSimple(_attr,outOjb,pre+i+".")
                }
            }else{outOjb[pre+""+i]=_attr;}
        }
    },
    getParam: function (pStr, pName) {
        var s = pStr.replace("?", "?&").split('&'), v = null, c, l, f;
        var reg = /^(\w|\$){1,}(\.(\w|\$)+){0,}$/;
        if (reg.test(pName)) {
            for (var i = 0; i < s.length; ++i) {
                c = this.trim(s[i]);
                l = c.indexOf('=');
                if (l > 0) {//不能有空的=号如"&="
                    f = c.substring(0, l);
                    if (f.toLocaleLowerCase() == pName.toLocaleLowerCase()) {
                        v = c.substring(l + 1);
                        break;
                    }
                }
            }
        }
        return v;
    },
    getFormElementVal: function (singleformSelector) {
        return $(singleformSelector).map(function(){
            var _this = this;
            switch (_this.type) {
                case "radio":
                case "checkbox":
                    if($(_this).is(":checked")){
                        return $(_this).val();
                    }
                    break;
                //多选select
                case "select-multiple":
                    val = $(_this).val();
                    if(null != val){
                        val = val.join(",");
                    }
                    return val;
                default:
                    return $(_this).val();
            }
        }).get().join(",");
    },
    getFormVal: function (singleformSelector) {
        var val;
        var obj = $(singleformSelector).get(0);
        switch (obj.type) {
            //多选select
            case "select-multiple":
                val = $(obj).val();
                if(null != val){
                    val = val.join(",");
                }
                break;
            default:
                val = $(obj).val();
                break;
        }
        return val;
    },
    setFormVal: function (singleformSelector, v) {
        var obj = $(singleformSelector).get(0);
        switch (obj.type) {
            case "radio":
            case "checkbox":
                //radio/checkbox
                v = "," + v + ",";
                $(singleformSelector).each(function (i, o) {
                    if (v.indexOf(',' + $(o).val() + ',') > -1) {
                        $(o).prop("checked", true);
                    }else{
                        $(o).prop("checked", false);
                    }
                });
                break;
            //多选select
            case "select-one":
                var _sel = $(obj);
                _sel.val(v).attr("data-value",v).attr("data-id",v);
                break;
            case "select-multiple":
                if(typeof v == "string") {
                    v = v.split(",");
                }
                $(obj).val(v).attr("data-value",v).attr("data-id",v);
                break;
            default:
                $(obj).val(v);
                break;
        }
        return $(obj);
    },
    in_arr: function (arr, str) {/*判断一个字符窜是否在一个数组字符窜当中*/
        var flag = false; for (var i = 0; i < arr.length; ++i) { if (arr[i] == str) { flag = true; break; } } return flag;
    },
    in_str:function(longStr,shortStr,c){//一个以分隔符为间隔的字符串是否包含有某个短字符串
    	if(longStr == null || shortStr == null || longStr.length == 0 || shortStr.length ==0){
    		return false;
    	}
    	return (c+longStr+c).indexOf(c+shortStr+c) > -1;
    },
    min: function (ix, iy) {
        return ix < iy ? ix : iy;
    },
    max: function (ix, iy) {
        return ix > iy ? ix : iy;
    },
    getFuncByName:function(name){
        return LE.isEmpty(name) ? null : new Function("return " + name)();
    },
    //复制左边的数据到右方
    copySelection : function(leftSelector,rightSelector,isall){
    	//所有
    	var selOption = "option:selected";
    	if(isall){
    		selOption = "option";
    	}
    	
    	$(selOption,leftSelector).each(function(i,v){
    		var lpar = $(v).parent();
    		var rpar = $(rightSelector);//默认是select标签
    		//这里是有optgroup情况下
    		if(lpar.is("optgroup")){
    			lpar = lpar.clone().empty();
    			var _index = parseInt(lpar.attr("data-index"));
    			rpar = $("optgroup[data-index="+_index+"]",rightSelector);
    			//没有要加上一个
    			if(rpar.length == 0){
    				//如果right select 下无optgroup
    				var glist = $("optgroup",rightSelector);
    				var hasBigerIndex = false;//默认没有
   					//从头循环：知道有一个大于自己
   					var last;
   					glist.each(function(gi,gv){
   						var index = parseInt($(gv).attr("data-index"));
   						if(index >= _index){
   							last = gv;
   							hasBigerIndex = true;
   							return false;
   						}
   					});
   					if(hasBigerIndex){
   						rpar = $(lpar).insertBefore(last);
   					}else{
    					rpar = $(lpar).appendTo(rightSelector);
   					}
    			}
    		}
    		var hasOpBingerIndex = false;//默认没有
    		var _oIndex = parseInt($(v).attr("data-index"));
    		var oLast;
    		$("option",rpar).each(function(oi,ov){
    			var oIndex = parseInt($(ov).attr("data-index"));
    			if(oIndex >= _oIndex){
    				oLast = ov;
    				hasOpBingerIndex = true;
    				return false;
    			}
    		});
    		if(hasOpBingerIndex){
    			$(this).insertBefore(oLast);
    		}else{
        		$(this).appendTo(rpar).attr("selected",false);
    		}
    	});
    }
};



/***顶部tip提示框：可拓展***/
/*************whh 2015年4月15日09:33:10*************/
//参数 target：tip目标
//title显示标题
//content tip内容
//maxWidth 框最大宽度
function setTopTip(target, title, content, maxWidth, arrow,extClass) {
    if($(target).hasClass("has-top-tip")){
        return;
    }
    if(null == extClass){
        extClass = "";
    }
    $(target).addClass("has-top-tip");
    if (arrow == null || arrow == undefined) {
        //默认浮动在上面
        arrow = "up";
    }
    var contents = "";
    if(!LE.isEmpty(title)){
        contents +="<div class=\"tip_title\">" + title + "</div>";
    }
    contents += "<div class=\"tip_content\">" + content + "</div><div class=\"arrow " + arrow + "\"></div>";
    if (arrow == "down") {
        contents = "<div class=\"tip_content\">" + content + "</div><div class=\"tip_title\">" + title + "</div><div class=\"arrow " + arrow + "\"></div>";
    }
    var $tip = $("<div class='top-tip fade_hide "+arrow+" "+extClass+"'></div>").appendTo($(target).parent());
    $tip.html(contents);
    //var divs = $tip.children();
    //divs.eq(0).html(title);
    //divs.eq(1).html(content);
    $tip.css("max-width", maxWidth + "px");
    var wd = $tip.width();
    var ht = $tip.height();
    var tarpos = $(target).position();//position:相对位置，offset:绝对位置
    var tarWidth = $(target).width();
    var tarPaddLeft = parseInt($(target).css("padding-left"));
    if (isNaN(tarPaddLeft)) {
        tarPaddLeft = 0;
    }
    var tarmargLeft = parseInt($(target).css("margin-left"));
    if (isNaN(tarmargLeft)) {
        tarmargLeft = 0;
    }
    var tipTop = 0;
    var tipLeft = 0;
    switch(arrow){
        case "up":
            tipTop = (tarpos.top - ht - 14);
            tipLeft = (tarpos.left + (tarWidth + tarPaddLeft + tarmargLeft - wd) / 2);
            break;
        case "down":
            tipTop = tarpos.top + 14 + $(target).height();
            tipLeft = (tarpos.left + (tarWidth + tarPaddLeft + tarmargLeft - wd) / 2);
            break;
        case "right":
            tipTop = tarpos.top + ($(target).height()-ht)/2;
            tipLeft = (tarpos.left + (tarWidth + tarPaddLeft + tarmargLeft + 12));
            break;
        default:
            break;
    }
    $tip.css({ "z-index": "999999", "top": tipTop, "left":tipLeft  });
    $tip.removeClass("up down left right").addClass("fade_show "+arrow);
    var topTipOut = setTimeout(function () {
        $tip.removeClass("fade_show");
        setTimeout(function () {
            $tip.remove();
            $(target).removeClass("has-top-tip");
        }, 200);
    }, 1500);
}
/**
 * 应用程序根路径
 */
var applicationPath="";


//＊重写$.get()等方法;为了获取应用程序的
(function($){
	var _get=$.get;
	$.get=function(url,data,callback,type){
		url=applicationPath+url;
		_get(url,data,callback,type);
	};
})(jQuery);
//
//repeater
repeater.reg=/\{(\w*?)\}/g;
function repeater(source,target,list,isNotremoveItems,listItemAsp){
	var reg=repeater.reg;
	if(!isNotremoveItems){
		$(target).html("");
	}
	for(var i in list){
		var item=list[i];
		var html=$(source).html().replace(reg,function(match,key){
			if(typeof listItemAsp =="function"){
				//var a=listItemAsp(item,key);
				return listItemAsp(item,key,i);
			}
			var property=item[key];
			if(property == undefined){
                property = "";
			}
			return item[key];
		});
		$(target).append(html);
	}
}
var EventUtil = {
    addHandler: function (oTarget, type, handler) {
        if (oTarget.addEventListener) {
            oTarget.addEventListener(type, handler, false);
        } else if (oTarget.attachEvent) {
            oTarget.attachEvent("on" + type, handler);
        } else { oTarget["on" + type] = handler; }
    },
    removeHandler: function (oTarget, type, handler) {
        if (oTarget.removeEventListener) {
            oTarget.removeEventListener(type, handler, false);
        } else if (oTarget.detachEvent) { oTarget.detachEvent("on" + type, handler); }
        else { oTarget["on" + type] = null; }
    },
    getEvent: function (e) { return (e ? e : (LE.isIE() ? window.event : EventUtil.getEvent.caller.arguments[0])); },
    getTarget: function (event) { return event.target || event.srcElement; }
};

function StrBuf(str) {
    this._string_ = new Array();
    if (str != null) {
         this._string_[0] = str;
    }
    if (typeof this.append != "function") {
        StrBuf.prototype.append = function(inStr) {
             this._string_.push(inStr);
             return this;
        };
        StrBuf.prototype.concat = function(sb) {
            this._string_ =this._string_.concat(sb._string_);
            return this;
        };
        StrBuf.prototype.toString = function() {
             return this._string_.join(",");
        };
        StrBuf.prototype.toNString = function(c) {
            return this._string_.join(c);
        };
    }
}

//-------------------------lbox-----------------------------//
/************************Lether.lBox************************************/
///版权所有 欢迎转载---[淘汰mBox版本]
///中华企业网-H2(韦海鹤) 2012-7-24
///@right 2012-2099
/************************Lether.lBox***********************************/
//事件驱动器.

///
//2012-7-24 whh整合到public 增加性能开销
///
///
//2012-9-07 whh更新驱动器：主要是事件驱动器
///

/*-------------------------所有的对象都依赖于最顶层的文档树----------------------------*/
//public.js

function checkResultIsOk(result){
    //result ok return true
    if(result.code == ResultStatus.ok){
        return true;
    }else{
        var f = null;
        if(result.code == ResultStatus.no_login){
            f = function(){top.location.href = "/" + applicationPath;}
        }
        lBox.alert({delay:2000,content:result.message,success:f});
        return false;
    }
}

var LA = {
    set_wd_ht_auto: function (block, o1,o2,ewidth) {/*---宽度高度自适应---*/
        var w = parseInt($("body").width()) - 155 - ewidth - 25;
        var ifh = parseInt(document.documentElement.clientHeight) - 106;
        $(block).css({"height":ifh,"width":w});
        $(o1).css("height", ifh);
        $(o2).css("height", ifh);
        $(block).children(".viyui-tab-content").height(ifh-65);
        $(block).find(".smp-box").height((ifh-53)/2);
    },
    DocumentEvent: function (obj) {//事件委托
        var that = this;
        if (typeof this.JqEvent != "function") {
            LA.DocumentEvent.prototype.JqEvent = function(){
                $(obj).on("click","[data-fc]",function(e){
                    var t = this;
                    var fc = $(t).attr("data-fc");
                    if (typeof fc == "string") {
                        var func = (new Function("return " + fc))();
                        func.call(t);
                    }
                });
            }
        }
    },
    Choosecolor: function (div, func) {
        this.ColorHex = new Array('00', '33', '66', '99', 'CC', 'FF');
        this.SpColorHex = new Array('FF0000', '00FF00', '0000FF', 'FFFF00', '00FFFF', 'FF00FF');
        this.current = null;
        LA.Choosecolor.prototype.initcolor = function () {
            var colorTable = '';
            for (var i = 0; i < 2; i++) {
                for (var j = 0; j < 6; j++) {
                    colorTable = colorTable + '<tr height=15>';
                    colorTable = colorTable + '<td width=15 style="background-color:#000000">';
                    if (i == 0) {
                        colorTable = colorTable + '<td width=15 style="cursor:pointer;background-color:#' + this.ColorHex[j] + this.ColorHex[j] + this.ColorHex[j] + '" onclick="LA.Choosecolor.prototype.doclick(this)">';
                    }
                    else {
                        colorTable = colorTable + '<td width=15 style="cursor:pointer;background-color:#' + this.SpColorHex[j] + '" onclick="LA.Choosecolor.prototype.doclick(this)">';
                    }
                    colorTable = colorTable + '<td width=15 style="background-color:#000000">';
                    for (var k = 0; k < 3; k++) {
                        for (var l = 0; l < 6; l++) {
                            colorTable = colorTable + '<td width=15 style="cursor:pointer;background-color:#' + this.ColorHex[k + i * 3] + this.ColorHex[l] + this.ColorHex[j] + '" onclick="LA.Choosecolor.prototype.doclick(this)">';
                        }
                    }
                }
            }
            colorTable = '<table border="0" cellspacing="0" cellpadding="0" style="border:1px #000000 solid;border-bottom:none;border-collapse: collapse;width:337px;" bordercolor="000000">'
            + '<tr height=20><td colspan=21 bgcolor=#ffffff style="font:12px tahoma;padding-left:2px;">'
                     + '<span style="float:right;padding-right:3px;cursor:pointer;" id="cloce_color_id" onclick="LA.Choosecolor.prototype.colorclose();">×关闭</span>'
            + '</td></table>'
            + '<table border="1" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="000000" style="cursor:pointer;">'
            + colorTable + '</table>';
            $(div).html(colorTable);
            $(div).hover(null, this.colorclose);
        };
        LA.Choosecolor.prototype.doclick = function (obj) {
            var sColor = $(obj).css("backgroundColor").replace("#", "");
            //change decimal to hex
            if (sColor.length > 7) {
                var sTempString = sColor.substring(4, sColor.length - 1).split(',');
                var sc0 = parseInt(sTempString[0]).toString(16).toString();
                if (sc0.length == 1) {
                    sc0 = "0" + sc0;
                }
                var sc1 = parseInt(sTempString[1]).toString(16).toString();
                if (sc1.length == 1) {
                    sc1 = "0" + sc1;
                }
                var sc2 = parseInt(sTempString[2]).toString(16).toString();
                if (sc2.length == 1) {
                    sc2 = "0" + sc2;
                }
                sColor = sc0 + sc1 + sc2;
            }
            if (typeof func == "function") { func(sColor); }
            $(div).hide();
        };
        LA.Choosecolor.prototype.colorclose = function () {
            $(div).hide();
        };
        LA.Choosecolor.prototype.coloropen = function (btn, evt) {
            var currentX = parseInt(evt.clientX) - 420;
            var currentY = parseInt(evt.clientY) - 90;
            $(div).css("left", currentX + "px");
            $(div).css("top", currentY + "px");
            $(div).show();
        };
    }
};
var LET = {//内部函数专用
    Tab: function (tabBoxSelector) {//tabpanel
        if (typeof this.init != "function") {
            LET.Tab.prototype.init = function(tabBoxSelector,initSelector) {
                if(initSelector == null){
                    initSelector = 0;
                }
                $(tabBoxSelector).each(function(){
                    var controlBoxSelector = $(this).attr("data-control");
                    $(this).on("click",".tab,.close-tab",function(){
                        var $controlItem = $(controlBoxSelector).children();
                        if($(this).is(".close-tab")){
                            var pr = $(this).closest(".tab"),pv = pr.prev(),index = pr.index();
                            pr.remove();
                            $controlItem.eq(index).remove();
                            if(pr.is(".current")){
                                pv.trigger("click");
                            }
                            return false;
                        }else if(!$(this).hasClass("current")){
                            $(this).addClass("current").siblings().removeClass("current");
                            $controlItem.hide();
                            $controlItem.eq($(this).index()).show();
                            var evt = $(this).attr("data-event");
                            if(null != evt){
                                window[evt]();
                            }
                        }
                    }).children().eq(initSelector).trigger("click");
                });
            };
        }
        this.init(tabBoxSelector);
    },
    scrollTab:function(tabBoxSelector,initSelector){
        var _stp = this;
        if (typeof this.init != "function") {
            LET.scrollTab.prototype.init = function(tabBoxSelector) {
                $(tabBoxSelector).each(function(){
                    var _t = $(this),$control = $($(_t).attr("data-control")),p = $control.parent(),
                        max_scroll_top = $control.height() - $control.parent().height();
                    $(this).on("click",".tab",function(){
                        var _tab = $(this),_index = _tab.index(),
                            item = $control.children().eq(_index),current_scroll_top = p.scrollTop(),
                            ptop = item.position().top;
                        p.animate({scrollTop:ptop},200,function(){
                            _stp.setCurrent(_tab);
                        });
                    });
                    p.on("scroll",function(){
                        var stop = $(this).scrollTop();
                        $control.children().each(function(i,v){
                            var _this = $(this);
                            var pt = _this.position().top;
                            if(stop <= pt + parseInt(_this.height()) - parseInt(_this.css("margin-bottom")) - parseInt(_this.css("margin-top"))){
                                _stp.setCurrent(_t.children(".tab").eq(i));
                                return false;
                            }
                        });
                    });
                });
            };
            LET.scrollTab.prototype.setCurrent = function(_tab){
                if(!_tab.hasClass("current")){
                    _tab.addClass("current").siblings().removeClass("current");
                    var evt = _tab.attr("data-event");
                    if(null != evt){
                        window[evt]();
                    }
                }
            };
        }
        _stp.init(tabBoxSelector);
        if(initSelector == null){
            initSelector = 0;
        }
        $(tabBoxSelector).each(function(){
            var item = $(this).children().eq(initSelector);
            item.trigger("click");
        });
    },
    addEditHeight: function (e, einp, v,w) {//编辑器高度设置
        var h = parseInt($(einp).val()) + v;
        if (h > 2000 || h < 500) {
            lBox.alert({content:'编辑器高度已达极限'});
        } else {
            e.resize(w, h);
            $(einp).val(h);
        }
    },
    openEvt: true, //事件流---单击某个提交按钮之后关闭
    dataBind:function(boxSelector,entity){//页面初始化绑定
        var box = $(boxSelector);
    	for(var pro in entity){
    		var proVal=entity[pro];
    		var target=$("#"+pro+",[data-bind-id='"+pro+"']",box);
    		if(target.length == 0){
    			continue;
    		}
    		var tag=target.prop("tagName").toString().toLowerCase();
    		var fc = LE.getFuncByName(target.attr("data-bind-function"));
    		if(tag == "input" || tag == "select" || tag =="textarea"){
                if(null != fc) {
                    proVal = fc(entity,proVal,0,target,true);
                }
                LE.setFormVal(target, proVal);
                target.attr("data-old-value",proVal);

    		}else{
    			target.html(null == fc ? proVal : (fc(entity,proVal,0,target))+"");
    		}
    	}

    },
    checkEditItem:function(formSelector){
      $(formSelector).on("change","input,select,textarea",function(){
          var ov = $(this).attr("data-old-value");
          if(null == ov){
              ov = "";
          }
          var cv = LE.getFormVal(this);
          if(ov != cv){
              $(this).addClass("had-edit");
              return;
              if(!$(this).is(":visible")){
                  return;
              }
              if($(this).next(".had-edit").length == 0){
                  $(this).after("<span style='top:"+($(this).height()-8)+"px;left:"+($(this).width()-5)+"px;' class='had-edit'></span>");
              }
          }else{
              $(this).removeClass("had-edit");//.next(".had-edit").remove();
          }
      });
    },
    dataBindByName:function(boxSelector,entity){
      var serializeObj = {};
        LE.complexJsonToSimple(entity,serializeObj,"");
        var box = $(boxSelector);
        for(var pro in serializeObj){
            var proVal=serializeObj[pro];
            var target=$("[name='"+pro+"']",box);
            if(target.length == 0){
                continue;
            }
            var tag=target.prop("tagName").toString().toLowerCase();
            var fc = LE.getFuncByName(target.attr("data-bind-function"));
            if(tag == "input" || tag == "select" || tag =="textarea" || tag == "radio"){
                if(null != fc) {
                    proVal = fc(entity,proVal,0,target,true);
                }
                LE.setFormVal(target, proVal);
            }else{
                target.html(null == fc ? proVal : (fc(entity,proVal,0,target))+"");
            }
        }

    },
    checkAmount:function(target,value){
        if(value < 0){
            return "价格不能小于0哟.";
        }
        return ResultStatus.ok;
    },
    checkgtZeroAmount:function(target,value){
        if(value <=0){
            return "充值金额必须大于0哟.";
        }
        return ResultStatus.ok;
    },
    reqAmount:function(value){
       if(!LE.isEmpty(value)){
           value *=MONEY_DIGIT;
           if(MONEY_DIGIT >= 100){
               value = Math.round(value);
           }
       }
       return value;
    },
    //循环校验某个.validator 输入项是否符合条件
    checkForm:function(form_seletor){
        var form = $(form_seletor);
        if(form.length == 0){
            lBox.alert({content:"执行校验的时候找不到form！"});
            return false;
        }
        var flag = true;
        form.find(".validator").each(function(){
            if (!LET.checkFormItem.call(this) && flag) {
                $(this).focus();
                flag = false;
            }
        });
        return flag;
    },
    //初始化form表单选项
    //tipType：提示文字显示方式：默认在inp后面
    intCheckFormTip: function (boxSelector) {
        $(".validator",boxSelector).each(function () {
            var $this = $(this);
            var tipType=$this.attr("data-tip-type");
            if(tipType == "1"){
                return true;
            }
            var tip = $this.attr("data-tip");
            var rules=$this.attr("data-rules");
            if (tip == undefined) {
                tip = "";
            }
            tip += "&nbsp;";

            var $tip_box = $this.parent().next(".help-tip");
            if($tip_box.length != 0){
                return true;
            }
            if ($tip_box.length == 0) {
                $tip_box = document.createElement("div");
                $tip_box.className = "help-tip";
                $this.parent().after($tip_box);
                $tip_box = $this.parent().next(".help-tip");
            }
            if (null != rules && rules.indexOf("required") > -1) {
                tip += "<span class='help-option'>[必填]</span>";
                $tip_box.addClass("tip-mustin");
            } else {
                tip += "<span class='addmsg_option'>[选填]</span>";
                $tip_box.addClass("tip-question");
            }
        	$tip_box.html("<i class='icon-info-sign'></i>"+tip);
            $this.attr("data-tip", tip);
        });
        $(boxSelector).on("blur",".validator",function(){
            LET.checkFormItem.call($(this));
        }).on("focus",".validator,.instead-show",function(){
            var $tip = $(this).parent().next();
            if($tip.length == 0 || !$tip.is(".help-tip")){
                return;
            }
            var tip_txt = $(this).attr("data-tip");
            if (tip_txt == undefined) {
                tip_txt = $tip.html();
                $tip.attr(tip_txt);
            }
            if ($tip.hasClass("error-tip")) {
                $tip.removeClass("error-tip");
            }
            $tip.html(tip_txt);
        }).on("keyup",".validator,.instead-show",function(){
            var fc = LE.getFuncByName($(this).attr("data-bind-function"));
            if(null != fc){
                var v = LE.getFormVal(this);
                fc({},v,0,this,false);
            }
        });
    },
    //tipType:{1:setToptip('top')}
    checkFormItem: function (prevValue) {
        var $this = $(this);
        var rules = $this.attr("data-rules");
        var val;
        if(null == prevValue){
            val = $.trim(LE.getFormVal($this));
        }else{
            val = prevValue;
        }
        var val_len = val.length;
        var flag = true;
        var tip = $this.attr("data-tip");
        if (undefined != rules) {
            rules = rules.split(',');
            for (var i = 0; i < rules.length; ++i) {
                var v = rules[i];
                if (v == "required") {
                    if(val_len==0){
                        tip = "[必填]";
                        flag = false;
                        break;
                    }
                }
                if (v == "phone" && (val_len>0 && !LE.isPhone(val))) {
                    tip = "请输入正确的手机号.";
                    flag = false;
                    break;
                }
                if (v == "email" && (val_len>0 && !LE.isEmail(val))) {
                    tip = "请输入正确的email.";
                    flag = false;
                    break;
                }
                if (v == "decimal_2" && (val_len > 0 && !LE.isFloat_2(val))) {
                    tip = "请输入两位小数的数字.";
                    flag = false;
                    break;
                }
                if (v == "pos_decimal_2" && (val_len > 0 && !LE.isPosFloat_2(val))) {
                    tip = "请输入非负两位小数的数字.";
                    flag = false;
                    break;
                }
                //如果是x则设置位数
                if (v == "decimal_x" && val_len > 0){
                	var x = $this.attr("data-decimal");
                	if(!LE.isFloat_x(val,x)){
                        tip = "请输入最多"+x+"位小数的数字.";
                        flag = false;
                		break;
                	}
                }
                if (v == "call" && (val_len > 0 && !LE.isTellCall(val))) {
                    tip = "请输入正确的电话号码.";
                    flag = false;
                    break;
                }
                if (v == "tel" && (val_len>0 && !LE.isPhone(val) && !LE.isTellCall(val))) {
                    tip = "请输入正确的手机/电话号码.";
                    flag = false;
                    break;
                }
                if (v == "number" && (val_len > 0 && !LE.isPosint(val))) {
                    tip = "请输入正确的正整数.";
                    flag = false;
                    break;
                }
                if (v == "membername" && (val_len > 0 && !LE.isMemberName(val))) {
                    tip = "请输入正确的名称格式.";
                    flag = false;
                    break;
                }
                if(v == "nonnegtive_int" && (val_len > 0 && !LE.isNonNegativeNum(val))){
                    tip = "请输入正确的非负整数.";
                    flag = false;
                    break;
                }
                if(v == "integer" && (val_len > 0 && !LE.isInteger(val))){
                    tip = "请输入正确的整数.";
                    flag = false;
                    break;
                }
                if(v == "normal_str" && (val_len > 0 && !LE.isNormalStr(val))){
                    tip = "请输入a至z、A至Z、0至9、_、- 这些字符组合";
                    flag = false;
                    break;
                }
                if(v == "password" && (val_len > 0 && !LE.isNormalPassword(val))){
                    tip = "请输入长度8~18位,数字、字母、字符至少包含两种";
                    flag = false;
                    break;
                }
                if(v == "chinesname" && (val_len > 0 && !LE.isChineseName(val))){
                    tip = "请输入0-12长度的中文字符串";
                    flag = false;
                    break;
                }
                if(v == "idnumber" && (val_len > 0 && !LE.isIdNumber(val))){
                    tip = "请输入15/18位长度的身份证件号码";
                    flag = false;
                    break;
                }
            }
        }
        //使用正则,使用正则的话 必须指定tip，否则找不到提示语
        if(flag) {
            var regex = $this.attr("data-regex");
            if (null != regex && !new RegExp(regex).test(val)) {
                flag = false;
            }
        }

        //判断是否有其他方法，其他方法如果返回result.ok则跳过否则tip为返回值
        if(flag) {
            var fc = LE.getFuncByName($this.attr("data-extra-rules"));
            if(null != fc){
                var r = fc($this,val);
                if (r != ResultStatus.ok) {
                    flag = false;
                    tip = r;
                }
            }
        }
        //显示样式
        if($this.css("display") == "none"){
            $this = $this.siblings("input.instead-show");
        }
        var $tip = $this.parent().next(".help-tip");
        var tipType=$this.attr("data-tip-type");
        if(null == tipType){
            tipType = 0;//默认放在input parent 后面
        }
        if(flag){
            //检查成功
            $this.removeClass("error-item");
            if(tipType == 0){
                $tip.html("<i class='icon-ok-sign'></i>OK").removeClass("error-tip").addClass("ok-tip");
            }
        }else{
            $this.addClass("error-item");
            switch(tipType){
                case "1":
                    setTopTip($this, "", tip, 300,"right","error-tip");
                    break;
                default:
                    $tip.html("<i class='icon-exclamation-sign'></i>"+tip).removeClass("ok-tip").addClass("error-tip");
                    break;
            }
        }
        return flag;
    }
};
/*****************************************tree********************************************/


/*****************************************树Tree.js**************************************************/
function ViyTree(p){
    this._p = {
        data:[],
        boxSelector:null,
        checkEvent:true
    }
    $.extend(this._p,p);
    if (typeof ViyTree._initialized == "undefined") {
        ViyTree._initialized = true;
        ViyTree.prototype.addNode = function(arr){
            // if(arr.length == 0){
            //     return "";
            // }
            var ul = $("<ul></ul>");
            for(var i in arr){
                var node = arr[i];
                var li = $("<li></li>");
                var expandBtn = $("<i class='icon'></i>");
                var ckbBtn = $("<i class='ckb-btn'></i>");
                if(node.checked){
                    li.addClass("checked");
                    if(node.children.length == node.children.filter(function(x){return x.checked;}).length){
                        li.addClass("checked-all");
                    }
                }
                var text = $("<a href='#'>"+node.text+"</a>")
                if(node.children.length > 0){
                    li.addClass("has-son");
                    if(node.expand){
                        li.addClass("expand");
                        expandBtn.addClass("icon-minus-sign");
                    }else{
                        expandBtn.addClass("icon-plus-sign");
                    }
                }
                li.append(expandBtn).append(text).append("<input type='hidden' value='"+node.value+"' />");
                li.append(this.addNode(node.children)).append(ckbBtn);

                ul.append(li);
            }
            return ul;
        }
        ViyTree.prototype.getCheckedValue = function(){
            return $(".checked",this._p.boxSelector).map(function(){
                return $(this).children("input").val();
            }).get().join(",");
        }
        ViyTree.prototype.initTree = function(){
            var _t = this;
            $(this._p.boxSelector).append(this.addNode(this._p.data)).addClass("viy-tree-box")
                .on("click",".icon-plus-sign,.icon-minus-sign,.ckb-btn",function(){
                    var _this = $(this);
                    var li = _this.parent();
                    if(_this.is(".icon-plus-sign")){
                        _this.removeClass("icon-plus-sign").addClass("icon-minus-sign");
                        li.addClass("expand");
                    }else if(_this.is(".icon-minus-sign")){
                        _this.removeClass("icon-minus-sign").addClass("icon-plus-sign");
                        li.removeClass("expand");
                    }else if(_t._p.checkEvent && _this.is(".ckb-btn")){
                        var checked = li.is(".checked");
                        if(checked){
                            if(li.hasClass("checked-all")){
                                //子
                                li.find("li").add(li).removeClass("checked checked-all");
                                //父
                                li.parents(".checked-all").removeClass("checked-all");
                            }else{
                                //子
                                li.find("li").add(li).addClass("checked checked-all");
                                //父
                                li = li.parent().parent(".has-son");
                                while(li.length){
                                    if(li.children("ul").children(":not(.checked-all)").length == 0){
                                        li.addClass("checked-all");
                                    }
                                    li = li.parent().parent(".has-son");
                                }

                            }

                        }else{
                            //子

                            //父
                            while(li.length){
                                li.addClass("checked");
                                if(li.children("ul").children(":not(.checked-all)").length == 0){
                                    li.addClass("checked-all");
                                }
                                li = li.parent().parent(".has-son");
                            }
                        }
                    }
                });
        }
    }
    this.initTree();
}

/******************************************树Tree.js完毕**************************************************/

function getContentWidth(ori){
    var countSpan = $("#countSpan");
    if(countSpan.length == 0){
        countSpan = $("<span id='countSpan'></span>");
        countSpan.appendTo("body");
    }
    countSpan.css({"visibility":"hidden","white-space":"nowrap","font-size":+$(ori).css("font-size")});
    countSpan.html(ori.html());
    return countSpan.visibleWidth();
}

//------------------------select box重绘-----------------------------//

LET.renderSelection = function(item,_p){
    var _sel = $(item);
    var hl = new StrBuf("");
    var box = _sel.siblings("dl");
    var lst = _p.dataList;
    if(!_p.treeFields) {
        var index = 0;
        _sel.find("*").each(function(){
            if($(this).is("optgroup")){
                hl.append("<dt>").append($(this).attr("label")).append("</dt>");
            }else if($(this).is("option")){
                hl.append("<dd data-index='").append(index).append("' data-value='").append($(this).val()).append("'>").append($(this).text()).append("</dd>");
                ++index;
            }
        });
    }else{
        //tree
        var pidex = -1;
        var treeFields = _p.treeFields;
        for(var i = 0,l=lst.length;i<l;++i){
            var it = lst[i];
            hl.append("<dd class='list-row ");
            pidex = it["parentIndex"];
            if(pidex > -1 && !lst[pidex].expand){
                hl.append("tree-close");
            }
            hl.append("' data-index='").append(i).append("'>");
            hl.concat(ViyGrid.getListDivValue(it, it[treeFields.textField], i, {},treeFields.textField,treeFields)).append("</dd>");
        }
    }
    box.html(hl.toNString(""));
}
LET.setRenderSelectValue = function(item,isSetValue){
    var _sel = $(item);
    var _p = _sel.data("param");
    var fields = _p.treeFields || _p.listFields;
    var text = $(_p.selectedItems).map(function(i,v){
        return this[fields.textField];
    }).get().join(",");
    var val = $(_p.selectedItems).map(function(i,v){return this[fields.valueField];}).get();
    _sel.siblings("input").val(text);
    if(isSetValue) {
        LE.setFormVal(_sel, val);
    }
    _sel.trigger("change");
}
LET.setRenderSelectedForcily = function(item,id,value,text){
    var p = $(item).data("param");
    var s = {};
    s[p.listFields.idField] = id;
    s[p.listFields.valueField] = value;
    s[p.listFields.textField] = text;
    p.selectedItems = [s];
    LET.setRenderSelectValue(item,true);
}
LET.loadSelection = function(item,param){
    var _sel = $(item);
    if(null == param){
        lBox.alert({content:"param值不能为空！"});
        return false;
    }
    if(_sel.attr("data-enumber") != null){
        var sb = new StrBuf("");
        var lst = eval(_sel.attr("data-enumber"));
        if(param["defaultItem"] != null){
            for(var i in param["defaultItem"]){
                sb.append("<option data-id='")
                    .append(param["defaultItem"][i]).append("' value='")
                    .append(param["defaultItem"][i]).append("'>");
                sb.append(i);
                sb.append("</option>");
            }
        }
        for(var i in lst){
            sb.append("<option data-id='")
                .append(lst[i]).append("' value='")
                .append(lst[i]).append("'>");
            sb.append(i);
            sb.append("</option>");
        }
        _sel.append(sb.toNString(""));
        return;
    }
    if(null == param["url"]){
        lBox.alert({content:"当data-list为空的情况下,param.url值不能为空！"});
        return false;
    }
    $.ajax({
        url:param.url,
        async:param.sync,
        data:param.queryFields,
        type:"post",
        success:function(d) {
            if (!checkResultIsOk(d)) {
                return;
            }
            var lst = d.content.list;
            var fields = param.treeFields || param.listFields;
            if(param.treeFields) {
                var olst=[];
                listForTreeArray(lst, param.treeFields,olst);
                lst = olst;
            }
            var hasDefaultItem;
            if(param["defaultItem"] != null){
                lst = [param.defaultItem].concat(lst);
                hasDefaultItem = true;
            }
            var sb = new StrBuf("");
            for(var i = 0,it;i<lst.length;++i){
                it = lst[i];
                sb.append("<option data-id='")
                    .append(it[fields.idField]).append("' value='")
                    .append(it[fields.valueField]).append("'>");
                if(null != fields.fmtFunc && (i > 0 || !hasDefaultItem)){
                    it[fields.textField] = window[fields.fmtFunc](it);
                }
                sb.append(it[fields.textField]);
                sb.append("</option>");
            }
            param.dataList = lst;
            _sel.html(sb.toNString(""));
            if(param["render"]){
                LET.renderSelection(item,param);
            }
        }
    });
    // if(LET.loadSelectionLaz != null){
    //     clearTimeout(LET.loadSelectionLaz);
    // }
    // LET.loadSelectionLaz = setTimeout(function(){
    //     $.ajax({
    //         url:param.url,
    //         async:param.sync,
    //         data:param.queryFields,
    //         type:"post",
    //         success:function(d) {
    //             if (!checkResultIsOk(d)) {
    //                 return;
    //             }
    //             var lst = d.content.list;
    //             var fields = param.treeFields || param.listFields;
    //             if(param.treeFields) {
    //                 var olst=[];
    //                 listForTreeArray(lst, param.treeFields,olst);
    //                 lst = olst;
    //             }
    //             var hasDefaultItem;
    //             if(param["defaultItem"] != null){
    //                 lst = [param.defaultItem].concat(lst);
    //                 hasDefaultItem = true;
    //             }
    //             var sb = new StrBuf("");
    //             for(var i = 0,it;i<lst.length;++i){
    //                 it = lst[i];
    //                 sb.append("<option data-id='")
    //                     .append(it[fields.idField]).append("' value='")
    //                     .append(it[fields.valueField]).append("'>");
    //                 if(null != fields.fmtFunc && (i > 0 || !hasDefaultItem)){
    //                     it[fields.textField] = window[fields.fmtFunc](it);
    //                 }
    //                 sb.append(it[fields.textField]);
    //                 sb.append("</option>");
    //             }
    //             param.dataList = lst;
    //             _sel.html(sb.toNString(""));
    //             if(param["render"]){
    //                 LET.renderSelection(item,param);
    //             }
    //         }
    //     });
    // },1000);
    return;

};
LET.loadSelectionLaz=null;
LET.renderForm=function(obj){
    var form = $(obj.selector);
    for(var i = 0;i<form.length;++i){
        var es = form.find("input,select,textarea");
        for(var x=0;x<es.length;++x){
            var _e = es.get(x);
            //select
            var t = _e.type;
            var mult = false;
            switch(t){
                case "text":
                    break;
                case "hidden":
                    if(!$(_e).is(".switch")){
                        break;
                    }
                    var box=$(_e).closest(".le-form-element");
                    var vs = $(_e).attr("data-value").split("|");
                    var ts = $(_e).attr("data-text").split("|");
                    var _s = "";
                    var _t = "";
                    if(vs[0] == $(_e).val()){
                        _s = "switch-btn-checked";
                        _t = ts[0];
                    }else{
                        _t = ts[1];
                    }
                    var hl = new StrBuf("<label class='switch-btn "+_s+"'><i>"+_t+"</i></label>");
                    $(_e).after(hl.toNString(""));
                    if(!$(box).is("[data-event]")){
                        $(box).on("click",".switch-btn",function(){
                           var inp = $(this).siblings("input");
                           var vs = inp.attr("data-value").split("|");
                           var ts = inp.attr("data-text").split("|");
                           var ckd = $(this).hasClass("switch-btn-checked");
                           $(this).children("i").html(ckd ? ts[1] : ts[0]);
                           inp.val(ckd ? vs[1]:vs[0]).trigger("change");
                           $(this).toggleClass("switch-btn-checked");
                        });
                        $(box).attr("data-event",true);
                    }
                    break;
                case "checkbox":
                case "radio":
                    break;
                case "select-multiple":
                    mult = true;
                case "select":
                case "select-one":
                    //如果有data-url ,则调用ajax方法进行
                    var $item = $(_e);
                    var param={
                        sync:false,
                        render:false,
                        treeFields:false,
                        listFields:{
                            idField:"id",
                            valueField:"id",
                            textField:"name",
                            fmtFunc:null
                        },
                        queryFields:{},
                        dataList:[],
                        selectedItems:[],
                        lazy:false,
                        placeHolder:"请选择",
                        readonly:true//默认不可编辑
                    };
                    //加载section

                    //加入有data-load
                    var dataLoad = $item.attr("data-load");
                    var isRenderSelector = !$item.hasClass("no-render");
                    if(null != dataLoad){
                        if($item.parent().hasClass("tree-render")){
                            param.treeFields = $.extend({},ViyGrid.defaultTreeFields);
                        }
                        $.extend(true,param,$.parseJSON(dataLoad));
                        if(!param.lazy) {
                            //判断自己是否有
                            LET.loadSelection($item, param);
                        }
                    }else if(isRenderSelector){//否则将option转化为dataList,暂时不管树初始化
                        if(param.treeFields){
                            lBox.alert({content:"暂不支持树结构初始化形式"});
                            return;
                        }
                        param.dataList=[];
                        param.selectedItems=[];
                        $item.find("option").each(function(i,v){
                            var dv = {};
                            dv[param.listFields.idField] = $(this).attr("data-id");
                            dv[param.listFields.valueField] = $(this).attr("value");
                            dv[param.listFields.textField] = $(this).text();
                            param.dataList.push(dv);
                        });
                    }

                    //判断是否有data-text,data-id,data-value,有的话一并设置值
                    var defaultValue = $item.attr("data-value");
                    if(null != defaultValue){
                        var ff = param.treeFields || param.listFields;
                        LE.setFormVal($item,defaultValue);
                        var did = $item.attr("data-id");
                        if(!mult){
                            var s = {};
                            s[ff.idField] = did;
                            s[ff.valueField] = defaultValue;
                            var sd = $item.find("option:selected");
                            s[ff.textField] = sd.text();
                            s["dataIndex"] = sd.index();
                            param.selectedItems.push(s);
                        }else{
                            var did = did.split(",");
                            $item.find("option:selected").each(function (i,v) {
                                var s = {};
                                s[ff.idField] = did[i];
                                s[ff.valueField] = $(this).val();
                                s[ff.textField] = $(this).text();
                                s["dataIndex"] = $(this).index();
                                param.selectedItems.push(s);
                            });
                        }
                    }
                    if(!isRenderSelector){
                        continue;
                    }
                    var box = $item.parent();
                    var hl = new StrBuf("<input ");
                    // if(null != param["defaultItem"]){
                    //     var f = param.treeFields || param.listFields;
                    //     hl.append("placeHolder='").append(param["defaultItem"][f.textField]).append("' ");
                    // }
                    hl.append("placeHolder='").append(param.placeHolder).append("' ");
                    hl.append(" class='instead-show' type='text' ").append(param.readonly?"readonly='readonly'":"").append(" class='input input-in-text' /><i class=\"icon-caret-down\"></i>");

                    hl.append("<dl "+(param.treeFields?"data-treebox='dl'":"")+" class='sel-text-list selnone'></dl>");
                    box.append(hl.toNString(""));
                    //初始化完毕

                    //并且渲染
                    LET.renderSelection($item,param);
                    //渲染完毕
                    $item.data("param",param);
                    LET.setRenderSelectValue($item,false);
                    //事件
                    if(!param.readonly){
                        $(box).on("keyup paste","input",function(e){
                            var $this = $(this);
                            var _sel = $this.siblings("select");
                            var v = LE.trim($this.val());
                            if(e.type == "paste"){
                                if(window.clipboardData && window.clipboardData.getData) { // IE
                                    v = window.clipboardData.getData('Text');
                                } else {
                                    v = e.originalEvent.clipboardData.getData('Text'); //e.clipboardData.getData('text/plain');
                                }
                            }
                            var ov = $this.attr("data-oval");
                            if(null == ov){
                                ov = "";
                            }

                            var _b = $(this).closest(".le-form-element");
                            var ul = $(_b).find("dl");
                            ul.removeClass("selnone");
                            _b.addClass("select-in");
                            if(v == ov){
                                return;
                            }
                            //
                            if(top.lazyQuery != null){
                                clearTimeout(top.lazyQuery);
                            }
                            top.lazyQuery = setTimeout(function(){
                                $this.attr("data-oval",v);
                                var opr = _sel.data("param");
                                if(null != opr["queryFields"]){
                                    for(var i in opr.queryFields){
                                        opr.queryFields[i] = v;
                                    }
                                }
                                opr["render"] = true;
                                LET.loadSelection(_sel, opr);
                            },600);
                        });
                    }
                    //追加事件
                    $(box).on("click","input,i,dd,span",function(ee){
                        var _this = $(this);
                        var _b = _this.closest(".le-form-element");
                        var ul = _b.find("dl");
                        var _sel = _b.children("select");
                        var _p = _sel.data("param");
                        if(_this.is("[data-value]")){
                            //单项选择
                            var index =0;
                            if(!_p.treeFields){
                                index = parseInt(_this.attr("data-index"));
                            }else{
                                index = parseInt(_this.closest(".list-row").attr("data-index"));
                            }
                            var f = _p.treeFields || _p.listFields;

                            _p.selectedItems =[];
                            var el = _p.dataList[index];
                            el["dataIndex"] = index;
                            _p.selectedItems.push(el);
                            LET.setRenderSelectValue(_sel,true);
                            //判断是否有校验
                            if(_sel.is(".validator") && !LET.checkFormItem.call(_sel)){
                                return;
                            }

                            ul.addClass("selnone");
                            _b.removeClass("select-in");
                            if(_sel.is("[data-change]")){
                                var func = (new Function("return " + _sel.attr("data-change")))();
                                func();
                            }
                        }else if(_this.is(".tree-icon:not(.no-children)")){
                            ViyGrid.setListExpandTreeEvent.call(_this,_p.dataList,_p.treeFields,null);
                        }else if(_this.is("input,i")){
                            ul.removeClass("selnone");
                            _b.addClass("select-in");
                            ul.find("[data-value='"+LE.getFormVal(_sel)+"']").closest("dd").addClass("selected").siblings().removeClass("selected");
                        }
                        //input show,
                        //icon show,
                        //is data-value then close else show
                    }).hover(null,function(){
                        $(this).removeClass("select-in").find("dl").addClass("selnone");
                    });

                    break;
                default:
                    break;
            }
            //radio
        }
    }
}
LET.render=function(arr){
    for(var i = 0;i<arr.length;++i){
        if(arr[i]["element"] == "form"){
            var r =$.extend({selector:".viy-form"},arr[i]);
            LET.renderForm(r);
        }
    }
}
//该方法不对外
function __findOffp(inList,outList,treeFields,parentId,level){
    if(null == inList || inList.length == 0){
        return false;
    }
    var newInList = [];
    var currentArr=[];
    for(var i = 0;i<inList.length;++i){
        var item = inList[i];
        item["expand"] = treeFields.expand;
        if(item[treeFields.parentField] == parentId){
            item["parentIndex"] = outList.length-1;
            currentArr.push(item);
        }else{
            newInList.push(item);
        }
    }
    for(var i=0;i<currentArr.length;++i){
        currentArr[i]["level"] = level;
        outList.push(currentArr[i]);
        var hasChildren = __findOffp(newInList,outList,treeFields,currentArr[i][treeFields.idField],level+1);
        currentArr[i]["hasChildren"] = hasChildren || currentArr[i]["hasSon"];
    }
    return currentArr.length > 0;
}
function listForTreeArray(list,treeFields,outList){
    __findOffp(list,outList,treeFields,treeFields.parentId,treeFields.topLevel);
}
//

//------------------------规格组合 new---------------------------------//
function generSpecCombiList(specs,rowCount,pro,pmpList){
    var list = new Array(rowCount);
    var quotient,sl = specs.length,spt,pindex;
    for(var i = 0;i<rowCount;++i){
        var item = {
            entity:{
                id:'0',
                proCode:pro.proCode,//产品编号
                salePrice:pro.salePrice,//销售价
                stock:pro.stock,//产品库存
                specIds:'',//规格id组合
                specNames:'',//规格名字组合
                specTypes:'',//规格显示类型组合
                specParIds:'',//规格参数值id组合
                specParVals:''//规格参数值组合
            },
            pmpList:pmpList
        }
        quotient = i;
        for(var j = 0;j<sl;++j){
            var pars = specs[j].pars;
            pindex = quotient%pars.length;
            spt= j != sl -1 ? '|' : '';
            item.entity.specIds += specs[j].id + spt;
            item.entity.specNames += specs[j].name + spt;
            item.entity.specTypes += specs[j].showType + spt;
            item.entity.specParIds += pars[pindex].paramId + spt;
            item.entity.specParVals += pars[pindex].paramValue + spt;
            quotient = Math.floor(quotient/pars.length);
        }
        item.entity.proCode = pro.proCode + "-" + item.entity.specParIds;//
        list[i] = item;
    }
    return list;
}
//------------------------规格组合 old----------------------------------//
function getSpecParam(a_proNo,a_stock,a_uPrice,a_mPrice,specArr,row){
	var dt=[];
	//循环每一行，给每一列添加属性
	for(var i=0;i<row;++i){
		dt.push({
			specParIds : "",
			specParVals : "",
            proCode : "",
			stock : "",
			salePrice : "",
			a_mPrice : "",
			id : '0'
		});
	}
    var c=1;
    var tLen=1;
    var m = 1;
    var b=1;
    var _curentRow=0;
    var sLen=specArr.length;
    var count=0;

    for (var i = 0; i < sLen; ++i){//循环列
        var spec=specArr[i];
        var pars=spec.pars;
        tLen = pars.length;
        c *= tLen;
        m = row / c;
        b = c / tLen;
        for (var n = 0; n <b ; ++n){//内分配
            for (var j = 0; j < tLen; ++j){//循环每列中的个数
                for (var k = 0; k < m; ++k){
                    _curentRow=(j * m) + (n * row / b) + k;
                    dt[_curentRow][""+spec.specId] = pars[j].paramValue;// 这个是存储 内容的
                    dt[_curentRow]["specParIds"] = dt[_curentRow]["specParIds"] + pars[j].paramId + "|";//存储IDs
                    dt[_curentRow]["specParVals"] = dt[_curentRow]["specParVals"] + pars[j].paramValue + "|";//存储名称s
                    dt[_curentRow]["proCode"] = a_proNo + "-" + LE.trim(dt[_curentRow]["specParIds"],'\\|');//
                    dt[_curentRow]["stock"] = a_stock;// 
                    dt[_curentRow]["salePrice"] = a_uPrice;// 
                    dt[_curentRow]["a_mPrice"] = a_mPrice;// 
                    count++;//看看循环了几次
                }
            }
        }
    }
    return dt;
}

//合并列中相同的数据
//whh 2013-09-12
function inCells(tbodyId) {
    var _tb = document.getElementById(tbodyId);
    var _rolls = _tb.rows;//
    if(_rolls.length <=1){
    	return;
    }
    var _cells = _tb.rows[0].cells;
    for (var i = 2; i < _cells.length - 3; ++i) {
        var _currCell = _tb.rows[1].cells[i];
        var _cnt = 0;
        var _rlen = _rolls.length;
        for (var k = 2; k < _rlen; ++k) {
            _currCell.rowSpan = 1;
            if (_currCell.innerHTML != _tb.rows[k].cells[i].innerHTML) {
                if (_cnt != 0) {
                    _currCell.rowSpan = _cnt + 1;
                }
                _currCell = _tb.rows[k].cells[i];
                _cnt = 0;

            } else {//相同的移除:事实上需要统一
                ++_cnt;
                _tb.rows[k].cells[i].style.display = "none";
                //--k;
            }

            //处理最后一个
            if (k == _rlen - 1) {
                if (_cnt != 0) {
                    _currCell.rowSpan = _cnt + 1;
                }
            }

        }
    }

}
function getFileSizeSam(file,maxSize){
    var sam={fileSize:0,suffix:'',maxSizeFmt:maxSize};
    var s = file.size;
    if (s > 1024 * 1024) {
        sam.fileSize = (Math.round(s * 100 / (1024 * 1024)) / 100);
        sam.maxSizeFmt = (Math.round(maxSize * 100 / (1024 * 1024)) / 100);
        sam.suffix = 'MB';
    }else {
        sam.fileSize = (Math.round(s * 100 / 1024) / 100);
        sam.maxSizeFmt = (Math.round(maxSize * 100 / 1024) / 100);
        sam.suffix = 'KB';
    }
    return sam;
}
function uploadSingleFile(action,fileItem,onprogress,callback){
    if(true) {
        var fd = new FormData();
        fd.append("imgFile", fileItem.file);
        if (fileItem["name"] != null) {
            fd.append("name", fileItem.name.replace(/'/g, '‘'));
        }
        $.ajax({
            url: action+"&ran="+LE.ranStr(),
            type: 'post',
            data: fd,
            processData: false,
            contentType: false,
            success: function (data) {
                if (callback) {
                    callback(data,fileItem);
                }
            },
            xhr: function () {
                var xhr = $.ajaxSettings.xhr();
                if (onprogress && xhr.upload) {
                    xhr.upload.addEventListener("progress", function (evt) {
                        onprogress(fileItem,evt);
                    }, false);
                    return xhr;
                }
            }

        });
        return;
    }
    var form = $("<form style='display:none;' enctype='multipart/form-data' method='post'></form>");
    form.append("<input type='file' name='imgFile' value='"+fileItem.value+"' />");
    if(fileItem["name"] != null){
        form.append("<input type='hidden' name='name' value='"+fileItem.name.replace(/\'/g,'‘')+"' />");
    }
    var options = {
        url: action,
        type:"post",
        dataType:"json",
        success: function(data){
            if(callback){
                callback(data);
            }
        }
    };
    form.ajaxSubmit(options);
}
function preuploadImgShow(file,showBoxSelector,otherEvet){
    //判断是否支持FileReader
    if (!window.FileReader) {
        lBox.alert({content:"您的设备不支持图片预览功能，如需该功能请升级您的设备！"});
        return false;
    }
    var reader = new FileReader();

    //获取文件
    var imageType = /^image\//;
    //是否是图片
    if (!imageType.test(file.type)) {
        lBox.alert({content:"请选择图片,不要选择其他七七八八的玩意！"});
        return false;
    }
    //读取完成
    reader.onload = function(e) {
        //图片路径设置为读取的图片
        $(showBoxSelector).attr("src",e.target.result);
        if(null != otherEvet){
            otherEvet(file,e);
        }
    };
    reader.readAsDataURL(file);
    return true;
}

var r20 = /%20/g,
    rbracket = /\[\]$/,
    rCRLF = /\r?\n/g,
    rsubmitterTypes = /^(?:submit|button|image|reset|file)$/i,
    rsubmittable = /^(?:input|select|textarea|keygen)/i,rcheckableType = ( /^(?:checkbox|radio)$/i );
$.fn.extend({
    initSelection:function(arr) {
        if(!$(this).is("select")){
            return false;
        }
        var sb = new StrBuf("");
        for(var i in arr){
            if(!arr.hasOwnProperty(i)){
                continue;
            }
            sb.append("<option data-id='").append(arr[i].id)
                .append("' value='").append(arr[i].value)
                .append("' title='").append(arr[i].title).append("'>")
                .append(arr[i].text).append("</option>");
        }
        $(this).append(sb.toNString(""));
    },
    visibleWidth:function(){
        var w = $(this).width();
        var borderWidth = parseInt($(this).css("border-left-width"))+parseInt($(this).css("border-right-width"));
        var marginWidth = parseInt($(this).css("margin-left"))+parseInt($(this).css("margin-right"));
        var paddingWidth = parseInt($(this).css("padding-left"))+parseInt($(this).css("padding-right"));
        return w + borderWidth + marginWidth + paddingWidth;
    },
    visibleHeight:function(){
        var h = $(this).height();
        var bh = parseInt($(this).css("border-top-width"))+parseInt($(this).css("border-bottom-width"));
        var mh = parseInt($(this).css("margin-top"))+parseInt($(this).css("margin-bottom"));
        var ph = parseInt($(this).css("padding-top"))+parseInt($(this).css("padding-bottom"));
        var t = h + bh + mh + ph;
        return isNaN(t) ? 0 : t;
    },
    serializeArrayOfEditFmt: function(filterFunc) {
        return this.map( function() {

            // Can add propHook for "elements" to filter or add form elements
            var elements = jQuery.prop( this, "elements" );
            return elements ? jQuery.makeArray( elements ) : this;
        } )
            .filter( function() {
                var type = this.type;

                // Use .is(":disabled") so that fieldset[disabled] works

                var filter = true;
                if(filterFunc != null){
                    filter = filterFunc(this);
                }

                return this.name && !jQuery( this ).is( ":disabled" ) &&
                    filter &&
                    rsubmittable.test( this.nodeName ) && !rsubmitterTypes.test( type ) &&
                    ( this.checked || !rcheckableType.test( type ) );
            } )
            .map( function( i, elem ) {
                var val = jQuery( this ).val();
                //fmt
                var fc = LE.getFuncByName(jQuery(this).attr("data-post-fmt-func"));
                if(null != fc){
                    val = fc(val,this)+"";
                }
                return val == null ?
                    null :
                    jQuery.isArray( val ) ?
                        jQuery.map( val, function( val ) {
                            return { name: elem.name, value: val.replace( rCRLF, "\r\n" ) };
                        } ) :
                        { name: elem.name, value: val.replace( rCRLF, "\r\n" ) };
            } ).get();
    },
    serializeJsonOfEditFmt: function(filterFunc) {
        var forms = $(this).serializeArrayOfEditFmt(filterFunc);
        var o = {};
        for(var x in forms){
            var names = forms[x].name.split(".");
            if(names.length == 1){
                var e = o[names[0]];if(e == null){o[names[0]] = forms[x].value;}else if(e.push){e.push(forms[x].value)}else{o[names[0]] = [e,forms[x].value];}
                continue;
            }
            var k =o[names[0]];
            if(k == null){k = o[names[0]] = {};}
            for(var i = 1;i<names.length;++i){
                if(i == names.length-1){
                    var e = k[names[i]];if(e == null){k[names[i]] = forms[x].value;}else if(e.push){e.push(forms[x].value)}else{k[names[i]] = [e,forms[x].value];}
                }else{
                    if(k[names[i]] == null){
                        k[names[i]] = {};
                    }
                    k = k[names[i]];
                }
            }
        }
        return o;
    },
    fadeBg:function(){
        var box = $(this);
        var childrens = box.children();
        var filter = box.attr("data-filter");
        box.css("filter",filter);
        var time = 5000;
        var f = function(){
            var current = childrens.filter(".current");
            current.removeClass("current");
            var next = current.next();
            if(null == next || next.length == 0){
                next = childrens.eq(0);
            }
            next.addClass("current");
            setTimeout(f,time);
        };
        setTimeout(f,time);
    }
});


/*********************公共加载执行***********************/
$(function(){
    var te = new LA.DocumentEvent(document);
    te.JqEvent();
    //关闭加载等待框
    if(top.navM){
        top.navM.close();
    }
});




/**********************公共加载执行完毕***********************/
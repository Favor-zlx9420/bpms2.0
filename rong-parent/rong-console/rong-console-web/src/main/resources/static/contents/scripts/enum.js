
function showMemAmount(tr,v,i,propName){
    return respOriAmountOfColor(tr,tr["account"][propName],i);
}
function memCateFunc(tr,v,i){
    return findNameByValueFromConsts(Consts.MemCate,v);
}
function bindRegFrom(tr,v,i){
    return findNameByValueFromConsts(Consts.RegFrom,v);
}
function bindGenderFunc(tr,v,i){
    return findNameByValueFromConsts(Consts.Gender,v);
}
function bindIdTypeFunc(tr,v,i){
    return findNameByValueFromConsts(Consts.IdType,v);
}
function bindOrgType(tr,v,i){
    return findNameByValueFromConsts(Consts.OrgType,v);
}
function bindUserType(tr,v,i){
    return findNameByValueFromConsts(Consts.MemType,v);
}
function bindUserLevelFunc(tr,v,i){
    return findNameByValueFromConsts(Consts.MemLevel,v);
}
function bindStateFunc(tr,v,i){
    return defaultBindSwitchItem0(tr["id"],"state",v,v == 0 ? 1 : 0,Consts.State);
}
function bindTopFunc(tr,v,i){
    return defaultBindSwitchItem(tr["id"],"top",v);
}
function bindHotFunc(tr,v,i){
    return defaultBindSwitchItem(tr["id"],"hot",v);
}
function bindPublishedFunc(tr,v,i){
    return defaultBindSwitchItem0(tr["id"],"published",v,v == 0 ? 1 : 0,Consts.Published);
}

function defaultBindSwitchItem(targetId,field,fieldValue){
    return defaultBindSwitchItem0(targetId,field,fieldValue,!fieldValue,Consts.YesOrNo);
}
function defaultBindSwitchItem0(targetId,field,fieldValue,absValue,em){
    return bindSwitchItem(60,targetId,field,fieldValue,absValue,em);
}
function bindSortInput(tr,v,i){
    return _bindSortInput(tr,v,i,tr["id"]);
}

function _bindSortInput(tr,v,i,opid){
    var sort = tr["sort"];
    if(null == sort){
        sort = "";
    }
    return "<div style=\"width:110px\" class=\"list-sort\">" +
        "<input onblur='setSort.call(this,\""+opid+"\",\"sort\",\""+sort+"\")' autocomplete=\"off\" type=\"text\" data-tip=\"\" data-tip-type=\"1\" data-rules=\"decimal_2\" " +
        "class=\"validator\" name=\"listsort\" maxlength=\"10\" value=\""+sort +"\"></div>"
}
function setSort(id,item,ov){
    var v = $(this).val();
    if(!LET.checkFormItem.call(this,v)){
        return;
    }
    if(LE.isEmpty(v) || v == ov){
        return;
    }
    editItem(id,item,v);
}
function bindSwitchItem(w,targetId,field,fieldValue,absValue,em){
    var html = new StrBuf('<div class="f-left le-form-element switch" style="width:');
    html.append(w);
    html.append('px;" onclick="editItem.call(this,\'');
    html.append(targetId);
    html.append('\',\'');
    html.append(field);
    html.append('\',\'');
    html.append(absValue);
    html.append('\')">');
    html.append('<label class="switch-btn ');
    html.append(fieldValue ? "switch-btn-checked" : "");
    html.append('"><i>');
    html.append(findNameByValueFromConsts(em,fieldValue));
    html.append('</i></label>');
    html.append("</div>");
    return html.toNString("");
}
function editParam(param){
    $.ajax({
        url : param.url,
        type:"post",
        contentType: "application/json; charset=utf-8",
        data:JSON.stringify(param),
        dataType:"json",
        success:function (data) {
            if(!checkResultIsOk(data)){
                return;
            }
            if(null != viyGrid){viyGrid.reload.call(viyGrid);}
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            m.close(function(){
                lBox.alert({content:"保存时出现异常！",success:function(){
                        LET.openEvt = true;
                    }});
            })
        }
    });
}
var rootIcon = "root.gif";
function addIcons(icons) {
    return rootIcon + "," + icons;
}
//移除选中option
function removeMultiSelectOpt(selectObj, inputId, inputName) {
    var inputIds = [];
    var inputNames = [];
    $(selectorAdapt(selectObj) + " option").each(function () {
        if (this.selected || !$(this).val()) {
            $(this).remove();
        } else {
            inputIds.push($(this).val());
            inputNames.push($(this).text());
        }
    });
    $(selectorAdapt(inputId)).val(inputIds.join(","));
    $(selectorAdapt(inputName)).val(inputNames.join(","));
}

//单位部门-单选
function selectSysDept(inputId, inputName, callback) {
    var icon = addIcons("company.gif,dept.gif");
    new PopTree({
        url: CONTEXT_NAME + '/sysDept/treeDataForSelect.do?icon=' + icon,
        targetId: inputId,
        targetValueId: inputName,
        callback: callback
    });
}
//单位部门-多选
function multiSelectSysDept(selectObj, inputId, inputName, callback) {
    var icon = addIcons("company.gif,dept.gif");
    new PopTree({
        url: CONTEXT_NAME + '/sysDept/treeDataForSelect.do?icon=' + icon,
        selectObj: selectObj,
        targetId: inputId,
        check: true,
        targetValueId: inputName,
        setMultiValues: setMultiValues,
        callback: callback
    });
}

//手机用户-多选
function multiSelectPhoneUser(selectObj, inputId, inputName, callback) {
    var icon = addIcons("company.gif,person.gif");
    new PopTree({
        url: CONTEXT_NAME + '/phoneUser/treeDataForSelect.do?icon=' + icon,
        selectObj: selectObj,
        targetId: inputId,
        check: false,
        targetValueId: inputName,
        setMultiValues: setMultiValues,
        callback: callback,
        onlyLeaf:true
    });
}

//手机用户-多选
function multiSelectPhoneUserByVendor(selectObj, inputId, inputName, callback) {
    var icon = addIcons("company.gif,person.gif");
    new PopTree({
        url: CONTEXT_NAME + '/sPhoneUser/treeDataForSelect.do?icon=' + icon,
        selectObj: selectObj,
        targetId: inputId,
        check: false,
        targetValueId: inputName,
        setMultiValues: setMultiValues,
        callback: callback,
        onlyLeaf:true
    });
}

function setMultiValues(checkNodes) {
    var ids = new Array();
    var values = new Array();
    var options = this.options;
    $.each(checkNodes, function () {
        //if (this.isLeaf) {
    	if(this.name != "区域分级"){
            ids.push(this.id);
            values.push(this.name);
        }
    });
    if (options.targetId) {
        $(selectorAdapt(options.targetId)).val(ids.join(","));
    }
    if (options.targetValueId) {
        $(selectorAdapt(options.targetValueId)).val(values.join(","));
    }
}
//仅用于bpmduty人员添加
function multiSelectSysPersonBpmperson(selectObj, inputId, inputName, callback) {
    var icon = addIcons("company.gif,dept.gif,person.gif");
    new PopTree({
        url: CONTEXT_NAME + '/sysPerson/treeDataForSelect.do?icon=' + icon,
        selectObj: selectObj,
        targetId: inputId,
        check: true,
        targetValueId: inputName,
        setMultiValues: setMultiValuesBpmperson,
        callback: callback
    });
}
//仅用于bpmduty人员添加
function setMultiValuesBpmperson(checkNodes) {
    var ids = new Array();
    var values = new Array();
    var options = this.options;
    var ovalue = document.getElementById("personIds").value;
    var ovalues = "";
    if (ovalue != "") {
        ids.push(ovalue);
        ovalues = ovalue.split(",");
    }
    $.each(checkNodes, function () {
        if (this.isLeaf) {
            var check = true;
            if (ovalues != "") {
                for (var i = 0; i < ovalues.length; i++) {
                    if (ovalues[i] == this.id) {
                        check = false;
                        break;
                    }
                }
            }
            if (check) {
                ids.push(this.id);
                values.push(this.name);
            }
        }
    });
    if (options.targetId) {
        $(selectorAdapt(options.targetId)).val(ids.join(","));
    }
    if (options.targetValueId) {
        $(selectorAdapt(options.targetValueId)).val(values.join(","));
    }
}

//区域-单选 selectSysArea  selectSysArea
function selectSysArea(inputId, inputName, callback) {
    var icon = addIcons("code.gif,code_detail.gif");
    new PopTree({
        url: CONTEXT_NAME + '/sysArea/treeDataForSelect.do?icon=' + icon,
        targetId: inputId,
        targetValueId: inputName,
        callback: callback
    });

}
//区域-多选selectSysArea  selectSysArea
function multiSelectSysArea(selectObj,inputId, inputName, callback) {
	var icon = addIcons("code.gif,code_detail.gif");
	new PopTree({
		url: CONTEXT_NAME + '/sysArea/treeDataForSelect.do?icon=' + icon,
		targetId: inputId,
		targetValueId: inputName,
        check: true,
        setMultiValues: setMultiValues,
		callback: callback
	});
}
//区域-多选但是不级联（及父子关联）selectSysArea
function multiSelSysAreaNotCascade(selectObj,inputId, inputName, callback) {
	var icon = addIcons("code.gif,code_detail.gif");
	new PopTree({
		url: CONTEXT_NAME + '/sysArea/treeDataForSelect.do?icon=' + icon,
		targetId: inputId,
		targetValueId: inputName,
        check: true,
        setMultiValues: setMultiValues,
        setting: {
			check: {
				enable: true,
				chkStyle: "radio",
				radioType: "all",
				chkboxType: { "Y": "", "N": "" }
				}
		},
		callback: callback,
		title: '设备树'
	});
}



//人员-单选
function selectSysPerson(inputId, inputName, callback) {
	var icon = addIcons("company.gif,dept.gif,person.gif");
	new PopTree({
		url: CONTEXT_NAME + '/sysPerson/treeDataForSelect.do?icon=' + icon,
		targetId: inputId,
		targetValueId: inputName,
		callback: callback
	});
	
}

//选择人员-多选
function multiSelectSysPerson(selectObj, inputId, inputName, callback) {
    var icon = addIcons("company.gif,dept.gif,person.gif");
    new PopTree({
        url: CONTEXT_NAME + '/sysPerson/treeDataForSelect.do?icon=' + icon,
        selectObj: selectObj,
        targetId: inputId,
        check: true,
        setMultiValues: setMultiValues,
        targetValueId: inputName,
        callback: callback
    });
}

//选择工地管理人员-多选
function multiSelectSysRegPerson(selectObj, inputId, inputName, callback) {
    var icon = addIcons("company.gif,dept.gif,person.gif");
    new PopTree({
        url: CONTEXT_NAME + '/sysRegPerson/treeDataForSelect.do?icon=' + icon,
        selectObj: selectObj,
        targetId: inputId,
        check: true,
        targetValueId: inputName,
        setMultiValues: setMultiValues,
        callback: callback
    });
}

//选择代码表-多选
function multiSelectSysCode(code, selectObj, inputId, inputName, callback) {
    var icon = addIcons("code.gif,code_detail.gif");
    new PopTree({
        url: CONTEXT_NAME + '/sysCode/treeDataForSelect.do?icon=' + icon + "&code=" + code,
        selectObj: selectObj,
        targetId: inputId,
        check: true,
        targetValueId: inputName,
        setMultiValues: setMultiValues,
        callback: callback
    });
}

/**
 * 判断是否需要添加默认的#，无则当作jquery的选择器进行处理
 * @param selector
 */
function selectorAdapt(selector) {
    if (!selector) {
        return "";
    }
    if ($.type(selector) == 'string') {
        if (/\w/.test(selector.charAt(0))) {
            var ele = $("#" + selector);
            if (ele.length == 1) {
                return "#" + selector;
            }
        }
    }
    return selector;
}


//选择用户-多选
function multiSelectSysUser(selectObj, inputId, inputName, callback) {
    var icon = addIcons("company.gif,dept.gif,person.gif");
    new PopTree({
        url: CONTEXT_NAME + '/sysUser/treeDataForSelect.do?icon=' + icon,
        selectObj: selectObj,
        targetId: inputId,
        check: true,
        targetValueId: inputName,
        setMultiValues: setMultiValues,
        callback: callback
    });
}

//选择用户-单选
function selectSysUser(inputId, inputName, callback) {
    var icon = addIcons("company.gif,dept.gif,person.gif");
    new PopTree({
        url: CONTEXT_NAME + '/sysUser/treeDataForSelect.do?icon=' + icon,
        targetId: inputId,
        targetValueId: inputName,
        callback: callback
    });
}

//选择用户-多选
function multiSelectPrivilege(selectObj, inputId, inputName, callback) {
    var icon = addIcons("privilege.gif,button.gif");
    new PopTree({
        url: CONTEXT_NAME + '/sysPrivilege/treeData.do?icon=' + icon,
        selectObj: selectObj,
        targetId: inputId,
        check: true,
        targetValueId: inputName,
        setMultiValues: setMultiValues,
        callback: callback
    });
}

//流程表单权限编码--多选
function multiSelectFormPrivilege(formId, selectObj, inputId, inputName, callback) {
    var icon = addIcons("privilege.gif,button.gif");
    new PopTree({
        url: CONTEXT_NAME + '/bpmFormTemplate/privilegeDataForSelect.do?icon=' + icon + "&formId=" + formId,
        selectObj: selectObj,
        targetId: inputId,
        check: true,
        targetValueId: inputName,
        setMultiValues: setMultiValues,
        callback: callback
    });
}

//流程模板--单选(不按项目过滤)
function selectProcessTemplate(inputId, inputName, callback) {
    var icon = addIcons("company.gif,dept.gif,person.gif");
    new PopTree({
        url: CONTEXT_NAME + '/bpmProcessTemplate/treeData.do?icon=' + icon,
        targetId: inputId,
        targetValueId: inputName,
        callback: callback
    });
}

//流程表单模板--多选(不按项目过滤)
function multiSelectFormTemplate(selectObj, inputId, inputName, callback) {
    var icon = addIcons("privilege.gif,button.gif");
    new PopTree({
        url: CONTEXT_NAME + '/bpmFormTemplate/treeData.do?icon=' + icon,
        selectObj: selectObj,
        targetId: inputId,
        check: true,
        targetValueId: inputName,
        setMultiValues: setMultiValues,
        callback: callback
    });
}

//文档目录--单选
function selectDocCategory(inputId, inputName, callback) {
    var icon = addIcons("root.gif,default.gif");
    new PopTree({
        url: CONTEXT_NAME + '/docCategory/treeData.do?icon=' + icon,
        targetId: inputId,
        targetValueId: inputName,
        callback: callback
    });
}

//流程表单模板--多选(不按项目过滤)--指定流程定义
function selectFormTemplateForDefinition(bpmProcessDefinitionId, inputId, inputName, callback) {
    var icon = addIcons("root.gif,default.gif");
    new PopTree({
        url: CONTEXT_NAME + '/bpmFormTemplate/treeDataForDefinition.do?bpmProcessDefinitionId=' + bpmProcessDefinitionId + '&icon=' + icon,
        targetId: inputId,
        targetValueId: inputName,
        callback: callback
    });
}

//流程表单key定义--单选
function selectFormKey(formId, inputId, inputName, callback) {
    var icon = addIcons("root.gif,default.gif");
    new PopTree({
        url: CONTEXT_NAME + '/bpmFormTemplate/treeDataForSelectKey.do?formId=' + formId + '&icon=' + icon,
        targetId: inputId,
        targetValueId: inputName,
        callback: callback
    });
}

//监督任务的单位工程-多选
function multiSelectTaskDwgc(selectObj, inputId, inputName, flowCode, callback) {
    var icon = addIcons("company.gif,dept.gif");
    new PopTree({
        url: CONTEXT_NAME + '/jdTaskDwgc/treeDataForSelect.do?icon=' + icon + "&flowCode=" + flowCode,
        selectObj: selectObj,
        targetId: inputId,
        check: true,
        targetValueId: inputName,
        setMultiValues: setMultiValues,
        callback: callback
    });
}
/*******************************************************************
 描述：获取一个Select 的所有value的字符串
 ********************************************************************/
function getSelectValues(select) {
    var result = "";
    for (var i = 0; i < select.options.length; i++) {
        if (i == 0) {
            result = select.options[i].value;
        }
        else {
            result = result + "," + select.options[i].value;
        }
    }
    return result;
}

/******************************************************************
 描述：获取一个Select 的所有name的字符串
 *******************************************************************/
function getSelectNames(select) {
    return $(select).find("option").map(
        function () {
            return $(this).text();
        }).get().join(",");
}
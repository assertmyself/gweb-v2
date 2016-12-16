<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
    var formId = "bean";
    $(function () {
        //页面验证初始化
        var validateCondition = [
            {name: "code", rule: "validate[required,maxSize[50]]"},
            {name: "name", rule: "validate[required,maxSize[40]]"},
            {name: "tag", rule: "validate[custom[integer],maxSize[10]"},
            {name: "url", rule: "validate[maxSize[200]"},
            {name: "definition", rule: "validate[maxSize[500]]"},
            {name: "description", rule: "validate[maxSize[50]]"}
        ];
        validateInit(validateCondition, formId);

        //更改按钮
        var btns="<button class=\"btn\" data-dismiss=\"modal\" aria-hidden=\"true\" id=\"cancelBtn\" >&nbsp;取消</button>" +
                "<button type=\"button\" class=\"btn btn-primary\" id='confirmBtn' onclick=\"save(this)\">&nbsp;确定</button>";

        $("#"+openWindowId+" .modal-footer").html(btns);
    });

    //保存操作
    function save(btn) {
        if (!validateForm(formId)) {
            return;
        }

        //提交表单
        disableBtn(btn);
        saveAjaxData("${ctx}/sysPrivilege/save.do", formId);
    }
</script>
<form:form commandName="bean" cssClass="form-horizontal">
    <form:hidden path="id"/>
    <form:hidden path="parent" value="${bean.parent.id}"/>

	<div class="form-group-hz">
		<label >
			权限编码:
		</label>
        <form:input path="code" cssClass="input_text_long"/>
	</div>
	<div class="form-group-hz">
		<label >
			权限名称:
		</label>
        <form:input path="name" cssClass="input_text_long"/>
	</div>
	<div class="form-group-hz">
		<label >
			权限类型:
		</label>
		<sys:code code="${typeCode}" sysCodeDetailId="${bean.type.id}" type="select" name="type"/>
	</div>
	<div class="form-group-hz">
		<label >
			TAG:
		</label>
        <form:input path="tag" cssClass="input_text"/>
	</div>
	<div class="form-group-hz">
		<label >
			页面地址:
		</label>
        <form:input path="url" cssClass="input_text_long"/>
	</div>
	<div class="form-group-hz">
		<label >
			定义:
		</label>
        <form:textarea path="definition" cssClass="input_textarea"/>
	</div>
	<div class="form-group-hz">
		<label >
			描述:
		</label>
        <form:textarea path="description" cssClass="input_textarea"/>
	</div>
</form:form>
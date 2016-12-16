<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
    var formId = "bean";
    $(function () {
        //页面验证初始化
        var validateCondition = [
            {name: "code", rule: "validate[required,maxSize[50]]"},
            {name: "name", rule: "validate[required,maxSize[50]]"},
            {name: "shortName", rule: "validate[maxSize[50]]"},
            {name: "organizationCode", rule: "validate[maxSize[20]]"},
            {name: "contacter", rule: "validate[maxSize[50]]"},
            {name: "telephone", rule: "validate[maxSize[50]]"},
            {name: "address", rule: "validate[maxSize[100]]"},
            {name: "orderNo", rule: "validate[custom[integer],maxSize[10]"},
            {name: "memo", rule: "validate[maxSize[250]]"}
        ];
        validateInit(validateCondition, formId);

        //更改按钮
        var btns="<button class=\"btn\" data-dismiss=\"modal\" aria-hidden=\"true\" id=\"cancelBtn\" ><i class='glyphicon glyphicon-remove'></i>取消</button>" +
                "<button type=\"button\" class=\"btn btn-primary\" id='confirmBtn' onclick=\"save(this)\" ><i class='glyphicon glyphicon-ok'></i>确定</button>";

        $("#"+openWindowId+" .modal-footer").html(btns);

    });

    //保存操作
    function save(btn) {
        if (!validateForm(formId)) {
            return;
        }

        //提交表单
        disableBtn(btn);
        saveAjaxData("${ctx}/sysDept/save.do", formId);
    }
</script>
<form:form commandName="bean" cssClass="form-horizontal">
    <form:hidden path="id"/>
    <form:hidden path="parent" value="${bean.parent.id}"/>

	<div class="form-group-hz">
		<label>
			单位代码:
		</label>
		<form:input path="code" cssClass="input_text"/>
	</div>
	<div class="form-group-hz">
		<label>
			单位名称:
		</label>
		<form:input path="name" cssClass="input_text"/>
	</div>
	<div class="form-group-hz">
		<label>
			简称:
		</label>
		<form:input path="shortName" cssClass="input_text"/>
	</div>
	<div class="form-group-hz">
		<label>
			机构代码证:
		</label>
		<form:input path="organizationCode" cssClass="input_text"/>
	</div>
	<div class="form-group-hz">
		<label>
			联系人:
		</label>
		<<form:input path="contacter" cssClass="input_text"/>
	</div>
	<div class="form-group-hz">
		<label>
			联系电话:
		</label>
		<form:input path="telephone" cssClass="input_text"/>
	</div>
	<div class="form-group-hz">
		<label>
			注册地址:
		</label>
		<form:input path="address" cssClass="input_text"/>
	</div>
	<div class="form-group-hz">
		<label>
			单位标志:
		</label>
		<form:hidden path="isCompany"/>
	</div>
	<div class="form-group-hz">
		<label>
			是否有效:
		</label>
		<form:checkbox path="isValid" value="1"/>
	</div>
	<div class="form-group-hz">
		<label>
			排序:
		</label>
		<form:input path="orderNo" cssClass="input_text"/>
	</div>
	<div class="form-group-hz">
		<label>
			备注:
		</label>
		<form:textarea path="memo" cssClass="input_textarea"/>
	</div>
</form:form>
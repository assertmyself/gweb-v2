<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
    var formId = "bean";
    $(function () {
        //页面验证初始化
        var validateCondition = [
            {name: "updateContent", rule: "validate[maxSize[500]]"}
        ];
        validateInit(validateCondition, formId);

        //更改按钮
        var btns="<button class=\"btn\" data-dismiss=\"modal\" aria-hidden=\"true\" id=\"cancelBtn\" ><i class='glyphicon glyphicon-remove'></i>取消</button>" +
                "<button type=\"button\" class=\"btn btn-primary\" id='confirmBtn' onclick= \"save(this)\" ><i class='glyphicon glyphicon-ok'></i>确定</button>";

        $("#"+openWindowId+" .modal-footer").html(btns);

    });

    //保存操作
    function save(btn) {
        if (!validateForm(formId)) {
            return;
        }
        //加入其他业务判断
        disableBtn(btn);
        //提交表单
        saveAjaxData("${ctx}/appVersion/save.do", formId);
    }
</script>
<form:form commandName="bean" cssClass="form-horizontal">
    <form:hidden path="id"/>

	
	<div class="form-group-hz">
		<label>
			版本更新内容:
		</label>
		<form:textarea path="updateContent" cssClass="input_textarea"/>
	</div>
	
</form:form>
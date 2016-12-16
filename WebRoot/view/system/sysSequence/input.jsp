<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
    var formId = "bean";
    $(function () {
        //页面验证初始化
        var validateCondition = [
            {name: "lastid", rule: "validate[required,custom[integer],maxSize[10]"}
        ];
        validateInit(validateCondition, formId);

      //更改按钮
        var btns="<button class=\"btn\" data-dismiss=\"modal\" aria-hidden=\"true\" id=\"cancelBtn\" ><i class='glyphicon glyphicon-remove'></i>&nbsp取消</button>" +
                "<button type=\"button\" class=\"btn btn-primary\" id='confirmBtn' onclick= \"save(this)\" ><i class='glyphicon glyphicon-ok'></i>&nbsp确定</button>";

		$("#" + openWindowId + " .modal-footer").html(btns);
		
    });

    //保存操作
    function save(btn) {
        if (!validateForm(formId)) {
            return;
        }

        //提交表单
        saveAjaxData("${ctx}/sysSequence/save.do", formId);
    }
</script>
<form:form commandName="bean" cssClass="form-horizontal">
    <form:hidden path="id"/>
	<div class="form-group-hz">
		<label>
			下一标识:
		</label>
		<form:input path="lastid" cssClass="input_text" />
	</div>
</form:form>
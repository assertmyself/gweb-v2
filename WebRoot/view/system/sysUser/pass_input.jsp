<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
    var formId = "bean";
    $(function () {
        //页面验证初始化
        var validateCondition = [
            {name: "oldPass", rule: "validate[required,maxSize[20]]"},
            {name: "newPass", rule: "validate[required,maxSize[20]]"},
            {name: "confirmPass", rule: "validate[required,maxSize[20]]"}
        ];

        var settings = {
            promptPosition: "centerRight:10,-25"
        };
    	
        validateInit(validateCondition, formId,settings);

        //更改按钮
        var btns="<button class=\"btn\" data-dismiss=\"modal\" aria-hidden=\"true\" id=\"cancelBtn\" ><i class='glyphicon glyphicon-remove'></i>&nbsp取消</button>" +
                "<button type=\"button\" class=\"btn btn-primary\" id='confirmBtn' onclick= \"save(this)\" ><i class='glyphicon glyphicon-ok'></i>&nbsp确定</button>";

        $("#"+openWindowId+" .modal-footer").html(btns);

    });

    //保存操作
    function save(btn) {
        if (!validateForm(formId)) {
            return;
        }
        //check
        var oldP = $('#oldPass');
        var newP = $('#newPass');
        var conP = $('#confirmPass');

        if (newP.val() == '') {
            showWarningMsg('请输入密码！');
            newP.focus();
            return;
        }
        if (conP.val() == '') {
            showWarningMsg('请输入确认密码！');
            conP.focus();
            return;
        }

        if (newP.val() != conP.val()) {
            showWarningMsg('两次密码不一致！请重新输入');
            newP.focus();
            return;
        }

        //提交表单
        saveAjaxData("${ctx}/sysPassword/savePass.do", formId);
    }
</script>
<form:form commandName="bean" cssClass="form-horizontal">

	<div class="form-group-hz">
		<label >
			 原密码：
		</label>
		<input type="password" id="oldPass" name="oldPass" value=""></input>
	</div>
	<div class="form-group-hz">
		<label >
			  新密码：
		</label>
		<input type="password" id="newPass" name="newPass" value=""></input>
	</div>
	<div class="form-group-hz">
		<label >
			 原密码：
		</label>
		<input type="password" id="confirmPass" name="confirmPass" value=""></input>
	</div>
	
</form:form>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
    var formId = "bean";
    $(function () {
        //页面验证初始化
        var validateCondition = [
            {name: "appVersionCode", rule: "validate[required]"},
            {name: "updateContent", rule: "validate[required]"}
        ];
        validateInit(validateCondition, formId);
        //更改按钮
        var btns="<button class=\"btn\" data-dismiss=\"modal\" aria-hidden=\"true\" id=\"cancelBtn\" ><i class='btn btn-sm btn-danger'></i>取消</button>" +
                "<button type=\"button\" class=\"btn btn-primary\" id='confirmBtn' onclick= \"save(this)\" ><i class='btn btn-sm btn-info'></i>确定</button>";

        $("#"+openWindowId+" .modal-footer").html(btns);

    });

    //保存操作
    function save(btn) {
        if (!validateForm(formId)) {
            return;
        }
        disableBtn(btn);
        //提交表单
        saveAjaxData("${ctx}/appVersion/insert.do", formId);
    }

    //保存操作
    function upload(btn) {
        //提交表单
        saveAjaxFileUpload("${ctx}/appVersion/upload.do");
    }
</script>

<!--<form method="post" enctype="multipart/form-data">
    <input type="file" id="attachment" name="attachment" placeholder=""/>
    <button type="button" class="btn btn-primary" id='confirmBtn' onclick="upload(this)" >上传</button>
</form>
-->

<form:form commandName="bean" cssClass="form-horizontal">
    <form:hidden path="id" />
    <form:hidden path="attachmentFileName" id="attachmentFileName" name="attachmentFileName"/>

	<div class="form-group-hz">
		<label>
			版本Code:
		</label>
		<form:input path="appVersionCode" cssClass="input_text"/>
	</div>
	<div class="form-group-hz">
		<label>
			版本更新内容:
		</label>
		<form:textarea path="updateContent" cssClass="input_textarea"/>
	</div>
	<div class="form-group-hz">
		<label>
			版本文件:
		</label>
		<input type="file" id="attachment" name="attachment" placeholder=""/>
        <button type="button" class="btn btn-primary" id='confirmBtn' onclick="upload(this)" >上传</button>
	</div>
</form:form>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
    var formId = "bean";
        
    $(function () {
        //页面验证初始化 ,custom[onlyLetterNumber
        var validateCondition = [
            {name: "name", rule: "validate[required,minSize[3],maxSize[50]]]"},
            {name: "age", rule: "validate[required,maxSize[30]]"}
            //{name: "productModel", rule: "validate[required,maxSize[50]"}

        ];
        validateInit(validateCondition, formId);

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
       
        //提交表单
        saveAjaxData("${ctx}/hello/save.do", formId);
    }

</script>
<form:form commandName="bean" cssClass="form-horizontal">
    <form:hidden path="id"/>
    <div class="form-group-hz">
		<label>
			名称：
		</label>
		<form:input path="name" cssClass="input_text" />
	</div>
    <div class="form-group-hz">
		<label>
			年龄：
		</label>
		<form:input path="age" cssClass="input_text" />
	</div>
    <div class="form-group-hz">
		<label>
			地址：
		</label>
		<form:input path="address" cssClass="input_text" />
	</div>
    <div class="form-group-hz">
		<label>
			描述：
		</label>
		<form:input path="description" cssClass="input_text" />
	</div>
</form:form>
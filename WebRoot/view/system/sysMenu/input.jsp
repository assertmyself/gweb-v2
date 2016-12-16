<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
    var formId = "bean";
    $(function () {
        //页面验证初始化
        var validateCondition = [
            {name: "name", rule: "validate[required]"},
            {name: "privilege", rule: "validate[required]"},
            {name: "menuLevel", rule: "validate[custom[integer],maxSize[3]"}
            //{name:"url", rule:"validate[required]"},
            //{name:"jsEvent", rule:"validate[required]"},
            //{name:"isLeaf", rule:"validate[required]"},
            //{name:"treeId", rule:"validate[required]"},
            //{name:"isValid", rule:"validate[required]"},
            //{name:"param", rule:"validate[required]"},
            //{name:"target", rule:"validate[required]"},
            //{name:"icon", rule:"validate[required]"},
        ];
        validateInit(validateCondition, formId);

        //更改按钮
        var btns="<button class=\"btn\" data-dismiss=\"modal\" aria-hidden=\"true\" id=\"cancelBtn\" ><i class='glyphicon glyphicon-remove'></i>&nbsp;取消</button>" +
                "<button type=\"button\" class=\"btn btn-primary\" id='confirmBtn' onclick= \"save(this)\" ><i class='glyphicon glyphicon-ok'></i>&nbsp;确定</button>";

        $("#"+openWindowId+" .modal-footer").html(btns);

    });

    //保存操作
    function save(btn) {
        if (!validateForm(formId)) {
            return;
        }

        //提交表单
        disableBtn(btn);
        saveAjaxData("${ctx}/sysMenu/save.do", formId);
    }
</script>

<form:form commandName="bean" cssClass="form-horizontal">
    <form:hidden path="id"/>
    <input type="hidden" name="parent" value="${bean.parent.id}"/>

	<div class="form-group-hz">
		<label >
			名称:
		</label>
        <form:input path="name" cssClass="input_text"/>
	</div>
	<div class="form-group-hz">
		<label >
			编码:
		</label>
		<form:input path="privilege" cssClass="input_text"/>
	</div>
	<div class="form-group-hz">
		<label >
			层次:
		</label>
        <form:input path="menuLevel" cssClass="input_text"/>
	</div>
	<div class="form-group-hz">
		<label >
			链接地址:
		</label>
        <form:input path="url" cssClass="input_text_long"/>
	</div>
	<div class="form-group-hz">
		<label >
			事件:
		</label>
        <form:input path="jsEvent" cssClass="input_text_long"/>
	</div>
	<div class="form-group-hz">
		<label >
			是否有效:
		</label>
        <form:checkbox path="isValid" value="1"/>
	</div>
	<div class="form-group-hz">
		<label >
			参数:
		</label>
        <form:input path="param" cssClass="input_text"/>
	</div>
	<div class="form-group-hz">
		<label >
			目标窗口:
		</label>
        <form:input path="target" cssClass="input_text"/>
	</div>
	<div class="form-group">
		<label class="col-sm-4 control-label">
			显示图标:
		</label>
		<label class="checkbox-inline col-sm-7">
		<c:forEach items="${ICON_LIST}" var="item" varStatus="status">
			<input type="radio" name="icon" id="icon"
				<c:if test="${item == bean.icon}">checked</c:if> value="${item}">
			<i class="${item} fa-lg">&nbsp;</i>
			
		<c:if test="${status.index>0 && (status.index+1) % 4 == 0}"></br></c:if>
		</c:forEach>
        </label>
	</div>
</form:form>

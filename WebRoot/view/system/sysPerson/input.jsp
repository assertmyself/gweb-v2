<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
    var formId = "bean";
    $(function () {
        //页面验证初始化
        var validateCondition = [
            {name: "code", rule: "validate[required]"},
            {name: "name", rule: "validate[required]"},
            {name: "card", rule: "validate[required]"},
//            {name:"age", rule:"validate[required,custom[integer],max[5]"},
            //{name:"sex", rule:"validate[required]"},
            //{name:"bornDate", rule:"validate[required]"},
            //{name:"bornPlace", rule:"validate[required]"},
            {name: "mobile", rule: "validate[required]"},
            //{name:"officeTel", rule:"validate[required]"},
            //{name:"faxTel", rule:"validate[required]"},
            //{name:"email", rule:"validate[required]"},
            //{name:"zipcode", rule:"validate[required]"},
            {name: "workYear", rule: "validate[custom[integer],maxSize[2]"},
            //{name:"msnCode", rule:"validate[required]"},
            //{name:"qqCode", rule:"validate[required]"},
            //{name:"memo", rule:"validate[required]"},
            {name: "orderNo", rule: "validate[custom[integer]]"}
        ];
        validateInit(validateCondition, formId);
        //更改按钮
        var btns="<button class=\"btn\" data-dismiss=\"modal\" aria-hidden=\"true\" id=\"cancelBtn\" >&nbsp;取消</button>" +
                "<button type=\"button\" class=\"btn btn-primary\" id='confirmBtn' onclick= \"save(this)\">&nbsp;确定</button>";

        $("#"+openWindowId+" .modal-footer").html(btns);


        $("#datetimepicker").datetimepicker({
    		lang:"ch", 
            format:'Y/m/d',
            timepicker:false
        });
    });

    //保存操作
    function save(btn) {
        if (!validateForm(formId)) {
            return;
        }
        disableBtn(btn);
        //提交表单
        saveAjaxData("${ctx}/sysPerson/save.do", formId);
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
</script>

<form:form commandName="bean" cssClass="form-horizontal">
    <form:hidden path="id"/>

	<div class="form-group-hz">
		<label >
			编号:
		</label>
        <form:input path="code" cssClass="input_text_long"/>
	</div>
	<div class="form-group-hz">
		<label >
			姓名:
		</label>
        <form:input path="name" cssClass="input_text"/>
	</div>
	<div class="form-group-hz">
		<label >
			所属单位（部门）:
		</label>
		 <input type="text" name="deptName" id="deptName" class="input_text" value="${bean.dept.name}"onclick="selectSysDept('sysDeptId','deptName')"title="点击选择所属单位（部门）"
                           readonly="true">
         <input type="hidden" name="sysDeptId" id="sysDeptId"
                           value="<c:if test="${bean.dept!=null}">${bean.dept.id}</c:if>"/>
	</div>
	<div class="form-group-hz">
		<label >
			身份证号:
		</label>
        <form:input path="card" cssClass="input_text"/>
	</div>
	<div class="form-group-hz">
		<label >
			年龄:
		</label>
        <form:input path="age" cssClass="input_text"/>
	</div>
	<div class="form-group">
		<label class="col-sm-4 control-label">
			性别:
		</label>
		<label class="checkbox-inline col-sm-7">
        <form:radiobutton path="sex" value="true"/>男&nbsp;&nbsp;
        <form:radiobutton path="sex" value="false"/>女
        </label>
	</div>
	<div class="form-group-hz">
		<label >
			出生年月:
		</label>
		<form:input path="bornDate" cssClass="input_date" id="datetimepicker" readonly="false" /> 
		<!-- <input type="text" name="bornDate"  id="datetimepicker"  /> -->
	</div>
	<div class="form-group-hz">
		<label >
			籍贯:
		</label>
		<form:input path="bornPlace" cssClass="input_text"/>
	</div>
	<div class="form-group-hz">
		<label >
			移动电话:
		</label>
		<form:input path="mobile" cssClass="input_text"/>
	</div>
	<div class="form-group-hz">
		<label >
			固定电话:
		</label>
		<form:input path="officeTel" cssClass="input_text" />
	</div>
	<div class="form-group-hz">
		<label >
			传真:
		</label>
		<form:input path="faxTel" cssClass="input_text" />
	</div>
	<div class="form-group-hz">
		<label >
			邮件:
		</label>
		<form:input path="email" cssClass="input_text" />
	</div>
	<div class="form-group-hz">
		<label >
			邮政编码:
		</label>
		<form:input path="zipcode" cssClass="input_text" />
	</div>
	<div class="form-group-hz">
		<label >
			工作年限:
		</label>
		<form:input path="workYear" cssClass="input_text" />
	</div>
	<div class="form-group-hz">
		<label >
			备注:
		</label>
		<form:textarea path="memo" cssClass="input_textarea" />
	</div>
	<div class="form-group-hz">
		<label >
			部门排序号:
		</label>
		<input type="text" name="orderNo" id="orderNo" class="input_text"
			value="${bean.personDept.orderNo}">
	</div>
</form:form>
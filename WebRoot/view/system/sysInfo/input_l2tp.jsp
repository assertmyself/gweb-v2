<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
    	$(function () {
	    	 //页面验证初始化
	        var validateCondition = [
	            {name: "l2tpServerIp", rule: "validate[required"},
	            {name: "l2tpUsrPass", rule: "validate[required"},
	            {name: "l2tpUsrName", rule: "validate[required"}
	        ];
	        validateInit(validateCondition, formId);
	        //更改按钮
	        var btns="<button class=\"btn\" data-dismiss=\"modal\" aria-hidden=\"true\" id=\"cancelBtn\" ><i class='glyphicon glyphicon-remove'></i>取消</button>" +
	                "<button type=\"button\" class=\"btn btn-primary\" id='confirmBtn' onclick= \"save(this)\" ><i class='glyphicon glyphicon-ok'></i>确定</button>";
	        $("#"+openWindowId+" .modal-footer").html(btns);
		});
	var formId = "bean";
    //保存操作
    function save(btn) {
        if (!validateForm(formId)) {
            return;
        }
        //提交表单
        saveAjaxData("${ctx}/sysInfo/l2tp.do", formId);
    }
</script>

<form class="form-horizontal" id="bean">
	<div class="form-group-hz">
		<label >
			L2TP服务器IP：
		</label>
		<input type="text" id="l2tpServerIp" name="l2tpServerIp" value="${l2tpServerIp}"></input>
	</div>
	<div class="form-group-hz">
		<label>
			L2TP账号：
		</label>
		<input type="text" id="l2tpUsrName" name="l2tpUsrName" value="${l2tpUsrName}"></input>
	</div>
	<div class="form-group-hz">
		<label class="control-label">
			L2TP密码：
		</label>
		<input type="password" id="l2tpUsrPass" name="l2tpUsrPass" value="${l2tpUsrPass}"></input>
    </div>
</form>



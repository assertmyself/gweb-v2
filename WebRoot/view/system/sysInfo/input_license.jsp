<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
    var formId = "bean";
    $(function () {
    	 //页面验证初始化
        var validateCondition = [
            {name: "snmp", rule: "validate[required"},
            {name: "ftp", rule: "validate[required"}
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

        //提交表单
        saveAjaxData("${ctx}/sysInfo/license.do", formId);
    }
</script>
<form class="form-horizontal"id="bean">
	<div class="form-group-hz">
		<label >
			Licenses升级：
		</label>
		<input type="text" id="info" class="input_text" value="支持字符串" readonly="readonly"></input>
	</div>
	<div class="form-group-hz">
		<label >
			当前授权数：
		</label>
		<input type="text" name="accessNum" class="input_text" value="${accessNum}" readonly="readonly"/>      
	</div>
	<div class="form-group-hz">
		<label >
			License字符串：
		</label>
        <input type="text" name="character" class="input_text" />    
	</div>
</form>
<!-- 
<div style="display:none;background:white;width:auto;position: absolute;border: 1px solid #617775;" id="che">
	<ul style="list-style-type:none;left: -35px;position: relative;">
	<li id="treeDemoId" class="ztree"></li>
	</ul>
</div>

 -->

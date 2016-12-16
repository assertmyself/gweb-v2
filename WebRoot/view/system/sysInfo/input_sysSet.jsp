<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
    	$(function () {
    	 //页面验证初始化
        var validateCondition = [
            {name: "ftp", rule: "validate[required"}
        ];
        
        validateInit(validateCondition, formId,null);
        //更改按钮
        var btns="<button class=\"btn\" data-dismiss=\"modal\" aria-hidden=\"true\" id=\"cancelBtn\" ><i class='glyphicon glyphicon-remove'></i>取消</button>" +
                "<button type=\"button\" class=\"btn btn-primary\" id='confirmBtn' onclick= \"save(this)\" ><i class='glyphicon glyphicon-ok'></i>确定</button>";
        $("#"+openWindowId+" .modal-footer").html(btns);
        
        //扩展jquery库
		$.fn.editable = function(){
			$(this).each(function(i,t){
				$(t).change(function(){
					var me=$(this);
					me.find('.customval').remove();
					if(-1 == me.val()){
						var ed = $("<input type=\"text\" class=\"form-control\" />");
						me.after(ed).hide();
						ed.blur(function(){
						var v=ed.val();
						if(null === v ||  v.length ==0){
						ed.remove();me.val(null).show();
						}else{
						me.append("<option value=\""+v+"\" class=\"customval\" selected>"+v+"</option>").show();
						ed.remove();
						}
						}).focus();
					}
				})
			});
		}
		$("#ftp").editable();
		$("#snmp").editable();      
    });

	var formId = "input_sysset_bean";
    //保存操作
    function save(btn) {
        if (!validateForm(formId)) {
            return;
        }

        //提交表单
        saveAjaxData("${ctx}/sysInfo/sysSet.do", formId);
    }
</script>

<form class="form-horizontal"id="input_sysset_bean">
	<div class="form-group-hz">
		<label >
			当前FTP地址：
		</label>
		<input type="text"  class="input_text" readonly="readonly" value="${ftp_current}"></input>
	</div>
	<div class="form-group-hz">
		<label>
			FTP地址：
		</label>
		<select class="form_select" id="ftp" name="ftp">
			<c:forEach items="${ipValues}" var="ipValue">
				<option value="${ipValue.key}">
					${ipValue.value}
				</option>
			</c:forEach>
			<option value="-1">
				请输入
			</option>
		</select>
	</div>
</form>
	
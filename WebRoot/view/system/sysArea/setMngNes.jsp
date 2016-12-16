<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
	var formId = "bean";
	$(function() {

		//页面验证初始化
		var validateCondition = [

		];
		validateInit(validateCondition, formId);

		//更改按钮
        var btns="<button class=\"btn\" data-dismiss=\"modal\" aria-hidden=\"true\" id=\"cancelBtn\" ><i class='glyphicon glyphicon-remove'></i>取消</button>" +
                "<button type=\"button\" class=\"btn btn-primary\" id='confirmBtn' onclick= \"save(this)\" ><i class='glyphicon glyphicon-ok'></i>确定</button>";

        $("#"+openWindowId+" .modal-footer").html(btns);
        //显示网关数据  为异步刷新准备
        $(".tr_dark ").append("<td colspan='2' align='left'><div class='nesDiv' style='max-height: 460px;overflow-y: auto;position: relative;'><c:forEach items='${list}' var='item'><div style='height: 30px;'>&nbsp;&nbsp;&nbsp;<input type='checkbox' name='mngNesId' value='${item.gwId}'<c:if test='${item.isChecked}'>checked</c:if><c:if test='${item.readOnly}'>disabled</c:if> align='absmiddle'><b>热点:${item.hotspot}</b><input type='hidden' name='treeId' value='${item.hotspot}'></div></c:forEach></div></td>");
        
		 $(".search-bottom").click(function(){
			 showProcessInfo();
			 var sgwId = $(".input-mngNes").val();
			$(".tr_dark").load("${ctx}/sysArea/searhMngNes.do?id="+${id}+"&sgwId="+sgwId,null,function(){hideProcessInfo();});
		 });     
	});

	//保存操作
	function save(btn) {

		//提交表单
		disableBtn(btn);
		saveAjaxData("${ctx}/sysArea/saveMngNes.do", formId);
	}

	//选择全选时所有选项都被自动选上。
	function checkAll() {
		var selectStatus = document.getElementById("selectAll").checked;
		var mngNesId = document.getElementsByName("mngNesId");
		var length = mngNesId.length;
		for ( var i = 0; i < length; i++) {
			mngNesId[i].checked = selectStatus;
		}
	}
</script>
<form:form commandName="bean" onsubmit="return false;">
	<form:hidden path="id" />
	<div class="form_div">
		<table cellpadding="0" cellspacing="0" class="form_table">
			<tr class="tr_light" align="left">
				<td colspan="1" align="left">
					&nbsp;&nbsp;&nbsp;设备选择（全选/全不选
					<input type="checkbox" id="selectAll" onclick=checkAll();>
					）
				</td>
				<!--start-top-serch-->
				<td align="right">
					<div id="search">
						<input type="text" class="input-mngNes" placeholder="Search here..." />
						<button type="submit" class="search-bottom" title="Search">
							<i class="glyphicon glyphicon-search"></i>
						</button>
						&nbsp;&nbsp;&nbsp;
					</div>
				</td>
			</tr>
			<tr class="tr_dark">
			</tr>
		</table>
	</div>
</form:form>

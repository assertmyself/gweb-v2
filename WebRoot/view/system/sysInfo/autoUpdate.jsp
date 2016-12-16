<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
	var newCur = ${newCur};
	//更改按钮
	if(newCur == false){
		var btns = "<button type=\"button\" class=\"btn btn-primary\" id='confirmBtn' onclick= \"update()\" ><i class='glyphicon glyphicon-share'></i>升级</button>";
		$("#"+openWindowId+" .modal-footer").html(btns);
	}
	//自动升级函数
	function update(){
		var opts = {
			successFnc: function(){
				$.get("${ctx}/autoUpdateClient/restartServer.do");
			}
		}
		saveAjaxData("${ctx}/autoUpdateClient/autoUpdate.do",null,null,opts);
	}
	
</script>
<div class="update">
	<table cellspacing="0" cellpadding="0" class="form_table">
		<tbody>
			<tr class="tr_light">
				<td class="form_label"></td>
				<td class="form_content">
					当前版本	
				</td>
				<td class="form_content">
					最新版本
				</td>
			</tr><%--
			<tr class="tr_dark">
				<td class="form_label">产品信息</td>
				<td class="form_content">${curVerInfo.product}</td>
				<td class="form_content">${newVerInfo.product}</td>
			</tr>
			<tr class="tr_light">
				<td class="form_label">版本号</td>
				<td class="form_content">${curVerInfo.no}</td>
				<td class="form_content">${newVerInfo.no}</td>
			</tr>--%>
			<tr class="tr_dark">
				<td class="form_label">构建号</td>
				<td class="form_content">${curVerInfo.no}</td>
				<td class="form_content">${newVerInfo.no}</td>
			</tr>
			<tr class="tr_light">
				<td class="form_label">构建日期</td>
				<td class="form_content">${curVerInfo.date}</td>
				<td class="form_content">${newVerInfo.date}</td>
			</tr>
		</tbody>
	</table>
</div>

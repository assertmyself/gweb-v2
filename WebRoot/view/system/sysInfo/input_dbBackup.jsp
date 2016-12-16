<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<script type="text/javascript">
	//数据库备份
	$(function () {
		$("#dbBackup").click(function(){
			downAjaxFile("${ctx}/sysInfo/dbBackup.do","${ctx}");
			});
		});
</script>
<form class="form-horizontal"id="bean">
	<div class="form-group-hz">
		<label >
			数据库备份：
		</label>
		<button class="btn btn-info" id="dbBackup" type="button"><i class="glyphicon glyphicon-copyright-mark"></i>&nbsp;数据库备份</button>
	</div>
</form>

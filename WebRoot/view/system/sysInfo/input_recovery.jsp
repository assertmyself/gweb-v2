<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
		//页面提示  ajaxFileUpload
		function toUpload(){
			saveAjaxFileUpload("${ctx}/sysInfo/recovery.do");
		}
</script>
<form role="form" class="form-inline" id="btnFile">
	<div class="form-group">
		<label class="control-label">
			浏览文件：
		</label>
		<input type="file" id="upFile" name="upFile" style="display:none;"  onchange="upFileValue('导入');" />
		<input id="textUpFile" class="form-control" disabled="disabled" placeholder="未选择文件" type="text">
	</div>
	<button class="btn btn-sm btn-info" id="browseFile" type="button" onclick="upFileValue2();">
			<i class="glyphicon glyphicon-folder-open"></i>&nbsp;浏览文件
	</button>
</form>
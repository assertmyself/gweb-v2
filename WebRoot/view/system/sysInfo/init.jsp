<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
var m = "${method}";
var icon = addIcons("code.gif,code_detail.gif");
$(function(){
	openNewWindow(openWindowId,"${title}","${ctx}/sysInfo/input.do?m=${method}",true);
});
</script>    

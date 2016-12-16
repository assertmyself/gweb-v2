<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
$(function() {
 //显示网关数据  为异步刷新准备
 $(".tr_dark").append("<td colspan='2' align='left'><div class='nesDiv' style='max-height: 460px;overflow-y: auto;position: relative;' > <c:forEach items='${list}' var='item'><div style='height: 30px;'>&nbsp;&nbsp;&nbsp;<input type='checkbox' name='mngNesId' value='${item.gwId}'<c:if test='${item.isChecked}'>checked</c:if><c:if test='${item.readOnly}'>disabled</c:if> align='absmiddle'><b>热点:${item.hotspot}</b><input type='hidden' name='treeId' value='${item.hotspot}'></div></c:forEach></div></td>");
});
</script>
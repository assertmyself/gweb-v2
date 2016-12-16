<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">

    $(function () {
        //initSize();
        /* $('#layout').layout(); 
        //点击panel时触发的事件
        $("#treeLeft").panel({
            onExpand: resizeGrid,
            onCollapse: resizeGrid
        }); */
        //加载左侧树
        loadAjaxData("treeLeft", "${ctx}/sysArea/tree.do");
        loadAjaxData("dataInfo", "${ctx}/sysArea/grid.do");
        
    });
</script>
<div class="ibox col-sm-12">
	<div class="ibox-content">
		<div class="row">
			<div class="col-sm-2" id="treeLeft"></div>
			<div class="col-sm-10 br-l"  id="dataInfo"></div>
		</div>
	</div>
</div>

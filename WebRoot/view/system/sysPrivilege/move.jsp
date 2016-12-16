<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
    function save(btn) {
        var formId = "bean";
        //提交保存
        saveAjaxData("${ctx}/sysPrivilege/moveSave.do", formId);
        loadAjaxData("treeLeft", "${ctx}/sysPrivilege/tree.do");
    }
     $(function () {
        //更改按钮
        
       var btns='<input type="button" value="确定" class="btn btn-primary" onclick="save(this)">&nbsp;'+
                 '<input type="button" value="取消" class="btn" onclick="closeWindow()">';
        $("#"+openWindowId+" .modal-footer").html(btns);

    });
</script>

<form:form commandName="bean" cssClass="form-horizontal">
    <input type="hidden" id="id" name="id" value="${bean.id}"/>

	<div class=" form-group">
		<label class="col-sm-4 control-label" >
			权限名称:
		</label>
        <label class="col-sm-7 control-label" style="text-align:left">
        	${bean.name}
        </label>
	</div>
	<div class="form-group-hz">
		<label >
			移动权限至:
		</label>
		<select id="parentid" name="parentid">
			<option value="">
				根节点
			</option>
			<c:forEach items="${sysList}" var="menu">
				<option value="${sys.id}">
					&nbsp;&nbsp;${sys.name}
				</option>
			</c:forEach>
		</select>
	</div>
</form:form>

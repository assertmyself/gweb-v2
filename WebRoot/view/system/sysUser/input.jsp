<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<style>
.v3_manager_area {
	display: none
}
</style>
<script type="text/javascript">
	var formId = "bean";
	$(function() {
		        //页面验证初始化
        var validateCondition = [
            {name: "loginName", rule: "validate[required,minSize[3],maxSize[50],custom[onlyLetterNumber]]"},
            {name: "person", rule: "validate[required]"},
            {name: "displayName", rule: "validate[required,maxSize[50],custom[onlyLetterNumberZH]]"},
            {name: "password", rule: "validate[maxSize[20]]"},
            {name: "confirmPassword", rule: "validate[maxSize[20]]"}
            //{name:"openDate", rule:"validate[required,custom[date],max[7]"},
            //{name:"closeDate", rule:"validate[required,custom[date],max[7]"},
        ];
        validateInit(validateCondition, formId);

		//更改按钮
        var btns="<button class=\"btn\" data-dismiss=\"modal\" aria-hidden=\"true\" id=\"cancelBtn\" ><i class='glyphicon glyphicon-remove'></i>&nbsp;取消</button>" +
                "<button type=\"button\" class=\"btn btn-primary\" id='confirmBtn' onclick= \"save(this)\" ><i class='glyphicon glyphicon-ok'></i>&nbsp;确定</button>";

		$("#" + openWindowId + " .modal-footer").html(btns);
		//点击input框弹出多选树
		$("#areaName").focus(function() {
			multiSelSysAreaNotCascade('selectId', 'area', 'areaName');
		});
		//编辑时如果是一级区域和二级区域用户，则显示区域选择框
		$.each($("input:checked"), function(i, n) {
			if ($(this).val() == 8 || $(this).val() == 9) {
				$("#v3_manager_id").removeClass("v3_manager_area");
				$("#areaName").val("");
			}
		})
		//如果用户是一级区域和二级区域用户，则显示区域选择input框
		$(":radio").change(function() {
			if ($(this).val() == 8 || $(this).val() == 9) {
				$("#v3_manager_id").removeClass("v3_manager_area");
				$("#areaName").val("");
			} else {
				$("#v3_manager_id").addClass("v3_manager_area")
			}
		});

		
		
	});

	//判断两次密码输的是否一致
	function sameToPassword() {
		var newP = $('#password');
		var conP = $('#confirmPassword');
		if (newP.val() != conP.val()) {
			showInfoMsg('两次密码不一致！请重新输入', null);
			$('#password').val("");
			$('#confirmPassword').val("");
			return;
		}
	}
	
	//保存操作
	function save(btn) {
		if (!validateForm(formId)) {
			return;
		}
		var newP = $('#password');
		var conP = $('#confirmPassword');

		if (newP.val() != conP.val()) {
			showInfoMsg('两次密码不一致！请重新输入', null);
			return;
		}
		//提交表单
		disableBtn(btn);
		saveAjaxData("${ctx}/sysUser/save.do", formId);
	}

	//显示姓名
	function doDisplayName() {
		var v = $("#personName").val();
		if (v) {
			$("#bean #displayName").val(v);
		}
	}
</script>
<form:form commandName="bean" cssClass="form-horizontal">
	<form:hidden path="id" />
	<div class="form-group-hz">
		<label>
			用户名：
		</label>
		<form:input path="loginName" cssClass="input_text" />
	</div>
	<div class="form-group-hz">
		<label>
			显示名称：
		</label>
		<form:input path="displayName" cssClass="input_text" />
	</div>




	<c:choose>
		<c:when test="${isAdd}">
			<div class="form-group-hz">
				<label>
					密码：
				</label>
				<form:input path="password" type="password" cssClass="input_text" />
			</div>
			<div class="form-group-hz">
				<label>
					密码确认：
				</label>
				<input type="password" name="confirmPassword" id="confirmPassword"
					value="" onchange="sameToPassword();" />
			</div>

		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose>



	<div class="form-group-hz">
		<label>
			是否有效：
		</label>
		<input type="checkbox" name="status" id="status" value="1"
			<c:if test="${bean.status=='1'}">checked=</c:if> />
	</div>


	<c:choose>
		<c:when test="${isAdd}">

			<div class="form-group-hz">
				<label>
					角色设置：
				</label>
				<div class="form-content">
					<c:forEach items="${roles}" var="item" varStatus="status">

						<!--	区域管理员用户，只显示区域管理员角色	-->
						<c:if test="${roleType==2}">
							<c:if test="${item.role.id==8 || item.role.id==9}">

								<input type="radio" name="roleId" id="roleId"
									value="${item.role.id}"
									<c:if test="${item.role.id==8}">checked</c:if> />
									${item.role.roleName}
							
									</c:if>
						</c:if>
						<!--	如果是系统管理员和超级管理员则显示全部角色	 -->
						<c:if test="${roleType==0}">
							<input type="radio" name="roleId" id="roleId"
								value="${item.role.id}"
								<c:if test="${item.role.id==3}">checked</c:if> />
									${item.role.roleName}
							
								</c:if>
					</c:forEach>

				</div>
			</div>
		</c:when>

		<c:otherwise>
			<div class="form-group-hz">
				<label>
					角色设置：
				</label>
				<div class="form-content">
					<c:forEach items="${roles}" var="item" varStatus="status">
						<!--	区域管理员用户，只显示区域管理员角色	-->
						<c:if test="${roleType==2}">
							<c:if test="${item.role.id==8 || item.role.id==9}">
								<input type="radio" name="roleId" id="roleId"
									value="${item.role.id}"
									<c:if test="${item.check}">checked="112"</c:if> />
											${item.role.roleName}
								</c:if>
						</c:if>
						<!--	如果是系统管理员和超级管理员则显示全部角色	 -->
						<c:if test="${roleType==0}">
							<input type="radio" name="roleId" id="roleId"
								value="${item.role.id}"
								<c:if test="${item.check}">checked="112"</c:if> />
										${item.role.roleName}
						</c:if>
					<c:if test="${status.index>0 && (status.index+1) % 2 == 0}"></br></c:if>
					</c:forEach>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
<!-- 
	<div class="form-group-hz" id="v3_manager_id">
		<label>
			管理区域：
		</label>
		<input type="text" name="areaName" id="areaName" class="input_text"
			value="${bean.area.areaName}" readonly="true" />
		<input type="hidden" name="area" id="area" value="${bean.area.id}" />
		<select name="selectId" id="selectId"
			style="width: 180px; display: none;" size="5"></select> 
	</div>
 -->
</form:form>
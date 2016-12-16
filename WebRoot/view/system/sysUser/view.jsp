<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="no_scrollbar">
	<div class="form_div">
		<table class="table table-hover">
			<tr class="tr_light">
				<td>
					用户名：
				</td>
				<td>
					${bean.loginName}
				</td>
			</tr>
			<tr class="tr_dark">
				<td>
					对应人员：
				</td>
				<td>
					${bean.person.name}
				</td>
			</tr>
			<tr class="tr_light">
				<td>
					显示名称：
				</td>
				<td>
					${bean.displayName}
				</td>
			</tr>
			<tr class="tr_dark">
				<td>
					是否有效：
				</td>
				<td>
					<input type="checkbox" name="status" id="status" value="1"
						<c:if test="${bean.status=='1'}">checked</c:if> disabled="true" />
				</td>
			</tr>
			<tr class="tr_light">
				<td>
					角色设置：
				</td>
				<td>
					<table border="0" align="left" width="95%">
						<tr>
							<c:forEach items="${roles}" var="item" varStatus="status">
								<td align="left" nowrap width="33%">
									<c:choose>
										<c:when test="${item.check}">
											<input type="checkbox" name="roleId" id="roleId"
												value="${item.role.id}" checked disabled="true" />
											<font color="blue">${item.role.roleName}</font>
										</c:when>
										<c:otherwise>
											<input type="checkbox" name="roleId" id="roleId"
												value="${item.role.id}" disabled="true" /> ${item.role.roleName}
                                    </c:otherwise>
									</c:choose>
								</td>
								<c:if test="${status.index>0 && (status.index+1) % 2 == 0}">
						</tr>
						<tr>
							</c:if>
							</c:forEach>
						</tr>
					</table>
				</td>
			</tr>



			<tr class="tr_dark">
				<td>
					管理区域：
				</td>
				<td>
					${bean.area.areaName}
				</td>
			</tr>

		</table>
	</div>
</div>
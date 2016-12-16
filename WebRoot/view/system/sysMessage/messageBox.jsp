<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="col-sm-12 animated fadeInRight">
	<div class="mail-box-header">

		<form method="get" action="#" class="pull-right mail-search">
			<div class="input-group">
				<input type="text" class="form-control input-sm" name="search"
					placeholder="搜索消息标题，正文等">
				<div class="input-group-btn">
					<button type="submit" class="btn btn-sm btn-primary">
						搜索
					</button>
				</div>
			</div>
		</form>
		<h2>
			${type}(${num})
		</h2>
		<div class="mail-tools tooltip-demo m-t-md">
			<div class="btn-group pull-right">
				<button class="btn btn-white btn-sm">
					<i class="fa fa-arrow-left"></i>
				</button>
				<button class="btn btn-white btn-sm">
					<i class="fa fa-arrow-right"></i>
				</button>

			</div>
			<button class="btn btn-white btn-sm" data-toggle="tooltip"
				data-placement="left" title="刷新邮件列表">
				<i class="fa fa-refresh"></i> 刷新
			</button>
			<button class="btn btn-white btn-sm" data-toggle="tooltip"
				data-placement="top" title="标为已读">
				<i class="fa fa-eye"></i>
			</button>
			<button class="btn btn-white btn-sm" data-toggle="tooltip"
				data-placement="top" title="标为重要">
				<i class="fa fa-exclamation"></i>
			</button>

		</div>
	</div>
	<div class="mail-box">

		<table class="table table-hover table-mail">
			<tbody>
				<c:forEach var="msg" items="${msgs}">
					<tr
						class="<c:choose><c:when test='${msg.status==1}'>read</c:when><c:otherwise>unread </c:otherwise> </c:choose>  ">
						<td class="check-mail">
							<input type="checkbox" class="i-checks">
						</td>
						<td class="mail-ontact">
							<a href="#" class="loadMainpage"
								rel="sysMessage/view.do?id=${msg.id}"
								tabName="<c:choose><c:when test='${msg.type==1}'>查看通知</c:when><c:when test='${msg.type==2}'>查看告警</c:when></c:choose>">${msg.title}</a>
						</td>
						<td class="mail-subject">
							<a href="#" class="loadMainpage"
								rel="sysMessage/view.do?id=${msg.id}"
								tabName="<c:choose><c:when test='${msg.type==1}'>查看通知</c:when><c:when test='${msg.type==2}'>查看告警</c:when></c:choose>">${msg.content}</a>
						</td>
						<td class="">
							<i class="fa fa-paperclip"></i>
						</td>
						<td class="text-right mail-date">
							${msg.createTime}
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<script>
	$(function(){
		$(".i-checks").iCheck({
			checkboxClass:"icheckbox_square-green",
			radioClass:"iradio_square-green",
			})
	});
</script>
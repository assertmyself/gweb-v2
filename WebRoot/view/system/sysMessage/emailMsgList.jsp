<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<a class="dropdown-toggle count-info" data-toggle="dropdown" href="#"
	aria-expanded="true"> <i class="fa fa-envelope"></i> <span
	class="label label-warning"</span>${num} </a>
<ul class="dropdown-menu dropdown-messages">
	<c:forEach var="msg" items="${msgs}">
		<li class="m-t-xs">
			<div class="dropdown-messages-box">
				<a href="#" rel="sysUser/info.do?id=${userId}" tabName="个人资料" class="pull-left loadMainpage"> <img
						alt="image" class="img-circle" src="${msg.photo}"> </a>
				<a href="#" rel="sysMessage/emailView.do?id=${msg.id }" tabName="查看邮件" class="loadMainpage" >
				<div class="media-body">
					<small class="pull-right">46小时前</small>
					<strong>${msg.title}</strong> ${msg.countent}
					<br>
					<small class="text-muted">${msg.data}</small>
				</div></a>
			</div>
		</li>

		<li class="divider"></li>
	</c:forEach>
	<li>
		<div class="text-center link-block">
			<a class="J_menuItem loadMainpage" href="#" rel="sysMessage/emailBox.do" tabName="所有邮件"> <i class="fa fa-envelope"></i>
				<strong> 查看所有邮件</strong> </a>
		</div>
	</li>
</ul>
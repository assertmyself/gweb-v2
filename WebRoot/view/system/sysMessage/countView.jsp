<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<a class="dropdown-toggle count-info" data-toggle="dropdown" href="#"
	aria-expanded="true"> <i class="fa fa-bell"></i> <span
	class="label label-primary">${alarm+msg }</span> </a>
<ul class="dropdown-menu dropdown-alerts">
	<li>
		<a href="#" rel="sysMessage/messageBox.do?type=1" tabName="通知" class="loadMainpage">
			<div>
				<i class="fa fa-envelope fa-fw"></i> 您有${msg }条通知未查看
			</div> </a>
	</li>
	<li class="divider"></li>
	<li>
		<a href="#" rel="sysMessage/messageBox.do?type=2" tabName="告警" class="loadMainpage">
			<div>
				<i class="fa fa-qq fa-fw"></i> ${alarm }条告警未查看
			</div> </a>
	</li>
	<li class="divider"></li>
	<li>
		<div class="text-center link-block">
			<a class="J_menuItem loadMainpage" href="#" rel="sysMessage/messageBox.do" tabName="消息" > <strong>查看所有
			</strong> <i class="fa fa-angle-right"></i> </a>
		</div>
	</li>
</ul>
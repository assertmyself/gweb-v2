<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="col-sm-3">
	<div class="ibox float-e-margins">
		<div class="ibox-content mailbox-content">
			<div class="file-manager">
				<a class="btn btn-block btn-primary compose-mail"
					href="mail_compose.html">写信</a>
				<div class="space-25"></div>
				<h5>
					文件夹
				</h5>
				<ul class="folder-list m-b-md" style="padding: 0">
					<li>
						<a href="#"> <i class="fa fa-inbox "></i> 收件箱 <span
							class="label label-warning pull-right">16</span> </a>
					</li>
					<li>
						<a href="#"> <i class="fa fa-envelope-o"></i> 发信</a>
					</li>
					<li>
						<a href="#"> <i class="fa fa-certificate"></i> 重要</a>
					</li>
					<li>
						<a href="#"> <i class="fa fa-file-text-o"></i> 草稿 <span
							class="label label-danger pull-right">2</span> </a>
					</li>
					<li>
						<a href="#"> <i class="fa fa-trash-o"></i> 垃圾箱</a>
					</li>
				</ul>
				<h5>
					分类
				</h5>
				<ul class="category-list" style="padding: 0">
					<li>
						<a href="#"> <i class="fa fa-circle text-navy"></i> 工作</a>
					</li>
					<li>
						<a href="#"> <i class="fa fa-circle text-danger"></i> 文档</a>
					</li>
					<li>
						<a href="#"> <i class="fa fa-circle text-primary"></i> 社交</a>
					</li>
					<li>
						<a href="#"> <i class="fa fa-circle text-info"></i> 广告</a>
					</li>
					<li>
						<a href="#"> <i class="fa fa-circle text-warning"></i> 客户端</a>
					</li>
				</ul>

				<h5 class="tag-title">
					标签
				</h5>
				<ul class="tag-list" style="padding: 0">
					<li>
						<a href="#"><i class="fa fa-tag"></i> 朋友</a>
					</li>
					<li>
						<a href="#"><i class="fa fa-tag"></i> 工作</a>
					</li>
					<li>
						<a href="#"><i class="fa fa-tag"></i> 家庭</a>
					</li>
					<li>
						<a href="#"><i class="fa fa-tag"></i> 孩子</a>
					</li>
					<li>
						<a href="#"><i class="fa fa-tag"></i> 假期</a>
					</li>
					<li>
						<a href="#"><i class="fa fa-tag"></i> 音乐</a>
					</li>
					<li>
						<a href="#"><i class="fa fa-tag"></i> 照片</a>
					</li>
					<li>
						<a href="#"><i class="fa fa-tag"></i> 电影</a>
					</li>
				</ul>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
</div>
<div class="col-sm-9 animated fadeInRight">
	<div class="mail-box-header">
		<form method="get" action="#" class="pull-right mail-search">
			<div class="input-group">
				<input type="text" class="form-control input-sm" name="search"
					placeholder="搜索邮件标题，正文等">
				<div class="input-group-btn">
					<button type="submit" class="btn btn-sm btn-primary">
						搜索
					</button>
				</div>
			</div>
		</form>
		<h2>
			收件箱 (16)
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
			<button class="btn btn-white btn-sm" data-toggle="tooltip"
				data-placement="top" title="标为垃圾邮件">
				<i class="fa fa-trash-o"></i>
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
								rel="sysMessage/emailView.do?id=${msg.id}" tabName="查看邮件">${msg.title}</a>
						</td>
						<td class="mail-subject">
							<a href="#" class="loadMainpage"
								rel="sysMessage/emailView.do?id=${msg.id}" tabName="查看邮件">${msg.content}</a>
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
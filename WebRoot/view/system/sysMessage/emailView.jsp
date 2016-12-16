<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %><!DOCTYPE html>
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
		<h2>
			<c:choose>
				<c:when test='${msg.type==1}'>查看通知</c:when>
				<c:when test='${msg.type==2}'>查看告警</c:when>
				<c:otherwise>查看邮件</c:otherwise>
			</c:choose>
		</h2>
		<div class="mail-tools tooltip-demo m-t-md">


			<h3>
				<span class="font-noraml">主题： </span>${msg.title}
			</h3>
			<h5>
				<span class="pull-right font-noraml">${msg.createTime}</span>
				<span class="font-noraml">创建者： </span>${createName}
			</h5>
		</div>
	</div>
	<div class="mail-box">


		<div class="mail-body">
			<p>
				${msg.content}
			</p>
		</div>
		<div class="mail-body text-right tooltip-demo">
			<a class="btn btn-sm btn-white" href="mail_compose.html"><i
				class="fa fa-reply"></i> 回复</a>
			<a class="btn btn-sm btn-white" href="mail_compose.html"><i
				class="fa fa-arrow-right"></i> 下一封</a>
			<button title="" data-placement="top" data-toggle="tooltip"
				type="button" data-original-title="打印这封邮件"
				class="btn btn-sm btn-white">
				<i class="fa fa-print"></i> 打印
			</button>
			<button title="" data-placement="top" data-toggle="tooltip"
				data-original-title="删除邮件" class="btn btn-sm btn-white">
				<i class="fa fa-trash-o"></i> 删除
			</button>
		</div>
		<div class="clearfix"></div>
	</div>
</div>

<!-- Mirrored from www.zi-han.net/theme/hplus/mail_detail.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:10 GMT -->
</html>

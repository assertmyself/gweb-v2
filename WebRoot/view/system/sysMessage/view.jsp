<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %><!DOCTYPE html>
<div class="col-sm-12 animated fadeInRight">
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

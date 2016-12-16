<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="wrapper wrapper-content">
	<div class="row animated fadeInRight">
		<div class="col-sm-4">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>
						个人资料
					</h5>
				</div>
				<div>
					<div class="ibox-content no-padding border-left-right">
						<img alt="image" class="img-responsive" src="${profile }">
					</div>
					<div class="ibox-content profile-content">
						<h4>
							<strong>${name }</strong>
						</h4>
						<p>
							<i class="fa fa-map-marker"></i> ${address }
						</p>
						<h5>
							关于我
						</h5>
						<p>
							${memo }
						</p>

						<div class="row m-t-lg">
							<div class="col-sm-4">
								<span class="bar">手机</span>
								<h5>
									<strong><i class="fa fa-mobile-phone"></i>
										&nbsp;${moblie }</strong>
								</h5>
							</div>
							<div class="col-sm-4">
								<span class="line">qq</span>
								<h5>
									<strong> <i class="fa fa-qq"></i>&nbsp;${qq }</strong>
								</h5>
							</div>
							<div class="col-sm-4">
								<span class="bar">邮箱</span>
								<h5>
									<strong><i class="fa fa-envelope-o"></i>&nbsp;${email}</strong>
								</h5>
							</div>
						</div>
						<div class="user-button">
							<div class="row">
								<div class="col-sm-6">
									<button type="button" class="btn btn-primary btn-sm btn-block">
										<i class="fa fa-envelope"></i> 发送消息
									</button>
								</div>
								<div class="col-sm-6">
									<button type="button" class="btn btn-default btn-sm btn-block">
										<i class="fa fa-coffee"></i> 取消
									</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-sm-8">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>
						系统资料
					</h5>
				</div>
				<div class="ibox-content ">
					<h4>
						${userName }
					</h4>
					<div class="row">
						<div class="col-sm-3">
							<span class="bar">用户别名：</span>
							<h5>
								<i class="fa fa-male"></i>&nbsp;${realName }
							</h5>
						</div>
						<div class="col-sm-3">
							<span class="line">角色：</span>
							<h5>
								<i class="fa fa-graduation-cap"></i>&nbsp;${sysRole }
							</h5>
						</div>
						<div class="col-sm-3">
							<span class="bar">ip地址：</span>
							<h5>
								<i class="fa fa-internet-explorer"></i>&nbsp;${ipAddress}
							</h5>
						</div>
						<div class="col-sm-3">
							<span class="bar">登陆时间：</span>
							<h5>
								<i class="fa fa-calendar"></i>&nbsp;${time}
							</h5>
						</div>
					</div>
					<div class="row m-t-lg">
						<div class="col-sm-12 ">
							<div class="row">
								<h4 style="padding-left:20px;">
									<strong>操作记录</strong>
								</h4>
							</div
						</div>
						<div class="col-sm-12 ">
							<div class="row">
								<div class="col-sm-2">
									操作模块：
								</div>
								<div class="col-sm-3">
									路径：
								</div>
								<div class="col-sm-2">
									操作类型：
								</div>
								<div class="col-sm-3">
									操作时间：
								</div>
								<div class="col-sm-2">
									ip：
								</div>
							</div>
							<c:forEach var="log" items="${sysLog}">
								<div class="row ">
									<div class="col-sm-2">
										${log.moudle }
									</div>
									<div class="col-sm-3">
										${log.pageUrl }
									</div>
									<div class="col-sm-2">
										${log.eventType }
									</div>
									<div class="col-sm-3">
										${log.enterTime }
									</div>
									<div class="col-sm-2">
										${log.ipAddress }
									</div>
								</div>
							</c:forEach>
						</div>
						<div class="ibox-content profile-content">
							<button class="btn btn-primary btn-block m">
								<i class="fa fa-arrow-down"></i> 显示更多
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

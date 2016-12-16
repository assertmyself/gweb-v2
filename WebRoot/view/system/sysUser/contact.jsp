<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="row" id="contact">
	<c:forEach var="user" items="${userInfos}">
		<div class="col-sm-4">
			<div class="contact-box">
				<a href="#" class="contactTabLoad loadMainpage"
					rel="sysUser/info.do?id=${user.id}" tabname="个人资料">
					<div class="col-sm-4">
						<div class="text-center">
							<img alt="image" class="img-circle m-t-xs img-responsive"
								src="${user.profile }">
							<div class="m-t-xs font-bold">${user.role }</div>
						</div>
					</div> </a>
				<div class="col-sm-8">
					<a href="#" class="contactTabLoad loadMainpage"
						rel="sysUser/info.do?id=${user.id}" tabname="个人资料">
						<h3>
							<strong>${user.realName }</strong>
						</h3>
						<i class="fa fa-map-marker"></i> ${user.address } </a>
					<address>
						<strong>${user.dept }</strong><br>
						E-mail:${user.email }<br>
						qq:${user.qq }<br>
						<abbr title="Phone">Tel:</abbr> ${user.moblie }
					</address>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</c:forEach>
</div>
<script>
$(document).ready(function() {
	$(".contact-box").each(function() {
		animationHover(this, "pulse")
	})
});
</script>
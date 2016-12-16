<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<style type="text/css">

.userLogStat {
	width: 100%;
}

.userLogStat .userPea,
.userLogStat .userLog,
.userLogStat .userRole,
.userLogStat .userTerminal {
	padding-top: 20px;
	height: 300px;
}

</style>
</head>
	<body>
		<div class="container userLogStat">
			<div class="row">
				<!--	峰值用户统计		-->
				<div class="col-sm-8 col-md-8 col-lg-8 userPea" id="userPeakStat">
				</div>			
				<!--	用户登录次数统计		-->
				<div class="col-sm-4 col-md-4 col-lg-4 userLog" id="userLogStat"></div>
			</div>
			<div class="row">
				<!--	角色点击统计		-->
				<div class="col-sm-8 col-md-8 col-lg-8 userRole" id="roleStat"></div>
				<!--	终端（浏览器）统计	-->
				<div class="col-sm-4 col-md-4 col-lg-4 userTerminal" id="terminalStat"></div>
			</div>
		</div>
		<script type="text/javascript">
			$(function(){
				//峰值用户统计
				var granularity = $(".dateSelect").val();
				var option = {
						customOpts: {
							type: 'line',
							ajax: true,
							url: '${ctx}/userLogStat/getUserPeakStat.do?granularity=' + granularity,
							date: true
						},
						highchartOpts:{
							title: {
								text: '峰值用户统计'
							}
						}	
				};
				initHighchart("userPeakStat",option);
				
				$(".userLogStat").on("change",".dateSelect",function(){
					var unit = $(this).val();
					option.customOpts.url = '${ctx}/userLogStat/getUserPeakStat.do?granularity=' + unit;
					reload("userPeakStat",option)
				});
				
				//角色登录次数统计
				var roleLogOption = {
						customOpts: {
							type: 'column',
							ajax: true,
							url: '${ctx}/userLogStat/getRoleLogStat.do'
						},
						highchartOpts:{
							title: {
								text: '角色登录次数统计'
							}
						}	
				};
				initHighchart("roleStat",roleLogOption);
				
				//用户登录次数统计
				var userLogOption = {
						customOpts: {
							type: 'pie',
							ajax: true,
							url: '${ctx}/userLogStat/getUserLogStat.do'
						},
						highchartOpts:{
							title: {
								text: '用户登录次数统计'
							}
						}	
				};
				initHighchart("userLogStat",userLogOption);
				
				//终端（浏览器）统计
				var terminalStatOption = {
						customOpts: {
							type: 'pie',
							ajax: true,
							url: '${ctx}/userLogStat/getTerminalStat.do'
						},
						highchartOpts:{
							title: {
								text: '终端（浏览器）统计'
							}
						}	
				};
				initHighchart("terminalStat",terminalStatOption);
			})
		</script>
	</body>
</html>
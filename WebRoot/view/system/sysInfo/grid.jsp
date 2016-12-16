<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<style type="text/css">
.contain-one,.contain-two{
	margin: 0px;
	padding: 0px;
	width:100%;
	background: #F1F1F1;
	float: left;
}
.cpu-contain, .memory-contain, .system-contain, .other-contain,.time-contain{
	padding: 15px;
	wdith:30%;
	background-color: #F1F1F1;
}
.cpu-use,.memory-use,.sys-use,.sys-time {
	height: 292px;
}
</style>
<script type="text/javascript">
	$(function () {
		//内存信息统计
		var memoryUseOption = {
			customOpts: {
				type: 'pie',
				ajax: true,
				url: '${ctx}/sysInfo/getMemUsedPercent.do'
			},
			highchartOpts:{
				title: {
					text: '内存信息'
				}
			}	
		};
		initHighchart("memoryUse",memoryUseOption);
	
		//CPU信息
		var cpuUseOption = {
			customOpts: {
				type: 'pie',
				ajax: true,
				url: '${ctx}/sysInfo/getCpuUsedPercent.do'
			},
			highchartOpts:{
				title: {
					text: 'CPU信息'
				}
			}	
		};
		initHighchart("cpuUse",cpuUseOption);
		
		//进程信息(内存)
		var sysUseOption = {
			customOpts: {
				type: 'pie',
				ajax: true,
				url: '${ctx}/sysInfo/getThreadUsedPercent.do'
			},
			highchartOpts:{
				title: {
					text: '进程信息(内存)'
				}
			}	
		};
		initHighchart("sysUse",sysUseOption);
		
		//进程信息(内存)
		var sysTimeOption = {
			customOpts: {
				type: 'line',
				ajax: true,
				url: '${ctx}/sysInfo/getTreadRunTime.do'
			},
			highchartOpts:{
				title: {
					text: '进程运行时间',
					 x: -20
				},
				yAxis: {
			        title: {
			            text: 'Minute'
			        },
			        plotLines: [{
			            value: 0,
			            width: 1,
			            color: '#808080'
			        }],
			       min: 0,
			       tickInterval: 50
			    },
			    tooltip: {
			        valueSuffix: 'm'
			    },
			    legend: {
			        layout: 'vertical',
			        align: 'right',
			        verticalAlign: 'middle',
			        borderWidth: 0
			    }
			}	
		};
		initHighchart("sysTime",sysTimeOption);
	})
</script>
<div class="contain-one">
	<div class="col-sm-12 col-md-4 cpu-contain">
		<div id="cpuUse" class="cpu-use"></div>
	</div>
	<div class="col-sm-12 col-md-4 memory-contain">
		<div id="memoryUse" class="memory-use"></div>
	</div>
	<div class="col-sm-12 col-md-4 memory-contain">
		<div id="sysUse" class="sys-use"></div>
	</div>
</div>
<div class="contain-two">
	<div class="col-sm-12 col-md-12 time-contain">
		<div id="sysTime" class="sys-time"></div>
	</div>
</div>

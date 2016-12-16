<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
 <head>

<style type="text/css">

.oper-logStat {
	width: 100%;
}
		
.oper-logStat .succFail-statistic, 
.oper-logStat .operFoldLinePlot,
.oper-logStat .menuClick-statistic,
.oper-logStat .operClick-statistic { 
  height: 300px; 
  margin-top: 15px;
}

.oper-logStat .dateSelect{
	right:15% !important;
	top:3% !important;
}

</style>
  
  	<script type="text/javascript">
  		
 		$(function(){
 	  		//菜单点击次数统计 -柱状图
 	  		setHighchart("column","statisticMenuClick.do","菜单点击统计","menuClickStatistic");
 	  		//操作类型点击次数统计信息 - 饼图
			setHighchart("pie","statisticOperClick.do","操作类型统计","operClickStatistic");
			//操作成功、失败 统计  - 饼图
			setHighchart("pie","statisticResult.do","操作成功/失败统计","succFailStatistic");
			//按时间统计操作类型
			var granularity = $(".dateSelect").val();
				var option = {
						customOpts: {
							type: 'line',
							ajax: true,
							url: '${ctx}/operLogStat/statOperFoldLinePlot.do?granularity=' + granularity,
							date: true
						},
						highchartOpts:{
							title: {
								text: '操作统计'
							}
						}	
				};
				initHighchart("operFoldLinePlot",option);
				
				$(".oper-logStat").on("change",".dateSelect",function(){
					var unit = $(this).val();
					option.customOpts.url = '${ctx}/operLogStat/statOperFoldLinePlot.do?granularity=' + unit;
					reload("operFoldLinePlot",option);
				});
		
 		});  		
 		
 		/**
 		 * 设置
 		 * @param type 图形类型
 		 * @param url 获取数据的路径
 		 * @param title 标题
 		 * @param ele 容器
 		 */
 		 function setHighchart(type,url,title,ele){
 		 	var operOption = {
			customOpts: {
				type: type,
				ajax: true,
				url: "${ctx}/operLogStat/" + url
			},
			highchartOpts:{
				title: {
					text: title
				}
			}	
		};
		initHighchart(ele,operOption);
		return operOption;
 		}
  		
  	</script>
</head>
  
  <body>
	<div class="oper-logStat container">
		<!--  -->
		<div class="row">
			<div id="menuClickStatistic" class="col-sm-8 col-md-8 col-lg-8 menuClick-statistic"></div>
			<div id="operClickStatistic" class="col-sm-4 col-md-4 col-lg-4 operClick-statistic"></div>
		</div>
		<!--  -->
		<div class="row">
			<div id="operFoldLinePlot" class="col-sm-8 col-md-8 col-lg-8 operFoldLinePlot"></div>
			<div id="succFailStatistic" class="col-sm-4 col-md-4 col-lg-4 succFail-statistic"></div>
			<!-- 
			<div class="dataLoading"><i class="fa fa-cog fa-spin fa-lg fa-fw"></i>数据正在加载中......</div>
			-->
   		</div>
	</div>
  </body>
</html>

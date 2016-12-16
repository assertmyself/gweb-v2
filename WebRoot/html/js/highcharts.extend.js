/**
 * highchart 组件封装
 * @author Administrator
 */
Highcharts.setOptions({
		lang: {
	        contextButtonTitle: '打印图表',
	        downloadJPEG: '导出为JPEG',
	        downloadPDF: '导出为PDF',
	        downloadPNG: '导出为PNG',
	        downloadSVG: '导出为SVG',
	        printChart: '打印图表',
	        resetZoom: '重置',
	        resetZoomTitle: '重置缩放比例'
	        
		}
	});
//封装highchart组件，使其更好使用
function initHighchart(container,option){
	var chart;
	Highcharts.setOptions({colors:['#95CEFF','#F7A35C','#00CC00','#90ED7D','#CCCC66', '#CCFFCC','#CCFF99', '#CCCC33']});
	var defaultOption = {
		customOpts : {
			ajax: false,
			date: false,
			dateValue: ''
		},	
		//折线图
		line : {
			chart: {
				type: 'line'
			},
			title: {
				text: 'line test',
				x: -20
			},
			xAxis: { 
				categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun','Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'] 
			},
			yAxis: {
				title: {
					text: ''
				}, 
				allowDecimals: false,
				plotLines: [{ 
					value: 0, 
					width: 1, 
					color: '#808080' 
				}],
				min: 0 
			},
			credits: {
				enabled: false
	        },
	        series: []
		},
		//柱状图
		column: {
			 chart: {
	            type: 'column'
	        },
	        title: {
	            text: 'column test'
	        },
	        xAxis: {
	            categories: []
	        },
	        yAxis: {
	            title: {
	                text: ''
	            },
	            allowDecimals: false
	        },
	        plotOptions: {
	            column: {
	                pointPadding: 0.2,
	                borderWidth: 0
	            }
	        },
	        credits: {
	        	enabled: false
	        },
	        series: []
		},
		//饼图
		pie: {
			chart: {
				type: 'pie',
	            plotBackgroundColor: null,
	            plotBorderWidth: null,
	            plotShadow: false
	        },
	        title: {
	        	text: "example"
	        },
	        tooltip: {
	    	    pointFormat: '{series.name}: <b>{point.y} ({point.percentage:.1f}%) </b>'
	        },
	        plotOptions: {
	            pie: {
	                allowPointSelect: true,
	                cursor: 'pointer',
	                showInLegend: true,
	                dataLabels: {
	                	enabled: false
	                }
	            }
	        },
	        legend: {
	            itemDistance: 5,
	            lineHeight : 8
	        },
	     	series: [],
	   		credits: {
	        	enabled: false   // 去除版权信息
	        }
		},
		multichart:{
			chart: {
				type: 'multichart'
			},
			title: { 
				text: 'Combination chart' 
			}, 
			xAxis: { 
				categories: ['Apples', 'Oranges', 'Pears', 'Bananas', 'Plums'] 
			},
			yAxis: {
	            title: {
	                text: ''
	            },
	            allowDecimals: false
	        },
			series: [],
	   		credits: {
	        	enabled: false   // 去除版权信息
	        }
		}
	};
	//深度拷贝
	var options = {},custom = {};
	custom = $.extend(true,{},defaultOption.customOpts,option.customOpts || {});
	if(option && option.customOpts && option.customOpts.type){
		for(k in defaultOption){
			if(k == option.customOpts.type){
				options = $.extend(true,{}, defaultOption[k], option.highchartOpts || {});
			}
		}
	}
	var url = "";
	if(options && option.customOpts && option.customOpts.url){
		url = option.customOpts.url;
	}
	if(option && option.customOpts.ajax){
		//执行数据解析
		//显示进度条
		showProcessInfo(container);
		var ajaxOption = {
			showLoading: false,
			showSuccessInfo: false,
			successFnc: function(ajaxData){
				hideProcessInfo(container);
				parseHighchartData(options,ajaxData);
				$("#" + container).highcharts(options);
				if(custom.date){
					timeSelect(container, custom.dateValue);
				}
			},
			errorFnc: function(){
				hideProcessInfo(container);
				$("#" + container).highcharts(options);
				if(custom.date){
					timeSelect(container);
				}
			}
		}
		saveAjaxData(url ,null,null,ajaxOption);
	}else{
		$("#" + container).highcharts(options || {});
		if(custom.date){
			timeSelect(container);
		}
	}
	return chart;
}

/**
 * 转换highchart数据
 * 约定:
 * 折线图数据格式：
 * {"name":{"x":"y1","x":"y2","x":"y3"},"name":{"x":"y4","x":"y5","x":"y6"},...}
 * 
 * 柱状图数据格式：
 * {"name":{"x":"y1","x":"y2","x":"y3"},"name":{"x":"y4","x":"y5","x":"y6"},...}
 * 
 * 饼图数据格式：
 * {"name":[["x1":"y1"],["x2":"y2"],["x3":"y3"],...]}
 * 
 * 混合图数据格式：
 * [{type:"",name:"",categories:[],data:[]},{type:"",name:"",categories:[],data:[]}]  
 * 
 * @param option 设置
 * @param data 数据
 */
function parseHighchartData(option,data){
	if(option.chart.type == "line"){
		transferLineData(option,data);
	}else if(option.chart.type == "column"){
		transferLineData(option,data);
	}else if(option.chart.type == "pie"){
		transferPieData(option,data);
	}else if(option.chart.type == "multichart"){
		transferMultichartData(option,data);
	}
}

/**
 * 转换折线数据
 * 
 * @param option 设置
 * @param data 服务器端返回的折线图数据
 * @return 填充数据后的数据
 */
function transferLineData(option,data){
	option.xAxis.categories.length = 0;
	for(var t in data){
		var serie = {
			name: "",
			data: [],
			dataLabels: {
             	enabled: true
            }
		};
		serie.name = t;
		var d = data[t];
		for(var y in d){
			option.xAxis.categories.push(y);
			serie.data.push(parseInt(d[y]));
		}
		option.series.push(serie);
	}
}

/**
 * 转换饼图数据
 * 
 * @param option 设置
 * @param data 服务器端返回的饼图数据
 * @return 填充数据后的数据
 */
function transferPieData(option,data){
	for(var t in data){
		var serie = {
		   type: 'pie',
		   name: '',
		   data: []
		 };
		serie.name = t;
		serie.data = data[t];
	}
	option.series.push(serie);
}

/**
 * 混合图数据转化:该部分没有封装。特殊化方法：本不应该放在这里。
 * [{type:"",name:"",categories:[],data:[]},{type:"",name:"",categories:[],data:[]}]
 * @param option 设置
 * @param data 服务器端返回的混合数据
 * @return 填充后的数据
 */
function transferMultichartData(option,data){
	for(var t in data){
		var serie = {
			type:'',
			name:'',
			data:[],
			dataLabels: {
             	enabled: true
            }
		};
		serie.type = data[t].type;
		serie.name = data[t].name;
		var d = data[t];
		var row = d.data;
		if(serie.type == 'pie'){
			var labels = { 
					items: [{ 
						html: '版本统计', 
						style: { 
							left: '600px', 
							top: '-40px', 
							color: 'black' 
								} 
					}] 
			};
			for(var k in row){
				var a = row[k];
				if(a == null){
					continue;
				}
				if(a.length == 2){
					a[1] = parseInt(a[1]);
				}
			}
			option.labels = labels;
			serie.data = row;
			serie.center = [600,15];
			serie.size = 110;
			serie.showInLegend = false;
			serie.dataLabels.enabled = false;
			option.series.push(serie);
			continue;
		}
		option.xAxis.categories = d.categories;
		for(k in row){
			serie.data.push(parseInt(row[k]));
		}
		if(serie.type == 'spline'){
			var marker = {
				lineWidth: 1,
				lineColor: Highcharts.getOptions().colors[3]
			};
			serie.marker = marker;
			serie.color = Highcharts.getOptions().colors[3];
		}
		option.series.push(serie);
	}
}

/**
 * 添加时间选择器
 * 
 * @param container 容器
 */
function timeSelect(container,value){
	var htmlContent = "<select id='dateSelect'  class='form_select dateSelect' style='position: absolute;z-index:1;right:15%;top:10%;width:100px;'>";
	if(value == 'HOUR' || value == ''){
		htmlContent = htmlContent + "<option value='HOUR' selected = 'true'>时</option>"
						+"<option value='DAY'>天</option>"
						+"<option value='MONTH'>月</option>"
						+"</select>";
	}else if(value == 'DAY'){
		htmlContent = htmlContent + "<option value='HOUR'>时</option>"
						+"<option value='DAY' selected = 'true'>天</option>"
						+"<option value='MONTH'>月</option>"
						+"</select>";
	}else if(value == 'MONTH'){
		htmlContent = htmlContent + "<option value='HOUR'>时</option>"
						+"<option value='DAY'>天</option>"
						+"<option value='MONTH' selected = 'true'>月</option>"
						+"</select>";
	}
	$("#"+container).prepend(htmlContent);
}

function reload(container,option){
	var ht_date_slect = document.getElementById("dateSelect");
	var value = ht_date_slect.value;
	if(option.customOpts.date){
		option.customOpts.dateValue = value;
	}
	initHighchart(container,option);
}


function setChart(chart, name, categories, data) { 
	chart.xAxis[0].setCategories(categories, false); 
	chart.series[0].remove(false); 
	chart.addSeries({
		type: 'column',
		name: name, 
		data: data
	}, false); 
	chart.redraw(); 
}

/*************************************************************************************************************/
/*
var startDate = getYMDTimeOther(0).substring(0,9)+" 00:00:00";
var endDate = getYMDTimeOther(0);
var granularity = "hour";
var gate = "";
var method = "APClientStat";
var ps = pageSize();
var winw = ps.WinW;
var count = 0;
$(".navbar-inner").css("width",winw-1);
$(".main-content").css("width",winw-207);



function PmStatHighchart(container, option){
	var defaultOpt = {
		chart: { 
		}, 
		colors: ['#F7A35C', '#95CEFF','#CCFFCC','#90ED7D','#CCCC66','#00CC00', '#CCFF99', '#CCCC33'],
		title: { 
			text: 'test' ,
			style: {
				fontWeight: 'bold'
			}
		}, 
		xAxis: { 
			type: 'datetime',
			tickInterval: 2*60*60*1000,
			max: 24*3600*1000-1,
			labels: { 
				formatter: function() { 
					if(granularity == "month" || granularity == "year" || granularity == "week"){
						return this.value;
					}
   				var b = getTimeByMillion(startDate,this.value,granularity);
   				if(dateDiff(endDate,startDate)==1){
   					b = b.substring(11);
   				}
					return b 
				} 
			},
			categories:[]
		}, 
		yAxis: { 
			min: 0,
			title: { 
				text: '数量' 
			}, 
			labels: { 
				formatter: function() { 
					return this.value 
					}
				} 
			}, 
		tooltip: { 
			crosshairs: {
				width: 0.5,
				color: 'red',
				dashStyle: 'Solid'
			}, 
			shared: true,
			useHTML: true,
			formatter: function () {
	            var s = "<div style='font-size:10px;'>"+getTimeByMillion(startDate,this.x,granularity)+"</div>";
	            if(granularity == "month" || granularity == "year" || granularity == "week"){
					s = "<div style='font-size:10px;'>"+this.x+"</div>";
				}
	            $.each(this.points, function () {
	                s += "<span style='color:"+this.point.color+";font-size:16px;'>●</span>  " +this.series.name + ": " +
	                     "<b>"+this.y+" </b><br/>";
	            });
	            return s;
			}
		}, 
		plotOptions: {
			spline: {
				marker: {
					enabled: true
				},
				pointInterval: 60*60*1000 // one hour 
			}
        },
		credits: {
			enabled: false
		},
		series: [],
		legend: {
			align: 'left',
			borderWidth: 1,
			x: 50,
			y: 15
		}
	};
	var opt = $.extend(true,{}, defaultOpt, option.highchartOpts || {});
	opt.customOpts = $.extend(true,{},option.customOpts);
	asyncGetFlowData(container,opt);
}

function asyncGetFlowData(container,options){
	var gateVal = $("#device").val();
	var url = options.customOpts.url;
	count = count + 1;
	if((gateVal == "" || gateVal == null) && count >1){
	   	 showInfoMsg("请先选择设备！");
	   	 return ;
	 }
	 $("#" + container).empty();
	 showProcessInfo(container);
	 $.ajax({
		type:'GET',
		dataType:'json',
		url: url,
		success: function(data){
		 	hideProcessInfo(container);
			var days = dateDiff(endDate,startDate);
			if(granularity == "hour"){
				if(days == 1){
					options.xAxis.tickInterval = 2*60*60*1000;
					options.plotOptions.spline.pointInterval = 60*60*1000;
					options.xAxis.max = 24*60*60*1000-1;
				}
				if(days>1 && days<6){
					options.xAxis.tickInterval = 24*60*60*1000;
					options.plotOptions.spline.pointInterval = 60*60*1000;
					options.xAxis.max = days*24*60*60*1000-1;
				}
			}
			if(granularity == "day"){
				if(days>1 && days<6){
					options.xAxis.tickInterval = 24*60*60*1000;
					options.plotOptions.spline.pointInterval = 24*60*60*1000;
					options.xAxis.max = days*24*60*60*1000-1;
				}
				if(days>5 && days<31){
					options.xAxis.tickInterval = 24*3600*1000;
					options.plotOptions.spline.pointInterval = 24*3600*1000;
					options.xAxis.max = days*24*3600*1000-1;
				}
			}
			options.series.length = 0;
			options.xAxis.categories.length = 0;
			for(var key in data.data){
				var seriesOptions = {
					name : '',
					data : []
				};
				seriesOptions.name = key;
				for(var a in data.data[key])  {
					for(var b in data.data[key][a]){
						if(b == "pointY"){
							seriesOptions.data.push(parseFloat(data.data[key][a][b]));
						}else{
							if(granularity == "week" || granularity == "month" || granularity == "year"){
								options.xAxis.categories.push(data.data[key][a][b]);
								options.xAxis.tickInterval = null;
								options.plotOptions.spline.pointInterval = null;
								options.xAxis.max = null;
								options.xAxis.type = null;
							}
						}	
					}  
				}  
				options.series.push(seriesOptions);
			}
			$('#' + container).highcharts(options);
			pmTimeSelect(container,options);
		},
		error: function(e){
			showErrorMsg("获取数据失败");
			pmTimeSelect(container);
		}
	});
}

function onclickTody(container,options){
	container = $(container).attr("id");
	$("#plotYear,#plotMonth,#plotWeek,#plotDay").css("display","none");
	$("#plotHour").css("display","block");
	$("#plotHour").addClass("mouse");
	startDate = getYMDTimeOther(0);
	endDate = getYMDTimeOther(1);
	granularity = "hour";
	var gateVal = $("#serchTreeVal").val();
	gate = gateVal;
	asyncGetFlowData(container,options);
}

function onclickYesterday(container,options){
	container = $(container).attr("id");
	$("#plotYear,#plotMonth,#plotWeek,#plotDay").css("display","none");
	$("#plotHour").css("display","block");
	$("#plotHour").addClass("mouse");
	startDate = getYMDTimeOther(-1);
	endDate = getYMDTimeOther(0);
	granularity = "hour";
	var gateVal = $("#serchTreeVal").val();
	gate = gateVal;
	asyncGetFlowData(container,options);
}

function onclickSevendays(container,options){
	container = $(container).attr("id");
	$("#plotYear,#plotMonth,#plotHour").css("display","none");
	$("#plotDay,#plotWeek").css("display","block");
	$("#plotYear,#plotMonth,#plotWeek,#plotDay,#plotHour,#plotYear a,#plotMonth a,#plotWeek a,#plotDay a,#plotHour a").removeClass("mouse");
	$("#plotDay").addClass("mouse");
	startDate = getYMDTimeOther(-6);
	endDate = getYMDTimeOther(1);
	granularity = "day";
	var gateVal = $("#serchTreeVal").val();
	gate = gateVal;
	asyncGetFlowData(container,options);
}

function onclikcThirtydays(container,options){
	container = $(container).attr("id");
	$("#plotWeek,#plotDay").css("display","block");
	$("#plotYear,#plotMonth,#plotHour").css("display","none");
	$("#plotYear,#plotMonth,#plotWeek,#plotDay,#plotHour,#plotYear a,#plotMonth a,#plotWeek a,#plotDay a,#plotHour a").removeClass("mouse");
	$("#plotDay").addClass("mouse");
	startDate = getYMDTimeOther(-29);
	endDate = getYMDTimeOther(1);
	granularity = "day";
	var gateVal = $("#serchTreeVal").val();
	gate = gateVal;
	asyncGetFlowData(container,options);
}

function onclickPlotYear(){
	$("#plotYear,#plotMonth,#plotWeek,#plotDay,#plotHour,#plotYear a,#plotMonth a,#plotWeek a,#plotDay a,#plotHour a").removeClass("mouse");
	$(this).parent().addClass("mouse");
	granularity = "year";
	asyncGetFlowData();
}

function onclickPlotMonth(){
	$("#plotYear,#plotMonth,#plotWeek,#plotDay,#plotHour,#plotYear a,#plotMonth a,#plotWeek a,#plotDay a,#plotHour a").removeClass("mouse");
	$(this).parent().addClass("mouse");
	granularity = "month";
	asyncGetFlowData();
}

function onclickPlotWeek(){
	$("#plotYear,#plotMonth,#plotWeek,#plotDay,#plotHour,#plotYear a,#plotMonth a,#plotWeek a,#plotDay a,#plotHour a").removeClass("mouse");
	$(this).parent().addClass("mouse");
	granularity = "week";
	asyncGetFlowData();
}

function onclickPlotDay(){
	$("#plotYear,#plotMonth,#plotWeek,#plotDay,#plotHour,#plotYear a,#plotMonth a,#plotWeek a,#plotDay a,#plotHour a").removeClass("mouse");
	$(this).parent().addClass("mouse");
	granularity = "day";
	asyncGetFlowData();
}

function onclickPlotHour(){
	granularity = "hour";
	asyncGetFlowData();
}

function onclickQueryBtn(startTime,endTime){
	var startDt = stringToTime(startTime);
	var endDt = stringToTime(endTime);
	if((startDt.getTime()-endDt.getTime())>0){
		showInfoMsg("开始时间不能大于等于结束时间！");
	}else{
		startDate = startTime;
		endDate = endTime;
		var days = dateDiff(endDate,startDate);
		if(days<2){
			$("#plotWeek,#plotDay,#plotMonth,#plotYear").css("display","none");
			$("#plotHour").css("display","block");
			$("#plotHour").addClass("mouse");
			granularity = "hour"
		}
		if(days>1 && days<6){
			$("#plotWeek,#plotMonth,#plotYear").css("display","none");
			$("#plotDay,#plotHour").css("display","block");
			$("#plotDay").addClass("mouse");
			granularity = "day"
		}
		if(days>5 && days<31){
			$("#plotHour,#plotMonth,#plotYear").css("display","none");
			$("#plotDay,#plotWeek").css("display","block");
			$("#plotDay").addClass("mouse");
			granularity = "day"
		}
		if(days>30 && days<365){
			$("#plotHour,#plotDay,#plotWeek").css("display","none");
			$("#plotMonth,#plotYear").css("display","block");
			$("#plotMonth").addClass("mouse");
			granularity = "month"
		}
		if(days>365){
			$("#plotHour,#plotDay,#plotWeek,#plotMonth").css("display","none");
			$("#plotYear").css("display","block");
			$("#plotYear").addClass("mouse");
			granularity = "year"
		}
		var gateVal = $("#serchTreeVal").val();
		gate = gateVal;
		asyncGetFlowData();
	}
}

function pmTimeSelect(container,options){
	var timePreContent = "<div id='plotHeader' style='position: absolute;z-index:1;right:55px;top:25px;width:100px;'>" +
		"<div id='plotYear' style='display: none;' class='defaultColor'><a href='#' onclick='onclickPlotYear();'>年</a></div>" + 
		"<div id='plotMonth' style='display: none;' class='defaultColor'><a href='#' onclick='onclickPlotMonth();'>月</a></div>" +
		"<div id='plotWeek' style='display: none;' class='defaultColor'><a href='#' onclick='onclickPlotWeek();'>周</a></div>" + 
		"<div id='plotDay' style='display:none;' class='defaultColor'><a href='#' onclick='onclickPlotDay();'>天</a></div>" +
		"<div id='plotHour' style='display: block;' class='defaultColor mouse'><a href=''#'>时</a></div>" +
		"</div>";
	var timeAfterContent = "<div class='row'>" +
		"<div class='col-sm-6 col-sm-offset-4 col-md-6 col-md-offset-4' style='margin-top: 25px;'>" +
		"<div class='day'><button id='today' class='btn_all btn_basic' onclick='onclickTody(" + container + "," + JSON.stringify(options) + ");'>今天</button></div>" +
		"<div class='day'><button id='yesterday' class='btn_all btn_basic' onclick='onclickYesterday(" + container + "," + JSON.stringify(options) + ");'>昨天</button></div>" +
		"<div class='day'><button id='sevendays' class='btn_all btn_basic btn_width' onclick='onclickSevendays(" + container + "," + JSON.stringify(options) + ");'>最近7日</button></div>" +
		"<div class='day'><button id='thirtydays' class='btn_all btn_basic btn_width' coclick='onclikcThirtydays(" + container + "," + JSON.stringify(options) + ");'>最近30日</button></div>" +
		"</div>" +
		"</div>";
	$("#"+container).prepend(timePreContent);
	$("#"+container).append(timeAfterContent);
}
*/
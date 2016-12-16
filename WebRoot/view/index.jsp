<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="renderer" content="webkit">
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<meta name="keywords"
			content="G+， java， web框架， hplus风格">
		<meta name="description"
			content="G+是一个完整的web开发框架（java语言），前端风格参考hplus，框架使用Ajax搭建，后台使用spring+hibernate框架，功能封装了RBAC/日志/系统信息/用户资料等常用功能">

		<title>G+ web开发框架</title>
		<!--[if lt IE 9]>
	    <meta http-equiv="refresh" content="0;ie.html"/>
	    <![endif]-->
		
		<link rel="shortcut icon"
			href="../assets/img/oem/${oem.vendor.imgPath}/favicon.ico"
			type="image/vnd.microsoft.icon">
			<link rel="icon"
				href="../assets/img/oem/${oem.vendor.imgPath}/favicon.ico"
				type="image/vnd.microsoft.icon">
	</head>

	<body class="fixed-sidebar full-height-layout gray-bg"
		style="overflow: hidden">
		<div id="wrapper">

			<!--左侧导航开始-->
			<nav class="navbar-default navbar-static-side" role="navigation">
			<!-- for click -->
			<div class="nav-close">
				<i class="fa fa-times-circle"></i>
			</div>
			<!-- 菜单DIV -->
			<div class="sidebar-collapse">

				<ul class="nav" id="side-menu">
					<!-- 菜单项 ：头 -->
					<li class="nav-header">
						<!-- 默认 -->
						<div class="dropdown profile-element">
							
							<!--  
							<span><img alt="image" class="img-circle"
									src="../html/img/profile_small.jpg" /> </span>
							-->
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<span class="clear"> 
									<h1 class="block m-t-xs"> <strong class="font-bold">Gweb+ <b class="caret"> </b></strong>  </h1>
								</span> 
							 </a>
							<ul class="dropdown-menu animated fadeInRight m-t-xs">
								<li>
									<a class="J_menuItem" href="#" rel="sysPerson/upLoadUrl.do">修改头像</a>
								</li>
								<li>
									<a class="J_menuItem" href="#" rel="sysUser/info.do">个人资料</a>
								</li>
								<li>
									<a class="J_menuItem" href="#" rel="sysUser/contact.do">联系我们</a>
								</li>
								<!-- 
								<li>
									<a class="J_menuItem" href="#" rel="html/mailbox.html">信箱</a>
								</li>
								 -->
								<li class="divider"></li>
								<li>
									<a href="#" class="loginOut">安全退出</a>
								</li>
							</ul>
						</div>
						<!-- 缩略 -->
						<div class="logo-element">
							G+
						</div>
					</li>

					<!-- 菜单项：项 -->
					<%--<li>
						<a href="#"rel="" pos="主页"> <i class="fa fa-home"></i> <span
							class="nav-label">主页</span> <span class="fa arrow"></span> </a>
						<ul class="nav nav-second-level">
							<li>
								<a class="J_menuItem" href="#" rel="html/index_v1.html">主页示例一</a>
							</li>
							<li>
								<a class="J_menuItem" href="#" rel="html/index_v2.html">主页示例二</a>
							</li>
							<li>
								<a class="J_menuItem" href="#" rel="html/index_v3.html">主页示例三</a>
							</li>
							<li>
								<a class="J_menuItem" href="#" rel="html/index_v4.html">主页示例四</a>
							</li>
							<li>
								<a  href="#" rel="../html/index_v5.html">主页示例五</a>
							</li>
						</ul>

					</li>
					<li>
						<a class="J_menuItem" href="#" rel="html/index_v1.html"><i
							class="fa fa-home fa-lg"></i> <span class="nav-label">首页</span> </a>
					</li>
					--%>

				</ul>
			</div>

			</nav>
			<!--左侧导航结束-->

			<!--右侧部分开始-->
			<div id="page-wrapper" class="gray-bg dashbard-1">
				<!-- 搜索 -->
				<div class="row border-bottom">
					<nav class="navbar navbar-static-top" role="navigation"
						style="margin-bottom: 0">
					<div class="navbar-header">
						<a class="navbar-minimalize minimalize-styl-2 btn btn-primary "
							href="#"><i class="fa fa-bars"></i> </a>

						<form role="search" class="navbar-form-custom" method="post"
							action="../html/search_results.html">
							<div class="form-group">
								<input type="text" placeholder="请输入您需要查找的内容 …"
									class="form-control" name="top-search" id="top-search">
							</div>
						</form>
					</div>
					<ul class="nav navbar-top-links navbar-right">
						<li class="dropdown" id="emailMsg">
						</li>
						<li class="dropdown" id="sysMsg">
						</li>
						<li class="hidden-xs">
		            		<a href="#" id="editPass"><i class="fa fa-unlock-alt"></i>&nbsp;修改密码</a>
		            	</li>
		            	
						<li class="hidden-xs">
		            		<a href="#" id="sysHelp"><i class="fa fa-download"></i>&nbsp;系统帮助</a>
		            	</li>
		            	
		         
						<li class="dropdown hidden-xs">
							<a class="right-sidebar-toggle" aria-expanded="false"> <i
								class="fa fa-tasks"></i> 主题 </a>
						</li>
					</ul>
					</nav>
				</div>
				<!-- 历史记录 -->
				<div class="row content-tabs">
					<button class="roll-nav roll-left J_tabLeft">
						<i class="fa fa-backward"></i>
					</button>
					<nav class="page-tabs J_menuTabs">
					<div class="page-tabs-content">
						<a href="#" rel="../html/index_v1.html" class="J_menuTab active" id="tab0"><i
							class="fa fa-home fa-fx"></i>&nbsp;首页</a>
					</div>
					</nav>
					<button class="roll-nav roll-right J_tabRight">
						<i class="fa fa-forward"></i>
					</button>
					<div class="btn-group roll-nav roll-right">
						<button class="dropdown J_tabClose" data-toggle="dropdown">
							关闭操作
							<span class="caret"></span>

						</button>
						<ul role="menu" class="dropdown-menu dropdown-menu-right">
							<li class="J_tabShowActive">
								<a>定位当前选项卡</a>
							</li>
							<li class="divider"></li>
							<li class="J_tabCloseAll">
								<a>关闭全部选项卡</a>
							</li>
							<li class="J_tabCloseOther">
								<a>关闭其他选项卡</a>
							</li>
						</ul>
					</div>
					<a  href="#" class="loginOut roll-nav roll-right J_tabExit"><i
						class="fa fa-sign-out"></i> 退出</a>
				</div>
				
				
				<!-- 内容 ，iframe -->
				<div class="row wrapper wrapper-content" id="content-main" style="animation-fill-mode:none;">
					<iframe class="J_iframe" name="iframe0" width="100%" height="100%"
						src="../html/index_v1.html" frameborder="0"
						data-id="index_v1.html" seamless></iframe>
				</div>
				
				
				
				
				<!-- foorter -->
				<div class="footer">
					<div class="pull-left"><span><small>版本号：</small>V2.0.20161031</span>
	                </div>
					<div class="pull-right">
						&copy; 2014-2016 &
						<a href="https://github.com/assertmyself" target="_blank">  Gweb+</a>
					</div>
				</div>
			</div>

			<!--右侧部分结束-->
			<!--边栏开始，主题框-->
			<div id="right-sidebar">
				<div class="sidebar-container">
					<ul class="nav nav-tabs navs-3">
						<li class="active">
							<a data-toggle="tab" href="#tab-1"> <i class="fa fa-gear"></i>
								主题 </a>
						</li>
						<li class="">
							<a data-toggle="tab" href="#tab-2"> 通知 </a>
						</li>
						<li>
							<a data-toggle="tab" href="#tab-3"> 项目进度 </a>
						</li>
					</ul>
					<!-- tab 页内容 -->
					<div class="tab-content">
						<div id="tab-1" class="tab-pane active">
							<div class="sidebar-title">
								<h3>
									<i class="fa fa-comments-o"></i> 主题设置
								</h3>
								<small><i class="fa fa-tim"></i>
									你可以从这里选择和预览主题的布局和样式，这些设置会被保存在本地，下次打开的时候会直接应用这些设置。</small>
							</div>
							<div class="skin-setttings">
								<div class="title">
									主题设置
								</div>
								<div class="setings-item">
									<span>收起左侧菜单</span>

									<div class="switch">
										<div class="onoffswitch">
											<input type="checkbox" name="collapsemenu"
												class="onoffswitch-checkbox" id="collapsemenu">
											<label class="onoffswitch-label" for="collapsemenu">
												<span class="onoffswitch-inner"></span>
												<span class="onoffswitch-switch"></span>
											</label>
										</div>
									</div>
								</div>
								<div class="setings-item">
									<span>固定顶部</span>

									<div class="switch">
										<div class="onoffswitch">
											<input type="checkbox" name="fixednavbar"
												class="onoffswitch-checkbox" id="fixednavbar">
											<label class="onoffswitch-label" for="fixednavbar">
												<span class="onoffswitch-inner"></span>
												<span class="onoffswitch-switch"></span>
											</label>
										</div>
									</div>
								</div>
								<div class="setings-item">
									<span> 固定宽度 </span>

									<div class="switch">
										<div class="onoffswitch">
											<input type="checkbox" name="boxedlayout"
												class="onoffswitch-checkbox" id="boxedlayout">
											<label class="onoffswitch-label" for="boxedlayout">
												<span class="onoffswitch-inner"></span>
												<span class="onoffswitch-switch"></span>
											</label>
										</div>
									</div>
								</div>		
								<div class="setings-item">
									<span> 显示例子 </span>
									<div class="switch">
										<div class="onoffswitch">
											<input type="checkbox" name="demolayout"
												class="onoffswitch-checkbox" id="demolayout">
											<label class="onoffswitch-label" for="demolayout">
												<span class="onoffswitch-inner"></span>
												<span class="onoffswitch-switch"></span>
											</label>
										</div>
									</div>
								</div>
								<div class="title">
									皮肤选择
								</div>
								<div class="setings-item default-skin nb">
									<span class="skin-name "> <a href="#" class="s-skin-0">
											默认皮肤 </a> </span>
								</div>
								<div class="setings-item blue-skin nb">
									<span class="skin-name "> <a href="#" class="s-skin-1">
											蓝色主题 </a> </span>
								</div>
								<div class="setings-item yellow-skin nb">
									<span class="skin-name "> <a href="#" class="s-skin-3">
											黄色/紫色主题 </a> </span>
								</div>
							</div>
						</div>
						<div id="tab-2" class="tab-pane">

							<div class="sidebar-title">
								<h3>
									<i class="fa fa-comments-o"></i> 最新通知
								</h3>
								<small><i class="fa fa-tim"></i> 您当前有3条未读信息</small>
							</div>

							<div>

								<div class="sidebar-message">
									<a href="#">
										<div class="pull-left text-center">
											<img alt="image" class="img-circle message-avatar"
												src="../html/img/a1.jpg">

											<div class="m-t-xs">
												<i class="fa fa-star text-warning"></i>
												<i class="fa fa-star text-warning"></i>
											</div>
										</div>
										<div class="media-body">
											香港男子背22把气动力枪入境被查 称帮人带货。
											<br>
											<small class="text-muted">今天 4:21</small>
										</div> </a>
								</div>
								<div class="sidebar-message">
									<a href="#">
										<div class="pull-left text-center">
											<img alt="image" class="img-circle message-avatar"
												src="../html/img/a2.jpg">
										</div>
										<div class="media-body">
											代购空气叫价1.2元一口 "进口空气"最贵699元
											<br>
											<small class="text-muted">昨天 2:45</small>
										</div> </a>
								</div>
								<div class="sidebar-message">
									<a href="#">
										<div class="pull-left text-center">
											<img alt="image" class="img-circle message-avatar"
												src="../html/img/a3.jpg">

											<div class="m-t-xs">
												<i class="fa fa-star text-warning"></i>
												<i class="fa fa-star text-warning"></i>
												<i class="fa fa-star text-warning"></i>
											</div>
										</div>
										<div class="media-body">
											注册容易，注销难？
											<br>
											<small class="text-muted">昨天 1:10</small>
										</div> </a>
								</div>
								<div class="sidebar-message">
									<a href="#">
										<div class="pull-left text-center">
											<img alt="image" class="img-circle message-avatar"
												src="../html/img/a4.jpg">
										</div>

										<div class="media-body">
											16家上市银行借记卡能异地注销 但有这些限制条件
											<br>
											<small class="text-muted">昨天 8:37</small>
										</div> </a>
								</div>
								
							</div>

						</div>
						<div id="tab-3" class="tab-pane">

							<div class="sidebar-title">
								<h3>
									<i class="fa fa-cube"></i> 测试项目
								</h3>
								<small><i class="fa fa-tim"></i> 您当前有14个任务，10个已完成</small>
							</div>

							<ul class="sidebar-list">
								<li>
									<a href="#">
										<div class="small pull-right m-t-xs">
											9小时以后
										</div>
										<h4>
											需求调研
										</h4> 市场调研，需求收集，项目立项；

										<div class="small">
											已完成： 22%
										</div>
										<div class="progress progress-mini">
											<div style="width: 22%;"
												class="progress-bar progress-bar-warning"></div>
										</div>
										<div class="small text-muted m-t-xs">
											项目截止： 4:00 - 2016.10.01
										</div> </a>
								</li>
								<li>
									<a href="#">
										<div class="small pull-right m-t-xs">
											5小时以后
										</div>
										<h4>
											需求分析
										</h4> 可行性报告研究报,需求分解，任务分配，开发计划，编写的目的在于更好的控制软件开发的时间，掌控风险

										<div class="small">
											已完成： 48%
										</div>
										<div class="progress progress-mini">
											<div style="width: 48%;" class="progress-bar"></div>
										</div> </a>
								</li>
								<li>
									<a href="#">
										<div class="small pull-right m-t-xs">
											1小时以后
										</div>
										<h4>
											设计阶段
										</h4> 总体设计、概要设计

										<div class="small">
											已完成： 100%
										</div>
										<div class="progress progress-mini">
											<div style="width: 100%;"
												class="progress-bar progress-bar-info"></div>
										</div> </a>
								</li>
								<li>
									<a href="#"> <span class="label label-primary pull-right">NEW</span>
										<h4>
											开发阶段
										</h4> 
										项目进度报告(Project Progress Report)
										<div class="small">
											已完成： 22%
										</div>
										<div class="small text-muted m-t-xs">
											项目截止： 4:00 - 2017.1.01
										</div> </a>
								</li>

							</ul>

						</div>
					</div>

				</div>
			</div>
			<!--右侧边栏结束-->
			
			<!--mini聊天窗口开始-->
			<div class="small-chat-box fadeInRight animated">

				<div class="heading" draggable="true">
					<small class="chat-date pull-right"> 2015.9.1 </small> 
					聊天中
				</div>

				<div class="content">

					<div class="left">
						<div class="author-name">
							Robot
							<small class="chat-date"> 10:02 </small>
						</div>
						<div class="chat-message active">
							你好
						</div>

					</div>
					<div class="right">
						<div class="author-name">
							游客
							<small class="chat-date"> 11:24 </small>
						</div>
						<div class="chat-message">
							你好，请问G+有帮助文档吗？
						</div>
					</div>
					<div class="left">
						<div class="author-name">
							Robot
							<small class="chat-date"> 08:45 </small>
						</div>
						<div class="chat-message active">
							有，页面直接下载，源码包括HelloWorld级别的例子，建议直接运行
						</div>
					</div>
					<div class="right">
						<div class="author-name">
							游客
							<small class="chat-date"> 11:24 </small>
						</div>
						<div class="chat-message">
							那除了帮助文档还提供什么样的服务？
						</div>
					</div>
					<div class="left">
						<div class="author-name">
							Robot
							<small class="chat-date"> 08:45 </small>
						</div>
						<div class="chat-message active">
							1.所有源码；
							<br>
							2.说明文档；
							<br>
							3.开发向导；
							<br>
							4.前端hplus风格参考；
							<br>
							5.例子；
							<br>
							6.仅供学习；
							<br>
							……
							<br>
						</div>
					</div>


				</div>
				<div class="form-chat">
					<div class="input-group input-group-sm">
						<input type="text" class="form-control">
						<span class="input-group-btn">
							<button class="btn btn-primary" type="button">
								发送
							</button> </span>
					</div>
				</div>

			</div>
			<!-- 隐藏状态 -->
			<div id="small-chat">
				<span class="badge badge-warning pull-right">5</span>
				<a class="open-small-chat"> <i class="fa fa-comments"></i> </a>
			</div>
		</div>


		<!--  
<div class="main-container">
    <div class="sidebar" id="sidebar">
        <div id="front" class="sidebar-collapse" >
		    <i class="icon-double-angle-left"></i>
	    </div>   
        <div id="leftMenu">

        </div>
        <div id="back" class="sidebar-collapse" >
		    <i class="icon-double-angle-left"></i>
	    </div>   
   </div>
    <div class="main-content" id="main-content">
        <div class="breadcrumbs" id="breadcrumbs">
            <ul class="breadcrumb">
           	<li>
				<a href="#"><i class="glyphicon glyphicon-home"></i>&nbsp;Home</a>
			</li>
         	</ul>    
        </div>     
        <div id="mainContent" class="mainContent">

        </div>
		<div class="modal fade" id="processWindowId" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	        <div class="process-dialog">
	    		<div class="process-content">
	     			<img  src="${ctx}/assets/img/loading.gif"/><span>正在执行，请稍候······</span>
	      		</div>
	        </div>
   		</div>
    </div>
</div>
-->
<script type="text/javascript">
    jQuery.ajaxSetup({cache: false});//ajax不缓存
	
    //        用户菜单
    var _menus = {basic:${xmlJsonString}};
	
    //初始化服务器时间
    var init_dateTime = "${init_dateTime}";

	var exist_num=0;
	
	function initDemoSwitch() {
		if($("#demolayout").click(function(){
			var demoDom = $("span:contains('例子').nav-label").parent().parent();
			if($("#demolayout").is(":checked")){
				localStorageSupport && localStorage.setItem("demolayout", "on");
				demoDom.show();
			}else{
				localStorageSupport && localStorage.setItem("demolayout", "off");
				demoDom.hide();
				demoDom.collapse('hide');
				if(demoDom.hasClass("active")){
					demoDom.children(".collapse").collapse('hide');
					demoDom.removeClass();
				}
				//demoDom.trigger("click");
			}
	    }), localStorageSupport){
	        var d = localStorage.getItem("demolayout");
	        "on" == d && $("#demolayout").prop("checked", "checked");
		}
	    if (localStorageSupport) {
	    	var d = localStorage.getItem("demolayout");
	        if(d == "off"){
				$("span:contains('例子').nav-label").parent().parent().hide();
	        }else if(d == "on"){
	        	$("span:contains('例子').nav-label").parent().parent().show();
	        }else{
	        	$("span:contains('例子').nav-label").parent().parent().show();
	        	localStorageSupport && localStorage.setItem("demolayout", "on");
	        }
	    }else{
	    	$("span:contains('例子').nav-label").parent().parent().hide();
	    }
	}

    $(function () {
    	
		//查询组件enter事件监听
       	$(document).on("keypress",".title_Search input", function(event){ 
       	    queryEnterPress(event);
       	    event.stopPropagation(); 
       	});
       	
      	//Form组件 enter事件监听
       	$(document).on("keypress",".modal form input:not(#treeSearchContent)", function(event){ 
       	    formEnterPress(event);
       	    event.stopPropagation(); 
       	});
       	
    	//加载菜单
        $("#side-menu").append(initLeftMenu());	
        
        //判断demosPage.js是否存在，加载例子页面
        if(typeof initDemoNav !="undefined"){		
        	initDemoNav();
        }
        initDemoSwitch();
        
        $('#side-menu li a').click(
                function(){
                    var url = $(this).attr('rel');
        	    	var pos = $(this).html();
                    var jsevent = $(this).attr('jsevent');
                    if(url!=""&&url!='undefined'&&url!='#'&&url!=undefined){
                        if (url.indexOf("http://") == -1) {
                            url ="../" + url;
                        }
                        loadMainPage(url,pos,jsevent);
                    }
                }
        )

        
        $('#sysHelp').click(function () {
            downAjaxFile("${ctx}/sysInfo/getHelpChm.do","${ctx}");
        });

        $('#editPass').click(function () {
            openWindow('修改密码', "${ctx}/sysPassword/changePass.do");
        });

        $('.loginOut').click(function(){
            showConfigMsg('您确定要退出系统吗?',function(r){
               if(r){
                   location.href = '${ctx}/j_spring_security_logout';
               }
            });
        });

        //加载邮件数据
		loadAjaxData("emailMsg","${ctx}/sysMessage/getLastMsg.do?type=3");
		loadAjaxData("sysMsg","${ctx}/sysMessage/getCountView.do");
        
    });
    //给所有的 带有 loadMainpage 的标签添加点击事件
	$("body").on("click",".loadMainpage",function(){
		var url=$(this).attr("rel");
		loadMainPage("../"+url);
		$(this).tabInit($(this));
	});

   

</script>
<!--这些js需要延迟加载，否则，动态添加的菜单将无效-->
<script src="${ctx}/html/js/hplus.js"></script><!--左侧菜单 样式 -->
	</body>
</html>
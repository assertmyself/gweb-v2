function initDemoNav(){
	var target = $("span:contains('例子')").parent().next("ul");
	var demoNode =
		'<li class="demo-nav">'+
		'	<a href="#" > <i class="fa fa-home"></i> <span'+
		'		class="">主页</span> <span class="fa arrow"></span> </a>'+
		'	<ul class="nav nav-third-level">'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/index_v1.html" data-index="0">主页示例一</a>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/index_v2.html">主页示例二</a>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/index_v3.html">主页示例三</a>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/index_v4.html">主页示例四</a>'+
		'		</li>'+
		'		<li>'+
		'			<a href="#" rel="html/index_v5.html" target="_blank">主页示例五</a>'+
		'		</li>'+
		'	</ul>'+
		''+
		'</li>'+
		'<li class="demo-nav">'+
		'	<a class="J_menuItem" href="#" rel="html/layouts.html"><i'+
		'		class="fa fa-columns"></i> <span class="">布局</span>'+
		'	</a>'+
		'</li>'+
		'<li class="demo-nav">'+
		'	<a href="#" > <i class="fa fa fa-bar-chart-o"></i> <span'+
		'		class="">统计图表</span> <span class="fa arrow"></span> </a>'+
		'	<ul class="nav nav-third-level">'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/graph_echarts.html">百度ECharts</a>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/graph_flot.html">Flot</a>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/graph_morris.html">Morris.js</a>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/graph_rickshaw.html">Rickshaw</a>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/graph_peity.html">Peity</a>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/graph_sparkline.html">Sparkline</a>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/graph_metrics.html">图表组合</a>'+
		'		</li>'+
		'	</ul>'+
		'</li>'+
		''+
		'<li class="demo-nav">'+
		'	<a href="#" rel="html/mailbox.html"><i class="fa fa-envelope"></i> <span'+
		'		class="">信箱 </span><span'+
		'		class="label label-warning pull-right">16</span>'+
		'	</a>'+
		'	<ul class="nav nav-third-level">'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/mailbox.html">收件箱</a>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/mail_detail.html">查看邮件</a>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/mail_compose.html">写信</a>'+
		'		</li>'+
		'	</ul>'+
		'</li>'+
		'<li class="demo-nav">'+
		'	<a href="#" ><i class="fa fa-edit"></i> <span class="">表单</span><span'+
		'		class="fa arrow"></span>'+
		'	</a>'+
		'	<ul class="nav nav-third-level">'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/form_basic.html">基本表单</a>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/form_validate.html">表单验证</a>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/form_advanced.html">高级插件</a>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/form_wizard.html">表单向导</a>'+
		'		</li>'+
		'		<li>'+
		'			<a href="#" >文件上传 <span class="fa arrow"></span>'+
		'			</a>'+
		'			<ul class="nav nav-fourth-level">'+
		'				<li>'+
		'					<a class="J_menuItem" href="#" rel="html/form_webuploader.html">百度WebUploader</a>'+
		'				</li>'+
		'				<li>'+
		'					<a class="J_menuItem" href="#" rel="html/form_file_upload.html">DropzoneJS</a>'+
		'				</li>'+
		'				<li>'+
		'					<a class="J_menuItem" href="#" rel="html/form_avatar.html">头像裁剪上传</a>'+
		'				</li>'+
		'			</ul>'+
		'		</li>'+
		'		<li>'+
		'			<a href="#" >编辑器 <span class="fa arrow"></span>'+
		'			</a>'+
		'			<ul class="nav nav-fourth-level">'+
		'				<li>'+
		'					<a class="J_menuItem" href="#" rel="html/form_editors.html">富文本编辑器</a>'+
		'				</li>'+
		'				<li>'+
		'					<a class="J_menuItem" href="#" rel="html/form_simditor.html">simditor</a>'+
		'				</li>'+
		'				<li>'+
		'					<a class="J_menuItem" href="#" rel="html/form_markdown.html">MarkDown编辑器</a>'+
		'				</li>'+
		'				<li>'+
		'					<a class="J_menuItem" href="#" rel="html/code_editor.html">代码编辑器</a>'+
		'				</li>'+
		'			</ul>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/suggest.html">搜索自动补全</a>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/layerdate.html">日期选择器layerDate</a>'+
		'		</li>'+
		'	</ul>'+
		'</li>'+
		'<li class="demo-nav">'+
		'	<a href="#" ><i class="fa fa-desktop"></i> <span'+
		'		class="">页面</span><span class="fa arrow"></span>'+
		'	</a>'+
		'	<ul class="nav nav-third-level">'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/contacts.html">联系人</a>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/profile.html">个人资料</a>'+
		'		</li>'+
		'		<li>'+
		'			<a href="#" >项目管理 <span class="fa arrow"></span>'+
		'			</a>'+
		'			<ul class="nav nav-fourth-level">'+
		'				<li>'+
		'					<a class="J_menuItem" href="#" rel="html/projects.html">项目</a>'+
		'				</li>'+
		'				<li>'+
		'					<a class="J_menuItem" href="#" rel="html/project_detail.html">项目详情</a>'+
		'				</li>'+
		'			</ul>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/teams_board.html">团队管理</a>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/social_feed.html">信息流</a>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/clients.html">客户管理</a>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/file_manager.html">文件管理器</a>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/calendar.html">日历</a>'+
		'		</li>'+
		'		<li>'+
		'			<a href="#" >博客 <span class="fa arrow"></span>'+
		'			</a>'+
		'			<ul class="nav nav-fourth-level">'+
		'				<li>'+
		'					<a class="J_menuItem" href="#" rel="html/blog.html">文章列表</a>'+
		'				</li>'+
		'				<li>'+
		'					<a class="J_menuItem" href="#" rel="html/article.html">文章详情</a>'+
		'				</li>'+
		'			</ul>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/faq.html">FAQ</a>'+
		'		</li>'+
		'		<li>'+
		'			<a href="#" >时间轴 <span class="fa arrow"></span>'+
		'			</a>'+
		'			<ul class="nav nav-fourth-level">'+
		'				<li>'+
		'					<a class="J_menuItem" href="#" rel="html/timeline.html">时间轴</a>'+
		'				</li>'+
		'				<li>'+
		'					<a class="J_menuItem" href="#" rel="html/timeline_v2.html">时间轴v2</a>'+
		'				</li>'+
		'			</ul>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/pin_board.html">标签墙</a>'+
		'		</li>'+
		'		<li>'+
		'			<a href="#" >单据 <span class="fa arrow"></span>'+
		'			</a>'+
		'			<ul class="nav nav-fourth-level">'+
		'				<li>'+
		'					<a class="J_menuItem" href="#" rel="html/invoice.html">单据</a>'+
		'				</li>'+
		'				<li>'+
		'					<a class="J_menuItem" href="#" rel="html/invoice_print.html">单据打印</a>'+
		'				</li>'+
		'			</ul>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/search_results.html">搜索结果</a>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/forum_main.html">论坛</a>'+
		'		</li>'+
		'		<li>'+
		'			<a href="#" >即时通讯 <span class="fa arrow"></span>'+
		'			</a>'+
		'			<ul class="nav nav-fourth-level">'+
		'				<li>'+
		'					<a class="J_menuItem" href="#" rel="html/chat_view.html">聊天窗口</a>'+
		'				</li>'+
		'				<li>'+
		'					<a class="J_menuItem" href="#" rel="html/webim.html">layIM</a>'+
		'				</li>'+
		'			</ul>'+
		'		</li>'+
		'		<li>'+
		'			<a href="#" >登录注册相关 <span class="fa arrow"></span>'+
		'			</a>'+
		'			<ul class="nav nav-fourth-level">'+
		'				<li>'+
		'					<a href="#" rel="html/login.html" target="_blank">登录页面</a>'+
		'				</li>'+
		'				<li>'+
		'					<a href="#" rel="html/login_v2.html" target="_blank">登录页面v2</a>'+
		'				</li>'+
		'				<li>'+
		'					<a href="#" rel="html/register.html" target="_blank">注册页面</a>'+
		'				</li>'+
		'				<li>'+
		'					<a href="#" rel="html/lockscreen.html" target="_blank">登录超时</a>'+
		'				</li>'+
		'			</ul>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/404.html">404页面</a>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/500.html">500页面</a>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/empty_page.html">空白页</a>'+
		'		</li>'+
		'	</ul>'+
		'</li>'+
		'<li class="demo-nav">'+
		'	<a href="#" ><i class="fa fa-flask"></i> <span'+
		'		class="">UI元素</span><span class="fa arrow"></span>'+
		'	</a>'+
		'	<ul class="nav nav-third-level">'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/typography.html">排版</a>'+
		'		</li>'+
		'		<li>'+
		'			<a href="#" >字体图标 <span class="fa arrow"></span>'+
		'			</a>'+
		'			<ul class="nav nav-fourth-level">'+
		'				<li>'+
		'					<a class="J_menuItem" href="#" rel="html/fontawesome.html">Font Awesome</a>'+
		'				</li>'+
		'				<li>'+
		'					<a class="J_menuItem" href="#" rel="html/glyphicons.html">Glyphicon</a>'+
		'				</li>'+
		'				<li>'+
		'					<a class="J_menuItem" href="#" rel="html/iconfont.html">阿里巴巴矢量图标库</a>'+
		'				</li>'+
		'			</ul>'+
		'		</li>'+
		'		<li>'+
		'			<a href="#" >拖动排序 <span class="fa arrow"></span>'+
		'			</a>'+
		'			<ul class="nav nav-fourth-level">'+
		'				<li>'+
		'					<a class="J_menuItem" href="#" rel="html/draggable_panels.html">拖动面板</a>'+
		'				</li>'+
		'				<li>'+
		'					<a class="J_menuItem" href="#" rel="html/agile_board.html">任务清单</a>'+
		'				</li>'+
		'			</ul>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/buttons.html">按钮</a>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/tabs_panels.html">选项卡 &amp; 面板</a>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/notifications.html">通知 &amp; 提示</a>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/badges_labels.html">徽章，标签，进度条</a>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/grid_options.html">栅格</a>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/plyr.html">视频、音频</a>'+
		'		</li>'+
		'		<li>'+
		'			<a href="#" >弹框插件 <span class="fa arrow"></span>'+
		'			</a>'+
		'			<ul class="nav nav-fourth-level">'+
		'				<li>'+
		'					<a class="J_menuItem" href="#" rel="html/layer.html">Web弹层组件layer</a>'+
		'				</li>'+
		'				<li>'+
		'					<a class="J_menuItem" href="#" rel="html/modal_window.html">模态窗口</a>'+
		'				</li>'+
		'				<li>'+
		'					<a class="J_menuItem" href="#" rel="html/sweetalert.html">SweetAlert</a>'+
		'				</li>'+
		'			</ul>'+
		'		</li>'+
		'		<li>'+
		'			<a href="#" >树形视图 <span class="fa arrow"></span>'+
		'			</a>'+
		'			<ul class="nav nav-fourth-level">'+
		'				<li>'+
		'					<a class="J_menuItem" href="#" rel="html/jstree.html">jsTree</a>'+
		'				</li>'+
		'				<li>'+
		'					<a class="J_menuItem" href="#" rel="html/tree_view.html">Bootstrap Tree'+
		'						View</a>'+
		'				</li>'+
		'				<li>'+
		'					<a class="J_menuItem" href="#" rel="html/nestable_list.html">nestable</a>'+
		'				</li>'+
		'			</ul>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/toastr_notifications.html">Toastr通知</a>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/diff.html">文本对比</a>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/spinners.html">加载动画</a>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/widgets.html">小部件</a>'+
		'		</li>'+
		'	</ul>'+
		'</li>'+
		'<li class="demo-nav">'+
		'	<a href="#" ><i class="fa fa-table"></i> <span'+
		'		class="">表格</span><span class="fa arrow"></span>'+
		'	</a>'+
		'	<ul class="nav nav-third-level">'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/table_basic.html">基本表格</a>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/table_data_tables.html">DataTables</a>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/table_jqgrid.html">jqGrid</a>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/table_foo_table.html">Foo Tables</a>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/table_bootstrap.html">Bootstrap'+
		'				Table <span class="label label-danger pull-right">推荐</span>'+
		'			</a>'+
		'		</li>'+
		'	</ul>'+
		'</li>'+
		'<li class="demo-nav">'+
		'	<a href="#" ><i class="fa fa-picture-o"></i> <span'+
		'		class="">相册</span><span class="fa arrow"></span>'+
		'	</a>'+
		'	<ul class="nav nav-third-level">'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/basic_gallery.html">基本图库</a>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/carousel.html">图片切换</a>'+
		'		</li>'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/blueimp.html">Blueimp相册</a>'+
		'		</li>'+
		'	</ul>'+
		'</li>'+
		'<li class="demo-nav">'+
		'	<a class="J_menuItem" href="#" rel="html/css_animation.html"><i'+
		'		class="fa fa-magic"></i> <span class="">CSS动画</span>'+
		'	</a>'+
		'</li>'+
		'<li class="demo-nav">'+
		'	<a href="#" ><i class="fa fa-cutlery"></i> <span'+
		'		class="">工具 </span><span class="fa arrow"></span>'+
		'	</a>'+
		'	<ul class="nav nav-third-level">'+
		'		<li>'+
		'			<a class="J_menuItem" href="#" rel="html/form_builder.html">表单构建器</a>'+
		'		</li>'+
		'	</ul>'+
		'</li>';
	
	target.append(demoNode);
};


<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="col-sm-12">
	<div class="ibox float-e-margins">
		<div class="ibox-title">
			<h5 id="title">
				头像上传
			</h5>
			<div class="ibox-tools">
				<a class="dropdown-toggle" data-toggle="dropdown"
					href="form_file_upload.html#"> <i class="fa fa-wrench"></i> </a>
				<ul class="dropdown-menu dropdown-user" id="photoSearch">
					<li var="1">
						<a href="#">上传头像</a>
					</li>
					<li var="2">
						<a href="#">上传生活照</a>
					</li>
				</ul>
			</div>
		</div>
		<div class="ibox-content">
			<form id="upLoad" class="dropzone"
				action="../sysPerson/upLoadPhoto.do">
				<div class="dropzone-previews"></div>
				<input name="type" id="type" style="display: none" value="1" />
				<button type="submit" class="btn btn-primary pull-right">
					提交
				</button>
			</form>
			<div>
				<div class="m">
					<small>DropzoneJS是一个开源库，提供拖放文件上传与图片预览：<a
						href="https://github.com/enyo/dropzone" target="_blank">https://github.com/enyo/dropzone</a>
					</small>，百度前端团队提供的
					<a href="http://fex.baidu.com/webuploader/" target="_blank">Web
						Uploader</a>也是一个非常不错的选择，值得一试！
				</div>
			</div>
		</div>
	</div>
</div>
<script>
$(function() {
	$("#upLoad").dropzone({
		autoProcessQueue : false,
		uploadMultiple : false,
		parallelUploads : 100,
		maxFiles : 100,
		paramName:"photo",
		url:"../sysPerson/upLoadPhoto.do",
		init : function() {
		var myDropzone = this;
		this.element.querySelector("button[type=submit]")
			.addEventListener("click", function(e) {
				e.preventDefault();
				e.stopPropagation();
				myDropzone.processQueue()
			});
			this.on("sendingmultiple", function() {
			});
			this.on("successmultiple", function(files, response) {
			});
			this.on("errormultiple", function(files, response) {
			})
		}
	});
	
	$("#photoSearch").on("click","li",postType);
	function postType(){
		var type=$(this).attr('var');
		$("#type").val(type);
		if(type==1){
			$("#title").html("头像上传");
		}else{
			$("#title").html("生活照上传");
		}
	}
});
</script>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>文件上传</title>
    <%@include file="/common/header.jsp" %>
    <meta name="description"
          content="File Upload widget with multiple file selection, drag&amp;drop support, progress bar and preview images for jQuery. Supports cross-domain, chunked and resumable file uploads. Works with any server-side platform (Google App Engine, PHP, Python, Ruby on Rails, Java, etc.) that supports standard HTML form file uploads.">
    <meta name="viewport" content="width=device-width">
    <!-- Bootstrap CSS Toolkit styles -->
    <link rel="stylesheet" href="${ctx}/js/upload/jqueryFileUpload/css/bootstrap.min.css">
    <!-- Generic page styles -->
    <%--<link rel="stylesheet" href="${ctx}/js/upload/jqueryFileUpload/css/style.css">--%>
    <!-- Bootstrap styles for responsive website layout, supporting different screen sizes -->
    <link rel="stylesheet" href="${ctx}/js/upload/jqueryFileUpload/css/bootstrap-responsive.min.css">
    <!-- Bootstrap CSS fixes for IE6 -->
    <!--[if lt IE 7]>
    <link rel="stylesheet" href="${ctx}/js/upload/jqueryFileUpload/css/bootstrap-ie6.min.css"><![endif]-->
    <!-- Bootstrap Image Gallery styles -->
    <link rel="stylesheet" href="${ctx}/js/upload/jqueryFileUpload/css/bootstrap-image-gallery.min.css">
    <!-- CSS to style the file input field as button and adjust the Bootstrap progress bars -->
    <link rel="stylesheet" href="${ctx}/js/upload/jqueryFileUpload/css/jquery.fileupload-ui.css">
    <!-- Shim to make HTML5 elements usable in older Internet Explorer versions -->
    <!--[if lt IE 9]>
    <script src="${ctx}/js/upload/jqueryFileUpload/js/other/html5.js"></script><![endif]-->
</head>
<body>
<div class="container" style="width: 100%;">
    <!-- The file upload form used as target for the file upload widget -->
    <form id="fileuploadForm" action="${ctx}/simpleFileUpload/uploadFile.do" method="post"
          enctype="multipart/form-data">
        <!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
        <div class="row fileupload-buttonbar">
            <c:if test="${canEdit}">
                <div class="span7">
                    <!-- The fileinput-button span is used to style the file input field as button -->
                <span class="btn btn-success fileinput-button">
                    <i class="icon-plus icon-white"></i>
                    <span>添加文件...</span>
                    <input type="file" name="file" multiple>
                </span>
                    <button type="submit" class="btn btn-primary start">
                        <i class="icon-upload icon-white"></i>
                        <span>开始上传</span>
                    </button>
                    <div style="color: #0000ff;">提示：请点击左上角的添加文件，选择企业上报的zbb数据包，文件大小不能超过1GB。</div>
                        <%--<button type="reset" class="btn btn-warning cancel">--%>
                        <%--<i class="icon-ban-circle icon-white"></i>--%>
                        <%--<span>取消上传</span>--%>
                        <%--</button>--%>
                        <%--<button type="button" class="btn btn-danger delete">--%>
                        <%--<i class="icon-trash icon-white"></i>--%>
                        <%--<span>删除</span>--%>
                        <%--</button>--%>
                        <%--&nbsp;&nbsp;全选&nbsp;&nbsp;<input type="checkbox" class="toggle">--%>


                </div>
            </c:if>
            <div class="span5">
                <!-- The global progress bar -->
                <div class="progress progress-success progress-striped active fade">
                    <div class="bar" style="width:0%;"></div>
                </div>
            </div>
        </div>
        <!-- The loading indicator is shown during image processing -->
        <div class="fileupload-loading"></div>
        <br>
        <!-- The table listing the files available for upload/download -->
        <table class="table table-striped">
            <tbody class="files" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody>
        </table>
    </form>
</div>
<!-- modal-gallery is the modal dialog used for the image gallery -->
<div id="modal-gallery" class="modal modal-gallery hide fade">
    <div class="modal-header">
        <a class="close" data-dismiss="modal">&times;</a>

        <h3 class="modal-title"></h3>
    </div>
    <div class="modal-body">
        <div class="modal-image"></div>
    </div>
    <div class="modal-footer">
        <a class="btn modal-download" target="fileDownLoadTargetIframe">
            <i class="icon-download"></i>
            <span>下载</span>
        </a>
        <a class="btn btn-success modal-play modal-slideshow" data-slideshow="5000">
            <i class="icon-play icon-white"></i>
            <span>幻灯片</span>
        </a>
        <a class="btn btn-info modal-prev">
            <i class="icon-arrow-left icon-white"></i>
            <span>向前</span>
        </a>
        <a class="btn btn-primary modal-next">
            <span>向后</span>
            <i class="icon-arrow-right icon-white"></i>
        </a>
    </div>
</div>
<!-- The template to display files available for upload -->
<script id="template-upload" type="text/x-tmpl">
    {% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-upload fade">
        <td class="preview"><span class="fade"></span></td>
        <td class="name"><span>{%=file.name%}</span></td>
        <td class="size"><span>{%=o.formatFileSize(file.size)%}</span></td>
        {% if (file.error) { %}
        <td class="error" colspan="2"><span class="label label-important">{%=locale.fileupload.error%}</span>
            {%=locale.fileupload.errors[file.error] || file.error%}
        </td>
        {% } else if (o.files.valid && !i) { %}
        <td>
            <div class="progress progress-success progress-striped active">
                <div class="bar" style="width:0%;"></div>
            </div>
        </td>
        <td colspan="2"></td>
        <td class="start">{% if (!o.options.autoUpload) { %}
            <button class="btn btn-primary">
                <i class="icon-upload icon-white"></i>
                <span>{%=locale.fileupload.start%}</span>
            </button>
            {% } %}
        </td>
        {% } else { %}
        <td></td>
        {% } %}
        <td class="cancel">{% if (!i) { %}
            <button class="btn btn-warning">
                <i class="icon-ban-circle icon-white"></i>
                <span>{%=locale.fileupload.cancel%}</span>
            </button>
            {% } %}
        </td>
    </tr>
    {% } %}
</script>


<!-- The template to display files available for download -->
<script id="template-download" type="text/x-tmpl">
    {% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-download fade">
        {% if (file.error) { %}
        <td><input type="hidden" name="attachmentId" value="{%=file.attachmentId%}"></td>
        <td class="name"><span>{%=file.name%}</span></td>
        <td class="size"><span>{%=o.formatFileSize(file.size)%}</span></td>
        <td class="error" colspan="2"><span class="label label-important">{%=locale.fileupload.error%}</span>
            {%=locale.fileupload.errors[file.error] || file.error%}
        </td>
        {% } else { %}
        <td class="preview">{% if (file.thumbnail_url) { %}
            <a href="{%=file.url%}" title="{%=file.name%}" rel="gallery" download="{%=file.name%}"><img
                    src="{%=file.thumbnail_url%}" width="40" height="40"></a>
            {% } %}
        </td>

        <td class="name">
            {%=file.name%}
        </td>

        <td class="size"><span>{%=o.formatFileSize(file.size)%}</span></td>
        <td><span>{%=file.updateDate%}</span></td>
        <td><span>{%=file.createUser%}</span></td>
        <td colspan="2"><input type="hidden" name="attachmentId" value="{%=file.attachmentId%}"></td>
        {% } %}

        <td class="text">
            {% if (file.msg == "success") { %}
            <span style="color: #0000ff;">上传成功</span>
            {% } else { %}
            <span style="color: #ff0000;">上传失败</span>
            {% } %}
        </td>

    </tr>
    {% } %}
</script>
<!-- The jQuery UI widget factory, can be omitted if jQuery UI is already included -->
<script src="${ctx}/js/upload/jqueryFileUpload/js/vendor/jquery.ui.widget.js"></script>
<!-- The Templates plugin is included to render the upload/download listings -->
<script src="${ctx}/js/upload/jqueryFileUpload/js/other/tmpl.min.js"></script>
<!-- The Load Image plugin is included for the preview images and image resizing functionality -->
<script src="${ctx}/js/upload/jqueryFileUpload/js/other/load-image.min.js"></script>
<!-- The Canvas to Blob plugin is included for image resizing functionality -->
<script src="${ctx}/js/upload/jqueryFileUpload/js/other/canvas-to-blob.min.js"></script>
<!-- Bootstrap JS and Bootstrap Image Gallery are not required, but included for the demo -->
<script src="${ctx}/js/upload/jqueryFileUpload/js/other/bootstrap.min.js"></script>
<script src="${ctx}/js/upload/jqueryFileUpload/js/other/bootstrap-image-gallery.min.js"></script>
<!-- The Iframe Transport is required for browsers without support for XHR file uploads -->
<script src="${ctx}/js/upload/jqueryFileUpload/js/jquery.iframe-transport.js"></script>
<!-- The basic File Upload plugin -->
<script src="${ctx}/js/upload/jqueryFileUpload/js/jquery.fileupload.js"></script>
<!-- The File Upload image processing plugin -->
<script src="${ctx}/js/upload/jqueryFileUpload/js/jquery.fileupload-ip.js"></script>
<!-- The File Upload user interface plugin -->
<script src="${ctx}/js/upload/jqueryFileUpload/js/jquery.fileupload-ui.js"></script>
<!-- The localization script -->
<script src="${ctx}/js/upload/jqueryFileUpload/js/locale.js"></script>
<!-- The main application script -->
<%--<script src="${ctx}/js/upload/jqueryFileUpload/js/main.js"></script>--%>
<script type="text/javascript">
    //    1024          1KB
    //    1024*1024     1MB
    //    1024*1024     1GB
    var fileSize = 1024 * 1024 * 1024; //1GB
    $(function () {
        'use strict';

        // Initialize the jQuery File Upload widget:
        $('#fileuploadForm').fileupload();

        // Enable iframe cross-exchange access via redirect option:
        $('#fileuploadForm').fileupload(
                'option',
                'redirect',
                window.location.href.replace(
                        /\/[^\/]*$/,
                        '/cors/result.html?%s'
                )
        );

//    if (window.location.hostname === 'blueimp.github.com') {
        // Demo settings:
        $('#fileuploadForm').fileupload('option', {
            url: this.action,
//            maxFileSize:5000000,
            maxFileSize: fileSize,
//            acceptFileTypes:/(\.|\/)(gif|jpe?g|png)$/i,
            acceptFileTypes: /(\.|\/)(${suffix})$/i,
            resizeMaxWidth: 1920,
            resizeMaxHeight: 1200
        });

        // Load existing files:
        $('#fileuploadForm').each(function () {
            var that = this;

        });
//    }
        //创建空的iframe供文件下载用
        var targetIframe = "fileDownLoadTargetIframe";
        if (!window.frames[targetIframe]) {
            var iframe = $(createFrame("", "fileDownLoadTargetIframe"));
            $("body").append($(iframe).hide());
        }
    });
    //    function doFileClose() {
    //        var ele = $(parent.window.document).find("#" + fileUploadId);
    //        if (ele.length <= 0) return;
    //        parent.closeWindow.call(parent.window, fileUploadId);
    //    }
</script>
<!-- The XDomainRequest Transport is included for cross-exchange file deletion for IE8+ -->
<!--[if gte IE 8]>
<script src="${ctx}/js/upload/jqueryFileUpload/js/cors/jquery.xdr-transport.js"></script><![endif]-->
</body>
</html>
